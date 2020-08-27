package com.hex.bigdata.udsp.iq.provider.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.common.constant.Order;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.dsl.constant.ColumnType;
import com.hex.bigdata.udsp.dsl.constant.ComparisonOperator;
import com.hex.bigdata.udsp.dsl.constant.LogicalOperator;
import com.hex.bigdata.udsp.dsl.model.Component;
import com.hex.bigdata.udsp.dsl.model.Composite;
import com.hex.bigdata.udsp.dsl.model.Dimension;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrPage;
import com.hex.bigdata.udsp.iq.provider.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
//import org.apache.solr.client.solrj.impl.Krb5HttpClientConfigurer;
import org.apache.solr.client.solrj.impl.Krb5HttpClientBuilder;
//import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.*;

/**
 * Created by hj on 2017/9/11.
 */
public class SolrUtil {
    private static Logger logger = LogManager.getLogger (SolrUtil.class);

    public static List<MetadataCol> getColumns(String collectionName, String solrServers) {
        if (StringUtils.isEmpty (collectionName) || StringUtils.isEmpty (solrServers)) {
            return null;
        }
        String response = "";
        String[] solrServerStrings = getSolrServerStrings (solrServers);
        int count = 0;
        for (String solrServer : solrServerStrings) {
            count++;
            String url = "http://" + solrServer + "/solr/" + collectionName + "/schema/fields";
            try {
                response = sendGet (url, "");
            } catch (Exception e) {
                e.printStackTrace ();
                if (count == solrServerStrings.length) {
                    throw new RuntimeException (e);
                }
                continue;
            }
            break;
        }
        List<MetadataCol> metadataCols = new ArrayList<> ();
        MetadataCol mdCol = null;
//        JSONObject jsonObject = JSONObject.parseObject (response);
//        JSONArray fields = jsonObject.getJSONArray ("fields");
//        for (int i = 0; i < fields.size (); i++) {
//            mdCol = new MetadataCol ();
//            mdCol.setSeq ((short) i);
//            mdCol.setName (fields.getJSONObject (i).getString ("name"));
//            mdCol.setDescribe (fields.getJSONObject (i).getString ("name"));
//            mdCol.setType (getColType (fields.getJSONObject (i).getString ("type")));
//            mdCol.setIndexed (fields.getJSONObject (i).getBoolean ("indexed"));
//            mdCol.setStored (fields.getJSONObject (i).getBoolean ("stored"));
//            mdCol.setPrimary (fields.getJSONObject (i).get ("uniqueKey") == null ? false : true);
//            metadataCols.add (mdCol);
//        }
        List<Map<String, Object>> fields = JSONUtil.parseJSON2SubList (response, "fields");
        for (int i = 0; i < fields.size (); i++) {
            Map<String, Object> field = fields.get (i);
            mdCol = new MetadataCol ();
            mdCol.setSeq ((short) i);
            mdCol.setName ((String) field.get ("name"));
            mdCol.setDescribe ((String) field.get ("name"));
            mdCol.setType (SolrUtil.getColType ((String) field.get ("type")));
            mdCol.setIndexed ((Boolean) field.get ("indexed"));
            mdCol.setStored ((Boolean) field.get ("stored"));
            mdCol.setPrimary (field.get ("uniqueKey") == null ? false : true);
            metadataCols.add (mdCol);
        }
        return metadataCols;
    }

    public static String[] getSolrServerStrings(String solrServers) {
        return solrServers.split (",");
    }

    public static QueryResponse getQueryResponse(SolrDatasource datasource, String collectionName, SolrQuery query) {
        QueryResponse res = null;
        try {
            SolrClient solrServer = getSolrServer (datasource, collectionName);
            res = solrServer.query (query);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return res;
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    public static boolean test(SolrDatasource datasource) {
        String[] solrServerStrings = getSolrServerStrings (datasource.gainSolrServers ());
        for (String solrServer : solrServerStrings) {
            String url = "http://" + solrServer + "/solr/";
            try {
                sendGet (url, "");
            } catch (Exception e) {
                e.printStackTrace ();
                return false;
            }
        }
        return true;
    }

    public static SolrQuery getSolrQuery(String query, List<SolrQuery.SortClause> sorts,
                                         String fields, int start, int rows) {
        logger.info ("q=" + query);
        if (sorts != null) {
            return new SolrQuery () //
                    .setQuery (query) //
                    .setSorts (sorts) //
                    .setFields (fields) //
                    .setStart (start) //
                    .setRows (rows);
        } else {
            return new SolrQuery () //
                    .setQuery (query) //
                    .setFields (fields) //
                    .setStart (start) //
                    .setRows (rows);
        }
    }

    public static SolrQuery getSolrQuery(List<QueryColumn> queryColumns, List<OrderColumn> orderColumns,
                                         String primaryKey, int maxSize) {
        return getSolrQuery (queryColumnsToQuery (queryColumns), columnsToSort (queryColumns, orderColumns),
                primaryKey, 0, maxSize);
    }

    public static SolrQuery getSolrQuery(List<QueryColumn> queryColumns, List<OrderColumn> orderColumns,
                                         String primaryKey, int pageIndex, int pageSize) {
        return getSolrQuery (queryColumnsToQuery (queryColumns), columnsToSort (queryColumns, orderColumns),
                primaryKey, (pageIndex - 1) * pageSize, pageSize);
    }

    public static SolrQuery getSolrQuery(Component component, String primaryKey, int maxSize) {
        return getSolrQuery (componentToQuery (component), null,
                primaryKey, 0, maxSize);
    }

    public static SolrQuery getSolrQuery(Component component, String primaryKey, int pageIndex, int pageSize) {
        return getSolrQuery (componentToQuery (component), null,
                primaryKey, (pageIndex - 1) * pageSize, pageSize);
    }

    public static SolrQuery getSolrQuery(List<QueryColumn> queryColumns, List<OrderColumn> orderColumns,
                                         List<ReturnColumn> returnColumns, int maxSize) {
        return getSolrQuery (queryColumnsToQuery (queryColumns), orderColumnsToSort (orderColumns),
                returnColumnsToFields (returnColumns), 0, maxSize);
    }

    public static SolrQuery getSolrQuery(Component component, List<DataColumn> returnColumns, int maxSize) {
        return getSolrQuery (componentToQuery (component), null,
                dataColumnsToFields (returnColumns), 0, maxSize);
    }

    public static SolrQuery getSolrQuery(List<QueryColumn> queryColumns, List<OrderColumn> orderColumns,
                                         List<ReturnColumn> returnColumns, int pageIndex, int pageSize) {
        return getSolrQuery (queryColumnsToQuery (queryColumns), orderColumnsToSort (orderColumns),
                returnColumnsToFields (returnColumns), (pageIndex - 1) * pageSize, pageSize);
    }

    public static SolrPage searchPage(String collectionName, SolrQuery query, int pageIndex, int pageSize, SolrDatasource datasource) {
        QueryResponse rsp = getQueryResponse (datasource, collectionName, query);
        if (rsp == null) {
            return null;
        }
        SolrDocumentList results = rsp.getResults ();
        long totalCount = results.getNumFound ();
        List<Map<String, String>> records = getRecords (results);
        return new SolrPage (records, pageIndex, pageSize, totalCount);
    }

    public static List<Map<String, String>> search(String collectionName, SolrQuery query, SolrDatasource datasource) {
        QueryResponse rsp = getQueryResponse (datasource, collectionName, query);
        if (rsp == null) {
            return null;
        }
        SolrDocumentList results = rsp.getResults ();
        if (datasource.gainMaxSizeAlarm () && results.getNumFound () > datasource.gainMaxSize ()) {
            throw new RuntimeException ("返回结果集大小超过了最大返回数据条数的限制");
        }
        return getRecords (results);
    }

    private static List<Map<String, String>> getRecords(SolrDocumentList results) {
        List<Map<String, String>> records = new ArrayList<Map<String, String>> ();
        Map<String, String> map = null;
        String value = null;
        for (int i = 0; i < results.size (); i++) {
            map = new HashMap<> ();
            for (Map.Entry<String, Object> entry : results.get (i).entrySet ()) {
                Object obj = entry.getValue ();
                if (obj != null) {
                    value = String.valueOf (obj);
                }
                map.put (entry.getKey (), value);
            }
            records.add (map);
        }
        return records;
    }

    private static List<SolrQuery.SortClause> columnsToSort(List<QueryColumn> queryColumns, List<OrderColumn> orderColumns) {
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

    private static String returnColumnsToFields(List<ReturnColumn> returnColumns) {
        Collections.sort (returnColumns, new Comparator<ReturnColumn> () {
            @Override
            public int compare(ReturnColumn obj1, ReturnColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        String str = "";
        int count = 0;
        for (ReturnColumn column : returnColumns) {
            if (column != null && StringUtils.isNotBlank (column.getName ())) {
                if (count == 0) {
                    str += column.getName ();
                } else {
                    str += "," + column.getName ();
                }
                count++;
            }
        }
        return str;
    }

    private static String dataColumnsToFields(List<DataColumn> returnColumns) {
        Collections.sort (returnColumns, new Comparator<DataColumn> () {
            @Override
            public int compare(DataColumn obj1, DataColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        String str = "";
        int count = 0;
        for (DataColumn column : returnColumns) {
            if (column != null && StringUtils.isNotBlank (column.getName ())) {
                if (count == 0) {
                    str += column.getName ();
                } else {
                    str += "," + column.getName ();
                }
                count++;
            }
        }
        return str;
    }

    private static List<SolrQuery.SortClause> orderColumnsToSort(List<OrderColumn> orderColumns) {
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

    private static String componentToQuery(Component component) {
        if (component == null) {
            return "*:*";
        }
        if (component instanceof Dimension) {
            return dimensionToQuery ((Dimension) component);
        } else if (component instanceof Composite) {
            Composite composite = (Composite) component;
            LogicalOperator logiOper = composite.getLogiOper ();
            String str = "(";
            List<Component> components = composite.getComponents ();
            for (int i = 0, l = components.size (); i < l; i++) {
                str += (i == 0 ? "" : " " + logiOper.getValue () + " ");
                str += componentToQuery (components.get (i));
            }
            str += ")";
            return str;
        }
        return null;
    }

    private static String dimensionToQuery(Dimension dimension) {
        String name = dimension.getColumnName ();
        ComparisonOperator compOper = dimension.getCompOper ();
        List<String> values = getValues (dimension.getColumnType (), dimension.getValues ());
        switch (compOper.getValue ()) {
            case "=":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":" + values.get (0);
            case "!=":
            case "<>":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":(* NOT " + values.get (0) + ")";
            case ">":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":[" + values.get (0) + " TO *] AND " + name + ":(* NOT " + values.get (0) + ")";
            case "<":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":[* TO " + values.get (0) + "] AND " + name + ":(* NOT " + values.get (0) + ")";
            case ">=":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":[" + values.get (0) + " TO *]";
            case "<=":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":[* TO " + values.get (0) + "]";
            case "LIKE":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":" + dimension.getValues ().get (0);
            case "NOT LIKE":
                return (values == null || values.size () == 0) ? "*:*" :
                        "-" + name + ":" + dimension.getValues ().get (0);
            case "BETWEEN AND":
                return (values == null || values.size () < 2) ? "*:*" :
                        name + ":[" + values.get (0) + " TO " + values.get (1) + "]";
            case "IN":
                if (values == null || values.size () == 0) {
                    return "*:*";
                } else {
                    String str = name + ":(";
                    int count = 0;
                    for (int i = 0; i < values.size (); i++) {
                        str += (count == 0 ? "" : " or ");
                        str += values.get (i);
                        count++;
                    }
                    str += ")";
                    return str;
                }
            case "NOT IN":
                if (values == null || values.size () == 0) {
                    return "*:*";
                } else {
                    String str = "-" + name + ":(";
                    int count = 0;
                    for (int i = 0; i < values.size (); i++) {
                        str += (count == 0 ? "" : " or ");
                        str += values.get (i);
                        count++;
                    }
                    str += ")";
                    return str;
                }
            case "IS NULL":
                return (values == null || values.size () == 0) ? "*:*" :
                        "-" + name + ":*";
            case "IS NOT NULL":
                return (values == null || values.size () == 0) ? "*:*" :
                        name + ":*";
            default:
                return "*:*";
        }
    }

    private static List<String> getValues(ColumnType columnType, List<String> values) {
        List<String> newValues = new ArrayList<> ();
        for (String value : values) {
            newValues.add (getValue (columnType, value));
        }
        return newValues;
    }

    private static String getValue(ColumnType columnType, String value) {
        switch (columnType.getValue ()) {
            case "STRING":
                return "\"" + value + "\"";
            default:
                return value;
        }
    }

    private static String queryColumnsToQuery(List<QueryColumn> queryColumns) {
        Collections.sort (queryColumns, new Comparator<QueryColumn> () {
            @Override
            public int compare(QueryColumn obj1, QueryColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        String str = "*:*";
        for (QueryColumn queryColumn : queryColumns) {
            str += " AND " + getQuery (queryColumn);
        }
        logger.debug ("q=" + str);
        return str;
    }

    private static String getQuery(QueryColumn queryColumn) {
        String name = queryColumn.getName ();
        String value = queryColumn.getValue ();
        DataType type = queryColumn.getType ();
        Operator operator = queryColumn.getOperator ();
        if (StringUtils.isBlank (value)) {
            return "*:*";
        }
        switch (operator) {
            case EQ:
                return name + ":" + getValue (type, value);
            case NE:
                return name + ":(* NOT " + getValue (type, value) + ")";
            case GT:
                return name + ":[" + getValue (type, value) + " TO *] AND " + name + ":(* NOT " + getValue (type, value) + ")";
            case LT:
                return name + ":[* TO " + getValue (type, value) + "] AND " + name + ":(* NOT " + getValue (type, value) + ")";
            case GE:
                return name + ":[" + getValue (type, value) + " TO *]";
            case LE:
                return name + ":[* TO " + getValue (type, value) + "]";
            case LK:
                return name + ":*" + getValue (value) + "*";
            case RLIKE:
                return name + ":" + getValue (value) + "*";
            case IN:
                String str = name + ":(";
                String[] values = value.split (",");
                int count = 0;
                for (int i = 0; i < values.length; i++) {
                    str += (count == 0 ? "" : " or ");
                    str += getValue (type, values[i]);
                    count++;
                }
                str += ")";
                return str;
            default:
                return "*:*";
        }
    }

    private static String getValue(DataType type, String value) {
        if (DataType.STRING.equals (type) || DataType.VARCHAR.equals (type) || DataType.CHAR.equals (type)) {
            value = "\"" + value + "\"";
        } else {
            value = getValue (value);
        }
        return value;
    }

    private static String getValue(String value) {
        if (value.startsWith ("-")) {
            value = "\\" + value;
        }
        return value;
    }

    /**
     * 获取Solr服务
     *
     * @param datasource
     * @param collectionName
     * @return
     */
    public static SolrClient getSolrServer(SolrDatasource datasource, String collectionName) {
        return getLBHttpSolrServer (datasource.gainSolrServers (), collectionName);
//        return getCloudSolrServer (datasource.gainSolrZkHost (), collectionName);
    }

    /**
     * 获取LBHttpSolrServer
     *
     * @param solrServices
     * @param collectionName
     * @return
     */
    public static LBHttpSolrClient getLBHttpSolrServer(String solrServices, String collectionName) {
        if (StringUtils.isBlank (collectionName)) {
            throw new IllegalArgumentException ("collection name不能为空");
        }
        String[] tempServers = solrServices.split (",");
        String[] servers = new String[tempServers.length];
        for (int i = 0; i < tempServers.length; i++) {
            servers[i] = "http://" + tempServers[i] + "/solr/" + collectionName;
        }
        LBHttpSolrClient solrServer = null;
        SystemDefaultHttpClient httpClient = new SystemDefaultHttpClient();
        solrServer = new LBHttpSolrClient.Builder().withBaseSolrUrls(servers).withHttpClient(httpClient).build();
        return solrServer;
    }

    /**
     * 获取CloudSolrServer
     *
     * @param zkHost
     * @param collectionName
     * @return
     */
    public static CloudSolrClient getCloudSolrServer(String zkHost, String collectionName) {
        if (StringUtils.isBlank (collectionName)) {
            throw new IllegalArgumentException ("collection name不能为空");
        }
        CloudSolrClient solrServer = new CloudSolrClient.Builder().withZkHost(zkHost).build();
        solrServer.setDefaultCollection (collectionName);
        solrServer.connect ();
        return solrServer;
    }

    /**
     * 向指定URL发送GET方法的请求
     * （不支持Kerberos，但支持Kerberos+LDAP）
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            if (StringUtils.isNotEmpty (param)) {
//                url += "?" + param;
//            }
//            logger.info ("url: " + url);
//            URL realUrl = new URL (url);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection ();
//            // 设置通用的请求属性
//            connection.setRequestProperty ("accept", "*/*");
//            connection.setRequestProperty ("connection", "Keep-Alive");
//            connection.setRequestProperty ("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//
////            String encoding = new String(Base64.encode (new String(":").getBytes ()));
////            connection.setRequestProperty ("Authorization", AuthSchemes.SPNEGO + " " + encoding);
//
////            String encoding = new String(Base64.encode (new String("udsp:123456").getBytes ()));
////            connection.setRequestProperty ("Authorization", AuthSchemes.BASIC + " " + encoding);
//
//            // 建立实际的连接
//            connection.connect ();
////            // 获取所有响应头字段
////            Map<String, List<String>> map = connection.getHeaderFields ();
////            // 遍历所有的响应头字段
////            for (String key : map.keySet ()) {
////                logger.debug (key + "--->" + map.get (key));
////            }
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader (new InputStreamReader (connection.getInputStream ()));
//            String line;
//            while ((line = in.readLine ()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            e.printStackTrace ();
//            throw new RuntimeException (e);
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close ();
//                }
//            } catch (Exception e) {
//                e.printStackTrace ();
//            }
//        }
//        return result;
//    }

    /**
     * 向指定URL发送GET方法的请求
     * （支持Kerberos，但不支持Kerberos+LDAP）
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            if (StringUtils.isNotEmpty (param)) {
                url += "?" + param;
            }
            logger.info ("url: " + url);
            HttpUriRequest request = new HttpGet (url);
            DefaultHttpClient client = new DefaultHttpClient ();

            // ----------------------支持Kerberos需要如下设置---------------------------
//            Krb5HttpClientConfigurer.setSPNegoAuth (client);
//            Krb5HttpClientBuilder krb5HttpClientBuilder = new Krb5HttpClientBuilder();    //暂不确定
            // --------------------------------------------------------------------------

            HttpResponse response = client.execute (request);
            if (HttpStatus.SC_OK == response.getStatusLine ().getStatusCode ()) {
                HttpEntity entity = response.getEntity ();
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader (new InputStreamReader (entity.getContent ()));
                String line;
                while ((line = in.readLine ()) != null) {
                    result += line;
                }
            } else {
                throw new RuntimeException (response.getStatusLine ().getStatusCode () + "发送GET请求出现异常");
            }
        } catch (Exception e) {
            e.printStackTrace ();
            throw new RuntimeException (e);
        } finally {
            try {
                if (in != null) {
                    in.close ();
                }
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
        return result;
    }

    /**
     * SOLR字段类型转DB字段类型
     *
     * @param type
     * @return
     */
    public static DataType getColType(String type) {
        switch (type.toUpperCase ()) {
            case "STRING":
                return DataType.STRING;
            case "INT":
                return DataType.INT;
            case "FLOAT":
                return DataType.FLOAT;
            case "DOUBLE":
                return DataType.DOUBLE;
            case "DATE":
                return DataType.TIMESTAMP;
            case "BOOLEAN":
                return DataType.BOOLEAN;
            default:
                return DataType.STRING;
        }
    }
}
