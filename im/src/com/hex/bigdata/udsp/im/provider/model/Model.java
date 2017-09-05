package com.hex.bigdata.udsp.im.provider.model;

import com.hex.bigdata.udsp.common.provider.model.Base;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class Model extends Base {
    private String name;

    private String describe;

    private String note;

    private String type; // 类型（1：批量 2：实时）

    private String buildMode; // 构建策略（1：增量，2：全量）

    private String updateMode; // 更新策略（1、匹配更新 2、更新、插入 3、增量插入，默认：2）

    private String updateKey; // 更新键值（更新策略是1或2时显示，且必输）

    private Datasource datasource; // 源的数据源

    private Metadata metadata; // 目标的元数据

    private List<ModelMapping> modelMappings; // 字段映射集合

    private List<ModelFilterCol> modelFilterCols; // 过滤字段集合

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
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("name不能为空");
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

    public String getUpdateKey() {
        return updateKey;
    }

    public void setUpdateKey(String updateKey) {
        this.updateKey = updateKey;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<ModelMapping> getModelMappings() {
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
}
