package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.common.util.*;
import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.im.util.JdbcUtil;
import com.hex.bigdata.udsp.mc.model.Current;
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
        final Request request = consumeRequest.getRequest();
        Current mcCurrent = consumeRequest.getMcCurrent();
        final String consumeId = (StringUtils.isNotBlank(request.getConsumeId()) ? request.getConsumeId() : mcCurrent.getPkId());
        RcUserService rcUserService = consumeRequest.getRcUserService();
        long maxSyncExecuteTimeout = (rcUserService == null || rcUserService.getMaxSyncExecuteTimeout() == 0) ?
                initParamService.getMaxSyncExecuteTimeout() : rcUserService.getMaxSyncExecuteTimeout();
        Response response = new Response();
        long runBef = System.currentTimeMillis();
        try {
            // 开启一个新的线程，其内部执行联机查询任务，执行成功时或者执行超时时向下走
            Future<Response> futureTask = executorService.submit(new Callable() {
                @Override
                public Response call() throws Exception {
                    return syncStart(consumeId, request.getAppId(), request.getSql(), request.getPage());
                }
            });
            response = futureTask.get(maxSyncExecuteTimeout, TimeUnit.SECONDS);
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

    /**
     * 异步运行（添加了超时机制）
     *
     * @param consumeRequest
     * @param fileName
     * @param bef
     */
    public void asyncStartForTimeout(ConsumeRequest consumeRequest, final String fileName, long bef) {
        long runBef = System.currentTimeMillis();
        Current mcCurrent = consumeRequest.getMcCurrent();
        final String consumeId = mcCurrent.getPkId();
        try {
            final String userName = consumeRequest.getMcCurrent().getUserName();
            final Request request = consumeRequest.getRequest();
            RcUserService rcUserService = consumeRequest.getRcUserService();
            long maxAsyncExecuteTimeout = (rcUserService == null || rcUserService.getMaxAsyncExecuteTimeout() == 0) ?
                    initParamService.getMaxAsyncExecuteTimeout() : rcUserService.getMaxAsyncExecuteTimeout();
            // 开启一个新的线程，其内部执行联机查询任务，执行成功时或者执行超时时向下走
            Future<Response> futureTask = executorService.submit(new Callable<Response>() {
                @Override
                public Response call() throws Exception {
                    return asyncStart(consumeId, request.getAppId(), request.getSql(), request.getPage(), fileName, userName);
                }
            });
            Response response = futureTask.get(maxAsyncExecuteTimeout, TimeUnit.SECONDS);
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
            logger.error(ExceptionUtil.getMessage(e));
            e.printStackTrace();
            response.setMessage(ErrorCode.ERROR_000007.getName() + "：" + e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
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
            response.setMessage(ErrorCode.ERROR_000009.getName() + ":" + e.getMessage());
            return response;
        }
        Status status = Status.SUCCESS;
        StatusCode statusCode = StatusCode.SUCCESS;
        String message = "成功";
        String filePath = "";
        OlqResponseFetch responseFetch = olqProviderService.selectFetch(consumeId, dsId, sql, page);
        Connection conn = responseFetch.getConnection();
        Statement stmt = responseFetch.getStatement();
        ResultSet rs = responseFetch.getResultSet();
        try {
            if (Status.SUCCESS == responseFetch.getStatus()) {
                // 写数据文件和标记文件到本地，并上传至FTP服务器
                CreateFileUtil.createDelimiterFile(rs, true, fileName);
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
                message = responseFetch.getMessage();
            }
        } catch (Exception e) {
            status = Status.DEFEAT;
            statusCode = StatusCode.DEFEAT;
            message = e.getMessage();
        } finally {
            StatementUtil.removeStatement(consumeId);
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
            JdbcUtil.close(conn);
        }
        response.setResponseContent(filePath);
        response.setMessage(message);
        response.setStatus(status.getValue());
        response.setStatusCode(statusCode.getValue());
        return response;
    }

    private void checkParam(String sql) throws Exception {
        if (StringUtils.isBlank(sql)) {
            throw new Exception("sql参数不能为空!");
        }
    }
}
