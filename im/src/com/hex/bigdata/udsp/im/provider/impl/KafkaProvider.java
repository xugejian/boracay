package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.KafkaWrapper;
import com.hex.bigdata.udsp.im.provider.RealtimeSourceProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.KafkaDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.KafkaModel;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-4.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.KafkaProvider")
public class KafkaProvider extends KafkaWrapper implements RealtimeSourceProvider {
    private static Logger logger = LogManager.getLogger(KafkaProvider.class);

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        List<MetadataCol> metadataCols = null;
        Datasource datasource = model.getSourceDatasource();
        List<Property> propertyList = datasource.getProperties();
        int consumerTimeoutMs = 1000;
        for (Property property : propertyList) {
            if ("consumer.timeout.ms".equals(property.getName())) {
                property.setValue(Integer.toString(consumerTimeoutMs));
            }
        }
        logger.debug("consumer.timeout.ms = " + consumerTimeoutMs + "ms");
        KafkaDatasource kafkaDatasource = new KafkaDatasource(propertyList);
        ConsumerConnector consumer = getConsumerConnector(kafkaDatasource);
        int threadNum = kafkaDatasource.getThreadNum();
        KafkaModel kafkaModel = new KafkaModel(model.getPropertyMap());
        String topic = kafkaModel.getTopic();
        List<KafkaStream<byte[], byte[]>> streams = receive(consumer, topic, threadNum);
        for (KafkaStream<byte[], byte[]> stream : streams) {
            ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
            while (iterator.hasNext()) {
                String message = new String(iterator.next().message());
                logger.debug("kafka接收的信息为：" + message);
                try {
                    Map<String, Object> map = JSONUtil.parseJSON2Map(message);
                    metadataCols = new ArrayList<>();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        DataType type = DataType.STRING;
                        Object value = entry.getValue();
                        if (value instanceof Integer) {
                            type = DataType.INT;
                        } else if (value instanceof BigDecimal) {
                            type = DataType.DECIMAL;
                        } else if (value instanceof Long) {
                            type = DataType.BIGINT;
                        } else if (value instanceof BigInteger) {
                            type = DataType.BIGINT;
                        } else if (value instanceof Double) {
                            type = DataType.DOUBLE;
                        } else if (value instanceof Float) {
                            type = DataType.FLOAT;
                        } else if (value instanceof Short) {
                            type = DataType.SMALLINT;
                        } else if (value instanceof Boolean) {
                            type = DataType.BOOLEAN;
                        } else if (value instanceof Timestamp) {
                            type = DataType.TIMESTAMP;
                        }
                        MetadataCol metadataCol = new MetadataCol();
                        metadataCol.setName(entry.getKey());
                        metadataCol.setType(type);
                        metadataCols.add(metadataCol);
                    }
                    break;
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                }
            }
        }
        return metadataCols;
    }

    @Override
    public List<Map<String, String>> inputData() {

        return null;
    }

}
