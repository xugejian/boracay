package com.hex.bigdata.udsp.consumer;

import com.hex.bigdata.udsp.common.api.model.Page;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * WebService Response
 */
@XmlRootElement(name = "udspResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class WSResponse implements Serializable {

    private String status; // 状态
    private String statusCode; // 状态码
    private String message; // 错误信息
    private String errorCode; // 错误编码
    private String consumeId; // 消费ID
    private long consumeTime; // 持续时间

    private List<HashMap<String, String>> records; // 记录集合

    private Page page; // 分页信息

    public String responseContent; // 返回信息

    private LinkedHashMap<String, String> returnColumns; // 返回字段信息

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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

    public List<HashMap<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<HashMap<String, String>> records) {
        this.records = records;
    }
}
