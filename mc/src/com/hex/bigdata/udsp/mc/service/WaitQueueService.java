package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.lock.RedisDistributedLock;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.dto.QueueIsFullResult;
import com.hex.bigdata.udsp.mc.dao.WaitQueueMapper;
import com.hex.bigdata.udsp.mc.model.WaitQueue;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.util.McCommonUtil;
import com.hex.bigdata.udsp.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 等待队列信息的服务
 */
@Service
public class WaitQueueService {

    private static Logger logger = LoggerFactory.getLogger(WaitQueueService.class);

    /**
     * 等待队列信息的KEY
     */
    private static final String MC_WAITQUEUE_KEY = "WAITQUEUE";

    @Autowired
    private WaitQueueMapper mcWaitQueueMapper;

    @Autowired
    private CurrentService mcCurrentService;

    @Autowired
    private RunQueueService runQueueService;

    /**
     * Redis 分布式锁
     */
    @Autowired
    private RedisDistributedLock redisLock;

    @Autowired
    private InitParamService initParamService;

    /**
     * 检查等待队列是否满了，未满则添加到等待队列。满了则不做操作，
     * 无论是否满都记录是否满状态和任务ID
     *
     * @param mcCurrent
     * @param maxWaitNum
     * @return
     */
    public QueueIsFullResult checkWaitQueueIsFull(Current mcCurrent, int maxWaitNum) {
        String key = this.getKey(mcCurrent);
        boolean isFull = false;
        QueueIsFullResult isFullResult = new QueueIsFullResult();
        synchronized (key.intern()) {
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                WaitQueue mcWaitQueue = this.select(key);
                if (mcWaitQueue == null) mcWaitQueue = initByKey(mcCurrent);
                // 若是等待队列未满，则请求进入
                if (mcWaitQueue.getCurrentLenth() < maxWaitNum) {
                    logger.debug("等待队列最大长度：" + maxWaitNum + "，等待队列长度："
                            + mcWaitQueue.getCurrentLenth() + "，" + Thread.currentThread().getName() + "进入等待队列！");
                    // 本次请求加入队列
                    String pkId = mcCurrent.getPkId();
                    isFullResult.setWaitQueueTaskId(pkId);
                    mcWaitQueue.offerElement(pkId); // 加入队列
                    // 更新等待队列统计信息
                    mcWaitQueueMapper.insert(key, mcWaitQueue);
                    // 加入统计队列
                    mcCurrentService.insertWait(mcCurrent);
                } else {
                    isFull = true;
                }
                isFullResult.setWaitQueueIsFull(isFull);
                return isFullResult;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key);
            }
        }
    }

    /**
     * 检查waitQueueTaskId是否是等待队列中第一个（排在队列第一个的才允许先出去），
     * 如果是则从等待队列中移除并返回true,如果不是的返回false
     *
     * @param mcCurrent
     * @param waitQueueTaskId
     * @return
     */
    public boolean checkWaitQueueIsFirst(Current mcCurrent, String waitQueueTaskId) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) {
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key);
            try {
                WaitQueue mcWaitQueue = this.select(key);
                // 判断key是不是队列第一个，如果是第一个则移除并返回true，如果不是第一个则返回false
                if (mcWaitQueue.isFirstElement(waitQueueTaskId)) {
                    // 等待队列信息回写到缓存
                    logger.debug("将任务从等待队列中移除：" + Thread.currentThread().getName());
                    mcWaitQueueMapper.insert(key, mcWaitQueue); // 更新等待队列统计信息
                    mcCurrentService.deleteWait(waitQueueTaskId); // 从统计队列中删除
                    runQueueService.addCurrent(mcCurrent); // 增加并发
                    return true;
                }
                return false;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key);
            }
        }
    }

    /**
     * 初始化等待队列对象
     * key为userId:appId:appType
     * 即key为userId 用户id，appId应用id，appType:应用类型，Type：同步异步
     *
     * @param current
     * @return
     */
    private WaitQueue initByKey(Current current) {
        return new WaitQueue(this.getKey(current).toString());
    }

    private String getKey(Current mcCurrent) {
        return MC_WAITQUEUE_KEY + ":" + mcCurrent.getUserName() + ":" + mcCurrent.getAppId()
                + ":" + mcCurrent.getAppType().toUpperCase() + ":" + mcCurrent.getSyncType().toUpperCase();
    }

    private WaitQueue select(String key) {
        return mcWaitQueueMapper.select(key);
    }

}
