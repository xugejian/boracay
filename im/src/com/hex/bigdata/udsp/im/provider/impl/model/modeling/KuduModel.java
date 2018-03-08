package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by JunjieM on 2018-3-7.
 */
public class KuduModel extends Model {
    public KuduModel(List<Property> properties, Datasource srcDatasource) {
        super(properties, srcDatasource);
    }

    public KuduModel(Model model) {
        super(model);
    }

    public String getKuduTableName() {
        String value = getProperty("kudu.table.name").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("kudu.table.name不能为空");
        return value;
    }
}
