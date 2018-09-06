package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.consumer.model.BaseRequest;

public class RtsConsumerRequest extends BaseRequest {

    private long timeout = 1000;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

}
