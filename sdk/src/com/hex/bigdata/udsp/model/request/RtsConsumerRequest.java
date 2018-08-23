package com.hex.bigdata.udsp.model.request;

public class RtsConsumerRequest extends UdspRequest {

    private long timeout = 1000;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

}
