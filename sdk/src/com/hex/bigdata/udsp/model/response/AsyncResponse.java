package com.hex.bigdata.udsp.model.response;

import com.hex.bigdata.udsp.consumer.model.BaseResponse;

public class AsyncResponse extends BaseResponse {

    private String responseContent;

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }
}
