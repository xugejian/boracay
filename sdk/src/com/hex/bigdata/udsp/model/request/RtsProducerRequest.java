package com.hex.bigdata.udsp.model.request;

import java.util.List;
import java.util.Map;

/**
 * 实时流生产者请求
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/16
 * TIME:9:13
 */
public class RtsProducerRequest extends UdspRequest {
    /**
     * 输入参数
     */
    private List<Map<String,String>> dataStream;

    public List<Map<String, String>> getDataStream() {
        return dataStream;
    }

    public void setDataStream(List<Map<String, String>> dataStream) {
        this.dataStream = dataStream;
    }
}
