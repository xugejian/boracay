package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.im.constant.RealtimeStatus;
import com.hex.bigdata.udsp.im.dao.RealtimeNodeMapper;
import com.hex.bigdata.udsp.im.model.RealtimeNodeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-15.
 */
@Service
public class RealtimeNodeService {
    private static Logger logger = LogManager.getLogger(RealtimeNodeService.class);
    private static final String REALTIME_NODE_INFO_KEY = "REALTIME_NODE";
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    @Autowired
    private RealtimeNodeMapper realtimeNodeMapper;

    public void starting(String id) {
        try {
            RealtimeNodeInfo realtimeInfo = new RealtimeNodeInfo();
            realtimeInfo.setId(id);
            realtimeInfo.setHost(HOST_KEY);
            realtimeInfo.setStatus(RealtimeStatus.STARTING);
            realtimeInfo.setUpdateTime(new Date());
            insert(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void running(String id) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUNNING);
            Date nowDate = new Date();
            realtimeInfo.setRunTime(nowDate);
            realtimeInfo.setUpdateTime(nowDate);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startFail(String id, String message) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.START_FAIL);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stoping(String id) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOPING);
            realtimeInfo.setUpdateTime(new Date());
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSuccess(String id) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOP_SUCCESS);
            Date nowDate = new Date();
            realtimeInfo.setUpdateTime(nowDate);
            realtimeInfo.setEndTime(nowDate);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopFail(String id, String message) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOP_FAIL);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runFail(String id, String message) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUN_FAIL);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String id, RealtimeNodeInfo realtimeInfo) {
        realtimeNodeMapper.insert(REALTIME_NODE_INFO_KEY + ":" + id + ":" + HOST_KEY, realtimeInfo);
    }

    public RealtimeNodeInfo select(String id) {
        return realtimeNodeMapper.select(REALTIME_NODE_INFO_KEY + ":" + id + ":" + HOST_KEY);
    }

    public void update(String id, RealtimeNodeInfo realtimeInfo) {
        realtimeNodeMapper.update(REALTIME_NODE_INFO_KEY + ":" + id + ":" + HOST_KEY, realtimeInfo);
    }

    public List<RealtimeNodeInfo> selectList(String id) {
        return realtimeNodeMapper.selectLike(REALTIME_NODE_INFO_KEY + ":" + id);
    }

    public void deleteList(String id) {
        for (RealtimeNodeInfo nodeInfo : selectList(id)) {
            realtimeNodeMapper.delete(REALTIME_NODE_INFO_KEY + ":" + id + ":" + nodeInfo.getHost());
        }
    }

    public void delete(String id) {
        realtimeNodeMapper.delete(REALTIME_NODE_INFO_KEY + ":" + id + ":" + HOST_KEY);
    }

    public void delete(String id, String host) {
        realtimeNodeMapper.delete(REALTIME_NODE_INFO_KEY + ":" + id + ":" + host);
    }
}
