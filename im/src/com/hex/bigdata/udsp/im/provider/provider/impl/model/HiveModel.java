package com.hex.bigdata.udsp.im.provider.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class HiveModel extends Model {
    public HiveModel(List<Property> properties) {
        super(properties);
    }

    public HiveModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public String getDatabaseName() {
        String value = getProperty("hive.database.name").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("hive.database.name不能为空");
        return value;
    }

    public String getTableName() {
        String value = getProperty("hive.table.name").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("hive.table.name不能为空");
        return value;
    }

    public String getSql() {
        String value = getProperty("hive.sql").getValue();
        //if (StringUtils.isBlank(value))
        //    throw new IllegalArgumentException("hive.sql不能为空");
        return value;
    }
}
