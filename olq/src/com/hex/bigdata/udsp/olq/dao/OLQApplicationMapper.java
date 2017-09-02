package com.hex.bigdata.udsp.olq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.olq.dto.OLQApplicationView;
import com.hex.bigdata.udsp.olq.model.OLQApplication;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OLQApplicationMapper extends SyncMapper<OLQApplication> {

    @Override
    protected boolean insertExe(OLQApplication olqApplication) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper.insert", olqApplication) == 1;
    }

    @Override
    protected boolean updateExe(OLQApplication olqApplication) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper.updateByPrimaryKey",olqApplication) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper.deleteByPrimaryKeyFake",id) == 1;
    }

    @Override
    protected OLQApplication selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper.selectByPrimaryKey",id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<OLQApplication> selectListExe(String id) {
        return null;
    }

    /**
     * 分页模糊查询
     *
     * @param olqApplicationView
     * @param page
     * @return
     */
    public List<OLQApplicationView> selectPage(OLQApplicationView olqApplicationView, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper.selectPage", olqApplicationView, page.toPageBounds());
    }

    /**
     * 根据应用名称进行精确查找
     * @param name
     * @return
     */
    public OLQApplication selectByName(String name) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper.selectByName",name);
    }

    /**
     * 查找所有存在的联机查询应用
     * @return
     */
    public List<OLQApplication> selectAll() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper.selectAll");
    }
}