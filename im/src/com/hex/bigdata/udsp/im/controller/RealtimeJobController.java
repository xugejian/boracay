package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.bigdata.udsp.im.service.RealtimeJobService;
import com.hex.goframe.model.MessageResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JunjieM on 2017-9-22.
 */
@RequestMapping("/im/job/realtime")
@Controller
public class RealtimeJobController {
    private static Logger logger = LogManager.getLogger(RealtimeJobController.class);
    @Autowired
    private RealtimeJobService realtimeJobService;

    @Autowired
    private ImModelService imModelService;

    /**
     * 启动
     *
     * @param pkId 模型的主键
     * @return
     */
    @RequestMapping(value = "start/{pkId}")
    @ResponseBody
    public MessageResult start(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "实时作业启动成功！";
        try {
            Model model = imModelService.getModel(pkId,null);
            realtimeJobService.start(model);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    /**
     * 停止
     *
     * @param id 实时作业的唯一键
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "stop/{id}")
    public MessageResult stop(@PathVariable("id") String id) {
        boolean status = true;
        String message = "实时作业停止成功！";
        try {
            realtimeJobService.stop(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message);
    }
}
