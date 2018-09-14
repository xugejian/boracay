package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.common.api.model.BaseRequest;

import java.util.Map;

public class MmRequest extends BaseRequest {

    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
