package com.hex.bigdata.udsp.common.api.model;

import java.io.Serializable;

/**
 * Created by JunjieM on 2018-8-31.
 */
public class BaseRequest implements Serializable {
    private String serviceName; // 服务名
    private String type = "SYNC"; // sync、async
    private String entity = "START"; // start、status、stop
    private String udspUser; // UDSP用户
    private String token; // UDSP密码
    private String appUser; // 外部的用户
    private Boolean readCache = true; // 是否读取缓存数据

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getUdspUser() {
        return udspUser;
    }

    public void setUdspUser(String udspUser) {
        this.udspUser = udspUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public Boolean getReadCache() {
        return readCache;
    }

    public void setReadCache(Boolean readCache) {
        this.readCache = readCache;
    }
}
