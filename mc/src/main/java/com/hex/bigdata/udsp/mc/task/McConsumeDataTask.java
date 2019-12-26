package com.hex.bigdata.udsp.mc.task;

import com.hex.bigdata.udsp.mc.service.McConsumeDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 消费数据的调度任务
 */
@Component
public class McConsumeDataTask {
    private static Logger logger = LogManager.getLogger (McConsumeDataTask.class);

    @Autowired
    private McConsumeDataService mcConsumeDataService;

    @Scheduled(cron = "${clean.consume.data.cron.expression:0 0 0 * * ?}")
    public void cleanOutmodedConsumeData() {
        logger.debug ("清空过期的消费日志【开始】");
        mcConsumeDataService.cleanOutmodedConsumeData ();
        logger.debug ("清空过期的消费日志【结束】");
    }

}
