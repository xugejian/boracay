package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.dto.ImModelView;
import com.hex.bigdata.udsp.im.model.ImModel;
import com.hex.bigdata.udsp.im.model.ImModelViews;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-4.
 */
@Controller
@RequestMapping("im/model")
public class ImModelController {

    private Logger log = LoggerFactory.getLogger(com.hex.bigdata.udsp.im.controller.ImModelController.class);
    @Autowired
    private ImModelService  imModelService;

    @ResponseBody
    @RequestMapping("insert")
    public MessageResult inset(@RequestBody ImModelViews imModelViews){
        log.debug("插入交互建模数据为："+ JSONUtil.parseObj2JSON(imModelViews));
        boolean result = true;
        String message = "保存成功！";
        try{
            result = imModelService.insert(imModelViews);
            if(!result){
                message = "保存失败！";
            }
        }catch (Exception e){
            result = false;
            message = "保存失败，失败信息：" + e.getMessage();
            log.error("交互建模数据插入失败，失败数据为："+ JSONUtil.parseObj2JSON(imModelViews)+";错误信息："+e.getMessage());
        }
        return new MessageResult(result,message);
    }

    @ResponseBody
    @RequestMapping("update/{pkId}")
    public MessageResult update(@RequestBody ImModelViews imModelViews,@PathVariable String pkId){
        log.debug("更新交互建模数据为："+ JSONUtil.parseObj2JSON(imModelViews));
        boolean result = true;
        String message = "更新成功";
        try{
            //设置pkId
            imModelViews.getImModel().setPkId(pkId);
            result = imModelService.update(imModelViews);
            if(!result){
                message = "更新失败！";
            }
        }catch (Exception e){
            result = false;
            message = "更新失败，失败信息：" + e.getMessage();
            log.error("交互建模数据更新失败，失败数据为："+ JSONUtil.parseObj2JSON(imModelViews)+";错误信息："+e.getMessage());
        }
        return new MessageResult(result,message);
    }

    /**
     * 根据查询内容获取交互建模列表
     * @param imModelView
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public PageListResult page(ImModelView imModelView, Page page){
        log.debug("查询交互建模列表的查询为：" + JSONUtil.parseObj2JSON(imModelView) + ";页面信息为："+JSONUtil.parseObj2JSON(page));
        List<ImModelView> imModels = imModelService.selectPage(imModelView,page);

        return new PageListResult(imModels,page);
    }

    @ResponseBody
    @RequestMapping("delete")
    public MessageResult delete(@RequestBody ImModel[] ImModels){
        boolean result = true;
        String message = "删除成功";
        try{
            result = imModelService.delete(ImModels);
            if(!result){
                message = "删除失败！";
            }
        }catch (Exception e){
            result = false;
            message = "删除失败，错误信息：" + e.getMessage();
        }
        return new MessageResult(result,message);
    }

    /**
     * 通过主键获取交互建模基础信息
     * @return
     */
    @ResponseBody
    @RequestMapping("selectByPkId/{pkId}")
    public ImModel selectByPkId(@PathVariable String pkId){

        return  imModelService.selectByPkId(pkId);
    }

    /**
     * 通过名称检查模型名称是否已存在
     * @return
     */
    @ResponseBody
    @RequestMapping("checkExist/{modelName}")
    public MessageResult checkExist(@PathVariable String modelName){
        boolean status = true;
        boolean result = false;
        String message = "模型名称不存在";
        try{
            if(imModelService.selectByName(modelName) != null){
                result = true;
                message = "模型名称存在";
            }
        }catch(Exception e){
            status = false;
            message = "检查模型名称是否存在失败";
            log.error("根据模型名称查询模型是否存在失败！模型名称为：" + modelName + ";错误信息：" + e.getMessage());
        }
        return new MessageResult(status,message,result);
    }
}
