package com.hex.bigdata.udsp.im.provider.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class OracleModel extends Model {
    public OracleModel(List<Property> properties) {
        super(properties);
    }

    public OracleModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public String getDatabaseName() {
        String value = getProperty("oracle.database.name").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("oracle.database.name不能为空");
        return value;
    }

    public String getTableName() {
        String value = getProperty("oracle.table.name").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("oracle.table.name不能为空");
        return value;
    }

    public String getSql() {
        String value = getProperty("oracle.sql").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("oracle.sql不能为空");
        return value;
    }
}
