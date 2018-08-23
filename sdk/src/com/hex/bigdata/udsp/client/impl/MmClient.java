package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.constant.ConsumerEntity;
import com.hex.bigdata.udsp.constant.ConsumerType;
import com.hex.bigdata.udsp.model.request.MmRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import com.hex.bigdata.udsp.model.response.origin.AsyncResponse;
import com.hex.bigdata.udsp.model.response.origin.StatusResponse;
import com.hex.bigdata.udsp.model.response.origin.SyncResponse;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import com.hex.bigdata.udsp.util.SdkHttpUtil;

import java.util.Map;

/**
 * 模型管理调用客户端
 */
public class MmClient extends ConsumerClient {

    private MmClient() {
    }

    private MmClient(String requestUrl) {
        super(requestUrl);
    }

    /**
     * 模型管理-同步start
     *
     * @param request
     * @return
     */
    public SyncPackResponse syncStart(MmRequest request) {
        this.checkBasicParams(request, ConsumerType.SYNC, ConsumerEntity.START);
        this.checkStartBusinessParams(request);
        SyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), SyncResponse.class);
        return this.transSyncPackResponse(response);
    }

    /**
     * 模型管理-异步start
     *
     * @param request
     * @return
     */
    public AsyncPackResponse asyncStart(MmRequest request) {
        this.checkBasicParams(request, ConsumerType.ASYNC, ConsumerEntity.START);
        this.checkStartBusinessParams(request);
        AsyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), AsyncResponse.class);
        return this.transAsyncPackResponse(response);
    }

    /**
     * 交互查询-异步status
     *
     * @param request
     * @return
     */
    public StatusPackResponse asyncStatus(StatusRequest request) {
        this.checkBasicParams(request, ConsumerType.ASYNC, ConsumerEntity.STATUS);
        this.checkStatusBusinessParams(request.getConsumeId());
        StatusResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), StatusResponse.class);
        return this.transStatusResponse(response);
    }

    @Override
    protected void checkStartBusinessParams(UdspRequest udspRequest) {
        MmRequest request = (MmRequest) udspRequest;
        Map<String, String> data = request.getData();
        if (data == null || data.size() == 0) {
            throw new IllegalArgumentException("参数data不能为空!");
        }
    }

}
