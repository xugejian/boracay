package com.hex.bigdata.udsp.iq.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.dto.IqApplicationPropsView;
import com.hex.bigdata.udsp.iq.dto.IqApplicationView;
import com.hex.bigdata.udsp.iq.model.IqApplication;
import com.hex.bigdata.udsp.iq.service.IqApplicationService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import com.hex.goframe.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-3-1.
 */
@RequestMapping("/iq/app")
@Controller
public class IqApplicationController extends BaseController {
    private static Logger logger = LogManager.getLogger(IqApplicationController.class);
    @Autowired
    private IqApplicationService iqApplicationService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(IqApplicationView iqApplicationView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqApplicationView) + " page=" + JSONUtil.parseObj2JSON(page));
        List<IqApplicationView> list = null;
        try {
            list = iqApplicationService.select(iqApplicationView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(IqApplicationView iqApplicationView) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqApplicationView));
        List<IqApplicationView> list = null;
        try {
            list = iqApplicationService.select(iqApplicationView);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody IqApplicationPropsView iqApplicationPropsView) {
        boolean status = true;
        String message = "添加成功";
        if (iqApplicationPropsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(iqApplicationService.insert(iqApplicationPropsView))) {
                    status = false;
                    message = "添加失败";
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
            logger.warn(message);
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
                if (!iqApplicationService.checkName(name)) {
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

    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult selectByPkId(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        IqApplication iqApplication = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                iqApplication = iqApplicationService.select(pkId);
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
        return new MessageResult(status, message, iqApplication);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult delete(@RequestBody IqApplication[] iqApplications) {
        boolean status = true;
        String message = "删除成功";
        if (iqApplications.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            if (!iqApplicationService.delete(iqApplications)) {
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
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/update"})
    @ResponseBody
    public MessageResult update(@RequestBody IqApplicationPropsView iqApplicationPropsView) {
        boolean status = true;
        String message = "更新成功";
        if (iqApplicationPropsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!iqApplicationService.update(iqApplicationPropsView)) {
                    status = false;
                    message = "更新失败";
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
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/selectByName/{name}"})
    @ResponseBody
    public MessageResult selectByName(@PathVariable("name") String name) {
        boolean status = true;
        String message = "查询成功";
        IqApplication iqApplication = null;
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                iqApplication = iqApplicationService.selectByName(name);
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
        return new MessageResult(status, message, iqApplication);
    }


    @RequestMapping("upload")
    @ResponseBody
    public MessageResult upload(MultipartFile excelFile){
        boolean status = true;
        String message = "上传成功";

        //判断结尾是否为xl或者xlsx
        if (((CommonsMultipartFile)excelFile).getFileItem().getName().endsWith(".xls")
                || ((CommonsMultipartFile)excelFile).getFileItem().getName().endsWith(".xlsx")) {
            //将文件放到项目上传文件目录中
            String uploadFilePath = FileUtil.uploadFile(FileUtil
                    .getRealUploadPath("EXCEL_UPLOAD"), excelFile);
            Map<String,String> result = iqApplicationService.uploadExcel(uploadFilePath);
            if("false".equals(result.get("status"))){
                status = false;
                message = result.get("message");
            }
        }else{
            status = false;
            message = "请上传正确格式的文件！";
        }
        return new MessageResult(status,message);
    }

    @ResponseBody
    @RequestMapping("/download")
    public String createExcel(@RequestBody IqApplication[] iqApplications){
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = iqApplicationService.createExcel(iqApplications);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
