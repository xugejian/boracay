package com.hex.bigdata.udsp.iq.provider.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Order;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.model.*;
import com.hex.bigdata.udsp.iq.provider.impl.util.HBaseUtil;
import com.hex.bigdata.udsp.iq.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslRequest;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslResponse;
import com.hex.bigdata.udsp.iq.provider.util.Util;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by junjiem on 2017-2-15.
 */
public class SolrHBaseProvider implements Provider {
    private static final int HBASE_GET_BATCH_SIZE = 1024;

    private static Logger logger = LoggerFactory.getLogger (SolrHBaseProvider.class);

    @Override
    public IqResponse query(IqRequest request) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request));
        long bef = System.currentTimeMillis ();
        IqResponse response = new IqResponse ();

        try {
            Application application = request.getApplication ();
            Metadata metadata = application.getMetadata ();
            List<QueryColumn> queryColumns = application.getQueryColumns ();
            List<ReturnColumn> returnColumns = application.getReturnColumns ();
            List<OrderColumn> orderColumns = application.getOrderColumns ();
            String tbName = metadata.getTbName ();
            SolrHBaseMetadata solrHBaseMetadata = new SolrHBaseMetadata (metadata.getPropertyMap ());
            List<DataColumn> metaReturnColumns = metadata.getReturnColumns ();
            SolrHBaseDatasource solrHBaseDatasource = new SolrHBaseDatasource (metadata.getDatasource ());
            String primaryKey = solrHBaseMetadata.gainSolrPrimaryKey ();
            SolrQuery query = SolrUtil.getSolrQuery (queryColumns, getSort (queryColumns, orderColumns),
                    primaryKey, solrHBaseDatasource.gainMaxSize ());
            Map<Integer, String> colMap = getColMap (metaReturnColumns);
            List<Map<String, String>> records = search (solrHBaseDatasource, tbName, query, colMap, solrHBaseMetadata);
            records = orderBy (records, queryColumns, orderColumns); // 排序处理
            records = Util.tranRecords (records, returnColumns); // 字段过滤并字段名改别名
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

    @Override
    public IqResponse query(IqRequest request, Page page) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request)
                + " pageIndex=" + page.getPageIndex () + " pageSize=" + page.getPageSize ());
        long bef = System.currentTimeMillis ();
        IqResponse response = new IqResponse ();

        try {
            Application application = request.getApplication ();
            Metadata metadata = application.getMetadata ();
            List<QueryColumn> queryColumns = application.getQueryColumns ();
            List<ReturnColumn> returnColumns = application.getReturnColumns ();
            List<OrderColumn> orderColumns = application.getOrderColumns ();
            String tbName = metadata.getTbName ();
            SolrHBaseMetadata solrHBaseMetadata = new SolrHBaseMetadata (metadata.getPropertyMap ());
            List<DataColumn> metaReturnColumns = metadata.getReturnColumns ();
            SolrHBaseDatasource solrHBaseDatasource = new SolrHBaseDatasource (metadata.getDatasource ());
            SolrQuery query = SolrUtil.getSolrQuery (queryColumns, getSort (queryColumns, orderColumns),
                    solrHBaseMetadata.gainSolrPrimaryKey (), page.getPageIndex (), page.getPageSize ());
            Map<Integer, String> colMap = getColMap (metaReturnColumns);
            HBasePage hbasePage = searchPage (solrHBaseDatasource, tbName, query, page.getPageIndex (), page.getPageSize (), colMap, solrHBaseMetadata);
            List<Map<String, String>> records = hbasePage.getRecords ();
            records = orderBy (records, queryColumns, orderColumns); // 排序
            records = Util.tranRecords (records, returnColumns); // 字段过滤并字段名改别名
            response.setRecords (records);
            page.setTotalCount (hbasePage.getTotalCount ());
            response.setPage (page);
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

    private List<SolrQuery.SortClause> getSort(List<QueryColumn> queryColumns, List<OrderColumn> orderColumns) {
        Map<String, QueryColumn> map = new HashMap<> ();
        for (QueryColumn queryColumn : queryColumns) {
            map.put (queryColumn.getName (), queryColumn);
        }
        // 排序字段按照序号排序
        Collections.sort (orderColumns, new Comparator<OrderColumn> () {
            @Override
            public int compare(OrderColumn obj1, OrderColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        // 排序字段集合
        List<SolrQuery.SortClause> list = new ArrayList<> ();
        for (OrderColumn orderColumn : orderColumns) {
            String colName = orderColumn.getName ();
            // 排序字段在查询字段中，可以使用solr自带的排序方式
            if (map.get (colName) != null) {
                Order order = orderColumn.getOrder ();
                if (order != null && Order.DESC.equals (order)) {
                    list.add (new SolrQuery.SortClause (colName, SolrQuery.ORDER.desc));
                } else {
                    list.add (new SolrQuery.SortClause (colName, SolrQuery.ORDER.asc));
                }
            }
        }
        return list;
    }

    private List<Map<String, String>> orderBy(List<Map<String, String>> records, List<QueryColumn> queryColumns,
                                              final List<OrderColumn> orderColumns) {
        Map<String, QueryColumn> map = new HashMap<> ();
        for (QueryColumn queryColumn : queryColumns) {
            map.put (queryColumn.getName (), queryColumn);
        }
        //  排序字段不在查询字段中，只能自己实现排序
        final List<OrderColumn> newOrderColumns = new ArrayList<> ();
        for (OrderColumn orderColumn : orderColumns) {
            if (map.get (orderColumn.getName ()) == null) {
                newOrderColumns.add (orderColumn);
            }
        }
        // 排序字段按照序号排序
        Collections.sort (newOrderColumns, new Comparator<OrderColumn> () {
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
                for (OrderColumn orderColumn : newOrderColumns) {
                    String colName = orderColumn.getName ();
                    Order order = orderColumn.getOrder ();
                    DataType dataType = orderColumn.getType ();
                    String val1 = obj1.get (colName);
                    String val2 = obj2.get (colName);
                    if (StringUtils.isNotBlank (val1) && StringUtils.isNotBlank (val2)) {
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

    private Map<Integer, String> getColMap(List<DataColumn> returnColumns) {
        Map<Integer, String> colMap = new HashMap<> ();
        Collections.sort (returnColumns, new Comparator<DataColumn> () {
            @Override
            public int compare(DataColumn obj1, DataColumn obj2) {
                return new Integer (obj1.getSeq ()).compareTo (new Integer (obj2.getSeq ()));
            }
        });
        for (int i = 0; i < returnColumns.size (); i++) {
            colMap.put (i + 1, returnColumns.get (i).getName ());
        }
        return colMap;
    }

    private HBasePage searchPage(SolrHBaseDatasource datasource, String tableName, SolrQuery query, int pageIndex, int pageSize,
                                 Map<Integer, String> colMap, SolrHBaseMetadata metadata) throws Exception {
        List<Map<String, String>> records = new ArrayList<> ();
        SolrHBasePage solrHBasePage = searchPage (datasource, tableName, query, pageIndex, pageSize, metadata.gainSolrPrimaryKey ());

        // HBase单条查询
//        for (String id : solrHBasePage.getRecords()) {
//            records.add(HBaseUtil.get(datasource, tableName, id, colMap));
//        }

        // HBase批量查询
        HBaseMetadata hBaseMetadata = new HBaseMetadata (metadata.getPropertyMap ());
        List<String> ids = solrHBasePage.getRecords ();
        if (ids != null && ids.size () > 0) {
            if (ids.size () <= HBASE_GET_BATCH_SIZE) {
                records.addAll (HBaseUtil.gets (datasource, tableName, ids, colMap, hBaseMetadata));
            } else {
                List<String> rowkeys = new ArrayList<> ();
                for (int i = 0; i < ids.size (); i++) {
                    rowkeys.add (ids.get (i));
                    if (i + 1 == HBASE_GET_BATCH_SIZE) {
                        records.addAll (HBaseUtil.gets (datasource, tableName, rowkeys, colMap, hBaseMetadata));
                        rowkeys = new ArrayList<> ();
                    }
                }
                if (rowkeys.size () > 0) {
                    records.addAll (HBaseUtil.gets (datasource, tableName, rowkeys, colMap, hBaseMetadata));
                }
            }
        }

        return new HBasePage (records, solrHBasePage.getPageIndex (), solrHBasePage.getPageSize (), solrHBasePage.getTotalCount ());
    }

    private List<Map<String, String>> search(SolrHBaseDatasource datasource, String tableName, SolrQuery query,
                                             Map<Integer, String> colMap, SolrHBaseMetadata metadata) throws Exception {
        List<String> ids = search (datasource, tableName, query, metadata.gainSolrPrimaryKey ());
        List<Map<String, String>> records = new ArrayList<> ();

        // HBase单条查询
//        for (String id : ids) {
//            records.add(HBaseUtil.get(datasource, tableName, id, colMap));
//        }

        // HBase批量查询
        HBaseMetadata hBaseMetadata = new HBaseMetadata (metadata.getPropertyMap ());
        if (ids != null && ids.size () > 0) {
            if (ids.size () <= HBASE_GET_BATCH_SIZE) {
                records.addAll (HBaseUtil.gets (datasource, tableName, ids, colMap, hBaseMetadata));
            } else {
                List<String> rowkeys = new ArrayList<> ();
                for (int i = 0; i < ids.size (); i++) {
                    rowkeys.add (ids.get (i));
                    if (i + 1 == HBASE_GET_BATCH_SIZE) {
                        records.addAll (HBaseUtil.gets (datasource, tableName, rowkeys, colMap, hBaseMetadata));
                        rowkeys = new ArrayList<> ();
                    }
                }
                if (rowkeys.size () > 0) {
                    records.addAll (HBaseUtil.gets (datasource, tableName, rowkeys, colMap, hBaseMetadata));
                }
            }
        }

        return records;
    }

    private SolrHBasePage searchPage(SolrHBaseDatasource datasource, String collectionName, SolrQuery query,
                                     int pageIndex, int pageSize, String solrPrimaryKey) throws Exception {
        // 获取QueryResponse
        SolrDatasource solrDatasource = new SolrDatasource (datasource);
        QueryResponse rsp = SolrUtil.getQueryResponse (solrDatasource, collectionName, query);
        // 总行数
        SolrDocumentList results = rsp.getResults ();
        long totalCount = results.getNumFound ();
        // 唯一键集合
        List<String> records = new ArrayList<> ();
        for (SolrDocument result : results) {
            String id = (String) result.get (solrPrimaryKey);
            if (StringUtils.isNotBlank (id)) {
                records.add (id);
            }
        }
        return new SolrHBasePage (records, pageIndex, pageSize, totalCount);
    }

    public List<String> search(SolrHBaseDatasource datasource, String collectionName, SolrQuery query,
                               String solrPrimaryKey) throws Exception {
        // 获取QueryResponse
        SolrDatasource solrDatasource = new SolrDatasource (datasource);
        QueryResponse rsp = SolrUtil.getQueryResponse (solrDatasource, collectionName, query);
        // ID集合
        SolrDocumentList results = rsp.getResults ();
        List<String> records = new ArrayList<> ();
        for (SolrDocument result : results) {
            String id = (String) result.get (solrPrimaryKey);
            if (StringUtils.isNotBlank (id)) {
                records.add (id);
            }
        }
        return records;
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        return !HBaseUtil.isAborted (new HBaseDatasource (datasource))
                && SolrUtil.test (new SolrDatasource (datasource));
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        SolrDatasource solrDatasource = new SolrDatasource (datasource);
        String solrServers = solrDatasource.gainSolrServers ();
        return SolrUtil.getColumns (schemaName, solrServers);
    }

    @Override
    public IqDslResponse select(IqDslRequest request) {
        throw new RuntimeException ("Solr+HBase目前暂时不支持自定义DSL");
    }
}
