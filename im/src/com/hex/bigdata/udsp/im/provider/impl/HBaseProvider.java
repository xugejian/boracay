package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.constant.DatasourceType;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HiveMetadata;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcProviderUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.SerDeProperty;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.HBaseWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.HBaseProvider")
public class HBaseProvider extends HBaseWrapper implements RealtimeTargetProvider, BatchTargetProvider {
    private static Logger logger = LogManager.getLogger(HBaseProvider.class);
    private static final String HIVE_ENGINE_STORAGE_HANDLER_CLASS = "org.apache.hadoop.hive.hbase.HBaseStorageHandler";

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        // HBase无法获取字段信息
        return null;
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }

    @Override
    public boolean createEngineSchema(Model model) throws Exception {
        boolean status = false;
        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        Metadata md = model.getTargetMetadata();
        Datasource tDs = md.getDatasource();
        String tDsType = tDs.getType();
        Datasource eDs = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(eDs.getPropertyMap());
        String id = model.getId();
        // 作为源
        if (DatasourceType.HBASE.getValue().equals(sDsType)) {
            throw new UnsupportedOperationException("暂不支持HBase作为源");
        }
        // 作为目标
        if (DatasourceType.HBASE.getValue().equals(tDsType)) {
            HBaseMetadata hbaseMetadata = new HBaseMetadata(md.getPropertyMap());
            String fullTbName = hbaseMetadata.getTbName();
            String tableName = getTargetTableName(fullTbName, id);
            List<ModelMapping> modelMappings = model.getModelMappings();
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getTargetColumns(modelMappings, hbaseMetadata), "目标的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, getSerDeProperties(modelMappings, hbaseMetadata),
                    getTblProperties(fullTbName));
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
        }
        return status;
    }

    @Override
    public boolean dropEngineSchema(Model model) throws Exception {
        boolean status = false;
        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        Metadata md = model.getTargetMetadata();
        Datasource tDs = md.getDatasource();
        String tDsType = tDs.getType();
        Datasource eDs = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(eDs.getPropertyMap());
        String id = model.getId();
        // 作为源
        if (DatasourceType.HBASE.getValue().equals(sDsType)) {
            throw new UnsupportedOperationException("暂不支持HBase作为源");
        }
        // 作为目标
        if (DatasourceType.HBASE.getValue().equals(tDsType)) {
            String tableName = getTargetTableName(md.getTbName(), id);
            String sql = HiveSqlUtil.dropTable(true, tableName);
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
        }
        return status;
    }
}
