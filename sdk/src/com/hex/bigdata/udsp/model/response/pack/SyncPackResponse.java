package com.hex.bigdata.udsp.model.response.pack;

import com.hex.bigdata.udsp.model.Page;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/19
 * TIME:20:58
 */
public class SyncPackResponse extends UdspPackResponse {
    /**
     * 记录集合
     */
    private List<Map<String, String>> records;
    /**
     * 分页
     */
    private Page page;

    /**
     * 返回字段名称和类型 key:名称；value:value
     */
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
