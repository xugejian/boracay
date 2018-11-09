package com.hex.bigdata.udsp.rts.executor.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.rts.executor.Executor;
import com.hex.bigdata.udsp.rts.executor.impl.model.KafkaConsumerDatasource;
import com.hex.bigdata.udsp.rts.executor.impl.model.KafkaProducerDatasource;
import com.hex.bigdata.udsp.rts.executor.model.*;
import kafka.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by junjiem on 2017-2-20.
 */
//@Component("com.hex.bigdata.udsp.rts.executor.impl.KafkaExecutor")
public class KafkaExecutor implements Executor {

    private static Logger logger = LogManager.getLogger(KafkaExecutor.class);

    private ProducerConfig getProducerConfig(KafkaProducerDatasource datasource) {
        Properties props = new Properties();
        if (StringUtils.isNotBlank(datasource.getMetadataBrokerList()))
            props.put("metadata.broker.list", datasource.getMetadataBrokerList());
        if (StringUtils.isNotBlank(datasource.getSerializerClass()))
            props.put("serializer.class", datasource.getSerializerClass());
        if (StringUtils.isNotBlank(datasource.getKeySerializerClass()))
            props.put("key.serializer.class", datasource.getKeySerializerClass());
        if (StringUtils.isNotBlank(datasource.getRequestRequiredAcks()))
            props.put("request.required.acks", datasource.getRequestRequiredAcks());
        return new ProducerConfig(props);
    }

    //-------------------------------------------ProducerApplication---------------------------------------------
    public ProducerResponse push(ProducerRequest producerRequest) {
        long bef = System.currentTimeMillis();

        ProducerResponse producerResponse = new ProducerResponse();
        producerResponse.setProducerRequest(producerRequest);

        Metadata producerMetadata = producerRequest.getApplication().getMetadata();
        Datasource producerDatasource = producerMetadata.getDatasource();
        KafkaProducerDatasource datasource = new KafkaProducerDatasource(producerDatasource.getProperties());
        Producer<String, String> producer = new Producer<>(getProducerConfig(datasource));
        String topic = producerMetadata.getTopic();
        List<Map<String, String>> dataMap = producerRequest.getMessageDatas();
        List<String> messageList = new ArrayList<>();
        for (Map<String, String> data : dataMap) {
            messageList.add(JSONUtil.parseObj2JSON(data));
        }
        try {
            send(producer, topic, messageList);
            producerResponse.setStatus(Status.SUCCESS);
            producerResponse.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            producerResponse.setStatus(Status.DEFEAT);
            producerResponse.setStatusCode(StatusCode.DEFEAT);
            producerResponse.setMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        producerResponse.setConsumeTime(consumeTime);

        return producerResponse;
    }

    private void send(Producer<String, String> producer, String topic, String message) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        if (message == null) {
            throw new RuntimeException("send message is null!");
        }
        // 如果具有多个partitions,请使用new KeyedMessage(String topicName, K key, V value).
        KeyedMessage<String, String> km = new KeyedMessage<>(topic, message);
        producer.send(km);
    }

    private void send(Producer<String, String> producer, String topic, List<String> messages) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        if (messages == null || messages.isEmpty()) {
            throw new RuntimeException("send message can not be empty!");
        }
        List<KeyedMessage<String, String>> kms = new ArrayList<>();
        for (String message : messages) {
            KeyedMessage<String, String> km = new KeyedMessage<>(topic, message);
            kms.add(km);
        }
        producer.send(kms);
    }

    private void send(Producer<String, String> producer, String topic, String key, String message) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        if (message == null) {
            throw new RuntimeException("send message is null!");
        }
        if (key == null) {
            throw new RuntimeException("send key is null!");
        }
        KeyedMessage<String, String> km = new KeyedMessage<>(topic, key, message);
        producer.send(km);
    }

    private void send(Producer<String, String> producer, String topic, Map<String, List<String>> messages) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        if (messages == null || messages.isEmpty()) {
            throw new RuntimeException("send messages can not be empty!");
        }
        List<KeyedMessage<String, String>> kms = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : messages.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            for (String message : value) {
                KeyedMessage<String, String> km = new KeyedMessage<>(topic, key, message);
                kms.add(km);
            }
        }
        producer.send(kms);
    }

    public boolean testDatasource(com.hex.bigdata.udsp.common.api.model.Datasource datasource) {
        boolean canConnection = true;
        Producer<String, String> producer = null;
        try {
            List<Property> propertyList = datasource.getProperties();
            Property prop = new Property();
            prop.setName("serializer.class");
            prop.setValue("kafka.serializer.StringEncoder");
            propertyList.add(prop);

            prop = new Property();
            prop.setName("key.serializer.class");
            prop.setValue("kafka.serializer.StringEncoder");
            propertyList.add(prop);

            prop = new Property();
            prop.setName("request.required.acks");
            prop.setValue("0");
            propertyList.add(prop);

            prop = new Property();
            prop.setName("message.send.max.retries");
            prop.setValue("3");
            propertyList.add(prop);

            prop = new Property();
            prop.setName("retry.backoff.ms");
            prop.setValue("1000");
            propertyList.add(prop);

            KafkaProducerDatasource kafkaProducerDsConfig = new KafkaProducerDatasource(propertyList);
            producer = new Producer<>(getProducerConfig(kafkaProducerDsConfig));

            if (producer == null) {
                canConnection = false;
            } else {
                producer.send(new KeyedMessage<String, String>("udsp-rts-ds-test", "udsp rts datasource test info"));
            }

        } catch (Exception e) {
            canConnection = false;
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
        return canConnection;
    }

    //-------------------------------------------Consumer---------------------------------------------

    private ConsumerConfig getCnsumerConfig(KafkaConsumerDatasource datasource) {
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

    public ConsumerResponse pull(ConsumerRequest consumerRequest) {
        long bef = System.currentTimeMillis();
        ConsumerResponse consumerResponse = new ConsumerResponse();
        consumerResponse.setConsumerRequest(consumerRequest);
        Metadata metadata = consumerRequest.getApplication().getMetadata();
        String topic = metadata.getTopic();
        Datasource datasource = metadata.getDatasource();
        Map<String, Property> propertyMap = datasource.getPropertyMap();
        propertyMap.put("consumer.timeout.ms", new Property("consumer.timeout.ms", Long.toString(consumerRequest.getTimeout())));
        KafkaConsumerDatasource consumerDatasource = new KafkaConsumerDatasource(propertyMap);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(getCnsumerConfig(consumerDatasource));
        int threadNum = consumerDatasource.getThreadNum();
        List<Map<String, String>> records = new ArrayList<>();
        try {
            List<KafkaStream<byte[], byte[]>> streams = receive(consumer, topic, threadNum);
            Map<String, Object> map = null;
            Map<String, String> result = null;
            for (KafkaStream<byte[], byte[]> stream : streams) {
                ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
                while (iterator.hasNext()) {
                    String message = new String(iterator.next().message());
                    logger.debug("kafka接收的信息为：" + message);
                    try {
                        map = JSONUtil.parseJSON2Map(message, String.class);
                        result = new HashMap<>();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            result.put(entry.getKey(), String.valueOf(entry.getValue()));
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    records.add(result);
                }
            }
            consumerResponse.setRecords(records);
            consumerResponse.setTotalCount(records.size());
            consumerResponse.setStatus(Status.SUCCESS);
            consumerResponse.setStatusCode(StatusCode.SUCCESS);
        } catch (ConsumerTimeoutException e) {
            consumerResponse.setRecords(records);
            consumerResponse.setStatus(Status.TIMEOUT);
            consumerResponse.setStatusCode(StatusCode.TIMEOUT);
            consumerResponse.setMessage(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            consumerResponse.setStatus(Status.DEFEAT);
            consumerResponse.setStatusCode(StatusCode.DEFEAT);
            consumerResponse.setMessage(e.getMessage());
        } finally {
            if (consumer != null) {
                consumer.shutdown();
            }
        }
        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        consumerResponse.setConsumeTime(consumeTime);
        return consumerResponse;
    }

    private KafkaStream<byte[], byte[]> receive(ConsumerConnector consumer, String topic) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, Integer> topicCountMap = new HashMap<>();
        // 一次从topic中获取1个KafkaStream（一个KafkaStream可产生一个迭代器）
        String topicName = topic;
        topicCountMap.put(topicName, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topicName).get(0);
        return stream;
    }

    private Map<String, KafkaStream<byte[], byte[]>> receiveMap(ConsumerConnector consumer, String topic) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, KafkaStream<byte[], byte[]>> streamMap = new HashMap<>();
        Map<String, Integer> topicCountMap = new HashMap<>();
        String[] topicNames = topic.split(",");
        for (String topicName : topicNames) {
            topicCountMap.put(topicName, 1);
        }
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        for (Map.Entry<String, Integer> entry : topicCountMap.entrySet()) {
            String topicName = entry.getKey();
            KafkaStream<byte[], byte[]> stream = consumerMap.get(topicName).get(0);
            streamMap.put(topicName, stream);
        }
        return streamMap;
    }

    private List<KafkaStream<byte[], byte[]>> receive(ConsumerConnector consumer, String topic, Integer threadNum) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, Integer> topicCountMap = new HashMap<>();
        // threadNum是线程数
        // 一次从topic中获取threadNum个KafkaStream（一个KafkaStream可产生一个迭代器）
        String topicName = topic;
        topicCountMap.put(topicName, threadNum);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streamList = consumerMap.get(topicName);
        return streamList;
    }

    private Map<String, List<KafkaStream<byte[], byte[]>>> receiveMap(ConsumerConnector consumer, String topic, Integer threadNum) {
        if (StringUtils.isBlank(topic)) {
            throw new RuntimeException("kafka topic can not be empty!");
        }
        Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = new HashMap<>();
        Map<String, Integer> topicCountMap = new HashMap<>();
        String[] topicNames = topic.split(",");
        for (String topicName : topicNames) {
            topicCountMap.put(topicName, threadNum);
        }
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        for (Map.Entry<String, Integer> entry : topicCountMap.entrySet()) {
            String topicName = entry.getKey();
            List<KafkaStream<byte[], byte[]>> streamList = consumerMap.get(topicName);
            streamMap.put(topicName, streamList);
        }
        return streamMap;
    }

}
