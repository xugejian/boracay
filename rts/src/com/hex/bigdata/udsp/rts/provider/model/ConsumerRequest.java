package com.hex.bigdata.udsp.rts.provider.model;

/**
 * Created by junjiem on 2017-2-20.
 */
public class ConsumerRequest {

    private ConsumerApplication consumerApplication;

    private int timeout;

    public ConsumerRequest(){

    }

    public ConsumerRequest(ConsumerApplication consumerApplication, int timeout) {
        this.consumerApplication = consumerApplication;
        this.timeout = timeout;
    }

    public ConsumerApplication getConsumerApplication() {
        return consumerApplication;
    }

    public void setConsumerApplication(ConsumerApplication consumerApplication) {
        this.consumerApplication = consumerApplication;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
