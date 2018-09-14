package com.hex.bigdata.udsp.iq.provider.impl.model;

import java.util.List;

/**
 * Solr+HBase查询的分页类
 */
public class SolrHBasePage {
    private List<String> records; // 每页记录集合，只存储id值

    private int pageIndex = 1; // 当前页
    private int pageSize = 10; // 默认的每页显示条数

    private long totalCount; // 总记录数
    private long totalPage; // 总页数

    public SolrHBasePage() {
        super();
    }

    public SolrHBasePage(List<String> records, int pageIndex, int pageSize, long totalCount) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalCount = totalCount;
        this.records = records;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
                : (totalCount / pageSize + 1);
    }

    public List<String> getRecords() {
        return records;
    }

    public void setRecords(List<String> records) {
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
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
                : (totalCount / pageSize + 1);
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}