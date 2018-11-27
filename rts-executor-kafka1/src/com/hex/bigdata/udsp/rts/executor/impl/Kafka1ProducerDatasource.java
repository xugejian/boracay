package com.hex.bigdata.udsp.rts.executor.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2018-11-27.
 */
public class Kafka1ProducerDatasource extends Datasource {

    public Kafka1ProducerDatasource(List<Property> propertyList) {
        super (propertyList);
    }

    public Kafka1ProducerDatasource(Map<String, Property> propertyMap) {
        super (propertyMap);
    }

    public String getBootstrapServers() {
        String value = getProperty ("bootstrap.servers").getValue ();
        if (StringUtils.isBlank (value))
            throw new IllegalArgumentException ("bootstrap.servers不能为空");
        return value;
    }

    public String getKeySerializer() {
        String value = getProperty ("key.serializer").getValue ();
        if (StringUtils.isBlank (value))
            value = "org.apache.kafka.common.serialization.StringSerializer";
        return value;
    }

    public String getValueSerializer() {
        String value = getProperty ("value.serializer").getValue ();
        if (org.apache.commons.lang.StringUtils.isBlank (value))
            value = "org.apache.kafka.common.serialization.StringSerializer";
        return value;
    }

    public String getAcks() {
        return getProperty ("acks").getValue ();
    }

    public String getRetries() {
        return getProperty ("retries").getValue ();
    }

    public String getRetryBackoffMs() {
        return getProperty ("retry.backoff.ms").getValue ();
    }

    public String getSecurityProtocol() {
        return getProperty ("security.protocol").getValue ();
    }

    public String getSaslKerberosServiceName() {
        return getProperty ("sasl.kerberos.service.name").getValue ();
    }
}
