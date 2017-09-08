package com.hex.bigdata.udsp.im.provider.wrapper;

import com.hex.bigdata.udsp.im.provider.factory.SolrConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class SolrWrapper {
    private static Logger logger = LogManager.getLogger(SolrWrapper.class);
    private static Map<String, SolrConnectionPoolFactory> dataSourcePool;

    protected synchronized SolrConnectionPoolFactory getDataSource(SolrDatasource datasource, String collectionName) {
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

    protected SolrServer getConnection(SolrDatasource datasource, String collectionName) {
        try {
            return getDataSource(datasource, collectionName).getConnection();
        } catch (Exception e) {
            return null;
        }
    }

    protected void release(SolrDatasource datasource, String collectionName, SolrServer solrServer) {
        getDataSource(datasource, collectionName).releaseConnection(solrServer);
    }
}
