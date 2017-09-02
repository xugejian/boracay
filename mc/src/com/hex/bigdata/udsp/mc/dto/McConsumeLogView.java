package com.hex.bigdata.udsp.mc.dto;

import com.hex.bigdata.udsp.mc.model.McConsumeLog;

import java.io.Serializable;

/**
 * Created by PC on 2017/3/9.
 */
public class McConsumeLogView extends McConsumeLog implements Serializable {
    private String requestStartTimeStart;
    private String requestStartTimeEnd;
    private String requestEndTimeStart;
    private String requestEndTimeEnd;
    private String runStartTimeStart;
    private String runStartTimeEnd;
    private String runEndTimeStart;
    private String runEndTimeEnd;

    public String getRequestStartTimeStart() {
        return requestStartTimeStart;
    }

    public void setRequestStartTimeStart(String requestStartTimeStart) {
        this.requestStartTimeStart = requestStartTimeStart;
    }

    public String getRequestStartTimeEnd() {
        return requestStartTimeEnd;
    }

    public void setRequestStartTimeEnd(String requestStartTimeEnd) {
        this.requestStartTimeEnd = requestStartTimeEnd;
    }

    public String getRequestEndTimeStart() {
        return requestEndTimeStart;
    }

    public void setRequestEndTimeStart(String requestEndTimeStart) {
        this.requestEndTimeStart = requestEndTimeStart;
    }

    public String getRequestEndTimeEnd() {
        return requestEndTimeEnd;
    }

    public void setRequestEndTimeEnd(String requestEndTimeEnd) {
        this.requestEndTimeEnd = requestEndTimeEnd;
    }

    public String getRunStartTimeStart() {
        return runStartTimeStart;
    }

    public void setRunStartTimeStart(String runStartTimeStart) {
        this.runStartTimeStart = runStartTimeStart;
    }

    public String getRunStartTimeEnd() {
        return runStartTimeEnd;
    }

    public void setRunStartTimeEnd(String runStartTimeEnd) {
        this.runStartTimeEnd = runStartTimeEnd;
    }

    public String getRunEndTimeStart() {
        return runEndTimeStart;
    }

    public void setRunEndTimeStart(String runEndTimeStart) {
        this.runEndTimeStart = runEndTimeStart;
    }

    public String getRunEndTimeEnd() {
        return runEndTimeEnd;
    }

    public void setRunEndTimeEnd(String runEndTimeEnd) {
        this.runEndTimeEnd = runEndTimeEnd;
    }
}
