package com.hex.bigdata.udsp.iq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.iq.model.IqAppQueryCol;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IqAppQueryColMapper extends SyncMapper<IqAppQueryCol> {

    protected boolean insertExe(IqAppQueryCol iqAppQueryCol) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.iq.dao.IqAppQueryColMapper.insert", iqAppQueryCol) == 1;
    }

    protected boolean updateExe(IqAppQueryCol iqAppQueryCol) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqAppQueryColMapper.updateByPrimaryKey", iqAppQueryCol) == 1;
    }

    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqAppQueryColMapper.deleteByPrimaryKey", id) == 1;
    }

    protected IqAppQueryCol selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.iq.dao.IqAppQueryColMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.iq.dao.IqAppQueryColMapper.deleteByAppId", id) >= 0;
    }

    protected List<IqAppQueryCol> selectListExe(String fkId) {
        IqAppQueryCol iqAppQueryCol = new IqAppQueryCol();
        iqAppQueryCol.setAppId(fkId);
        return this.select(iqAppQueryCol);
    }

    public List<IqAppQueryCol> select(IqAppQueryCol iqAppQueryCol, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqAppQueryColMapper.select", iqAppQueryCol,
                page.toPageBounds());
    }

    public List<IqAppQueryCol> select(IqAppQueryCol iqAppQueryCol) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqAppQueryColMapper.select", iqAppQueryCol);
    }

    public boolean deleteByAppId(String appId) {
        return this.deleteList(appId);
    }

    public List<IqAppQueryCol> selectByAppId(String appId) {
        return this.selectList(appId);
    }
}