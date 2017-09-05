package com.hex.bigdata.udsp.im.provider.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrHBaseDatasource extends Datasource {
    private String zkQuorum;
    private String zkPort;
    private String solrServers;

    public SolrHBaseDatasource(List<Property> properties) {
        super(properties);
    }

    public SolrHBaseDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public String getZkQuorum() {
        String value = getProperty("hbase.zk.quorum").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("hbase.zk.quorum不能为空");
        return value;
    }

    public String getZkPort() {
        String value = getProperty("hbase.zk.port").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("hbase.zk.port不能为空");
        return value;
    }

    public String getSolrServers() {
        String value = getProperty("solr.servers").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("solr.servers不能为空");
        return value;
    }
}
