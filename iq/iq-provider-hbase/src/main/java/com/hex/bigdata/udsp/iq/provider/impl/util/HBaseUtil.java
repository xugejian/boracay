package com.hex.bigdata.udsp.iq.provider.impl.util;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseMetadata;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBasePage;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * Created by JunjieM on 2019-7-1.
 */
public class HBaseUtil {
    private static final int HBASE_SCAN_CACHING_SIZE = 1024; // 每次RPC请求记录数
    private static final int HBASE_SCAN_BATCH_SIZE = 1024; // 每一批获取记录数

    public static final String rkSep = "|";

    private static final String startStr = "";
    private static final String stopStr = "|";

    private static Logger logger = LogManager.getLogger (HBaseUtil.class);

    private static void close(Table table) {
        if (table != null) {
            try {
                table.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }

    public static long count(Table table, String startRow, String stopRow) throws Exception {
        Scan scan = new Scan ();
        setRowScan (scan, startRow, stopRow);
        /*
        使用聚合协处理器获取总行数，如果该表没有注册聚合协处理器则使用scan的方式获取总行数
         */
//        try {
//            return client.rowCount (table, new LongColumnInterpreter (), scan);
//        } catch (Throwable throwable) {
//            //logger.warn(throwable.getMessage());
//            logger.warn ("HBase表" + table.toString () + "没有注册集合协处理器，无法使用协处理器方式获取总行数！");
//            return count (table, scan);
//        }
        logger.warn ("HBase client 2.1.0-cdh6.3.2.jar" + table.toString () + "没有注册集合协处理器，无法使用协处理器方式获取总行数！使用scan的方式获取总行数");
        return count (table, scan);
    }

    public static long count(Table table, Scan scan) throws Exception {
        scan.setCaching (500);
        scan.setCacheBlocks (false);
        scan.setFilter (new FirstKeyOnlyFilter ());
        ResultScanner rs = table.getScanner (scan);
        long count = 0;
        while (rs.next () != null) {
            count++;
        }
        rs.close ();
        return count;
    }

    public static List<Map<String, String>> scan(Table table, Scan scan, Map<Integer, String> colMap,
                                                 byte[] family, byte[] qualifier, String separator, String dataType) throws Exception {
        ResultScanner rs = table.getScanner (scan);
        return getMaps (rs, colMap, family, qualifier, separator, dataType);
    }

    public static List<Map<String, String>> scan(HBaseDatasource datasource, String tableName, String startRow, String stopRow,
                                                 Map<Integer, String> colMap, HBaseMetadata metadata) throws Exception {
        List<Map<String, String>> list = null;
        Connection conn = null;
        Table table = null;
//        AggregationClient client = null;
        try {
            conn = HBaseConnectionPool.getConnection (datasource);
            table = conn.getTable (TableName.valueOf (tableName));
//            client = HBaseAggregationClientPool.getAggregationClient (datasource);
            list = scan (table, startRow, stopRow, colMap, metadata.gainFamilyName (),
                    metadata.gainQualifierName (), metadata.gainDsvSeparator (), metadata.gainFqDataType (),
                    datasource.gainMaxSize (), datasource.gainMaxSizeAlarm ());
        } finally {
            close (table);
        }
        return list;
    }

    public static List<Map<String, String>> scan(Table table, String startRow, String stopRow, Map<Integer, String> colMap,
                                                 byte[] family, byte[] qualifier, String separator, String dataType,
                                                 long maxSize) throws Exception {
        Scan scan = new Scan ();
        addColumn (scan, family, qualifier);
        setRowScan (scan, startRow, stopRow);
        scan.setFilter (new PageFilter (maxSize));
        return scan (table, scan, colMap, family, qualifier, separator, dataType);
    }

    public static List<Map<String, String>> scan(Table table,
                                                 String startRow, String stopRow, Map<Integer, String> colMap,
                                                 byte[] family, byte[] qualifier, String separator, String dataType,
                                                 long maxSize, boolean maxSizeAlarm) throws Exception {
        if (maxSizeAlarm) {
            long totalCount = count (table, startRow, stopRow);
            if (totalCount > maxSize) {
                throw new RuntimeException ("返回结果集大小超过了最大返回数据条数的限制");
            }
        }
        return scan (table, startRow, stopRow, colMap, family, qualifier, separator, dataType, maxSize);
    }

    public static List<Map<String, String>> scanPageList(Table table, HBasePage page,
                                                     Map<Integer, String> colMap, byte[] family, byte[] qualifier,
                                                     String separator, String dataType) throws Exception {
        Scan scan = new Scan ();
        addColumn (scan, family, qualifier);
        setRowScan (scan, page);
        return scanPage (table, scan, page, colMap, family, qualifier, separator, dataType);
    }

    public static List<Map<String, String>> scanPage(Table table, Scan scan, HBasePage page,
                                                     Map<Integer, String> colMap, byte[] family, byte[] qualifier,
                                                     String separator, String dataType) throws Exception {
        ResultScanner rs = table.getScanner (scan);
        return getMapsPage (rs, page, colMap, family, qualifier, separator, dataType);
    }

    public static HBasePage scanPage(HBaseDatasource datasource, String tableName, HBasePage page,
                                     Map<Integer, String> colMap, HBaseMetadata metadata) throws Exception {
        if (page.getPageSize () > datasource.gainMaxSize ()) {
            page.setPageSize (datasource.gainMaxSize ());
            if (datasource.gainMaxSizeAlarm ()) {
                throw new RuntimeException ("返回结果集大小超过了最大返回数据条数的限制");
            }
        }
        Connection conn = null;
        Table table = null;
//        AggregationClient client = null;
        try {
            conn = HBaseConnectionPool.getConnection (datasource);
            table = conn.getTable (TableName.valueOf (tableName));
//            client = HBaseAggregationClientPool.getAggregationClient (datasource);
            page = scanPage (table, page, colMap, metadata.gainFamilyName (),
                    metadata.gainQualifierName (), metadata.gainDsvSeparator (), metadata.gainFqDataType ());
        } finally {
            close (table);
        }
        return page;
    }

    public static HBasePage scanPage(Table table, HBasePage page, Map<Integer, String> colMap,
                                     byte[] family, byte[] qualifier, String separator, String dataType) throws Exception {
        page.setRecords (scanPageList (table, page, colMap, family, qualifier, separator, dataType));
        page.setTotalCount (count (table, page.getStartRow (), page.getStopRow ()));
        return page;
    }

    public static void addColumn(Scan scan, byte[] family, byte[] qualifier) {
        scan.addColumn (family, qualifier);
    }

    public static void setRowScan(Scan scan, String startRow, String stopRow) {
        scan.setCaching (HBASE_SCAN_CACHING_SIZE); // 每次RPC请求记录数
        // Cannot set batch on a scan using a filter that returns true for filter.hasFilterRow
        //scan.setBatch(HBASE_SCAN_BATCH_SIZE); // 每一批获取记录数
        if (startRow != null) {
            startRow = startRow + rkSep + startStr;
            scan.setStartRow (Bytes.toBytes (startRow));
        }
        if (stopRow != null) {
            stopRow = stopRow + rkSep + stopStr;
            scan.setStopRow (Bytes.toBytes (stopRow));
        }
    }

    public static void setRowScan(Scan scan, HBasePage page) {
        // 添加行键范围
        String startRow = page.getStartRow ();
        String stopRow = page.getStopRow ();
        setRowScan (scan, startRow, stopRow);
        // 添加分页过滤器
        int pageIndex = page.getPageIndex ();
        int pageSize = page.getPageSize ();
        int scanSize = pageSize * pageIndex; // 扫描的数据条数
        Filter pageFilter = new PageFilter (scanSize);
        scan.setFilter (pageFilter);
    }

    public static List<Map<String, String>> getMaps(ResultScanner rs, Map<Integer, String> colMap,
                                                    byte[] family, byte[] qualifier, String separator, String dataType) {
        List<Map<String, String>> list = new ArrayList<> ();
        for (Result r : rs) {
            list.add (getMap (r, colMap, family, qualifier, separator, dataType));
        }
        rs.close ();
        return list;
    }

    public static List<Map<String, String>> getMapsPage(ResultScanner rs, HBasePage page,
                                                        Map<Integer, String> colMap, byte[] family,
                                                        byte[] qualifier, String separator, String dataType) {
        int pageIndex = page.getPageIndex ();
        int pageSize = page.getPageSize ();
        long befNum = pageSize * (pageIndex - 1); // 不需要显示的数据条数
        long count = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
        for (Result r : rs) {
            count++;
            if (count > befNum) {
                list.add (getMap (r, colMap, family, qualifier, separator, dataType));
            }
        }
        rs.close ();
        return list;
    }

    public static Map<String, String> getMap(Result r, Map<Integer, String> colMap,
                                             byte[] family, byte[] qualifier, String separator, String dataType) {
        Map<String, String> map = new HashMap<String, String> ();
        String fqVal = Bytes.toString (r.getValue (family, qualifier));
        if (fqVal == null) fqVal = "";
        if (dataType.equalsIgnoreCase ("dsv")) { // 分隔符格式数据
            String[] fqVals = fqVal.split (separator, -1);
            // 注：如果上线后又修改需求，需要添加字段，则该检查需要注释掉
//        if (colMap.size() != fqVals.length) {
//            throw new RuntimeException("查询结果数与字段数不一致！");
//        }
            for (int i = 0; i < fqVals.length; i++) {
                String colName = colMap.get (i + 1);
                if (colName != null) {
                    map.put (colName, fqVals[i]);
                }
            }
        } else if (dataType.equalsIgnoreCase ("json")) { // JSON MAP格式数据
            Map<String, Object> result = JSONUtil.parseJSON2Map (fqVal);
            Set<Integer> keys = colMap.keySet ();
            for (Integer key : keys) {
                String colName = colMap.get (key);
                map.put (colName, result.get (colName).toString ());
            }
        }
        return map;
    }

    public static Map<String, String> get(String tableName, String rowkey,
                                          Map<Integer, String> colMap, HBaseMetadata metadata) throws Exception {
        Connection conn = null;
        Table table = null;
        Map<String, String> map = null;
        HBaseDatasource datasource = new HBaseDatasource (metadata.getDatasource ());
        try {
            conn = HBaseConnectionPool.getConnection (datasource);
            table = conn.getTable (TableName.valueOf (tableName));
            map = get (table, rowkey, colMap, metadata.gainFamilyName (),
                    metadata.gainQualifierName (), metadata.gainDsvSeparator (), metadata.gainFqDataType ());
        } finally {
            close (table);
        }
        return map;
    }

    public static List<Map<String, String>> gets(String tableName, List<String> rowkeys,
                                                 Map<Integer, String> colMap, HBaseMetadata metadata) throws Exception {
        Connection conn = null;
        Table table = null;
        List<Map<String, String>> list = null;
        HBaseDatasource datasource = new HBaseDatasource (metadata.getDatasource ());
        try {
            conn = HBaseConnectionPool.getConnection (datasource);
            table = conn.getTable (TableName.valueOf (tableName));
            list = gets (table, rowkeys, colMap, metadata.gainFamilyName (),
                    metadata.gainQualifierName (), metadata.gainDsvSeparator (), metadata.gainFqDataType ());
        } finally {
            close (table);
        }
        return list;
    }

    public static List<Map<String, String>> gets(Table table, List<String> rowkeys, Map<Integer, String> colMap,
                                                 byte[] family, byte[] qualifier, String separator, String dataType) throws Exception {
        List<Get> gets = new ArrayList<> ();
        for (String rowkey : rowkeys) {
            Get get = new Get (Bytes.toBytes (rowkey));
            addColumn (get, family, qualifier);
            gets.add (get);
        }
        return getMaps (table, gets, colMap, family, qualifier, separator, dataType);
    }

    public static List<Map<String, String>> getMaps(Table table, List<Get> gets, Map<Integer, String> colMap,
                                                    byte[] family, byte[] qualifier, String separator, String dataType) throws Exception {
        Result[] rs = table.get (gets);
        return getMaps (rs, colMap, family, qualifier, separator, dataType);
    }

    public static List<Map<String, String>> getMaps(Result[] rs, Map<Integer, String> colMap,
                                                    byte[] family, byte[] qualifier, String separator, String dataType) {
        List<Map<String, String>> list = null;
        if (rs != null && rs.length != 0) {
            list = new ArrayList<> ();
            for (Result r : rs) {
                list.add (getMap (r, colMap, family, qualifier, separator, dataType));
            }
        }
        return list;
    }

    public static Map<String, String> get(Table table, String rowkey, Map<Integer, String> colMap,
                                          byte[] family, byte[] qualifier, String separator, String dataType) throws Exception {
        Get get = new Get (Bytes.toBytes (rowkey));
        addColumn (get, family, qualifier);
        return get (table, get, colMap, family, qualifier, separator, dataType);
    }

    public static Map<String, String> get(Table table, Get get, Map<Integer, String> colMap,
                                          byte[] family, byte[] qualifier, String separator, String dataType) throws Exception {
        Result r = table.get (get);
        return getMap (r, colMap, family, qualifier, separator, dataType);
    }

    public static void addColumn(Get get, byte[] family, byte[] qualifier) {
        get.addColumn (family, qualifier);
    }

    /**
     * 连接是否异常
     *
     * @param datasource
     * @return
     */
    public static boolean isAborted(HBaseDatasource datasource) {
        return HBaseConnectionPool.getConnection (datasource) == null;
    }
}
