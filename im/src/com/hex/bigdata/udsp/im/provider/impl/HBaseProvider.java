package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.HBaseProvider")
public class HBaseProvider implements RealtimeTargetProvider, BatchTargetProvider {

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

    private static Logger logger = LogManager.getLogger(HBaseProvider.class);
    private static Map<String, HBaseConnectionPoolFactory> dataSourcePool;

    private synchronized HBaseConnectionPoolFactory getDataSource(HBaseDatasource datasource) {
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

    private HConnection getConnection(HBaseDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    private void release(HBaseDatasource datasource, HConnection conn) {
        getDataSource(datasource).releaseConnection(conn);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        // HBase无法获取字段信息
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void drop() {

    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }
}
