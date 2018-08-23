package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.RtsConsumerClient;
import com.hex.bigdata.udsp.constant.ConsumerEntity;
import com.hex.bigdata.udsp.constant.ConsumerType;
import com.hex.bigdata.udsp.constant.StatusCode;
import com.hex.bigdata.udsp.model.request.RtsConsumerRequest;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 实时流消费者调用示例
 */
public class RtsConsumerClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsConsumerClientDemo.class);

    public void syncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        RtsConsumerClient client = ConsumerClientFactory.createCustomClient(RtsConsumerClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        RtsConsumerClient client = ConsumerClientFactory.createCustomClient(RtsConsumerClient.class);

        //创建请求实体
        RtsConsumerRequest request = new RtsConsumerRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("rts_test_consumer2");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10071");
        //基础参数设置-设置调用start接口
        request.setEntity(ConsumerEntity.START.getValue());
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        request.setType(ConsumerType.SYNC.getValue());

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("admin");
        request.setToken("000000");

        // 设置超时时间
        request.setTimeout(1000);

        //发起请求
        SyncPackResponse response = client.syncStart(request);

        // 拆包响应对象
        if (response == null) {
            logger.error("客户端异常");
        } else {
            if (StatusCode.SUCCESS == response.getStatusCode()) {
                // 耗时
                logger.debug("耗时：" + response.getConsumeTime());
                // 消费ID
                logger.debug("消费ID：" + response.getConsumeId());
                // 字段信息
                LinkedHashMap<String, String> returnColumns = response.getReturnColumns();
                for (Map.Entry<String, String> entry : returnColumns.entrySet()) {
                    logger.debug("名称：" + entry.getKey() + "，类型：" + entry.getValue());
                }
                // 数据信息
                List<Map<String, String>> records = response.getRecords();
                for (Map<String, String> record : records) {
                    for (Map.Entry<String, String> entry : record.entrySet()) {
                        logger.debug("名称：" + entry.getKey() + "，值：" + entry.getValue());
                    }
                }
            } else {
                logger.error("状态：" + response.getStatus());
                logger.error("状态码：" + response.getStatusCode());
                logger.error("错误码：" + response.getErrorCode());
                logger.error("错误信息：" + response.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        RtsConsumerClientDemo demo = new RtsConsumerClientDemo();
        demo.syncStart();
    }
}
