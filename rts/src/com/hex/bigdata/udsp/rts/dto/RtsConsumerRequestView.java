package com.hex.bigdata.udsp.rts.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/3/8
 * TIME:14:43
 */
public class RtsConsumerRequestView implements Serializable {

    private String consumerId;

    private int timeout=10000;

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
