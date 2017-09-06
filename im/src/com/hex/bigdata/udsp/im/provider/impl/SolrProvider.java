package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.factory.SolrConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.SolrModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrProvider")
public class SolrProvider implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(SolrProvider.class);
    private static Map<String, SolrConnectionPoolFactory> dataSourcePool;

    private synchronized SolrConnectionPoolFactory getDataSource(SolrDatasource datasource, String collectionName) {
        String dsId = datasource.getId() + ":" + collectionName;
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, SolrConnectionPoolFactory>();
        }
        SolrConnectionPoolFactory factory = dataSourcePool.get(dsId);
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
            factory = new SolrConnectionPoolFactory(config, datasource.getSolrServers(), collectionName);
            dataSourcePool.put(dsId, factory);
        }
        return factory;
    }

    private SolrServer getConnection(SolrDatasource datasource, String collectionName) {
        try {
            return getDataSource(datasource, collectionName).getConnection();
        } catch (Exception e) {
            return null;
        }
    }

    private void release(SolrDatasource datasource, String collectionName, SolrServer solrServer) {
        getDataSource(datasource, collectionName).releaseConnection(solrServer);
    }

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
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        SolrModel solrModel = new SolrModel(datasource.getPropertyMap());
        String collectionName = solrModel.getCollectionName();
        SolrServer solrServer = getConnection(solrDatasource, collectionName);
        return getColumns(solrServer);
    }

    @Override
    public void create() {

    }

    @Override
    public void drop() {

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
}
