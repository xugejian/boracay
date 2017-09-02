package com.hex.bigdata.udsp.rts.provider.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;

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

    public ProducerDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }
}
