package com.hex.bigdata.udsp.olq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import httl.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2018/9/27.
 */
public class PhoenixDatasource extends JdbcDatasource {

    public PhoenixDatasource(List<Property> properties) {
        super(properties);
    }

    public PhoenixDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public PhoenixDatasource(Datasource datasource) {
        super(datasource);
    }

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "org.apache.phoenix.jdbc.PhoenixDriver";
        return value;
    }
}
