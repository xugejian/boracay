package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.KafkaDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.KafkaModel;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by JunjieM on 2017-9-15.
 */
public class KafkaUtil {

    public static List<KafkaStream<byte[], byte[]>> outputData(KafkaModel model) {
        Datasource datasource = model.getSourceDatasource();
        String consumerTimeoutMs = model.getConsumerTimeoutMs();
        String topic = model.getTopic();
        Map<String, Property> propertyMap = datasource.getPropertyMap();
        propertyMap.put("consumer.timeout.ms", new Property("consumer.timeout.ms", consumerTimeoutMs));
        KafkaDatasource kafkaDatasource = new KafkaDatasource(propertyMap);
        ConsumerConnector consumer = getConsumerConnector(kafkaDatasource);
        int threadNum = kafkaDatasource.getThreadNum();
        return receive(consumer, topic, threadNum);
    }

    public static ConsumerConnector getConsumerConnector(KafkaDatasource datasource) {
        ConsumerConfig config = getCnsumerConfig(datasource);
        return Consumer.createJavaConsumerConnector(config);
    }

    public static ConsumerConfig getCnsumerConfig(KafkaDatasource datasource) {
        Properties props = new Properties();
        if (StringUtils.isNotBlank(datasource.getZookeeperConnect()))
            props.put("zookeeper.connect", datasource.getZookeeperConnect());
        if (StringUtils.isNotBlank(datasource.getMetadataBrokerList()))
            props.put("metadata.broker.list", datasource.getMetadataBrokerList());
        if (StringUtils.isNotBlank(datasource.getGroupId()))
            props.put("group.id", datasource.getGroupId());
        if (StringUtils.isNotBlank(datasource.getZookeeperSessionTimeoutMs()))
            props.put("zookeeper.session.timeout.ms",
                    datasource.getZookeeperSessionTimeoutMs());
        if (StringUtils.isNotBlank(datasource.getZookeeperConnectionTimeoutMs()))
            props.put("zookeeper.connection.timeout.ms",
                    datasource.getZookeeperConnectionTimeoutMs());
        if (StringUtils.isNotBlank(datasource.getZookeeperSyncTimeMs()))
            props.put("zookeeper.sync.time.ms", datasource.getZookeeperSyncTimeMs());
        if (StringUtils.isNotBlank(datasource.getAutoCommitIntervalMs()))
            props.put("auto.commit.interval.ms", datasource.getAutoCommitIntervalMs());
        if (StringUtils.isNotBlank(datasource.getConsumerTimeoutMs()))
            props.put("consumer.timeout.ms", datasource.getConsumerTimeoutMs());
        if (StringUtils.isNotBlank(datasource.getAutoCommitEnable()))
            props.put("auto.commit.enable", datasource.getAutoCommitEnable());
        if (StringUtils.isNotBlank(datasource.getAutoOffsetReset()))
            props.put("auto.offset.reset", datasource.getAutoOffsetReset());
        if (StringUtils.isNotBlank(datasource.getRebalanceMaxRetries()))
            props.put("rebalance.max.retries", datasource.getRebalanceMaxRetries());
        if (StringUtils.isNotBlank(datasource.getRebalanceBackoffMs()))
            props.put("rebalance.backoff.ms", datasource.getRebalanceBackoffMs());
        return new ConsumerConfig(props);
    }

    public static KafkaStream<byte[], byte[]> receive(ConsumerConnector consumer, String topic) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        // 一次从topic中获取1个KafkaStream（一个KafkaStream可产生一个迭代器）
        String topicName = topic;
        topicCountMap.put(topicName, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topicName).get(0);
        return stream;
    }

    public static Map<String, KafkaStream<byte[], byte[]>> receiveMap(ConsumerConnector consumer, String topic) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, KafkaStream<byte[], byte[]>> streamMap = new HashMap<String, KafkaStream<byte[], byte[]>>();
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        String[] topicNames = topic.split(",");
        for (String topicName : topicNames) {
            topicCountMap.put(topicName, 1);
        }
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        for (Map.Entry<String, Integer> entry : topicCountMap.entrySet()) {
            String topicName = entry.getKey();
            KafkaStream<byte[], byte[]> stream = consumerMap.get(topicName)
                    .get(0);
            streamMap.put(topicName, stream);
        }
        return streamMap;
    }

    public static List<KafkaStream<byte[], byte[]>> receive(ConsumerConnector consumer, String topic, Integer threadNum) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        // threadNum是线程数
        // 一次从topic中获取threadNum个KafkaStream（一个KafkaStream可产生一个迭代器）
        String topicName = topic;
        topicCountMap.put(topicName, threadNum);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streamList = consumerMap
                .get(topicName);
        return streamList;
    }

    public static Map<String, List<KafkaStream<byte[], byte[]>>> receiveMap(ConsumerConnector consumer, String topic, Integer threadNum) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = new HashMap<String, List<KafkaStream<byte[], byte[]>>>();
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        String[] topicNames = topic.split(",");
        for (String topicName : topicNames) {
            topicCountMap.put(topicName, threadNum);
        }
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        for (Map.Entry<String, Integer> entry : topicCountMap.entrySet()) {
            String topicName = entry.getKey();
            List<KafkaStream<byte[], byte[]>> streamList = consumerMap
                    .get(topicName);
            streamMap.put(topicName, streamList);
        }
        return streamMap;
    }
}
