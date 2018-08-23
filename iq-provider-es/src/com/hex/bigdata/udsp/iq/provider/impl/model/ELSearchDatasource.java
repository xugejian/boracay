package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Solr的数据源配置
 */
public class ELSearchDatasource extends Datasource {

    public ELSearchDatasource(List<Property> properties) {
        super(properties);
    }

    public ELSearchDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public String getElasticsearchServers() {
        String value = getProperty("elasticsearch.servers").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("elasticsearch.servers不能为空");
        return value;
    }

    public int getMaxNum() {
        String value = getProperty("max.data.size").getValue();
        if (StringUtils.isBlank(value)) {
            value = "65536";
        }
        return Integer.valueOf(value);
    }
}
