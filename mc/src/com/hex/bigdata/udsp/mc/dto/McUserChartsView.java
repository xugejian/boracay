package com.hex.bigdata.udsp.mc.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by PC on 2017/3/23.
 */
public class McUserChartsView implements Serializable {

    private String id;
    private String empId;
    private String userId;
    private String userName;
    private String orgName;
    private String password;
    private String status;
    private String menuType;
    private Timestamp createDate;
    private String updateUserid;
    private String appId;
    private String userComment;
    private String validStartdate;
    private String validEnddate;
    private String isRemember;
    /**
     * 请求成功次数
     */
    private int requestSuccessCount;
    /**
     * 请求失败次数
     */
    private int requestFailedCount;

    /**
     * 请求总次数
     */
    private int requestTotalCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getValidStartdate() {
        return validStartdate;
    }

    public void setValidStartdate(String validStartdate) {
        this.validStartdate = validStartdate;
    }

    public String getValidEnddate() {
        return validEnddate;
    }

    public void setValidEnddate(String validEnddate) {
        this.validEnddate = validEnddate;
    }

    public String getIsRemember() {
        return isRemember;
    }

    public void setIsRemember(String isRemember) {
        this.isRemember = isRemember;
    }

    public int getRequestSuccessCount() {
        return requestSuccessCount;
    }

    public void setRequestSuccessCount(int requestSuccessCount) {
        this.requestSuccessCount = requestSuccessCount;
    }

    public int getRequestFailedCount() {
        return requestFailedCount;
    }

    public void setRequestFailedCount(int requestFailedCount) {
        this.requestFailedCount = requestFailedCount;
    }

    public int getRequestTotalCount() {
        return requestSuccessCount + requestFailedCount;
    }

    public void setRequestTotalCount(int requestTotalCount) {
        this.requestTotalCount = requestTotalCount;
    }
}
