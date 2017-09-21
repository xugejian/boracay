package com.hex.bigdata.udsp.im.task;

import com.hex.bigdata.udsp.common.constant.ServiceMode;
import com.hex.bigdata.udsp.im.service.BatchService;
import com.hex.bigdata.udsp.im.service.RealtimeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by JunjieM on 2017-9-21.
 */
@Component
public class RealtimeTask {
    private static Logger logger = LogManager.getLogger(BatchTask.class);

    @Autowired
    private RealtimeService realtimeService;

    /**
     * 服务模式（single、cluster）
     */
    @Value("${service.mode:single}")
    private String serviceMode;

    @Scheduled(cron = "${check.realtime.live.cron.expression:*/60 * * * * ?}")
    public void checkRealtimeLive() {
        if (ServiceMode.CLUSTER.getValue().equalsIgnoreCase(serviceMode)) {
            logger.debug("检测实时作业存活【开始】");
            realtimeService.checkRealtimeLive();
            logger.debug("检测实时作业存活【结束】");
        } else {
            logger.debug("单机模式不需要检测实时作业存活");
        }
    }
}
