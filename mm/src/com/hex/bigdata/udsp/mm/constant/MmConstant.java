package com.hex.bigdata.udsp.mm.constant;

/**
 * 模型管理常量
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/3
 * TIME:9:48
 */
public class MmConstant {
    /**
     * 接口业务类型-开始调用
     */
    public static final String MM_ENTITY_START = "start";
    /**
     * 接口业务类型-查看状态
     */
    public static final String MM_ENTITY_STATUS = "status";
    /**
     * 调用接口类型-同步
     */
    public static final String MM_TYPE_SYNC = "sync";
    /**
     * 调用接口类型-异步
     */
    public static final String MM_TYPE_ASYNC = "async";

    /**
     * 参数是否必输-是
     */
    public static final String MM_PARAM_IS_NEED_YES = "0";

    /**
     * 参数是否必输-否
     */
    public static final String MM_PARAM_IS_NEED_NO = "1";

    /**
     * 执行参数
     */
    public static final String MM_PARAM_EXE = "1";

    /**
     * 返回参数
     */
    public static final String MM_PARAM_RETURN = "2";

    /**
     * 模型status接口结果，SUCCESS 执行成功
     */
    public static final String MM_RUN_RETURN_SUCCESS = "SUCCESS" ;

    /**
     *模型status接口结果，SUCCESS 正在执行
     */
    public static final String MM_RUN_RETURN_RUNNING = "RUNNING" ;

    /**
     *模型status接口结果，SUCCESS 执行失败
     */
    public static final String MM_RUN_RETURN_DEFEAT = "DEFEAT" ;


}
