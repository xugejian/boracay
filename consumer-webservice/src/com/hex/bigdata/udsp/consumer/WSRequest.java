package com.hex.bigdata.udsp.consumer;

import com.hex.bigdata.udsp.common.api.model.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * WebService Request
 */
public class WSRequest implements Serializable {
    private String serviceName; // 服务名

    private String type; // sync、async
    private String entity; // start、status、stop

    private String udspUser; // UDSP用户
    private String token; // UDSP密码

    private String appUser; // 外部的用户

    private String appType; // 应用类型
    private String appName; // 应用名称
    private String appId; // 应用ID

    private String requestType; // 请求类型
    private String requestIp; // 请求IP

    private Page page; // 分页信息
    private String sql; // SQL语句
    private HashMap<String, String> data; // 请求数据

    private long timeout; // 超时时间（毫秒）
    private List<HashMap<String, String>> dataStream; // 数据流集合

    private String consumeId; // 消费ID

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getUdspUser() {
        return udspUser;
    }

    public void setUdspUser(String udspUser) {
        this.udspUser = udspUser;
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public List<HashMap<String, String>> getDataStream() {
        return dataStream;
    }

    public void setDataStream(List<HashMap<String, String>> dataStream) {
        this.dataStream = dataStream;
    }
}
