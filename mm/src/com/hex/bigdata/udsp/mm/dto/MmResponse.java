package com.hex.bigdata.udsp.mm.dto;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/3
 * TIME:14:25
 */
public class MmResponse implements Serializable{

    /**
     * 状态
     */
    private String status;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 请求类型，同步：sync，异步：async
     */
    private String type;

    /**
     * 请求业务类型：start 开始调用模型
     */
    private String entity;

    /**
     * 任务id
     */
    private String uuid;

    /**
     * 执行时间
     */
    private long consumeTime;


    /**
     * 任务运行状态
     */
    private Status systemStatus;

    /**
     * 状态码
     */
    private StatusCode statusCode;

    /**
     * 返回数据信息
     */
    private MmResponseData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Status getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Status systemStatus) {
        this.systemStatus = systemStatus;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public MmResponseData getData() {
        return data;
    }

    public void setData(MmResponseData data) {
        this.data = data;
    }


}
