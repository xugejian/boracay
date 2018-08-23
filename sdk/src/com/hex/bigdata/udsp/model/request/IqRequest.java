package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.model.Page;

import java.util.Map;

@Deprecated
public class IqRequest extends UdspRequest {

    private Page page;
    private Map<String, String> data;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
