package com.hex.bigdata.udsp;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.SqlClient;
import com.hex.bigdata.udsp.common.constant.ConsumerEntity;
import com.hex.bigdata.udsp.common.constant.ConsumerType;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.model.request.SqlRequest;
import com.hex.bigdata.udsp.model.response.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2018/4/12.
 */
public class ConcurrentTest {


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            SqlClientThread thread = new SqlClientThread();
            thread.start();
            Thread.sleep(10);
        }
    }
}


class SqlClientThread extends Thread {
    private static Logger logger = LogManager.getLogger(SqlClientThread.class);

    public void run() {
        SqlClient client = ConsumerClientFactory.createCustomClient(SqlClient.class);

        //创建请求实体
        SqlRequest request = new SqlRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("olq_ar_query");
        //基础参数设置-设置调用start接口
        request.setEntity(ConsumerEntity.START.getValue());
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        request.setType(ConsumerType.SYNC.getValue());

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("ERS");
        request.setToken("000000");

        //设置业务参数-查询SQL
        request.setSql("select jyjg, count(1) from edwapp.soa_jyls group by jyjg");

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
                logger.debug("------------------------------------------------------------------");
                // 字段信息
                LinkedHashMap<String, String> returnColumns = response.getReturnColumns();
                for (Map.Entry<String, String> entry : returnColumns.entrySet()) {
                    logger.debug("名称：" + entry.getKey() + "，类型：" + entry.getValue());
                }
                logger.debug("------------------------------------------------------------------");
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
}
