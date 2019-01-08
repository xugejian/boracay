package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.FTPClientConfig;
import com.hex.bigdata.udsp.common.util.FTPHelper;
import com.hex.bigdata.udsp.common.util.StatementUtil;
import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.im.converter.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponseFetch;
import com.hex.bigdata.udsp.olq.service.OlqProviderService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 同步联机查询的服务
 */
@Service
public class OlqSyncService {
    private static Logger logger = LoggerFactory.getLogger(OlqSyncService.class);

    private static final ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
        private AtomicInteger id = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("olq-service-" + id.addAndGet(1));
            return thread;
        }
    });

    static {
        FTPClientConfig.loadConf("goframe/udsp/udsp.config.properties");
    }

    @Autowired
    private OlqProviderService olqProviderService;
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
        try {
            final Request request = consumeRequest.getRequest();
            final String consumeId = request.getConsumeId();
            RcUserService rcUserService = consumeRequest.getRcUserService();
            if (rcUserService == null || rcUserService.getMaxSyncExecuteTimeout() == 0) { // 不开启超时
                response = syncStart(consumeId, request.getAppId(), request.getSql(), request.getPage());
            } else { // 开启一个新的线程，其内部执行联机查询任务，执行成功时或者执行超时时向下走
                Future<Response> futureTask = executorService.submit(new Callable() {
                    @Override
                    public Response call() throws Exception {
                        return syncStart(consumeId, request.getAppId(), request.getSql(), request.getPage());
                    }
                });
                response = futureTask.get(rcUserService.getMaxSyncExecuteTimeout(), TimeUnit.SECONDS);
            }
        } catch (TimeoutException e) {
            loggingService.writeResponseLog(response, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName() + ":" + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            loggingService.writeResponseLog(response, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName() + ":" + e.toString());
        }
        return response;
    }

    /**
     * 异步运行（添加了超时机制）
     *
     * @param consumeRequest
     * @param fileName
     * @param bef
     */
    public void asyncStartForTimeout(ConsumeRequest consumeRequest, final String fileName, long bef) {
        long runBef = System.currentTimeMillis();
        Response response = null;
        try {
            final Request request = consumeRequest.getRequest();
            final String consumeId = request.getConsumeId();
            RcUserService rcUserService = consumeRequest.getRcUserService();
            if (rcUserService == null || rcUserService.getMaxAsyncExecuteTimeout() == 0) { // 不开启超时
                response = asyncStart(consumeId, request.getAppId(), request.getSql(), request.getPage(), fileName, request.getUdspUser());
            } else { // 开启一个新的线程，其内部执行联机查询任务，执行成功时或者执行超时时向下走
                Future<Response> futureTask = executorService.submit(new Callable<Response>() {
                    @Override
                    public Response call() throws Exception {
                        return asyncStart(consumeId, request.getAppId(), request.getSql(), request.getPage(), fileName, request.getUdspUser());
                    }
                });
                response = futureTask.get(rcUserService.getMaxAsyncExecuteTimeout(), TimeUnit.SECONDS);
            }
            loggingService.writeResponseLog(request, response, bef, runBef, false);
        } catch (TimeoutException e) {
            loggingService.writeResponseLog(null, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName() + ":" + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            loggingService.writeResponseLog(null, consumeRequest, bef, runBef,
                    ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName() + ":" + e.toString());
        }
    }

    /**
     * 同步运行
     *
     * @param consumeId
     * @param dsId
     * @param sql
     * @param page
     * @return
     */
    public Response syncStart(String consumeId, String dsId, String sql, Page page) {
        Response response = new Response();
        try {
            checkParam(sql);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000009.getValue());
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.getMessage());
            return response;
        }
        try {
            OlqResponse olqResponse = olqProviderService.select(consumeId, dsId, sql, page);
            response.setMessage(olqResponse.getMessage());
            response.setConsumeTime(olqResponse.getConsumeTime());
            response.setStatus(olqResponse.getStatus().getValue());
            response.setStatusCode(olqResponse.getStatusCode().getValue());
            response.setRecords(olqResponse.getRecords());
            response.setReturnColumns(olqResponse.getColumns());
            response.setPage(olqResponse.getPage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(ErrorCode.ERROR_000007.getName() + ":" + e.getMessage());
        }
        return response;
    }

    /**
     * 异步运行
     *
     * @param dsId
     * @param sql
     * @return
     */
    public Response asyncStart(String consumeId, String dsId, String sql, Page page, String fileName, String userName) {
        Response response = new Response();
        try {
            checkParam(sql);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000009.getValue());
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.getMessage());
            return response;
        }
        OlqResponseFetch responseFetch = olqProviderService.selectFetch(consumeId, dsId, sql, page);
        if (Status.DEFEAT == responseFetch.getStatus()) {
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage(responseFetch.getMessage());
        }
        Connection conn = responseFetch.getConnection();
        Statement stmt = responseFetch.getStatement();
        ResultSet rs = responseFetch.getResultSet();
        try {
            CreateFileUtil.createDelimiterFile(rs, true, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setMessage("生成文件失败！" + e.getMessage());
            return response;
        } finally {
            StatementUtil.removeStatement(consumeId);
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
            JdbcUtil.close(conn);
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

    private void checkParam(String sql) throws Exception {
        if (StringUtils.isBlank(sql)) {
            throw new Exception("sql参数不能为空!");
        }
    }
}
