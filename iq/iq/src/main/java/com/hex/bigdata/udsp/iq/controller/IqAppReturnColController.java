package com.hex.bigdata.udsp.iq.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.model.IqAppReturnCol;
import com.hex.bigdata.udsp.iq.service.IqAppReturnColService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by tomnic on 2017/2/20.
 */
@RequestMapping("/iq/app/return/col")
@Controller
public class IqAppReturnColController extends BaseController {
    private static Logger logger = LogManager.getLogger(IqAppReturnColController.class);

    @Autowired
    private IqAppReturnColService iqAppReturnColService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(IqAppReturnCol iqAppReturnCol, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqAppReturnCol) + " page=" + JSONUtil.parseObj2JSON(page));
        List<IqAppReturnCol> list = null;
        try {
            list = iqAppReturnColService.select(iqAppReturnCol, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(IqAppReturnCol iqAppReturnCol) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqAppReturnCol));
        List<IqAppReturnCol> list = null;
        try {
            list = iqAppReturnColService.select(iqAppReturnCol);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult selectByPkId(@PathVariable("pkId") String pkId) {
        logger.debug("select pkId=" + pkId);
        IqAppReturnCol iqAppReturnCol = null;
        try {
            iqAppReturnCol = iqAppReturnColService.select(pkId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new MessageResult(true, "", iqAppReturnCol);
    }

    @RequestMapping({"/selectByAppId/{appId}"})
    @ResponseBody
    public MessageResult selectByAppId(@PathVariable("appId") String appId) {
        logger.debug("select appId=" + appId);
        List<IqAppReturnCol> list = null;
        try {
            list = iqAppReturnColService.selectByAppId(appId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }
}
