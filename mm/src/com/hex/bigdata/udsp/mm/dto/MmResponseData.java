package com.hex.bigdata.udsp.mm.dto;

import com.hex.bigdata.udsp.common.provider.model.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/3
 * TIME:14:32
 */
public class MmResponseData implements Serializable {

    private List<Map<String, String>> records; // 记录集合

    private Page page;//分页信息

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
