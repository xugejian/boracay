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

    public String getBootstrapServers() {
        String value = getProperty ("bootstrap.servers").getValue ();
        if (StringUtils.isBlank (value))
            throw new IllegalArgumentException ("bootstrap.servers不能为空");
        return value;
    }

    public String getKeyDeserializer() {
        String value = getProperty ("key.deserializer").getValue ();
        if (StringUtils.isBlank (value))
            value = "org.apache.kafka.common.serialization.StringDeserializer";
        return value;
    }

    public String getValueDeserializer() {
        String value = getProperty ("value.deserializer").getValue ();
        if (org.apache.commons.lang.StringUtils.isBlank (value))
            value = "org.apache.kafka.common.serialization.StringDeserializer";
        return value;
    }

    public String getEnableAutoCommit() {
        return getProperty ("enable.auto.commit").getValue ();
    }

    public String getAutoCommitIntervalMs() {
        return getProperty ("auto.commit.interval.ms").getValue ();
    }

    public String getSecurityProtocol() {
        return getProperty ("security.protocol").getValue ();
    }

    public String getSaslKerberosServiceName() {
        return getProperty ("sasl.kerberos.service.name").getValue ();
    }
}
