package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.im.converter.impl.model.SolrMetadata;
import com.hex.bigdata.udsp.im.converter.impl.wrapper.SolrHBaseWrapper;
import com.hex.bigdata.udsp.im.converter.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
//@Component("com.hex.bigdata.udsp.im.converter.impl.SolrHBaseConverter")
public class SolrHBaseConverter extends SolrHBaseWrapper {
    private static Logger logger = LoggerFactory.getLogger (SolrHBaseConverter.class);

    private SolrConverter solrConverter = (SolrConverter) ObjectUtil.newInstance ("com.hex.bigdata.udsp.im.converter.impl.SolrConverter");
    private HBaseConverter hbaseConverter = (HBaseConverter) ObjectUtil.newInstance ("com.hex.bigdata.udsp.im.converter.impl.HBaseConverter");

    @Override
    public boolean testDatasource(Datasource datasource) {
        return hbaseConverter.testDatasource (datasource)
                && solrConverter.testDatasource (datasource);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        return solrConverter.columnInfo (metadata);
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        hbaseConverter.createSchema (metadata);
        try {
            solrConverter.createSchema (getSolrMetadata (metadata));
        } catch (Exception e) {
            // 回滚删除hbase对应的表 删除失败怎么办？暂时不考虑
            hbaseConverter.dropSchema (metadata);
            throw new Exception (e);
        }
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        solrConverter.dropSchema (metadata);
        hbaseConverter.dropSchema (metadata);
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        return solrConverter.checkSchema (metadata)
                && hbaseConverter.checkSchema (metadata);
    }

    @Override
    public void addColumns(Metadata metadata, List<MetadataCol> addMetadataCols) throws Exception {
        hbaseConverter.addColumns (metadata, addMetadataCols);
        solrConverter.addColumns (getSolrMetadata (metadata), getSolrMetadataCols (addMetadataCols));
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        Model hBaseModel = getHBaseModel (model);
        hbaseConverter.createTargetEngineSchema (hBaseModel);
        try {
            solrConverter.createTargetEngineSchema (getSolrModel (model));
        } catch (Exception e) {
            // 删除回滚hbase对应的目标引擎表 删除失败怎么办？暂时不考虑
            hbaseConverter.dropTargetEngineSchema (hBaseModel);
            throw new Exception (e);
        }
    }

    @Override
    public void dropTargetEngineSchema(Model model) throws SQLException {
        hbaseConverter.dropTargetEngineSchema (getHBaseModel (model));
        solrConverter.dropTargetEngineSchema (getSolrModel (model));
    }

    /**
     * 重构buildRealtime
     *
     * @param key
     * @param model
     * @throws Exception
     */
    @Override
    public void buildBatch(String key, Model model) throws Exception {
        hbaseConverter.buildBatch (getHBaseKey (key), getHBaseModel (model));
        solrConverter.buildBatch (getSolrKey (key), getSolrModel (model));
    }

    /**
     * 重构buildRealtime
     *
     * @param key
     * @param model
     * @return
     */
    @Override
    public RealtimeResponse buildRealtime(String key, Model model) {
        RealtimeResponse hbaseRealtimeResponse = hbaseConverter.buildRealtime (key, getHBaseModel (model));
        RealtimeResponse solrRealtimeResponse = solrConverter.buildRealtime (key, getSolrModel (model));
        RealtimeResponse realtimeResponse = new RealtimeResponse ();
        realtimeResponse.setCountNum (hbaseRealtimeResponse.getCountNum () + solrRealtimeResponse.getCountNum ());
        realtimeResponse.setMessage ("HBASE:" + hbaseRealtimeResponse.getMessage () + ", SOLR:" + solrRealtimeResponse.getMessage ());
        realtimeResponse.setFilterNum (hbaseRealtimeResponse.getFilterNum () + solrRealtimeResponse.getFilterNum ());
        realtimeResponse.setParseFailedNum (hbaseRealtimeResponse.getParseFailedNum () + solrRealtimeResponse.getParseFailedNum ());
        realtimeResponse.setStoreSucceedNum (hbaseRealtimeResponse.getStoreSucceedNum () + solrRealtimeResponse.getStoreSucceedNum ());
        realtimeResponse.setStoreFailedNum (hbaseRealtimeResponse.getStoreFailedNum () + solrRealtimeResponse.getStoreFailedNum ());
        return realtimeResponse;
    }

    private SolrMetadata getSolrMetadata(Metadata metadata) {
        SolrMetadata solrMetadata = new SolrMetadata (metadata);
        solrMetadata.setMetadataCols (getSolrMetadataCols (metadata.getMetadataCols ()));
        return solrMetadata;
    }

    private List<MetadataCol> getSolrMetadataCols(List<MetadataCol> metadataCols) {
        List<MetadataCol> newMetadataCols = new ArrayList<> ();
        for (MetadataCol metadataCol : metadataCols) {
            if (metadataCol.isPrimary () || metadataCol.isIndexed ()) { // 是主键或索引字段
                if (metadataCol.isIndexed ()) { // 索引字段，必须不存储
                    metadataCol.setStored (false);
                }
                newMetadataCols.add (metadataCol);
            }
        }
        return newMetadataCols;
    }

    private Model getHBaseModel(Model model) {
        Model hBaseModel = new Model (model);
        hBaseModel.setId ("HBASE" + HIVE_ENGINE_TABLE_SEP + model.getId ());
        // 如果参数中有group.id则说明是实时运行，由于相同组消费是抢占式的所以这里需要将配置的group.id参数需要修改
        Property groupId = model.getPropertyMap ().get ("group.id");
        if (groupId != null) {
            groupId.setValue ("HBASE" + HIVE_ENGINE_TABLE_SEP + groupId.getValue ());
        }
        List<ModelMapping> modelMappings = new ArrayList<> ();
        for (ModelMapping modelMapping : hBaseModel.getModelMappings ()) {
            MetadataCol metadataCol = modelMapping.getMetadataCol ();
            if (metadataCol.isPrimary () || metadataCol.isStored ()) { // 是主键或存储字段
                modelMappings.add (modelMapping);
            }
        }
        hBaseModel.setModelMappings (modelMappings);
        return hBaseModel;
    }

    private Model getSolrModel(Model model) {
        Model solrModel = new Model (model);
        solrModel.setId ("SOLR" + HIVE_ENGINE_TABLE_SEP + model.getId ());
        // 如果参数中有group.id则说明是实时运行，由于相同组消费是抢占式的所以这里需要将配置的group.id参数需要修改
        Property groupId = model.getPropertyMap ().get ("group.id");
        if (groupId != null) {
            groupId.setValue ("SOLR" + HIVE_ENGINE_TABLE_SEP + groupId.getValue ());
        }
        List<ModelMapping> modelMappings = new ArrayList<> ();
        for (ModelMapping modelMapping : solrModel.getModelMappings ()) {
            MetadataCol metadataCol = modelMapping.getMetadataCol ();
            if (metadataCol.isPrimary () || metadataCol.isIndexed ()) { // 是主键或索引字段
                modelMappings.add (modelMapping);
            }
        }
        solrModel.setModelMappings (modelMappings);
        return solrModel;
    }

    private String getHBaseKey(String key) {
        return "HBASE" + HIVE_ENGINE_TABLE_SEP + key;
    }

    private String getSolrKey(String key) {
        return "SOLR" + HIVE_ENGINE_TABLE_SEP + key;
    }
}
