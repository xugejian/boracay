package com.hex.bigdata.udsp.iq.provider.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.dsl.model.Component;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrPage;
import com.hex.bigdata.udsp.iq.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslRequest;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslResponse;
import com.hex.bigdata.udsp.iq.provider.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;
import java.util.Map;

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
            SolrQuery query = SolrUtil.getSolrQuery (queryColumns, orderColumns, returnColumns, solrDatasource.gainMaxSize ());
            logger.info ("query: " + query);
            List<Map<String, String>> records = SolrUtil.search (tbName, query, solrDatasource);
            response.setRecords (Util.tranRecordsObject (records, returnColumns)); // 字段过滤并字段名改别名
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
            if (page.getPageSize () > solrDatasource.gainMaxSize ()) {
                page.setPageSize (solrDatasource.gainMaxSize ());
                if (solrDatasource.gainMaxSizeAlarm ()) {
                    throw new RuntimeException ("返回结果集大小超过了最大返回数据条数的限制");
                }
            }
            SolrQuery query = SolrUtil.getSolrQuery (queryColumns, orderColumns, returnColumns, page.getPageIndex (), page.getPageSize ());
            logger.info ("query: " + query);
            SolrPage solrPage = SolrUtil.searchPage (tbName, query, page.getPageIndex (), page.getPageSize (), solrDatasource);
            response.setRecords (Util.tranRecordsObject (solrPage.getRecords (), returnColumns)); // 字段过滤并字段名改别名
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
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request));
        long bef = System.currentTimeMillis ();
        IqDslResponse response = new IqDslResponse ();

        try {
            Metadata metadata = request.getMetadata ();
            List<DataColumn> returnColumns = metadata.getReturnColumns ();
            Component component = request.getComponent ();
            String tbName = metadata.getTbName ();
            SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
            SolrQuery query = SolrUtil.getSolrQuery (component, returnColumns, solrDatasource.gainMaxSize ());
            logger.info ("query: " + query);
            List<Map<String, String>> records = SolrUtil.search (tbName, query, solrDatasource);
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

}
