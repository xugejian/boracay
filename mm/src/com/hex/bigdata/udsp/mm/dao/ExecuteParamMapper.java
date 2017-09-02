package com.hex.bigdata.udsp.mm.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ExecuteParamMapper extends SyncMapper<MmAppExecuteParam> {

    @Override
    protected boolean insertExe(MmAppExecuteParam mmAppReturnParam) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.mm.dao.ExecuteParamMapper.insert", mmAppReturnParam) == 1;
    }

    @Override
    protected boolean updateExe(MmAppExecuteParam mmAppReturnParam) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.mm.dao.ExecuteParamMapper.updateByPrimaryKey", mmAppReturnParam) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.mm.dao.ExecuteParamMapper.deleteByPrimaryKey", id) == 1;
    }

    @Override
    protected MmAppExecuteParam selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.mm.dao.ExecuteParamMapper.selectByPrimaryKey", id);
    }


    @Override
    protected boolean deleteListExe(String fkId) {
        this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.mm.dao.ExecuteParamMapper.deleteListByFkId", fkId);
        return true;
    }

    @Override
    protected List<MmAppExecuteParam> selectListExe(String fkId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mm.dao.ExecuteParamMapper.selectListByFkId",fkId);
    }
}