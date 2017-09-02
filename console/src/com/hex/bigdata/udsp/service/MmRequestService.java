package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mm.dto.MmResponse;
import com.hex.bigdata.udsp.mm.dto.MmResponseData;
import com.hex.bigdata.udsp.mm.service.MmProviderService;
import com.hex.bigdata.udsp.model.Request;
import com.hex.bigdata.udsp.model.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模型请求的服务
 */
@Service
public class MmRequestService {
    /**
     * 模型调用服务
     */
    @Autowired
    private MmProviderService mmProviderService;

    /**
     * @param mcCurrent 并发信息
     * @param appId     应用Id
     * @param request   请求实体
     * @return
     */
    public Response start(McCurrent mcCurrent, String appId, Request request) {

        Response response = new Response();
        MmResponse mmResponse = null;
        String consumeId = mcCurrent.getPkId();
        try {

            //内部请求，则设置serviceName
            if (ConsumerConstant.CONSUMER_REQUEST_TYPE_INNER.equals(request.getRequestType())) {
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
            response.setMessage(ErrorCode.ERROR_000007.getName() + "："+ e.getMessage());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            return response;
        }
        return response;
    }

    /**
     * 模型调用-异步status
     *
     * @param request
     * @return
     */
    public Response status(Request request, String appId) {
        Response response = new Response();
        MmResponse mmResponse = null;
        try {
            mmResponse = mmProviderService.status(request, appId);
            if (response != null){
                response.setMessage(mmResponse.getMessage());
                response.setErrorCode(mmResponse.getErrorCode());
                response.setStatus(mmResponse.getStatus());
                response.setStatusCode(mmResponse.getStatusCode().getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            //设置消费id
            response.setConsumeId(request.getConsumeId());
            response.setMessage(ErrorCode.ERROR_000007.getName()+ "："+ e.getMessage());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            return response;
        }
        return response;
    }
}
