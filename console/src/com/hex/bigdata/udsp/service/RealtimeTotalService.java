package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.dao.RealtimeTotalMapper;
import com.hex.bigdata.udsp.im.constant.RealtimeStatus;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MqModel;
import com.hex.bigdata.udsp.model.RealtimeTotalInfo;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-14.
 */
@Service
public class RealtimeTotalService {
    private static Logger logger = LogManager.getLogger(RealtimeTotalService.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String REALTIME_TOTAL_KEY = "REALTIME_TOTAL";
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    @Autowired
    private RealtimeTotalMapper realtimeTotalMapper;

    public void readyStart(MqModel model) {
        try {
            String id = model.getId();
            RealtimeTotalInfo realtimeInfo = new RealtimeTotalInfo();
            realtimeInfo.setStartHost(HOST_KEY);
            String nowTime = format.format(new Date());
            realtimeInfo.setStartTime(nowTime);
            realtimeInfo.setStatus(RealtimeStatus.READY_START);
            realtimeInfo.setUpdateTime(nowTime);
            realtimeInfo.setModel(model);
            insert(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void starting(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STARTING);
            realtimeInfo.setUpdateTime(format.format(new Date()));
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void running(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUNNING);
            String nowTime = format.format(new Date());
            realtimeInfo.setRunTime(nowTime);
            realtimeInfo.setUpdateTime(nowTime);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startFail(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.START_FAIL);
            realtimeInfo.setUpdateTime(format.format(new Date()));
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readyStop(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStopHost(HOST_KEY);
            String nowTime = format.format(new Date());
            realtimeInfo.setStopTime(nowTime);
            realtimeInfo.setStatus(RealtimeStatus.READY_STOP);
            realtimeInfo.setUpdateTime(nowTime);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stoping(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOPING);
            realtimeInfo.setUpdateTime(format.format(new Date()));
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSuccess(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOP_SUCCESS);
            String nowTime = format.format(new Date());
            realtimeInfo.setUpdateTime(nowTime);
            realtimeInfo.setEndTime(nowTime);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopFail(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOP_FAIL);
            realtimeInfo.setUpdateTime(format.format(new Date()));
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runFail(String id) {
        try {
            RealtimeTotalInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUN_FAIL);
            realtimeInfo.setUpdateTime(format.format(new Date()));
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String id, RealtimeTotalInfo realtimeInfo) {
        realtimeTotalMapper.insert(REALTIME_TOTAL_KEY + ":" + id, realtimeInfo);
    }

    public void update(String id, RealtimeTotalInfo realtimeInfo) {
        realtimeTotalMapper.update(REALTIME_TOTAL_KEY + ":" + id, realtimeInfo);
    }

    public RealtimeTotalInfo select(String id) {
        return realtimeTotalMapper.select(REALTIME_TOTAL_KEY + ":" + id);
    }

    public List<RealtimeTotalInfo> selectList() {
        return realtimeTotalMapper.selectLike(REALTIME_TOTAL_KEY);
    }

    public void delete(String id) {
        realtimeTotalMapper.delete(REALTIME_TOTAL_KEY + ":" + id);
    }
}
