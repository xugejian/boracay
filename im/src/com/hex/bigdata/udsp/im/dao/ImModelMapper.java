package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImModelMapper extends SyncMapper<ImModel> {

    @Override
    protected boolean insertExe(ImModel imModel) {
        return false;
    }

    @Override
    protected boolean updateExe(ImModel imModel) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImModel selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ImModel> selectListExe(String id) {
        return null;
    }
}