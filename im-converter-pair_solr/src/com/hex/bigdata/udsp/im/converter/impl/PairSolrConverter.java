package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.im.converter.impl.wrapper.PairSolrWrapper;
import com.hex.bigdata.udsp.im.converter.model.Metadata;
import com.hex.bigdata.udsp.im.converter.model.MetadataCol;
import com.hex.bigdata.udsp.im.converter.model.Model;
import com.hex.bigdata.udsp.im.converter.model.RealtimeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunjieM on 2019-5-22.
 */
public class PairSolrConverter extends PairSolrWrapper {
    private static Logger logger = LoggerFactory.getLogger (PairSolrConverter.class);

    private SolrConverter solrConverter = (SolrConverter) ObjectUtil.newInstance ("com.hex.bigdata.udsp.im.converter.impl.SolrConverter");

    @Override
    public boolean testDatasource(Datasource datasource) {
        return solrConverter.testDatasource (getActiveDatasource (datasource))
                && solrConverter.testDatasource (getStandbyDatasource (datasource));
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        return solrConverter.columnInfo (getActiveMetadata (metadata));
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        Metadata activeMetadata = getActiveMetadata (metadata);
        solrConverter.createSchema (activeMetadata);
        try {
            solrConverter.createSchema (getStandbyMetadata (metadata));
        } catch (Exception e) {
            // 回滚删除solr（主）对应的表 删除失败怎么办？暂时不考虑
            solrConverter.dropSchema (activeMetadata);
            throw new Exception (e);
        }
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        solrConverter.dropSchema (getActiveMetadata (metadata));
        solrConverter.dropSchema (getStandbyMetadata (metadata));
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        return solrConverter.checkSchema (getActiveMetadata (metadata))
                && solrConverter.checkSchema (getStandbyMetadata (metadata));
    }

    @Override
    public void addColumns(Metadata metadata, List<MetadataCol> addMetadataCols) throws Exception {
        solrConverter.addColumns (getActiveMetadata (metadata), addMetadataCols);
        solrConverter.addColumns (getStandbyMetadata (metadata), addMetadataCols);
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        Model activeModel = getActiveModel (model);
        solrConverter.createTargetEngineSchema (activeModel);
        try {
            solrConverter.createTargetEngineSchema (getStandbyModel (model));
        } catch (Exception e) {
            // 删除回滚solr（主）对应的目标引擎表 删除失败怎么办？暂时不考虑
            solrConverter.dropTargetEngineSchema (activeModel);
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
        solrConverter.dropTargetEngineSchema (getStandbyModel (model));
        solrConverter.dropTargetEngineSchema (getActiveModel (model));
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
        solrConverter.buildBatch (getActiveKey (key), getActiveModel (model));
        solrConverter.buildBatch (getStandbyKey (key), getStandbyModel (model));
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
        RealtimeResponse activeRealtimeResponse = solrConverter.buildRealtime (key, getActiveModel (model));
        RealtimeResponse standbyRealtimeResponse = solrConverter.buildRealtime (key, getStandbyModel (model));
        RealtimeResponse realtimeResponse = new RealtimeResponse ();
        realtimeResponse.setCountNum (activeRealtimeResponse.getCountNum () + standbyRealtimeResponse.getCountNum ());
        realtimeResponse.setMessage ("ACTIVE:" + activeRealtimeResponse.getMessage () + ", STANDBY:" + standbyRealtimeResponse.getMessage ());
        realtimeResponse.setFilterNum (activeRealtimeResponse.getFilterNum () + standbyRealtimeResponse.getFilterNum ());
        realtimeResponse.setParseFailedNum (activeRealtimeResponse.getParseFailedNum () + standbyRealtimeResponse.getParseFailedNum ());
        realtimeResponse.setStoreSucceedNum (activeRealtimeResponse.getStoreSucceedNum () + standbyRealtimeResponse.getStoreSucceedNum ());
        realtimeResponse.setStoreFailedNum (activeRealtimeResponse.getStoreFailedNum () + standbyRealtimeResponse.getStoreFailedNum ());
        return realtimeResponse;
    }

    private Datasource getActiveDatasource(Datasource datasource) {
        Datasource ds = new Datasource (datasource);
        Property solrServers = datasource.getPropertyMap ().get ("active.solr.servers");
        ds.getPropertyMap ().put ("solr.servers", solrServers);
        Property solrUrl = datasource.getPropertyMap ().get ("active.solr.url");
        ds.getPropertyMap ().put ("solr.url", solrUrl);
        return ds;
    }

    private Datasource getStandbyDatasource(Datasource datasource) {
        Datasource ds = new Datasource (datasource);
        Property zkQuorum = datasource.getPropertyMap ().get ("standby.solr.servers");
        ds.getPropertyMap ().put ("solr.servers", zkQuorum);
        Property solrUrl = datasource.getPropertyMap ().get ("standby.solr.url");
        ds.getPropertyMap ().put ("solr.url", solrUrl);
        return ds;
    }

    private Metadata getActiveMetadata(Metadata metadata) {
        Metadata md = new Metadata (metadata);
        Datasource datasource = getActiveDatasource (metadata.getDatasource ());
        md.setDatasource (datasource);
        return md;
    }

    private Metadata getStandbyMetadata(Metadata metadata) {
        Metadata md = new Metadata (metadata);
        Datasource datasource = getStandbyDatasource (metadata.getDatasource ());
        md.setDatasource (datasource);
        return md;
    }

    private Model getActiveModel(Model model) {
        Model activeModel = new Model (model);
        activeModel.setId ("ACTIVE" + HIVE_ENGINE_TABLE_SEP + model.getId ());
        // 如果参数中有group.id则说明是实时运行，由于相同组消费是抢占式的所以这里需要将配置的group.id参数需要修改
        Property groupId = model.getPropertyMap ().get ("group.id");
        if (groupId != null) {
            groupId.setValue ("ACTIVE" + HIVE_ENGINE_TABLE_SEP + groupId.getValue ());
        }
        Metadata metadata = getActiveMetadata (model.getTargetMetadata ());
        activeModel.setTargetMetadata (metadata);
        return activeModel;
    }

    private Model getStandbyModel(Model model) {
        Model standbyModel = new Model (model);
        standbyModel.setId ("STANDBY" + HIVE_ENGINE_TABLE_SEP + model.getId ());
        // 如果参数中有group.id则说明是实时运行，由于相同组消费是抢占式的所以这里需要将配置的group.id参数需要修改
        Property groupId = model.getPropertyMap ().get ("group.id");
        if (groupId != null) {
            groupId.setValue ("STANDBY" + HIVE_ENGINE_TABLE_SEP + groupId.getValue ());
        }
        Metadata metadata = getStandbyMetadata (model.getTargetMetadata ());
        standbyModel.setTargetMetadata (metadata);
        return standbyModel;
    }

    private String getActiveKey(String key) {
        return "ACTIVE" + HIVE_ENGINE_TABLE_SEP + key;
    }

    private String getStandbyKey(String key) {
        return "STANDBY" + HIVE_ENGINE_TABLE_SEP + key;
    }

}
