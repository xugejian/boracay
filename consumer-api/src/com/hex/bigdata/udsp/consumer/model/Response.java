package com.hex.bigdata.udsp.consumer.model;

import com.hex.bigdata.udsp.common.api.model.BaseResponse;
import com.hex.bigdata.udsp.common.api.model.Page;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/4/17.
 */
public class Response extends BaseResponse implements Serializable {
    /*
    由于使用字段进行对象拷贝，所以这里父类的字段也需要重载
     */
    // ------------------------------------
    private String status; // 状态
    private String statusCode; // 状态码
    private String message; // 错误信息
    private String errorCode; // 错误编码
    private String consumeId; // 消费ID
    private long consumeTime; // 持续时间
    // ------------------------------------

    private List<Map<String, String>> records; // 记录集合
    private LinkedHashMap<String, String> returnColumns; // 返回字段信息
    private Page page; // 分页信息
    public String responseContent; // 返回信息

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

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public LinkedHashMap<String, String> getReturnColumns() {
        return returnColumns;
    }

    public void setReturnColumns(LinkedHashMap<String, String> returnColumns) {
        this.returnColumns = returnColumns;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getConsumeId() {
        return consumeId;
    }

    @Override
    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }

    @Override
    public long getConsumeTime() {
        return consumeTime;
    }

    @Override
    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }
}
