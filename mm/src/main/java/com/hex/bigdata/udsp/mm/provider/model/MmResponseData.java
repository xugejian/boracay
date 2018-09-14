package com.hex.bigdata.udsp.mm.provider.model;

import com.hex.bigdata.udsp.common.api.model.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MmResponseData implements Serializable {
    /*
    同步
     */
    private List<Map<String, String>> records; // 记录集合
    private Page page;//分页信息

    /*
    异步
     */
    private String file;//文件路径

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
