package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.constant.ConsumerEntity;
import com.hex.bigdata.udsp.constant.ConsumerType;
import com.hex.bigdata.udsp.model.request.RtsConsumerRequest;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import com.hex.bigdata.udsp.model.response.origin.SyncResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import com.hex.bigdata.udsp.util.SdkHttpUtil;

/**
 * 实时流-消费者客户端
 */
public class RtsConsumerClient extends ConsumerClient {

    private RtsConsumerClient() {
    }

    private RtsConsumerClient(String requestUrl) {
        super(requestUrl);
    }

    /**
     * 实时流消费者-同步start
     *
     * @param request
     * @return
     */
    public SyncPackResponse syncStart(RtsConsumerRequest request) {
        this.checkBasicParams(request, ConsumerType.SYNC, ConsumerEntity.START);
        this.checkStartBusinessParams(request);
        SyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), SyncResponse.class);
        return this.transSyncPackResponse(response);
    }

    @Override
    protected void checkStartBusinessParams(UdspRequest udspRequest) {
        RtsConsumerRequest request = (RtsConsumerRequest) udspRequest;
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("参数timeout必须大于零!");
        }
    }


}
