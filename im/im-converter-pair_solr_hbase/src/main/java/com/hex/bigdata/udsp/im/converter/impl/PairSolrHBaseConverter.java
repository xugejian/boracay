package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.converter.impl.wrapper.PairSolrHBaseWrapper;
import com.hex.bigdata.udsp.im.converter.model.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2019-5-28.
 */
public class PairSolrHBaseConverter extends PairSolrHBaseWrapper {
    private static Logger logger = LoggerFactory.getLogger (PairSolrHBaseConverter.class);

    private PairSolrConverter pairSolrConverter = (PairSolrConverter) ObjectUtil.newInstance ("com.hex.bigdata.udsp.im.converter.impl.PairSolrConverter");
    private PairHBaseConverter pairHBaseConverter = (PairHBaseConverter) ObjectUtil.newInstance ("com.hex.bigdata.udsp.im.converter.impl.PairHBaseConverter");

    @Override
    public boolean testDatasource(Datasource datasource) {
        return pairHBaseConverter.testDatasource (getHBaseDatasource (datasource))
                && pairSolrConverter.testDatasource (getSolrDatasource (datasource));
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        return pairSolrConverter.columnInfo (getSolrMetadata (metadata));
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        Metadata hbaseMetadata = getHBaseMetadata (metadata);
        pairHBaseConverter.createSchema (hbaseMetadata);
        try {
            pairSolrConverter.createSchema (getSolrMetadata (metadata));
        } catch (Exception e) {
            // 回滚删除hbase对应的表 删除失败怎么办？暂时不考虑
            pairHBaseConverter.dropSchema (hbaseMetadata);
            throw new Exception (e);
        }
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        pairSolrConverter.dropSchema (getSolrMetadata (metadata));
        pairHBaseConverter.dropSchema (getHBaseMetadata (metadata));
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        return pairSolrConverter.checkSchema (getSolrMetadata (metadata))
                && pairHBaseConverter.checkSchema (getHBaseMetadata (metadata));
    }

    @Override
    public void addColumns(Metadata metadata, List<MetadataCol> addMetadataCols) throws Exception {
        pairHBaseConverter.addColumns (getHBaseMetadata (metadata), addMetadataCols);
        pairSolrConverter.addColumns (getSolrMetadata (metadata), getSolrMetadataCols (addMetadataCols));
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        Model hBaseModel = getHBaseModel (model);
        pairHBaseConverter.createTargetEngineSchema (hBaseModel);
        try {
            pairSolrConverter.createTargetEngineSchema (getSolrModel (model));
        } catch (Exception e) {
            // 删除回滚hbase对应的目标引擎表 删除失败怎么办？暂时不考虑
            pairHBaseConverter.dropTargetEngineSchema (hBaseModel);
            throw new Exception (e);
        }
    }

    /**
     * 重构dropTargetEngineSchema
     *
     * @param model
     * @throws SQLException
     */
    @Override
    public void dropTargetEngineSchema(Model model) throws SQLException {
        pairHBaseConverter.dropTargetEngineSchema (getHBaseModel (model));
        pairSolrConverter.dropTargetEngineSchema (getSolrModel (model));
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
        pairHBaseConverter.buildBatch (getHBaseKey (key), getHBaseModel (model));
        pairSolrConverter.buildBatch (getSolrKey (key), getSolrModel (model));
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
        RealtimeResponse hbaseRealtimeResponse = pairHBaseConverter.buildRealtime (key, getHBaseModel (model));
        RealtimeResponse solrRealtimeResponse = pairSolrConverter.buildRealtime (key, getSolrModel (model));
        RealtimeResponse realtimeResponse = new RealtimeResponse ();
        realtimeResponse.setCountNum (hbaseRealtimeResponse.getCountNum () + solrRealtimeResponse.getCountNum ());
        realtimeResponse.setMessage ("HBASE:" + hbaseRealtimeResponse.getMessage () + ", SOLR:" + solrRealtimeResponse.getMessage ());
        realtimeResponse.setFilterNum (hbaseRealtimeResponse.getFilterNum () + solrRealtimeResponse.getFilterNum ());
        realtimeResponse.setParseFailedNum (hbaseRealtimeResponse.getParseFailedNum () + solrRealtimeResponse.getParseFailedNum ());
        realtimeResponse.setStoreSucceedNum (hbaseRealtimeResponse.getStoreSucceedNum () + solrRealtimeResponse.getStoreSucceedNum ());
        realtimeResponse.setStoreFailedNum (hbaseRealtimeResponse.getStoreFailedNum () + solrRealtimeResponse.getStoreFailedNum ());
        return realtimeResponse;
    }

    private Datasource getHBaseDatasource(Datasource datasource) {
        Datasource ds = new Datasource (datasource);
        ds.setType (DatasourceType.HBASE.getValue ());
        return ds;
    }

    private Datasource getSolrDatasource(Datasource datasource) {
        Datasource ds = new Datasource (datasource);
        ds.setType (DatasourceType.SOLR.getValue ());
        return ds;
    }

    private Metadata getHBaseMetadata(Metadata metadata) {
        Metadata hbaseMetadata = new Metadata (metadata);
        hbaseMetadata.setDatasource (getHBaseDatasource (metadata.getDatasource ()));
        // HBase真实表名称
        String hbaseNamespace = metadata.gainProperty ("hbase.namespace").getValue ();
        if (StringUtils.isNotBlank (hbaseNamespace) && !"default".equalsIgnoreCase (hbaseNamespace)) {
            hbaseMetadata.setTbName (hbaseNamespace + ":" + metadata.getTbName ());
        }
        return hbaseMetadata;
    }

    private Metadata getSolrMetadata(Metadata metadata) {
        Metadata solrMetadata = new Metadata (metadata);
        solrMetadata.setMetadataCols (getSolrMetadataCols (metadata.getMetadataCols ()));
        solrMetadata.setDatasource (getSolrDatasource (metadata.getDatasource ()));
        return solrMetadata;
    }

    private List<MetadataCol> getSolrMetadataCols(List<MetadataCol> metadataCols) {
        if (metadataCols == null) {
            return null;
        }
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
        hBaseModel.setTargetMetadata (getHBaseMetadata (model.getTargetMetadata ()));
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
        solrModel.setTargetMetadata (getSolrMetadata (model.getTargetMetadata ()));
        return solrModel;
    }

    private String getHBaseKey(String key) {
        return "HBASE" + HIVE_ENGINE_TABLE_SEP + key;
    }

    private String getSolrKey(String key) {
        return "SOLR" + HIVE_ENGINE_TABLE_SEP + key;
    }
}
