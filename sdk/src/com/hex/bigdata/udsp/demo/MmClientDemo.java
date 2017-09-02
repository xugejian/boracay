package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.MmClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.MmRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型管理调用示例
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:19:52
 */
public class MmClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(FileClientDemo.class);

    public static void main(String[] args) {
        MmClientDemo mmClientDemo = new MmClientDemo();
        //mmClientDemo.asyncStartTest();
        mmClientDemo.syncStartTest();
        mmClientDemo.asyncStatusTest();
    }

    /**
     * 模型管理-同步start接口示例
     */
    public void syncStartTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        MmClient mmClient= ConsumerClientFactory.createCustomClient(MmClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //MmClient mmClient=ConsumerClientFactory.createDefaultClient(MmClient.class);
        //创建请求实体
        MmRequest mmRequest=new MmRequest();
        //基础参数设置-设置调用服务的名称
        mmRequest.setServiceName("mmceshi002");
        //基础参数设置-上层应用系统使用者工号
        mmRequest.setAppUser("10071");
        //基础参数设置-设置调用start接口
        mmRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        mmRequest.setType(SdkConstant.CONSUMER_TYPE_SYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        mmRequest.setUdspUser("test");
        mmRequest.setToken("002158");

        //设置业务参数-查询参数
        Map<String, String> data=new HashMap<>();
        data.put("date", "20170503");
        data.put("age","10");
        data.put("modelId", "10");
        data.put("pkId", "10");
        mmRequest.setData(data);
        //发起调用
        SyncPackResponse syncPackResponse = mmClient.syncStart(mmRequest);

    }

    /**
     * 模型管理-异步start接口示例
     */
    public void asyncStartTest() {
        String url="http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        MmClient mmClient= ConsumerClientFactory.createCustomClient(MmClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //MmClient mmClient=ConsumerClientFactory.createDefaultClient(MmClient.class);
        //创建请求实体
        MmRequest mmRequest=new MmRequest();
        //基础参数设置-设置调用服务的名称
        mmRequest.setServiceName("mmceshi002");
        //基础参数设置-上层应用系统使用者工号
        mmRequest.setAppUser("10071");
        //基础参数设置-设置调用start接口
        mmRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        mmRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        mmRequest.setUdspUser("test");
        mmRequest.setToken("002158");

        //设置业务参数-查询参数
        Map<String, String> data=new HashMap<>();
        data.put("date", "20170503");
        data.put("age","10");
        data.put("modelId", "10");
        data.put("pkId", "10");
        mmRequest.setData(data);
        //发起调用
        AsyncPackResponse asyncPackResponse = mmClient.asyncStart(mmRequest);
    }

    /**
     * 模型管理-异步status接口示例
     */
    public void asyncStatusTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        MmClient mmClient= ConsumerClientFactory.createCustomClient(MmClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //MmClient mmClient=MmClient.createIqDefaultClient();
        //创建请求实体
        StatusRequest statusRequest = new StatusRequest();

        //基础参数设置-设置调用服务的名称
        statusRequest.setServiceName("mmceshi002");
        //基础参数设置-上层应用系统使用者工号
        statusRequest.setAppUser("10071");
        //基础参数设置-设置调用status接口
        statusRequest.setEntity(SdkConstant.CONSUMER_ENTITY_STATUS);
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        statusRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        statusRequest.setUdspUser("test");
        statusRequest.setToken("002158");

        //设置业务参数-设置消费id
        statusRequest.setConsumeId("5119e6f55f588268c246a6b8f45f8701");
        //发起动用
        StatusPackResponse udspResponse = mmClient.asyncStatus(statusRequest);
    }



}
