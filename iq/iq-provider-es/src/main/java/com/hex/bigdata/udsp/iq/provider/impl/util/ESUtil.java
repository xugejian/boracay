package com.hex.bigdata.udsp.iq.provider.impl.util;

import com.hex.bigdata.udsp.iq.provider.impl.factory.ElasticSearchConnectionPoolFactory;
import com.hex.bigdata.udsp.iq.provider.impl.model.ELSearchDatasource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JunjieM on 2019-8-19.
 */
public class ESUtil {
    private static Logger logger = LogManager.getLogger (ESUtil.class);
    private static Map<String, ElasticSearchConnectionPoolFactory> dataSourcePool = new ConcurrentHashMap<> ();

    public static ElasticSearchConnectionPoolFactory getDataSource(ELSearchDatasource datasource) {
        String dsId = datasource.getId ();
        String key = "iq-es-" + dsId;
        synchronized (key.intern ()) {
            ElasticSearchConnectionPoolFactory factory = dataSourcePool.remove (dsId);
            if (factory == null) {
                GenericObjectPool.Config config = new GenericObjectPool.Config ();
                config.lifo = true;
                config.minIdle = 1;
                config.maxActive = 10;
                config.maxWait = 3000;
                config.maxActive = 5;
                config.timeBetweenEvictionRunsMillis = 30000;
                config.testWhileIdle = true;
                config.testOnBorrow = false;
                config.testOnReturn = false;
                factory = new ElasticSearchConnectionPoolFactory (config, datasource.getElasticsearchServers ());
            }
            dataSourcePool.put (dsId, factory);
            return factory;
        }
    }

    public static RestClient getConnection(ELSearchDatasource datasource) throws Exception {
        return getDataSource (datasource).getConnection ();
    }

    public static void release(ELSearchDatasource datasource, RestClient restClient) {
        if (restClient != null) {
            getDataSource (datasource).releaseConnection (restClient);
        }
    }
}
