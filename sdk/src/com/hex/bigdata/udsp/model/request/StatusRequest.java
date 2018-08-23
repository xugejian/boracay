package com.hex.bigdata.udsp.model.request;

public class StatusRequest extends UdspRequest {

    private String consumeId;

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }
}
