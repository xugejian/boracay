package com.hex.bigdata.udsp.model.request;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:14:29
 */
public class StatusRequest extends UdspRequest {
    /**
     *  消费ID
     */
    private String consumeId;

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }
}
