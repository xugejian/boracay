package com.hex.bigdata.udsp.common.lock;

import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁
 */
@Repository
public class RedisDistributedLock {
    private static Logger logger = LogManager.getLogger(RedisDistributedLock.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存储到redis中的标志
     */
    private static final String LOCKED = "LOCKED";

    /**
     * 请求锁超时时间（ms）
     */
    private static final long TIME_OUT = 60000l;

    /**
     * 检查锁是否解锁的休眠时间（ms）
     */
    private static final long SLEEP_TIME = 3;

    /**
     * 锁的有效时间
     */
    public static final int EXPIRE = 1;

    public final static String LOCK_KEY_PREFIX = "lock:";

    /**
     * 上锁
     *
     * @param key
     */
    public void lock(String key) {
        String newkey = LOCK_KEY_PREFIX + key;
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate) WebApplicationContextUtil.getBean("redisLockTemplate");
        }
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        try {
            long count = 0;
            long num = TIME_OUT / SLEEP_TIME;
            while (count < num) {
                try {
                    if (redisConnection.setNX(newkey.getBytes(), LOCKED.getBytes())) {
                        redisTemplate.expire(newkey, EXPIRE, TimeUnit.SECONDS);
                        break;
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage() + " key: " + key);
                }
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (Exception e) {
                    logger.error(e.getMessage() + " key: " + key);
                }
                count++;
            }
            if (count >= num) {
                logger.error("key: " + key + " lock time out");
                unlock(newkey);
            }
        } finally {
            redisConnection.close();
        }
    }

    /**
     * 解锁
     *
     * @param key
     */
    public void unlock(String key) {
        String newkey = LOCK_KEY_PREFIX + key;
        try {
            redisTemplate.delete(newkey);
        } catch (Exception e) {
            logger.error(e.getMessage() + " key: " + key);
        }
    }

}
