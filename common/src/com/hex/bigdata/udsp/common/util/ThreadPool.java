package com.hex.bigdata.udsp.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

    private static int threadPoolSize = 100;
    public static ExecutorService service;

    static {
        InputStream in = null;
        try {
            in = FTPClientConfig.class.getClassLoader().getResourceAsStream(
                    "goframe/udsp/udsp.config.properties");
            Properties props = new Properties();
            props.load(in);
            String threadPoolSizeStr = props.getProperty("thread.pool.size", "100");
            if (StringUtils.isNotBlank(threadPoolSizeStr))
                threadPoolSize = Integer.valueOf(threadPoolSizeStr);
            service = Executors.newFixedThreadPool(threadPoolSize, new ThreadFactory() {
                private AtomicInteger id = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("async-service-" + id.addAndGet(1));
                    return thread;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void execute(Runnable runnable) {
        service.execute(runnable);
    }
}
