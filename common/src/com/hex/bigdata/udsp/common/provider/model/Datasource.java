package com.hex.bigdata.udsp.common.provider.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.util.PropertyUtil;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-3-2.
 */
public class Datasource extends Base {
    private String name;

    private String describe;

    private String type;  // 类型

    private String note;

    private String implClass;

    private List<Property> properties; // 配置参数集合

    private Map<String, Property> propertyMap;

    public Datasource(List<Property> properties) {
        this.setProperties(properties);
    }

    public Datasource(Map<String, Property> propertieMap) {
        this.setPropertyMap(propertieMap);
    }

    public Datasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        this.name = comDatasource.getName();
        this.type = comDatasource.getType();
        this.describe = comDatasource.getDescribe();
        this.note = comDatasource.getNote();
        this.implClass = comDatasource.getImplClass();
        this.properties = PropertyUtil.convertToPropertyList(comPropertieList);
        if (this.propertyMap == null)
            this.propertyMap = new HashMap<String, Property>();
        for (Property property : this.properties) {
            this.propertyMap.put(property.getName(), property);
        }
    }

    public String getImplClass() {
        return implClass;
    }

    public void setImplClass(String implClass) {
        this.implClass = implClass;
    }

    public String getId() {
        StringBuffer sb = new StringBuffer();
        for (Property property : properties) {
            sb.append(property.getName() + "=" + property.getValue() + "\n");
        }
        return Util.MD5(sb.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        if (StringUtils.isBlank(type))
            throw new IllegalArgumentException("type不能为空");
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
