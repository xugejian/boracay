package com.hex.bigdata.udsp.olq.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/3/14.
 */
public class ResultData implements Serializable {
    private List<String> fields; // 字段集
    private List<Map<String, String>> records; // 记录集

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }
}
