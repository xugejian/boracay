package com.hex.bigdata.udsp.iq.provider.impl.util;

import com.hex.bigdata.udsp.iq.provider.impl.factory.RedisConnectionPoolFactory;
import com.hex.bigdata.udsp.iq.provider.impl.model.RedisDatasource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JunjieM on 2019-8-19.
 */
public class RedisUtil {
    private static Logger logger = LogManager.getLogger (RedisUtil.class);
    private static Map<String, RedisConnectionPoolFactory> dataSourcePool = new ConcurrentHashMap<> ();

    public static RedisConnectionPoolFactory getDataSource(RedisDatasource datasource) {
        String dsId = datasource.getId ();
        String key = "iq-redis-" + dsId;
        synchronized(key.intern ()) {
            RedisConnectionPoolFactory factory = dataSourcePool.remove (dsId);
            if (factory == null) {
                factory = new RedisConnectionPoolFactory (datasource);
            }
            dataSourcePool.put (dsId, factory);
            return factory;
        }
    }

    public static Jedis getConnection(RedisDatasource datasource) {
        return getDataSource (datasource).getConnection ();
    }

    public static boolean release(RedisDatasource datasource, Jedis jedis) {
        if (jedis != null) {
            return getDataSource (datasource).release (jedis);
        }
        return true;
    }
}
