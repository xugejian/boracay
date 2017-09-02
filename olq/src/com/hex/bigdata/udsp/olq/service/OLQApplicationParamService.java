package com.hex.bigdata.udsp.olq.service;

import com.hex.bigdata.udsp.olq.dao.OLQApplicationParamMapper;
import com.hex.bigdata.udsp.olq.model.OLQApplicationParam;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 联机查询应用参数服务
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/26
 * TIME:10:48
 */
@Service
public class OLQApplicationParamService extends BaseService {

    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(OLQApplicationParamService.class);

    /**
     * 联机查询应用参数DAO层服务
     */
    @Autowired
    private OLQApplicationParamMapper olqApplicationParamMapper;

    /**
     * 批量插入应用参数信息
     * @param paramList
     * @return
     */
    @Transactional
    public boolean insertList(List<OLQApplicationParam> paramList,String olqApplicationId){
        for (OLQApplicationParam item:paramList){
            if(null == item.getIsNeed() || "否".equals(item.getIsNeed())){
                item.setIsNeed("1");
            }else if("是".equals(item.getIsNeed())){
                item.setIsNeed("0");
            }
            item.setOlqAppId(olqApplicationId);
            this.insert(item);
        }
        return true;
    }

    /**
     *
     * @param param 插入参数
     * @return
     */
    @Transactional
    public boolean insert(OLQApplicationParam  param){
        if (StringUtils.isBlank(param.getIsNeed())){
            param.setIsNeed("1");
        }
        String pkId = Util.uuid();
        param.setPkId(pkId);
        return this.olqApplicationParamMapper.insert(pkId,param);
    }

    /**
     * 通过AppId查询参数
     * @param appId 应用id
     * @return
     */
    public List<OLQApplicationParam> getParamsByAppId(String appId){
        return this.olqApplicationParamMapper.selectList(appId);
    }

    /**
     * 根据应用id查询传入参数
     * @param appId
     * @return
     */
    public List<OLQApplicationParam> selectByAppId(String appId) {
        return this.olqApplicationParamMapper.selectList(appId);
    }

    /**
     * 根据appId删除参数信息
     * @param appId
     * @return
     */
    public boolean deleteByAppId(String appId) {
        return this.olqApplicationParamMapper.deleteList(appId);
    }
}
