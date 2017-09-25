package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;

/**
 * Created by JunjieM on 2017-9-25.
 */
public class SqlUtil {
    public static String getCondition(String value, DataType type, Operator operator) {
        String str = "";
        if (DataType.INT == type || DataType.TINYINT == type || DataType.DOUBLE == type
                || DataType.DECIMAL == type || DataType.BIGINT == type || DataType.FLOAT == type
                || DataType.SMALLINT == type) {
            if (Operator.EQ == operator) {
                str = " = " + value;
            } else if (Operator.NE == operator) {
                str = " != " + value;
            } else if (Operator.GE == operator) {
                str = " >= " + value;
            } else if (Operator.GT == operator) {
                str = " > " + value;
            } else if (Operator.LE == operator) {
                str = " <= " + value;
            } else if (Operator.LT == operator) {
                str = " < " + value;
            } else if (Operator.IN == operator) {
                str = " IN (" + value + ")";
            } else if (Operator.LK == operator) {
                str = " LIKE '%" + value + "%'";
            } else if (Operator.RLIKE == operator) {
                str = " LIKE '" + value + "%'";
            }
        } else {
            if (Operator.EQ == operator) {
                str = " = '" + value + "'";
            } else if (Operator.NE == operator) {
                str = " != '" + value + "'";
            } else if (Operator.GE == operator) {
                str = " >= '" + value + "'";
            } else if (Operator.GT == operator) {
                str = " > '" + value + "'";
            } else if (Operator.LE == operator) {
                str = " <= '" + value + "'";
            } else if (Operator.LT == operator) {
                str = " < '" + value + "'";
            } else if (Operator.IN == operator) {
                String[] strs = value.split(",");
                for (int j = 0; j < strs.length; j++) {
                    if (j == 0) {
                        value = "'" + strs[j] + "'";
                    } else {
                        value += ",'" + strs[j] + "'";
                    }
                }
                str = " IN (" + value + ")";
            } else if (Operator.LK == operator) {
                str = " LIKE '%" + value + "%'";
            } else if (Operator.RLIKE == operator) {
                str = " LIKE '" + value + "%'";
            }
        }
        return str;
    }
}
