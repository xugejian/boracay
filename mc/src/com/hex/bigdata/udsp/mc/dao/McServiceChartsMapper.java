package com.hex.bigdata.udsp.mc.dao;

import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.dto.McServiceChartsView;
import com.hex.bigdata.udsp.mc.dto.McUserChartsView;
import com.hex.goframe.dao.BaseMapper;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 服务维度统计
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/8/28
 * TIME:9:17
 */
@Repository
public class McServiceChartsMapper extends BaseMapper {

    public List<McUserChartsView> selectServiceStatis(McServiceChartsView serviceChartsView, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.selectServiceStatis",serviceChartsView,page.toPageBounds());
    }

    public List<McChartsView> serviceChart1Second(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.serviceChart1Second", mcChartsView);
    }

    public List<McChartsView> serviceChart1Minute(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.serviceChart1Minute", mcChartsView);
    }

    public List<McChartsView> serviceChart1Hour(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.serviceChart1Hour", mcChartsView);
    }

    public List<McChartsView> serviceChart1Day(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.serviceChart1Day", mcChartsView);
    }

    /**
     * 按照用户对服务调用进行统计
     * @param mcChartsView
     * @return
     */
    public List<McChartsView> statsServiceGroupByUserName(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.serviceChart3", mcChartsView);
    }

    public List<McChartsView> userTimeSecond(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.userTimeSecond", mcChartsView);
    }

    public List<McChartsView> userTimeMinute(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.userTimeMinute", mcChartsView);
    }

    public List<McChartsView> userTimeHour(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.userTimeHour", mcChartsView);
    }

    public List<McChartsView> userTimeDay(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper.userTimeDay", mcChartsView);
    }

}
