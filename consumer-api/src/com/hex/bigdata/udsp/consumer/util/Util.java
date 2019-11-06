package com.hex.bigdata.udsp.consumer.util;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.FTPHelper;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.MD5Util;
import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.rc.model.RcService;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2018-9-7.
 */
public class Util {

    /**
     * JSON字符串转Request对象
     *
     * @param json
     * @return
     */
    public static Request jsonToRequest(String json) {
        Map<String, Class> classMap = new HashMap<> ();
        classMap.put ("dataStream", Map.class);
        return JSONUtil.parseJSON2Obj (json, Request.class, classMap);
    }

    /**
     * 上传文件到FTP服务器并返回结果
     *
     * @param fileName
     * @param userName
     * @return
     */
    public static Response uploadFtp(String fileName, String userName) {
        String dataFileName = CreateFileUtil.getDataFileName (fileName);
        String flgFileName = CreateFileUtil.getFlgFileName (fileName);
        String localDataFilePath = CreateFileUtil.getLocalDataFilePath (fileName);
        String localFlgFilePath = CreateFileUtil.getLocalFlgFilePath (fileName);
        String ftpFileDir = CreateFileUtil.getFtpFileDir (userName);
        String ftpDataFilePath = ftpFileDir + "/" + dataFileName;
        // 写数据文件和标记文件到本地，并上传至FTP服务器
        FTPHelper ftpHelper = new FTPHelper ();
        try {
            ftpHelper.connectFTPServer ();
            ftpHelper.uploadFile (localDataFilePath, dataFileName, ftpFileDir);
            ftpHelper.uploadFile (localFlgFilePath, flgFileName, ftpFileDir);
            //String filePath = "ftp://" + FTPClientConfig.getHostname() + ":" + FTPClientConfig.getPort() + ftpFilePath;
            String filePath = ftpDataFilePath;
            Response response = new Response ();
            response.setStatus (Status.SUCCESS.getValue ());
            response.setStatusCode (StatusCode.SUCCESS.getValue ());
            //response.setMessage (localDataFilePath);
            response.setResponseContent (filePath);
            return response;
        } catch (Exception e) {
            e.printStackTrace ();
            return errorResponse (ErrorCode.ERROR_000007, "FTP上传失败！" + e.getMessage ());
        } finally {
            try {
                ftpHelper.closeFTPClient ();
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
    }

    /**
     * 获取错误的Response
     *
     * @param errorCode
     * @param message
     * @return
     */
    public static Response errorResponse(ErrorCode errorCode, String message) {
        Response response = new Response ();
        response.setStatus (Status.DEFEAT.getValue ());
        response.setStatusCode (StatusCode.DEFEAT.getValue ());
        response.setErrorCode (errorCode.getValue ());
        response.setMessage (errorCode.getName () + ":" + message);
        return response;
    }

    /**
     * 获取缓存超时时间
     *
     * @param rcService
     * @return
     */
    public static long getCacheTimeout(RcService rcService) {
        long timeout = 0;
        if (rcService != null && "0".equals (rcService.getIsCache ())) { // 开启缓存
            timeout = rcService.getTimeout ();
        }
        return timeout;
    }

    /**
     * 判断是否开启存储
     *
     * @param rcService
     * @return
     */
    public static boolean isStore(RcService rcService) {
        return rcService != null && "0".equals (rcService.getIsStore ());
    }

    /**
     * 获取当前日期是星期几
     * <p>
     * 0：星期日
     * 1：星期一
     * 2：星期二
     * 3：星期三
     * 4：星期四
     * 5：星期五
     * 6：星期六
     *
     * @return
     */
    public static int nowWeek() {
        Calendar cal = Calendar.getInstance ();
        cal.setTime (new Date ());
        int week = cal.get (Calendar.DAY_OF_WEEK) - 1;
        if (week < 0) {
            week = 0;
        }
        return week;
    }
}
