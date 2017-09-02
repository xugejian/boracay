package com.hex.bigdata.udsp.rts.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rts.dto.RtsMatedataView;
import com.hex.bigdata.udsp.rts.model.RtsMatedata;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实时流-消元数据Dao层服务
 * Created by tomnic on 2017/2/28.
 */
@Repository
public class RtsMatedataMapper extends SyncMapper<RtsMatedata> {

    @Override
    protected boolean insertExe(RtsMatedata rtsMatedata) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.insert", rtsMatedata) == 1;
    }

    @Override
    protected boolean updateExe(RtsMatedata rtsMatedata) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.updateByPrimaryKey", rtsMatedata) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected RtsMatedata selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<RtsMatedata> selectListExe(String fkId) {
        return null;
    }

    /**
     * 分页多条件查询
     *
     * @param rtsMatedataView 查询参数
     * @param page            分页参数
     * @return
     */
    public List<RtsMatedataView> selectPage(RtsMatedataView rtsMatedataView, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.selectPage", rtsMatedataView, page.toPageBounds());
    }

    /**
     * 分页多条件查询-不分页
     *
     * @param rtsMatedataView
     * @return
     */
    public List<RtsMatedataView> select(RtsMatedataView rtsMatedataView) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.selectPage", rtsMatedataView);
    }

    /**
     * 根据名称查询数据源实体
     *
     * @param name 数据源名称
     * @return
     */
    public RtsMatedata selectByName(String name) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.selectByName", name);
    }

    public List<String> selectAppIdsByMdid(String pkId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.selectAppIdsByMdid", pkId);
    }

    public List<RtsMatedata> selectListByDsid(String dsId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper.selectListByDsid", dsId);
    }
}