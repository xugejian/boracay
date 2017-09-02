package com.hex.bigdata.udsp.mm.service;

import com.hex.bigdata.udsp.mm.dao.ModelParamMapper;
import com.hex.bigdata.udsp.mm.model.ModelParam;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/19
 * TIME:14:43
 */
@Service
public class ModelParamService extends BaseService {

    @Autowired
    private ModelParamMapper modelParamMapper;

    @Transactional
    public String insert(ModelParam modelParam) {
        String pkId = Util.uuid();
        modelParam.setPkId(pkId);
        if (modelParamMapper.insert(modelParam.getPkId(), modelParam)) {
            return pkId;
        }
        return "";
    }

    @Transactional
    public boolean update(ModelParam modelParam) {
        return modelParamMapper.update(modelParam.getPkId(), modelParam);
    }

    @Transactional
    public boolean delete(String pkId) {
        return modelParamMapper.delete(pkId);
    }

    @Transactional
    public boolean deleteByMmId(String mmId) {
        return modelParamMapper.deleteByMmId(mmId);
    }

    public ModelParam select(String pkId) {
        return modelParamMapper.select(pkId);
    }

    public List<ModelParam> select(ModelParam modelParam, Page page) {
        return modelParamMapper.select(modelParam, page);
    }

    public List<ModelParam> select(String mmId, String type) {
        return modelParamMapper.select(mmId, type);
    }

    public List<ModelParam> select(ModelParam modelParam) {
        return modelParamMapper.select(modelParam);
    }

    public List<ModelParam> selectByMmId(String mmId) {
        return modelParamMapper.selectByMmId(mmId);
    }

    @Transactional
    public boolean insertQueryColList(String mmId, List<ModelParam> modelParams) {
        for (ModelParam modelParam : modelParams) {
            modelParam.setPkId(Util.uuid());
            modelParam.setMmId(mmId);
            modelParam.setType("1");
        }
        return modelParamMapper.insertList(mmId, modelParams);
    }

    @Transactional
    public boolean insertReturnColList(String mmId, List<ModelParam> modelParams) {
        if (modelParams == null) {
            return true;
        }
        for (ModelParam modelParam : modelParams) {
            modelParam.setPkId(Util.uuid());
            modelParam.setMmId(mmId);
            modelParam.setType("2");
        }
        return modelParamMapper.insertList(mmId, modelParams);
    }

}
