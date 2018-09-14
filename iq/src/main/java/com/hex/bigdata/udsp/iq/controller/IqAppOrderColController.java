package com.hex.bigdata.udsp.iq.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.model.IqAppOrderCol;
import com.hex.bigdata.udsp.iq.service.IqAppOrderColService;
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
@RequestMapping("/iq/app/order/col")
@Controller
public class IqAppOrderColController extends BaseController {
    private static Logger logger = LogManager.getLogger(IqAppOrderColController.class);

    @Autowired
    private IqAppOrderColService iqAppOrderColService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(IqAppOrderCol iqAppOrderCol, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqAppOrderCol) + " page=" + JSONUtil.parseObj2JSON(page));
        List<IqAppOrderCol> list = null;
        try {
            list = iqAppOrderColService.select(iqAppOrderCol, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(IqAppOrderCol iqAppOrderCol) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqAppOrderCol));
        List<IqAppOrderCol> list = null;
        try {
            list = iqAppOrderColService.select(iqAppOrderCol);
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
        IqAppOrderCol iqAppOrderCol = null;
        try {
            iqAppOrderCol = iqAppOrderColService.select(pkId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new MessageResult(true, "", iqAppOrderCol);
    }

    @RequestMapping({"/selectByAppId/{appId}"})
    @ResponseBody
    public MessageResult selectByAppId(@PathVariable("appId") String appId) {
        logger.debug("select appId=" + appId);
        List<IqAppOrderCol> list = null;
        try {
            list = iqAppOrderColService.selectByAppId(appId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }
}
