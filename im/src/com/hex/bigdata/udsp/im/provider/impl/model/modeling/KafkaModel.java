package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class KafkaModel extends Model {
    public KafkaModel(List<Property> properties) {
        super(properties);
    }

    public KafkaModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public String getTopic() {
        String value = getProperty("kafka.topic").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("kafka.topic不能为空");
        return value;
    }

    public String getConsumerTimeMs() {
        String value = getProperty("kafka.consumer.time.ms").getValue();
        if (StringUtils.isBlank(value))
            value = "1000";
        return value;
    }

    public String getConsumerInterval() {
        String value = getProperty("kafka.consumer.interval").getValue();
        if (StringUtils.isBlank(value))
            value = "1";
        return value;
    }
}
