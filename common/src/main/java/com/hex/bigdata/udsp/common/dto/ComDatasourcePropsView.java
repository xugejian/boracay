package com.hex.bigdata.udsp.common.dto;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;

import java.util.List;

/**
 * Created by junjiem on 2017-2-27.
 */
public class ComDatasourcePropsView {
    private ComDatasource comDatasource;
    private List<ComProperties> comPropertiesList;

    public ComDatasource getComDatasource() {
        return comDatasource;
    }

    public void setComDatasource(ComDatasource comDatasource) {
        this.comDatasource = comDatasource;
    }

    public List<ComProperties> getComPropertiesList() {
        return comPropertiesList;
    }

    public void setComPropertiesList(List<ComProperties> comPropertiesList) {
        this.comPropertiesList = comPropertiesList;
    }
}
