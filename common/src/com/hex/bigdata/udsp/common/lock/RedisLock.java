package com.hex.bigdata.udsp.common.lock;

import com.hex.goframe.util.WebApplicationContextUtil;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/24
 * TIME:14:33
 */
@Deprecated
public class RedisLock implements Lock {


    protected RedisTemplate redisLockTemplate;

    /**
     * 存储到redis中的标志
     */
    private static final String LOCKED = "LOCKED";

    /**
     * 请求锁超时时间(ms)
     */
    private static final long TIME_OUT = 3000;

    /**
     * 锁的有效时间
     */
    public static final int EXPIRE = 1;

    /**
     * 锁标志对应的key
     */
    private String key;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public RedisLock(String key, RedisTemplate redisLockTemplate) {
        this.key = key;
        this.redisLockTemplate = redisLockTemplate;
    }

    public RedisLock(RedisTemplate redisLockTemplate) {
        this.redisLockTemplate = redisLockTemplate;
    }

    public RedisLock() {
        this.redisLockTemplate = (RedisTemplate) WebApplicationContextUtil.getBean("redisTemplate");
    }

    @Override
    public void lock() {

            long nowTime = System.nanoTime();
            long timeout = TIME_OUT * 1000000;
            final Random random = new Random();
            if (redisLockTemplate == null) {
                redisLockTemplate = (RedisTemplate) WebApplicationContextUtil.getBean("redisLockTemplate");
            }
            RedisConnection redisConnection=redisLockTemplate.getConnectionFactory().getConnection();
            try {
                while ((System.nanoTime() - nowTime) < timeout) {
                    synchronized (RedisLock.class) {
                        String nxKey = key;
                        if (redisConnection.setNX(nxKey.getBytes(), LOCKED.getBytes())) {
                            redisLockTemplate.expire(nxKey, EXPIRE, TimeUnit.SECONDS);
                            break;
                        }
                    }
                    Thread.sleep(3, random.nextInt(500));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                redisConnection.close();
            }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        synchronized (RedisLock.class) {
            try {
                redisLockTemplate.delete(key);
            } catch (Exception e) {
                //
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
