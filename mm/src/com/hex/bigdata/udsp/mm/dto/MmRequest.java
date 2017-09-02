package com.hex.bigdata.udsp.mm.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/3
 * TIME:9:46
 */
public class MmRequest implements Serializable {
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 源系统用户
     */
    private String appUser;

    /**
     * 接口业务类型start、status
     */
    private String entity;

    /**
     * 接口调用类型：sync同步、async异步
     */
    private String type;

    /**
     * 最大返回记录数
     */
    private long limit;

    /**
     * 任务的唯一识别码
     */
    private String uuid;

    /**
     * 返回的字段
     */
    private String reponseField;

    /**
     * 异步调用-生成文件后的存放路径
     */
    private String path;

    /**
     * 业务参数
     */
    private Map<String,String> request;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getReponseField() {
        return reponseField;
    }

    public void setReponseField(String reponseField) {
        this.reponseField = reponseField;
    }

    public Map<String, String> getRequest() {
        return request;
    }

    public void setRequest(Map<String, String> request) {
        this.request = request;
    }
}
