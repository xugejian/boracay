package com.hex.bigdata.udsp.rts.controller;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.rts.dto.RtsConsumerRequestView;
import com.hex.bigdata.udsp.rts.dto.RtsProducerRequestView;
import com.hex.bigdata.udsp.rts.provider.model.ConsumerResponse;
import com.hex.bigdata.udsp.rts.provider.model.ProducerResponse;
import com.hex.bigdata.udsp.rts.provider.model.WebConsumerResponse;
import com.hex.bigdata.udsp.rts.service.RtsProviderService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/3/8
 * TIME:14:37
 */
@RequestMapping("/rts/qm")
@Controller
@Deprecated
public class RtsProviderController extends BaseController {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsConsumerController.class);

    @Autowired
    private RtsProviderService rtsProviderService;

    /**
     * 生产者测试
     *
     * @param requestView
     * @return
     */
    @RequestMapping("/producer")
    @ResponseBody
    public MessageResult producertest(@RequestBody RtsProducerRequestView requestView) {
        boolean status = true;
        String message = "测试成功";
        ProducerResponse response = null;
        if (requestView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                response = this.rtsProviderService.producer(requestView);
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
        return new MessageResult(status, message, response);
    }

    /**
     * 消费测试
     *
     * @param requestView
     * @return
     */
    @RequestMapping("/consumer")
    @ResponseBody
    public MessageResult consumertest(@RequestBody RtsConsumerRequestView requestView) {
        boolean status = true;
        String message = "测试成功";
        WebConsumerResponse webResponse = null;
        if (requestView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                ConsumerResponse response = this.rtsProviderService.consumer(requestView);
                webResponse = convert(response);
                response.getStatus();
                if (response.getStatus().equals(Status.DEFEAT)) {
                    message = "测试失败";
                } else if (response.getStatus().equals(Status.TIMEOUT)) {
                    message = "测试超时";
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
        return new MessageResult(status, message, webResponse);
    }

    /**
     * 类型转换
     *
     * @param response
     * @return
     */
    private WebConsumerResponse convert(ConsumerResponse response) {
        WebConsumerResponse webConsumerResponse = new WebConsumerResponse();
        webConsumerResponse.setStatus(response.getStatus());
        webConsumerResponse.setRecords(response.getRecords());
        webConsumerResponse.setConsumerRequest(response.getConsumerRequest());
        webConsumerResponse.setMessage(response.getMessage());
        webConsumerResponse.setConsumeTime(response.getConsumeTime());
        webConsumerResponse.setStatusCode(response.getStatusCode());
        webConsumerResponse.setTotalCount(response.getTotalCount());
        return webConsumerResponse;
    }

}
