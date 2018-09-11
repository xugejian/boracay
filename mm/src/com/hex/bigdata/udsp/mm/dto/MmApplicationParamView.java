package com.hex.bigdata.udsp.mm.dto;

import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import com.hex.bigdata.udsp.mm.model.MmAppReturnParam;
import com.hex.bigdata.udsp.mm.model.MmApplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TZBank on 17-4-17.
 */
public class MmApplicationParamView implements Serializable {
    private MmApplication mmapplication;

    private List<MmAppExecuteParam> mmAppExecuteParam;

    private List<MmAppReturnParam> mmAppReturnParam;

    public MmApplication getMmapplication() {
        return mmapplication;
    }

    public void setMmapplication(MmApplication mmapplication) {
        this.mmapplication = mmapplication;
    }

    public List<MmAppExecuteParam> getMmAppExecuteParam() {
        return mmAppExecuteParam;
    }

    public void setMmAppExecuteParam(List<MmAppExecuteParam> mmAppExecuteParam) {
        this.mmAppExecuteParam = mmAppExecuteParam;
    }

    public List<MmAppReturnParam> getMmAppReturnParam() {
        return mmAppReturnParam;
    }

    public void setMmAppReturnParam(List<MmAppReturnParam> mmAppReturnParam) {
        this.mmAppReturnParam = mmAppReturnParam;
    }
}
