package com.hex.bigdata.udsp.rts.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rts.dto.RtsMetadataView;
import com.hex.bigdata.udsp.rts.model.RtsMetadata;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实时流-消元数据Dao层服务
 * Created by tomnic on 2017/2/28.
 */
@Repository
public class RtsMetadataMapper extends SyncMapper<RtsMetadata> {

    @Override
    protected boolean insertExe(RtsMetadata rtsMetadata) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.insert", rtsMetadata) == 1;
    }

    @Override
    protected boolean updateExe(RtsMetadata rtsMetadata) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.updateByPrimaryKey", rtsMetadata) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected RtsMetadata selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<RtsMetadata> selectListExe(String fkId) {
        return null;
    }

    /**
     * 分页多条件查询
     *
     * @param rtsMetadataView 查询参数
     * @param page            分页参数
     * @return
     */
    public List<RtsMetadataView> selectPage(RtsMetadataView rtsMetadataView, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.selectPage", rtsMetadataView, page.toPageBounds());
    }

    /**
     * 分页多条件查询-不分页
     *
     * @param rtsMetadataView
     * @return
     */
    public List<RtsMetadataView> select(RtsMetadataView rtsMetadataView) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.selectPage", rtsMetadataView);
    }

    /**
     * 根据名称查询数据源实体
     *
     * @param name 数据源名称
     * @return
     */
    public RtsMetadata selectByName(String name) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.selectByName", name);
    }

    public List<String> selectAppIdsByMdid(String pkId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.selectAppIdsByMdid", pkId);
    }

    public List<RtsMetadata> selectListByDsid(String dsId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataMapper.selectListByDsid", dsId);
    }
}