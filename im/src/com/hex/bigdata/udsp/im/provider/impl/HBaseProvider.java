package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcProviderUtil;
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
public class HBaseProvider extends HBaseWrapper implements RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(HBaseProvider.class);
    private static final String HIVE_ENGINE_STORAGE_HANDLER_CLASS = "org.apache.hadoop.hive.hbase.HBaseStorageHandler";

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        // HBase无法获取字段信息，返回null
        return null;
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        //HBaseDatasource datasource = new HBaseDatasource(metadata.getDatasource().getPropertyMap());
        //int numRegions = Integer.parseInt(metadata.getPropertyMap().get("hbase.region.num").getValue());
        //String family = metadata.getPropertyMap().get("hbase.family").getValue();
        //return createHTable(datasource, metadata.getTbName(),numRegions,true,true, family);
        return createHTable(new HBaseMetadata(metadata.getPropertyMap()));
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        return dropHTable(new HBaseDatasource(metadata.getDatasource().getPropertyMap()), metadata.getTbName());
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws Exception {
        Metadata md = model.getTargetMetadata();
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        String id = model.getId();
        HBaseMetadata hbaseMetadata = new HBaseMetadata(md.getPropertyMap());
        String fullTbName = hbaseMetadata.getTbName();
        String tableName = getTargetTableName(id);
        List<ModelMapping> modelMappings = model.getModelMappings();
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                getTargetColumns(modelMappings, hbaseMetadata), "目标的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, getSerDeProperties(modelMappings, hbaseMetadata),
                getTblProperties(fullTbName));
        return JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
    }

    @Override
    public void inputData() {
        // TODO ...
    }

    @Override
    public boolean checkTableExists(Metadata metadata) throws Exception {
        return false;
    }
}
