package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.service.BatchJobService;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.goframe.model.MessageResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-22.
 */
@RequestMapping("/im/job/batch")
@Controller
public class BatchJobController {
    private static Logger logger = LogManager.getLogger(BatchJobController.class);

    @Autowired
    private BatchJobService batchJobService;

    /**
     * 开始构建
     *
     * @param pkId 模型的主键
     * @return
     */
    @RequestMapping(value = "start/{pkId}")
    @ResponseBody
    public MessageResult start(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "批量作业构建成功！";
        Model model = null; // TODO ...
        try {
            batchJobService.start(model);
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
     * 停止构建
     *
     * @param id 批量作业的唯一键
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "stop/{id}")
    public MessageResult stop(@PathVariable("id") String id) {
        boolean status = true;
        String message = "批量作业停止成功！";
        try {
            batchJobService.stop(id);
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
