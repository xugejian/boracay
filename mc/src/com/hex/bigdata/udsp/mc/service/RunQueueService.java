package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.lock.RedisDistributedLock;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.mc.dao.RunQueueMapper;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.model.RunQueue;
import com.hex.bigdata.udsp.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运行队列信息的服务
 */
@Service
public class RunQueueService {
    private static Logger logger = LoggerFactory.getLogger(RunQueueService.class);

    /**
     * 运行队列信息的KEY
     */
    private static final String MC_RUNQUEUE_KEY = "RUNQUEUE";

    @Autowired
    private RunQueueMapper runQueueMapper;

    @Autowired
    private CurrentService mcCurrentService;

    @Autowired
    private InitParamService initParamService;

    /**
     * Redis 分布式锁
     */
    @Autowired
    private RedisDistributedLock redisLock;

    /**
     * 增加并发
     *
     * @param mcCurrent
     * @return
     */
    public boolean addCurrent(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) {
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key);
            try {
                logger.debug("增加" + key + "并发！");
                RunQueue runQueue = this.select(key);
                if (runQueue == null) runQueue = this.initByKey(key);
                if ((ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())
                        && runQueue.getCurrentSyncNum() < mcCurrent.getMaxCurrentNum())
                        || (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())
                        && runQueue.getCurrentAsyncNum() < mcCurrent.getMaxCurrentNum())) {
                    if (ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
                        runQueue.setCurrentSyncNum(runQueue.getCurrentSyncNum() + 1);
                        logger.debug("增加" + key + "同步并发后并发数==>" + runQueue.getCurrentAsyncNum());
                    } else {
                        runQueue.setCurrentAsyncNum(runQueue.getCurrentAsyncNum() + 1);
                        logger.debug("增加" + key + "异步并发后并发数==>" + runQueue.getCurrentAsyncNum());
                    }
                    this.insert(key, runQueue);
                    mcCurrentService.insert(mcCurrent);
                    return true;
                }
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key);
            }
            return false;
        }
    }

    /**
     * 减少并发
     *
     * @param mcCurrent
     * @return
     */
    public boolean reduceCurrent(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) {
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key);
            try {
                logger.debug("减少" + key + "并发！");
                this.mcCurrentService.delete(mcCurrent.getPkId());
                RunQueue runQueue = this.select(key);
                if (runQueue != null) {
                    if (ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
                        int currentSyncNum = runQueue.getCurrentSyncNum();
                        currentSyncNum = (currentSyncNum > 1 ? currentSyncNum - 1 : 0);
                        runQueue.setCurrentSyncNum(currentSyncNum);
                        logger.debug("减少" + key + "同步并发后并发数==>" + runQueue.getCurrentSyncNum());
                    } else {
                        int currentAsyncNum = runQueue.getCurrentAsyncNum();
                        currentAsyncNum = (currentAsyncNum > 1 ? currentAsyncNum - 1 : 0);
                        runQueue.setCurrentAsyncNum(currentAsyncNum);
                        logger.debug("减少" + key + "异步并发后并发数==>" + runQueue.getCurrentAsyncNum());
                    }
                    this.insert(key, runQueue);
                }
                return true;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key);
            }
        }
    }

    public boolean update(String key, RunQueue runQueue) {
        return this.runQueueMapper.update(key, runQueue);
    }

    public boolean insert(String key, RunQueue runQueue) {
        return this.runQueueMapper.insert(key, runQueue);
    }

    public RunQueue select(String key) {
        return runQueueMapper.select(key);
    }

    /**
     * key为userId:appId:appType
     * 即key为userId 用户id，appId应用id，appType
     *
     * @param key
     * @return
     */
    private RunQueue initByKey(String key) {
        logger.debug("初始化并发数控制实体！");
        String[] keys = key.split("\\:");
        RunQueue runQueue = new RunQueue();
        if (keys.length >= 5) {
            runQueue.setUserId(keys[1]);
            runQueue.setAppId(keys[2]);
            runQueue.setAppType(keys[3]);
            runQueue.setSyncType(keys[4]);
        }
        return runQueue;
    }

    /**
     * 检查执行队列是否满
     *
     * @param mcCurrent 不为空
     * @return false:未满，true：满
     */
    public boolean runQueueFull(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) {
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key);
            try {
                RunQueue runQueue = this.select(key);
                if (runQueue == null) return false;
                if ((CommonConstant.REQUEST_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())
                        && runQueue.getCurrentSyncNum() < mcCurrent.getMaxCurrentNum())
                        || (CommonConstant.REQUEST_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())
                        && runQueue.getCurrentAsyncNum() < mcCurrent.getMaxCurrentNum())) {
                    return false;
                }
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key);
            }
            return true;
        }
    }

    private String getKey(Current mcCurrent) {
        return MC_RUNQUEUE_KEY + ":" + mcCurrent.getUserName() + ":" + mcCurrent.getAppId()
                + ":" + mcCurrent.getAppType().toUpperCase() + ":" + mcCurrent.getSyncType().toUpperCase();
    }

}
