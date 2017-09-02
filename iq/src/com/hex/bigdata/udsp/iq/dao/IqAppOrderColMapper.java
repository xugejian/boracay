package com.hex.bigdata.udsp.iq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.iq.model.IqAppOrderCol;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IqAppOrderColMapper extends SyncMapper<IqAppOrderCol> {

    protected boolean insertExe(IqAppOrderCol iqAppOrderCol) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.iq.dao.IqAppOrderColMapper.insert", iqAppOrderCol) == 1;
    }

    protected boolean updateExe(IqAppOrderCol iqAppOrderCol) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqAppOrderColMapper.updateByPrimaryKey", iqAppOrderCol) == 1;
    }

    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqAppOrderColMapper.deleteByPrimaryKey", id) == 1;
    }

    protected IqAppOrderCol selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.iq.dao.IqAppOrderColMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.iq.dao.IqAppOrderColMapper.deleteByAppId", id) >= 0;
    }

    protected List<IqAppOrderCol> selectListExe(String fkId) {
        IqAppOrderCol iqAppOrderCol = new IqAppOrderCol();
        iqAppOrderCol.setAppId(fkId);
        return this.select(iqAppOrderCol);
    }

    public List<IqAppOrderCol> select(IqAppOrderCol iqAppOrderCol, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqAppOrderColMapper.select", iqAppOrderCol,
                page.toPageBounds());
    }

    public List<IqAppOrderCol> select(IqAppOrderCol iqAppOrderCol) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqAppOrderColMapper.select", iqAppOrderCol);
    }

    public boolean deleteByAppId(String appId) {
        return this.deleteList(appId);
    }

    public List<IqAppOrderCol> selectByAppId(String appId) {
        return this.selectList(appId);
    }
}