package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrWrapper;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.SolrModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
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
        return false;
    }

    @Override
    public boolean dropEngineSchema(Model model) throws Exception {
        return false;
    }
}
