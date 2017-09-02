package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.mc.constant.McConstant;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * 异步联机查询的服务
 */
public class OlqAsyncService implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(IqAsyncService.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    private OlqSyncService olqSyncService;
    private McConsumeLogService mcConsumeLogService;
    private McCurrentCountService mcCurrentCountService;

    private McCurrent mcCurrent;
    private String appId;
    private String sql;
    private String fileName;
    private String appType;

    public OlqAsyncService(McCurrent mcCurrent, String appId, String sql, String appType, String fileName) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");

        this.mcCurrent = mcCurrent;
        this.appId = appId;
        this.sql = sql;
        this.appType = appType;
        this.fileName = fileName;
    }


    @Override
    public void run() {
        try {
            logger.debug("IQ START 线程调用开始");
            this.exec();
            logger.debug(" IQ START 线程调用结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //减少异步并发统计
            mcCurrentCountService.reduceAsyncCurrent(mcCurrent);
        }
    }

    private void exec() {
        McConsumeLog mcConsumeLog = new McConsumeLog();
        mcConsumeLog.setPkId(this.mcCurrent.getPkId());
        mcConsumeLog.setRequestContent(this.mcCurrent.getRequestContent());
        mcConsumeLog.setServiceName(this.mcCurrent.getServiceName());
        mcConsumeLog.setUserName(this.mcCurrent.getUserName());
        mcConsumeLog.setRequestStartTime(this.mcCurrent.getStartTime());

        String status = "0";
        String errorCode = "";
        String message = "成功";

        Calendar calendar = Calendar.getInstance();
        mcConsumeLog.setRunStartTime(format.format(calendar.getTime()));
        OLQResponse response = null;
        try {
            response = this.olqSyncService.asyncStart(this.appId, this.sql, this.fileName, this.mcCurrent.getUserName());
            message = response.getMessage();
            //内部调用失败
            if (response.getStatus().getValue().equals(Status.DEFEAT.getValue())) {
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000007.getValue();
                message = ErrorCode.ERROR_000007.getName() +"："+ message;
            }
            calendar.add(Calendar.MILLISECOND, (int) response.getConsumeTime());
            mcConsumeLog.setRunEndTime(format.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
            status = McConstant.MCLOG_STATUS_FAILED;
            errorCode = ErrorCode.ERROR_000007.getValue();
            message = ErrorCode.ERROR_000007.getName() +"："+e.getMessage();
        }
        mcConsumeLog.setRunEndTime(format.format(new Date()));

        // 写日志到数据库和本地日志文件
        //logger.info("select " + appId + this.appType + " application sql=" + sql + " " + message);

        mcConsumeLog.setResponseContent(response.getFilePath());
        mcConsumeLog.setMessage(message);
        mcConsumeLog.setRequestEndTime(format.format(new Date()));
        mcConsumeLog.setStatus(status);
        mcConsumeLog.setAppType(this.appType);
        mcConsumeLog.setErrorCode(errorCode);
        mcConsumeLog.setRequestType(this.mcCurrent.getRequestType());
        mcConsumeLog.setAppName(this.mcCurrent.getAppName());
        if (CommonConstant.REQUEST_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
            mcConsumeLog.setSyncType(mcCurrent.getSyncType().toUpperCase());
        } else if (CommonConstant.REQUEST_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
            mcConsumeLog.setSyncType(mcCurrent.getSyncType().toUpperCase());
        } else {
            mcConsumeLog.setSyncType("NULL");
        }

        this.mcConsumeLogService.insert(mcConsumeLog);
    }
}
