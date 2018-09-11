package com.hex.bigdata.udsp.mm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.constant.ConsumerType;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.mm.dto.MmFullAppInfoView;
import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import com.hex.bigdata.udsp.mm.model.MmAppReturnParam;
import com.hex.bigdata.udsp.mm.provider.model.MmRequest;
import com.hex.bigdata.udsp.mm.provider.model.MmResponse;
import com.hex.bigdata.udsp.mm.provider.model.MmResponseData;
import com.hex.goframe.util.DateUtil;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 远程调用服务接口
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/3
 * TIME:9:32
 */
@Service
public class MmProviderService {

    private static Logger logger = LogManager.getLogger(MmProviderService.class);

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTEXT_TYPE_TEXT_JSON = "text/json";

    private static final Map<String, String> typeMap = new HashMap<String, String>() {{
        put("sync", "1");
        put("async", "2");
    }};

    @Autowired
    private MmApplicationService mmApplicationService;

    /**
     * 模型调度最大返回记录数
     */
    @Value("${udsp.mm.return.maxnum}")
    private long returnLimit;

    /**
     * 调用（同步、异步）
     */
    public MmResponse start(Request request) {
        MmResponse response = new MmResponse();
        response.setUuid(request.getConsumeId());
        MmFullAppInfoView appInfoView = mmApplicationService.selectFullAppInfo(request.getAppId());
        if (appInfoView == null) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_200004.getValue());
            response.setMessage(ErrorCode.ERROR_200004.getName() + ":获取不到模型应用信息！");
            return response;
        }
        String modelType = appInfoView.getModelType();
        String type = request.getType().toLowerCase();
        String typeKey = typeMap.get(type);
        if (!modelType.contains(typeKey)) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_200005.getValue());
            response.setMessage(ErrorCode.ERROR_200005.getName() + ":该模型不支持" + type + "类型!");
            return response;
        }
        try {
            MmRequest mmRequest = getMmRequest(appInfoView, request);
            response = requestModelService(mmRequest, appInfoView.getHttpUrl());
        } catch (ConnectException e) {
            e.printStackTrace();
            response.setErrorCode(ErrorCode.ERROR_200001.getValue());
            response.setMessage(ErrorCode.ERROR_200001.getName() + ":" + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setErrorCode(ErrorCode.ERROR_200004.getValue());
            response.setMessage(ErrorCode.ERROR_200004.getName() + ":" + e.toString());
        }
        return response;
    }

    /**
     * 查看状态
     */
    public MmResponse status(Request request) throws Exception {
        MmFullAppInfoView appInfoView = mmApplicationService.selectFullAppInfo(request.getAppId());
        if (appInfoView == null) {
            MmResponse response = new MmResponse();
            response.setStatus(Status.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_200004.getValue());
            response.setMessage(ErrorCode.ERROR_200004.getName() + ":获取不到模型应用信息！");
            return response;
        }
        MmRequest mmRequest = new MmRequest();
        mmRequest.setModelName(appInfoView.getModelName());
        mmRequest.setType(request.getType());
        mmRequest.setEntity(request.getEntity());
        mmRequest.setUuid(request.getConsumeId());
        return this.requestModelService(mmRequest, appInfoView.getHttpUrl());
    }

    private MmRequest getMmRequest(MmFullAppInfoView appInfoView, Request request) {
        MmRequest mmRequest = new MmRequest();
        mmRequest.setType(request.getType());
        mmRequest.setEntity(request.getEntity());
        mmRequest.setModelName(appInfoView.getModelName());
        mmRequest.setAppUser(request.getAppUser());
        mmRequest.setLimit(returnLimit);
        mmRequest.setUuid(request.getConsumeId());
        List<MmAppReturnParam> returnParams = appInfoView.getReturnParams();
        if (returnParams != null && returnParams.size() != 0) {
            String responseField = "";
            int count = 0;
            for (MmAppReturnParam returnParam : returnParams) {
                responseField += (count == 0 ? "" : ",") + returnParam.getName();
                count++;
            }
            mmRequest.setResponseField(responseField);
        }
        Map<String, String> paraMap = request.getData();
        List<MmAppExecuteParam> executeParams = appInfoView.getExecuteParams();
        if (executeParams != null && executeParams.size() != 0) {
            Map<String, String> map = new HashMap<>();
            for (MmAppExecuteParam executeParam : executeParams) {
                String name = executeParam.getName();
                String value = (paraMap != null ? paraMap.get(name) : null);
                map.put(name, value);
            }
            mmRequest.setRequest(map);
        }
        if (ConsumerType.ASYNC.getValue().equalsIgnoreCase(request.getType())) {
            String path = CreateFileUtil.getFtpFileDir(request.getUdspUser()) + "/" +
                    appInfoView.getContractorName() + "/" + DateUtil.format(new Date(), "yyyyMMdd");
            mmRequest.setPath(path);
        }
        return mmRequest;
    }

    /**
     * 请求调用模型接口
     *
     * @param mmRequest
     * @param url
     */
    private MmResponse requestModelService(MmRequest mmRequest, String url) throws Exception {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(mmRequest);
        logger.info("请求地址：" + url);
        logger.info("请求参数：" + jsonObject.toJSONString());
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        StringEntity entity = new StringEntity(jsonObject.toJSONString());
        entity.setContentType(CONTEXT_TYPE_TEXT_JSON);
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        String returnString = analysisResponse(response);
        logger.info("模型返回信息：" + returnString);
        MmResponse mmResponse = JSONUtil.parseJSON2Obj(returnString, MmResponse.class);
        JSONObject dataObject = JSON.parseObject(returnString).getJSONObject("data");
        if (dataObject != null) {
            MmResponseData mmResponseData = JSONUtil.parseJSON2Obj(dataObject.toJSONString(), MmResponseData.class);
            mmResponse.setData(mmResponseData);
        }
        return mmResponse;
    }

    /**
     * 解析返回的http请求
     *
     * @param httpResponse
     * @return
     */
    private String analysisResponse(HttpResponse httpResponse) throws Exception {
        if (httpResponse == null) {
            throw new Exception("httpResponse为空");
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (HttpStatus.SC_OK != statusCode) {
            throw new Exception(httpResponse.toString());
        }
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder buffer = new StringBuilder();
        HttpEntity entity = httpResponse.getEntity();
        String returnString = "";
        try {
            is = entity.getContent();
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            returnString = buffer.toString();
            returnString = StringEscapeUtils.unescapeJava(returnString);
        } catch (Exception e) {
            throw new Exception("解析返回结果失败!" + e.toString());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnString;
    }
}
