package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.constant.EnumTrans;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.mm.provider.model.MmResponse;
import com.hex.bigdata.udsp.mm.provider.model.MmResponseData;
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
     * 调用（同步、异步）
     *
     * @param request 请求实体
     * @return
     */
    public Response start(Request request) {
        long bef = System.currentTimeMillis();
        Response response = new Response();
        try {
            checkParam(request.getAppId(), request.getData());
        } catch (Exception e) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000009.getValue());
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.toString());
            return response;
        }
        try {
            MmResponse mmResponse = mmProviderService.start(request);
            response.setStatus(mmResponse.getStatus());
            response.setStatusCode(Status.SUCCESS.getValue().equals(mmResponse.getStatus())
                    ? StatusCode.SUCCESS.getValue() : StatusCode.DEFEAT.getValue());
            response.setErrorCode(mmResponse.getErrorCode());
            response.setMessage(mmResponse.getMessage());
            if (Status.SUCCESS.getValue().equals(mmResponse.getStatus())) {
                MmResponseData mmResponseData = mmResponse.getData();
                if (mmResponseData != null) {
                    response.setRecords(mmResponseData.getRecords());
                    response.setResponseContent(mmResponseData.getFile());
                }
            }
            response.setConsumeTime(System.currentTimeMillis() - bef);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(ErrorCode.ERROR_000007.getName() + "：" + e.toString());
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
     * 查看状态
     *
     * @param request
     * @return
     */
    public Response status(Request request) {
        long bef = System.currentTimeMillis();
        Response response = new Response();
        try {
            MmResponse mmResponse = mmProviderService.status(request);
            response.setStatus(mmResponse.getStatus());
            response.setStatusCode(Status.SUCCESS.getValue().equals(mmResponse.getStatus())
                    ? StatusCode.SUCCESS.getValue() : StatusCode.DEFEAT.getValue());
            response.setErrorCode(mmResponse.getErrorCode());
            response.setMessage(mmResponse.getMessage());
            response.setConsumeTime(System.currentTimeMillis() - bef);
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
