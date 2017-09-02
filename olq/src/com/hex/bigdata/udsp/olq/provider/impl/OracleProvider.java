package com.hex.bigdata.udsp.olq.provider.impl;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.olq.model.OLQQuerySql;
import com.hex.bigdata.udsp.olq.provider.Provider;
import com.hex.bigdata.udsp.olq.provider.impl.model.OracleDatasource;
import com.hex.bigdata.udsp.olq.provider.model.OLQRequest;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponseFetch;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

/**
 * Created by PC on 2017/6/26.
 */
@Component("com.hex.bigdata.udsp.olq.provider.impl.OracleProvider")
public class OracleProvider implements Provider {

    private Logger logger = LogManager.getLogger(OracleProvider.class);
    private static Map<String, BasicDataSource> dataSourcePool;

    /**
     * 初始化
     *
     * @param datasource
     */
    public void init(Datasource datasource) {
        try {
            OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
            getConnection(oracleDatasource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private synchronized BasicDataSource getDataSource(OracleDatasource oracleDatasource) {
        String dsId = oracleDatasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, BasicDataSource>();
        }
        BasicDataSource dataSource = dataSourcePool.get(dsId);
        if (dataSource == null) {
            dataSource = new BasicDataSource();

            if (StringUtils.isNotBlank(oracleDatasource.getDriverClass()))
                dataSource.setDriverClassName(oracleDatasource.getDriverClass());
            if (StringUtils.isNotBlank(oracleDatasource.getJdbcUrl()))
                dataSource.setUrl(oracleDatasource.getJdbcUrl());
            if (StringUtils.isNotBlank(oracleDatasource.getUsername()))
                dataSource.setUsername(oracleDatasource.getUsername());
            if (StringUtils.isNotBlank(oracleDatasource.getPassword()))
                dataSource.setPassword(oracleDatasource.getPassword());
            if (StringUtils.isNotBlank(oracleDatasource.getInitialSize()))
                dataSource.setInitialSize(Integer.valueOf(oracleDatasource.getInitialSize()));// 数据库初始化时，创建的连接个数
            if (StringUtils.isNotBlank(oracleDatasource.getMinIdle()))
                dataSource.setMinIdle(Integer.valueOf(oracleDatasource.getMinIdle()));// 最小空闲连接数
            if (StringUtils.isNotBlank(oracleDatasource.getMaxIdle()))
                dataSource.setMaxIdle(Integer.valueOf(oracleDatasource.getMaxIdle()));// 数据库最大连接数
            if (StringUtils.isNotBlank(oracleDatasource.getMaxActive()))
                dataSource.setMaxActive(Integer.valueOf(oracleDatasource.getMaxActive()));// 设置最大并发数
            if (StringUtils.isNotBlank(oracleDatasource.getMaxWait()))
                dataSource.setMaxWait(Integer.valueOf(oracleDatasource.getMaxWait()));// 最长等待时间，单位毫秒
            if (StringUtils.isNotBlank(oracleDatasource.getValidationQuery()))
                dataSource.setValidationQuery(oracleDatasource.getValidationQuery()); // 验证链接的SQL语句，必须能返回一行及以上数据
            if (StringUtils.isNotBlank(oracleDatasource.getValidationQueryTimeout()))
                dataSource.setValidationQueryTimeout(Integer.valueOf(oracleDatasource.getValidationQueryTimeout())); // 自动验证连接的时间
            if (StringUtils.isNotBlank(oracleDatasource.getTimeBetweenEvictionRunsMillis()))
                dataSource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(oracleDatasource.getTimeBetweenEvictionRunsMillis())); // N毫秒检测一次是否有死掉的线程
            if (StringUtils.isNotBlank(oracleDatasource.getMinEvictableIdleTimeMillis()))
                dataSource.setMinEvictableIdleTimeMillis(Integer.valueOf(oracleDatasource.getMinEvictableIdleTimeMillis()));// 空闲连接N毫秒中后释放
            if (StringUtils.isNotBlank(oracleDatasource.getTestWhileIdle()))
                dataSource.setTestWhileIdle(Boolean.valueOf(oracleDatasource.getTestWhileIdle()));
            if (StringUtils.isNotBlank(oracleDatasource.getTestOnBorrow()))
                dataSource.setTestOnBorrow(Boolean.valueOf(oracleDatasource.getTestOnBorrow()));
            if (StringUtils.isNotBlank(oracleDatasource.getTestOnReturn()))
                dataSource.setTestOnReturn(Boolean.valueOf(oracleDatasource.getTestOnReturn()));

            dataSourcePool.put(dsId, dataSource);
        }
        return dataSource;
    }

    private Connection getConnection(OracleDatasource oracleDatasource) throws SQLException {
        Connection conn = null;
        BasicDataSource dataSource = getDataSource(oracleDatasource);
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }

    /**
     * 执行查询
     *
     * @param request
     * @return
     */
    public OLQResponse execute(OLQRequest request) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request));
        long bef = System.currentTimeMillis();

        OLQResponse response = new OLQResponse();
        response.setRequest(request);

        Datasource datasource = request.getDatasource();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        int count = 0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        try {
            conn = getConnection(oracleDatasource);
            stmt = conn.createStatement();
            //获取查询信息
            OLQQuerySql olqQuerySql = request.getOlqQuerySql();
            if (olqQuerySql.getPage() == null){
                rs = stmt.executeQuery(olqQuerySql.getOriginalSql());
            }else {
                rs = stmt.executeQuery(olqQuerySql.getPageSql());
            }
            rs.setFetchSize(1000);
            ResultSetMetaData rsmd = rs.getMetaData();
            response.setMetadata(rsmd);
            int columnCount = rsmd.getColumnCount();
            int max_num_size = oracleDatasource.getMaxNum();
            while (rs.next()) {
                map = new LinkedHashMap<String, String>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getString(i));
                }
                list.add(map);
                count++;
                if (count >= max_num_size) {
                    break;
                }
            }

            //查询的记录数总数
            String totalSql = olqQuerySql.getTotalSql();
            if (StringUtils.isNotBlank(totalSql)){
                rs = stmt.executeQuery(totalSql);
                int totalCount = 0;
                if (rs.next()){
                    totalCount = rs.getInt(1);
                }
                //设置总记录数
                olqQuerySql.getPage().setTotalCount(totalCount);
                response.setPage(olqQuerySql.getPage());
            }
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;

        response.setRecords(list);
        response.setConsumeTime(consumeTime);
        response.setTotalCount(count);

        logger.debug("consumeTime=" + response.getConsumeTime() + " recordsSize=" + response.getRecords().size());
        return response;
    }

    /**
     * 关闭数据源连接
     *
     * @param datasource
     */
    public void close(Datasource datasource) {
        BasicDataSource dataSource = dataSourcePool.remove(datasource.getId());
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试数据源连接
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = true;
        Connection connection = null;
        try {
            OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
            connection = getConnection(oracleDatasource);
            if (connection == null) {
                canConnection = false;
            } else {
                canConnection = true;
            }
        } catch (Exception e) {
            canConnection = false;
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return canConnection;
    }

    /**
     * 批处理查询
     *
     * @param request
     * @return
     */
    public OLQResponseFetch executeFetch(OLQRequest request) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request));
        long bef = System.currentTimeMillis();

        OLQResponseFetch response = new OLQResponseFetch();
        response.setRequest(request);

        Datasource datasource = request.getDatasource();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        try {
            conn = getConnection(oracleDatasource);
            stmt = conn.createStatement();
            OLQQuerySql olqQuerySql = request.getOlqQuerySql();
            rs = stmt.executeQuery(olqQuerySql.getOriginalSql());
            rs.setFetchSize(1000);
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
        }
        response.setConnection(conn);
        response.setStatement(stmt);
        response.setResultSet(rs);

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        logger.debug("consumeTime=" + response.getConsumeTime());
        return response;
    }

    @Override
    public OLQQuerySql getPageSql(String sql, Page page) {
        OLQQuerySql olqQuerySql = new OLQQuerySql(sql);
        if (page == null) {
            return olqQuerySql;
        }
        //分页sql组装
        int pageSize = page.getPageSize();
        int pageIndex = page.getPageIndex();
        pageIndex = pageIndex == 0 ? 1 : pageIndex;
        Integer startRow = (pageIndex - 1) * pageSize;
        Integer endRow = pageSize * pageIndex;
        StringBuffer pageSqlBuffer = new StringBuffer("select * from ( select rownum row_num,t.* from (");
        pageSqlBuffer.append(sql);
        pageSqlBuffer.append(")t)n");
        pageSqlBuffer.append(" where rownum>=");
        pageSqlBuffer.append(startRow);
        pageSqlBuffer.append(" and rownum<= ");
        pageSqlBuffer.append(endRow);
        olqQuerySql.setPageSql(pageSqlBuffer.toString());
        //总记录数查询SQL组装
        StringBuffer totalSqlBuffer = new StringBuffer("select count(*) from (");
        totalSqlBuffer.append(sql);
        totalSqlBuffer.append(")t");
        olqQuerySql.setTotalSql(totalSqlBuffer.toString());
        //page设置
        olqQuerySql.setPage(page);
        return olqQuerySql;
    }

    public static void main(String[] args) {
        String sql = "select PK_ID, NAME, DESCRIBE, TYPE, APP_ID, DEL_FLG, CRT_USER, CRT_TIME, UPT_USER, UPT_TIME from RC_SERVICE where DEL_FLG = '0' order by UPT_TIME desc, CRT_TIME desc, NAME";
        Page page = new Page();
        page.setPageIndex(2);
        page.setPageSize(20);
        OracleProvider oracleProvider = new OracleProvider();
        System.out.println(oracleProvider.getPageSql(sql, page));
    }

}
