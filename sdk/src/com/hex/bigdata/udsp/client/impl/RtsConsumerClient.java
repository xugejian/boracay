package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.RtsConsumerRequest;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import com.hex.bigdata.udsp.model.response.origin.SyncResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import com.hex.bigdata.udsp.util.SdkHttpUtil;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:14:07
 */
public class RtsConsumerClient extends ConsumerClient {

    private RtsConsumerClient(){}

    private RtsConsumerClient(String requestUrl) {
        super(requestUrl);
    }

    /**
     * 实时流消费者-同步start
     * @param request
     * @return
     */
    public SyncPackResponse syncStart(RtsConsumerRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, SdkConstant.CONSUMER_TYPE_SYNC, SdkConstant.CONSUMER_ENTITY_START);
        //检查业务参数
        this.checkStartBusinessParams(request);
        SyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(),SyncResponse.class);
        return this.transSyncPackResponse(response);
    }

    @Override
    protected void checkStartBusinessParams(UdspRequest udspRequest) {

    }


}
