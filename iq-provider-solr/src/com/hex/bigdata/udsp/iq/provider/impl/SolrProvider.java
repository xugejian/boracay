package com.hex.bigdata.udsp.iq.provider.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.Order;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrPage;
import com.hex.bigdata.udsp.iq.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslRequest;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslResponse;
import com.hex.bigdata.udsp.iq.provider.util.Util;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.util.*;

/**
 * Created by junjiem on 2017-3-3.
 */
public class SolrProvider implements Provider {
    private static Logger logger = LogManager.getLogger (SolrProvider.class);

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
            SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
            SolrQuery query = SolrUtil.getSolrQuery (queryColumns, getSort (orderColumns),
                    getFields (returnColumns), solrDatasource.gainMaxSize ());
            List<Map<String, Object>> records = search (tbName, query, solrDatasource);
            response.setRecords (Util.tranRecordsObject (records, returnColumns));
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
            SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
            SolrQuery query = SolrUtil.getSolrQuery (queryColumns, getSort (orderColumns),
                    getFields (returnColumns), page.getPageIndex (), page.getPageSize ());
            SolrPage solrPage = searchPage (tbName, query, page.getPageIndex (), page.getPageSize (), solrDatasource);
            response.setRecords (Util.tranRecordsObject (solrPage.getRecords (), returnColumns));
            page.setTotalCount (solrPage.getTotalCount ());
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

    private String getFields(List<ReturnColumn> returnColumns) {
        Collections.sort (returnColumns, new Comparator<ReturnColumn> () {
            @Override
            public int compare(ReturnColumn obj1, ReturnColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        StringBuffer sb = new StringBuffer ();
        int count = 0;
        for (ReturnColumn returnColumn : returnColumns) {
            if (returnColumn != null && StringUtils.isNotBlank (returnColumn.getName ())) {
                if (count == 0) {
                    sb.append (returnColumn.getName ());
                } else {
                    sb.append ("," + returnColumn.getName ());
                }
                count++;
            }
        }
        return sb.toString ();
    }

    private List<SolrQuery.SortClause> getSort(List<OrderColumn> orderColumns) {
        // 排序字段按照序号排序
        Collections.sort (orderColumns, new Comparator<OrderColumn> () {
            @Override
            public int compare(OrderColumn obj1, OrderColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        // 排序字段集合
        List<SolrQuery.SortClause> list = new ArrayList<SolrQuery.SortClause> ();
        for (OrderColumn orderColumn : orderColumns) {
            String colName = orderColumn.getName ();
            Order order = orderColumn.getOrder ();
            if (order != null && Order.DESC.equals (order)) {
                list.add (new SolrQuery.SortClause (colName, SolrQuery.ORDER.desc));
            } else {
                list.add (new SolrQuery.SortClause (colName, SolrQuery.ORDER.asc));
            }
        }
        return list;
    }

    private SolrPage searchPage(String collectionName, SolrQuery query, int pageIndex, int pageSize, SolrDatasource datasource) {
        QueryResponse rsp = SolrUtil.getQueryResponse (datasource, collectionName, query);
        if (rsp == null) {
            return null;
        }
        SolrDocumentList results = rsp.getResults ();
        long totalCount = results.getNumFound ();
        List<Map<String, Object>> records = new ArrayList<Map<String, Object>> ();
        Map<String, Object> map = null;
        for (int i = 0; i < results.size (); i++) {
            map = new HashMap<> ();
            for (Map.Entry<String, Object> entry : results.get (i).entrySet ()) {
                map.put (entry.getKey (), entry.getValue ());
            }
            records.add (map);
            //records.add(results.get(i).getFieldValueMap());
        }
        return new SolrPage (records, pageIndex, pageSize, totalCount);
    }

    private List<Map<String, Object>> search(String collectionName, SolrQuery query, SolrDatasource datasource) {
        QueryResponse rsp = SolrUtil.getQueryResponse (datasource, collectionName, query);
        if (rsp == null) {
            return null;
        }
        SolrDocumentList results = rsp.getResults ();
        List<Map<String, Object>> records = new ArrayList<Map<String, Object>> ();
        for (int i = 0; i < results.size (); i++) {
            records.add (results.get (i).getFieldValueMap ());
        }
        return records;
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        return SolrUtil.test (new SolrDatasource (datasource));
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        SolrDatasource solrDatasource = new SolrDatasource (datasource);
        String solrServers = solrDatasource.gainSolrServers ();
        return SolrUtil.getColumns (schemaName, solrServers);
    }

    @Override
    public IqDslResponse select(IqDslRequest request) {
        throw new RuntimeException ("Solr目前暂时不支持自定义DSL");
    }

}
