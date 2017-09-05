package com.hex.bigdata.udsp.im.controller;


import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.model.ImMetadata;
import com.hex.bigdata.udsp.im.model.ImMetadataDto;
import com.hex.bigdata.udsp.im.model.ImMetadataView;
import com.hex.bigdata.udsp.im.service.ImMetadataService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-4.
 */
@RequestMapping("/im/md")
@Controller
public class ImMetadataController {
    private static Logger logger = LogManager.getLogger(ImMetadataController.class);
    @Autowired
    private ImMetadataService imMetadataService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(ImMetadataView imMetadataView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(imMetadataView) + " page=" + JSONUtil.parseObj2JSON(page));
        List<ImMetadata> list = null;
        try {
            list = imMetadataService.select(imMetadataView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody ImMetadataDto imMetadataDto) {
        boolean status = true;
        String message = "添加成功";
        if (imMetadataDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(imMetadataService.insert(imMetadataDto))) {
                    status = false;
                    message = "添加失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e;
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.info(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult delete(@RequestBody ImMetadata[] imMetadatas) {
        boolean status = true;
        String message = "删除成功";
        if (imMetadatas.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            if ( !imMetadataService.delete(imMetadatas)) {
                status = false;
                message = "删除失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e;
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.info(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/checkName/{name}"})
    @ResponseBody
    public MessageResult checkName(@PathVariable("name") String name) {
        boolean status = true;
        String message = "检查完成，名称存在！";
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!imMetadataService.checkName(name)) {
                    status = false;
                    message = "检查完成，名称不存在！";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message);
    }
}
