package com.hex.bigdata.udsp.im.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class MysqlModel extends Model {
    public MysqlModel(List<Property> properties) {
        super(properties);
    }

    public MysqlModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public String getDatabaseName() {
        String value = getProperty("mysql.database.name").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("mysql.database.name不能为空");
        return value;
    }

    public String getTableName() {
        String value = getProperty("mysql.table.name").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("mysql.table.name不能为空");
        return value;
    }

    public String getSql() {
        String value = getProperty("mysql.sql").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("mysql.sql不能为空");
        return value;
    }
}
