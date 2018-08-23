package com.hex.bigdata.udsp.common.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 枚举转换
 */
public class EnumTrans {

    public static DataType transDataType(String dataTypeStr) {
        DataType type = DataType.STRING;
        if (StringUtils.isNotBlank(dataTypeStr)) {
            if (DataType.BIGINT.getValue().equals(dataTypeStr)) {
                type = DataType.BIGINT;
            } else if (DataType.BOOLEAN.getValue().equals(dataTypeStr)) {
                type = DataType.BOOLEAN;
            } else if (DataType.CHAR.getValue().equals(dataTypeStr)) {
                type = DataType.CHAR;
            } else if (DataType.DECIMAL.getValue().equals(dataTypeStr)) {
                type = DataType.DECIMAL;
            } else if (DataType.DOUBLE.getValue().equals(dataTypeStr)) {
                type = DataType.DOUBLE;
            } else if (DataType.FLOAT.getValue().equals(dataTypeStr)) {
                type = DataType.FLOAT;
            } else if (DataType.INT.getValue().equals(dataTypeStr)) {
                type = DataType.INT;
            } else if (DataType.SMALLINT.getValue().equals(dataTypeStr)) {
                type = DataType.SMALLINT;
            } else if (DataType.TIMESTAMP.getValue().equals(dataTypeStr)) {
                type = DataType.TIMESTAMP;
            } else if (DataType.TINYINT.getValue().equals(dataTypeStr)) {
                type = DataType.TINYINT;
            } else if (DataType.VARCHAR.getValue().equals(dataTypeStr)) {
                type = DataType.VARCHAR;
            }
        }
        return type;
    }

    public static Operator transOperator(String operatorStr) {
        Operator operator = Operator.EQ;
        if (StringUtils.isNotBlank(operatorStr)) {
            if (Operator.NE.getValue().equals(operatorStr)) {
                operator = Operator.NE;
            } else if (Operator.GT.getValue().equals(operatorStr)) {
                operator = Operator.GT;
            } else if (Operator.GE.getValue().equals(operatorStr)) {
                operator = Operator.GE;
            } else if (Operator.LT.getValue().equals(operatorStr)) {
                operator = Operator.LT;
            } else if (Operator.LE.getValue().equals(operatorStr)) {
                operator = Operator.LE;
            } else if (Operator.LK.getValue().equals(operatorStr)) {
                operator = Operator.LK;
            } else if (Operator.IN.getValue().equals(operatorStr)) {
                operator = Operator.IN;
            } else if (Operator.RLIKE.getValue().equals(operatorStr)) {
                operator = Operator.RLIKE;
            }
        }
        return operator;
    }

    public static Stats transStats(String statsStr) {
        Stats stats = Stats.NONE;
        if (StringUtils.isNotBlank(statsStr)) {
            if (Stats.AVG.getValue().equals(statsStr)) {
                stats = Stats.AVG;
            } else if (Stats.CONCAT.getValue().equals(statsStr)) {
                stats = Stats.CONCAT;
            } else if (Stats.COUNT.getValue().equals(statsStr)) {
                stats = Stats.COUNT;
            } else if (Stats.MAX.getValue().equals(statsStr)) {
                stats = Stats.MAX;
            } else if (Stats.MIN.getValue().equals(statsStr)) {
                stats = Stats.MIN;
            } else if (Stats.SUM.getValue().equals(statsStr)) {
                stats = Stats.SUM;
            }
        }
        return stats;
    }

    public static Order transOrder(String orderStr) {
        Order order = Order.ASC;
        if (StringUtils.isNotBlank(orderStr)) {
            if (Order.DESC.getValue().equals(orderStr)) {
                order = Order.DESC;
            }
        }
        return order;
    }

    public static boolean transTrue(String booleanStr) {
        Boolean flg = true;
        if (StringUtils.isNotBlank(booleanStr)) {
            if ("1".equals(booleanStr)) {
                flg = false;
            }
        }
        return flg;
    }

    public static ServiceStatus transServiceStatus(String serviceStatusStr) {
        ServiceStatus serviceStatus = ServiceStatus.START;
        if (StringUtils.isNotBlank(serviceStatusStr)) {
            if (ServiceStatus.STOP.getValue().equals(serviceStatusStr)) {
                serviceStatus = ServiceStatus.STOP;
            }
        }
        return serviceStatus;
    }
}
