package com.hex.bigdata.udsp.im.task;

import com.hex.bigdata.udsp.im.service.BatchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by JunjieM on 2017-9-21.
 */
@Component
public class BatchTask {
    private static Logger logger = LogManager.getLogger(BatchTask.class);

    @Autowired
    private BatchService batchService;

    @Scheduled(cron = "${check.batch.status.cron.expression:1 1 * * * ?}")
    public void checkBatchStatus() {
        logger.debug("检查批量作业状态【开始】");
        batchService.checkBatchStatus();
        logger.debug("检查批量作业状态【结束】");
    }
}
