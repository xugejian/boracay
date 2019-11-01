package com.hex.bigdata.udsp;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.solr.client.solrj.impl.Krb5HttpClientConfigurer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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

//        HttpClientUtil.setConfigurer (new Krb5HttpClientConfigurer ());
        // ----------------Kerberos必须参数【END】--------------------

        String url = "http://172.18.21.61:8983/solr/TEST";
//        String respone = sendGet (url, null);
        String respone = sendGet2 (url, null);
        System.out.println (respone);

    }

    /**
     * 向指定URL发送GET方法的请求
     * （不支持Kerberos，但支持Kerberos+LDAP）
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
            URL realUrl = new URL (url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection ();
            // 设置通用的请求属性
            connection.setRequestProperty ("accept", "*/*");
            connection.setRequestProperty ("connection", "Keep-Alive");
            connection.setRequestProperty ("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

//            String encoding = new String(Base64.encode (new String(":").getBytes ()));
//            connection.setRequestProperty ("Authorization", AuthSchemes.SPNEGO + " " + encoding);

//            String encoding = new String(Base64.encode (new String("udsp:123456").getBytes ()));
//            connection.setRequestProperty ("Authorization", AuthSchemes.BASIC + " " + encoding);

            // 建立实际的连接
            connection.connect ();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields ();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet ()) {
//                logger.debug (key + "--->" + map.get (key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader (new InputStreamReader (connection.getInputStream ()));
            String line;
            while ((line = in.readLine ()) != null) {
                result += line;
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
     * 向指定URL发送GET方法的请求
     * （支持Kerberos，但不支持Kerberos+LDAP）
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet2(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            if (StringUtils.isNotEmpty (param)) {
                url += "?" + param;
            }
            HttpUriRequest request = new HttpGet (url);
            DefaultHttpClient client = new DefaultHttpClient ();

            // ----------------------支持Kerberos需要如下设置---------------------------
//            Krb5HttpClientConfigurer krb5HttpClientConfigurer = new Krb5HttpClientConfigurer ();
            Krb5HttpClientConfigurer.setSPNegoAuth (client);
//            HttpClientUtil.setConfigurer (krb5HttpClientConfigurer);
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

}
