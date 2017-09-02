package com.hex.bigdata.udsp.mm.dto;

import com.hex.bigdata.udsp.mm.model.ModelInfo;
import com.hex.bigdata.udsp.mm.model.ModelParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TZBank on 17-4-19.
 */
public class MmCollocateParamView implements Serializable {
    private ModelInfo modelInfo;

    private List<ModelParam> modelExcuteParam;

    private List<ModelParam> modelReturnParam;

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }


    public List<ModelParam> getModelExcuteParam() {
        return modelExcuteParam;
    }

    public void setModelExcuteParam(List<ModelParam> modelExcuteParam) {
        this.modelExcuteParam = modelExcuteParam;
    }

    public List<ModelParam> getModelReturnParam() {
        return modelReturnParam;
    }

    public void setModelReturnParam(List<ModelParam> modelReturnParam) {
        this.modelReturnParam = modelReturnParam;
    }
}
