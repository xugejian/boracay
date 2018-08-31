package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.consumer.model.BaseRequest;

import java.util.Map;

public class ImRequest extends BaseRequest {

    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
