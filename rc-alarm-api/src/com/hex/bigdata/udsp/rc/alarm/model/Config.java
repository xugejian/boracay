package com.hex.bigdata.udsp.rc.alarm.model;

import com.hex.bigdata.udsp.common.api.model.Base;
import com.hex.bigdata.udsp.common.api.model.Property;

import java.util.List;
import java.util.Map;

/**
 * 警报的配置参数类
 */
public class Config extends Base {
    public Config() {
    }

    public Config(List<Property> properties) {
        super (properties);
    }

    public Config(Map<String, Property> propertyMap) {
        super (propertyMap);
    }

}
