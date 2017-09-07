package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Property;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class MysqlModel extends JdbcModel {

    public MysqlModel(List<Property> properties) {
        super(properties);
    }

    public MysqlModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }
}