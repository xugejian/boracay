package com.hex.bigdata.udsp.common.aggregator;

import com.hex.bigdata.udsp.common.aggregator.constant.H2DataType;
import com.hex.bigdata.udsp.common.aggregator.model.H2DataColumn;
import com.hex.bigdata.udsp.common.aggregator.model.H2Response;
import com.hex.bigdata.udsp.common.aggregator.util.H2SqlUtil;
import com.hex.bigdata.udsp.common.util.MD5Util;
import com.hex.bigdata.udsp.dsl.DslSqlAdaptor;
import com.hex.bigdata.udsp.dsl.model.Component;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * H2聚合器
 */
@Repository
public class H2Aggregator {
    private static Logger logger = LogManager.getLogger (H2Aggregator.class);

    private static final FastDateFormat format = FastDateFormat.getInstance ("yyyy-MM-dd HH:mm:ss");

    private static final String TBL_PREFIX = "CACHE_";
    private static final int BATCH_SIZE = 20000;

    public static final String CACHE_NAME = "CACHE_NAME";

    @Autowired
    @Qualifier("h2DataSource")
    private BasicDataSource h2DataSource;

    /**
     * H2表名和超时时间戳映射
     * Map<String table_name, Long end_timestamp>
     */
    private static Map<String, Long> h2TableAndTimeMap = new ConcurrentHashMap<> ();

    /**
     * H2表名和服务名映射
     * Map<String table_name, String service_name>
     */
    private static Map<String, String> h2TableAndServiceMap = new ConcurrentHashMap<> ();

    /**
     * 添加H2表名和服务名映射关系
     *
     * @param tableName
     * @param serviceName
     */
    public static void putH2TableAndService(String tableName, String serviceName) {
        if (StringUtils.isNotBlank (tableName) && StringUtils.isNotBlank (serviceName)) {
            h2TableAndServiceMap.put (tableName, serviceName);
        }
    }

    /**
     * 获取表名称（大写）
     *
     * @param serviceName
     * @param component
     * @return
     */
    public String getH2TableName(String serviceName, Component component) {
        return getH2TableNamePrefix (serviceName) + getH2TableNameSuffix (component);
    }

    /**
     * 获取表名称前缀（大写）
     *
     * @param serviceName
     * @return
     */
    private String getH2TableNamePrefix(String serviceName) {
        return TBL_PREFIX + MD5Util.MD5_16 (serviceName).toUpperCase () + "_";
    }

    /**
     * 获取表名称后缀（大写）
     *
     * @param component
     * @return
     */
    private String getH2TableNameSuffix(Component component) {
        return MD5Util.MD5_16 (DslSqlAdaptor.componentToStatement (component)).toUpperCase ();
    }

    /**
     * 加载数据操作
     *
     * @param tableName
     * @param columns
     * @param records
     */
    public void load(String tableName, List<H2DataColumn> columns, List<Map<String, String>> records, long timeout) {
        if (records == null) {
            return;
        }
        long bef = System.currentTimeMillis ();
        // before Load
        logger.info (tableName + " before Load");
        beforeLoad (tableName, columns);
        // Load data
        logger.info (tableName + " Load data");
        if (records.size () != 0) {
            try (Connection conn = h2DataSource.getConnection ();
                 PreparedStatement ps = conn.prepareStatement (H2SqlUtil.insertInto (tableName, columns.size ()));
            ) {
                H2DataColumn column = null;
                String colName = "";
                H2DataType dataType = null;
                String value = "";
                int count = 0;
                for (Map<String, String> record : records) {
                    for (int i = 1; i <= columns.size (); i++) {
                        column = columns.get (i - 1);
                        colName = column.getColName ();
                        dataType = column.getDataType ();
                        value = record.get (colName);
                        try {
                            switch (dataType) {
                                case VARCHAR:
                                case CHAR:
                                    ps.setString (i, value);
                                    break;
                                case DECIMAL:
                                    ps.setBigDecimal (i, new BigDecimal (value));
                                    break;
                                case DOUBLE:
                                    ps.setDouble (i, Double.parseDouble (value));
                                    break;
                                case INT:
                                    ps.setInt (i, Integer.parseInt (value));
                                    break;
                                case TINYINT:
                                    ps.setByte (i, Byte.parseByte (value));
                                    break;
                                case SMALLINT:
                                    ps.setShort (i, Short.parseShort (value));
                                    break;
                                case FLOAT:
                                    ps.setFloat (i, Float.parseFloat (value));
                                    break;
                                case BIGINT:
                                    ps.setLong (i, Long.parseLong (value));
                                    break;
                                case TIMESTAMP:
                                    ps.setTimestamp (i, Timestamp.valueOf (value));
                                    break;
                                case BOOLEAN:
                                    ps.setBoolean (i, Boolean.parseBoolean (value));
                                    break;
                                default:
                                    ps.setString (i, value);
                            }
                        } catch (Exception e) {
                            //e.printStackTrace ();
                            logger.warn (e.getMessage ());
                            ps.setObject (i, null);
                        }
                    }
                    ps.addBatch ();
                    if (++count % BATCH_SIZE == 0) {
                        ps.executeBatch ();
                        logger.info ("H2 load batch {}", count);
                    }
                }
                ps.executeBatch ();
            } catch (SQLException e) {
                e.printStackTrace ();
                throw new RuntimeException (e.getMessage ());
            }
        }
        // after Load
        logger.info (tableName + " after Load");
        afterLoad (tableName, timeout);
        logger.info ("H2 Database loadBatch using time: {} ms", System.currentTimeMillis () - bef);
    }

    /**
     * 加载数据前操作
     *
     * @param tableName
     * @param columns
     */
    private void beforeLoad(String tableName, List<H2DataColumn> columns) {
        dropTable (tableName);
        createTable (tableName, columns);
    }

    /**
     * 加载数据后操作
     *
     * @param tableName
     * @param timeout
     */
    private void afterLoad(String tableName, long timeout) {
        if (timeout != 0) {
            h2TableAndTimeMap.put (tableName, System.currentTimeMillis () + (timeout * 1000));
        }
    }

    /**
     * 查询H2数据库
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public H2Response query(String sql) {
        logger.info ("Execute: {}", sql);
        long bef = System.currentTimeMillis ();
        H2Response response = new H2Response ();
        List<Map<String, String>> records = new ArrayList<> ();
        LinkedHashMap<String, String> columns = new LinkedHashMap<> ();
        Map<String, String> record = null;
        String columnLabel = "";
        try (Connection conn = h2DataSource.getConnection ();
             Statement stat = conn.createStatement ();
             ResultSet rs = stat.executeQuery (sql)) {
            ResultSetMetaData metadata = rs.getMetaData ();
            int count = metadata.getColumnCount ();
            for (int i = 1; i <= count; i++) {
                columns.put (metadata.getColumnLabel (i), metadata.getColumnTypeName (i));
            }
            while (rs.next ()) {
                record = new HashMap<> ();
                for (int i = 1; i <= count; i++) {
                    columnLabel = metadata.getColumnLabel (i);
                    record.put (columnLabel, rs.getString (columnLabel));
                }
                records.add (record);
            }
        } catch (Exception e) {
            e.printStackTrace ();
            throw new RuntimeException (e.getMessage ());
        }
        response.setColumns (columns);
        response.setRecords (records);
        logger.info ("H2 Database query using time: {} ms", System.currentTimeMillis () - bef);
        return response;
    }

    /**
     * 是否可以重新加载
     *
     * @param tableName
     * @return
     */
    public boolean isReload(String tableName) {
        return isTimeout (tableName) || !isTableExists (tableName);
    }

    // 判断h2数据是否超时
    private boolean isTimeout(String tableName) {
        Long endTimestamp = h2TableAndTimeMap.get (tableName);
        return endTimestamp == null || System.currentTimeMillis () > endTimestamp;
    }

    /**
     * 检查表是否存在于h2数据库中
     *
     * @param tableName
     * @return
     */
    public boolean isTableExists(String tableName) {
        boolean exists = false;
        String sql = H2SqlUtil.tablesInfo (tableName);
        try (Connection conn = h2DataSource.getConnection ();
             Statement stat = conn.createStatement ();
             ResultSet rs = stat.executeQuery (sql)) {
            int count = 0;
            while (rs.next ()) {
                count = rs.getInt (1);
            }
            if (count > 0) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace ();
            throw new RuntimeException (e.getMessage ());
        }
        return exists;
    }

    /**
     * 获取服务的缓存名称
     *
     * @param serviceName
     * @return
     */
    public List<Map<String, String>> getServiceCaches(String serviceName) {
        return getCaches (getH2TableNamePrefix (serviceName));
    }

    /**
     * 获取所有的缓存名称
     *
     * @return
     */
    public List<Map<String, String>> getAllCaches() {
        return getCaches (TBL_PREFIX);
    }

    /**
     * 获取表信息列表
     *
     * @param tableNamePrefix
     * @return
     */
    public List<Map<String, String>> getTablesInfo(String tableNamePrefix) {
        List<Map<String, String>> list = new ArrayList<> ();
        String sql = H2SqlUtil.tableList (tableNamePrefix);
        logger.info ("Execute: {}", sql);
        Map<String, String> map = null;
        String colName = null;
        String colValue = null;
        try (Connection conn = h2DataSource.getConnection ();
             Statement stat = conn.createStatement ();
             ResultSet rs = stat.executeQuery (sql)) {
            ResultSetMetaData md = rs.getMetaData ();
            while (rs.next ()) {
                map = new HashMap<> ();
                for (int i = 1; i <= md.getColumnCount (); i++) {
                    colName = md.getColumnLabel (i);
                    colValue = rs.getString (colName);
                    map.put (colName, colValue);
                }
                list.add (map);
            }
        } catch (Exception e) {
            e.printStackTrace ();
            throw new RuntimeException (e.getMessage ());
        }
        return list;
    }

    /**
     * 获取缓存信息列表
     *
     * @param tableNamePrefix
     * @return
     */
    public List<Map<String, String>> getCaches(String tableNamePrefix) {
        List<Map<String, String>> records = new ArrayList<> ();
        List<Map<String, String>> list = getTablesInfo (tableNamePrefix);
        if (list != null && list.size () != 0) {
            Map<String, String> record = null;
            String key = null;
            String value = null;
            for (Map<String, String> map : list) {
                String tableName = map.get ("TABLE_NAME");
                if (isTimeout (tableName)) { // 超时
                    logger.info (tableName + " cache timeout");
                    dropTable (tableName); // 删表
                    continue;
                }
                record = new HashMap<> ();
                String endTime = "";
                String serviceName = "";
                if (StringUtils.isNotBlank (tableName)) {
                    Long endTimestamp = h2TableAndTimeMap.get (tableName);
                    if (endTimestamp != null && endTimestamp > 0) {
                        try {
                            endTime = format.format (new Date (endTimestamp));
                        } catch (Exception e) {
                            //
                        }
                    }
                    serviceName = h2TableAndServiceMap.get (tableName);
                    if (serviceName == null) {
                        serviceName = "";
                    }
                }
                // 缓存的结束时间
                record.put ("CACHE_END_TIME", endTime);
                // 缓存的服务名称
                record.put ("SERVICE_NAME", serviceName);
                // 缓存的其他信息
                for (Map.Entry<String, String> entry : map.entrySet ()) {
                    key = entry.getKey ();
                    value = entry.getValue ();
                    if ("TABLE_NAME".equals (key)) {
                        key = CACHE_NAME;
                    } else if ("SQL".equals (key)) {
                        key = "CACHE_CONTENT";
                    } else {
                        continue;
                    }
                    record.put (key, value);
                }
                records.add (record);
            }
        }
        return records;
    }

    /**
     * 删除表
     *
     * @param tableName
     * @return
     */
    public void dropTable(String tableName) {
        String sql = H2SqlUtil.dropTable (tableName);
        logger.info ("Execute: {}", sql);
        try (Connection conn = h2DataSource.getConnection ();
             Statement stat = conn.createStatement ();) {
            stat.execute (sql);
        } catch (Exception e) {
            e.printStackTrace ();
            throw new RuntimeException (e.getMessage ());
        }
    }

    /**
     * 创建表
     *
     * @param tableName
     * @param columns
     */
    private void createTable(String tableName, List<H2DataColumn> columns) {
        try (Connection conn = h2DataSource.getConnection ();
             Statement stmt = conn.createStatement ();) {
            String createTableSql = H2SqlUtil.createTable (tableName, columns);
            logger.info ("Execute: {}", createTableSql);
            stmt.execute (createTableSql);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new RuntimeException (e.getMessage ());
        }
    }

    /**
     * 清空数据库
     *
     * @return
     */
    public boolean cleanDatabase() {
        String sql = H2SqlUtil.resetDB ();
        logger.info ("Execute: {}", sql);
        try (Connection conn = h2DataSource.getConnection ();
             Statement stmt = conn.createStatement ();) {
            stmt.execute (sql);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new RuntimeException (e.getMessage ());
        }
        return true;
    }
}
