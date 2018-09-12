package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
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
    private static Logger logger = LoggerFactory.getLogger(SolrHBaseConverter.class);

    private SolrConverter solrConverter = (SolrConverter) ObjectUtil.newInstance("com.hex.bigdata.udsp.im.converter.impl.SolrConverter");
    private HBaseConverter hbaseConverter = (HBaseConverter) ObjectUtil.newInstance("com.hex.bigdata.udsp.im.converter.impl.HBaseConverter");

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        String collectionName = metadata.getTbName();
        Datasource datasource = metadata.getDatasource();
        String solrServers = datasource.getPropertyMap().get("solr.servers").getValue();
        return solrConverter.getColumns(collectionName, solrServers);
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        hbaseConverter.createSchema(metadata);

        try {
            List<MetadataCol> newMetadataCols = new ArrayList<>();
            for (MetadataCol metadataCol : metadata.getMetadataCols()) {
                if (metadataCol.isPrimary() || metadataCol.isIndexed()) { // 是主键或索引字段
                    if (metadataCol.isIndexed()) { // 索引字段，必须不存储
                        metadataCol.setStored(false);
                    }
                    newMetadataCols.add(metadataCol);
                }
            }
            metadata.setMetadataCols(newMetadataCols);
            solrConverter.createSchema(metadata);
        } catch (Exception e) {
            // 回滚删除hbase对应的表 删除失败怎么办？暂时不考虑
            hbaseConverter.dropSchema(metadata);
            throw new Exception(e);
        }
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        solrConverter.dropSchema(metadata);
        hbaseConverter.dropSchema(metadata);
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        return solrConverter.checkSchema(metadata) && hbaseConverter.checkSchema(metadata);
    }

    @Override
    public void addColumns(Metadata metadata, List<MetadataCol> addMetadataCols) throws Exception {
        hbaseConverter.addColumns(metadata, addMetadataCols);

        List<MetadataCol> newMetadataCols = new ArrayList<>();
        for (MetadataCol metadataCol : metadata.getMetadataCols()) {
            if (metadataCol.isPrimary() || metadataCol.isIndexed()) { // 是主键或索引字段
                if (metadataCol.isIndexed()) { // 索引字段，必须不存储
                    metadataCol.setStored(false);
                }
                newMetadataCols.add(metadataCol);
            }
        }
        metadata.setMetadataCols(newMetadataCols);

        List<MetadataCol> newAddMetadataCols = new ArrayList<>();
        for (MetadataCol metadataCol : addMetadataCols) {
            if (metadataCol.isPrimary() || metadataCol.isIndexed()) { // 是主键或索引字段
                if (metadataCol.isIndexed()) { // 索引字段，必须不存储
                    metadataCol.setStored(false);
                }
                newAddMetadataCols.add(metadataCol);
            }
        }

        solrConverter.addColumns(metadata, newAddMetadataCols);
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        String id = model.getId();

        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        hbaseConverter.createTargetEngineSchema(hBaseModel);

        try {
            Model solrModel = new Model(model);
            solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
            List<ModelMapping> newModelMappings = new ArrayList<>();
            for (ModelMapping modelMapping : solrModel.getModelMappings()) {
                MetadataCol metadataCol = modelMapping.getMetadataCol();
                if (metadataCol.isPrimary() || metadataCol.isIndexed()) { // 是主键或索引字段
                    newModelMappings.add(modelMapping);
                }
            }
            solrModel.setModelMappings(newModelMappings);
            solrConverter.createTargetEngineSchema(solrModel);
        } catch (Exception e) {
            // 删除回滚hbase对应的目标引擎表 删除失败怎么办？暂时不考虑
            hbaseConverter.dropTargetEngineSchema(hBaseModel);
            throw new Exception(e);
        }
    }

    @Override
    public void dropTargetEngineSchema(Model model) throws SQLException {
        String id = model.getId();

        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        hbaseConverter.dropTargetEngineSchema(solrModel);

        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        solrConverter.dropTargetEngineSchema(hBaseModel);
    }

    @Override
    public RealtimeResponse buildRealtime(String key, Model model) {
        String id = model.getId();
        // 将配置的group.id参数删除，强制内部使用modeId作为group.id
        model.getPropertyMap().remove("group.id");

        Model hbaseModel = new Model(model);
        hbaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        List<ModelMapping> newModelMappings = new ArrayList<>();
        for (ModelMapping modelMapping : hbaseModel.getModelMappings()) {
            MetadataCol metadataCol = modelMapping.getMetadataCol();
            if (metadataCol.isPrimary() || metadataCol.isStored()) { // 是主键或存储字段
                newModelMappings.add(modelMapping);
            }
        }
        hbaseModel.setModelMappings(newModelMappings);
        RealtimeResponse hbaseRealtimeResponse = hbaseConverter.buildRealtime(key, hbaseModel);

        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        newModelMappings = new ArrayList<>();
        for (ModelMapping modelMapping : solrModel.getModelMappings()) {
            MetadataCol metadataCol = modelMapping.getMetadataCol();
            if (metadataCol.isPrimary() || metadataCol.isIndexed()) { // 是主键或索引字段
                newModelMappings.add(modelMapping);
            }
        }
        solrModel.setModelMappings(newModelMappings);
        RealtimeResponse solrRealtimeResponse = solrConverter.buildRealtime(key, solrModel);

        RealtimeResponse realtimeResponse = new RealtimeResponse();
        realtimeResponse.setCountNum(hbaseRealtimeResponse.getCountNum() + solrRealtimeResponse.getCountNum());
        realtimeResponse.setMessage("HBASE:" + hbaseRealtimeResponse.getMessage() + ", SOLR:" + solrRealtimeResponse.getMessage());
        realtimeResponse.setFilterNum(hbaseRealtimeResponse.getFilterNum() + solrRealtimeResponse.getFilterNum());
        realtimeResponse.setParseFailedNum(hbaseRealtimeResponse.getParseFailedNum() + solrRealtimeResponse.getParseFailedNum());
        realtimeResponse.setStoreSucceedNum(hbaseRealtimeResponse.getStoreSucceedNum() + solrRealtimeResponse.getStoreSucceedNum());
        realtimeResponse.setStoreFailedNum(hbaseRealtimeResponse.getStoreFailedNum() + solrRealtimeResponse.getStoreFailedNum());
        return realtimeResponse;
    }

    @Override
    public void buildBatch(String key, Model model) throws Exception {
        String id = model.getId();

        Model hbaseModel = new Model(model);
        // model的Id在建引擎表时会用到，所以需要区分
        hbaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        List<ModelMapping> newModelMappings = new ArrayList<>();
        for (ModelMapping modelMapping : hbaseModel.getModelMappings()) {
            MetadataCol metadataCol = modelMapping.getMetadataCol();
            if (metadataCol.isPrimary() || metadataCol.isStored()) { // 是主键或存储字段
                newModelMappings.add(modelMapping);
            }
        }
        hbaseModel.setModelMappings(newModelMappings);
        // build的key在监控中会用到，所以需要区分
        hbaseConverter.buildBatch("HBASE" + HIVE_ENGINE_TABLE_SEP + key, hbaseModel);

        Model solrModel = new Model(model);
        // model的Id在建引擎表时会用到，所以需要区分
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        newModelMappings = new ArrayList<>();
        for (ModelMapping modelMapping : solrModel.getModelMappings()) {
            MetadataCol metadataCol = modelMapping.getMetadataCol();
            if (metadataCol.isPrimary() || metadataCol.isIndexed()) { // 是主键或索引字段
                newModelMappings.add(modelMapping);
            }
        }
        solrModel.setModelMappings(newModelMappings);
        // build的key在监控中会用到，所以需要区分
        solrConverter.buildBatch("SOLR" + HIVE_ENGINE_TABLE_SEP + key, solrModel);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        return hbaseConverter.testDatasource(datasource)
                && solrConverter.testDatasource(datasource);
    }
}
