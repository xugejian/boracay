package com.hex.bigdata.udsp.common.dao;

import com.hex.bigdata.udsp.common.dao.base.AsyncInsertMapper;
import com.hex.bigdata.udsp.common.dto.ComOperationLogView;
import com.hex.bigdata.udsp.common.model.ComOperationLog;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComOperationLogMapper extends AsyncInsertMapper<ComOperationLog> {
    /**
     * 后台起线程异步插入
     */
    @Override
    protected boolean insertExe(ComOperationLog comOperationLog) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.common.dao.ComOperationLogMapper.insert", comOperationLog) == 1;
    }

    @Override
    protected ComOperationLog selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.common.dao.ComOperationLogMapper.selectByPrimaryKey", id);
    }

    public List<ComOperationLog> select(ComOperationLogView comOperationLogView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.common.dao.ComOperationLogMapper.select", comOperationLogView,
                page.toPageBounds());
    }

    public List<ComOperationLog> select(ComOperationLogView comOperationLogView) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.common.dao.ComOperationLogMapper.select", comOperationLogView);
    }
}