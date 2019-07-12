package com.hex.bigdata.udsp.iq.provider.impl.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrDatasource;
import com.hex.bigdata.udsp.iq.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.provider.model.OrderColumn;
import com.hex.bigdata.udsp.iq.provider.model.QueryColumn;
import com.hex.bigdata.udsp.iq.provider.model.ReturnColumn;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by hj on 2017/9/11.
 */
public class SolrUtil {
    private static Logger logger = LogManager.getLogger(SolrUtil.class);

    public static List<MetadataCol> getColumns(String collectionName, String solrServers) {
        if (StringUtils.isEmpty (collectionName) || StringUtils.isEmpty (solrServers)) {
            return null;
        }
        String response = "";
        String[] addresses = solrServers.split (",");
        for (String solrServer : addresses) {
            String url = "http://" + solrServer + "/solr/" + collectionName + "/schema/fields";
            response = SolrUtil.sendGet (url, "");
            if (StringUtils.isNotBlank (response)) {
                break;
            }
        }
        if (StringUtils.isBlank (response)) {
            throw new RuntimeException (collectionName + "表名不存在");
        }
        JSONObject rs = JSONObject.parseObject (response);
        JSONArray fields = (JSONArray) rs.get ("fields");
        List<MetadataCol> metadataCols = new ArrayList<> ();
        MetadataCol mdCol = null;
        for (int i = 0; i < fields.size (); i++) {
            mdCol = new MetadataCol ();
            mdCol.setSeq ((short) i);
            mdCol.setName ((String) fields.getJSONObject (i).get ("name"));
            mdCol.setDescribe ((String) fields.getJSONObject (i).get ("name"));
            mdCol.setType (SolrUtil.getColType ((String) fields.getJSONObject (i).get ("type")));
            mdCol.setIndexed ((boolean) fields.getJSONObject (i).get ("indexed"));
            mdCol.setStored ((boolean) fields.getJSONObject (i).get ("stored"));
            mdCol.setPrimary (fields.getJSONObject (i).get ("uniqueKey") == null ? false : true);
            metadataCols.add (mdCol);
        }
        return metadataCols;
    }

    public static QueryResponse getQueryResponse(SolrDatasource datasource, String collectionName, SolrQuery query) {
        SolrServer solrServer = null;
        QueryResponse res = null;
        try {
            solrServer = SolrUtil.getSolrServer (datasource.gainSolrServers (), collectionName);
            res = solrServer.query (query);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return res;
    }

    public static boolean test(String solrServers) {
        HttpURLConnection connection = null;
        URL url = null;
        try {
            String[] servers = solrServers.split (",");
            for (String server : servers) {
                try {
                    url = new URL ("http://" + server + "/solr");
                    connection = (HttpURLConnection) url.openConnection ();
                    connection.setDoInput (true);
                    connection.setDoOutput (true);
                    connection.setUseCaches (false);
                    connection.setInstanceFollowRedirects (true);
                    connection.connect ();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace ();
                    logger.warn ("获取solr连接失败的地址为：" + (url == null ? "" : url.toString ()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            if (connection != null) {
                connection.disconnect ();
            }
        }
        return false;
    }

    public static SolrQuery getSolrQuery(List<QueryColumn> queryColumns, List<SolrQuery.SortClause> sorts,
                                         String fields, int rows) {
        return new SolrQuery () //
                .setQuery (getQuery (queryColumns)) //
                .setStart (0) //
                .setRows (rows) //
                .setSorts (sorts) //
                .setFields (fields);
    }

    public static SolrQuery getSolrQuery(List<QueryColumn> queryColumns, List<SolrQuery.SortClause> sorts,
                                         String fields, int pageIndex, int pageSize) {
        return new SolrQuery () //
                .setQuery (getQuery (queryColumns)) //
                .setStart ((pageIndex - 1) * pageSize) //
                .setRows (pageSize) //
                .setSorts (sorts) //
                .setFields (fields);
    }

    private static String getQuery(List<QueryColumn> queryColumns) {
        Collections.sort (queryColumns, new Comparator<QueryColumn> () {
            @Override
            public int compare(QueryColumn obj1, QueryColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        StringBuffer sb = new StringBuffer ("*:*");
        for (QueryColumn queryColumn : queryColumns) {
            String name = queryColumn.getName ();
            String value = queryColumn.getValue ();
            DataType type = queryColumn.getType ();
            Operator operator = queryColumn.getOperator ();
            if (org.apache.commons.lang3.StringUtils.isNotBlank (value)) {
                if (Operator.EQ.equals (operator)) {
                    sb.append (" AND " + name + ":" + getValue (type, value));
                } else if (Operator.GT.equals (operator)) {
                    sb.append (" AND " + name + ":[" + getValue (type, value) + " TO *] AND " + name + ":(* NOT " + getValue (type, value) + ")");
                } else if (Operator.LT.equals (operator)) {
                    sb.append (" AND " + name + ":[* TO " + getValue (type, value) + "] AND " + name + ":(* NOT " + getValue (type, value) + ")");
                } else if (Operator.GE.equals (operator)) {
                    sb.append (" AND " + name + ":[" + getValue (type, value) + " TO *]");
                } else if (Operator.LE.equals (operator)) {
                    sb.append (" AND " + name + ":[* TO " + getValue (type, value) + "]");
                } else if (Operator.NE.equals (operator)) {
                    sb.append (" AND " + name + ":(* NOT " + getValue (type, value) + ")"); // sb.append(" AND " + name + ":(-" + getValue(type, value) + ")");
                } else if (Operator.LK.equals (operator)) {
                    sb.append (" AND " + name + ":*" + getValue (value) + "*");
                } else if (Operator.RLIKE.equals (operator)) {
                    sb.append (" AND " + name + ":" + getValue (value) + "*");
                } else if (Operator.IN.equals (operator)) {
                    sb.append (" AND " + name + ":(");
                    String[] values = value.split (",");
                    for (int i = 0; i < values.length; i++) {
                        if (org.apache.commons.lang3.StringUtils.isBlank (values[i])) {
                            continue;
                        }
                        sb.append (getValue (type, values[i]));
                        if (i < values.length - 1) {
                            sb.append (" or ");
                        }
                        if (i == values.length - 1) {
                            sb.append (")");
                        }
                    }
                }
            }
        }
        logger.debug ("q=" + sb.toString ());
        return sb.toString ();
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

    public static SolrServer getSolrServer(String solrServices, String collectionName) throws MalformedURLException {
        if (StringUtils.isBlank (collectionName)) {
            throw new IllegalArgumentException ("collection name不能为空");
        }
        String[] tempServers = solrServices.split (",");
        String[] servers = new String[tempServers.length];
        for (int i = 0; i < tempServers.length; i++) {
            servers[i] = "http://" + tempServers[i] + "/solr/" + collectionName;
        }
        return new LBHttpSolrServer (servers);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            if (StringUtils.isNotEmpty(param)) {
                urlNameString += "?" + param;
            }
            logger.info("solrUrlApi: " + urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            String encoding = new String(Base64.encode (new String("udsp:123456").getBytes ()));
//            connection.setRequestProperty ("Authorization", "Basic " + encoding);
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                logger.debug(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送GET请求出现异常！" + e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
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
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type) {
            case "STRING":
                dataType = DataType.STRING;
                break;
            case "INT":
                dataType = DataType.INT;
                break;
            case "FLOAT":
                dataType = DataType.FLOAT;
                break;
            case "DOUBLE":
                dataType = DataType.DOUBLE;
                break;
            case "DATE":
                dataType = DataType.TIMESTAMP;
                break;
            case "BOOLEAN":
                dataType = DataType.BOOLEAN;
                break;
            default:
                dataType = DataType.STRING;
        }
        return dataType;
    }
}
