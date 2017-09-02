package com.hex.bigdata.udsp.model.response.pack;

import com.hex.bigdata.udsp.constant.ErrorCode;
import com.hex.bigdata.udsp.constant.StatusCode;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/20
 * TIME:18:31
 */
public class UdspPackResponse {
    /**
     * 状态
     */
    private String status;

    /**
     * 消费ID
     */
    private String consumeId;

    /**
     * 持续时间
     */
    private long consumeTime;

    /**
     * 错误编码
     */
    private ErrorCode errorCode;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 状态码
     */
    private StatusCode statusCode;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

}
