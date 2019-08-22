package com.hex.bigdata.udsp.olq.provider.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.StatementUtil;
import com.hex.bigdata.udsp.olq.provider.Provider;
import com.hex.bigdata.udsp.olq.provider.impl.model.JdbcDatasource;
import com.hex.bigdata.udsp.olq.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.olq.provider.model.OlqQuerySql;
import com.hex.bigdata.udsp.olq.provider.model.OlqRequest;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponseFetch;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2018/1/11.
 */
public abstract class JdbcProvider implements Provider {
    private static Logger logger = LogManager.getLogger (JdbcProvider.class);
    private static final int FETCH_SIZE = 1024;

    /**
     * 执行
     *
     * @param consumeId
     * @param request
     * @return
     */
    @Override
    public OlqResponse execute(String consumeId, OlqRequest request) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request));
        long bef = System.currentTimeMillis ();
        OlqResponse response = new OlqResponse ();
        response.setRequest (request);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Datasource datasource = request.getDatasource ();
            JdbcDatasource jdbcDatasource = new JdbcDatasource (datasource.getPropertyMap ());
            conn = JdbcUtil.getConnection (jdbcDatasource);
            stmt = conn.createStatement ();

            StatementUtil.putStatement (consumeId, stmt);

            OlqQuerySql olqQuerySql = getPageSql (request.getSql (), request.getPage ());
            if (olqQuerySql.getPage () == null) {
                rs = stmt.executeQuery (olqQuerySql.getOriginalSql ());
            } else {
                rs = stmt.executeQuery (olqQuerySql.getPageSql ());
            }

            // 防止部分数据库驱动不支持setFetchSize方法而抛异常
            try {
                rs.setFetchSize (FETCH_SIZE);
            } catch (Exception e) {
                //
            }

            LinkedHashMap<String, String> columns = getColumns (rs);
            response.setColumns (columns);
            // 根据结果集大小限制返回结果集数据
            List<Map<String, String>> records = getRecords (rs, jdbcDatasource.gainMaxSize (), jdbcDatasource.gainMaxSizeAlarm ());
            response.setRecords (records);
            response.setTotalCount (records.size ());
            response.setPage (getPage (stmt, olqQuerySql));

            response.setStatus (Status.SUCCESS);
            response.setStatusCode (StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace ();
            response.setStatus (Status.DEFEAT);
            response.setStatusCode (StatusCode.DEFEAT);
            response.setMessage (e.getMessage ());
            logger.error (ExceptionUtil.getMessage (e));
        } finally {
            JdbcUtil.close (rs, stmt, conn);
            StatementUtil.removeStatement (consumeId);
        }

        long now = System.currentTimeMillis ();
        long consumeTime = now - bef;
        response.setConsumeTime (consumeTime);
        logger.debug ("consumeTime=" + response.getConsumeTime ());
        return response;
    }

    private Page getPage(Statement stmt, OlqQuerySql olqQuerySql) throws SQLException {
        String totalSql = olqQuerySql.getTotalSql ();
        if (StringUtils.isNotBlank (totalSql)) {
            ResultSet rs = stmt.executeQuery (totalSql);
            long totalCount = 0;
            if (rs.next ()) {
                totalCount = rs.getLong (1);
            }
            olqQuerySql.getPage ().setTotalCount (totalCount);
            return olqQuerySql.getPage ();
        }
        return null;
    }

    private List<Map<String, String>> getRecords(ResultSet rs, int maxSize, boolean maxSizeAlarm) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData ();
        List<Map<String, String>> list = new ArrayList<> ();
        LinkedHashMap<String, String> map = null;
        int count = 0;
        while (rs.next ()) {
            map = new LinkedHashMap<> ();
            for (int i = 1; i <= rsmd.getColumnCount (); i++) {
                /*
                虽然源是Hive时返回字段别名是表名.字段名，但是由于查询SQL是自定义的很可能select中包含了函数而没有起别名，
                如果简单的获取点后面的字符串作为字段别名，很可能误处理select中包含了函数而没有起别名返回的字段别名，
                所以这里字段别名索性就保留原样。
                 */
                map.put (rsmd.getColumnLabel (i), rs.getString (i) == null ? "" : JSONUtil.encode (rs.getString (i)));
            }
            list.add (map);
            count++;
            if (count >= maxSize) {
                if (maxSizeAlarm) {
                    throw new RuntimeException ("返回结果集大小超过了最大返回数据条数的限制");
                }
                break;
            }
        }
        return list;
    }

    private LinkedHashMap<String, String> getColumns(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData ();
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<> ();
        for (int i = 1; i <= rsmd.getColumnCount (); i++) {
            columnMap.put (rsmd.getColumnLabel (i), rsmd.getColumnTypeName (i));
        }
        return columnMap;
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    @Override
    public boolean testDatasource(Datasource datasource) {
        Connection conn = null;
        try {
            JdbcDatasource jdbcDatasource = new JdbcDatasource (datasource.getPropertyMap ());
            conn = JdbcUtil.getConnection (jdbcDatasource);
            if (conn != null && !conn.isClosed ()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            JdbcUtil.close (null, null, conn);
        }
        return false;
    }

    /**
     * 执行（结果集一批批抽取）
     *
     * @param consumeId
     * @param request
     * @return
     */
    @Override
    public OlqResponseFetch executeFetch(String consumeId, OlqRequest request) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request));
        long bef = System.currentTimeMillis ();
        OlqResponseFetch response = new OlqResponseFetch ();
        response.setRequest (request);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Datasource datasource = request.getDatasource ();
            JdbcDatasource jdbcDatasource = new JdbcDatasource (datasource.getPropertyMap ());

            conn = JdbcUtil.getConnection (jdbcDatasource);
            stmt = conn.createStatement ();

            StatementUtil.putStatement (consumeId, stmt);

            OlqQuerySql olqQuerySql = getPageSql (request.getSql (), request.getPage ());
            if (olqQuerySql.getPage () == null) {
                rs = stmt.executeQuery (olqQuerySql.getOriginalSql ());
            } else {
                rs = stmt.executeQuery (olqQuerySql.getPageSql ());
            }
            rs.setFetchSize (FETCH_SIZE);

            response.setConnection (conn);
            response.setStatement (stmt);
            response.setResultSet (rs);

            response.setStatus (Status.SUCCESS);
            response.setStatusCode (StatusCode.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace ();
            response.setStatus (Status.DEFEAT);
            response.setStatusCode (StatusCode.DEFEAT);
            response.setMessage (e.getMessage ());
            logger.error (ExceptionUtil.getMessage (e));
        }

        long now = System.currentTimeMillis ();
        long consumeTime = now - bef;
        response.setConsumeTime (consumeTime);
        logger.debug ("consumeTime=" + response.getConsumeTime ());
        return response;
    }

    protected abstract OlqQuerySql getPageSql(String sql, Page page);
}
