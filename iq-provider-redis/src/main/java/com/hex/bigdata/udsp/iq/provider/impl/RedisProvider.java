package com.hex.bigdata.udsp.iq.provider.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.model.RedisDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.util.RedisUtil;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslRequest;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslResponse;
import com.hex.bigdata.udsp.iq.provider.util.Util;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * redis接口实现类
 */
public class RedisProvider implements Provider {
    private static Logger logger = LogManager.getLogger (RedisProvider.class);
    private static final String rkSep = "|";
    private static final String tableColumnSeq = ":";

    @Override
    public IqResponse query(IqRequest request) {
        return query (request, null);
    }

    private String getRedisQuery(List<DataColumn> metaDataQueryColumns, List<QueryColumn> queryColumns, String tableName) {
        Map<Short, String> canQueryColumn = new HashMap ();
        for (DataColumn metaDataQueryColumn : metaDataQueryColumns) {
            canQueryColumn.put (metaDataQueryColumn.getSeq (), metaDataQueryColumn.getName ());
        }
        Map<String, QueryColumn> queryColumnValueMap = new HashMap ();
        for (QueryColumn queryColumn : queryColumns) {
            queryColumnValueMap.put (queryColumn.getName (), queryColumn);
        }
        StringBuffer queryString = new StringBuffer ("");
        queryString.append (tableName);
        queryString.append (tableColumnSeq);
        Operator operator;
        for (Short i = 1; canQueryColumn.get (i) != null; i++) {

            QueryColumn queryColumn = queryColumnValueMap.get (canQueryColumn.get (i));
            if (queryColumn == null || StringUtils.isBlank (queryColumn.getValue ())) {
                queryString.append ("*");
                queryString.append (rkSep);
                continue;
            }
            operator = queryColumn.getOperator ();
            if (Operator.LK.equals (operator)) {
                queryString.append ("*");
                queryString.append (queryColumn.getValue ());
                queryString.append ("*");
            } else if (Operator.RLIKE.equals (operator)) {
                queryString.append (queryColumn.getValue ());
                queryString.append ("*");
            } else if (Operator.EQ.equals (operator)) {
                queryString.append (queryColumn.getValue ());
            } else {
                throw new IllegalArgumentException ("redis不支持" + operator.getValue () + "操作类型！");
            }
            //最后一个|不用去除的，防止模糊匹配时匹配到错误数据
            queryString.append (rkSep);

        }
        return queryString.toString ();
    }

    @Override
    public IqResponse query(IqRequest request, Page page) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request));
        long bef = System.currentTimeMillis ();
        IqResponse response = new IqResponse ();

        try {
            Application application = request.getApplication ();
            Metadata metadata = application.getMetadata ();
            List<QueryColumn> queryColumns = application.getQueryColumns ();
            List<ReturnColumn> returnColumns = application.getReturnColumns ();
            List<OrderColumn> orderColumns = application.getOrderColumns ();
            List<DataColumn> metaReturnColumns = metadata.getReturnColumns ();
            RedisDatasource redisDatasource = new RedisDatasource (metadata.getDatasource ());
            String tableName = metadata.getTbName ();
            String query = getRedisQuery (metadata.getQueryColumns (), queryColumns, tableName);
            String fqSep = redisDatasource.gainSeprator ();
            List<Map<String, String>> records = null;
            if (page != null) {
                page.setTotalCount (getCountNum (query, redisDatasource));
                records = search (fqSep, query, redisDatasource, metaReturnColumns, page);
            } else {
                records = search (fqSep, query, redisDatasource, metaReturnColumns, redisDatasource.gainMaxSize ());
            }
            records = Util.orderBy (records, orderColumns); // 排序
            records = Util.tranRecords (records, returnColumns); // 字段过滤并字段名改别名
            response.setPage (page);
            response.setRecords (records);
            response.setStatus (Status.SUCCESS);
            response.setStatusCode (StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace ();
            response.setStatus (Status.DEFEAT);
            response.setStatusCode (StatusCode.DEFEAT);
            response.setMessage (e.toString ());
        }

        long now = System.currentTimeMillis ();
        long consumeTime = now - bef;
        response.setConsumeTime (consumeTime);

        logger.debug ("consumeTime=" + response.getConsumeTime ());
        return response;
    }

    private List<Map<String, String>> search(String fqSep, String queryString, RedisDatasource datasource,
                                             List<DataColumn> returnColumns, Page page) {
        int pageIndex = page.getPageIndex ();
        int pageSize = page.getPageSize ();
        int startRow = (pageIndex - 1) * pageSize;
        int endRow = pageIndex * pageSize;
        return search (fqSep, queryString, datasource, returnColumns, startRow, endRow);
    }

    private List<Map<String, String>> search(String fqSep, String queryString, RedisDatasource datasource,
                                             List<DataColumn> returnColumns, int startRow, int endRow) {
        Jedis jedis = RedisUtil.getConnection (datasource);
        List<Map<String, String>> records = new ArrayList<> ();
        String[] returnResults;
        Map<String, String> record;
        try {
            //获取模糊匹配的key
            Set<String> keys = jedis.keys (queryString);
            String[] results = new String[keys.size ()];
            results = keys.toArray (results);
            for (int i = 0; i < results.length && i <= endRow && i >= startRow; i++) {
                record = new HashMap<String, String> ();
                returnResults = jedis.get (results[i]).split (fqSep);
                for (DataColumn returnColumn : returnColumns) {
                    record.put (returnColumn.getName (), returnResults[returnColumn.getSeq () - 1]);
                }
                records.add (record);
            }
        } finally {
            RedisUtil.release (datasource, jedis);
        }
        return records;
    }


    private int getCountNum(String queryString, RedisDatasource datasource) {
        Jedis jedis = RedisUtil.getConnection (datasource);
        //获取模糊匹配的key
        try {
            Set<String> keys = jedis.keys (queryString);
            return keys.size ();
        } finally {
            RedisUtil.release (datasource, jedis);
        }
    }

    private List<Map<String, String>> search(String fqSep, String queryString, RedisDatasource datasource, List<DataColumn> returnColumns, int maxNum) {
        Jedis jedis = RedisUtil.getConnection (datasource);

        List<Map<String, String>> records = new ArrayList<> ();
        String[] returnResults;
        Map<String, String> record;
        try {
            //获取模糊匹配的key
            Set<String> keys = jedis.keys (queryString);

            String[] results = new String[keys.size ()];
            results = keys.toArray (results);
            for (int i = 0; i < results.length; i++) {
                record = new HashMap<String, String> ();
                returnResults = jedis.get (results[i]).split (fqSep);
                for (DataColumn returnColumn : returnColumns) {
                    record.put (returnColumn.getName (), returnResults[returnColumn.getSeq () - 1]);
                }
                records.add (record);
                if (records.size () >= maxNum) {
                    break;
                }
            }
        } finally {
            RedisUtil.release (datasource, jedis);
        }
        return records;
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        Jedis jedis = null;
        RedisDatasource redisDatasource = new RedisDatasource (datasource);
        try {
            jedis = RedisUtil.getConnection (redisDatasource);
            return jedis.isConnected ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            RedisUtil.release (redisDatasource, jedis);
        }
        return false;
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        return null;
    }

    @Override
    public IqDslResponse select(IqDslRequest request) {
        throw new RuntimeException ("Redis目前暂时不支持DSL");
    }
}
