package com.hex.bigdata.udsp.iq.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.model.IqAppQueryCol;
import com.hex.bigdata.udsp.iq.service.IqAppQueryColService;
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
@RequestMapping("/iq/app/query/col")
@Controller
public class IqAppQueryColController extends BaseController {
    private static Logger logger = LogManager.getLogger(IqAppQueryColController.class);

    @Autowired
    private IqAppQueryColService iqAppQueryColService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(IqAppQueryCol iqAppQueryCol, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqAppQueryCol) + " page=" + JSONUtil.parseObj2JSON(page));
        List<IqAppQueryCol> list = null;
        try {
            list = iqAppQueryColService.select(iqAppQueryCol, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(IqAppQueryCol iqAppQueryCol) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqAppQueryCol));
        List<IqAppQueryCol> list = null;
        try {
            list = iqAppQueryColService.select(iqAppQueryCol);
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
        IqAppQueryCol iqAppQueryCol = null;
        try {
            iqAppQueryCol = iqAppQueryColService.select(pkId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new MessageResult(true, "", iqAppQueryCol);
    }

    @RequestMapping({"/selectByAppId/{appId}"})
    @ResponseBody
    public MessageResult selectByAppId(@PathVariable("appId") String appId) {
        logger.debug("select appId=" + appId);
        List<IqAppQueryCol> list = null;
        try {
            list = iqAppQueryColService.selectByAppId(appId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }
}
