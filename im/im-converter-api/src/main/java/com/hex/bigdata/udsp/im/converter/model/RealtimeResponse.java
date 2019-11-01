package com.hex.bigdata.udsp.im.converter.model;

/**
 * Created by JunjieM on 2018-9-12.
 */
public class RealtimeResponse {
    private long countNum = 0; // 消费总条数
    private long parseFailedNum = 0; // 解析失败数
    private long filterNum = 0; // 被过滤数
    private long storeSucceedNum = 0; // 存储成功数
    private long storeFailedNum = 0; // 存储失败数
    private String message; // 错误信息

    public long getCountNum() {
        return countNum;
    }

    public void setCountNum(long countNum) {
        this.countNum = countNum;
    }

    public long getParseFailedNum() {
        return parseFailedNum;
    }

    public void setParseFailedNum(long parseFailedNum) {
        this.parseFailedNum = parseFailedNum;
    }

    public long getFilterNum() {
        return filterNum;
    }

    public void setFilterNum(long filterNum) {
        this.filterNum = filterNum;
    }

    public long getStoreSucceedNum() {
        return storeSucceedNum;
    }

    public void setStoreSucceedNum(long storeSucceedNum) {
        this.storeSucceedNum = storeSucceedNum;
    }

    public long getStoreFailedNum() {
        return storeFailedNum;
    }

    public void setStoreFailedNum(long storeFailedNum) {
        this.storeFailedNum = storeFailedNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
