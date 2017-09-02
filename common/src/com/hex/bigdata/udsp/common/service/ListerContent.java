package com.hex.bigdata.udsp.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

/**
 * Created by PC on 2017/6/21.
 */
@Repository
public class ListerContent implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private FtpUserManagerService ftpUserManagerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ftpUserManagerService.init();
    }
}
