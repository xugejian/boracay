package com.hex.bigdata.udsp.common.util;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;

import java.util.List;

/**
 * Created by JunjieM on 2018-5-2.
 */
public class DatasourceUtil {
    /**
     * 获取Datasource
     *
     * @param comDatasource
     * @param comPropertiesList
     * @return
     */
    public static Datasource getDatasource(ComDatasource comDatasource, List<ComProperties> comPropertiesList) {
        List<Property> properties = PropertyUtil.convertToPropertyList(comPropertiesList);
        Datasource datasource = new Datasource(properties);
        datasource.setName(comDatasource.getName());
        datasource.setDescribe(comDatasource.getDescribe());
        datasource.setType(comDatasource.getType());
        datasource.setImplClass(comDatasource.getImplClass());
        datasource.setNote(comDatasource.getNote());
        return datasource;
    }

}
