package com.hex.bigdata.udsp.rts.executor.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-3-3.
 */
public class ProducerDatasource extends Datasource implements Serializable {

    public ProducerDatasource(List<Property> properties) {
        super(properties);
    }

    public ProducerDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public ProducerDatasource(Datasource datasource) {
        super(datasource);
    }
}
