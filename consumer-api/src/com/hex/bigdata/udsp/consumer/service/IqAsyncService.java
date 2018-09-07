package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.service.RunQueueService;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异步交互查询的服务
 */
public class IqAsyncService implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(IqAsyncService.class);

    private IqSyncService iqSyncService;
    private RunQueueService runQueueService;

    private ConsumeRequest consumeRequest;
    private String fileName;
    private long bef;


    public IqAsyncService(ConsumeRequest consumeRequest, String fileName, long bef) {
        this.iqSyncService = (IqSyncService) WebApplicationContextUtil.getBean("iqSyncService");
        this.runQueueService = (RunQueueService) WebApplicationContextUtil.getBean("runQueueService");

        this.consumeRequest = consumeRequest;
        this.fileName = fileName;
        this.bef = bef;
    }

    @Override
    public void run() {
        logger.debug("IQ START 线程调用开始");
        try {
            // 没有进入等待队列或从等待队列中出来，则进入执行队列中执行任务
            iqSyncService.asyncStartForTimeout(consumeRequest, fileName, bef);
        } finally {
            runQueueService.reduceCurrent(consumeRequest.getMcCurrent());
        }
        logger.debug(" IQ START 线程调用结束");
    }
}
