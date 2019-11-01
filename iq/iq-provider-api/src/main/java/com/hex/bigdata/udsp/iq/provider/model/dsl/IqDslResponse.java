package com.hex.bigdata.udsp.iq.provider.model.dsl;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义SQL响应类
 */
public class IqDslResponse {

    private List<Map<String, String>> records; // 返回结果集
    private LinkedHashMap<String, String> columns; // 返回字段信息
    private long consumeTime; // 持续时间(毫秒)
    private Status status; // 状态
    private StatusCode statusCode; // 状态码
    private String message; // 信息

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }

    public LinkedHashMap<String, String> getColumns() {
        return columns;
    }

    public void setColumns(LinkedHashMap<String, String> columns) {
        this.columns = columns;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
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
}
