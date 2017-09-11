package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.constant.DatasourceType;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.SolrMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.SolrModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcProviderUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrProvider")
public class SolrProvider extends SolrWrapper implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(SolrProvider.class);
    private static final String HIVE_ENGINE_STORAGE_HANDLER_CLASS = "com.hex.hive.solr.SolrStorageHandler";

    private List<MetadataCol> getColumns(SolrServer solrServer) {

        // TODO ..... 获取Solr字段信息

        return null;
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        String collectionName = metadata.getTbName();
        SolrServer solrServer = getConnection(solrDatasource, collectionName);
        return getColumns(solrServer);
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
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getSourceDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        SolrModel solrModel = new SolrModel(datasource.getPropertyMap());
        String collectionName = solrModel.getCollectionName();
        SolrServer solrServer = getConnection(solrDatasource, collectionName);
        return getColumns(solrServer);
    }

    @Override
    public String inputSQL() {
        return null;
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
        if (DatasourceType.SOLR.getValue().equals(sDsType)) {
            SolrModel solrModel = new SolrModel(model.getPropertyMap());
            String collectionName = solrModel.getCollectionName();
            String tableName = getSourceTableName(null, collectionName, id);
            SolrDatasource solrDs = new SolrDatasource(sDs.getPropertyMap());
            List<ModelMapping> modelMappings = model.getModelMappings();
            String pkName = getSourcePrimaryKey(modelMappings);
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getSourceColumns(modelMappings), "源的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(solrDs, pkName, collectionName));
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
            if (!status) return status;
        }
        // 作为目标
        if (DatasourceType.SOLR.getValue().equals(tDsType)) {
            SolrMetadata solrMetadata = new SolrMetadata(md.getPropertyMap());
            String collectionName = solrMetadata.getTbName();
            String tableName = getTargetTableName(collectionName, id);
            SolrDatasource solrDs = new SolrDatasource(tDs.getPropertyMap());
            List<ModelMapping> modelMappings = model.getModelMappings();
            String pkName = getTargetPrimaryKey(modelMappings);
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getTargetColumns(modelMappings), "目标的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(solrDs, pkName, collectionName));
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
        if (DatasourceType.SOLR.getValue().equals(sDsType)) {
            SolrModel solrModel = new SolrModel(model.getPropertyMap());
            String tableName = getSourceTableName(null, solrModel.getCollectionName(), id);
            String sql = HiveSqlUtil.dropTable(true, tableName);
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
            if (!status) return status;
        }
        // 作为目标
        if (DatasourceType.SOLR.getValue().equals(tDsType)) {
            SolrMetadata solrMetadata = new SolrMetadata(md.getPropertyMap());
            String tableName = getTargetTableName(solrMetadata.getTbName(), id);
            String sql = HiveSqlUtil.dropTable(true, tableName);
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
        }
        return status;
    }
}
