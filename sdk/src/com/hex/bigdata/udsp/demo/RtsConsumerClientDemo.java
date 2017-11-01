package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.RtsConsumerClient;
import com.hex.bigdata.udsp.client.impl.RtsProducerClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.RtsConsumerRequest;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 实时流消费者服务DEMO
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:19:53
 */
public class RtsConsumerClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsConsumerClientDemo.class);

    public void syncStart() {
        //udsp请求连接
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        //创建自定义客户端
        RtsConsumerClient rtsConsumerClient = ConsumerClientFactory.createCustomClient(RtsConsumerClient.class, url);
        //创建默认客户端,根据sdk.config.properties配置文件获取地址
        //RtsConsumerClient rtsConsumerClient = ConsumerClientFactory.createCustomClient(RtsConsumerClient.class);
        //创建请求实体
        RtsConsumerRequest rtsConsumerRequest = new RtsConsumerRequest();
        //基础参数设置-设置调用服务的名称
        rtsConsumerRequest.setServiceName("rts_test_consumer2");
        //基础参数设置-上层应用系统使用者工号
        rtsConsumerRequest.setAppUser("10071");
        //基础参数设置-设置调用start接口
        rtsConsumerRequest.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        rtsConsumerRequest.setType(SdkConstant.CONSUMER_TYPE_SYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        rtsConsumerRequest.setUdspUser("admin");
        rtsConsumerRequest.setToken("000000");
        //发起请求
        SyncPackResponse syncPackResponse = rtsConsumerClient.syncStart(rtsConsumerRequest);
    }

    public static void main(String[] args) {
        RtsConsumerClientDemo demo = new RtsConsumerClientDemo();
        demo.syncStart();
    }
}
