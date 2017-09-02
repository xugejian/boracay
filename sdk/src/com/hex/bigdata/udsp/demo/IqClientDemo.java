package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.IqClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.Page;
import com.hex.bigdata.udsp.model.request.IqRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 交互查询调用示例
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:19:52
 */
@Deprecated
public class IqClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(IqClientDemo.class);

    public static void main(String[] args) {
        IqClientDemo iqClientDemo = new IqClientDemo();
        iqClientDemo.asyncStatusTest();
        //iqClientDemo.syncStartTest();
        //iqClientDemo.asyncStartTest();
    }

    /**
     * 交互查询-异步start接口示例
     */
    public void asyncStartTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        IqClient iqClient = ConsumerClientFactory.createCustomClient(IqClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //IqClient iqClient=ConsumerClientFactory.createDefaultClient(IqClient.class);
        //创建请求实体
        IqRequest iqRequest = new IqRequest();
        //基础参数设置-设置调用服务的名称
        iqRequest.setServiceName("soa_jyls_app");
        //基础参数设置-上层应用系统使用者工号
        iqRequest.setAppUser("10940");
        //基础参数设置-设置调用start接口
        iqRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        iqRequest.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        iqRequest.setUdspUser("CRM");
        iqRequest.setToken("000000");

        //设置业务参数-查询参数设置
        Map<String, String> data = new HashMap<>();
        data.put("acct_no", "130000365600030");
        data.put("start_time", "2016-12-31");
        data.put("end_time", "2017-12-31");
        iqRequest.setData(data);

        //设置业务参数-分页参数设置
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageSize(20);
        iqRequest.setPage(page);

        //发起调用
        AsyncPackResponse asyncPackResponse = iqClient.asyncStart(iqRequest);
    }

    /**
     * 交互查询-异步status接口示例
     */
    public void asyncStatusTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        IqClient iqClient = ConsumerClientFactory.createCustomClient(IqClient.class,url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //IqClient iqClient=ConsumerClientFactory.createDefaultClient(IqClient.class);
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
        statusRequest.setUdspUser("CRM");
        statusRequest.setToken("000000");

        //业务参数
        statusRequest.setConsumeId("955a0c14a55ebe727ff45f20939dfc97");
        //发起调用
        StatusPackResponse statusResponse = iqClient.asyncStatus(statusRequest);
    }

    /**
     * 交互查询-同步start接口示例
     */
    public void syncStartTest() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        IqClient iqClient = ConsumerClientFactory.createCustomClient(IqClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //IqClient iqClient=ConsumerClientFactory.createDefaultClient(IqClient.class);

        //创建请求实体
        IqRequest iqRequest = new IqRequest();
        //基础参数设置-设置调用服务的名称
        iqRequest.setServiceName("soa_jyls_app1");
        //基础参数设置-上层应用系统使用者工号
        iqRequest.setAppUser("10940");
        //基础参数设置-设置调用start接口
        iqRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        iqRequest.setType(SdkConstant.CONSUMER_TYPE_SYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        iqRequest.setUdspUser("CRM");
        iqRequest.setToken("000000");

        //设置业务参数-查询参数设置
        Map<String, String> data = new HashMap<>();
        data.put("acct_no", "130000365600030");
        data.put("start_time", "2016-12-31");
        data.put("end_time", "2017-12-31");
        iqRequest.setData(data);

        //设置业务参数-分页参数设置
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageSize(20);
        iqRequest.setPage(page);

        //发起动用
        SyncPackResponse syncPackResponse = iqClient.syncStart(iqRequest);
    }


}
