package com.hex.bigdata.udsp.rts.provider.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-3-3.
 */
public class ConsumerDatasource extends Datasource  implements Serializable {

    private String groupId;

    public ConsumerDatasource(List<Property> properties) {
        super(properties);
    }

    public ConsumerDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public ConsumerDatasource(ComDatasource comDatasource,List<ComProperties> comPropertieList){
        super(comDatasource, comPropertieList);
    }

    public String getGroupId() {
        if (StringUtils.isBlank(groupId))
            throw new IllegalArgumentException("group.id不能为空");
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getId() {
        return Util.MD5(super.getId() + "\ngroup.id=" + groupId + "\n");
    }
}
