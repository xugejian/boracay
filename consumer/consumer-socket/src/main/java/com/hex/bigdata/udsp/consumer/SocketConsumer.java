package com.hex.bigdata.udsp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Netty Socket Consumer
 */
@Component
public class SocketConsumer {
    private Logger logger = LoggerFactory.getLogger(SocketConsumer.class);

    @Value("${socket.port:9089}")
    private int socketPort;

    @Value ("${boss.group.threads:1}")
    private int bossGroupThreads;

    @Value ("${work.group.threads:0}")
    private int workGroupThreads;

    @Value ("${busi.group.threads:-1}")
    private int busiGroupThreads;

    @PostConstruct
    public void start() throws Exception {
        logger.debug("启动bocd esb socket线程开始");
        Thread thread = new Thread(new SocketServer(socketPort, bossGroupThreads, workGroupThreads, busiGroupThreads));
        thread.start();
        logger.debug("启动bocd esb socket线程成功");
    }
}
