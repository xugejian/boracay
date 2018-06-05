package com.hex.bigdata.udsp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.HostUtil;
import com.hex.bigdata.udsp.model.RequestModel;
import com.hex.bigdata.udsp.model.ResponseModel;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/4
 * TIME:15:17
 */
public class BatchHttpControllerTest {

    private static Logger logger = LogManager.getLogger(BatchHttpControllerTest.class);

    private static final String APPLICATION_JSON="application/json;charset=UTF-8";
    private static final String CONTEXT_TYPE_TEXT_JSON="text/json";

    public static void main(String[] args) {

        BatchHttpControllerTest test=new BatchHttpControllerTest();

        //批处理-start接口
        //test.httpJsonTest_mmBatchStart();
        //批处理-status接口
        test.httpJsonTest_mmBatchStatus();
        //批处理-stop接口
        //test.httpJsonTest_mmBatchStop();
    }

    /**
     *  批处理-start接口
     */
    public void httpJsonTest_mmBatchStart(){
        //String url="http://10.1.40.152:8080/modelManage/test";
        String url="http://10.1.96.180:5000/taoshu";
        RequestModel requestModel=new RequestModel();
        requestModel.setModelName("MN_XBDKLSKHYXMX");
        requestModel.setEntity("start");
        requestModel.setBizDate("20170531");
        requestModel.setType("batch");
        requestModel.setLimit(1000);
        String uuid= HostUtil.getConsumeId(JSONUtil.parseObj2JSON(requestModel));
        logger.info("uuid:"+uuid);
        requestModel.setUuid(uuid);
        this.requestUdsp(requestModel,url);
    }

    /**
     * 批处理-status接口
     */
    public void httpJsonTest_mmBatchStatus(){
        //String url="http://10.1.40.152:8080/modelManage/test";
        String url="http://10.1.96.180:5000/status/taoshu";
        RequestModel requestModel=new RequestModel();
        requestModel.setModelName("MN_XBDKLSKHYXMX");
        requestModel.setType("batch");
        requestModel.setEntity("status");
        requestModel.setUuid("19da92f39c2c0abfa0f5ade553045b69");
        this.requestUdsp(requestModel,url);
    }

    /**
     * 批处理-stop接口
     */
    public void httpJsonTest_mmBatchStop(){
        String url="http://10.1.40.152:8080/modelManage/test";
        RequestModel requestModel=new RequestModel();
        requestModel.setModelName("model1");
        requestModel.setType("batch");
        requestModel.setEntity("stop");
        requestModel.setUuid(HostUtil.getConsumeId(JSONUtil.parseObj2JSON(requestModel)));
        this.requestUdsp(requestModel,url);
    }

    /**
     * 公共方法
     * @param externalRequest
     * @param url
     */
    public void requestUdsp(RequestModel externalRequest,String url){

        JSONObject jsonObject= (JSONObject) JSON.toJSON(externalRequest);

        //打印json参数
        logger.warn("json参数：" + jsonObject.toJSONString());

        logger.warn("请求url：" + url);

        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);
        InputStream resultStream = null;
        try {
            StringEntity se=new StringEntity(jsonObject.toJSONString());
            se.setContentType(CONTEXT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            String returnString=analysisResponse(response);
            logger.warn(returnString);
            ResponseModel responseObject=JSON.parseObject(returnString, ResponseModel.class);
            logger.warn(JSONUtil.parseObj2JSON(responseObject));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String analysisResponse(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        String returnString = "";
        int statuscode = httpResponse.getStatusLine().getStatusCode();
        if (statuscode == HttpStatus.SC_NOT_FOUND) {
            throw new RuntimeException("请检查URL!");
        } else if (statuscode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            throw new RuntimeException("服务器内部错误!");
        } else if (statuscode == HttpStatus.SC_BAD_REQUEST) {
            throw new RuntimeException("错误的请求!");
        } else {
            InputStream resultStream = null;
            BufferedReader br = null;
            StringBuffer buffer = new StringBuffer();
            HttpEntity responseEntity = httpResponse.getEntity();
            try {
                resultStream = responseEntity.getContent();
                br = new BufferedReader(new InputStreamReader(resultStream));
                String tempStr;
                while ((tempStr = br.readLine()) != null) {
                    buffer.append(tempStr);
                }
                returnString = buffer.toString();
                returnString = StringEscapeUtils.unescapeJava(returnString);
            } catch (Exception e) {
                throw new RuntimeException("解析返回结果失败!");
            }
            return returnString;
        }
    }
}





