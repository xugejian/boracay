package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.im.converter.model.Model;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by JunjieM on 2017-9-15.
 */
public class MqModel extends Model implements Serializable {

    public MqModel() {
        // Redis使用Jackson进行序列化和反序列化时必须要有空构造函数
    }

    public MqModel(Model model) {
        super(model);
    }

    // 主题
    public String gainTopic() {
        String value = gainProperty("topic").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("topic不能为空");
        }
        return value;
    }

    // 消费超时时间（毫秒）
    public String gainConsumerTimeoutMs() {
        String value = gainProperty("consumer.timeout.ms").getValue();
        if (StringUtils.isBlank(value)) {
            value = "1000";
        }
        return value;
    }

    // 消费计划任务表达式
    public String gainConsumerCronExpression() {
        String value = gainProperty("consumer.cron.expression").getValue();
        if (StringUtils.isBlank(value)) {
            value = "0/2 * * * * ?";
        }
        return value;
    }
}
