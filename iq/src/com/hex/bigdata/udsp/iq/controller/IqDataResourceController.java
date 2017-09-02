package com.hex.bigdata.udsp.iq.controller;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.iq.service.IqDataResourceService;
import com.hex.goframe.model.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by PC on 2017/5/17.
 */
@Controller
@RequestMapping("/iq/dataResource")
public class IqDataResourceController {

    @Autowired
    private IqDataResourceService iqDataResourceService;

    @RequestMapping("/checkDatasourceUsed/{pkId}")
    @ResponseBody
    public MessageResult checkDatasourceUsed(@PathVariable String pkId){
        boolean status = false;
        String message = "未被引用！";

        if(iqDataResourceService.checkDatasourceUsed(pkId)){
            status = true;
            message = "该数据源已被引用！";
        }
        return new MessageResult(status,message);
    }

    @RequestMapping("/checkDatasourcesUsed")
    @ResponseBody
    public MessageResult checkDatasourcesUsed(@RequestBody ComDatasource[] comDatasources){
        boolean status = false;
        String message = "未被引用！";
        Map result = iqDataResourceService.checkDatasourcesUsed(comDatasources);
        if("true".equals(result.get("result"))){
            status = true;
            message = "数据源名称为："+result.get("name")+"已被引用！";
        }
        return new MessageResult(status,message);
    }
}
