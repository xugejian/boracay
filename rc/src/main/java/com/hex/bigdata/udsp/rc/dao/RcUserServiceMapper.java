package com.hex.bigdata.udsp.rc.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceView;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.goframe.model.GFUser;
import com.hex.goframe.model.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RcUserServiceMapper extends SyncMapper<RcUserService> {

    @Override
    protected boolean insertExe(RcUserService rcUserService) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.insert", rcUserService) == 1;
    }

    @Override
    protected boolean updateExe(RcUserService rcUserService) {
        boolean returnFlg = this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.updateByPrimaryKey", rcUserService) == 1;
        return returnFlg;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected RcUserService selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectByPrimaryKey", id);
    }


    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<RcUserService> selectListExe(String fkId) {
        return null;
    }

    /**
     * @param rcUserServiceView
     * @param page
     * @return
     */
    public List<RcUserServiceView> selectPage(RcUserServiceView rcUserServiceView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectPage", rcUserServiceView,
                page.toPageBounds());
    }

    /**
     * 查询用户Id和服务Id之间的关系
     *
     * @param userId
     * @param serviceId
     */
    public List<RcUserService> selectRelation(String userId, String serviceId) {
        RcUserService rcUserService = new RcUserService();
        rcUserService.setUserId(userId);
        rcUserService.setServiceId(serviceId);
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectRelation", rcUserService);
    }

    public RcUserServiceView selectFullResultMap(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectFullResultMap", id);
    }

    /**
     * 通过条件分页查询与服务无关的用户信息
     * 服务Id、用户姓名、分页参数
     *
     * @param rcUserServiceView
     * @param page
     * @return
     */
    public List<GFUser> selectNotRelationUsers(RcUserServiceView rcUserServiceView, Page page) {
        //serviceIds为必输项
        if (StringUtils.isBlank(rcUserServiceView.getServiceIds())) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userName", rcUserServiceView.getUserName());
        params.put("serviceIds", rcUserServiceView.getServiceArray());
        params.put("userId", rcUserServiceView.getUserId());
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectNotRelationUsers", params,
                page.toPageBounds());
    }

    /**
     * 通过条件查询全部与服务有关的用户信息
     * 服务Id、用户姓名、分页参数
     *
     * @param rcUserServiceView
     * @return
     */
    public List<GFUser> selectRelationUsers(RcUserServiceView rcUserServiceView) {
        //serviceIds为必输项
        if (StringUtils.isBlank(rcUserServiceView.getServiceIds())) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("serviceIds", rcUserServiceView.getServiceArray());

        if (StringUtils.isNotBlank(rcUserServiceView.getUserName())) {
            params.put("userName", rcUserServiceView.getUserName());
        }
        if (StringUtils.isNotBlank(rcUserServiceView.getUserName())){
            params.put("userId", rcUserServiceView.getUserId());
        }
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectRelationUsers", params);
    }

    /**
     * 根据服务注册外键批量删除数据
     *
     * @param serviceId
     * @return
     */
    public boolean deleteServiceId(String serviceId) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.deleteByServieId", serviceId) == 1;
    }

    /**
     * 根据服务名称，查询对应服务授权信息是否建立
     *
     * @param serviceId
     * @return
     */
    public List selectRelationByServiceId(String serviceId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectRelationByServiceId", serviceId);
    }

    /**
     * 根据用户id查找对应的服务
     * @param userId
     * @return
     */
    public List<RcUserServiceView> selectServicesByUserId(String userId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper.selectServicesByUserId", userId);
    }
}