package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.OlqClient;
import com.hex.bigdata.udsp.client.impl.SqlClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.OlqRequest;
import com.hex.bigdata.udsp.model.request.SqlRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;

/**
 * SQL客户端查询DEMO
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/20
 * TIME:20:03
 */
public class SqlClientDemo {
    public static void main(String[] args) {
        SqlClientDemo demo = new SqlClientDemo();

        //SQL客户端查询异步start
        //demo.asyncStartTest();
        //SQL客户端查询异步status
        //demo.syncStatustTest();
        //SQL客户端查询同步start
        demo.syncStartTest();

    }

    /**
     * SQL客户端查询异步start
     */
    private void asyncStartTest() {
        //udsp请求url
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        SqlClient sqlClient = ConsumerClientFactory.createCustomClient(SqlClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //SqlClient sqlClient=ConsumerClientFactory.createOlqDefaultClient();
        //创建请求实体
        SqlRequest sqlRequest = new SqlRequest();
        //基础参数设置-设置调用服务的名称
        sqlRequest.setServiceName("core02");
        //基础参数设置-上层应用系统使用者工号
        sqlRequest.setAppUser("10097");
        //基础参数设置-设置调用start接口
        sqlRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        sqlRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        sqlRequest.setUdspUser("test");
        sqlRequest.setToken("000000");
        //设置业务参数-查询SQL
        sqlRequest.setSql("select * from cmdata.c01_cd_acct limit 1");
        //调用并获取返回结果
        AsyncPackResponse asyncPackResponse = sqlClient.asyncStart(sqlRequest);
    }

    /**
     * SQL客户端查询同步start
     */
    private void syncStartTest() {
        //udsp请求url
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        SqlClient sqlClient = ConsumerClientFactory.createCustomClient(SqlClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //SqlClient sqlClient=ConsumerClientFactory.createOlqDefaultClient();
        //创建请求实体
        SqlRequest sqlRequest = new SqlRequest();
        //基础参数设置-设置调用服务的名称
        sqlRequest.setServiceName("core02");
        //基础参数设置-上层应用系统使用者工号
        sqlRequest.setAppUser("10097");
        //基础参数设置-设置调用start接口
        sqlRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        sqlRequest.setType(SdkConstant.CONSUMER_TYPE_SYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        sqlRequest.setUdspUser("test");
        sqlRequest.setToken("000000");

        //设置业务参数-查询SQL
        sqlRequest.setSql("select * from cmdata.c01_cd_acct limit 1");
        //调用并获取返回结果
        SyncPackResponse syncPackResponse = sqlClient.syncStart(sqlRequest);
    }

    /**
     * SQL客户端查询异步status
     */
    private void syncStatustTest() {
        //udsp请求url
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        SqlClient sqlClient = ConsumerClientFactory.createCustomClient(SqlClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //SqlClient sqlClient=ConsumerClientFactory.createOlqDefaultClient();
        //创建请求实体
        StatusRequest statusRequest = new StatusRequest();
        //基础参数设置-设置调用服务的名称
        statusRequest.setServiceName("smart01");
        //基础参数设置-上层应用系统使用者工号
        statusRequest.setAppUser("10097");
        //基础参数设置-设置调用status接口
        statusRequest.setEntity(SdkConstant.CONSUMER_ENTITY_STATUS);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        statusRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        statusRequest.setUdspUser("test");
        statusRequest.setToken("000000");

        //设置业务参数-消费id
        statusRequest.setConsumeId("a7f85c82a1c290c9316b7fa07a7a627");
        //调用并获取返回结果
        StatusPackResponse statusPackResponse = sqlClient.asyncStatus(statusRequest);
    }
}
