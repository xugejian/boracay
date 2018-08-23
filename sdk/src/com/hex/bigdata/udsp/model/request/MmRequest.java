package com.hex.bigdata.udsp.model.request;

import java.util.Map;

public class MmRequest extends UdspRequest {

    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
