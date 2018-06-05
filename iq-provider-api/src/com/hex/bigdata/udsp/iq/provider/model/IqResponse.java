package com.hex.bigdata.udsp.iq.provider.model;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.api.model.Result;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by junjiem on 2017-3-2.
 */
public class IqResponse {
    private IqRequest request;

    private List<Result> records;

    private Page page;

    private long consumeTime; // 持续时间

    private Status status; // 状态

    private StatusCode statusCode; // 状态码

    private String message; // 信息

    /**
     * 用于异步调用时存储文件路径
     */
    private String filePath;

    /**
     *返回字段信息
     */
    private LinkedHashMap<String,String> columns;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public IqRequest getRequest() {
        return request;
    }

    public void setRequest(IqRequest request) {
        this.request = request;
    }

    public List<Result> getRecords() {
        return records;
    }

    public void setRecords(List<Result> records) {
        this.records = records;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LinkedHashMap<String, String> getColumns() {
        return columns;
    }

    public void setColumns(LinkedHashMap<String, String> columns) {
        this.columns = columns;
    }
}
