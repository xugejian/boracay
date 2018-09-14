package com.hex.bigdata.udsp.iq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.iq.dto.IqApplicationView;
import com.hex.bigdata.udsp.iq.model.IqApplication;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IqApplicationMapper extends SyncMapper<IqApplication> {

    protected boolean insertExe(IqApplication iqApplication) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.insert", iqApplication) == 1;
    }

    protected boolean updateExe(IqApplication iqApplication) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.updateByPrimaryKey", iqApplication) == 1;
    }

    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    protected IqApplication selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    protected List<IqApplication> selectListExe(String id) {
        return null;
    }

    public List<IqApplication> selectAll() {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.selectAll");
    }

    public List<IqApplicationView> select(IqApplicationView iqApplicationView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.select", iqApplicationView,
                page.toPageBounds());
    }

    public List<IqApplicationView> select(IqApplicationView iqApplicationView) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.select", iqApplicationView);
    }

    public IqApplication selectByName(String name) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.iq.dao.IqApplicationMapper.selectByName", name);
    }
}
