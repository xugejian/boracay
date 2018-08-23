package com.hex.bigdata.udsp.model.request;

public class UdspRequest {

    private String serviceName;
    private String type;
    private String entity;
    private String udspUser;
    private String token;
    private String appUser;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
}
