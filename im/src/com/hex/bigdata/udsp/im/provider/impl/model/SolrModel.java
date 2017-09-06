package com.hex.bigdata.udsp.im.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrModel extends Model {
    public SolrModel(List<Property> properties) {
        super(properties);
    }

    public SolrModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public String getCollectionName() {
        String value = getProperty("solr.collection.name").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("solr.collection.name不能为空");
        return value;
    }
}
