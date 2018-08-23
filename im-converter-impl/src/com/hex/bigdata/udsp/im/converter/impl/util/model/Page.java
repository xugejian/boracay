package com.hex.bigdata.udsp.im.converter.impl.util.model;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-26.
 */
public class Page {
    private List<Map<String, String>> records; // 每页记录集合
    private int pageSize = 10; // 默认的每页显示条数
    private int pageIndex = 1; // 当前页
    private long totalCount; // 总记录数
    private long totalPage; // 总页数

    public Page(List<Map<String, String>> records, int pageIndex, int pageSize, long totalCount) {
        this.records = records;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalCount = totalCount;
        this.totalPage = (totalCount % pageSize == 0 ? totalCount / pageSize
                : (totalCount / pageSize + 1));
    }

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.totalPage = (totalCount % pageSize == 0 ? totalCount / pageSize
                : (totalCount / pageSize + 1));
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}
