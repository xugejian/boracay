package com.hex.bigdata.udsp.olq.controller;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.olq.model.ResultData;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.olq.service.OlqProviderService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tomnic on 2017/2/20.
 */
@Deprecated
@RequestMapping("/olq/qm")
@Controller
public class OlqProviderController extends BaseController {
    private static Logger logger = LogManager.getLogger(OlqProviderController.class);

    @Autowired
    private OlqProviderService olqProviderService;

    @RequestMapping({"/select"})
    @ResponseBody
    public MessageResult select(@RequestBody Map<String, String> paraMap) {
        boolean status = true;
        String message = "执行成功";
        String dsId = paraMap.get("dsId");
        String sql = paraMap.get("sql");
        String defaultNum = paraMap.get("defaultNum");
        logger.debug("connection " + dsId + " datasource execute SQL=" + sql + " defaultNum=" + defaultNum);
        List<Map<String, String>> records = null;

        String sqlUpper = sql.toUpperCase().trim();
        if (sqlUpper.startsWith("SELECT ") && !sqlUpper.contains("LIMIT")) {
            if (sqlUpper.endsWith(";")) {
                sql = sqlUpper.substring(0, sqlUpper.length() - 1) + " LIMIT " + defaultNum;
            } else {
                sql = sqlUpper + " LIMIT " + defaultNum;
            }
        }

        ResultData resultData = new ResultData();
        try {
            OLQResponse response = olqProviderService.select(dsId, sql);
            if (response.getStatus() == Status.DEFEAT) {
                status = false;
                message = response.getMessage();
            } else {
                resultData.setRecords(response.getRecords());
                ResultSetMetaData metadata = response.getMetadata();
                List<String> fields = new ArrayList<>();
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    String fieldName = metadata.getColumnLabel(i);
                    int index = fieldName.indexOf(".");
                    fieldName = index == 1 ? fieldName : fieldName.substring(index+1, fieldName.length());
                    fields.add(fieldName);
                }
                resultData.setFields(fields);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = e.getMessage();
        }
        return new MessageResult(status, message, resultData);
    }

}
