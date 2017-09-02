package com.hex.bigdata.udsp.controller;

import com.dc.eai.data.*;
import com.dcfs.esb.server.service.Service;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.ConsumerService;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Tzb Esb Controller
 */
public class TzbEsbController implements Service {
    private static Logger logger = LoggerFactory.getLogger(TzbEsbController.class);

    private static final FastDateFormat format1 = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat format2 = FastDateFormat.getInstance("hh:mm:ss");

    private ConsumerService consumerService;

    public TzbEsbController() {
        this.consumerService = (ConsumerService) WebApplicationContextUtil.getBean("consumerService");
    }

    @Override
    public CompositeData exec(CompositeData reqData) {
        ExternalRequest request = unpack(reqData);
        long bef = System.currentTimeMillis();
        Response response = consumerService.externalConsume(request);
        long now = System.currentTimeMillis();
        logger.debug("耗时:" + (now - bef) / 1000.0 + "秒");
        return pack(reqData, response);
    }

    /**
     * 拆包
     *
     * @param reqData
     * @return
     */
    private ExternalRequest unpack(CompositeData reqData) {
        logger.info("请求的报文如下: \n" + reqData.toString());

        CompositeData reqSysHead = reqData.getStruct("SYS_HEAD");
        CompositeData reqAppHead = reqData.getStruct("APP_HEAD");
        CompositeData reqBody = reqData.getStruct("BODY");

        /*********************************************
         ******************* 系统头 ******************
         *********************************************/
        // 服务代码
        String serviceCode = reqSysHead.getField("SERVICE_CODE").getValue().toString();
        // 服务场景
        String serviceScene = reqSysHead.getField("SERVICE_SCENE").getValue().toString();
        // 请求系统编号
        String consumerId = reqSysHead.getField("CONSUMER_ID").getValue().toString();
        // 发送方交易日期
        String tranDate = reqSysHead.getField("TRAN_DATE").getValue().toString();
        // 发送方交易时间
        String tranTimestamp = reqSysHead.getField("TRAN_TIMESTAMP").getValue().toString();
        // 发送方机构ID
        String branchId = reqSysHead.getField("BRANCH_ID").getValue().toString();
        // 服务请求者身份
        String userId = reqSysHead.getField("USER_ID").getValue().toString();
        // 服务请求者密码
        String userPassword = reqSysHead.getField("USER_PASSWORD").getValue().toString();
        // 交易流水号
        String consumerSeqNo = reqSysHead.getField("CONSUMER_SEQ_NO").getValue().toString();
        // 服务器标识（用于分行服务器，名称或者地址）
        // 注：我们这里要求是IP
        String serverId = reqSysHead.getField("SERVER_ID").getValue().toString();

        /*********************************************
         ******************** 应用体 *****************
         *********************************************/
        // 无

        /*********************************************
         ******************** 内容体 *****************
         *********************************************/
        // 服务请求者身份
        String appUser = reqBody.getField("appUser").getValue().toString();
        if (StringUtils.isBlank(appUser)) appUser = userId;
        // 请求服务名
        String serviceName = reqBody.getField("serviceName").getValue().toString();
        // 请求用户名
        String udspUser = reqBody.getField("udspUser").getValue().toString();
        if (StringUtils.isBlank(udspUser)) udspUser = userId;
        // 请求密码
        String token = reqBody.getField("token").getValue().toString();
        if (StringUtils.isBlank(token)) token = userPassword;
        // 请求类型 sync、async两种类型
        String type = reqBody.getField("type").getValue().toString();
        // 请求实体 start、status、stop三种实体
        String entity = reqBody.getField("entity").getValue().toString();
        // 分页信息 应用类型是“交互查询”时有效
        Page page = compositeDataToObj(reqBody.getStruct("page"), Page.class);
        // 请求SQL    应用类型是“联机查询”时有效
        String sql = reqBody.getField("sql").getValue().toString();
        // 超时时间（毫秒） 应用类型是“实时流-消费者”时有效
        // 规定时间内没有流数据可供消费，则消费结束。
        String timeout = reqBody.getField("timeout").getValue().toString();
        // 消费ID type=async且entity=status或stop有效
        String consumeId = reqBody.getField("consumeId").getValue().toString();
        // 请求数据 应用类型是“交互查询”时有效
        Map<String, String> data = compositeDataToMap(reqBody.getStruct("data"));
        // 请求数据集合   应用类型是“实时流-生产者”时有效
        List<Map<String, String>> datas = arrayToList(reqBody.getArray("datas"));
        // 请求服务器IP
        String requestIp = reqBody.getField("requestIp").getValue().toString();
        if (StringUtils.isBlank(requestIp)) requestIp = serverId;

        /**
         * 组ExternalRequest对象
         */
        ExternalRequest request = new ExternalRequest();
        request.setAppUser(appUser);
        request.setServiceName(serviceName);
        request.setUdspUser(udspUser);
        request.setToken(token);
        request.setType(type);
        request.setEntity(entity);
        request.setPage(page);
        request.setSql(sql);
        request.setTimeout(StringUtils.isBlank(timeout) ? 0 : Long.valueOf(timeout));
        request.setConsumeId(consumeId);
        request.setData(data);
        request.setDataStream(datas);
        request.setRequestIp(requestIp);

        return request;
    }

    /**
     * 组包
     *
     * @param response
     * @return
     */
    private CompositeData pack(CompositeData reqData, Response response) {
        // 记录集合
        List<Map<String, String>> records = response.getRecords();
        // 分页信息
        Page page = response.getPage();
        // 持续时间
        long consumeTime = response.getConsumeTime();
        // 状态
        String status = response.getStatus();
        // 错误信息
        String message = response.getMessage();
        // 消费ID
        String consumeId = response.getConsumeId();
        // 错误编码
        String errorCode = response.getErrorCode();
        // 返回信息
        String responseContent = response.getResponseContent();
        // 状态码
        String statusCode = response.getStatusCode();

        CompositeData rspData = new CompositeData();
        CompositeData rspBody = new CompositeData();
        CompositeData rspSysHead = new CompositeData();
        CompositeData rspAppHead = new CompositeData();

        /*********************************************
         ******************* 系统头 ******************
         *********************************************/
        CompositeData reqSysHead = reqData.getStruct("SYS_HEAD");
        // 服务代码
        rspSysHead.addField("SERVICE_CODE", reqSysHead.getField("SERVICE_CODE"));
        // 服务场景
        rspSysHead.addField("SERVICE_SCENE", reqSysHead.getField("SERVICE_SCENE"));
        // 请求系统编号
        rspSysHead.addField("CONSUMER_ID", reqSysHead.getField("CONSUMER_ID"));

        /*
        交易返回代码数组
         */
        Array retArray = new Array();
        CompositeData retData = new CompositeData();
        //交易返回代码 "RET_CODE",
        Field retCode = new Field(new FieldAttr(FieldType.FIELD_STRING, 30, 0));
        retCode.setValue(statusCode);
        //交易返回信息 "RET_MSG",
        Field retMsg = new Field(new FieldAttr(FieldType.FIELD_STRING, 512, 0));
        retMsg.setValue(message);
        retData.addField("RET_CODE", retCode);
        retData.addField("RET_MSG", retMsg);
        retArray.addStruct(retData);
        rspSysHead.addArray("RET", retArray);

        Date date = new Date();
        // 发送方交易日期
        Field tranDateField = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
        tranDateField.setValue(format1.format(date));
        rspSysHead.addField("TRAN_DATE", tranDateField);

        // 发送方交易时间
        Field tranTimeField = new Field(new FieldAttr(FieldType.FIELD_STRING, 9, 0));
        tranTimeField.setValue(format2.format(date));
        rspSysHead.addField("TRAN_TIMESTAMP", tranTimeField);

        // 交易状态（bS－系统处理成功；F－系统处理失败）
        Field retStatus = new Field(new FieldAttr(FieldType.FIELD_STRING, 1, 0));
        if (StatusCode.SUCCESS.getValue().equals(statusCode)) {
            retStatus.setValue("S");
        } else {
            retStatus.setValue("F");
        }
        rspSysHead.addField("RET_STATUS", retStatus);

        // 交易流水号
        rspSysHead.addField("CONSUMER_SEQ_NO", reqSysHead.getField("CONSUMER_SEQ_NO"));

        /*********************************************
         ******************** 应用体 *****************
         *********************************************/
        if (page != null) {
            // 当前记录号
            Field pageIndexField = new Field(new FieldAttr(FieldType.FIELD_STRING, 10, 0));
            pageIndexField.setValue(String.valueOf(page.getPageIndex()));
            rspAppHead.addField("CURRENT_NUM", pageIndexField);
            // 本页记录总数
            Field pageSizeField = new Field(new FieldAttr(FieldType.FIELD_STRING, 10, 0));
            pageSizeField.setValue(String.valueOf(page.getPageSize()));
            rspBody.addField("TOTAL_NUM", pageSizeField);
            // 总笔数
            Field totalCountField = new Field(new FieldAttr(FieldType.FIELD_STRING, 10, 0));
            totalCountField.setValue(String.valueOf(page.getTotalCount()));
            rspBody.addField("TOTAL_ROWS", totalCountField);
            // 是否结束（Y最后一页，查询结束）
            Field endFlagField = new Field(new FieldAttr(FieldType.FIELD_STRING, 1, 0));
            if (page.getTotalPage() <= page.getPageIndex()) {
                endFlagField.setValue("Y");
            } else {
                endFlagField.setValue("N");
            }
            rspAppHead.addField("END_FLAG", endFlagField);
        }

        /*********************************************
         ******************** 内容体 ******************
         *********************************************/
        // 记录集合
        rspBody.addArray("records", listToArray(records));
        // 分页信息
        rspBody.addStruct("page", objToCompositeData(page));
        // 持续时间
        Field consumeTimeField = new Field(new FieldAttr(FieldType.FIELD_LONG, 19, 0));
        consumeTimeField.setValue(consumeTime);
        rspBody.addField("consumeTime", consumeTimeField);
        // 状态
        Field statusField = new Field(new FieldAttr(FieldType.FIELD_STRING, 32, 0));
        statusField.setValue(status);
        rspBody.addField("status", statusField);
        // 错误信息
        Field messageField = new Field(new FieldAttr(FieldType.FIELD_STRING, 6000, 0));
        messageField.setValue(message);
        rspBody.addField("message", messageField);
        // 消费ID
        Field consumeIdField = new Field(new FieldAttr(FieldType.FIELD_STRING, 64, 0));
        consumeIdField.setValue(consumeId);
        rspBody.addField("consumeId", consumeIdField);
        // 错误编码
        Field errorCodeField = new Field(new FieldAttr(FieldType.FIELD_STRING, 16, 0));
        errorCodeField.setValue(errorCode);
        rspBody.addField("errorCode", errorCodeField);
        // 返回信息
        Field responseContentField = new Field(new FieldAttr(FieldType.FIELD_STRING, 6000, 0));
        responseContentField.setValue(responseContent);
        rspBody.addField("responseContent", responseContentField);
        // 状态码
        Field statusCodeField = new Field(new FieldAttr(FieldType.FIELD_INT, 10, 0));
        statusCodeField.setValue(statusCode);
        rspBody.addField("statusCode", statusCodeField);

        /**
         * 组CompositeData对象
         */
        rspData.addStruct("SYS_HEAD", rspSysHead);
        rspData.addStruct("APP_HEAD", rspAppHead);
        rspData.addStruct("BODY", rspBody);

        logger.debug("响应的报文如下: \n" + rspData.toString());

        return rspData;
    }

    /**
     * compositeData 转 Map<String, String>
     *
     * @param struct
     * @return
     */
    private Map<String, String> compositeDataToMap(CompositeData struct) {
        Map<String, String> data = new HashMap<>();
        Iterator it = struct.iterator();
        while (it.hasNext()) {
            Field field = (Field) it.next();
            String attr = field.getAttr().toString();
            String value = field.getValue().toString();
            data.put(attr, value);
        }
        return data;
    }

    /**
     * Array 转 List<Map<String, String>>
     *
     * @param array
     * @return
     */
    private List<Map<String, String>> arrayToList(Array array) {
        List<Map<String, String>> datas = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            datas.add(compositeDataToMap(array.getStruct(i)));
        }
        return datas;
    }

    /**
     * Map<String, String> 转 CompositeData
     *
     * @param data
     * @return
     */
    private CompositeData mapToCompositeData(Map<String, String> data) {
        CompositeData struct = new CompositeData();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String value = entry.getValue();
            Field valueField = new Field(new FieldAttr(FieldType.FIELD_STRING, value == null ? 0 : value.length(), 0));
            valueField.setValue(value);
            struct.addField(entry.getKey(), valueField);
        }
        return struct;
    }

    /**
     * List<Map<String, String>> 转 Array
     *
     * @param datas
     * @return
     */
    private Array listToArray(List<Map<String, String>> datas) {
        Array array = new Array();
        for (Map<String, String> data : datas) {
            array.addStruct(mapToCompositeData(data));
        }
        return array;
    }

    /**
     * Object 转 CompositeData
     *
     * @param obj
     * @param <T>
     * @return
     */
    private <T> CompositeData objToCompositeData(T obj) {
        CompositeData struct = new CompositeData();
        if (obj == null) {
            return struct;
        }
        Field fieldObj = null;
        java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            field.setAccessible(true); // 取消权限检查,暴力访问
            Class fieldType = field.getType();
            String fieldName = field.getName();
            fieldObj = new Field(new FieldAttr(FieldType.FIELD_STRING, Integer.MAX_VALUE, 0));
            fieldObj.setValue("");
            try {
                if (fieldType.equals(String.class)) {
                    String fieldValue = (String) field.get(obj);
                    fieldObj = new Field(new FieldAttr(FieldType.FIELD_STRING, fieldValue == null ? 0 : fieldValue.length(), 0));
                    fieldObj.setValue(fieldValue);
                } else if (fieldType.equals(Short.class)) {
                    Short fieldValue = (Short) field.get(obj);
                    fieldObj = new Field(new FieldAttr(FieldType.FIELD_SHORT, 5, 0));
                    fieldObj.setValue(fieldValue);
                } else if (fieldType.equals(Integer.class)) {
                    Integer fieldValue = (Integer) field.get(obj);
                    fieldObj = new Field(new FieldAttr(FieldType.FIELD_INT, 10, 0));
                    fieldObj.setValue(fieldValue);
                } else if (fieldType.equals(Long.class)) {
                    Long fieldValue = (Long) field.get(obj);
                    fieldObj = new Field(new FieldAttr(FieldType.FIELD_LONG, 19, 0));
                    fieldObj.setValue(fieldValue);
                } else if (fieldType.equals(Float.class)) {
                    Float fieldValue = (Float) field.get(obj);
                    fieldObj = new Field(new FieldAttr(FieldType.FIELD_FLOAT, 7, 3));
                    fieldObj.setValue(fieldValue);
                } else if (fieldType.equals(Double.class)) {
                    Double fieldValue = (Double) field.get(obj);
                    fieldObj = new Field(new FieldAttr(FieldType.FIELD_DOUBLE, 22, 6));
                    fieldObj.setValue(fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            struct.addField(fieldName, fieldObj);
        }
        return struct;
    }

    /**
     * CompositeData 转 Object
     *
     * @param struct
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T compositeDataToObj(CompositeData struct, Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.newInstance();
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true); // 取消权限检查,暴力访问
                Class fieldType = field.getType();
                String fieldName = field.getName();
                Field esbField = struct.getField(fieldName);
                if (esbField == null) {
                    continue;
                }
                if (fieldType.equals(String.class)) {
                    String fieldValue = (String) esbField.getValue();
                    field.set(obj, fieldValue);
                } else if (fieldType.equals(Short.class)) {
                    Short fieldValue = (Short) esbField.getValue();
                    field.set(obj, fieldValue);
                } else if (fieldType.equals(Integer.class)) {
                    Integer fieldValue = (Integer) esbField.getValue();
                    field.set(obj, fieldValue);
                } else if (fieldType.equals(Long.class)) {
                    Long fieldValue = (Long) esbField.getValue();
                    field.set(obj, fieldValue);
                } else if (fieldType.equals(Float.class)) {
                    Float fieldValue = (Float) esbField.getValue();
                    field.set(obj, fieldValue);
                } else if (fieldType.equals(Double.class)) {
                    Double fieldValue = (Double) esbField.getValue();
                    field.set(obj, fieldValue);
                } else if (fieldType.equals(Object.class)) {
                    Object fieldValue = esbField.getValue();
                    field.set(obj, fieldValue);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
