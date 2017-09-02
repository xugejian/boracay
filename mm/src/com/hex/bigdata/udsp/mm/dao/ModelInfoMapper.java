package com.hex.bigdata.udsp.mm.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.mm.dto.ModelInfoView;
import com.hex.bigdata.udsp.mm.model.ModelInfo;
import com.hex.goframe.model.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModelInfoMapper extends SyncMapper<ModelInfo> {

    @Override
    protected boolean insertExe(ModelInfo modelInfo) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.insert", modelInfo) == 1;
    }

    @Override
    protected boolean updateExe(ModelInfo modelInfo) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.updateByPrimaryKey", modelInfo) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected ModelInfo selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.selectByPrimaryKey", id);
    }


    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ModelInfo> selectListExe(String fkId) {
        return null;
    }

    /**
     * @param modelInfoView
     * @param page
     * @return
     */
    public List<ModelInfoView> selectPage(ModelInfoView modelInfoView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.selectPage", modelInfoView,
                page.toPageBounds());
    }

    /**
     * 根据名称查找
     * @param name
     * @return
     */
    public ModelInfo selectByName(String name) {
        return sqlSessionTemplate.selectOne(
                "com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.selectByName",name);
    }

    /**
     * 查询所有模型
     * @return
     */
    public List<ModelInfo> selectAll() {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.selectAll");
    }

    public List<ModelInfo> selectByContractorId(String contractorId) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mm.dao.ModelInfoMapper.selectByContractorId",contractorId);
    }
}