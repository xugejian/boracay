package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.NoSqlRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import com.hex.bigdata.udsp.model.response.origin.AsyncResponse;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.origin.StatusResponse;
import com.hex.bigdata.udsp.model.response.origin.SyncResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import com.hex.bigdata.udsp.util.SdkHttpUtil;

/**
 * NoSQL（交互查询/联机查询应用客户端）
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/19
 * TIME:16:30
 */
public class NoSqlClient extends ConsumerClient {

    private NoSqlClient() {
    }

    private NoSqlClient(String requestUrl) {
        super(requestUrl);
    }

    /**
     * 交互查询-同步start
     *
     * @param request
     * @return
     */
    public SyncPackResponse syncStart(NoSqlRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, SdkConstant.CONSUMER_TYPE_SYNC, SdkConstant.CONSUMER_ENTITY_START);
        //检查业务参数
        this.checkStartBusinessParams(request);
        SyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), SyncResponse.class);
        return this.transSyncPackResponse(response);
    }

    /**
     * 交互查询-异步start
     *
     * @param request
     * @return
     */
    public AsyncPackResponse asyncStart(NoSqlRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, SdkConstant.CONSUMER_TYPE_ASYNC, SdkConstant.CONSUMER_ENTITY_START);
        //检查业务参数
        this.checkStartBusinessParams(request);
        AsyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(),AsyncResponse.class);
        return this.transAsyncPackResponse(response);
    }

    /**
     * 交互查询-异步status
     *
     * @param request
     * @return
     */
    public StatusPackResponse asyncStatus(StatusRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, SdkConstant.CONSUMER_TYPE_ASYNC, SdkConstant.CONSUMER_ENTITY_STATUS);
        this.checkStatusBusinessParams(request.getConsumeId());
        StatusResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(),StatusResponse.class);
        return this.transStatusResponse(response);
    }

    /**
     * 检查start业务参数
     */
    protected void checkStartBusinessParams(UdspRequest udspRequest) {
    }
}
