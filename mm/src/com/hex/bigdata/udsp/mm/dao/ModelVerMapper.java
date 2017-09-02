package com.hex.bigdata.udsp.mm.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.mm.model.ModelVer;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class ModelVerMapper extends SyncMapper<ModelVer> {

    @Override
    protected boolean insertExe(ModelVer modelVer) {
        return false;
    }

    @Override
    protected boolean updateExe(ModelVer modelVer) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ModelVer selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ModelVer> selectListExe(String id) {
        return null;
    }
}