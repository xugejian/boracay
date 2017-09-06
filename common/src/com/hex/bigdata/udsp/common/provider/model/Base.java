package com.hex.bigdata.udsp.common.provider.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class Base {

    private List<Property> properties; // 配置参数集合

    private Map<String, Property> propertyMap; // 配置参数Map

    public Base() {
    }

    public Base(List<Property> properties) {
        this.properties = properties;
    }

    public Base(Map<String, Property> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Property getProperty(String key) {
        Property property = this.propertyMap.get(key);
        if (property == null) {
            property = new Property();
        }
        return property;
    }

    public Map<String, Property> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, Property> propertieMap) {
        this.propertyMap = propertieMap;
        if (this.properties == null)
            this.properties = new ArrayList<Property>();
        for (Map.Entry<String, Property> entry : propertieMap.entrySet()) {
            properties.add(entry.getValue());
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
        if (this.propertyMap == null)
            this.propertyMap = new HashMap<String, Property>();
        for (Property property : properties) {
            this.propertyMap.put(property.getName(), property);
        }
    }

}
