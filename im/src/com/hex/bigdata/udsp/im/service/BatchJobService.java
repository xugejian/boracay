package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.im.constant.BatchStatus;
import com.hex.bigdata.udsp.im.dao.BatchMapper;
import com.hex.bigdata.udsp.im.model.BatchInfo;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveJdbcUtil;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-21.
 */
@Service
public class BatchJobService {
    private static Logger logger = LogManager.getLogger(BatchJobService.class);
    private static final String BATCH_INFO_KEY = "BATCH";
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    @Autowired
    private BatchMapper batchMapper;
    @Autowired
    private ImProviderService imProviderService;

    /**
     * 启动
     */
    public void start(Model model) throws Exception {
        String id = UdspCommonUtil.getConsumeId(model.getId());
        readyBuild(id);
        try {
            imProviderService.buildBatch(id, model);
            buildSuccess(id);
        } catch (Exception e) {
            buildFail(id, e.getMessage());
            throw new Exception(e);
        }
    }

    /**
     * 停止
     *
     * @param id
     * @throws Exception
     */
    public void stop(String id) throws Exception {
        Statement stmt = HiveJdbcUtil.getStatement(id);
        if (stmt == null || stmt.isClosed()) {
            throw new Exception("作业不存在或已经完成");
        }
        stmt.close();
    }

    /**
     * 准备构建
     */
    public void readyBuild(String id) {
        try {
            BatchInfo batchInfo = new BatchInfo();
            batchInfo.setId(id);
            batchInfo.setStatus(BatchStatus.READY_BUILD);
            batchInfo.setHost(HOST_KEY);
            Date nowDate = new Date();
            batchInfo.setStartTime(nowDate);
            batchInfo.setUpdateTime(nowDate);
            insert(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始构建
     */
    public void building(String id, int percent) {
        try {
            BatchInfo batchInfo = select(id);
            batchInfo.setStatus(BatchStatus.BUILDING);
            batchInfo.setUpdateTime(new Date());
            batchInfo.setPercent(percent);
            update(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建成功
     *
     * @param id
     */
    public void buildSuccess(String id) {
        try {
            BatchInfo batchInfo = select(id);
            batchInfo.setStatus(BatchStatus.BUILD_SUCCESS);
            Date nowDate = new Date();
            batchInfo.setUpdateTime(nowDate);
            batchInfo.setEndTime(nowDate);
            batchInfo.setPercent(100);
            update(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建失败
     *
     * @param id
     */
    public void buildFail(String id, String message) {
        try {
            BatchInfo batchInfo = select(id);
            batchInfo.setStatus(BatchStatus.BUILD_FAIL);
            Date nowDate = new Date();
            batchInfo.setUpdateTime(nowDate);
            batchInfo.setEndTime(nowDate);
            batchInfo.setMessage(message);
            update(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空过时的批量作业信息
     */
    public void cleanOutmodedBatch() {
        List<BatchInfo> batchInfos = selectList();
        for (BatchInfo batchInfo : batchInfos) {
            String id = batchInfo.getId();
            BatchStatus status = batchInfo.getStatus();
            String host = batchInfo.getHost();
            if ((BatchStatus.BUILD_SUCCESS == status || BatchStatus.BUILD_FAIL == status)
                    && HOST_KEY.equals(host)) {
                logger.debug("删除失败或成功且过时的批量作业信息,ID:" + id);
                delete(id);
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
