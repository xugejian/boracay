package com.hex.bigdata.udsp.mc.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.mc.dto.McConsumeLogView;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by junjiem on 2017-3-1.
 */
@RequestMapping("/mc/consume/log")
@Controller
public class McConsumeLogController extends BaseController {
    private static Logger logger = LogManager.getLogger(McConsumeLogController.class);

    @Autowired
    private McConsumeLogService mcConsumeLogService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(McConsumeLogView mcConsumeLogView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(mcConsumeLogView) + " page=" + JSONUtil.parseObj2JSON(page));
        List<McConsumeLog> list = null;
        try {
            list = mcConsumeLogService.select(mcConsumeLogView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(McConsumeLogView mcConsumeLogView) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(mcConsumeLogView));
        List<McConsumeLog> list = null;
        try {
            list = mcConsumeLogService.select(mcConsumeLogView);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult selectByPkId(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        McConsumeLog mcConsumeLog = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                mcConsumeLog = mcConsumeLogService.select(pkId);
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e;
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message, mcConsumeLog);
    }
}
