package com.hex.bigdata.udsp.im.provider.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class ModelMapping {
    private Short seq;

    private String name;

    private String note;

    private MetadataCol metadataCol; // 元数据字段

    public Short getSeq() {
        if (seq == null)
            throw new IllegalArgumentException("seq不能为空");
        return seq;
    }

    public void setSeq(Short seq) {
        this.seq = seq;
    }

    public String getName() {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("name不能为空");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public MetadataCol getMetadataCol() {
        if (metadataCol == null)
            throw new IllegalArgumentException("metadataCol不能为空");
        return metadataCol;
    }

    public void setMetadataCol(MetadataCol metadataCol) {
        this.metadataCol = metadataCol;
    }
}
