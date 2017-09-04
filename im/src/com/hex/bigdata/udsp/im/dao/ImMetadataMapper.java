package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImMetadata;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImMetadataMapper extends SyncMapper<ImMetadata> {
    @Override
    protected boolean insertExe(ImMetadata imMetadata) {
        return false;
    }

    @Override
    protected boolean updateExe(ImMetadata imMetadata) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImMetadata selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ImMetadata> selectListExe(String id) {
        return null;
    }
}