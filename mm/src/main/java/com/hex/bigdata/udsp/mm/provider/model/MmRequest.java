package com.hex.bigdata.udsp.mm.provider.model;

import java.io.Serializable;
import java.util.Map;

public class MmRequest implements Serializable {
    private String modelName; // 模型名称
    private String appUser;
    private String entity; // 调用类型
    private String type; // 接口类型
    private long limit;
    private String uuid; // 任务Id
    private String responseField; // 返回的字段，若该参数为空时表示模型字段全部返回，否则参数形式如下，逗号分隔“field1,field2,field3”
    private Map<String, String> request; // 业务参数(JSON字符串)
    private String path; // 文件路径

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


    public Map<String, String> getRequest() {
        return request;
    }

    public void setRequest(Map<String, String> request) {
        this.request = request;
    }

    public String getResponseField() {
        return responseField;
    }

    public void setResponseField(String responseField) {
        this.responseField = responseField;
    }
}
