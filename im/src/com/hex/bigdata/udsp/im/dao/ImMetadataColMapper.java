package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImMetadataColMapper extends SyncMapper<ImMetadataCol> {

    @Override
    protected boolean insertExe(ImMetadataCol imMetadataCol) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.insert", imMetadataCol) == 1;
    }

    @Override
    protected boolean updateExe(ImMetadataCol imMetadataCol) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImMetadataCol selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.deleteByMdId", id) >= 0;
    }

    @Override
    protected List<ImMetadataCol> selectListExe(String id) {
        return null;
    }

    public List<ImMetadataCol> select(ImMetadataCol imMetadataCol) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.select", imMetadataCol);
    }
}