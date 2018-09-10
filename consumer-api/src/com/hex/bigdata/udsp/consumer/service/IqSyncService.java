package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.EnumTrans;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.FTPClientConfig;
import com.hex.bigdata.udsp.common.util.FTPHelper;
import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.iq.model.IqAppQueryCol;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;
import com.hex.bigdata.udsp.iq.service.IqAppQueryColService;
import com.hex.bigdata.udsp.iq.service.IqProviderService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        final Request request = consumeRequest.getRequest();
        String consumeId = request.getConsumeId();
        try {
            RcUserService rcUserService = consumeRequest.getRcUserService();
            if (rcUserService == null || rcUserService.getMaxSyncExecuteTimeout() == 0) { // 不开启超时
                response = syncStart(request.getAppId(), request.getData(), request.getPage());
            } else { // 开启一个新的线程，其内部执行交互查询任务，执行成功时或者执行超时时向下走
                Future<Response> futureTask = executorService.submit(new Callable() {
                    @Override
                    public Response call() throws Exception {
                        return syncStart(request.getAppId(), request.getData(), request.getPage());
                    }
                });
                response = futureTask.get(rcUserService.getMaxSyncExecuteTimeout(), TimeUnit.SECONDS);
            }
        } catch (TimeoutException e) {
            loggingService.writeResponseLog(response, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName() + ":" + e.toString(), consumeId);
        } catch (Exception e) {
            e.printStackTrace();
            loggingService.writeResponseLog(response, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName() + ":" + e.toString(), consumeId);
        }
        return response;
    }

    public void asyncStartForTimeout(ConsumeRequest consumeRequest, final String fileName, long bef) {
        long runBef = System.currentTimeMillis();
        Response response = null;
        final Request request = consumeRequest.getRequest();
        String consumeId = request.getConsumeId();
        try {
            RcUserService rcUserService = consumeRequest.getRcUserService();
            if (rcUserService == null || rcUserService.getMaxAsyncExecuteTimeout() == 0) { // 不开启超时
                response = asyncStart(request.getAppId(), request.getData(), request.getPage(), fileName, request.getUdspUser());
            } else { // 开启一个新的线程，其内部执行交互查询任务，执行成功时或者执行超时时向下走
                Future<Response> futureTask = executorService.submit(new Callable<Response>() {
                    @Override
                    public Response call() throws Exception {
                        return asyncStart(request.getAppId(), request.getData(), request.getPage(), fileName, request.getUdspUser());
                    }
                });
                response = futureTask.get(rcUserService.getMaxAsyncExecuteTimeout(), TimeUnit.SECONDS);
            }
            loggingService.writeResponseLog(request.getConsumeId(), bef, runBef, request, response, false);
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
            response = run(appId, paraMap, page);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(e.getMessage());
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
    private Response run(String appId, Map<String, String> paraMap, Page page) {
        Response response = new Response();
        try {
            checkParam(appId, paraMap);
        } catch (Exception e) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000009.getValue());
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.toString());
            return response;
        }
        IqResponse iqResponse = null;
        if (page != null && page.getPageIndex() > 0) {
            iqResponse = iqProviderService.select(appId, paraMap, page);
        } else {
            iqResponse = iqProviderService.select(appId, paraMap);
        }
        response.setStatus(iqResponse.getStatus().getValue());
        response.setStatusCode(iqResponse.getStatusCode().getValue());
        response.setRecords(iqResponse.getRecords());
        response.setReturnColumns(iqResponse.getColumns());
        response.setPage(iqResponse.getPage());
        response.setMessage(iqResponse.getMessage());
        response.setConsumeTime(iqResponse.getConsumeTime());
        return response;
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
    public Response asyncStart(String appId, Map<String, String> paraMap, Page page, String fileName, String userName) {
        Response response = run(appId, paraMap, page);
        if (Status.DEFEAT.getValue().equals(response.getStatus())) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(response.getMessage());
            return response;
        }
        try {
            CreateFileUtil.createDelimiterFile(response.getRecords(), true, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage("生成文件失败！" + e.getMessage());
            return response;
        }
        String dataFileName = CreateFileUtil.getDataFileName(fileName);
        String flgFileName = CreateFileUtil.getFlgFileName(fileName);
        String localDataFilePath = CreateFileUtil.getLocalDataFilePath(fileName);
        String localFlgFilePath = CreateFileUtil.getLocalFlgFilePath(fileName);
        String ftpFileDir = CreateFileUtil.getFtpFileDir(userName);
        String ftpDataFilePath = ftpFileDir + "/" + dataFileName;
        // 写数据文件和标记文件到本地，并上传至FTP服务器
        FTPHelper ftpHelper = new FTPHelper();
        try {
            ftpHelper.connectFTPServer();
            ftpHelper.uploadFile(localDataFilePath, dataFileName, ftpFileDir);
            ftpHelper.uploadFile(localFlgFilePath, flgFileName, ftpFileDir);
            //String filePath = "ftp://" + FTPClientConfig.getHostname() + ":" + FTPClientConfig.getPort() + ftpFilePath;
            String filePath = ftpDataFilePath;
            response.setStatus(Status.SUCCESS.getValue());
            response.setStatusCode(StatusCode.SUCCESS.getValue());
            response.setMessage(localDataFilePath);
            response.setResponseContent(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage("FTP上传失败！" + e.getMessage());
        } finally {
            try {
                ftpHelper.closeFTPClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}
