package com.hex.bigdata.udsp.mm.dto;

import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import com.hex.bigdata.udsp.mm.model.MmAppReturnParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/3
 * TIME:10:00
 */
public class MmFullAppInfoView implements Serializable {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 业务参数列表
     */
    private List<MmAppExecuteParam> executeParams;

    /**
     * 返回参数列表
     */
    private List<MmAppReturnParam> returnParams;

    /**
     * 厂商名称
     */
    private String contractorName;

    /**
     * 远程调用连接
     */
    private String httpUrl;


    /**
     * 模型调用类型，1：同步、2：异步（多个用逗号分隔）
     */
    private String modelType;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 最大返回数
     */
    private long maxNum;


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

    public List<MmAppExecuteParam> getExecuteParams() {
        return executeParams;
    }

    public void setExecuteParams(List<MmAppExecuteParam> executeParams) {
        this.executeParams = executeParams;
    }

    public List<MmAppReturnParam> getReturnParams() {
        return returnParams;
    }

    public void setReturnParams(List<MmAppReturnParam> returnParams) {
        this.returnParams = returnParams;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(long maxNum) {
        this.maxNum = maxNum;
    }
}
