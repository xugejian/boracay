package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImModelFilterCol;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ImModelFilterColMapper  extends SyncMapper<ImModelFilterCol> {

    @Override
    protected boolean insertExe(ImModelFilterCol imModelFilterCol) {
        return false;
    }

    @Override
    protected boolean updateExe(ImModelFilterCol imModelFilterCol) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImModelFilterCol selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ImModelFilterCol> selectListExe(String id) {
        return null;
    }
}