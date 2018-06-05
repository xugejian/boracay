package com.hex.bigdata.udsp.rts.executor.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-3-3.
 */
public class ConsumerDatasource extends Datasource implements Serializable {

    private String groupId;

    public ConsumerDatasource(List<Property> properties) {
        super(properties);
    }

    public ConsumerDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public ConsumerDatasource(Datasource datasource) {
        super(datasource);
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
        return DigestUtils.md5Hex(super.getId() + "\ngroup.id=" + groupId + "\n");
    }
}
