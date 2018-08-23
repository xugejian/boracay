package com.hex.bigdata.udsp.model.response.origin;

import com.hex.bigdata.udsp.model.Page;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SyncResponse extends UdspResponse {

    private Page page;
    private List<Map<String, String>> records;
    private LinkedHashMap<String, String> returnColumns;

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public LinkedHashMap<String, String> getReturnColumns() {
        return returnColumns;
    }

    public void setReturnColumns(LinkedHashMap<String, String> returnColumns) {
        this.returnColumns = returnColumns;
    }
}
