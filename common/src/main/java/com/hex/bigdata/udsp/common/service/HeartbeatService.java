package com.hex.bigdata.udsp.common.service;

import com.hex.bigdata.udsp.common.dao.HeartbeatMapper;
import com.hex.bigdata.udsp.common.model.HeartbeatInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 心跳的服务
 */
@Service
public class HeartbeatService {

    private static Logger logger = LogManager.getLogger(HeartbeatService.class);

    /**
     * 心跳信息的KEY
     */
    private static final String HEARTBEAT_INFO_KEY = "HEARTBEAT";

    @Autowired
    private HeartbeatMapper heartbeatMapper;

    public List<HeartbeatInfo> selectList() {
        return heartbeatMapper.selectLike(HEARTBEAT_INFO_KEY);
    }

}
