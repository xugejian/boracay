package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import com.hex.bigdata.udsp.model.response.origin.SyncResponse;
import com.hex.bigdata.udsp.model.request.RtsProducerRequest;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import com.hex.bigdata.udsp.util.SdkHttpUtil;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:14:08
 */
public class RtsProducerClient extends ConsumerClient {

    private RtsProducerClient() {
    }

    private RtsProducerClient(String requestUrl) {
        super(requestUrl);
    }

    /**
     * 实时流生产者-同步start
     *
     * @param request
     * @return
     */
    public SyncPackResponse syncStart(RtsProducerRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, SdkConstant.CONSUMER_TYPE_SYNC, SdkConstant.CONSUMER_ENTITY_START);
        //检查业务参数
        this.checkStartBusinessParams(request);
        SyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(),SyncResponse.class);
        return this.transSyncPackResponse(response);
    }

    @Override
    protected void checkStartBusinessParams(UdspRequest udspRequest) {
        RtsProducerRequest request = (RtsProducerRequest) udspRequest;
        List<Map<String, String>> dataStream = request.getDataStream();
        if (dataStream == null&&dataStream.isEmpty()){
            throw new IllegalArgumentException("查询业务参数dataStream不能为空!");
        }
    }
}
