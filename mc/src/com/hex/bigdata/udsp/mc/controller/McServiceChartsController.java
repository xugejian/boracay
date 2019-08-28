package com.hex.bigdata.udsp.mc.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.dto.McServiceChartsView;
import com.hex.bigdata.udsp.mc.dto.McUserChartsView;
import com.hex.bigdata.udsp.mc.service.McServiceChartsService;
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
@RequestMapping("/mc/stats/service/charts")
@Controller
public class McServiceChartsController {

    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(McServiceChartsController.class);

    private static final FastDateFormat ymdhmsFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static final FastDateFormat ymdhmFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");


    @Autowired
    private McServiceChartsService mcServiceChartsService;

    @RequestMapping({"/selectServiceStatis"})
    @ResponseBody
    public PageListResult selectServiceStatis(McServiceChartsView serviceChartsView,Page page) {
        List<McUserChartsView> list = mcServiceChartsService.selectServiceStatis(serviceChartsView, page);
        logger.debug("selectPage search=" + JSONUtil.parseObj2JSON(serviceChartsView) + " page=" + JSONUtil.parseObj2JSON(page));
        return new PageListResult(list, page);
    }

    @RequestMapping({"/chart1Minute"})
    @ResponseBody
    public PageListResult chart1Minute(String datetime, String interval, String serviceName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "serviceName = "+serviceName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcServiceChartsService.serviceChart1Minute(dtStartStr, dtEndStr, serviceName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart1Hour"})
    @ResponseBody
    public PageListResult chart1Hour(String datetime, String interval, String serviceName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "serviceName = "+serviceName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcServiceChartsService.serviceChart1Hour(dtStartStr, dtEndStr, serviceName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart1Day"})
    @ResponseBody
    public PageListResult chart1Day(String datetime, String interval, String serviceName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "serviceName = "+serviceName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcServiceChartsService.serviceChart1Day(dtStartStr, dtEndStr, serviceName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/serviceUserNameChar"})
    @ResponseBody
    public PageListResult serviceUserNameChar(String datetime, String interval, String serviceName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcServiceChartsService.statsSingleServiceGroupByUserName(dtStartStr, dtEndStr, serviceName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/userTimeMinute"})
    @ResponseBody
    public PageListResult userTimeMinute(String datetime, String interval, String serviceName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "serviceName = "+serviceName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcServiceChartsService.userTimeMinute(dtStartStr, dtEndStr, serviceName, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/userTimeHour"})
    @ResponseBody
    public PageListResult userTimeHour(String datetime, String interval, String serviceName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "serviceName = "+serviceName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcServiceChartsService.userTimeHour(dtStartStr, dtEndStr, serviceName, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/userTimeDay"})
    @ResponseBody
    public PageListResult userTimeDay(String datetime, String interval, String serviceName) {
        logger.debug("select datetime=" + datetime + " interval=" + interval + "serviceName = "+serviceName);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcServiceChartsService.userTimeDay(dtStartStr, dtEndStr, serviceName, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    private Date getDate(String datetime) throws ParseException {
        if (StringUtils.isNotBlank(datetime)) {
            return ymdhmFormat.parse(datetime);
        } else {
            return new Date();
        }
    }

}
