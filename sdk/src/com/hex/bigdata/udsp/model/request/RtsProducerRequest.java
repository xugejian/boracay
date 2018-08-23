package com.hex.bigdata.udsp.model.request;

import java.util.List;
import java.util.Map;

public class RtsProducerRequest extends UdspRequest {

    private List<Map<String,String>> dataStream;

    public List<Map<String, String>> getDataStream() {
        return dataStream;
    }

    public void setDataStream(List<Map<String, String>> dataStream) {
        this.dataStream = dataStream;
    }
}
