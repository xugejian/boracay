package com.hex.bigdata.udsp.common.aggregator.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2019-8-20.
 */
public class H2Response {

    private List<Map<String, String>> records; // 返回结果集
    private LinkedHashMap<String, String> columns; // 返回字段信息

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }

    public LinkedHashMap<String, String> getColumns() {
        return columns;
    }

    public void setColumns(LinkedHashMap<String, String> columns) {
        this.columns = columns;
    }
}
