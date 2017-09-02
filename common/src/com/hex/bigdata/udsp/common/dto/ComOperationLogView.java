package com.hex.bigdata.udsp.common.dto;


import com.hex.bigdata.udsp.common.model.ComOperationLog;

/**
 * Created by PC on 2017/3/9.
 */
public class ComOperationLogView extends ComOperationLog {
    private String actionTimeStart;
    private String actionTimeEnd;

    public String getActionTimeStart() {
        return actionTimeStart;
    }

    public void setActionTimeStart(String actionTimeStart) {
        this.actionTimeStart = actionTimeStart;
    }

    public String getActionTimeEnd() {
        return actionTimeEnd;
    }

    public void setActionTimeEnd(String actionTimeEnd) {
        this.actionTimeEnd = actionTimeEnd;
    }
}
