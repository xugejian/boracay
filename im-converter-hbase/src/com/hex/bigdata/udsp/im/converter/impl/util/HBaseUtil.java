package com.hex.bigdata.udsp.im.converter.impl.util;

import com.hex.bigdata.udsp.im.converter.impl.factory.HBaseAdminPoolFactory;
import com.hex.bigdata.udsp.im.converter.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.im.converter.impl.model.HBaseDatasource;
import com.hex.bigdata.udsp.im.converter.impl.model.HBaseMetadata;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-26.
 */
public class HBaseUtil {
    static {
        // 解决winutils.exe不存在的问题
        try {
            File workaround = new File (".");
            System.getProperties ().put ("hadoop.home.dir",
                    workaround.getAbsolutePath ());
            new File ("./bin").mkdirs ();
            new File ("./bin/winutils.exe").createNewFile ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private static Logger logger = LogManager.getLogger (HBaseUtil.class);

    private static Map<String, HBaseConnectionPoolFactory> dataSourcePool;
    private static Map<String, HBaseAdminPoolFactory> hbaseAdminPool;

    private static final String HBASE_REGION_START_KEY = "0000000000000000";
    private static final String HBASE_REGION_STOP_KEY = "ffffffffffffffff";

    /**
     * 发现HBaseAdminPoolFactory获取的HBaseAdmin会有链接断开的情况
     *
     * @param datasource
     * @return
     */
    @Deprecated
    public static synchronized HBaseAdminPoolFactory getHBaseAdminFactory(HBaseDatasource datasource) {
        String dsId = datasource.getId ();
        if (hbaseAdminPool == null) {
            hbaseAdminPool = new HashMap<> ();
        }
        HBaseAdminPoolFactory factory = hbaseAdminPool.remove (dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config ();
            config.lifo = true;
            config.minIdle = 1;
            config.maxIdle = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new HBaseAdminPoolFactory (config, datasource);
        }
        hbaseAdminPool.put (dsId, factory);
        return factory;
    }

    public static HBaseAdmin getHBaseAdmin(HBaseDatasource datasource) {
        Configuration conf = HBaseConfiguration.create ();
        conf.set ("hbase.zookeeper.quorum", datasource.gainZkQuorum ());
        conf.set ("hbase.zookeeper.property.clientPort", datasource.gainZkPort ());
        if (StringUtils.isNotBlank (datasource.gainRpcTimeout ())) {
            conf.set ("hbase.rpc.timeout", datasource.gainRpcTimeout ());
        }
        if (StringUtils.isNotBlank (datasource.gainClientRetriesNumber ())) {
            conf.set ("hbase.client.retries.number", datasource.gainClientRetriesNumber ());
        }
        if (StringUtils.isNotBlank (datasource.gainClientPause ())) {
            conf.set ("hbase.client.pause", datasource.gainClientPause ());
        }
        if (StringUtils.isNotBlank (datasource.gainZkRecoveryRetry ())) {
            conf.set ("zookeeper.recovery.retry", datasource.gainZkRecoveryRetry ());
        }
        if (StringUtils.isNotBlank (datasource.gainZkRecoveryRetryIntervalmill ())) {
            conf.set ("zookeeper.recovery.retry.intervalmill", datasource.gainZkRecoveryRetryIntervalmill ());
        }
        if (StringUtils.isNotBlank (datasource.gainClientOperationTimeout ())) {
            conf.set ("hbase.client.operation.timeout", datasource.gainClientOperationTimeout ());
        }
//        if (StringUtils.isNotBlank(datasource.gainRegionserverLeasePeriod()))
//            conf.set("hbase.regionserver.lease.period", datasource.gainRegionserverLeasePeriod()); // 已被弃用
        if (StringUtils.isNotBlank (datasource.gainClientScannerTimeoutPeriod ())) {
            conf.set ("hbase.client.scanner.timeout.period", datasource.gainClientScannerTimeoutPeriod ());
        }
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin (conf);
        } catch (IOException e) {
            logger.warn (e.getMessage ());
            e.printStackTrace ();
        }
        return admin;
//        try {
//            return getHBaseAdminFactory(datasource).getHBaseAdmin();
//        } catch (Exception e) {
//            logger.warn(e.getMessage());
//            return null;
//        }
    }

    public static void release(HBaseDatasource datasource, HBaseAdmin admin) {
//        getHBaseAdminFactory(datasource).releaseHBaseAdmin(admin);
        try {
            admin.close ();
        } catch (IOException e) {
            logger.warn (e.getMessage ());
            e.printStackTrace ();
        }
    }

    public static void release(HBaseAdmin admin) {
        try {
            admin.close ();
        } catch (IOException e) {
            logger.warn (e.getMessage ());
            e.printStackTrace ();
        }
    }

    public static synchronized HBaseConnectionPoolFactory getDataSource(HBaseDatasource datasource) {
        String dsId = datasource.getId ();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<> ();
        }
        HBaseConnectionPoolFactory factory = dataSourcePool.get (dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config ();
            config.lifo = true;
            config.minIdle = 1;
            config.maxIdle = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new HBaseConnectionPoolFactory (config, datasource);
        }
        dataSourcePool.put (dsId, factory);
        return factory;
    }

    public static HConnection getConnection(HBaseDatasource datasource) {
        try {
            return getDataSource (datasource).getConnection ();
        } catch (Exception e) {
            logger.warn (e.getMessage ());
            return null;
        }
    }

    public static void release(HBaseDatasource datasource, HConnection conn) {
        getDataSource (datasource).releaseConnection (conn);
    }

    /**
     * 获取HBase Regions分区的Hex Splits
     *
     * @param startKey
     * @param endKey
     * @param numRegions
     * @return
     */
    public static byte[][] getHexSplits(String startKey, String endKey, int numRegions) {
        byte[][] splits = new byte[numRegions - 1][];
        if (startKey.length () < 16) {
            int len = 16 - startKey.length ();
            for (int i = 0; i < len; i++) {
                startKey += "0";
            }
        }
        if (endKey.length () < 16) {
            int len = 16 - endKey.length ();
            for (int i = 0; i < len; i++) {
                endKey += "0";
            }
        }
        BigInteger lowestKey = new BigInteger (startKey, 16);
        BigInteger highestKey = new BigInteger (endKey, 16);
        BigInteger range = highestKey.subtract (lowestKey);
        BigInteger regionIncrement = range.divide (BigInteger.valueOf (numRegions));
        lowestKey = lowestKey.add (regionIncrement);
        for (int i = 0; i < numRegions - 1; i++) {
            BigInteger key = lowestKey.add (regionIncrement.multiply (BigInteger.valueOf (i)));
            byte[] b = String.format ("%016x", key).getBytes ();
            splits[i] = b;
        }
        return splits;
    }

    /**
     * 创建HBase表
     *
     * @return
     */
    public static void createHTable(HBaseMetadata metadata, boolean ifNotExists) throws Exception {
        HBaseDatasource datasource = new HBaseDatasource (metadata.getDatasource ());
        String tableName = metadata.getTbName ();
        // 判断是否已经存在该表
        if (isTableAvailable (datasource, tableName)) {
            logger.debug ("HBase表" + tableName + "存在，无需创建！");
            if (!ifNotExists) {
                throw new Exception ("HBase表" + tableName + "已经存在！");
            }
        } else {
            logger.debug ("HBase表" + tableName + "不存在，进行创建！");
            TableName hbaseTableName = TableName.valueOf (tableName);
            HTableDescriptor htd = new HTableDescriptor (hbaseTableName);
            // 族设置参数
            HColumnDescriptor hcd = new HColumnDescriptor (metadata.gainFamily ());
            hcd.setBlocksize (65536); // 块大小
            // 压缩
            String compression = metadata.gainCompression ();
            if (Compression.Algorithm.SNAPPY.getName ().equals (compression)) {
                hcd.setCompressionType (Compression.Algorithm.SNAPPY);
            } else if (Compression.Algorithm.GZ.getName ().equals (compression)) {
                hcd.setCompressionType (Compression.Algorithm.GZ);
            } else if (Compression.Algorithm.LZ4.getName ().equals (compression)) {
                hcd.setCompressionType (Compression.Algorithm.LZ4);
            } else if (Compression.Algorithm.LZO.getName ().equals (compression)) {
                hcd.setCompressionType (Compression.Algorithm.LZO);
            } else if (Compression.Algorithm.NONE.getName ().equals (compression)) {
                hcd.setCompressionType (Compression.Algorithm.NONE);
            }
            hcd.setMaxVersions (1); // 数据保存的最大版本数
            // hcd.setMinVersions(0); // 数据保存的最小版本数（配合TimeToLive使用）
            // hcd.setTimeToLive(36000); // 表中数据存储生命期，过期数据将自动被删除
            hcd.setBloomFilterType (BloomType.ROW); //
            hcd.setDataBlockEncoding (DataBlockEncoding.PREFIX);
            hcd.setBlockCacheEnabled (true); // 块缓存
            hcd.setInMemory (false); // 是否保存在内存中以提高相应速度
            String familyReplicationScope = metadata.gainFamilyReplicationScope ();
            if ("1".equals (familyReplicationScope)) {
                hcd.setScope (HConstants.REPLICATION_SCOPE_GLOBAL);
            } else if ("0".equals (familyReplicationScope)) {
                hcd.setScope (HConstants.REPLICATION_SCOPE_LOCAL);
            }
            htd.addFamily (hcd);
            // 表设置参数
            String splitPolicy = metadata.gainSplitPolicy ();
            if (StringUtils.isNotBlank (splitPolicy)) {
                htd.setConfiguration (HTableDescriptor.SPLIT_POLICY, splitPolicy);
            }
            // 聚合的协处理器
            htd.addCoprocessor ("org.apache.hadoop.hbase.coprocessor.AggregateImplementation");
            // 创建表
            byte[][] regionSplits = getHexSplits (HBASE_REGION_START_KEY, HBASE_REGION_STOP_KEY, metadata.gainRegionNum ());
            HBaseAdmin admin = HBaseUtil.getHBaseAdmin (datasource);
            try {
                admin.createTable (htd, regionSplits);
                logger.debug ("HBase表" + tableName + "创建成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "创建失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "创建失败！" + e.getMessage ());
            } finally {
                release (datasource, admin);
            }
        }
    }

    /**
     * 禁用HBase表
     *
     * @param tableName
     * @return
     */
    public static void disableHTable(HBaseDatasource datasource, String tableName, boolean ifExists) throws Exception {
        // 判断是否已经存在该表
        if (isTableAvailable (datasource, tableName)) {
            logger.debug ("HBase表" + tableName + "存在，进行禁用！");
            TableName hbaseTableName = TableName.valueOf (tableName);
            HBaseAdmin admin = getHBaseAdmin (datasource);
            try {
                admin.disableTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "禁用成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "禁用失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "禁用失败！" + e.getMessage ());
            } finally {
                release (datasource, admin);
            }
        } else {
            logger.debug ("HBase表" + tableName + "不存在，无需禁用！");
            if (!ifExists) {
                throw new Exception ("HBase表" + tableName + "不存在！");
            }
        }
    }

    /**
     * 删除HBase表（必须先禁用才可删除）
     *
     * @param tableName
     * @return
     */
    public static void deleteHTable(HBaseDatasource datasource, String tableName, boolean ifExists) throws Exception {
        // 判断是否已经存在该表
        if (isTableAvailable (datasource, tableName)) {
            logger.debug ("HBase表" + tableName + "存在，进行删除！");
            TableName hbaseTableName = TableName.valueOf (tableName);
            HBaseAdmin admin = getHBaseAdmin (datasource);
            try {
                admin.deleteTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "删除成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "删除失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "删除失败！" + e.getMessage ());
            } finally {
                release (datasource, admin);
            }
        } else {
            logger.debug ("HBase表" + tableName + "不存在，无需删除！");
            if (!ifExists) {
                throw new Exception ("HBase表" + tableName + "不存在！");
            }
        }
    }

    public static boolean isTableAvailable(HBaseDatasource datasource, String tableName) throws IOException {
        boolean status = false;
        TableName hbaseTableName = TableName.valueOf (tableName);
        HBaseAdmin admin = getHBaseAdmin (datasource);
        try {
            status = admin.isTableAvailable (hbaseTableName);
        } catch (IOException e) {
            e.printStackTrace ();
            throw new IOException (e);
        } finally {
            release (datasource, admin);
        }
        return status;
    }

    /**
     * 一步删除HBase表
     *
     * @param tableName
     * @return
     */
    public static void dropHTable(HBaseDatasource datasource, String tableName, boolean ifExists) throws Exception {
        // 判断是否已经存在该表
        if (isTableAvailable (datasource, tableName)) {
            logger.debug ("HBase表" + tableName + "存在，进行一步删除！");
            TableName hbaseTableName = TableName.valueOf (tableName);
            HBaseAdmin admin = getHBaseAdmin (datasource);
            try {
                admin.disableTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "禁用成功！");
                try {
                    admin.deleteTable (hbaseTableName);
                    logger.debug ("HBase表" + tableName + "删除成功！");
                } catch (IOException e) {
                    logger.warn ("HBase表" + tableName + "删除失败！");
                    e.printStackTrace ();
                    throw new IOException ("HBase表" + tableName + "删除失败！" + e.getMessage ());
                } finally {
                    release (datasource, admin);
                }
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "禁用失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "禁用失败！" + e.getMessage ());
            } finally {
                release (datasource, admin);
            }
        } else {
            logger.debug ("HBase表" + tableName + "不存在，无需一步删除！");
            if (!ifExists) {
                throw new Exception ("HBase表" + tableName + "不存在！");
            }
        }
    }

    /**
     * 清空HBase表数据
     *
     * @param datasource
     * @param tableName
     * @param ifExists
     * @throws Exception
     */
    public static void truncateHTable(HBaseDatasource datasource, String tableName, boolean ifExists) throws Exception {
        // 判断是否已经存在该表
        if (isTableAvailable (datasource, tableName)) {
            logger.debug ("HBase表" + tableName + "存在，进行一步清空！");
            TableName hbaseTableName = TableName.valueOf (tableName);
            HBaseAdmin admin = getHBaseAdmin (datasource);
            try {
                admin.disableTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "禁用成功！");
                try {
                    admin.truncateTable (hbaseTableName, true);
                    logger.debug ("HBase表" + tableName + "清空成功！");
                } catch (IOException e) {
                    logger.warn ("HBase表" + tableName + "清空失败！");
                    e.printStackTrace ();
                    throw new IOException ("HBase表" + tableName + "清空失败！" + e.getMessage ());
                } finally {
                    release (datasource, admin);
                }
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "禁用失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "禁用失败！" + e.getMessage ());
            } finally {
                release (datasource, admin);
            }
        } else {
            logger.debug ("HBase表" + tableName + "不存在，无需一步清空！");
            if (!ifExists) {
                throw new Exception ("HBase表" + tableName + "不存在！");
            }
        }
    }


    /**
     * 清空HBase表数据
     */
    public static void emptyHTable(HBaseMetadata metadata) throws Exception {
        HBaseDatasource datasource = new HBaseDatasource (metadata.getDatasource ());
        String tableName = metadata.getTbName ();
//        dropHTable (datasource, tableName, false);
//        createHTable (metadata, false);
        truncateHTable(datasource, tableName, false);
    }

    /**
     * 清空HBase表数据
     */
    public static boolean emptyHTable(HBaseDatasource datasource, String tableName, int numRegions, boolean ifExists) throws Exception {
        dropHTable (datasource, tableName, ifExists);

        TableName hbaseTableName = TableName.valueOf (tableName);
        HConnection conn = getConnection (datasource);
        HBaseAdmin admin = getHBaseAdmin (datasource);
        byte[][] regionSplits = getHexSplits (HBASE_REGION_START_KEY, HBASE_REGION_STOP_KEY, numRegions);
        try {
            HTableDescriptor hbaseTable = conn.getHTableDescriptor (hbaseTableName);
            admin.createTable (hbaseTable, regionSplits);
            logger.debug ("HBase表" + tableName + "创建成功！");
            return true;
        } catch (IOException e) {
            logger.warn ("HBase表" + tableName + "创建失败！");
            e.printStackTrace ();
            throw new IOException ("HBase表" + tableName + "创建失败！" + e.getMessage ());
        } finally {
            release (datasource, admin);
            release (datasource, conn);
        }
    }

    /**
     * put数据到HBase表的rowkey的族列（单个列）
     *
     * @param tableName
     * @param rowkey
     * @param family
     * @param qualifier
     * @param value
     * @return
     */
    public static void put(HBaseDatasource datasource, String tableName, String rowkey,
                           String family, String qualifier, String value) throws IOException {
        HConnection conn = null;
        HTableInterface table = null;
        try {
            conn = getConnection (datasource);
            table = conn.getTable (tableName);
            Put put = new Put (Bytes.toBytes (rowkey));
            put.add (Bytes.toBytes (family), Bytes.toBytes (qualifier), Bytes.toBytes (value));
            table.put (put);
            logger.debug ("添加数据成功！");
        } catch (IOException e) {
            logger.warn ("添加数据失败！" + e.getMessage ());
            e.printStackTrace ();
            throw new IOException (e);
        } finally {
            if (table != null) {
                table.close ();
            }
            release (datasource, conn);
        }
    }

    /**
     * put数据到HBase表的rowkey的族（多个列）
     *
     * @param datasource
     * @param tableName
     * @param rowkey
     * @param family
     * @param map
     * @throws IOException
     */
    public static void put(HBaseDatasource datasource, String tableName, String rowkey,
                           String family, Map<String, String> map) throws IOException {
        HConnection conn = null;
        HTableInterface table = null;
        try {
            conn = getConnection (datasource);
            table = conn.getTable (tableName);
            Put put = new Put (Bytes.toBytes (rowkey));
            for (Map.Entry<String, String> entry : map.entrySet ()) {
                byte[] qualifier = Bytes.toBytes (entry.getKey ());
                byte[] value = Bytes.toBytes (entry.getValue ());
                put.add (Bytes.toBytes (family), qualifier, value);
            }
            table.put (put);
            logger.debug ("添加数据成功！");
        } catch (IOException e) {
            logger.warn ("添加数据失败！" + e.getMessage ());
            e.printStackTrace ();
            throw new IOException (e);
        } finally {
            if (table != null) {
                table.close ();
            }
            release (datasource, conn);
        }
    }
}
