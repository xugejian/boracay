package com.hex.bigdata.udsp.rts.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 实时流-元数据列配置Dao层服务
 * Created by tomnic on 2017/2/28.
 */
@Repository
public class RtsMatedataColMapper extends SyncMapper<RtsMatedataCol> {

    @Override
    protected boolean insertExe(RtsMatedataCol rtsMatedataCol) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.insert", rtsMatedataCol) == 1;
    }

    @Override
    protected boolean updateExe(RtsMatedataCol rtsMatedataCol) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.updateByPrimaryKey", rtsMatedataCol) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.logicDelete", id) == 1;
    }

    @Override
    protected RtsMatedataCol selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.deleteByMdId",id)>=0;
    }

    @Override
    protected List<RtsMatedataCol> selectListExe(String fkId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.selectByMdId",fkId);
    }

    /**
     * 分页查询
     * @param rtsMetadataColView 查询参数
     * @param page 分页参数
     * @return
     */
    public List<RtsMatedataCol> select(RtsMatedataCol rtsMetadataColView, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.selectPage",rtsMetadataColView,page.toPageBounds());
    }

    /**
     * 查询所有
     * @param rtsMetadataColView 查询参数
     * @return
     */
    public List<RtsMatedataCol> select(RtsMatedataCol rtsMetadataColView) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.selectPage",rtsMetadataColView);
    }

    /**
     * 根据元数据Id删除元数据累
     * @param mdId
     * @return
     */
    public boolean deleteByMdId(String mdId) {
        return this.deleteListExe(mdId);
    }

    public List<RtsMatedataCol> selectByMdId(String mdId) {
        return this.selectList(mdId);
    }

    public List<RtsMatedataCol> selectByProducerPkid(String appId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper.selectByProducerPkid",appId);
    }
}