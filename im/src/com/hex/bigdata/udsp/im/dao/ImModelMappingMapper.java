package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImModelMapping;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImModelMappingMapper  extends SyncMapper<ImModelMapping> {

    @Override
    protected boolean insertExe(ImModelMapping imModelMapping) {
        return false;
    }

    @Override
    protected boolean updateExe(ImModelMapping imModelMapping) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImModelMapping selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ImModelMapping> selectListExe(String id) {
        return null;
    }
}