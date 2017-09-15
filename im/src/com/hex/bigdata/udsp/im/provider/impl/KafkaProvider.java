package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.RealtimeSourceProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.KafkaDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.KafkaModel;
import com.hex.bigdata.udsp.im.provider.impl.util.KafkaUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.KafkaWrapper;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-4.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.KafkaProvider")
public class KafkaProvider extends KafkaWrapper implements RealtimeSourceProvider {
    private static Logger logger = LogManager.getLogger(KafkaProvider.class);
    private static final int CONSUMER_TIMEOUT_MS = 1000;

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        List<MetadataCol> metadataCols = null;
        Datasource datasource = model.getSourceDatasource();
        KafkaModel kafkaModel = new KafkaModel(model.getPropertyMap());
        String topic = kafkaModel.getTopic();
        Map<String, Property> propertyMap = datasource.getPropertyMap();
        propertyMap.put("consumer.timeout.ms", new Property("consumer.timeout.ms", Integer.toString(CONSUMER_TIMEOUT_MS)));
        KafkaDatasource kafkaDatasource = new KafkaDatasource(propertyMap);
        ConsumerConnector consumer = KafkaUtil.getConsumerConnector(kafkaDatasource);
        int threadNum = kafkaDatasource.getThreadNum();
        List<KafkaStream<byte[], byte[]>> streams = KafkaUtil.receive(consumer, topic, threadNum);
        for (KafkaStream<byte[], byte[]> stream : streams) {
            ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
            while (iterator.hasNext()) {
                String message = new String(iterator.next().message());
                logger.debug("kafka接收的信息为：" + message);
                try {
                    Map<String, Object> map = JSONUtil.parseJSON2Map(message);
                    metadataCols = new ArrayList<>();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        MetadataCol metadataCol = new MetadataCol();
                        metadataCol.setName(entry.getKey());
                        metadataCol.setType(getType(entry.getValue()));
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
}
