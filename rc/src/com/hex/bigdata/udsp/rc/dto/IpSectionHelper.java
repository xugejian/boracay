package com.hex.bigdata.udsp.rc.dto;

/**
 * 检查格式如[10-30]、[1-5,6-20]、[0,1-10]
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/25
 * TIME:10:26
 */
public class IpSectionHelper {
    /**
     * 等于
     */
    public static final String OPERATE_EQUALS="EQUALS";
    /**
     * 范围
     */
    public static final String OPERATE_SCOPE="SCOPE";

    /**
     * 操作
     */
    private String operate;

    /**
     * operate为EQUALS时用到-相等的数
     */
    private Integer equalNum;

    /**
     * operate为SCOPE时用到-比较大的数
     */
    private Integer higerNum;

    /**
     *operate为SCOPE时用到-比较小的数
     */
    private Integer lowerNum;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Integer getEqualNum() {
        return equalNum;
    }

    public void setEqualNum(Integer equalNum) {
        this.equalNum = equalNum;
    }

    public Integer getHigerNum() {
        return higerNum;
    }

    public void setHigerNum(Integer higerNum) {
        this.higerNum = higerNum;
    }

    public Integer getLowerNum() {
        return lowerNum;
    }

    public void setLowerNum(Integer lowerNum) {
        this.lowerNum = lowerNum;
    }


}
