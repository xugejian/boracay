package com.hex.bigdata.udsp.iq.util;

import com.hex.bigdata.udsp.common.constant.Operator;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/19
 * TIME:9:06
 */
public class IqCommonUtil {
    /**
     * 根据操作获取，操作名称
     * @param operator
     * @return
     */
    public static String getOperatorName(Operator operator){
        if (Operator.EQ.equals(operator)) {
            return Operator.EQ.getValue();
        } else if (Operator.GT.equals(operator)) {
            return Operator.GT.getValue();
        } else if (Operator.LT.equals(operator)) {
            return Operator.LT.getName();
        } else if (Operator.GE.equals(operator)) {
            return Operator.GE.getValue();
        } else if (Operator.LE.equals(operator)) {
            return Operator.LE.getValue();
        } else if (Operator.NE.equals(operator)) {
            return Operator.NE.getValue();
        } else if (Operator.LK.equals(operator)) {
            return Operator.LK.getValue();
        } else if (Operator.IN.equals(operator)) {
            return Operator.IN.getValue();
        }else if (Operator.RLIKE.equals(operator)){
            return Operator.RLIKE.getValue();
        }
        return null;
    }
}
