package com.hex.bigdata.udsp.mm.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.mm.model.ModelParam;
import com.hex.goframe.model.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModelParamMapper extends SyncMapper<ModelParam> {

    protected boolean insertExe(ModelParam modelParam) {

        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.mm.dao.ModelParamMapper.insert", modelParam) == 1;
    }

    protected boolean updateExe(ModelParam modelParam) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.mm.dao.ModelParamMapper.updateByPrimaryKey", modelParam) == 1;
    }

    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.mm.dao.ModelParamMapper.deleteByPrimaryKey", id) == 1;
    }

    protected ModelParam selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.mm.dao.ModelParamMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.mm.dao.ModelParamMapper.deleteByMmId", id) >= 0;
    }

    protected List<ModelParam> selectListExe(String fkId) {
        ModelParam modelParam = new ModelParam();
        modelParam.setMmId(fkId);
        return select(modelParam);
    }

    public List<ModelParam> select(String mmId, String type) {
        ModelParam modelParam = new ModelParam();
        modelParam.setMmId(mmId);
        modelParam.setType(type);
        return select(modelParam);
    }

    public List<ModelParam> selectByMmId(String mmId) {
        return this.selectList(mmId);
    }

    public List<ModelParam> select(ModelParam modelParam, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mm.dao.ModelParamMapper.select", modelParam,
                page.toPageBounds());
    }

    public List<ModelParam> select(ModelParam modelParam) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mm.dao.ModelParamMapper.select", modelParam);
    }

    public boolean deleteByMmId(String mmId) {
        return this.deleteList(mmId);
    }
}