package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-6.
 */
public class JdbcModel extends Model {
    public JdbcModel() {
    }

    public JdbcModel(List<Property> properties) {
        super(properties);
    }

    public JdbcModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public JdbcModel(Model model) {
        super(model);
    }

    public JdbcModel(List<Property> properties, Datasource srcDatasource){
        super(properties,srcDatasource);
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
