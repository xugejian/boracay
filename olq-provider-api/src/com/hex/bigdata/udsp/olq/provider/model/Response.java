package com.hex.bigdata.udsp.olq.provider.model;


import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;

import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-2-15.
 */
public class Response {
    private Request request; // 请求信息

    private ResultSetMetaData metadata; // 元数据
    private List<Map<String, String>> records; // 记录集合
    private long totalCount; // 总记录数

    private long consumeTime; // 持续时间

    private Status status; // 状态
    private StatusCode statusCode; // 状态码

    private String message; // 信息

    /**
     * 异步时存储文件路径
     */
    private String filePath;

    public ResultSetMetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(ResultSetMetaData metadata) {
        this.metadata = metadata;
    }

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
