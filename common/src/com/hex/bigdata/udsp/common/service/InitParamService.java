package com.hex.bigdata.udsp.common.service;

import com.hex.bigdata.udsp.common.constant.CacheMode;
import com.hex.bigdata.udsp.common.constant.ServiceMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 初始化参数的服务
 */
@Service
public class InitParamService {
    /**
     * 服务模式（single、cluster）
     */
    @Value("${service.mode:single}")
    private String serviceMode;

    /**
     * 缓存模式（none、local、ehcache、redis）
     */
    @Value("${cache.mode:ehcache}")
    private String cacheMode;

    private static Boolean isUseClusterRedisLock; // 是否使用分布式Redis锁
    private static Boolean isClusterMode; // 是否是集群模式

    /**
     * 是否使用分布式Redis锁
     *
     * @return
     */
    public boolean isUseClusterRedisLock() {
        if (isUseClusterRedisLock == null) {
            isUseClusterRedisLock = (ServiceMode.CLUSTER.getValue().equalsIgnoreCase(serviceMode)
                    && CacheMode.REDIS.getValue().equalsIgnoreCase(cacheMode)) ? true : false;
        }
        return isUseClusterRedisLock;
    }

    /**
     * 是否是集群模式
     *
     * @return
     */
    public boolean isClusterMode() {
        if (isClusterMode == null) {
            isClusterMode = (ServiceMode.CLUSTER.getValue().equalsIgnoreCase(serviceMode)) ? true : false;
        }
        return isClusterMode;
    }

}
