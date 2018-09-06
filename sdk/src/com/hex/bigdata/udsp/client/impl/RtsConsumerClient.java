package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.common.constant.ConsumerEntity;
import com.hex.bigdata.udsp.common.constant.ConsumerType;
import com.hex.bigdata.udsp.consumer.model.BaseRequest;
import com.hex.bigdata.udsp.model.request.RtsConsumerRequest;
import com.hex.bigdata.udsp.model.response.SyncResponse;
import com.hex.bigdata.udsp.model.response.SyncPackResponse;
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
    protected void checkStartBusinessParams(BaseRequest baseRequest) {
        RtsConsumerRequest request = (RtsConsumerRequest) baseRequest;
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("参数timeout必须大于零!");
        }
    }


}
