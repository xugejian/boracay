package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.common.api.model.BaseRequest;

public class StatusRequest extends BaseRequest {

    private String consumeId;

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }
}
