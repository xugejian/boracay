package com.hex.bigdata.udsp.rts.executor.model;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;

/**
 * Created by junjiem on 2017-2-20.
 */
public class ProducerResponse {

    private ProducerRequest producerRequest; // 请求信息

    private long consumeTime; // 持续时间

    private Status status; // 状态

    private StatusCode statusCode; // 状态码

    private String message; // 信息

    public ProducerRequest getProducerRequest() {
        return producerRequest;
    }

    public void setProducerRequest(ProducerRequest producerRequest) {
        this.producerRequest = producerRequest;
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
