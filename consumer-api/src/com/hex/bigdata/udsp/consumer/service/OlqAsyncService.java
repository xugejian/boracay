package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.service.RunQueueService;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异步联机查询的服务
 */
public class OlqAsyncService implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(IqAsyncService.class);

    private OlqSyncService olqSyncService;
    private RunQueueService runQueueService;

    private ConsumeRequest consumeRequest;
    private String fileName;
    private long bef;

    public OlqAsyncService(ConsumeRequest consumeRequest, String fileName, long bef) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.runQueueService = (RunQueueService) WebApplicationContextUtil.getBean("runQueueService");
        this.consumeRequest = consumeRequest;
        this.fileName = fileName;
        this.bef = bef;
    }

    @Override
    public void run() {
        try {
            // 没有进入等待队列或从等待队列中出来，则进入执行队列中执行任务
            olqSyncService.asyncStartForTimeout(consumeRequest, fileName, bef);
        } finally {
            runQueueService.reduceCurrent(consumeRequest.getMcCurrent());
        }
    }
}
