package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.EnumTrans;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.FTPClientConfig;
import com.hex.bigdata.udsp.common.util.FTPHelper;
import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.consumer.thread.IqAsyncCallable;
import com.hex.bigdata.udsp.consumer.thread.IqSyncServiceCallable;
import com.hex.bigdata.udsp.iq.model.IqAppQueryCol;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;
import com.hex.bigdata.udsp.iq.service.IqAppQueryColService;
import com.hex.bigdata.udsp.iq.service.IqProviderService;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 同步交互查询的服务
 */
@Service
public class IqSyncService {

    private static Logger logger = LoggerFactory.getLogger(IqSyncService.class);

    private static final ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
        private AtomicInteger id = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("iq-service-" + id.addAndGet(1));
            return thread;
        }
    });

    static {
        FTPClientConfig.loadConf("goframe/udsp/udsp.config.properties");
    }

    @Autowired
    private IqProviderService iqProviderService;
    @Autowired
    private IqAppQueryColService iqAppQueryColService;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private InitParamService initParamService;

    /**
     * 同步运行（添加了超时机制）
     *
     * @param consumeRequest
     * @param bef
     * @return
     */
    public Response syncStartForTimeout(ConsumeRequest consumeRequest, long bef) {
        long runBef = System.currentTimeMillis();
        Response response = new Response();
        try {
            Request request = consumeRequest.getRequest();
            RcUserService rcUserService = consumeRequest.getRcUserService();
            long maxSyncExecuteTimeout = (rcUserService == null || rcUserService.getMaxSyncExecuteTimeout() == 0) ?
                    initParamService.getMaxSyncExecuteTimeout() : rcUserService.getMaxSyncExecuteTimeout();
            // 开启一个新的线程，其内部执行交互查询任务，执行成功时或者执行超时时向下走
            Future<Response> futureTask = executorService.submit(
                    new IqSyncServiceCallable(request.getData(), request.getAppId(), request.getPage()));
            response = futureTask.get(maxSyncExecuteTimeout, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            loggingService.writeResponseLog(response, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName() + ":" + e.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            loggingService.writeResponseLog(response, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName() + ":" + e.toString(), null);
        }
        return response;
    }

    public void asyncStartForTimeout(ConsumeRequest consumeRequest, String fileName, long bef) {
        long runBef = System.currentTimeMillis();
        Current mcCurrent = consumeRequest.getMcCurrent();
        String consumeId = mcCurrent.getPkId();
        try {
            Request request = consumeRequest.getRequest();
            String userName = consumeRequest.getMcCurrent().getUserName();
            RcUserService rcUserService = consumeRequest.getRcUserService();
            long maxAsyncExecuteTimeout = (rcUserService == null || rcUserService.getMaxAsyncExecuteTimeout() == 0) ?
                    initParamService.getMaxAsyncExecuteTimeout() : rcUserService.getMaxAsyncExecuteTimeout();
            // 开启一个新的线程，其内部执行交互查询任务，执行成功时或者执行超时时向下走
            Future<IqResponse> iqFutureTask = executorService.submit(
                    new IqAsyncCallable(userName, request.getAppId(), request.getData(), request.getPage(), fileName));
            IqResponse iqResponse = iqFutureTask.get(maxAsyncExecuteTimeout, TimeUnit.SECONDS);
            Response response = new Response();
            response.setResponseContent(iqResponse.getFilePath());
            loggingService.writeResponseLog(consumeId, bef, runBef, request, response, false);
        } catch (TimeoutException e) {
            loggingService.writeResponseLog(null, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName() + ":" + e.toString(), consumeId);
        } catch (Exception e) {
            e.printStackTrace();
            loggingService.writeResponseLog(null, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName() + ":" + e.toString(), consumeId);
        }
    }

    /**
     * 同步运行
     *
     * @param appId
     * @param paraMap
     * @param page
     * @return
     */
    public Response syncStart(String appId, Map<String, String> paraMap, Page page) {
        Response response = new Response();
        try {
            IqResponse iqResponse = run(appId, paraMap, page);
            response.setPage(iqResponse.getPage());
            response.setMessage(iqResponse.getMessage());
            response.setConsumeTime(iqResponse.getConsumeTime());
            response.setStatus(iqResponse.getStatus().getValue());
            response.setStatusCode(iqResponse.getStatusCode().getValue());
            response.setRecords(iqResponse.getRecords());
            response.setReturnColumns(iqResponse.getColumns());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
        }
        return response;
    }

    /**
     * 运行
     *
     * @param appId
     * @param paraMap
     * @param page
     * @return
     */
    private IqResponse run(String appId, Map<String, String> paraMap, Page page) {
        try {
            checkParam(appId, paraMap);
        } catch (Exception e) {
            IqResponse response = new IqResponse();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.getMessage());
            return response;
        }
        if (page != null && page.getPageIndex() > 0) {
            return iqProviderService.select(appId, paraMap, page);
        } else {
            return iqProviderService.select(appId, paraMap);
        }
    }

    /**
     * 检查输入的参数
     */
    private void checkParam(String appId, Map<String, String> paraMap) throws Exception {
        if (paraMap != null && paraMap.size() != 0) {
            boolean isError = false;
            String message = "";
            int count = 0;
            for (IqAppQueryCol iqAppQueryCol : iqAppQueryColService.selectByAppId(appId)) {
                if (EnumTrans.transTrue(iqAppQueryCol.getIsNeed())) {
                    String name = iqAppQueryCol.getLabel();
                    String value = paraMap.get(name);
                    if (StringUtils.isBlank(value)) { // 没有传入值
                        message += (count == 0 ? "" : ", ") + name;
                        isError = true;
                        count++;
                    }
                }
            }
            if (isError) {
                throw new Exception(message + "参数不能为空!");
            }
        }
    }

    /**
     * 异步运行
     *
     * @param appId
     * @param paraMap
     * @param page
     * @return
     */
    public IqResponse asyncStart(String appId, Map<String, String> paraMap, Page page, String fileName, String userName) {
        Status status = Status.SUCCESS;
        StatusCode statusCode = StatusCode.SUCCESS;
        String message = "成功";
        String filePath = "";
        IqResponse response = run(appId, paraMap, page);
        if (Status.SUCCESS == response.getStatus()) {
            List<Map<String, String>> records = response.getRecords();
            // 写数据文件和标记文件到本地，并上传至FTP服务器
            CreateFileUtil.createDelimiterFile(records, true, fileName);
            String dataFileName = CreateFileUtil.getDataFileName(fileName);
            String flgFileName = CreateFileUtil.getFlgFileName(fileName);
            String localDataFilePath = CreateFileUtil.getLocalDataFilePath(fileName);
            String localFlgFilePath = CreateFileUtil.getLocalFlgFilePath(fileName);
            String ftpFileDir = CreateFileUtil.getFtpFileDir(userName);
            String ftpDataFilePath = ftpFileDir + "/" + dataFileName;
            FTPHelper ftpHelper = new FTPHelper();
            try {
                ftpHelper.connectFTPServer();
                ftpHelper.uploadFile(localDataFilePath, dataFileName, ftpFileDir);
                ftpHelper.uploadFile(localFlgFilePath, flgFileName, ftpFileDir);
                //filePath = "ftp://" + FTPClientConfig.getHostname() + ":" + FTPClientConfig.getPort() + ftpFilePath;
                filePath = ftpDataFilePath;
                message = localDataFilePath;
            } catch (Exception e) {
                status = Status.DEFEAT;
                statusCode = StatusCode.DEFEAT;
                message = "FTP上传失败！" + e.getMessage();
                e.printStackTrace();
            } finally {
                try {
                    ftpHelper.closeFTPClient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            status = Status.DEFEAT;
            statusCode = StatusCode.DEFEAT;
            message = response.getMessage();
        }
        response.setFilePath(filePath);
        response.setMessage(message);
        response.setStatus(status);
        response.setStatusCode(statusCode);
        return response;
    }

}
