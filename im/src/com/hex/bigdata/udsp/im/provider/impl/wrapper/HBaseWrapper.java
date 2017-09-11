package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import com.hex.bigdata.udsp.im.provider.impl.util.model.SerDeProperty;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class HBaseWrapper extends Wrapper {
    static {
        // 解决winutils.exe不存在的问题
        try {
            File workaround = new File(".");
            System.getProperties().put("hadoop.home.dir",
                    workaround.getAbsolutePath());
            new File("./bin").mkdirs();
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger logger = LogManager.getLogger(HBaseWrapper.class);
    private static Map<String, HBaseConnectionPoolFactory> dataSourcePool;

    protected synchronized HBaseConnectionPoolFactory getDataSource(HBaseDatasource datasource) {
        String dsId = datasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<>();
        }
        HBaseConnectionPoolFactory factory = dataSourcePool.get(dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config();
            config.lifo = true;
            config.minIdle = 1;
            config.maxActive = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new HBaseConnectionPoolFactory(config, datasource.getZkQuorum(), datasource.getZkPort());
        }
        dataSourcePool.put(dsId, factory);
        return factory;
    }

    protected HConnection getConnection(HBaseDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    protected void release(HBaseDatasource datasource, HConnection conn) {
        getDataSource(datasource).releaseConnection(conn);
    }

    protected List<TableColumn> getTargetColumns(List<ModelMapping> modelMappings, HBaseMetadata hbaseMetadata) {
        List<TableColumn> columns = new ArrayList<>();
        columns.add(new TableColumn("key", DataType.STRING.getValue()));
        columns.add(new TableColumn("val", DataType.STRING.getValue()));
        return columns;
    }

    protected List<TblProperty> getTblProperties(String tableName) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("hbase.table.name", tableName));
        return tblProperties;
    }

    protected String getTargetTableName(String fullTbName, String id) {
        String[] strs = fullTbName.split(DATABASE_AND_TABLE_SEP);
        String dbName = null;
        String tbName = null;
        if (strs.length == 1) {
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
        } else if (strs.length == 2) {
            dbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[1];
        }
        String tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + id;
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + dbName + HIVE_ENGINE_TABLE_SEP + tbName + HIVE_ENGINE_TABLE_SEP + id;
        } else if (StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + tbName + HIVE_ENGINE_TABLE_SEP + id;
        }
        return tableName;
    }

    protected List<SerDeProperty> getSerDeProperties(List<ModelMapping> modelMappings, HBaseMetadata hbaseMetadata) {
        List<SerDeProperty> serDeProperties = new ArrayList<>();
        String family = hbaseMetadata.getFamily();
        String qualifier = hbaseMetadata.getQualifier();
        String hbaseColumnsMapping = ":key," + family + ":" + qualifier;
        serDeProperties.add(new SerDeProperty("hbase.columns.mapping", hbaseColumnsMapping));
        return serDeProperties;
    }
}
