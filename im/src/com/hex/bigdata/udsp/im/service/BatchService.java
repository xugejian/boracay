package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.im.constant.BatchStatus;
import com.hex.bigdata.udsp.im.dao.BatchMapper;
import com.hex.bigdata.udsp.im.model.BatchInfo;
import com.hex.bigdata.udsp.im.model.RealtimeTotalInfo;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelFilterCol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-21.
 */
@Service
public class BatchService {
    private static Logger logger = LogManager.getLogger(BatchService.class);
    private static final String BATCH_INFO_KEY = "BATCH";
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    @Autowired
    private BatchMapper batchMapper;

    /**
     * 准备构建
     */
    public void readyBuild(String id) {
        BatchInfo batchInfo = new BatchInfo();
        batchInfo.setId(id);
        batchInfo.setStatus(BatchStatus.READY_BUILD);
        batchInfo.setHost(HOST_KEY);
        Date nowDate = new Date();
        batchInfo.setStartTime(nowDate);
        batchInfo.setUpdateTime(nowDate);
        insert(id, batchInfo);
    }

    /**
     * 开始构建
     */
    public void building(String id, int percent) {
        BatchInfo batchInfo = select(id);
        batchInfo.setStatus(BatchStatus.BUILDING);
        batchInfo.setUpdateTime(new Date());
        batchInfo.setPercent(percent);
        update(id, batchInfo);
    }

    /**
     * 构建成功
     *
     * @param id
     */
    public void buildSuccess(String id) {
        BatchInfo batchInfo = select(id);
        batchInfo.setStatus(BatchStatus.BUILD_SUCCESS);
        Date nowDate = new Date();
        batchInfo.setUpdateTime(nowDate);
        batchInfo.setEndTime(nowDate);
        batchInfo.setPercent(100);
        update(id, batchInfo);
    }

    /**
     * 构建失败
     *
     * @param id
     */
    public void buildFail(String id, String message) {
        BatchInfo batchInfo = select(id);
        batchInfo.setStatus(BatchStatus.BUILD_FAIL);
        Date nowDate = new Date();
        batchInfo.setUpdateTime(nowDate);
        batchInfo.setEndTime(nowDate);
        batchInfo.setMessage(message);
        update(id, batchInfo);
    }

    /**
     * 检查并删除失败或成功的作业信息
     */
    public void checkBatchStatus() {
        List<BatchInfo> batchInfos = selectList();
        for (BatchInfo batchInfo : batchInfos) {
            BatchStatus status = batchInfo.getStatus();
            if (BatchStatus.BUILD_SUCCESS == status || BatchStatus.BUILD_FAIL == status) {
                delete(batchInfo.getId());
            }
        }
    }

    public BatchInfo select(String id) {
        return batchMapper.select(BATCH_INFO_KEY + ":" + id);
    }

    public List<BatchInfo> selectList() {
        return batchMapper.selectLike(BATCH_INFO_KEY);
    }

    public void delete(String id) {
        batchMapper.delete(BATCH_INFO_KEY + ":" + id);
    }

    public void insert(String id, BatchInfo batchInfo) {
        batchMapper.insert(BATCH_INFO_KEY + ":" + id, batchInfo);
    }

    public void update(String id, BatchInfo batchInfo) {
        batchMapper.update(BATCH_INFO_KEY + ":" + id, batchInfo);
    }

}
