package com.hex.bigdata.udsp.mc.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.dto.McUserChartsView;
import com.hex.bigdata.udsp.mc.service.McUserChartsService;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/8/28
 * TIME:11:07
 */
@RequestMapping("/mc/stats/user/charts")
@Controller
public class McUserChartsController {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(McUserChartsController.class);

    private static final FastDateFormat ymdhmsFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static final FastDateFormat ymdhmFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

    @Autowired
    private McUserChartsService mcUserChartsService;

    @RequestMapping({"/selectUserStatis"})
    @ResponseBody
    public PageListResult selectUserStatis(McUserChartsView mcUserChartsView,Page page) {
        List<McUserChartsView> list = mcUserChartsService.selectUserStatis(mcUserChartsView, page);
        logger.debug("selectPage search=" + JSONUtil.parseObj2JSON(mcUserChartsView) + " page=" + JSONUtil.parseObj2JSON(page));
        return new PageListResult(list, page);
    }

    @RequestMapping({"/chart1Minute"})
    @ResponseBody
    public PageListResult chart1Minute(String datetime, String interval, String userName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "userName =" + userName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcUserChartsService.userChart1Minute(dtStartStr, dtEndStr,"", userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart1Hour"})
    @ResponseBody
    public PageListResult chart1Hour(String datetime, String interval, String userName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "userName =" + userName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcUserChartsService.userChart1Hour(dtStartStr, dtEndStr, "", userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart1Day"})
    @ResponseBody
    public PageListResult chart1Day(String datetime, String interval, String userName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval +"userName =" + userName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcUserChartsService.userChart1Day(dtStartStr, dtEndStr, "", userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/userServiceNameChar"})
    @ResponseBody
    public PageListResult userServiceNameChar(String datetime, String interval, String userName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcUserChartsService.statsServiceGroupByUserName(dtStartStr, dtEndStr, userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/serviceTimeMinute"})
    @ResponseBody
    public PageListResult serviceTimeMinute(String datetime, String interval, String userName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "userName = "+userName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcUserChartsService.serviceTimeMinute(dtStartStr, dtEndStr, "", userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/serviceTimeHour"})
    @ResponseBody
    public PageListResult serviceTimeHour(String datetime, String interval, String userName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "userName = "+userName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcUserChartsService.serviceTimeHour(dtStartStr, dtEndStr, "", userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/serviceTimeDay"})
    @ResponseBody
    public PageListResult serviceTimeDay(String datetime, String interval, String userName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "userName = "+userName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcUserChartsService.serviceTimeDay(dtStartStr, dtEndStr, "", userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }


    private Date getDate(String datetime) throws ParseException {
        Date dtEnd = null;
        if (StringUtils.isNotBlank(datetime)) {
            dtEnd = ymdhmFormat.parse(datetime);
        } else {
            dtEnd = new Date();
        }
        return dtEnd;
    }
}
