-- 添加IM的数据源类型
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'PAIR_SOLR_HBASE', 'PAIR_SOLR_HBASE', null, 13, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'PAIR_SOLR_HBASE', 'PAIR_SOLR_HBASE', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'PAIR_SOLR_HBASE', 'PAIR_SOLR_HBASE', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_REALTIME_TYPE', 'PAIR_SOLR_HBASE', 'PAIR_SOLR_HBASE', null, 9, null, null, 'default', null);

-- 添加IM的数据源配置
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', '交互建模-数据源配置-PAIR_SOLR_HBASE', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'active.hbase.zk.quorum', '【主】HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'standby.hbase.zk.quorum', '【备】HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.rpc.timeout', '一次RPC请求的超时时间（毫秒）', null, 3, null, null, 'default', '5000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.client.retries.number', '客户端重试最大次数', null, 4, null, null, 'default', '3');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.client.pause', '重试的休眠时间（毫秒）', null, 5, null, null, 'default', '100');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'zookeeper.recovery.retry', 'zookeeper的重试次数（zk的重试总次数是：hbase.client.retries.number * zookeeper.recovery.retry）', null, 6, null, null, 'default', '3');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'zookeeper.recovery.retry.intervalmill', 'zookeeper重试的休眠时间（毫秒）', null, 7, null, null, 'default', '200');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.client.operation.timeout', 'HBase客户端发起一次数据操作直至得到响应之间总的超时时间，数据操作类型包括get、append、increment、delete、put等（毫秒）', null, 8, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.regionserver.lease.period', 'scan操作超时时间（毫秒）[已被弃用]', null, 9, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.client.scanner.timeout.period', 'scan操作超时时间（毫秒）', null, 10, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.security.authentication', 'HBase的安全认证方式', null, 11, null, null, 'default', 'kerberos');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hadoop.security.authentication', 'Hadoop安全认证方式', null, 12, null, null, 'default', 'kerberos');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.master.kerberos.principal', 'HBase Master 的 Kerberos Principal，如：hbase/node1@BIGDATA.HEX.COM', null, 13, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.regionserver.kerberos.principal', 'HBase RegionServer 的 Kerberos Principal，如：hbase/_HOST@BIGDATA.HEX.COM', null, 14, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'kerberos.principal', 'Kerberos Principal，如：test@BIGDATA.HEX.COM', null, 15, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'kerberos.keytab', 'Kerberos Keytab 文件路径，如：C:/kerberos/test.keytab 或 /root/test.keytab', null, 16, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'active.solr.servers', '【主】Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'active.solr.url', '【主】Solr的zookeeper地址、端口和目录，多个地址用逗号分隔，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr', null, 12, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'standby.solr.servers', '【备】Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 13, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'standby.solr.url', '【备】Solr的zookeeper地址、端口和目录，多个地址用逗号分隔，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr', null, 14, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'hbase.rootdir', 'HDFS根目录', null, 10, null, null, 'default', '/hbase');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'zookeeper.znode.parent', 'ZooKeeper Znode 父级', null, 10, null, null, 'default', '/hbase');

-- 添加IM的（目标）元数据配置
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', '交互建模-元数据参数-PAIR_SOLR+HBASE', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'solr.max.shards.per.node', 'Solr 单节点最大分片数', null, 3, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'solr.replicas', 'Solr 副本数', null, 2, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'solr.shards', 'Solr 分片数（solr.shards*solr.replicas<=solr.max.shards.per.node*节点数）', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.compression', 'HBase 压缩格式', null, 5, null, null, 'default', 'snappy');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.family', 'HBase 族名', null, 8, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.method', 'HBase 方法', null, 6, null, null, 'default', 'table_att');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.qualifier', 'HBase 列名', null, 9, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.region.num', 'HBase Region数量', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.split.policy', 'HBase 分区策略类', null, 7, null, null, 'default', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 10, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.fq.dsv.separator', '结果数据分隔符，如：|、||、\007、\t、\036', null, 11, null, null, 'default', '\007');

-- 添加IM的接口实现类
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'PAIR_SOLR_HBASE', 'com.hex.bigdata.udsp.im.converter.impl.PairSolrHBaseConverter', null, 13, null, null, 'default', '交互建模的主备Solr+HBase接口实现类');
