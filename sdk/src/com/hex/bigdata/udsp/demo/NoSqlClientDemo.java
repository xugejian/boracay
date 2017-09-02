package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.NoSqlClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.Page;
import com.hex.bigdata.udsp.model.request.NoSqlRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.origin.SyncResponse;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 交互查询/联机查询应用 请求DEMO
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/20
 * TIME:9:01
 */
public class NoSqlClientDemo {

    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(NoSqlClientDemo.class);

    public static void main(String[] args) {
        NoSqlClientDemo noSqlClientDemo = new NoSqlClientDemo();
        //noSqlClientDemo.iqAsyncStartTest();//交互查询-异步start接口示例
        //noSqlClientDemo.iqAsyncStatusTest();//交互查询-异步status接口示例
        noSqlClientDemo.iqSyncStartTest();//交互查询-同步start接口示例
    }

    /**
     * 交互查询/联机查询应用-异步start接口示例
     */
    public void iqAsyncStartTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        NoSqlClient noSqlClient = ConsumerClientFactory.createCustomClient(NoSqlClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //NoSqlClient noSqlClient=ConsumerClientFactory.createDefaultClient(NoSqlClient.class);
        //创建请求实体
        NoSqlRequest noSqlRequest = new NoSqlRequest();
        //基础参数设置-设置调用服务的名称
        noSqlRequest.setServiceName("soa_jyls_app");
        //基础参数设置-上层应用系统使用者工号
        noSqlRequest.setAppUser("100940");
        //基础参数设置-设置调用start接口
        noSqlRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        noSqlRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        noSqlRequest.setUdspUser("test");
        noSqlRequest.setToken("000000");

        //设置业务参数-查询参数设置
        Map<String, String> data = new HashMap<>();
        data.put("client_no", "1113829408");
        noSqlRequest.setData(data);

        //设置业务参数-分页参数设置
        Page page = new Page();
        page.setPageSize(300);
        page.setPageIndex(1);
        noSqlRequest.setPage(page);

        //调用并获取结果
        AsyncPackResponse asyncPackResponse = noSqlClient.asyncStart(noSqlRequest);
    }

    /**
     * 交互查询/联机查询应用-异步status接口示例
     */
    public void iqAsyncStatusTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        NoSqlClient noSqlClient = ConsumerClientFactory.createCustomClient(NoSqlClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //NoSqlClient noSqlClient=ConsumerClientFactory.createDefaultClient(NoSqlClient.class);
        //创建请求实体
        StatusRequest statusRequest = new StatusRequest();
        //基础参数设置-设置调用服务的名称
        statusRequest.setServiceName("soa_jyls_app");
        //基础参数设置-上层应用系统使用者工号
        statusRequest.setAppUser("10940");
        //基础参数设置-设置调用status接口，查看任务状态
        statusRequest.setEntity(SdkConstant.CONSUMER_ENTITY_STATUS);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        statusRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        statusRequest.setUdspUser("test");
        statusRequest.setToken("000000");

        //设置业务参数-消费id
        statusRequest.setConsumeId("955a0c14a55ebe727ff45f20939dfc97");
        //调用并获取结果
        StatusPackResponse statusResponse = noSqlClient.asyncStatus(statusRequest);
    }

    /**
     * 交互查询/联机查询应用-同步start接口示例
     */
    public void iqSyncStartTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        NoSqlClient noSqlClient = ConsumerClientFactory.createCustomClient(NoSqlClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //NoSqlClient noSqlClient=ConsumerClientFactory.createDefaultClient(NoSqlClient.class);
        //创建请求实体
        NoSqlRequest noSqlRequest = new NoSqlRequest();

        //基础参数
        noSqlRequest.setServiceName("message");
        //基础参数设置-上层应用系统使用者工号
        noSqlRequest.setAppUser("10940");
        //基础参数设置-设置调用start接口
        noSqlRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        noSqlRequest.setType(SdkConstant.CONSUMER_TYPE_SYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        noSqlRequest.setUdspUser("test");
        noSqlRequest.setToken("000000");

        //设置业务参数-查询参数设置
        Map<String, String> data = new HashMap<>();
        data.put("client_no", "1113829408");
        noSqlRequest.setData(data);

        //设置业务参数-分页参数设置
        Page page = new Page();
        page.setPageSize(300);
        page.setPageIndex(1);
        noSqlRequest.setPage(page);
        //调用并获取结果
        SyncPackResponse syncPackResponse = noSqlClient.syncStart(noSqlRequest);
    }

}
