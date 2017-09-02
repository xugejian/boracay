package com.hex.bigdata.udsp.model.request;

import java.util.Map;

/**
 * 模型管理请求
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/15
 * TIME:10:55
 */
public class MmRequest extends UdspRequest {
    /**
     * 传入参数Map
     */
    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
