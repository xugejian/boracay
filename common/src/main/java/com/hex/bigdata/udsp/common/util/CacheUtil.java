package com.hex.bigdata.udsp.common.util;

import com.hex.bigdata.udsp.common.constant.CacheMode;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.goframe.util.WebApplicationContextUtil;

/**
 * Created by PC on 2017/6/2.
 */
public class CacheUtil {

    /**
     * 获取Cache实例，可以没有缓存
     *
     * @param cacheMode
     * @return
     */
    public static <T> Cache<T> getCache(String cacheMode) {
        String beanId = "ehCache";
        if (CacheMode.REDIS.getValue().equalsIgnoreCase(cacheMode)) {
            beanId = "redisCache";
        } else if (CacheMode.LOCAL.getValue().equalsIgnoreCase(cacheMode)) {
            beanId = "localCache";
        } else if (CacheMode.NONE.getValue().equalsIgnoreCase(cacheMode)) {
            beanId = "noCache";
        }
        return (Cache<T>) WebApplicationContextUtil.getBean(beanId);
    }

    /**
     * 获取Cache实例，必须要有缓存
     *
     * @param cacheMode
     * @param <T>
     * @return
     */
    public static <T> Cache<T> getCache2(String cacheMode) {
        String beanId = "ehCache";
        if (CacheMode.REDIS.getValue().equalsIgnoreCase(cacheMode)) {
            beanId = "redisCache";
        } else if (CacheMode.LOCAL.getValue().equalsIgnoreCase(cacheMode)) {
            beanId = "localCache";
        }
        return (Cache<T>) WebApplicationContextUtil.getBean(beanId);
    }

}
