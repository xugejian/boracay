package com.hex.bigdata.udsp.iq.provider.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Order;
import com.hex.bigdata.udsp.iq.provider.model.OrderColumn;
import com.hex.bigdata.udsp.iq.provider.model.ReturnColumn;

import java.util.*;

/**
 * Created by JunjieM on 2019-7-1.
 */
public class Util {

    /**
     * 字段过滤并字段名改别名
     *
     * @param list
     * @param returnColumns
     * @return
     */
    public static List<Map<String, String>> tranRecordsObject(List<Map<String, String>> list, List<ReturnColumn> returnColumns) {
        List<Map<String, String>> records = new ArrayList<> ();
        if (list == null || list.size () == 0) {
            return records;
        }
        Map<String, String> result = null;
        for (Map<String, String> map : list) {
            result = new HashMap<> ();
            for (ReturnColumn item : returnColumns) {
                result.put (item.getLabel (), map.get (item.getName ()));
            }
            records.add (result);
        }
        return records;
    }

    /**
     * 字段过滤并字段名改别名
     *
     * @param list
     * @param returnColumns
     * @return
     */
    public static List<Map<String, String>> tranRecords(List<Map<String, String>> list, List<ReturnColumn> returnColumns) {
        List<Map<String, String>> records = new ArrayList<> ();
        if (list == null || list.size () == 0) {
            return records;
        }
        Map<String, String> result = null;
        for (Map<String, String> map : list) {
            result = new HashMap<> ();
            for (ReturnColumn item : returnColumns) {
                result.put (item.getLabel (), map.get (item.getName ()));
            }
            records.add (result);
        }
        return records;
    }

    /**
     * 比较大小
     *
     * @param str1
     * @param str2
     * @param order
     * @param dataType
     * @return
     */
    public static int compareTo(String str1, String str2, Order order, DataType dataType) {
        if (dataType == null || DataType.STRING.equals (dataType) || DataType.VARCHAR.equals (dataType)
                || DataType.CHAR.equals (dataType) || DataType.TIMESTAMP.equals (dataType)) {
            if (order != null && Order.DESC.equals (order)) {
                if (str1.compareTo (str2) > 0) {
                    return -1;
                } else if (str1.compareTo (str2) == 0) {
                    return 0;
                }
                return 1;
            } else {
                if (str1.compareTo (str2) > 0) {
                    return 1;
                } else if (str1.compareTo (str2) == 0) {
                    return 0;
                }
                return -1;
            }
        } else {
            if (order != null && Order.DESC.equals (order)) {
                if (Double.valueOf (str1).compareTo (Double.valueOf (str2)) > 0) {
                    return -1;
                } else if (str1.compareTo (str2) == 0) {
                    return 0;
                }
                return 1;
            } else {
                if (Double.valueOf (str1).compareTo (Double.valueOf (str2)) > 0) {
                    return 1;
                } else if (str1.compareTo (str2) == 0) {
                    return 0;
                }
                return -1;
            }
        }
    }

    /**
     * 结果集排序
     *
     * @param records
     * @param orderColumns
     * @return
     */
    public static List<Map<String, String>> orderBy(List<Map<String, String>> records, final List<OrderColumn> orderColumns) {
        if (records == null || records.size () == 0) {
            return records;
        }
        Collections.sort (orderColumns, new Comparator<OrderColumn> () {
            @Override
            public int compare(OrderColumn obj1, OrderColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        // 多字段混合排序
        Collections.sort (records, new Comparator<Map<String, String>> () {
            @Override
            public int compare(Map<String, String> obj1, Map<String, String> obj2) {
                int flg = 0;
                for (OrderColumn orderColumn : orderColumns) {
                    String colName = orderColumn.getName ();
                    Order order = orderColumn.getOrder ();
                    DataType dataType = orderColumn.getType ();
                    String val1 = obj1.get (colName);
                    String val2 = obj2.get (colName);
                    if (val1 == null || val2 == null) {
                        throw new RuntimeException ("返回字段中不存在" + colName + "排序字段");
                    }
                    if (val1 != null && val2 != null) {
                        flg = Util.compareTo (val1, val2, order, dataType);
                        if (flg != 0) {
                            break;
                        }
                    }
                }
                return flg;
            }
        });
        return records;
    }

}
