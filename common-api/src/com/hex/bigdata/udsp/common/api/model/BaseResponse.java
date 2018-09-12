package com.hex.bigdata.udsp.common.api.model;

import java.io.Serializable;

/**
 * Created by JunjieM on 2018-8-31.
 */
public class BaseResponse implements Serializable {
    private String status; // 状态
    private String statusCode; // 状态码
    private String message; // 错误信息
    private String errorCode; // 错误编码
    private String consumeId; // 消费ID
    private long consumeTime; // 持续时间

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
