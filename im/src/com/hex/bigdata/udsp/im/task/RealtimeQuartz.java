package com.hex.bigdata.udsp.im.task;

import com.hex.bigdata.udsp.im.service.RealtimeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 实时任务Quartz
 */
@Component
public class RealtimeQuartz {
    private static Logger logger = LogManager.getLogger(RealtimeQuartz.class);

    @Autowired
    private RealtimeService realtimeService;

    /**
     * 检查实时任务状态
     */
    //@Scheduled(cron = "${check.realtime.status.cron.expression:0/2 * * * * ?}")
    public void checkRealtimeStatus() {
        logger.debug("检查实时作业状态【开始】");
        realtimeService.checkRealtimeStatus();
        logger.debug("检查实时作业状态【结束】");
    }

}
