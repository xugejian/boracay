package com.hex.bigdata.udsp.mc.dao;

import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.dto.McUserChartsView;
import com.hex.goframe.dao.BaseMapper;
import com.hex.goframe.model.GFUser;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.MapUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户维护统计
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/8/28
 * TIME:9:18
 */
@Repository
public class McUserChartsMapper extends BaseMapper {
    public List<McUserChartsView> selectUserStatis(McUserChartsView userChartsView, Page page) {
        List list = this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.selectUserStatis", userChartsView, page.toPageBounds());
        return list;
    }
    public List<McChartsView> userChart1Second(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.userChart1Second", mcChartsView);
    }

    public List<McChartsView> userChart1Minute(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.userChart1Minute", mcChartsView);
    }

    public List<McChartsView> userChart1Hour(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.userChart1Hour", mcChartsView);
    }

    public List<McChartsView> userChart1Day(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.userChart1Day", mcChartsView);
    }

    public List<McChartsView> statsServiceGroupByUserName(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.userChart3", mcChartsView);
    }

    public List<McChartsView> serviceTimeSecond(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.serviceTimeSecond", mcChartsView);
    }

    public List<McChartsView> serviceTimeMinute(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.serviceTimeMinute", mcChartsView);
    }

    public List<McChartsView> serviceTimeHour(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.serviceTimeHour", mcChartsView);
    }

    public List<McChartsView> serviceTimeDay(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McUserChartsMapper.serviceTimeDay", mcChartsView);
    }
}
