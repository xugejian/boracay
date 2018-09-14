package com.hex.bigdata.udsp.rts.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.rts.dto.RtsProducerProsView;
import com.hex.bigdata.udsp.rts.dto.RtsProducerView;
import com.hex.bigdata.udsp.rts.model.RtsMetadataCol;
import com.hex.bigdata.udsp.rts.model.RtsProducer;
import com.hex.bigdata.udsp.rts.service.RtsProducerService;
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
 * Created by tomnic on 2017/3/1.
 */
@RequestMapping("/rts/producer")
@Controller
public class RtsProducerController extends BaseController {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsProducerController.class);

    /**
     * 实时流数据源服务
     */
    @Autowired
    private RtsProducerService rtsProducerService;

    /**
     * 分页多条件查询
     *
     * @param rtsProConfigView 查询参数
     * @param page              分页参数
     * @return
     */
    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult queryRtsDatasources(RtsProducerView rtsProConfigView, Page page) {
        List<RtsProducerView> list = rtsProducerService.select(rtsProConfigView, page);
        logger.debug("selectPage search=" + JSONUtil.parseObj2JSON(rtsProConfigView) + " page=" + JSONUtil.parseObj2JSON(page));
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public MessageResult<List> select(RtsProducerView rtsProConfigView) {
        List<RtsProducerView> list = rtsProducerService.select(rtsProConfigView);
        logger.debug("select search=" + JSONUtil.parseObj2JSON(rtsProConfigView));
        return new MessageResult(true, list);
    }

    /**
     * 新增实时流数据源实体
     *
     * @param rtsProducerProsView 实时流数据源及其配置参数实体
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public MessageResult insert(@RequestBody RtsProducerProsView rtsProducerProsView) {
        boolean status = true;
        String message = "添加成功";
        if (rtsProducerProsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(rtsProducerService.insert(rtsProducerProsView))) {
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

    /**
     * 检查名称唯一性
     *
     * @param name 数据源名称
     * @return
     */
    @RequestMapping({"/checkName/{name}"})
    @ResponseBody
    public MessageResult checekName(@PathVariable("name") String name) {
        boolean status = true;
        String message = "添加成功";
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!rtsProducerService.checekUniqueName(name)) {
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
            logger.warn(message);
        }
        return new MessageResult(status, message);
    }

    /**
     * 根据主键查询实时流数据源实体
     *
     * @param pkId 记录主键
     * @return
     */
    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult select(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        RtsProducer rtsProducer = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                rtsProducer = this.rtsProducerService.select(pkId);
            } catch (Exception e) {
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, rtsProducer);
    }

    /**
     * 编辑数据源实体
     *
     * @param rtsProducerProsView
     * @return
     */
    @RequestMapping({"/update"})
    @ResponseBody
    public MessageResult update(@RequestBody RtsProducerProsView rtsProducerProsView) {
        boolean status = true;
        String message = "更新成功";
        if (rtsProducerProsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!rtsProducerService.update(rtsProducerProsView)) {
                    status = false;
                    message = "更新失败";
                }
            } catch (Exception e) {
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

    /**
     * 批量删除
     *
     * @param rtsProducers
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult deleteList(@RequestBody RtsProducer[] rtsProducers) {
        boolean status = true;
        String message = "删除成功";
        if (rtsProducers.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {

            if (!rtsProducerService.delete(rtsProducers)) {
                status = false;
                message = "删除失败";
            }
        } catch (Exception e) {
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping("/selectProducerColumns/{producerId}")
    @ResponseBody
    public MessageResult selectProducerColumns(@PathVariable("producerId")String producerId) {
        boolean status = true;
        String message = "查询成功";
        List<RtsMetadataCol> rtsProducers = null;
        if (StringUtils.isBlank(producerId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                rtsProducers = this.rtsProducerService.selectProducerColumns(producerId);
            } catch (Exception e) {
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, rtsProducers);
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
            Map<String,String> result = rtsProducerService.uploadExcel(uploadFilePath);
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
    public String createExcel(@RequestBody RtsProducer[] rtsProducers){
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = rtsProducerService.createExcel(rtsProducers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
