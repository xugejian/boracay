package com.hex.bigdata.udsp.im.provider.model;

import com.hex.bigdata.udsp.common.provider.model.Base;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class Model extends Base {
    private String name;

    private String describe;

    private String note;

    private String type; // 类型（1：批量 2：实时）

    private String buildMode; // 构建策略（1：增量，2：全量）

    private String updateMode; // 更新策略（1、匹配更新 2、更新、插入 3、增量插入）

    private List<MetadataCol> updateKeys; // 更新键值集合

    private Datasource datasource; // 源的数据源

    private Metadata metadata; // 目标的元数据

    private List<ModelMapping> modelMappings; // 字段映射集合

    private List<ModelFilterCol> modelFilterCols; // 过滤字段集合

    public Model(List<Property> properties) {
        super(properties);
    }

    public Model(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public String getName() {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("name不能为空");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        if (StringUtils.isBlank(type))
            throw new IllegalArgumentException("type不能为空");
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuildMode() {
        return buildMode;
    }

    public void setBuildMode(String buildMode) {
        this.buildMode = buildMode;
    }

    public String getUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(String updateMode) {
        this.updateMode = updateMode;
    }

    public Datasource getDatasource() {
        if (datasource == null)
            throw new IllegalArgumentException("datasource不能为空");
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public Metadata getMetadata() {
        if (metadata == null)
            throw new IllegalArgumentException("metadata不能为空");
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<ModelMapping> getModelMappings() {
        if (modelMappings == null || modelMappings.size() == 0)
            throw new IllegalArgumentException("modelMappings不能为空");
        return modelMappings;
    }

    public void setModelMappings(List<ModelMapping> modelMappings) {
        this.modelMappings = modelMappings;
    }

    public List<ModelFilterCol> getModelFilterCols() {
        return modelFilterCols;
    }

    public void setModelFilterCols(List<ModelFilterCol> modelFilterCols) {
        this.modelFilterCols = modelFilterCols;
    }

    public List<MetadataCol> getUpdateKeys() {
        return updateKeys;
    }

    public void setUpdateKeys(List<MetadataCol> updateKeys) {
        this.updateKeys = updateKeys;
    }
}
