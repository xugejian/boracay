package com.hex.bigdata.udsp.model.response.origin;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/15
 * TIME:15:17
 */
public class UdspResponse {

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
    private String errorCode;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 状态码
     */
    private String statusCode;

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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
