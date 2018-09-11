package com.hex.bigdata.udsp.mm.controller;

import com.hex.bigdata.udsp.mm.model.MmAppReturnParam;
import com.hex.bigdata.udsp.mm.service.MmAppReturnParamService;
import com.hex.goframe.model.MessageResult;
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
 * Created by JunjieM on 2018-9-11.
 */
@RequestMapping("/mm/app/return/col/")
@Controller
public class MmAppReturnParamController {

    private static Logger logger = LogManager.getLogger(MmAppReturnParamController.class);

    @Autowired
    private MmAppReturnParamService returnParamService;

    @RequestMapping({"/selectByAppId/{appId}"})
    @ResponseBody
    public MessageResult selectByAppId(@PathVariable("appId") String appId) {
        logger.debug("select appId=" + appId);
        List<MmAppReturnParam> list = null;
        try {
            list = returnParamService.selectByFkId(appId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }
}
