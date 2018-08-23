package com.hex.bigdata.udsp.rts.executor.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-2-20.
 */
public class KafkaConsumerDatasource extends Datasource {

    public KafkaConsumerDatasource(List<Property> propertyList) {
        super(propertyList);
    }

    public KafkaConsumerDatasource(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public int getThreadNum() {
        String value = getProperty("thread.num").getValue();
        if (StringUtils.isBlank(value))
            value = "1";
        return Integer.valueOf(value);
    }

    public String getZookeeperConnect() {
        return getProperty("zookeeper.connect").getValue();
    }

    public String getMetadataBrokerList() {
        return getProperty("metadata.broker.list").getValue();
    }

    public String getZookeeperSessionTimeoutMs() {
        return getProperty("zookeeper.session.timeout.ms").getValue();
    }

    public String getZookeeperConnectionTimeoutMs() {
        return getProperty("zookeeper.connection.timeout.ms").getValue();
    }

    public String getZookeeperSyncTimeMs() {
        return getProperty("zookeeper.sync.time.ms").getValue();
    }

    public String getConsumerTimeoutMs() {
        return getProperty("consumer.timeout.ms").getValue();
    }

    public String getAutoCommitEnable() {
        return getProperty("auto.commit.enable").getValue();
    }

    public String getAutoCommitIntervalMs() {
        return getProperty("auto.commit.interval.ms").getValue();
    }

    public String getAutoOffsetReset() {
        return getProperty("auto.offset.reset").getValue();
    }

    public String getRebalanceMaxRetries() {
        return getProperty("rebalance.max.retries").getValue();
    }

    public String getRebalanceBackoffMs() {
        return getProperty("rebalance.backoff.ms").getValue();
    }

    public String getGroupId() {
        String value = getProperty("group.id").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("group.id不能为空");
        return value;
    }
}
