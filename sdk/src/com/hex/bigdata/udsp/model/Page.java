package com.hex.bigdata.udsp.model;

public class Page {
    /**
     * 当前页
     */
    private int pageIndex = 1;
    /**
     * 每页显示条数
     */
    private int pageSize = 10;

    /**
     *  总页数
     */
    private int totalPage = 1;

    /**
     * 总记录数
     */
    private int totalCount = 0;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
    }
}