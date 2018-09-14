package com.hex.bigdata.udsp.rts.executor.model;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-2-20.
 */
public class ConsumerResponse {

    private ConsumerRequest consumerRequest; // 请求信息
    private List<Map<String,String>> records; // 记录集合
    private long totalCount; // 总记录数
    private long consumeTime; // 持续时间
    private Status status; // 状态
    private StatusCode statusCode; // 状态码
    private String message; // 信息
    private LinkedHashMap<String,String> columns;//返回字段信息

    public ConsumerRequest getConsumerRequest() {
        return consumerRequest;
    }

    public void setConsumerRequest(ConsumerRequest consumerRequest) {
        this.consumerRequest = consumerRequest;
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

    public LinkedHashMap<String, String> getColumns() {
        return columns;
    }

    public void setColumns(LinkedHashMap<String, String> columns) {
        this.columns = columns;
    }
}
