package com.hex.bigdata.udsp.model.request;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/15
 * TIME:9:54
 */
public class UdspRequest {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * udsp用户登录名
     */
    private String udspUser;
    /**
     * 访问令牌
     */
    private String token;
    /**
     * 源系统用户名称
     */
    private String appUser;

    /**
     * 请求类型，同步：sync；异步：async
     */
    private String type;

    /**
     * 业务类型：开始调用：start；查看状态：status
     */
    private String entity;

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
