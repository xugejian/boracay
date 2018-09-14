package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.MmClient;
import com.hex.bigdata.udsp.common.constant.ConsumerEntity;
import com.hex.bigdata.udsp.common.constant.ConsumerType;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.model.request.MmRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型管理调用示例
 */
public class MmClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(FileClientDemo.class);

    /**
     * 模型管理-同步start接口示例
     */
    public void syncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        MmClient client = ConsumerClientFactory.createCustomClient(MmClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        MmClient client = ConsumerClientFactory.createCustomClient(MmClient.class);

        //创建请求实体
        MmRequest request = new MmRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("mmceshi002");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10071");
        //基础参数设置-设置调用start接口
        request.setEntity(ConsumerEntity.START.getValue());
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        request.setType(ConsumerType.SYNC.getValue());
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("test");
        request.setToken("002158");

        //设置业务参数-查询参数
        Map<String, String> data = new HashMap<>();
        data.put("date", "20170503");
        data.put("age", "10");
        data.put("modelId", "10");
        data.put("pkId", "10");
        request.setData(data);

        //发起调用
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

    /**
     * 模型管理-异步start接口示例
     */
    public void asyncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        MmClient client = ConsumerClientFactory.createCustomClient(MmClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        MmClient client = ConsumerClientFactory.createCustomClient(MmClient.class);

        //创建请求实体
        MmRequest request = new MmRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("mmceshi002");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10071");
        //基础参数设置-设置调用start接口
        request.setEntity(ConsumerEntity.START.getValue());
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        request.setType(ConsumerType.ASYNC.getValue());
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("test");
        request.setToken("002158");

        //设置业务参数-查询参数
        Map<String, String> data = new HashMap<>();
        data.put("date", "20170503");
        data.put("age", "10");
        data.put("modelId", "10");
        data.put("pkId", "10");
        request.setData(data);

        //发起调用
        AsyncPackResponse response = client.asyncStart(request);

        // 拆包响应对象
        if (response == null) {
            logger.error("客户端异常");
        } else {
            if (StatusCode.SUCCESS == response.getStatusCode()) {
                // 耗时
                logger.debug("耗时：" + response.getConsumeTime());
                // 消费ID
                logger.debug("消费ID：" + response.getConsumeId());
                // 生成文件的FTP路径
                logger.debug("生成文件的FTP路径：" + response.getResponseContent());
                /**
                 * 成功说明异步任务已经调起
                 */
                // 可以继续循环执行查看状态的操作
            } else {
                logger.error("状态：" + response.getStatus());
                logger.error("状态码：" + response.getStatusCode());
                logger.error("错误码：" + response.getErrorCode());
                logger.error("错误信息：" + response.getMessage());
            }
        }
    }

    /**
     * 模型管理-异步status接口示例
     */
    public void asyncStatus() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        MmClient client = ConsumerClientFactory.createCustomClient(MmClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        MmClient client = ConsumerClientFactory.createCustomClient(MmClient.class);

        //创建请求实体
        StatusRequest request = new StatusRequest();

        //基础参数设置-设置调用服务的名称
        request.setServiceName("mmceshi002");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10071");
        //基础参数设置-设置调用status接口
        request.setEntity(ConsumerEntity.STATUS.getValue());
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        request.setType(ConsumerType.ASYNC.getValue());
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("test");
        request.setToken("002158");

        //设置业务参数-设置消费id
        request.setConsumeId("5119e6f55f588268c246a6b8f45f8701");

        //发起调用
        StatusPackResponse response = client.asyncStatus(request);

        // 拆包响应对象
        if (response == null) {
            logger.error("客户端异常");
        } else {
            if (StatusCode.SUCCESS == response.getStatusCode()) {
                logger.info("异步消费完成");
                // 可以继续执行FTP下载文件的操作
            }
            if (StatusCode.RUNNING == response.getStatusCode()) {
                logger.info("异步消费正在执行");
                // 可以继续执行查看状态的操作
            } else {
                logger.error("状态：" + response.getStatus());
                logger.error("状态码：" + response.getStatusCode());
                logger.error("错误码：" + response.getErrorCode());
                logger.error("错误信息：" + response.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        MmClientDemo demo = new MmClientDemo();
        demo.syncStart();
        demo.asyncStart();
        demo.asyncStatus();
    }
}
