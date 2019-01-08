package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.im.constant.ModelType;
import com.hex.bigdata.udsp.im.converter.model.Model;
import com.hex.bigdata.udsp.im.service.BatchJobService;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.bigdata.udsp.im.service.RealtimeJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 同步交互建模的服务
 */
@Service
public class ImSyncService {
    private static Logger logger = LoggerFactory.getLogger(ImSyncService.class);

    @Autowired
    private BatchJobService batchJobService;
    @Autowired
    private RealtimeJobService realtimeJobService;
    @Autowired
    private ImModelService imModelService;

    /**
     * 同步运行
     *
     * @param appId
     * @param data
     * @return
     */
    public Response start(String appId, Map<String, String> data) {
        long bef = System.currentTimeMillis();
        Response response = new Response();
        try {
            Model model = imModelService.getModel(appId, data);
            if (ModelType.REALTIME == model.getType()) {
                realtimeJobService.start(model);
            } else {
                batchJobService.start(model);
            }
            response.setStatus(Status.SUCCESS.getValue());
            response.setStatusCode(StatusCode.SUCCESS.getValue());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(ErrorCode.ERROR_000007.getName () + ":" + e.getMessage());
        }
        response.setConsumeTime(System.currentTimeMillis() - bef);
        return response;
    }
}
