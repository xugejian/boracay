package com.hex.bigdata.udsp.consumer.model;

import com.hex.bigdata.udsp.common.api.model.Page;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/4/17.
 */
public class Response extends BaseResponse implements Serializable {
    private List<Map<String, String>> records; // 记录集合

    private Page page; // 分页信息

    public String responseContent; // 返回信息

    private LinkedHashMap<String, String> returnColumns; // 返回字段信息

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

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public LinkedHashMap<String, String> getReturnColumns() {
        return returnColumns;
    }

    public void setReturnColumns(LinkedHashMap<String, String> returnColumns) {
        this.returnColumns = returnColumns;
    }
}
