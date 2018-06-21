package com.hex.bigdata.udsp.im.task;

import com.hex.bigdata.udsp.im.model.RealtimeTotalInfo;
import com.hex.bigdata.udsp.im.converter.impl.model.modeling.MqModel;
import com.hex.bigdata.udsp.im.service.ImConverterService;
import com.hex.bigdata.udsp.im.service.RealtimeTotalService;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by JunjieM on 2017-9-15.
 */
public class RealtimeJob implements Job {
    private static Logger logger = LogManager.getLogger(RealtimeJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("一次实时构建【开始】");
        RealtimeTotalService realtimeTotalService = (RealtimeTotalService) WebApplicationContextUtil.getBean("realtimeTotalService");
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        String id = jobName; // 在这里jobName就是id
        RealtimeTotalInfo realtimeTotalInfo = realtimeTotalService.select(id);
        MqModel model = realtimeTotalInfo.getModel();
        ImConverterService imConverterService = (ImConverterService) WebApplicationContextUtil.getBean("imConverterService");
        try {
            imConverterService.buildRealtime(id, model);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
        logger.debug("一次实时构建【结束】");
    }
}
