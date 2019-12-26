package com.hex.bigdata.udsp.rc.dao;


import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rc.model.RcService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RcServiceForServiceNameMapper extends SyncMapper<RcService> {

    @Override
    protected boolean insertExe(RcService rcService) {
        return true;
    }

    @Override
    protected boolean updateExe(RcService rcService) {
        return true;
    }

    /**
     * 这里的id = srviceName
     *
     * @param id
     * @return
     */
    @Override
    protected boolean deleteExe(String id) {
        return true;
    }

    /**
     * 通过srviceName查询
     * <p>
     * 这里的id = srviceName
     *
     * @param id
     * @return
     */
    @Override
    protected RcService selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectByServiceName", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<RcService> selectListExe(String id) {
        return null;
    }

}