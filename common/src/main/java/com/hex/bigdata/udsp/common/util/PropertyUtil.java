package com.hex.bigdata.udsp.common.util;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class PropertyUtil {
    /**
     * ComProperties类型转换为Property类型
     *
     * @param comPropertiesList
     * @return
     */
    public static List<Property> convertToPropertyList(List<ComProperties> comPropertiesList) {
        List<Property> propertyList = new ArrayList<>();
        for (ComProperties item : comPropertiesList) {
            Property property = new Property();
            property.setName(item.getName());
            property.setValue(item.getValue());
            property.setDescribe(item.getDescribe());
            propertyList.add(property);
        }
        return propertyList;
    }
}
