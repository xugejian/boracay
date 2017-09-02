package com.hex.bigdata.udsp.mm.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.mm.model.ModelFile;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class ModelFileMapper extends SyncMapper<ModelFile> {

    @Override
    protected boolean insertExe(ModelFile modelFile) {
        return false;
    }

    @Override
    protected boolean updateExe(ModelFile modelFile) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ModelFile selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ModelFile> selectListExe(String id) {
        return null;
    }

}