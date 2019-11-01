package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class KafkaDatasource extends Datasource {
    public KafkaDatasource(List<Property> properties) {
        super(properties);
    }

    public KafkaDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public KafkaDatasource(Datasource datasource) {
        super(datasource);
    }

    // 线程数
    public int gainThreadNum() {
        String value = gainProperty("thread.num").getValue();
        if (StringUtils.isBlank(value)) {
            value = "1";
        }
        return Integer.valueOf(value);
    }

    // zookeeper集群的IP和端口地址，多个地址用逗号分隔
    public String gainZookeeperConnect() {
        return gainProperty("zookeeper.connect").getValue();
    }

    // Kafka集群的IP和端口地址，多个地址用逗号分隔
    public String gainMetadataBrokerList() {
        return gainProperty("metadata.broker.list").getValue();
    }

    // 连接zookeeper的session超时时间
    public String gainZookeeperSessionTimeoutMs() {
        return gainProperty("zookeeper.session.timeout.ms").getValue();
    }

    // 客户端连接zookeeper的最大超时时间
    public String gainZookeeperConnectionTimeoutMs() {
        return gainProperty("zookeeper.connection.timeout.ms").getValue();
    }

    // zookeeper同步时间
    public String gainZookeeperSyncTimeMs() {
        return gainProperty("zookeeper.sync.time.ms").getValue();
    }

    // 如果true,consumer定期地往zookeeper写入每个分区的offset
    public String gainAutoCommitEnable() {
        return gainProperty("auto.commit.enable").getValue();
    }

    // 消费者向zookeeper发送offset的时间
    public String gainAutoCommitIntervalMs() {
        return gainProperty("auto.commit.interval.ms").getValue();
    }

    // offset初始化或者达到上线时的处理方式
    public String gainAutoOffsetReset() {
        return gainProperty("auto.offset.reset").getValue();
    }

    // rebalance时的最大尝试次数
    public String gainRebalanceMaxRetries() {
        return gainProperty("rebalance.max.retries").getValue();
    }

    // 平衡补偿重试间隔时间
    public String gainRebalanceBackoffMs() {
        return gainProperty("rebalance.backoff.ms").getValue();
    }

}
