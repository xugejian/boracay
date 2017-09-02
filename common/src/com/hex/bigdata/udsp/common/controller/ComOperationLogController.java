package com.hex.bigdata.udsp.common.controller;

import com.hex.bigdata.udsp.common.dto.ComOperationLogView;
import com.hex.bigdata.udsp.common.model.ComOperationLog;
import com.hex.bigdata.udsp.common.service.ComOperationLogService;
import com.hex.bigdata.udsp.common.util.JSONUtil;
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
 * Created by PC on 2017/3/9.
 */
@RequestMapping("/com/operation/log")
@Controller
public class ComOperationLogController extends BaseController {
    private static Logger logger = LogManager.getLogger(ComOperationLogController.class);
    @Autowired
    private ComOperationLogService comOperationLogService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(ComOperationLogView comOperationLogView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(comOperationLogView) + " page=" + JSONUtil.parseObj2JSON(page));
        List<ComOperationLog> list = null;
        try {
            list = comOperationLogService.select(comOperationLogView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(ComOperationLogView comOperationLogView) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(comOperationLogView));
        List<ComOperationLog> list = null;
        try {
            list = comOperationLogService.select(comOperationLogView);
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
        ComOperationLog comOperationLog = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                comOperationLog = comOperationLogService.select(pkId);
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
        return new MessageResult(status, message, comOperationLog);
    }

}
