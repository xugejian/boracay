package com.hex.bigdata.udsp.im.converter.impl.model;


import com.hex.bigdata.udsp.im.converter.model.Model;
import org.apache.commons.lang.StringUtils;

/**
 * Created by JunjieM on 2018-3-7.
 */
public class KuduModel extends Model {

    public KuduModel(Model model) {
        super(model);
    }

    public String gainKuduTableName() {
        String value = gainProperty("kudu.table.name").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("kudu.table.name不能为空");
        }
        return value;
    }
}
