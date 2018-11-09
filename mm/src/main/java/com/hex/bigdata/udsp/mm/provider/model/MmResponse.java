package com.hex.bigdata.udsp.mm.provider.model;

import java.io.Serializable;

public class MmResponse implements Serializable {
    private String status;
    private String message;
    private String uuid;
    private MmResponseData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public MmResponseData getData() {
        return data;
    }

    public void setData(MmResponseData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
