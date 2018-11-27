package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.rts.executor.model.ConsumerResponse;
import com.hex.bigdata.udsp.rts.executor.model.ProducerResponse;
import com.hex.bigdata.udsp.rts.model.RtsMetadataCol;
import com.hex.bigdata.udsp.rts.model.RtsProducer;
import com.hex.bigdata.udsp.rts.service.RtsExecutorService;
import com.hex.bigdata.udsp.rts.service.RtsMetadataColService;
import com.hex.bigdata.udsp.rts.service.RtsProducerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 同步实时流的服务（包括生产者和消费者）
 */
@Service
public class RtsSyncService {
    @Autowired
    private RtsMetadataColService rtsMetadataColService;
    @Autowired
    private RtsProducerService rtsProducerService;
    @Autowired
    private RtsExecutorService rtsExecutorService;

    /**
     * 同步运行生产者
     *
     * @param appId
     * @param datas
     * @return
     */
    public Response startProducer(String appId, List<Map<String, String>> datas) {
        Response response = new Response();
        try {
            checkParam(appId, datas);
        } catch (Exception e) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000009.getValue());
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.toString());
            return response;
        }
        try {
            RtsProducer rtsProducer = rtsProducerService.select(appId);
            String mdId = rtsProducer.getMdId();
            List<RtsMetadataCol> rtsMetadataCols = rtsMetadataColService.selectByMdId(mdId);
            List<Map<String, String>> messageDatas = new ArrayList<>();
            for (Map<String, String> data : datas) {
                Map<String, String> message = new HashMap<>();
                for (RtsMetadataCol col : rtsMetadataCols) {
                    String name = col.getName();
                    message.put(name, data.get(name));
                }
                messageDatas.add(message);
            }
            ProducerResponse producerResponse = rtsExecutorService.producer(appId, messageDatas);
            response.setConsumeTime(producerResponse.getConsumeTime());
            response.setMessage(producerResponse.getMessage());
            response.setStatus(producerResponse.getStatus().getValue());
            response.setStatusCode(producerResponse.getStatusCode().getValue());
        } catch (Exception e) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(ErrorCode.ERROR_000007.getName() + ":" + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 检查输入的参数
     *
     * @param appId
     * @param datas
     * @return
     */
    private void checkParam(String appId, List<Map<String, String>> datas) throws Exception {
        boolean isError = false;
        String message = "";
        int count = 0;
        for (RtsMetadataCol rtsMetadataCol : rtsMetadataColService.selectByProducerPkid(appId)) {
            String name = rtsMetadataCol.getName();
            String value = datas.get(0).get(name);
            if (StringUtils.isBlank(value)) {
                message += (count == 0 ? "" : ", ") + name;
                isError = true;
                count++;
            }
        }
        if (isError) {
            throw new Exception(message + "参数不能为空!");
        }
    }

    /**
     * 同步运行消费者
     *
     * @param appId
     * @param timeout
     * @return
     */
    public Response startConsumer(String appId, long timeout) {
        Response response = new Response();
        try {
            ConsumerResponse consumerResponse = rtsExecutorService.consumer(appId, timeout);
            response.setConsumeTime(consumerResponse.getConsumeTime());
            response.setMessage(consumerResponse.getMessage());
            response.setStatus(consumerResponse.getStatus().getValue());
            response.setStatusCode(consumerResponse.getStatusCode().getValue());
            response.setReturnColumns(consumerResponse.getColumns());
            response.setRecords(consumerResponse.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
