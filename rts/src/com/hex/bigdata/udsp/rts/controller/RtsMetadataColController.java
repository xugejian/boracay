package com.hex.bigdata.udsp.rts.controller;

import com.hex.bigdata.udsp.rts.model.RtsMetadataCol;
import com.hex.bigdata.udsp.rts.service.RtsMetadataColService;
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
 * 实时流-元数据列控制器
 * Created by tomnic on 2017/3/1.
 */
@RequestMapping("/rts/md/cols")
@Controller
public class RtsMetadataColController extends BaseController {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsMetadataColController.class);

    /**
     * 实时流-元数据列服务
     */
    @Autowired
    private RtsMetadataColService rtsMetadataColService;

    /**
     * 根据外键查询实时流数据源实体
     * @param mdId 记录唯一性外键
     * @return
     */
    @RequestMapping({"/page/{mdId}"})
    @ResponseBody
    public MessageResult select(@PathVariable("mdId") String mdId, Page page) {
        RtsMetadataCol rtsMetadataColView = new RtsMetadataCol();
        rtsMetadataColView.setMdId(mdId);
        List<RtsMetadataCol> rtsMetadataCols = rtsMetadataColService.select(rtsMetadataColView, page);
        return new PageListResult(rtsMetadataCols, page);
    }


    @RequestMapping({"/checkBeforeRemoveCols/{pkId}"})
    @ResponseBody
    public MessageResult select(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "";
        if(rtsMetadataColService.hasUsed(pkId)){
            status = false;
            message = "该元数据已被引用！";
        }
        return new MessageResult(status,message);
    }
}
