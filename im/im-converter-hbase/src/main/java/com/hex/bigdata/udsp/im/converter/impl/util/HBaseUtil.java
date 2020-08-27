package com.hex.bigdata.udsp.im.converter.impl.util;

import com.hex.bigdata.udsp.im.converter.impl.model.HBaseDatasource;
import com.hex.bigdata.udsp.im.converter.impl.model.HBaseMetadata;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by JunjieM on 2019-7-1.
 */
public class HBaseUtil {

    private static final String HBASE_REGION_START_KEY = "0000000000000000";
    private static final String HBASE_REGION_STOP_KEY = "ffffffffffffffff";

    private static Logger logger = LogManager.getLogger (HBaseUtil.class);

    private static void close(Admin admin) {
        if (admin != null) {
            try {
                admin.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }

    private static void close(Table table) {
        if (table != null) {
            try {
                table.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
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
            Admin admin = null;
            try {
                admin = HBaseConnectionPool.getConnection (datasource).getAdmin ();
                admin.createTable (htd, regionSplits);
                logger.debug ("HBase表" + tableName + "创建成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "创建失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "创建失败！" + e.getMessage ());
            } finally {
                close (admin);
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
            Admin admin = null;
            try {
                admin = HBaseConnectionPool.getConnection (datasource).getAdmin ();
                admin.disableTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "禁用成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "禁用失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "禁用失败！" + e.getMessage ());
            } finally {
                close (admin);
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
            Admin admin = null;
            try {
                admin = HBaseConnectionPool.getConnection (datasource).getAdmin ();
                admin.deleteTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "删除成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "删除失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "删除失败！" + e.getMessage ());
            } finally {
                close (admin);
            }
        } else {
            logger.debug ("HBase表" + tableName + "不存在，无需删除！");
            if (!ifExists) {
                throw new Exception ("HBase表" + tableName + "不存在！");
            }
        }
    }

    public static boolean isTableAvailable(HBaseDatasource datasource, String tableName) throws Exception {
        boolean status = false;
        TableName hbaseTableName = TableName.valueOf (tableName);
        Admin admin = null;
        try {
            admin = HBaseConnectionPool.getConnection (datasource).getAdmin ();
            status = admin.isTableAvailable (hbaseTableName);
        } catch (Exception e) {
            e.printStackTrace ();
            throw new Exception (e);
        } finally {
            close (admin);
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
            Admin admin = null;
            try {
                admin = HBaseConnectionPool.getConnection (datasource).getAdmin ();
                admin.disableTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "禁用成功！");
                admin.deleteTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "删除成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "禁用或删除失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "禁用或删除失败！" + e.getMessage ());
            } finally {
                close (admin);
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
            Admin admin = null;
            try {
                admin = HBaseConnectionPool.getConnection (datasource).getAdmin ();
                admin.disableTable (hbaseTableName);
                logger.debug ("HBase表" + tableName + "禁用成功！");
                admin.truncateTable (hbaseTableName, true);
                logger.debug ("HBase表" + tableName + "清空成功！");
            } catch (IOException e) {
                logger.warn ("HBase表" + tableName + "禁用或删除失败！");
                e.printStackTrace ();
                throw new IOException ("HBase表" + tableName + "禁用或删除失败！" + e.getMessage ());
            } finally {
                close (admin);
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
        truncateHTable (datasource, tableName, false);
    }

    /**
     * 清空HBase表数据
     */
    public static boolean emptyHTable(HBaseDatasource datasource, String tableName, int numRegions, boolean ifExists) throws Exception {
        dropHTable (datasource, tableName, ifExists);

        Connection conn = null;
        Admin admin = null;
        TableName hbaseTableName = TableName.valueOf (tableName);
        byte[][] regionSplits = getHexSplits (HBASE_REGION_START_KEY, HBASE_REGION_STOP_KEY, numRegions);
        try {
            conn = HBaseConnectionPool.getConnection (datasource);
            admin = conn.getAdmin ();
            HTableDescriptor hbaseTable = admin.getTableDescriptor (hbaseTableName);
            admin.createTable (hbaseTable, regionSplits);
            logger.debug ("HBase表" + tableName + "创建成功！");
            return true;
        } catch (IOException e) {
            logger.warn ("HBase表" + tableName + "创建失败！");
            e.printStackTrace ();
            throw new IOException ("HBase表" + tableName + "创建失败！" + e.getMessage ());
        } finally {
            close (admin);
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
                           String family, String qualifier, String value) throws Exception {
        Connection conn = null;
        Table table = null;
        try {
            conn = HBaseConnectionPool.getConnection (datasource);
            table = conn.getTable (TableName.valueOf (tableName));
            Put put = new Put (Bytes.toBytes (rowkey));
//            put.add (Bytes.toBytes (family), Bytes.toBytes (qualifier), Bytes.toBytes (value));
            put.addColumn (Bytes.toBytes (family), Bytes.toBytes (qualifier), Bytes.toBytes (value));
            table.put (put);
            logger.debug ("添加数据成功！");
        } catch (IOException e) {
            logger.warn ("添加数据失败！" + e.getMessage ());
            e.printStackTrace ();
            throw new IOException (e);
        } finally {
            close (table);
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
                           String family, Map<String, String> map) throws Exception {
        Connection conn = null;
        Table table = null;
        try {
            conn = HBaseConnectionPool.getConnection (datasource);
            table = conn.getTable (TableName.valueOf (tableName));
            Put put = new Put (Bytes.toBytes (rowkey));
            for (Map.Entry<String, String> entry : map.entrySet ()) {
                byte[] qualifier = Bytes.toBytes (entry.getKey ());
                byte[] value = Bytes.toBytes (entry.getValue ());
//                put.add (Bytes.toBytes (family), qualifier, value);
                put.addColumn (Bytes.toBytes (family), qualifier, value);
            }
            table.put (put);
            logger.debug ("添加数据成功！");
        } catch (IOException e) {
            logger.warn ("添加数据失败！" + e.getMessage ());
            e.printStackTrace ();
            throw new IOException (e);
        } finally {
            close (table);
        }
    }

    /**
     * 连接是否异常
     *
     * @param datasource
     * @return
     */
    public static boolean isAborted(HBaseDatasource datasource) {
        return HBaseConnectionPool.getConnection (datasource) == null;
    }
}
