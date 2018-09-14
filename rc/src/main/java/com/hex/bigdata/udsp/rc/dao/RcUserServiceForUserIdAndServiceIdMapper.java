package com.hex.bigdata.udsp.rc.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RcUserServiceForUserIdAndServiceIdMapper extends SyncMapper<RcUserService> {

    @Override
    protected boolean insertExe(RcUserService rcUserService) {
        return true;
    }

    @Override
    protected boolean updateExe(RcUserService rcUserService) {
        return true;
    }

    /**
     * 这里的id = userId+"|"+serviceId
     *
     * @param id
     * @return
     */
    @Override
    protected boolean deleteExe(String id) {
        return true;
    }

    /**
     * 通过userId和serviceId查询
     * <p>
     * 这里的id = userId+"|"+serviceId
     *
     * @param id
     * @return
     */
    @Override
    protected RcUserService selectExe(String id) {
        String[] strings = id.split("\\|");
        String userId = strings[0];
        String serviceId = strings[1];
        RcUserService rcUserService = new RcUserService();
        rcUserService.setUserId(userId);
        rcUserService.setServiceId(serviceId);
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectByUserIdAndServiceId", rcUserService);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return true;
    }

    @Override
    protected List<RcUserService> selectListExe(String id) {
        return null;
    }

}