package com.hex.bigdata.udsp;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
//import org.apache.solr.client.solrj.impl.Krb5HttpClientConfigurer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2019-8-2.
 */
public class SolrJavaKerberosTest {

    public static void main(String[] args) {

        // ----------------Kerberos必须参数【START】--------------------
        System.setProperty ("java.security.krb5.conf", "A:\\kerberos\\krb5.conf");
        System.setProperty ("java.security.auth.login.config", "A:\\kerberos\\jaas.conf");
        System.setProperty ("javax.security.auth.useSubjectCredsOnly", "false");
        System.setProperty ("sun.security.krb5.debug", "false");

//        HttpClientUtil.setConfigurer (new Krb5HttpClientConfigurer ());
        // ----------------Kerberos必须参数【END】--------------------

        /*
        CloudSolrServer
         */
        CloudSolrClient solrServer = new CloudSolrClient.Builder().withZkHost ("172.18.21.61:2181/solr").build();
        solrServer.setDefaultCollection ("TEST");
        solrServer.connect ();

        /*
        LBHttpSolrServer
         */
//        String[] servers = new String[2];
//        servers[0] = "http://172.18.21.61:8983/solr/TEST";
//        servers[1] = "http://172.18.21.62:8983/solr/TEST";
//        LBHttpSolrServer solrServer = null;
//        try {
//            solrServer = new LBHttpSolrServer (servers);
//        } catch (MalformedURLException e) {
//            e.printStackTrace ();
//        }

        /*
        HttpSolrServer
         */
//        HttpSolrServer solrServer = new HttpSolrServer ("http://172.18.21.61:8983/solr/TEST");


        List<SolrInputDocument> inputDocuments = new ArrayList<> ();
        SolrInputDocument inputDocument = new SolrInputDocument ();
        inputDocument.setField ("id", "111111111111");
        inputDocuments.add (inputDocument);
        try {
            solrServer.add (inputDocuments);
            solrServer.commit ();
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        SolrQuery query = new SolrQuery ();
        query.setQuery ("*:*");
        try {
            QueryResponse response = solrServer.query (query);
            SolrDocumentList docs = response.getResults ();
            for (SolrDocument doc : docs) {
                System.out.println (doc.get ("id"));
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace ();
        }
    }

}
