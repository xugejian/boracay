package com.hex.bigdata.udsp.iq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.iq.model.IqAppReturnCol;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IqAppReturnColMapper extends SyncMapper<IqAppReturnCol> {

    protected boolean insertExe(IqAppReturnCol iqAppReturnCol) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.iq.dao.IqAppReturnColMapper.insert", iqAppReturnCol) == 1;
    }

    protected boolean updateExe(IqAppReturnCol iqAppReturnCol) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqAppReturnColMapper.updateByPrimaryKey", iqAppReturnCol) == 1;
    }

    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqAppReturnColMapper.deleteByPrimaryKey", id) == 1;
    }

    protected IqAppReturnCol selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.iq.dao.IqAppReturnColMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.iq.dao.IqAppReturnColMapper.deleteByAppId", id) >= 0;
    }

    protected List<IqAppReturnCol> selectListExe(String fkId) {
        IqAppReturnCol iqAppReturnCol = new IqAppReturnCol();
        iqAppReturnCol.setAppId(fkId);
        return this.select(iqAppReturnCol);
    }

    public List<IqAppReturnCol> select(IqAppReturnCol iqAppReturnCol, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqAppReturnColMapper.select", iqAppReturnCol,
                page.toPageBounds());
    }

    public List<IqAppReturnCol> select(IqAppReturnCol iqAppReturnCol) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqAppReturnColMapper.select", iqAppReturnCol);
    }

    public boolean deleteByAppId(String appId) {
        return this.deleteList(appId);
    }

    public List<IqAppReturnCol> selectByAppId(String appId) {
        return this.selectList(appId);
    }
}