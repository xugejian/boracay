package com.hex.bigdata.udsp;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.Krb5HttpClientConfigurer;
import org.apache.solr.client.solrj.impl.PreemptiveBasicAuthConfigurer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2018-12-18.
 */
public class SolrKerberosTest {
    static final String zkHost = "172.18.21.61:2181/solr";
    static final String defaultCollection = "collection1";
    static final int socketTimeout = 20000;
    static final int zkConnectTimeout = 1000;

    public static void main(String[] args) throws IOException {
        /**
         * Kerberos
         */
        System.setProperty ("java.security.krb5.conf", "A:/keytabs/krb5.conf");
        System.setProperty ("java.security.auth.login.config", "A:/keytabs/jaas.conf");
        System.setProperty ("javax.security.auth.useSubjectCredsOnly", "false");
        System.setProperty ("sun.security.krb5.debug", "false");

        HttpClientUtil.setConfigurer (new Krb5HttpClientConfigurer ());

        String url = "http://172.18.21.61:8983/solr/" + defaultCollection + "/schema/fields";
        String str = sendGet (url, null);
        System.out.println (str);

        /**
         * LDAP
         */
//        HttpClientUtil.setConfigurer(new PreemptiveBasicAuthConfigurer ());
//        ModifiableSolrParams solrParams = new ModifiableSolrParams();
//        solrParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER, "test");
//        solrParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, "1qaz2wsx");
//        // Configure the JVM default parameters.
//        PreemptiveBasicAuthConfigurer.setDefaultSolrParams(solrParams);

//        CloudSolrServer cloudSolrServer = new CloudSolrServer (zkHost);
//        cloudSolrServer.setDefaultCollection (defaultCollection);
//        cloudSolrServer.setZkClientTimeout (zkConnectTimeout);
//        cloudSolrServer.setZkConnectTimeout (socketTimeout);
//        cloudSolrServer.connect ();
//
//        search (cloudSolrServer, "field1:1234567890");
//        addIndex (cloudSolrServer);

        // deleteIndex(cloudSolrServer, "1234567890");
    }

    public static String sendGet(String url, Map<String, String> param) throws IOException {
        String resultString = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault ();
        try {
            URIBuilder builder = new URIBuilder (url);
            if (param != null) {
                for (String key : param.keySet ()) {
                    builder.addParameter (key, param.get (key));
                }
            }
            URI uri = builder.build ();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet (uri);
            // 执行请求
            response = httpClient.execute (httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine ().getStatusCode () == 200) {
                resultString = EntityUtils.toString (response.getEntity (), "UTF-8");
            } else {
                System.out.println (response.getStatusLine ().getStatusCode ());
                System.out.println (EntityUtils.toString (response.getEntity (), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            try {
                if (response != null) {
                    response.close ();
                }
                httpClient.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return resultString;
    }

    /**
     * 查找
     *
     * @param solrClient
     * @param String
     */

    public static void search(CloudSolrServer solrClient, String String) {
        SolrQuery query = new SolrQuery ();
        query.setQuery (String);
        try {
            QueryResponse response = solrClient.query (query);
            SolrDocumentList docs = response.getResults ();

            System.out.println ("文档个数：" + docs.getNumFound ());
            System.out.println ("查询时间：" + response.getQTime ());

            for (SolrDocument doc : docs) {
                String field1 = (String) doc.getFieldValue ("field1");
                String field2 = (String) doc.getFieldValue ("field2");
                String field3 = (String) doc.getFieldValue ("field3");
                System.out.println ("field1: " + field1);
                System.out.println ("field2: " + field2);
                System.out.println ("field3: " + field3);
                System.out.println ();
            }
        } catch (Exception e) {
            System.out.println ("Unknowned Exception!!!!");
            e.printStackTrace ();
        }
    }

    /**
     * 添加索引
     *
     * @param solrClient
     */
    public static void addIndex(CloudSolrServer solrClient) {
        try {
            SolrInputDocument solrInputDocument = new SolrInputDocument ();
            solrInputDocument.setField ("field1", "1234567890");
            solrInputDocument.setField ("field2", "hello world");
            solrInputDocument.setField ("field3", "张三是个农民，勤劳致富，奔小康");
            solrClient.add (solrInputDocument);
            solrClient.commit ();
        } catch (Exception e) {
            System.out.println ("Unknowned Exception!!!!!");
            e.printStackTrace ();
        }
    }

    /**
     * 删除指定Collection中的Index
     *
     * @param solrServer
     * @param id
     */
    public static void deleteIndex(CloudSolrServer solrServer, String id) {
        try {
            solrServer.deleteById (id);
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

}
