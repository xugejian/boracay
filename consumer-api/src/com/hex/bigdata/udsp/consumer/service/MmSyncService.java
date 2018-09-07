package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.constant.*;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.mm.dto.MmResponse;
import com.hex.bigdata.udsp.mm.dto.MmResponseData;
import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import com.hex.bigdata.udsp.mm.service.MmApplicationService;
import com.hex.bigdata.udsp.mm.service.MmProviderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 模型请求的服务
 */
@Service
public class MmSyncService {
    /**
     * 模型调用服务
     */
    @Autowired
    private MmProviderService mmProviderService;
    @Autowired
    private MmApplicationService mmApplicationService;

    /**
     * @param consumeId 消费Id
     * @param appId     应用Id
     * @param request   请求实体
     * @return
     */
    public Response start(String consumeId, String appId, Request request) {
        Response response = new Response();
        try {
            checkParam(appId, request.getData());
        } catch (Exception e) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.toString());
            return response;
        }
        MmResponse mmResponse = null;
        try {
            //内部请求，则设置serviceName
            if (RequestType.INNER.getValue().equals(request.getRequestType())) {
                request.setServiceName(request.getAppName());
            }
            //模型调用
            mmResponse = mmProviderService.start(appId, consumeId, request);
            if (StringUtils.isBlank(mmResponse.getErrorCode())) {
                MmResponseData mmResponseData = mmResponse.getData();
                if (mmResponseData != null) {
                    response.setRecords(mmResponseData.getRecords());
                    response.setResponseContent(mmResponseData.getFile());
                }
            }
            response.setMessage(mmResponse.getMessage());
            response.setErrorCode(mmResponse.getErrorCode());
            response.setStatus(mmResponse.getSystemStatus().getValue());
            response.setStatusCode(mmResponse.getStatusCode().getValue());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ErrorCode.ERROR_000007.getName() + "：" + e.toString());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
        }
        return response;
    }

    /**
     * 检查输入的参数
     *
     * @param appId
     * @param paraMap
     * @return
     */
    private void checkParam(String appId, Map<String, String> paraMap) throws Exception {
        boolean isError = false;
        String message = "";
        int count = 0;
        for (MmAppExecuteParam mmAppExecuteParam : mmApplicationService.selectFullAppInfo(appId).getExecuteParams()) {
            if (EnumTrans.transTrue(mmAppExecuteParam.getIsNeed())) {
                String name = mmAppExecuteParam.getName();
                String value = paraMap.get(name);
                if (StringUtils.isBlank(value)) {
                    message += (count == 0 ? "" : ", ") + name;
                    isError = true;
                    count++;
                }
            }
        }
        if (isError) {
            throw new Exception(message + "参数不能为空!");
        }
    }

    /**
     * 模型调用-异步status
     *
     * @param request
     * @return
     */
    public Response status(Request request, String appId) {
        Response response = new Response();
        try {
            MmResponse mmResponse = mmProviderService.status(request, appId);
            response.setMessage(mmResponse.getMessage());
            response.setErrorCode(mmResponse.getErrorCode());
            response.setStatus(mmResponse.getStatus());
            response.setStatusCode(mmResponse.getStatusCode().getValue());
        } catch (Exception e) {
            e.printStackTrace();
            response.setConsumeId(request.getConsumeId());
            response.setMessage(ErrorCode.ERROR_000007.getName() + "：" + e.getMessage());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            return response;
        }
        return response;
    }
}
