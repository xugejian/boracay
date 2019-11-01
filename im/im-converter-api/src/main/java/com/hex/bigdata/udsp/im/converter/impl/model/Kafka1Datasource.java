package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2018-11-28.
 */
public class Kafka1Datasource extends Datasource {
    public Kafka1Datasource(List<Property> properties) {
        super(properties);
    }

    public Kafka1Datasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public Kafka1Datasource(Datasource datasource) {
        super(datasource);
    }

    // Kafka集群的IP和端口地址，多个地址用逗号分隔
    public String gainBootstrapServers() {
        String value = gainProperty ("bootstrap.servers").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("bootstrap.servers不能为空");
        }
        return value;
    }

    // Key的反序列化类
    public String gainKeyDeserializer() {
        String value = gainProperty ("key.deserializer").getValue ();
        if (StringUtils.isBlank (value)) {
            value = "org.apache.kafka.common.serialization.StringDeserializer";
        }
        return value;
    }

    // Value的反序列化类
    public String gainValueDeserializer() {
        String value = gainProperty ("value.deserializer").getValue ();
        if (org.apache.commons.lang.StringUtils.isBlank (value)) {
            value = "org.apache.kafka.common.serialization.StringDeserializer";
        }
        return value;
    }

    // 如果为true消费者会定期在后台提交offset偏移量
    public String gainEnableAutoCommit() {
        return gainProperty ("enable.auto.commit").getValue ();
    }

    // 如果enable.auto.commit=true，消费者向kafka自动提交offsets的频率
    public String gainAutoCommitIntervalMs() {
        return gainProperty ("auto.commit.interval.ms").getValue ();
    }

    // 安全协议
    public String gainSecurityProtocol() {
        return gainProperty ("security.protocol").getValue ();
    }

    // Kerberos服务名
    public String gainSaslKerberosServiceName() {
        return gainProperty ("sasl.kerberos.service.name").getValue ();
    }

    // 在kafka中没有初始的offset或者当前的offset不存在将返回的offset值，latest、earliest
    public String gainAutoOffsetReset() {
        return gainProperty ("auto.offset.reset").getValue ();
    }

    // 在一次调用poll()中返回的最大记录数
    public String gainMaxPollRecords() {
        return gainProperty ("max.poll.records").getValue ();
    }
}
