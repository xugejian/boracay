package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.im.converter.model.Model;
import org.apache.commons.lang.StringUtils;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrModel extends Model {

    public SolrModel(Model model) {
        super(model);
    }

    public String gainCollectionName() {
        String value = gainProperty("solr.collection.name").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("solr.collection.name不能为空");
        }
        return value;
    }
}
