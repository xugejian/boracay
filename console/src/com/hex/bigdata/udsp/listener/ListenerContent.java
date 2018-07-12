package com.hex.bigdata.udsp.listener;


import com.hex.bigdata.udsp.service.FtpUserManagerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

/**
 * Spring初始化完成时指定执行该类
 */
@Repository
public class ListenerContent implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = LogManager.getLogger(ListenerContent.class);

    @Autowired
    private FtpUserManagerService ftpUserManagerService;
//    @Autowired
//    private RunQueueService runQueueService;
//    @Autowired
//    private WaitQueueService waitQueueService;

    /**
     * Spring初始化完成时指定执行该方法
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("启动服务时初始化FTP服务器上UDSP用户和用户组");
        ftpUserManagerService.init(); // 启动服务时初始化FTP服务器上UDSP用户和用户组
        /*
        这里无法初始化时清空队列信息，因为这里WebApplicationContext还没有初始化
         */
//        logger.info("启动服务时清空本机的运行队列信息");
//        runQueueService.emptyLocalCache(); // 启动服务时清空本机的运行队列信息
//        logger.info("启动服务时清空本机的等待队列信息");
//        waitQueueService.emptyLocalCache(); // 启动服务时清空本机的等待队列信息
    }
}
