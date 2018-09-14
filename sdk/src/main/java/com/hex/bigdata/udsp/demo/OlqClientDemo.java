package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.OlqClient;
import com.hex.bigdata.udsp.common.constant.ConsumerEntity;
import com.hex.bigdata.udsp.common.constant.ConsumerType;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.model.request.OlqRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 联机查询调用示例
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
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        OlqClient client = ConsumerClientFactory.createCustomClient(OlqClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        OlqClient client = ConsumerClientFactory.createCustomClient(OlqClient.class);

        //创建请求实体
        OlqRequest request = new OlqRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("core02");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10097");
        //基础参数设置-设置调用start接口
        request.setEntity(ConsumerEntity.START.getValue());
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        request.setType(ConsumerType.SYNC.getValue());
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("admin");
        request.setToken("000000");

        //设置业务参数-查询SQL
        request.setSql("select * from cmdata.c01_cd_acct limit 1");

        //调用并获取返回结果
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
     * 联机查询异步start
     */
    private void asyncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        OlqClient client = ConsumerClientFactory.createCustomClient(OlqClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        OlqClient client = ConsumerClientFactory.createCustomClient(OlqClient.class);

        //创建请求实体
        OlqRequest request = new OlqRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("core02");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10097");
        //基础参数设置-设置调用start接口
        request.setEntity(ConsumerEntity.START.getValue());
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        request.setType(ConsumerType.ASYNC.getValue());

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("test");
        request.setToken("000000");
        //设置业务参数-查询SQL
        request.setSql("select * from cmdata.c01_cd_acct limit 1");

        //调用并获取返回结果
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
     * 联机查询异步status
     */
    private void asyncStatus() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http//consume";
//        OlqClient client = ConsumerClientFactory.createCustomClient(OlqClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        OlqClient client = ConsumerClientFactory.createCustomClient(OlqClient.class);
        //创建请求实体
        StatusRequest request = new StatusRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("smart01");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10097");
        //基础参数设置-设置调用status接口
        request.setEntity(ConsumerEntity.STATUS.getValue());
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        request.setType(ConsumerType.ASYNC.getValue());

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("test");
        request.setToken("000000");

        //设置业务参数-消费id
        request.setConsumeId("a7f85c82a1c290c9316b7fa07a7a627b");

        //调用并获取返回结果
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
        OlqClientDemo demo = new OlqClientDemo();
        demo.asyncStart();
        demo.asyncStatus();
        demo.syncStart();
    }
}
