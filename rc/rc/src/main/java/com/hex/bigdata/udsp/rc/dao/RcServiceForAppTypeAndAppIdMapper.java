package com.hex.bigdata.udsp.rc.dao;


import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rc.model.RcService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RcServiceForAppTypeAndAppIdMapper extends SyncMapper<RcService> {

    @Override
    protected boolean insertExe(RcService rcService) {
        return true;
    }

    @Override
    protected boolean updateExe(RcService rcService) {
        return true;
    }

    /**
     * 这里的id = appType+"|"+appId
     *
     * @param id
     * @return
     */
    @Override
    protected boolean deleteExe(String id) {
        return true;
    }

    /**
     * 通过appType和appId查询
     * <p>
     * 这里的id = appType+"|"+appId
     *
     * @param id
     * @return
     */
    @Override
    protected RcService selectExe(String id) {
        String[] strings = id.split("\\|");
        String appType = strings[0];
        String appId = strings[1];
        RcService rcService = new RcService();
        rcService.setType(appType);
        rcService.setAppId(appId);
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectByAppTypeAndAppId", rcService);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return true;
    }

    @Override
    protected List<RcService> selectListExe(String id) {
        return null;
    }

}