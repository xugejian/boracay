package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.OlqClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.OlqRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 联机查询调用示例
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:14:31
 */
@Deprecated
public class OlqClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(OlqClientDemo.class);

    /**
     * 联机查询同步start
     */
    private void syncStart() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        OlqClient olqClient = ConsumerClientFactory.createCustomClient(OlqClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //OlqClient olqClient=ConsumerClientFactory.createCustomClient(OlqClient.class);
        //创建请求实体
        OlqRequest olqRequest = new OlqRequest();
        //基础参数设置-设置调用服务的名称
        olqRequest.setServiceName("core02");
        //基础参数设置-上层应用系统使用者工号
        olqRequest.setAppUser("10097");
        //基础参数设置-设置调用start接口
        olqRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        olqRequest.setType(SdkConstant.CONSUMER_TYPE_SYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        olqRequest.setUdspUser("admin");
        olqRequest.setToken("000000");

        //设置业务参数-查询SQL
        olqRequest.setSql("select * from cmdata.c01_cd_acct limit 1");

        //调用并获取返回结果
        SyncPackResponse syncPackResponse = olqClient.syncStart(olqRequest);
    }

    /**
     * 联机查询异步start
     */
    private void asyncStart() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        OlqClient olqClient = ConsumerClientFactory.createCustomClient(OlqClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //OlqClient olqClient=ConsumerClientFactory.createCustomClient(OlqClient.class);
        //创建请求实体
        OlqRequest olqRequest = new OlqRequest();
        //基础参数设置-设置调用服务的名称
        olqRequest.setServiceName("core02");
        //基础参数设置-上层应用系统使用者工号
        olqRequest.setAppUser("10097");
        //基础参数设置-设置调用start接口
        olqRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        olqRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        olqRequest.setUdspUser("test");
        olqRequest.setToken("000000");
        //设置业务参数-查询SQL
        olqRequest.setSql("select * from cmdata.c01_cd_acct limit 1");
        //调用并获取返回结果
        AsyncPackResponse asyncPackResponse = olqClient.asyncStart(olqRequest);
    }

    /**
     * 联机查询异步status
     */
    private void asyncStatus() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        //创建自定义客户端
        OlqClient olqClient = ConsumerClientFactory.createCustomClient(OlqClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //OlqClient olqClient=ConsumerClientFactory.createCustomClient(OlqClient.class);
        //创建请求实体
        StatusRequest statusRequest = new StatusRequest();
        //基础参数设置-设置调用服务的名称
        statusRequest.setServiceName("smart01");
        //基础参数设置-上层应用系统使用者工号
        statusRequest.setAppUser("10097");
        //基础参数设置-设置调用status接口
        statusRequest.setEntity(SdkConstant.CONSUMER_ENTITY_STATUS);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        statusRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        statusRequest.setUdspUser("test");
        statusRequest.setToken("000000");

        //设置业务参数-消费id
        statusRequest.setConsumeId("a7f85c82a1c290c9316b7fa07a7a627b");
        //调用并获取返回结果
        StatusPackResponse statusPackResponse = olqClient.asyncStatus(statusRequest);
    }

    public static void main(String[] args) {
        OlqClientDemo demo = new OlqClientDemo();
        demo.asyncStart();
        demo.asyncStatus();
        demo.syncStart();
    }
}
