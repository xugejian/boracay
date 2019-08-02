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
public class SolrHttpKerberosTest {

    public static void main(String[] args) throws IOException {
        // ----------------Kerberos必须参数【START】--------------------
        System.setProperty ("java.security.krb5.conf", "A:\\kerberos\\krb5.conf");
        System.setProperty ("java.security.auth.login.config", "A:\\kerberos\\jaas.conf");
        System.setProperty ("javax.security.auth.useSubjectCredsOnly", "false");
        System.setProperty ("sun.security.krb5.debug", "false");

        HttpClientUtil.setConfigurer (new Krb5HttpClientConfigurer ());
        // ----------------Kerberos必须参数【END】--------------------


        String url = "http://172.18.21.61:8983/solr/TEST";
        String str = sendGet (url, null);
        System.out.println (str);
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
}
