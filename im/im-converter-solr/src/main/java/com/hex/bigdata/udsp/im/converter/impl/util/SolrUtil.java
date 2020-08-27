package com.hex.bigdata.udsp.im.converter.impl.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.im.converter.impl.model.SolrDatasource;
import com.hex.bigdata.udsp.im.converter.impl.model.SolrMetadata;
import com.hex.bigdata.udsp.im.converter.model.MetadataCol;
import com.hex.bigdata.udsp.im.converter.model.Page;
import com.hex.bigdata.udsp.im.converter.model.WhereProperty;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
//import org.apache.solr.client.solrj.impl.Krb5HttpClientConfigurer;
import org.apache.solr.client.solrj.impl.Krb5HttpClientBuilder;
//import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.zookeeper.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hj on 2017/9/11.
 */
public class SolrUtil {
    private static Logger logger = LogManager.getLogger (SolrUtil.class);
    private static final String SOLR_CONFIG_ZOOKEEPER_DIR = "/configs";
    private static String solrConfigPath;
    private static String solrConfigSchemaPath;

    static {
        try {
            solrConfigPath = ResourceUtils.getFile ("classpath:goframe/im/solr/template/conf")
                    .getAbsolutePath ();
            solrConfigSchemaPath = solrConfigPath + "/schema.xml";
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
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

    /**
     * 检查Collection
     *
     * @param solrServers
     * @param collectionName
     * @return
     * @throws Exception
     */
    public static boolean checkCollection(String solrServers, String collectionName) throws Exception {
        // 检查Collection
        String response = "";
        String[] solrServerStrings = getSolrServerStrings (solrServers);
        int count = 0;
        for (String solrServer : solrServerStrings) {
            count++;
            String url = getSolrAdminCollectionsUrl (solrServer);
            String param = "action=LIST";
            try {
                response = sendGet (url, param);
            } catch (Exception e) {
                e.printStackTrace ();
                if (count == solrServerStrings.length) {
                    throw new RuntimeException (e);
                }
                continue;
            }
            break;
        }
        boolean exists = false;
        if (response.startsWith("{") && response.endsWith("}")){
            //solr7 返回为json
            JSONObject responseJson = JSONObject.parseObject(response);
            JSONArray collections = responseJson.getJSONArray("collections");
            if (collections != null){
                for (Object e : collections) {
                    if (e.equals (collectionName)) {
                        exists = true;
                        break;
                    }
                }
            }
        } else {
            // solr4 返回为xml，按原方法解析
            Document document = DocumentHelper.parseText (response);
            Element root = document.getRootElement ();
            Element arr = root.element ("arr");
            List<Element> collections = arr.elements ("str");
            for (Element e : collections) {
                if (e.getData ().equals (collectionName)) {
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    /**
     * 删除Collection
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public static boolean dropCollection(SolrMetadata metadata, boolean ifExists) throws Exception {
        String collectionName = metadata.getTbName ();
        SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
        String solrServers = solrDatasource.gainSolrServers ();
        String solrZkHost = solrDatasource.gainSolrZkHost ();
        // 判断是否已经存在该表
        if (checkCollection (solrServers, collectionName)) {
            logger.warn ("Solr表" + collectionName + "存在，进行删除！");
            // 删除Collection
            if (!deleteCollection (metadata)) {
                throw new RuntimeException ("删除SOLR表失败，请检查SOLR配置！");
            }
            // 删除Config
            deleteZnode (solrZkHost, collectionName);
        } else {
            logger.debug ("Solr表" + collectionName + "不存在，无需删除！");
            if (!ifExists) {
                throw new Exception ("Solr表" + collectionName + "不存在！");
            }

        }
        return true;
    }

    /**
     * 删除Collection
     *
     * @param metadata
     * @return
     */
    public static boolean deleteCollection(SolrMetadata metadata) {
        String collectionName = metadata.getTbName ();
        SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
        String solrServers = solrDatasource.gainSolrServers ();
        String[] solrServerStrings = getSolrServerStrings (solrServers);
        int count = 0;
        for (String solrServer : solrServerStrings) {
            count++;
            String url = getSolrAdminCollectionsUrl (solrServer);
            String param = "action=DELETE&name=" + collectionName;
            try {
                String response = sendGet (url, param);

                if (response.startsWith("{") && response.endsWith("}")){
                    //solr7 返回为json
                    JSONObject responseJson = JSONObject.parseObject(response);
                    JSONObject success = responseJson.getJSONObject("success");
                    if (success != null){
                        return true;
                    }
                } else {
                    // solr4 返回为xml，按原方法解析
                    Document document = DocumentHelper.parseText (response);
                    Element root = document.getRootElement ();
                    List<Element> lstElts = root.elements ("lst");
                    Attribute att = null;
                    for (Element lstElt : lstElts) {
                        att = lstElt.attribute ("name");
                        if ("responseHeader".equals (att.getValue ())) {
                            List<Element> intElts = lstElt.elements ("int");
                            for (Element intElt : intElts) {
                                att = intElt.attribute ("name");
                                if ("status".equals (att.getValue ())) {
                                    if ("0".equals (intElt.getText ())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace ();
                if (count == solrServerStrings.length) {
                    throw new RuntimeException (e);
                }
                continue;
            }
            break;
        }
        return false;
    }

    /**
     * 上传配置并创建Collection
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public static boolean createCollection(SolrMetadata metadata, boolean ifNotExists) throws Exception {
        // 检查参数
        checkSolrProperty (metadata);
        String collectionName = metadata.getTbName ();
        List<MetadataCol> metadataCols = metadata.getMetadataCols ();
        SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
        String solrServers = solrDatasource.gainSolrServers ();
        String solrZkHost = solrDatasource.gainSolrZkHost ();
        // 判断是否已经存在该表
        if (checkCollection (solrServers, collectionName)) {
            logger.debug ("Solr表" + collectionName + "存在，无需创建！");
            if (!ifNotExists) {
                throw new Exception ("Solr表" + collectionName + "已经存在！");
            }
        } else {
            logger.debug ("Solr表" + collectionName + "不存在，进行创建！");
            // 添加Config
            uploadSolrConfig (solrZkHost, collectionName, metadataCols);
            // 创建Collection
            try {
                if (!createCollection (metadata)) {
                    throw new RuntimeException ("创建SOLR表失败，请检查SOLR配置！");
                }
            } catch (Exception e) {
                deleteZnode (solrZkHost, collectionName); // 删除Config
                throw new RuntimeException (e);
            }
        }
        return true;
    }

    /**
     * 创建Collection
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public static boolean createCollection(SolrMetadata metadata) throws Exception {
        String collectionName = metadata.getTbName ();
        int replicas = metadata.gainReplicas ();
        int shards = metadata.gainShards ();
        int maxShardsPerNode = metadata.gainMaxShardsPerNode ();
        SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
        String solrServers = solrDatasource.gainSolrServers ();
        String response = "";
        String[] solrServerStrings = getSolrServerStrings (solrServers);
        int count = 0;
        for (String solrServer : solrServerStrings) {
            count++;
            String url = getSolrAdminCollectionsUrl (solrServer);
            String param = "action=CREATE" + "&name=" + collectionName + "&replicationFactor=" + replicas +
                    "&numShards=" + shards + "&maxShardsPerNode=" + maxShardsPerNode;
//            String param = "action=CREATE" + "&name=" + collectionName + "&replicationFactor=" + replicas +
//                    "&numShards=" + shards + "&maxShardsPerNode=" + maxShardsPerNode +
//                    "&collection.configName=" + collectionName;
            try {
                response = sendGet (url, param);

                if (response.startsWith("{") && response.endsWith("}")){
                    //solr7 返回为json
                    JSONObject responseJson = JSONObject.parseObject(response);
                    JSONObject success = responseJson.getJSONObject("success");
                    if (success != null){
                        return true;
                    }
                } else {
                    // solr4 返回为xml，按原方法解析
                    Document document = DocumentHelper.parseText (response);
                    Element root = document.getRootElement ();
                    List<Element> lstElts = root.elements ("lst");
                    Attribute att = null;
                    for (Element lstElt : lstElts) {
                        att = lstElt.attribute ("name");
                        if ("responseHeader".equals (att.getValue ())) {
                            List<Element> intElts = lstElt.elements ("int");
                            for (Element intElt : intElts) {
                                att = intElt.attribute ("name");
                                if ("status".equals (att.getValue ())) {
                                    if ("0".equals (intElt.getText ())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace ();
                if (count == solrServerStrings.length) {
                    throw new RuntimeException (e);
                }
                continue;
            }
            break;
        }
        return false;
    }


    /**
     * 更新配置并重载Collection
     *
     * @param metadata
     * @param addMetadataCols
     * @return
     */
    public static void updateCollection(SolrMetadata metadata, List<MetadataCol> addMetadataCols) throws Exception {
        String collectionName = metadata.getTbName ();
        List<MetadataCol> metadataCols = metadata.getMetadataCols ();
        metadataCols.addAll (addMetadataCols); // 添加新的字段
        SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
        String solrServers = solrDatasource.gainSolrServers ();
        String solrZkHost = solrDatasource.gainSolrZkHost ();
        if (checkCollection (solrServers, collectionName)) {
            logger.debug ("Solr表" + collectionName + "存在，进行更新！");
            // 更新Config
            updateSolrConfig (solrZkHost, collectionName, metadataCols);
            // 重载Collection
            if (!reloadCollection (metadata)) {
                /*
                还原回原来的配置并且重载
                 */
                updateSolrConfig (solrZkHost, collectionName, metadata.getMetadataCols ()); // 更新配置
                reloadCollection (metadata); // 重载Collection
                throw new RuntimeException ("重载SOLR表失败，请检查SOLR配置！");
            }
        } else {
            throw new Exception ("Solr表" + collectionName + "不存在，无法更新！");
        }
    }

    /**
     * 重载Collection
     *
     * @param metadata
     * @return
     */
    public static boolean reloadCollection(SolrMetadata metadata) {
        String collectionName = metadata.getTbName ();
        SolrDatasource solrDatasource = new SolrDatasource (metadata.getDatasource ());
        String solrServers = solrDatasource.gainSolrServers ();
        String response = "";
        String[] solrServerStrings = getSolrServerStrings (solrServers);
        int count = 0;
        for (String solrServer : solrServerStrings) {
            count++;
            String url = getSolrAdminCollectionsUrl (solrServer);
            String param = "action=RELOAD&name=" + collectionName;
            try {
                response = sendGet (url, param);

                if (response.startsWith("{") && response.endsWith("}")){
                    //solr7 返回为json
                    JSONObject responseJson = JSONObject.parseObject(response);
                    JSONObject success = responseJson.getJSONObject("success");
                    if (success != null){
                        return true;
                    }
                } else {
                    // solr4 返回为xml，按原方法解析
                    Document document = DocumentHelper.parseText (response);
                    Element root = document.getRootElement ();
                    List<Element> lstElts = root.elements ("lst");
                    Attribute att = null;
                    for (Element lstElt : lstElts) {
                        att = lstElt.attribute ("name");
                        if ("responseHeader".equals (att.getValue ())) {
                            List<Element> intElts = lstElt.elements ("int");
                            for (Element intElt : intElts) {
                                att = intElt.attribute ("name");
                                if ("status".equals (att.getValue ())) {
                                    if ("0".equals (intElt.getText ())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace ();
                if (count == solrServerStrings.length) {
                    throw new RuntimeException (e);
                }
                continue;
            }
            break;
        }
        return false;
    }

    public static String getSolrAdminCollectionsUrl(String solrServer) {
        return "http://" + solrServer + "/solr/admin/collections";
    }

    public static String[] getSolrServerStrings(String solrServers) {
        return solrServers.split (",");
    }

    /**
     * 上传配置文件
     *
     * @param solrZkHost
     * @param collectionName
     * @param metadataCols
     * @throws Exception
     */
    public static void uploadSolrConfig(String solrZkHost, String collectionName, List<MetadataCol> metadataCols) throws Exception {
        upload (getZkClient (solrZkHost), solrConfigPath, SOLR_CONFIG_ZOOKEEPER_DIR, collectionName, metadataCols);
    }

    /**
     * 更新配置文件
     *
     * @param solrZkHost
     * @param collectionName
     * @param metadataCols
     */
    public static void updateSolrConfig(String solrZkHost, String collectionName, List<MetadataCol> metadataCols) throws Exception {
        deleteZnode (solrZkHost, collectionName);
        uploadSolrConfig (solrZkHost, collectionName, metadataCols);
    }

    /**
     * 获取zookeeper链接
     *
     * @param solrZkHost
     * @return
     * @throws IOException
     */
    public static ZooKeeper getZkClient(String solrZkHost) throws IOException {
        ZooKeeper zkClient = null;
        try {
            CountDownLatch connectedLatch = new CountDownLatch (1);
            Watcher watcher = new ConnectedWatcher (connectedLatch);
            zkClient = new ZooKeeper (solrZkHost, 20000, watcher);
            waitUntilConnected (zkClient, connectedLatch);
        } catch (IOException e) {
            e.printStackTrace ();
            throw new IOException (e);
        }
        return zkClient;
    }

    public static void waitUntilConnected(ZooKeeper zooKeeper, CountDownLatch connectedLatch) {
        if (ZooKeeper.States.CONNECTING == zooKeeper.getState ()) {
            try {
                connectedLatch.await ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
                throw new IllegalStateException (e);
            }
        }
    }

    static class ConnectedWatcher implements Watcher {
        private CountDownLatch connectedLatch;

        ConnectedWatcher(CountDownLatch connectedLatch) {
            this.connectedLatch = connectedLatch;
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getState () == Watcher.Event.KeeperState.SyncConnected) {
                connectedLatch.countDown ();
            }
        }
    }

    /**
     * 上传配置目录及文件（递归）
     *
     * @param zkClient
     * @param filePath       配置文件路径
     * @param solrConfigPath 上传zookeeper的路径
     * @param configName     配置名称 collection1
     * @param metadataCols
     * @throws Exception
     */
    public static void upload(ZooKeeper zkClient, String filePath, String solrConfigPath, String configName, List<MetadataCol> metadataCols) throws Exception {
        File file = new File (filePath);
        if (file.isFile ()) {
            byte[] bytes = getSchemaXMLBytes (configName, metadataCols, file);
            zkClient.create (solrConfigPath + "/" + file.getName (), bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            solrConfigPath += "/" + (configName != null ? configName : file.getName ());
            // 配置文件目录已存在
            if (zkClient.exists (solrConfigPath, false) != null) {
//                // 删除原配置目录及文件
                delPath (zkClient, solrConfigPath);
                zkClient.delete (solrConfigPath, -1);
//                throw new Exception("该名称的配置文件已存在！");
            }
            byte[] bytes = file.getName ().getBytes ();
            zkClient.create (solrConfigPath, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            File[] files = file.listFiles ();
            for (File e : files) {
                if (e.isDirectory ()) {
                    upload (zkClient, e.getPath (), solrConfigPath, null, metadataCols);
                } else {
                    bytes = getSchemaXMLBytes (configName, metadataCols, e);
                    zkClient.create (solrConfigPath + "/" + e.getName (), bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    continue;
                }
            }
        }
    }

    private static byte[] getSchemaXMLBytes(String configName, List<MetadataCol> metadataCols, File file) {
        return "schema.xml".equals (file.getName ()) ? setSchemaField (configName, metadataCols) : readFileByBytes (file.getPath ());
    }

    /**
     * 修改schema的字段
     *
     * @param metadataCols
     * @return
     */
    public static byte[] setSchemaField(String collectionName, List<MetadataCol> metadataCols) {
        File file = new File (solrConfigSchemaPath);
        Document document = File2Doc (file);
        Element root = document.getRootElement ();
        root.addAttribute ("name", collectionName);
        Element fields = root.element ("fields");
        for (MetadataCol metadataCol : metadataCols) {
            if (metadataCol.isPrimary ()) { // 主键
                Element field = DocumentHelper.createElement ("field");
                field.addAttribute ("name", metadataCol.getName ());
                field.addAttribute ("type", getSolrType (metadataCol.getType ()));
                field.addAttribute ("indexed", "true"); // 主键必须indexed=true
                field.addAttribute ("stored", "true"); // 主键必须stored=true
                field.addAttribute ("required", "true"); // 主键必须required=true
                field.addAttribute ("multiValued", "false"); // 主键必须multiValued=false
                fields.add (field);
                Element uniqueKey = DocumentHelper.createElement ("uniqueKey");
                uniqueKey.setText (metadataCol.getName ());
                root.add (uniqueKey);
            } else { // 非主键
                Element field = DocumentHelper.createElement ("field");
                field.addAttribute ("name", metadataCol.getName ());
                field.addAttribute ("type", getSolrType (metadataCol.getType ()));
                field.addAttribute ("indexed", metadataCol.isIndexed () ? "true" : "false");
                field.addAttribute ("stored", metadataCol.isStored () ? "true" : "false");
                fields.add (field);
            }
        }
        return root.asXML ().getBytes ();
    }

    /**
     * DB字段类型转SOLR字段类型
     *
     * @param type
     * @return
     */
    public static String getSolrType(DataType type) {
        if (DataType.STRING == type || DataType.VARCHAR == type || DataType.CHAR == type || DataType.TIMESTAMP == type) {
            return "string";
        } else if (DataType.DOUBLE == type || DataType.DECIMAL == type) {
            return "double";
        } else if (DataType.INT == type || DataType.SMALLINT == type || DataType.TINYINT == type) {
            return "int";
        } else if (DataType.FLOAT == type) {
            return "float";
        } else if (DataType.BIGINT == type) {
            return "long";
        } else if (DataType.BOOLEAN == type) {
            return "boolean";
        } else {
            return "string";
        }
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

    /**
     * 将给定文件的内容或者给定 URI 的内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象
     *
     * @param file 文件
     * @return DOM的Document对象
     * @throws Exception
     */
    public static Document File2Doc(File file) {
        Document doc = null;
        try {
            SAXReader reader = new SAXReader ();
            doc = reader.read (file);
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] readFileByBytes(String fileName) {
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream ();
        try {
            in = new FileInputStream (fileName);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read (buf)) != -1) {
                out.write (buf, 0, length);
            }
        } catch (Exception e1) {
            e1.printStackTrace ();
        } finally {
            if (in != null) {
                try {
                    in.close ();
                } catch (IOException e1) {
                }
            }
        }
        return out.toByteArray ();
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
//            Krb5HttpClientBuilder krb5HttpClientBuilder = new Krb5HttpClientBuilder();     //暂不确定
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
     * 删除solr配置
     *
     * @param solrZkHost
     * @param collectionName
     * @throws Exception
     */
    public static void deleteZnode(String solrZkHost, String collectionName) throws Exception {
        ZooKeeper zkClient = getZkClient (solrZkHost);
        String path = SOLR_CONFIG_ZOOKEEPER_DIR + "/" + collectionName;
        delPath (zkClient, path);
        zkClient.delete (path, -1);
    }

    /**
     * 删除配置目录及文件（递归）
     *
     * @param zk
     * @param path
     * @throws Exception
     */
    public static void delPath(ZooKeeper zk, String path) throws Exception {
        List<String> files = zk.getChildren (path, false);
        for (String file : files) {
            delPath (zk, path + "/" + file);
        }
        for (String file : files) {
            zk.delete (path + "/" + file, -1);
        }
    }

    /**
     * 检查Solr配置
     *
     * @param metadata
     * @throws Exception
     */
    public static void checkSolrProperty(SolrMetadata metadata) throws Exception {
        //检查主键是否合法
        List<MetadataCol> cols = metadata.getMetadataCols ();
        int count = 0;
        for (MetadataCol col : cols) {
            if (col.isPrimary ()) {
                count++;
            }
        }
        if (count != 1) {
            throw new Exception ("必须要有且仅有一个主键字段！");
        }
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
//    public static SolrServer getSolrServer(SolrDatasource datasource, String collectionName) {
//        return getLBHttpSolrServer (datasource.gainSolrServers (), collectionName);
////        return getCloudSolrServer (datasource.gainSolrZkHost (), collectionName);
//    }

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
        //            solrServer = new LBHttpSolrClient (servers);
        solrServer = new LBHttpSolrClient.Builder().withBaseSolrUrls(servers).build();
        return solrServer;
    }

    /**
     * 获取CloudSolrClient
     * 原获取CloudSolrServer
     *
     * @param zkHost
     * @param collectionName
     * @return
     */
    public static CloudSolrClient getCloudSolrServer(String zkHost, String collectionName) {
        if (StringUtils.isBlank (collectionName)) {
            throw new IllegalArgumentException ("collection name不能为空");
        }
//        CloudSolrClient solrServer = new CloudSolrClient (zkHost);
        CloudSolrClient solrServer = new CloudSolrClient.Builder().withZkHost(zkHost).build();
        solrServer.setDefaultCollection (collectionName);
        solrServer.connect ();
        return solrServer;
    }

    /**
     * 清空表数据
     *
     * @param datasource
     * @param collectionName
     * @throws IOException
     * @throws SolrServerException
     */
    public static void deleteAll(SolrDatasource datasource, String collectionName) throws IOException, SolrServerException {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        solrServer.deleteByQuery ("*:*");
        solrServer.commit ();
    }

    /**
     * 添加单条数据
     *
     * @param datasource
     * @param collectionName
     * @param map
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, Map<String, String> map) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        SolrInputDocument doc = new SolrInputDocument ();
        for (Map.Entry<String, String> entry : map.entrySet ()) {
            doc.addField (entry.getKey (), entry.getValue ());
        }
        addDocument (solrServer, doc);
    }

    /**
     * 添加单条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValue
     * @param map
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, String idName, String idValue, Map<String, String> map) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        SolrInputDocument doc = new SolrInputDocument ();
        doc.addField (idName, idValue);
        for (Map.Entry<String, String> entry : map.entrySet ()) {
            doc.addField (entry.getKey (), entry.getValue ());
        }
        addDocument (solrServer, doc);
    }

    /**
     * 添加多条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValues
     * @param map
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, String idName, List<String> idValues, Map<String, String> map) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        List<SolrInputDocument> docs = new ArrayList<> ();
        SolrInputDocument doc = null;
        for (String idValue : idValues) {
            doc = new SolrInputDocument ();
            doc.addField (idName, idValue);
            for (Map.Entry<String, String> entry : map.entrySet ()) {
                doc.addField (entry.getKey (), entry.getValue ());
            }
            docs.add (doc);
        }
        addDocument (solrServer, docs);
    }

    /**
     * 添加多条数据
     *
     * @param datasource
     * @param collectionName
     * @param maps
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, List<Map<String, String>> maps) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        List<SolrInputDocument> docs = new ArrayList<> ();
        SolrInputDocument doc = null;
        for (Map<String, String> map : maps) {
            doc = new SolrInputDocument ();
            for (Map.Entry<String, String> entry : map.entrySet ()) {
                doc.addField (entry.getKey (), entry.getValue ());
            }
            docs.add (doc);
        }
        addDocument (solrServer, docs);
    }

    /**
     * 添加单个对象
     *
     * @param datasource
     * @param collectionName
     * @param bean
     * @param <T>
     */
    public static <T> void addDocumentBean(SolrDatasource datasource, String collectionName, T bean) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        try {
            solrServer.addBean (bean);
            solrServer.commit ();
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (solrServer != null) {
//                solrServer.shutdown ();
                try {
                    solrServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加多个对象
     *
     * @param datasource
     * @param collectionName
     * @param beans
     * @param <T>
     */
    public static <T> void addDocumentBean(SolrDatasource datasource, String collectionName, List<T> beans) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        try {
            solrServer.addBeans (beans);
            solrServer.commit ();
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (solrServer != null) {
//                solrServer.shutdown ();
                try {
                    solrServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 更新单条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValue
     * @param map
     */
    public static void updateDocument(SolrDatasource datasource, String collectionName, String idName, String idValue, Map<String, String> map) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        SolrInputDocument doc = new SolrInputDocument ();
        doc.addField (idName, idValue);
        Map<String, String> obj = null;
        for (Map.Entry<String, String> entry : map.entrySet ()) {
            obj = new HashMap<> ();
            obj.put ("set", entry.getValue ());
            doc.addField (entry.getKey (), obj);
        }
        addDocument (solrServer, doc);
    }

    /**
     * 添加单个文档
     *
     * @param solrServer
     * @param doc
     */
    public static void addDocument(SolrClient solrServer, SolrInputDocument doc) {
        try {
            solrServer.add (doc);
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (solrServer != null) {
//                solrServer.shutdown ();
                try {
                    solrServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 更新多条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValues
     * @param map
     */
    public static void updateDocument(SolrDatasource datasource, String collectionName, String idName, List<String> idValues, Map<String, String> map) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        List<SolrInputDocument> docs = new ArrayList<> ();
        SolrInputDocument doc = null;
        Map<String, String> obj = null;
        for (String idValue : idValues) {
            doc = new SolrInputDocument ();
            doc.addField (idName, idValue);
            for (Map.Entry<String, String> entry : map.entrySet ()) {
                obj = new HashMap<> ();
                obj.put ("set", entry.getValue ());
                doc.addField (entry.getKey (), obj);
            }
            docs.add (doc);
        }
        addDocument (solrServer, docs);
    }

    /**
     * 添加多个文档
     *
     * @param solrServer
     * @param docs
     */
    public static void addDocument(SolrClient solrServer, List<SolrInputDocument> docs) {
        try {
            solrServer.add (docs);
            solrServer.commit ();
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (solrServer != null) {
//                solrServer.shutdown ();
                try {
                    solrServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除单条数据
     *
     * @param datasource
     * @param collectionName
     * @param ids
     */
    public static void deleteDocument(SolrDatasource datasource, String collectionName, List<String> ids) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        try {
            solrServer.deleteById (ids);
            solrServer.commit ();
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (solrServer != null) {
//                solrServer.shutdown ();
                try {
                    solrServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除多条数据
     *
     * @param datasource
     * @param collectionName
     * @param id
     */
    public static void deleteDocument(SolrDatasource datasource, String collectionName, String id) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        try {
            solrServer.deleteById (id);
            solrServer.commit ();
        } catch (SolrServerException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (solrServer != null) {
//                solrServer.shutdown ();
                try {
                    solrServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getQuery(List<WhereProperty> whereProperties) {
        StringBuffer queryConditons = new StringBuffer ("*:*");
        if (whereProperties != null && whereProperties.size () != 0) {
            for (WhereProperty property : whereProperties) {
                String name = property.getName ();
                String value = property.getValue ();
                Operator operator = property.getOperator ();
                if (StringUtils.isBlank (name) || StringUtils.isBlank (value) || operator == null) {
                    continue;
                }
                queryConditons.append (" AND " + name + getCondition (value, operator));
            }
        }
        return queryConditons.toString ();
    }

    /**
     * 分页查询
     *
     * @param datasource
     * @param collectionName
     * @param whereProperties
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static Page searchPage(SolrDatasource datasource, String collectionName, List<WhereProperty> whereProperties, int pageIndex, int pageSize) {
        Page page = null;
        SolrQuery query = new SolrQuery ();
        query.setQuery (getQuery (whereProperties));
        query.setStart ((pageIndex - 1) * pageSize);
        query.setRows (pageSize);
        QueryResponse rsp = getQueryResponse (datasource, collectionName, query);
        if (rsp != null) {
            SolrDocumentList sdl = rsp.getResults ();
            long totalCount = sdl.getNumFound ();
            List<Map<String, String>> list = getSolrReturnList (rsp);
            return new Page (list, pageIndex, pageSize, totalCount);
        }
        return page;
    }

    /**
     * 查询
     *
     * @param datasource
     * @param collectionName
     * @param whereProperties
     * @return
     */
    public static List<Map<String, String>> search(SolrDatasource datasource, String collectionName, List<WhereProperty> whereProperties) {
        SolrQuery query = new SolrQuery ();
        query.setQuery (getQuery (whereProperties));
        QueryResponse rsp = getQueryResponse (datasource, collectionName, query);
        return getSolrReturnList (rsp);
    }

    public static QueryResponse getQueryResponse(SolrDatasource datasource, String collectionName, SolrQuery query) {
        SolrClient solrServer = getSolrServer (datasource, collectionName);
        if (solrServer == null) {
            return null;
        }
        QueryResponse res = null;
        try {
            res = solrServer.query (query);
        } catch (SolrServerException e) {
            e.printStackTrace ();
            res = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Map<String, String>> getSolrReturnList(QueryResponse rsp) {
        if (rsp == null) return null;
        List<Map<String, String>> list = new ArrayList<> ();
        Map<String, String> map = null;
        SolrDocumentList docs = rsp.getResults ();
        for (int i = 0; i < docs.size (); i++) {
            SolrDocument doc = docs.get (i);
            map = new HashMap<> ();
            for (Map.Entry<String, Object> entry : doc.entrySet ()) {
                map.put (entry.getKey (), objectToString (entry.getValue ()));
            }
            list.add (map);
        }
        return list;
    }

    public static String objectToString(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).toString ();
        } else if (obj instanceof Long) {
            return ((Long) obj).toString ();
        } else if (obj instanceof Double) {
            return ((Double) obj).toString ();
        } else if (obj instanceof Float) {
            return ((Float) obj).toString ();
        } else if (obj instanceof Float) {
            return ((Float) obj).toString ();
        } else if (obj instanceof Boolean) {
            return ((Boolean) obj).toString ();
        } else {
            return (String) obj;
        }
    }

    public static String getCondition(String value, Operator operator) {
        String str = "";
        if (Operator.EQ == operator) {
            str = ":" + value;
        } else if (Operator.NE == operator) {
            str = ":(* NOT " + value + ")";
        } else if (Operator.GE == operator) {
            str = ":[" + value + " TO *]";
        } else if (Operator.GT == operator) {
            str = ":{" + value + " TO *]";
        } else if (Operator.LE == operator) {
            str = ":[* TO " + value + "]";
        } else if (Operator.LT == operator) {
            str = ":[* TO " + value + "}";
        } else if (Operator.IN == operator) {
            str = ":(" + value + ")";
        } else if (Operator.LK == operator) {
            str = ":*" + value + "*";
        } else if (Operator.RLIKE == operator) {
            str = ":" + value + "*";
        }
        return str;
    }
}
