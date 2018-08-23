package com.hex.bigdata.udsp.rts.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rts.model.RtsMetadataCol;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 实时流-元数据列配置Dao层服务
 * Created by tomnic on 2017/2/28.
 */
@Repository
public class RtsMetadataColMapper extends SyncMapper<RtsMetadataCol> {

    @Override
    protected boolean insertExe(RtsMetadataCol rtsMetadataCol) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.insert", rtsMetadataCol) == 1;
    }

    @Override
    protected boolean updateExe(RtsMetadataCol rtsMetadataCol) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.updateByPrimaryKey", rtsMetadataCol) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.logicDelete", id) == 1;
    }

    @Override
    protected RtsMetadataCol selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.deleteByMdId",id)>=0;
    }

    @Override
    protected List<RtsMetadataCol> selectListExe(String fkId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.selectByMdId",fkId);
    }

    /**
     * 分页查询
     * @param rtsMetadataColView 查询参数
     * @param page 分页参数
     * @return
     */
    public List<RtsMetadataCol> select(RtsMetadataCol rtsMetadataColView, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.selectPage",rtsMetadataColView,page.toPageBounds());
    }

    /**
     * 查询所有
     * @param rtsMetadataColView 查询参数
     * @return
     */
    public List<RtsMetadataCol> select(RtsMetadataCol rtsMetadataColView) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.selectPage",rtsMetadataColView);
    }

    /**
     * 根据元数据Id删除元数据累
     * @param mdId
     * @return
     */
    public boolean deleteByMdId(String mdId) {
        return this.deleteListExe(mdId);
    }

    public List<RtsMetadataCol> selectByMdId(String mdId) {
        return this.selectList(mdId);
    }

    public List<RtsMetadataCol> selectByProducerPkid(String appId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper.selectByProducerPkid",appId);
    }
}