package com.hex.bigdata.udsp.iq.controller;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.provider.model.Result;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;
import com.hex.bigdata.udsp.iq.service.IqProviderService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tomnic on 2017/2/20.
 */
@Deprecated
@RequestMapping("/iq/qm")
@Controller
public class IqProviderController extends BaseController {
    private static Logger logger = LogManager.getLogger(IqProviderController.class);

    @Autowired
    private IqProviderService iqProviderService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(HttpServletRequest request) {
        boolean status = true;
        String message = "执行成功";
        Map<String, String[]> paramMap = request.getParameterMap();
        Map<String, String> paraMap = new HashMap<>();
        String regex = "data\\[(.+?)\\]";
        Pattern pattern = Pattern.compile(regex);
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            Matcher matcher = pattern.matcher(entry.getKey());
            while (matcher.find()) {
                paraMap.put(matcher.group(1), entry.getValue()[0]);
                break;
            }
        }
        String appId = paramMap.get("appId")[0];
        int pageIndex = Integer.valueOf(paramMap.get("pageIndex")[0]);
        int pageSize = Integer.valueOf(paramMap.get("pageSize")[0]);
        logger.debug("page select " + appId + " application search=" + JSONUtil.parseObj2JSON(paraMap) + " pageIndex=" + pageIndex + " pageSize=" + pageSize);

        PageListResult pageListResult = null;
        try {
            IqResponse response = iqProviderService.select(appId, paraMap, pageIndex + 1, pageSize);
            if (response.getStatus() == Status.SUCCESS) {
                Page page = new Page();
                page.setPageIndex(pageIndex);
                page.setPageSize(pageSize);
                page.setTotalCount(response.getPage().getTotalCount());

                List<Result> list = response.getRecords();
                List<Map<String, String>> records = new ArrayList<>();
                Map<String, String> map = null;
                for (Result result : list) {
                    map = new HashMap<>();
                    for (Map.Entry<String, Object> entry : result.entrySet()) {
                        map.put(entry.getKey(), result.getString(entry.getKey()));
                    }
                    records.add(map);
                }
                pageListResult = new PageListResult(records, page);
                pageListResult.setTotal(page.getTotalCount());
                pageListResult.setStatus(status);
                pageListResult.setMessage(message);
            } else {
                status = false;
                message = response.getMessage();
                pageListResult = new PageListResult(null);
                pageListResult.setStatus(status);
                pageListResult.setMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = e.getMessage();
            pageListResult = new PageListResult(null);
            pageListResult.setStatus(status);
            pageListResult.setMessage(message);
        }
        return pageListResult;
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public MessageResult select(HttpServletRequest request) {
        boolean status = true;
        String message = "执行成功";
        Map<String, String[]> paramMap = request.getParameterMap();
        Map<String, String> paraMap = new HashMap<>();
        String regex = "data\\[(.+?)\\]";
        Pattern pattern = Pattern.compile(regex);
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            Matcher matcher = pattern.matcher(entry.getKey());
            while (matcher.find()) {
                paraMap.put(matcher.group(1), entry.getValue()[0]);
                break;
            }
        }
        String appId = paramMap.get("appId")[0];
        logger.debug("page select " + appId + " application search=" + JSONUtil.parseObj2JSON(paraMap));

        List<Map<String, String>> records = null;
        try {
            IqResponse response = iqProviderService.select(appId, paraMap);
            if (response.getStatus() == Status.SUCCESS) {
                List<Result> list = response.getRecords();
                records = new ArrayList<>();
                Map<String, String> map = null;
                for (Result result : list) {
                    map = new HashMap<>();
                    for (Map.Entry<String, Object> entry : result.entrySet()) {
                        map.put(entry.getKey(), result.getString(entry.getKey()));
                    }
                    records.add(map);
                }
            } else {
                status = false;
                message = response.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = e.getMessage();
        }
        return new MessageResult(status, message, records);
    }

}
