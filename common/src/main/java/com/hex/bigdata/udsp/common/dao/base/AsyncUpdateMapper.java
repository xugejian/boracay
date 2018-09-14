package com.hex.bigdata.udsp.common.dao.base;

import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.model.ComOperationLog;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.MD5Util;
import com.hex.goframe.dao.BaseMapper;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步update操作和同步select操作的抽象类，DDL操作都记日志但不入库
 *
 * @param <T>
 */
public abstract class AsyncUpdateMapper<T> extends BaseMapper implements Runnable {
    private static Logger logger = LogManager.getLogger(AsyncUpdateMapper.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    private static final ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
        private AtomicInteger id = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("async-update-mapper" + id.addAndGet(1));
            return thread;
        }
    });

    private String clazz = this.getClass().getName();

    private T t;

    /**
     * 默认为没有缓存
     */
    @Autowired
    @Qualifier("noCache")
    protected Cache<T> cache;

    /**
     * 这里必须是protected，如子类需要使用其他缓存时可以自行重载
     *
     * @return
     */
    protected Cache<T> getCache() {
        return this.cache;
    }

    @Override
    public void run() {
        updateExe(t);
        insertLog("2", "Class=" + clazz + " Object=" + t.getClass().getName());
    }

    //------------------------------update------------------------------------------
    public boolean update(String id, T t) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        AsyncUpdateMapper mapper = null;
        try {
            //多线程调用，如果不重新创建一个this类型的对象，就可能会出现以下情况：
            //前一个进入synchronized代码块的线程A释放锁，但还未调用 run()中的insertExe(Aobject)时，如果后一个线程B做了setT(Bobject)操作
            //此时线程A调用的insertExe(Bobject)，并发搞则就会插入两条相同的记录到数据库，出现异常
            mapper = this.getClass().newInstance();
            // 将this中的sqlSessionTemplate对象赋值给新对象mapper
            mapper.sqlSessionTemplate = this.sqlSessionTemplate;
            mapper.cache = this.cache;
            mapper.t = t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        Thread thread = new Thread(mapper);
//        thread.start();
        executorService.execute(mapper);
        return getCache().updateCache(key, t);
    }

    protected abstract boolean updateExe(T t);

    //------------------------------------select------------------------------------
    public T select(String id) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        Cache<T> cache = getCache();
        T t = cache.selectCache(key);
        if (t == null) {
            t = selectExe(id);
            if (t != null) {
                cache.insertCache(key, t);
            }
        }
        logger.debug("查询 Class=" + clazz + " PkId=" + id);
        return t;
    }

    protected abstract T selectExe(String id);

    // --------------------------------------------------------------------------------------
    private void insertLog(String type, String log) {
        try {
            ComOperationLog comOperationLog = new ComOperationLog();
            comOperationLog.setPkId(Util.uuid());
            comOperationLog.setActionType(type);
//            comOperationLog.setActionUser(WebUtil.getCurrentUserName());
//            comOperationLog.setActionUrl(WebUtil.getRequest().getRequestURL().toString());
            comOperationLog.setActionTime(format.format(new Date()));
            comOperationLog.setActionContent(log);
            logger.info(JSONUtil.parseObj2JSON(comOperationLog));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
