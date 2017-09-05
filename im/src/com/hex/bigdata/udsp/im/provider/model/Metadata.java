package com.hex.bigdata.udsp.im.provider.model;

import com.hex.bigdata.udsp.common.provider.model.Base;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class Metadata extends Base {
    private String name;

    private String describe;

    private String note;

    private String tbName; // 库表

    private String status; // 状态

    private String type; // 类型

    private Datasource datasource; // 数据源

    private List<MetadataCol> metadataCols; // 字段集合

    public String getStatus() {
        if (StringUtils.isBlank(status))
            throw new IllegalArgumentException("status不能为空");
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        if (StringUtils.isBlank(type))
            throw new IllegalArgumentException("type不能为空");
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTbName() {
        if (StringUtils.isBlank(tbName))
            throw new IllegalArgumentException("tbName不能为空");
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getName() {
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

    public Datasource getDatasource() {
        if (datasource == null)
            throw new IllegalArgumentException("datasource不能为空");
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public List<MetadataCol> getMetadataCols() {
        return metadataCols;
    }

    public void setMetadataCols(List<MetadataCol> metadataCols) {
        this.metadataCols = metadataCols;
    }
}
