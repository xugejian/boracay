package com.hex.bigdata.udsp.im.model;


import com.hex.bigdata.udsp.common.model.ComProperties;

import java.io.Serializable;
import java.util.List;

/**
 * 交互建模视图
 * Created by jintian on 2017/9/6.
 */
public class ImModelViews implements Serializable {
    //模型
    private ImModel imModel;
    //关系映射表
    private List<ImModelMapping> imModelMappings;
    //字段过滤
    private List<ImModelFilterCol> imModelFilterCols;
    //参数内容
    private List<ComProperties> comPropertiesList;

    public ImModel getImModel() {
        return imModel;
    }

    public void setImModel(ImModel imModel) {
        this.imModel = imModel;
    }

    public List<ImModelMapping> getImModelMappings() {
        return imModelMappings;
    }

    public void setImModelMappings(List<ImModelMapping> imModelMappings) {
        this.imModelMappings = imModelMappings;
    }

    public List<ImModelFilterCol> getImModelFilterCols() {
        return imModelFilterCols;
    }

    public void setImModelFilterCols(List<ImModelFilterCol> imModelFilterCols) {
        this.imModelFilterCols = imModelFilterCols;
    }

    public List<ComProperties> getComPropertiesList() {
        return comPropertiesList;
    }

    public void setComPropertiesList(List<ComProperties> comPropertiesList) {
        this.comPropertiesList = comPropertiesList;
    }
}
