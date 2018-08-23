package com.hex.bigdata.udsp.rts.executor.model;

import java.io.Serializable;

/**
 * Created by tomnic on 2017/3/3.
 */
public class Application implements Serializable{

    private String name;

    private String describe;

    private String note;

    private Metadata metadata;

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

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
