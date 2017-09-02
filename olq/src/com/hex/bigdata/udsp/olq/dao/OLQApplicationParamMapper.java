package com.hex.bigdata.udsp.olq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.olq.model.OLQApplicationParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OLQApplicationParamMapper extends SyncMapper<OLQApplicationParam> {
    @Override
    protected boolean insertExe(OLQApplicationParam olqApplicationParam) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.olq.dao.OLQApplicationParamMapper.insert", olqApplicationParam) == 1;
    }

    @Override
    protected boolean updateExe(OLQApplicationParam olqApplicationParam) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected OLQApplicationParam selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.olq.dao.OLQApplicationParamMapper.deleteListByAppId", id) >= 0;
    }

    @Override
    protected List<OLQApplicationParam> selectListExe(String id) {
        return this.selectByAppId(id);
    }


    protected List<OLQApplicationParam> selectByAppId(String olqAppId) {
        OLQApplicationParam applicationParam = new OLQApplicationParam();
        applicationParam.setOlqAppId(olqAppId);
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.olq.dao.OLQApplicationParamMapper.selectByCondition", applicationParam);

    }
}