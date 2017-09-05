prompt PL/SQL Developer import file
prompt Created on 2017年9月4日 by JunjieM
set feedback off
set define off
prompt Loading COM_DATASOURCE...
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('cf2ce388877644d0848194fd13152285', 'hex_dev_redis', '致宇大数据redis交互查询', 'REDIS', null, '0', 'admin', '2017-07-31 22:08:05.024', 'admin', '2017-07-31 22:08:05.024', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('e4680c30fba940698beb19a217a891e5', 'hex_dev_hbase_for_hostname', '致宇大数据开发环境HBASE使用主机名方式', 'HBASE', null, '0', 'admin', '2017-07-31 22:08:07.886', 'admin', '2017-07-31 22:08:07.886', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('c23ffa34de214b769fc57e7da423cf16', 'hex_dev_hbase_for_proxy', '致宇大数据开发环境HBASE使用代理方式', 'HBASE', null, '0', 'admin', '2017-07-31 22:08:09.969', 'admin', '2017-07-31 22:08:09.969', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('f0e8932771c8499ab39be729ae007101', 'hex_dev_solr_hbase_for_proxy', '致宇大数据开发环境SOLR+HBASE使用代理方式', 'SOLR_HBASE', null, '0', 'admin', '2017-07-31 22:08:11.025', 'admin', '2017-07-31 22:08:11.025', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('4f81658c49ea4ffaab4197e77dc3b820', 'hex_impala', '致宇大数据-Impala数据源', 'IMPALA', null, '0', 'admin', '2017-07-31 22:08:52.716', 'admin', '2017-09-02 07:58:52.263', null, 'OLQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('39dc538bfbe2463da58469242af48ab0', 'hex_dev_kafka', '致宇公司开发环境', 'KAFKA', null, '0', 'admin', '2017-08-17 09:03:30.800', 'admin', '2017-08-17 09:03:30.800', null, 'RTS');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('53dbd410f3664ad697fb33eacd77853c', 'hex_dev_hbase', '致宇大数据开发环境HBASE', 'HBASE', null, '0', 'admin', '2017-07-31 22:08:06.838', 'admin', '2017-07-31 22:08:06.838', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('56221da424da45688b005243caa7bacf', 'hex_dev_solr_hbase_for_hostname', '致宇大数据开发环境SOLR+HBASE使用主机名方式', 'SOLR_HBASE', null, '0', 'admin', '2017-07-31 22:08:08.924', 'admin', '2017-07-31 22:08:08.924', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('b8f83b642df642b8af5d853d49de9c0a', 'hex_dev_solr_hbase', '致宇大数据开发环境SOLR+HBASE', 'SOLR_HBASE', null, '0', 'admin', '2017-07-31 22:08:12.069', 'admin', '2017-07-31 22:08:12.069', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('31bb8f04f37e4b2da5b10999d05a6f9c', 'hex_oracle', '本地oracle', 'ORACLE', null, '0', 'admin', '2017-08-01 10:06:40.373', 'admin', '2017-08-01 10:06:40.373', null, 'OLQ');
commit;
prompt 10 records loaded
prompt Loading COM_PROPERTIES...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('172ad5a3617c4f2b88febbbfd1b9b428', '53dbd410f3664ad697fb33eacd77853c', 'hbase.zk.quorum', '192.168.1.61,192.168.1.62,192.168.1.63', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d456ca9855ad4dc28e38a69317b155b1', '53dbd410f3664ad697fb33eacd77853c', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f0cb3833ca1a46f7b027bf4cd5ce5174', '56221da424da45688b005243caa7bacf', 'hbase.zk.quorum', 'node1,node2,node3', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6eb26b585364478383669aca338fc8b2', '56221da424da45688b005243caa7bacf', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('92378f650c1b4bb1bd9f0ccf1c6b606a', '56221da424da45688b005243caa7bacf', 'solr.servers', 'node1:8983,node2:8983,node3:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('55e6c0c0085f4ffb869fcd06b2355c9f', 'b8f83b642df642b8af5d853d49de9c0a', 'hbase.zk.quorum', '192.168.1.61,192.168.1.62,192.168.1.63', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5bc7a632d20a4b67a60a27c1bf000999', 'b8f83b642df642b8af5d853d49de9c0a', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('aa8ae4046b144b978b5265ceb57d8185', 'b8f83b642df642b8af5d853d49de9c0a', 'solr.servers', '192.168.1.61:8983,192.168.1.62:8983,192.168.1.63:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('12bc97d5c6214291a3782dbece326f9d', '4f81658c49ea4ffaab4197e77dc3b820', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'Impala 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('91aaa379df85422d8a2dbccfeb7818d1', '4f81658c49ea4ffaab4197e77dc3b820', 'jdbc.url', 'jdbc:hive2://192.168.1.61:21050', 'Impala JDBC URL，如：无密码jdbc:hive2://${ip}:${port}/;auth=noSasl  有密码jdbc:hive2://${ip}:${port}/');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4bcf802a33494c5397e25f6ca5c06ba7', '4f81658c49ea4ffaab4197e77dc3b820', 'username', 'hive', 'Impala 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7ace7b0a751742dd90f44b83527c3919', '4f81658c49ea4ffaab4197e77dc3b820', 'password', '111111', 'Impala 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b38c760602c94f67a05db27485497ba5', '4f81658c49ea4ffaab4197e77dc3b820', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('48dcdd3bc5684ea6a5d6a10120ae2e66', '4f81658c49ea4ffaab4197e77dc3b820', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('60f7d6e6ea6546709de81392d73dc984', '4f81658c49ea4ffaab4197e77dc3b820', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ad5997505d4f4d688eeeae00529b6938', '4f81658c49ea4ffaab4197e77dc3b820', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('194ce2316ff74a6daf70d4a4c9c3dd3e', '4f81658c49ea4ffaab4197e77dc3b820', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5fc4dec7d0264788a981a949910689f9', '4f81658c49ea4ffaab4197e77dc3b820', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('868c52b716e34b3ebd85e0cf7d236ab1', '4f81658c49ea4ffaab4197e77dc3b820', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9602cef9b5f54af59247246da49fe99f', '4f81658c49ea4ffaab4197e77dc3b820', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ef49394207a14a4fa88a0ca4fb991a34', '4f81658c49ea4ffaab4197e77dc3b820', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26cd988c1355417f821fdbb8721621e0', '4f81658c49ea4ffaab4197e77dc3b820', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('410a79c2b02c453e82cd5904846ca6a5', '4f81658c49ea4ffaab4197e77dc3b820', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('de3d517e3eb34ef681534279c5acebaf', '4f81658c49ea4ffaab4197e77dc3b820', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cd26f6af7a364259aef9da967687b93a', '4f81658c49ea4ffaab4197e77dc3b820', 'max.connected.size', '2000', null);
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ae7fd42dea2e495c92ae47376ae20594', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6c7900878d3644b58c6bc655e7ab63b9', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('968c26ba1ba74d1d9edb4da9e2a9a94b', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('82e533bd208d4e72b93b33a3173dc80a', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9750e3b5f90c460d9db823a8da2c2ee4', '3b9c3e875f854ec2bf36a23f2c444e8d', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7948434f022646f5919b61abf64bf314', '3b9c3e875f854ec2bf36a23f2c444e8d', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c6ef45a80b614001a58a629a80c60a0a', '3b9c3e875f854ec2bf36a23f2c444e8d', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('51a7ab484d164a3aac5f11a14d4132e7', '3b9c3e875f854ec2bf36a23f2c444e8d', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('240cfa390553461bb0b39070419411fa', '3b9c3e875f854ec2bf36a23f2c444e8d', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2a7c2acfa780476a995dbac554c48429', '3b9c3e875f854ec2bf36a23f2c444e8d', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6c50b4a6ee2041309f56286963554fb4', '2e3b08b5009348f6a1511334710d46c1', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3678c201c1b5418493c5562b0880bb61', '2e3b08b5009348f6a1511334710d46c1', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26f4204ed5ef41b7ae41d1d5dc0dfa5f', '2e3b08b5009348f6a1511334710d46c1', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('541b98f345da47a5a0449c55fce53967', '2e3b08b5009348f6a1511334710d46c1', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('98dce2ed5d5148e5977a1250dd706b26', '39dc538bfbe2463da58469242af48ab0', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6dd02455d99841d784799c07805e3517', '3275cbea78534218b8e5495a77e285e4', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.21:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('65a2acb88f56486c925ee097ef49a78d', '3275cbea78534218b8e5495a77e285e4', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0799891d478e405a88c4162340624abe', '3275cbea78534218b8e5495a77e285e4', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('97f9c2a25e584f4492d9eaac52622d20', '3275cbea78534218b8e5495a77e285e4', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fc900abddca74aa29807acbd600d8a3a', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('66a2c5802cae462d8cd7c65df5b791fa', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4cf52fc3aaf6433ba60204a0b975426e', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ce0fa0c5e04041d68c96b98b27c32e1d', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('73ca365a3a804e11a66c20b2600d5624', '60bebb6656a6460c80d95e382ee7fd9e', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b51fcfc0ec3e420990ae68637671ddc5', '60bebb6656a6460c80d95e382ee7fd9e', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('34044191abcf4831b3b2d302af836611', '60bebb6656a6460c80d95e382ee7fd9e', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('764e19b08f534dd98f8e19cb7b5afdcc', '60bebb6656a6460c80d95e382ee7fd9e', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('51e1cdcc0e4a4e709180f46912ddbc2e', '60bebb6656a6460c80d95e382ee7fd9e', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d0991af1af23428aa0d4dfad94f9069f', '60bebb6656a6460c80d95e382ee7fd9e', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('69f7b1829df9459ea95f41b81b62cd30', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('38c65ffc99414c6aae5f91e1aa9fd0b0', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fbf57de98e8543179c0e9e0d984e9694', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b8cc18c4e43647219a1c79c2b3d11cb8', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ba2935dbc9df41269078c4a291cc3ba8', '1ec11dfb5beb4e00902d8048acf04320', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('659c052dabf04b1a8e1308d5beb998b8', '1ec11dfb5beb4e00902d8048acf04320', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e7e8118d5fef4e21b85389503b624551', '1ec11dfb5beb4e00902d8048acf04320', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5b4feac5a9c94d94b5afccd9e98e8231', '1ec11dfb5beb4e00902d8048acf04320', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('800ffc6fd566421c977bd85e17f3fa05', '1ec11dfb5beb4e00902d8048acf04320', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8b117522eaee4c23867626a242bee1bd', '1ec11dfb5beb4e00902d8048acf04320', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('225b64ee971b45bca3ec59abb54e6949', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('29f798ad38da4b78a1694eb25da22390', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('78265a9786cb4c0b859bffbd45ca8188', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d62828ccfc3a43e7a3ec1de229ff06f6', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('77ec8ec182dd4fa2937d35361d5ec4af', '8f8a9c78d75943d9b62374fa12a3e879', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7cbe7e30c9d449cd95ed17f0c64c9439', '8f8a9c78d75943d9b62374fa12a3e879', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9dff1eeb22ab43e09e772e3b574eaf1f', '8f8a9c78d75943d9b62374fa12a3e879', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('42a2adbd827c41089c86d831f609a978', '8f8a9c78d75943d9b62374fa12a3e879', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('948f3ef0757642b4b3f12fb92cf765f3', 'cf2ce388877644d0848194fd13152285', 'redis.connection.port', '6379', 'redis连接端口号');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b901000f1665464dafd99cfd2cf49685', 'cf2ce388877644d0848194fd13152285', 'redis.max.idle', '10000', 'redis连接最大空闲数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('691f0c8820bc4c36b4080d74036a8ea0', 'cf2ce388877644d0848194fd13152285', 'redis.max.wait', '1000', 'redis连接最长等待时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1dafce4b11214b32882d50c10bf16866', 'cf2ce388877644d0848194fd13152285', 'redis.max.timeOut', '1000', 'redis连接最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9729565bad65482fa3f65590a976f026', 'cf2ce388877644d0848194fd13152285', 'redis.test.on.brrow', 'true', 'redis连接是否检查连通性');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('037118408b564163aa04f7a8804b0ab7', 'cf2ce388877644d0848194fd13152285', 'redis.max.total', '20000', 'redis连接池最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e2949336952a4957ac1ec85b9d9e5a0f', 'cf2ce388877644d0848194fd13152285', 'redis.connection.ip', '192.168.1.61', 'redis连接ip地址');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7771a0f385dd44c1b41d1ddf5e3922ec', 'cf2ce388877644d0848194fd13152285', 'redis.seprator', '\\007', '结果数据分隔符');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('08263318a3ca43d8adef8965c166037e', 'e4680c30fba940698beb19a217a891e5', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3cf504146a2d41558f0ea4fb2b5f763a', 'e4680c30fba940698beb19a217a891e5', 'hbase.zk.quorum', 'node1,node2,node3', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8d9305e52fdb4bad990e71289457608b', 'c23ffa34de214b769fc57e7da423cf16', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e1fdf4bb75844619842ae523f01ffc04', 'c23ffa34de214b769fc57e7da423cf16', 'hbase.zk.quorum', '192.168.1.61', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4745c4133841493daddc364515952be6', 'f0e8932771c8499ab39be729ae007101', 'solr.servers', '192.168.1.61:8983,192.168.1.62:8983,192.168.1.63:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8f307628f5df4298969bb50e9563fe07', 'f0e8932771c8499ab39be729ae007101', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('663063bc230048178e3f78d0daf9b1bb', 'f0e8932771c8499ab39be729ae007101', 'hbase.zk.quorum', '192.168.1.61', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('98a68c0b56ad411588887daaf46e75cf', '31bb8f04f37e4b2da5b10999d05a6f9c', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'oracle 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('439cb03c127344e296ab0f9316811501', '31bb8f04f37e4b2da5b10999d05a6f9c', 'jdbc.url', 'jdbc:oracle:thin:@192.168.1.61:1521/orcl', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f32136d3839341dfbcb2403783a0ddba', '31bb8f04f37e4b2da5b10999d05a6f9c', 'username', 'udsp', 'oracle 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('48221d9bd82d44eba7806676d5190865', '31bb8f04f37e4b2da5b10999d05a6f9c', 'password', 'udsp', 'oracle 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8502e920f17e4499a061397afd38e57d', '31bb8f04f37e4b2da5b10999d05a6f9c', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('37485d4b1cfb4e5389a863ad18a2953f', '31bb8f04f37e4b2da5b10999d05a6f9c', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('840442046c6e42b3a49596405dea3fb5', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('221c1b079ac94c658ca4c3babe1b99ec', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('05873260db274164a2b477f09f47c1cf', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9808b5c035be4b738e635285733282ef', '31bb8f04f37e4b2da5b10999d05a6f9c', 'validation.query', 'select 1 from dual', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c340b31353514ee788ac2b61d420b5f1', '31bb8f04f37e4b2da5b10999d05a6f9c', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('09b0b26e501b4a339ccf4cd892fbe4e4', '31bb8f04f37e4b2da5b10999d05a6f9c', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4b88f7645d5e470bbd7df831167684eb', '31bb8f04f37e4b2da5b10999d05a6f9c', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
commit;
prompt 100 records committed...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d110e3c915dc42df956b05e9384107f8', '31bb8f04f37e4b2da5b10999d05a6f9c', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cef26964294544f8a7f0c4704f83f029', '31bb8f04f37e4b2da5b10999d05a6f9c', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26239700450a49d9b64fb57b206ca9fd', '31bb8f04f37e4b2da5b10999d05a6f9c', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2019278def2d411db2b4ad3e52f9c9f9', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.data.size', '65535', '最大数据返回条数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fc39878e36cf41448a50281215222165', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26a86b60ca464ea8b60232eabc4151aa', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6f3a92889b3a4b2197c9a679d5b2fa23', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1ca60f14e36142a78caedddae0b66d26', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b2a833d8613c495f9da90955c86b35ff', '38ebdd9795a84d2aae51cd753859e325', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('56b25ef9c2fe4fbcb2f691a3b3931862', '38ebdd9795a84d2aae51cd753859e325', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('15ec68981dd2495a8e1018833934b2ce', '38ebdd9795a84d2aae51cd753859e325', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b019e6c13d094de1a0d8fa60da2be11d', '38ebdd9795a84d2aae51cd753859e325', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('24c90ae641ee44b084c022df62cbe89c', '38ebdd9795a84d2aae51cd753859e325', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3e237892db80458e897ef33d22a71550', '38ebdd9795a84d2aae51cd753859e325', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e43c1f4c74284704ac1f990ab66ad821', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5f7938484d174b51b4d726ec092ae506', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('82e0bb7a4b574a94946c063bbbee4663', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2a37e1eaf1fc49c0a4108816271b25f9', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('dd11091b9f1a499088777194334d8f8c', '44aebab24d4741eb9d240fd198e15490', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b9f750a5a6964423b4ad510e1907a536', '44aebab24d4741eb9d240fd198e15490', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1c88d9ad72e140e3949d31041122c71e', '44aebab24d4741eb9d240fd198e15490', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ab6b429af762442788d35df35ca57b26', '44aebab24d4741eb9d240fd198e15490', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5a20683204bf48b799ba50a5345e2865', '44aebab24d4741eb9d240fd198e15490', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ade8cf0e91d841a7953a1e3f99b93f8e', '44aebab24d4741eb9d240fd198e15490', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('540afda11856413b9e60fcb049cd3590', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ebc99be608a145b9b5402720cb8c4fd6', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('76fc6c4e38544e379deb8f76e9c009f7', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4ee09817437c4f18905fb968c4098521', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'request.required.acks', '0', '请求确认模式');
commit;
prompt 128 records loaded
prompt Loading IQ_APPLICATION...
insert into IQ_APPLICATION (pk_id, md_id, name, describe, note, max_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('5f7693ccf3f449fdb575cdd5e9d32f32', '7ffb213154444588900d239b7f5e3131', 'soa_cupatrxjnl_solr_hbase_app', '前置银联明细查询', null, 60000, '0', 'admin', '2017-07-31 22:08:37.597', 'admin', '2017-07-31 22:08:37.597');
commit;
prompt 1 records loaded
prompt Loading IQ_APPLICATION_ORDER_COLUMN...
prompt Table is empty
prompt Loading IQ_APPLICATION_QUERY_COLUMN...
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('0657f3bf3d1f446c9076834f35387a43', '5f7693ccf3f449fdb575cdd5e9d32f32', 1, 'system_tracr_no', '系统跟踪号', 'STRING', null, '1', null, '==', 'system_tracr_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('52cec670e39744be95dfa91c29b8f48a', '5f7693ccf3f449fdb575cdd5e9d32f32', 2, 'card_no', '卡号', 'STRING', null, '1', null, '==', 'card_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('5085801b88c646ec92010e701145a9b7', '5f7693ccf3f449fdb575cdd5e9d32f32', 3, 'tran_in_acct_no', '转入账号', 'STRING', null, '1', null, '==', 'tran_in_acct_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('411431a461ba4cd58456d6943bcc3b67', '5f7693ccf3f449fdb575cdd5e9d32f32', 4, 'channel_type', '渠道类型', 'STRING', null, '1', null, '==', 'channel_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('c3eae89dc347436fbc6944b47c418593', '5f7693ccf3f449fdb575cdd5e9d32f32', 5, 'amt', '交易金额(金额)', 'STRING', null, '1', null, '==', 'amt', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('5fd9b758a2e545999e6ae5074030c5f5', '5f7693ccf3f449fdb575cdd5e9d32f32', 6, 'auth_code', '授权码', 'STRING', null, '1', null, '==', 'auth_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('af29c3610ac445d6b39e4ae672e4f201', '5f7693ccf3f449fdb575cdd5e9d32f32', 7, 'bu_settlement_date', '银联清算日期', 'STRING', null, '1', null, '==', 'bu_settlement_date', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('be52291625334199a143c724f0e71344', '5f7693ccf3f449fdb575cdd5e9d32f32', 8, 'teller_no', '柜员号', 'STRING', null, '1', null, '==', 'teller_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('1c4a7acdab2145678a93e4b590002d92', '5f7693ccf3f449fdb575cdd5e9d32f32', 9, 'bu_tran_code', '银联交易码', 'STRING', null, '1', null, '==', 'bu_tran_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('4bd52868739844a38da3ccef2f5fc5a5', '5f7693ccf3f449fdb575cdd5e9d32f32', 10, 'ccy', '币种', 'STRING', null, '1', null, '==', 'ccy', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('3cfa42ed7b864df889093c2e18366520', '5f7693ccf3f449fdb575cdd5e9d32f32', 11, 'message_type', '报文类型', 'STRING', null, '1', null, '==', 'message_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('b53eb1dbddb34b77b96317a61d668c7c', '5f7693ccf3f449fdb575cdd5e9d32f32', 12, 'tran_status', '交易状态', 'STRING', null, '1', null, '==', 'tran_status', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('173f3962a408416d9b6e3c0e40014428', '5f7693ccf3f449fdb575cdd5e9d32f32', 13, 'term_seq_no', '终端流水号', 'STRING', null, '1', null, '==', 'term_seq_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('797a6d9f271a484ba3709be32a759bbe', '5f7693ccf3f449fdb575cdd5e9d32f32', 14, 'paymen_date', '支付日期', 'STRING', null, '1', null, '==', 'paymen_date', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('fa5532a6f0fe4b57ae50d83434e1e821', '5f7693ccf3f449fdb575cdd5e9d32f32', 15, 'medium_type', '介质类型', 'STRING', null, '1', null, '==', 'medium_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('a255e61893264ff1a6f492f9bf4e2d8d', '5f7693ccf3f449fdb575cdd5e9d32f32', 16, 'retr_ref_no', '检索参考号', 'STRING', null, '1', null, '==', 'retr_ref_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('b303197ee1ae455ea2f2010d5ebab63b', '5f7693ccf3f449fdb575cdd5e9d32f32', 17, 'uni_message_type', '统一报文类型', 'STRING', null, '1', null, '==', 'uni_message_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('41d58a471cb44a5b8cf606874b17ad17', '5f7693ccf3f449fdb575cdd5e9d32f32', 18, 'bu_ret_code', '银联标准返回代码', 'STRING', null, '1', null, '==', 'bu_ret_code', '0');
commit;
prompt 18 records loaded
prompt Loading IQ_APPLICATION_RETURN_COLUMN...
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('39cdf71e72274b36a4fb9bd48b75d8d4', '5f7693ccf3f449fdb575cdd5e9d32f32', 'accept_branch_id', '受理机构', 'STRING', 'none', 'accept_branch_id', 1, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('29d9636e374444428f92b3883e7766e3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'branch_id', '发送方机构ID(机构码)', 'STRING', 'none', 'branch_id', 2, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b5391d2aa8844df69467fa174d545ece', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_time', '交易时间', 'STRING', 'none', 'tran_time', 3, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('9757535129f148c1893531d3648ae763', '5f7693ccf3f449fdb575cdd5e9d32f32', 'system_tracr_no', '系统跟踪号', 'STRING', 'none', 'system_tracr_no', 4, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('75c6b809c02744f3be340d8c11d13459', '5f7693ccf3f449fdb575cdd5e9d32f32', 'paymen_date', '支付日期', 'STRING', 'none', 'paymen_date', 5, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('42edf515303642c4aada75067c9c47f3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'channel_type', '渠道类型', 'STRING', 'none', 'channel_type', 6, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('ef3d4394feda4ea68854da1f7d870581', '5f7693ccf3f449fdb575cdd5e9d32f32', 'uni_message_type', '统一报文类型', 'STRING', 'none', 'uni_message_type', 7, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f623ef1226fa43939f4fa44545959fa1', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_tran_code', '银联交易码', 'STRING', 'none', 'bu_tran_code', 8, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e2032a2147b4433288910267cbe176fe', '5f7693ccf3f449fdb575cdd5e9d32f32', 'card_no', '卡号', 'STRING', 'none', 'card_no', 9, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b6ba3905668d4cd4903e9de1415bc93e', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ic_trxamt', '交易金额(金额)', 'STRING', 'none', 'ic_trxamt', 10, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('fad36dcfb86846eeb48727b43ad79212', '5f7693ccf3f449fdb575cdd5e9d32f32', 'accept_date', '受理日期', 'STRING', 'none', 'accept_date', 11, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('72a89d94f7954b74813ac44948d6e482', '5f7693ccf3f449fdb575cdd5e9d32f32', 'accept_time', '受理时间', 'STRING', 'none', 'accept_time', 12, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('16c6b639262f447c9ab19e3399d8f581', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_settlement_date', '银联清算日期', 'STRING', 'none', 'bu_settlement_date', 13, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a2ab116d09354efbb71bb68a69a4da08', '5f7693ccf3f449fdb575cdd5e9d32f32', 'trader_type', '商户类型', 'STRING', 'none', 'trader_type', 14, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('9ef5d566e912459782b7ecc45af9517f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'country_code', '国家代码', 'STRING', 'none', 'country_code', 15, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2dc55af844124123af6161e2a1b115f4', '5f7693ccf3f449fdb575cdd5e9d32f32', 'acct_input_type', '主账号输入方式', 'STRING', 'none', 'acct_input_type', 16, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('09147b474ed141f296f114fb4e2ee15f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'passwoar_inpuat_type', '密码输入方式', 'STRING', 'none', 'passwoar_inpuat_type', 17, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('dbd01f5bc8be4411a5648749f0ca4be7', '5f7693ccf3f449fdb575cdd5e9d32f32', 'card_serial_no', '卡序号', 'STRING', 'none', 'card_serial_no', 18, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d15bfea6f43f4d67894fe28e88d861ea', '5f7693ccf3f449fdb575cdd5e9d32f32', 'commission', '手续费金额', 'STRING', 'none', 'commission', 19, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f4122e95ef0146d6807f309f2f00ed0b', '5f7693ccf3f449fdb575cdd5e9d32f32', 'retr_ref_no', '检索参考号', 'STRING', 'none', 'retr_ref_no', 20, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('509def8dd1cd420c933dffa8f1ac3e2f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'auth_code', '授权码', 'STRING', 'none', 'auth_code', 21, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('619feaef59e84637b05a495f3733f6be', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_ret_code', '银联标准返回代码', 'STRING', 'none', 'bu_ret_code', 22, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('cecbecd745ed4415a357e34d8c1a7715', '5f7693ccf3f449fdb575cdd5e9d32f32', 'term_no', '终端号', 'STRING', 'none', 'term_no', 23, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e0c89bea44524d109589e12df0d41cd5', '5f7693ccf3f449fdb575cdd5e9d32f32', 'mech_no', '商户号', 'STRING', 'none', 'mech_no', 24, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7a97b2aa82e84aedb149bc738c7f03bc', '5f7693ccf3f449fdb575cdd5e9d32f32', 'trader_name_addr', '商户名称地址', 'STRING', 'none', 'trader_name_addr', 25, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('291fb5bf62494753a19acbb6d237b451', '5f7693ccf3f449fdb575cdd5e9d32f32', 'add_data', '附加数据', 'STRING', 'none', 'add_data', 26, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('52340052cee941bfb9eb66516b9237a3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_ccy', '银联货币代码', 'STRING', 'none', 'bu_ccy', 27, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('55f68febb2ab4dc78ab3ef28bf86becb', '5f7693ccf3f449fdb575cdd5e9d32f32', 'reason_msg', '原因内容', 'STRING', 'none', 'reason_msg', 28, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('68b66b1096e44d9d87aaabb6170abb1d', '5f7693ccf3f449fdb575cdd5e9d32f32', 'input_cap_flag', '输入能力标志', 'STRING', 'none', 'input_cap_flag', 29, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('25f5b8bfb973447c8d84d0652a00002a', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ws_type', '终端类型', 'STRING', 'none', 'ws_type', 30, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('fb6c496338184a908c67dad2ed9b9679', '5f7693ccf3f449fdb575cdd5e9d32f32', 'interactive_mode', '交互方式', 'STRING', 'none', 'interactive_mode', 31, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('79be3c20acaf46c7838f5e525c11b64f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'special_fee_type', '特殊费用类型', 'STRING', 'none', 'special_fee_type', 32, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('102412f54a5543da848d4b4b1f25607f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'special_fee_level', '特殊费用级别', 'STRING', 'none', 'special_fee_level', 33, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e5428a48552b49788f6b5355bceb94f7', '5f7693ccf3f449fdb575cdd5e9d32f32', 'medium_type', '介质类型', 'STRING', 'none', 'medium_type', 34, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('87838570283c483abfd061c2a61e0621', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_mode', '交易模式', 'STRING', 'none', 'tran_mode', 35, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3cc11212e87f46bfa67427a979e64f52', '5f7693ccf3f449fdb575cdd5e9d32f32', 'use_type', '使用类型', 'STRING', 'none', 'use_type', 36, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('47d9cab3bd804831aa0252f90eb459ff', '5f7693ccf3f449fdb575cdd5e9d32f32', 'acct_type', '账户类型', 'STRING', 'none', 'acct_type', 37, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a443af678c5b433ca338482d14f8f398', '5f7693ccf3f449fdb575cdd5e9d32f32', 'user_level', '用户级别', 'STRING', 'none', 'user_level', 38, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a5e224a2cba34dd8a46a1bf8f6e6a8bd', '5f7693ccf3f449fdb575cdd5e9d32f32', 'product_type', '产品类型', 'STRING', 'none', 'product_type', 39, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d515742b3d014f949f9819855ac49f85', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ori_accept_branch_id', '原受理机构标识码', 'STRING', 'none', 'ori_accept_branch_id', 40, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('35f97ac1547d44a5bf98323d2cc4c282', '5f7693ccf3f449fdb575cdd5e9d32f32', 'old_branch_id', '原发送方机构ID', 'STRING', 'none', 'old_branch_id', 41, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('436aa788f25343189059d5a5a05d8b86', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ori_tran_time', '原交易时间', 'STRING', 'none', 'ori_tran_time', 42, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3931799600d7423ebecc28688ea512cc', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ori_system_tracr_no', '原系统跟踪号', 'STRING', 'none', 'ori_system_tracr_no', 43, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('869e01be74ce4f4e83929f142aded7f3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'rcv_bank', '接收机构', 'STRING', 'none', 'rcv_bank', 44, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2e8653426fb046baa75a7a18f6f0c94b', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_in_acct_no', '转入账号', 'STRING', 'none', 'tran_in_acct_no', 45, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('dd30855b35d647c09b4bcc37cb004d3a', '5f7693ccf3f449fdb575cdd5e9d32f32', 'trader_discount', '商户折扣率', 'STRING', 'none', 'trader_discount', 46, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d0f130062243426a86e4098fe65e1425', '5f7693ccf3f449fdb575cdd5e9d32f32', 'term_seq_no', '终端流水号', 'STRING', 'none', 'term_seq_no', 47, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('cf83bb41f58c4394b83651be0ed146b4', '5f7693ccf3f449fdb575cdd5e9d32f32', 'app_bank_id', '经办行行号', 'STRING', 'none', 'app_bank_id', 48, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1d530f4de92247f7beebb832fb05e904', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bank_type', '银行类型', 'STRING', 'none', 'bank_type', 49, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('600f7cda901845a3b854e7601c511cb6', '5f7693ccf3f449fdb575cdd5e9d32f32', 'app_branch_no', '经办机构', 'STRING', 'none', 'app_branch_no', 50, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5804200b257e448eb9540d3c1284535e', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_bank_id', '主交易行行号', 'STRING', 'none', 'main_bank_id', 51, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d4e51ea071c54df6a49f1e3ce3888424', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_bank_type', '主交易银行类型', 'STRING', 'none', 'main_bank_type', 52, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1a42ca92ba2c4a46b0f47b46d407bcba', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_branch_id', '主交易机构码', 'STRING', 'none', 'main_branch_id', 53, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f78ac286abfa4d06afca02c905e1e5ca', '5f7693ccf3f449fdb575cdd5e9d32f32', 'sub_acct_no', '子账号', 'STRING', 'none', 'sub_acct_no', 54, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e10f37cc973046fc913d4dd1cdbb1399', '5f7693ccf3f449fdb575cdd5e9d32f32', 'assi_bank_id', '副交易行号', 'STRING', 'none', 'assi_bank_id', 55, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('07821b018a3c49209db9572b72e4e88c', '5f7693ccf3f449fdb575cdd5e9d32f32', 'assi_bank_type', '副交易银行类型', 'STRING', 'none', 'assi_bank_type', 56, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3dbd5711629e45579c720df127618243', '5f7693ccf3f449fdb575cdd5e9d32f32', 'assi_branch_id', '副交易机构码', 'STRING', 'none', 'assi_branch_id', 57, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d28819a832834709a5ead6acc6d42f83', '5f7693ccf3f449fdb575cdd5e9d32f32', 'teller_no', '柜员号', 'STRING', 'none', 'teller_no', 58, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a2f926dba81644809d8869a44f2459c9', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_code', '交易码', 'STRING', 'none', 'tran_code', 59, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5f06672ea7b642a88bc0e63aa72bdfa6', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ccy', '币种', 'STRING', 'none', 'ccy', 60, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('85c672fa0df84a908aaa83651217bc6d', '5f7693ccf3f449fdb575cdd5e9d32f32', 'dr_cr_flag', '借贷标志', 'STRING', 'none', 'dr_cr_flag', 61, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('c6be339a27c84c1285e8f3d4ae02ba2e', '5f7693ccf3f449fdb575cdd5e9d32f32', 'amt', '交易金额(金额)', 'STRING', 'none', 'amt', 62, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('ac3377cd3caa47a7a7baddd3d1775a7c', '5f7693ccf3f449fdb575cdd5e9d32f32', 'limit_id', '限额编号', 'STRING', 'none', 'limit_id', 63, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('bba614749f494d7e93e409e52d78263d', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_card_no', '主卡号', 'STRING', 'none', 'main_card_no', 64, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1147d40acd0a4c1b9b9f51aa1957cafc', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_limit_id', '交易限额号', 'STRING', 'none', 'tran_limit_id', 65, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1cf7d9eb90874e0db6b12ea2a108bbe7', '5f7693ccf3f449fdb575cdd5e9d32f32', 'business_scope', '主经营范围', 'STRING', 'none', 'business_scope', 66, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('db6177f5d57044c6b315e377058e1ff3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'message_type', '报文类型', 'STRING', 'none', 'message_type', 67, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d05e10bf3396482e841922080d2ef648', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_status', '交易状态', 'STRING', 'none', 'tran_status', 68, null);
commit;
prompt 68 records loaded
prompt Loading IQ_METADATA...
insert into IQ_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name)
values ('ac1a651160814a5fafa821b0589eb95b', '53dbd410f3664ad697fb33eacd77853c', 'SOA_HBASE_CUPATRXJNL_HBASE', '前置银联查询', null, '0', 'admin', '2017-08-01 11:16:02.550', 'admin', '2017-08-01 11:16:02.550', 'SOA_HBASE_CUPATRXJNL');
insert into IQ_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name)
values ('7ffb213154444588900d239b7f5e3131', 'b8f83b642df642b8af5d853d49de9c0a', 'soa_cupatrxjnl_solr_hbase', '前置银联明细查询', null, '0', 'admin', '2017-07-31 22:08:25.086', 'admin', '2017-07-31 22:08:25.086', 'HDS_CUPATRXJNL');
commit;
prompt 2 records loaded
prompt Loading IQ_METADATA_COLUMN...
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8c7c21b6acd54d158bdffc1c0b24937a', 'ac1a651160814a5fafa821b0589eb95b', 1, 'card_no', '卡号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a18f762dbbeb491e90e58f5e1293e4a6', 'ac1a651160814a5fafa821b0589eb95b', 2, 'paymen_date', '支付时间', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2767537ebd9147578c2650581471deb5', 'ac1a651160814a5fafa821b0589eb95b', 1, 'card_no', '卡号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('e3a8467e5e4f4a1fbff5abeca41a208e', 'ac1a651160814a5fafa821b0589eb95b', 2, 'paymen_date', '支付时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('3b26a736d09c4cda805e5c9b8607e4a3', '7ffb213154444588900d239b7f5e3131', 1, 'accept_branch_id', '受理机构', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('89236f77a947451ca50d26569e2b3da5', '7ffb213154444588900d239b7f5e3131', 2, 'branch_id', '发送方机构ID(机构码)', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('1282c34a52c844c3ae0d6945b00e857a', '7ffb213154444588900d239b7f5e3131', 3, 'tran_time', '交易时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8bf6a5b302d4476eaf20627aeb6e90ce', '7ffb213154444588900d239b7f5e3131', 4, 'system_tracr_no', '系统跟踪号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b190ac9d03094c718acc6cb9829af5ff', '7ffb213154444588900d239b7f5e3131', 5, 'paymen_date', '支付日期', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('56185e2da57e486fba73211c1032981f', '7ffb213154444588900d239b7f5e3131', 6, 'channel_type', '渠道类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0c51ed0c26ac46de85ac5e384ab32bdb', '7ffb213154444588900d239b7f5e3131', 7, 'uni_message_type', '统一报文类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('004fb88e7674470b89c33046e63e51b7', '7ffb213154444588900d239b7f5e3131', 8, 'bu_tran_code', '银联交易码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2271b6b49d9241628b66f65a2381d358', '7ffb213154444588900d239b7f5e3131', 9, 'card_no', '卡号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('1f05c71080c24f5bb4e1a10c39341933', '7ffb213154444588900d239b7f5e3131', 10, 'ic_trxamt', '交易金额(金额)', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('fe3d985ce3524c049ab465c65731bb35', '7ffb213154444588900d239b7f5e3131', 11, 'accept_date', '受理日期', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('be4e3bedf0b241159d0aec19877199f7', '7ffb213154444588900d239b7f5e3131', 12, 'accept_time', '受理时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('9729419773294b4181bd9f2618b93b06', '7ffb213154444588900d239b7f5e3131', 13, 'bu_settlement_date', '银联清算日期', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('906f6997a1d242cd8390baa24c7fd9be', '7ffb213154444588900d239b7f5e3131', 14, 'trader_type', '商户类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('635fa142e5c046a1895fad0c086e3d5b', '7ffb213154444588900d239b7f5e3131', 15, 'country_code', '国家代码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('d66ce9a0427c4baaad449f495efc5324', '7ffb213154444588900d239b7f5e3131', 16, 'acct_input_type', '主账号输入方式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('5c6930bc2ee94f3fb0dd2b9543cee1f8', '7ffb213154444588900d239b7f5e3131', 17, 'passwoar_inpuat_type', '密码输入方式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b6b2cfd1615d4c02b19165089a15a067', '7ffb213154444588900d239b7f5e3131', 18, 'card_serial_no', '卡序号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0f11275aeac74d3fab83e0827e35cf25', '7ffb213154444588900d239b7f5e3131', 19, 'commission', '手续费金额', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('5fcdc5bca0284545a213116e3b6f65c7', '7ffb213154444588900d239b7f5e3131', 20, 'retr_ref_no', '检索参考号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2425ad718fd943079002e7f312219eac', '7ffb213154444588900d239b7f5e3131', 21, 'auth_code', '授权码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7bccd5e2dc5449458b62104ad137f6ee', '7ffb213154444588900d239b7f5e3131', 22, 'bu_ret_code', '银联标准返回代码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7b075797b8fa4b56a22fe985b5110d1d', '7ffb213154444588900d239b7f5e3131', 23, 'term_no', '终端号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a1a086623c044311979665d4967b6a05', '7ffb213154444588900d239b7f5e3131', 24, 'mech_no', '商户号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('ece59236906545a99dfbe1a8b57590c4', '7ffb213154444588900d239b7f5e3131', 25, 'trader_name_addr', '商户名称地址', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('47daf62a403a42ce832784f30744058a', '7ffb213154444588900d239b7f5e3131', 26, 'add_data', '附加数据', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('399d462c1e2a441e8639e9991b522091', '7ffb213154444588900d239b7f5e3131', 27, 'bu_ccy', '银联货币代码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('4acdae2e791344cdbd4b76c14da232bd', '7ffb213154444588900d239b7f5e3131', 28, 'reason_msg', '原因内容', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('818c080eb3cb4fd1a429f762b286672c', '7ffb213154444588900d239b7f5e3131', 29, 'input_cap_flag', '输入能力标志', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('f4083cc2d65e4f8a96a1f82de73fe1ac', '7ffb213154444588900d239b7f5e3131', 30, 'ws_type', '终端类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a98761b1c5064d2c85d7b4b1a3e48b2f', '7ffb213154444588900d239b7f5e3131', 31, 'interactive_mode', '交互方式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7e2a451e69ee4176b128e3ee5690473d', '7ffb213154444588900d239b7f5e3131', 32, 'special_fee_type', '特殊费用类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('6e4f95f987624d8cbaeecfc8e54b22c6', '7ffb213154444588900d239b7f5e3131', 33, 'special_fee_level', '特殊费用级别', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('ff03161c794142868023448ede2bf89a', '7ffb213154444588900d239b7f5e3131', 34, 'medium_type', '介质类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('64f9023de12049d893bfc7eeb038e6d2', '7ffb213154444588900d239b7f5e3131', 35, 'tran_mode', '交易模式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a80b4073a6094f25a6c4de787d192280', '7ffb213154444588900d239b7f5e3131', 36, 'use_type', '使用类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('400324b05d0540b7a8bd994a21c01c20', '7ffb213154444588900d239b7f5e3131', 37, 'acct_type', '账户类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8fc6851560af4c9e8d14e720fb940631', '7ffb213154444588900d239b7f5e3131', 38, 'user_level', '用户级别', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('129e5c741d8646e993043677fa9cfcf5', '7ffb213154444588900d239b7f5e3131', 39, 'product_type', '产品类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('286eeef78d824b9f8a7421e68bfa6a07', '7ffb213154444588900d239b7f5e3131', 40, 'ori_accept_branch_id', '原受理机构标识码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('878e929a7c064e1bb7fb13118d28cee3', '7ffb213154444588900d239b7f5e3131', 41, 'old_branch_id', '原发送方机构ID', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('3bbe991703d443d5a3b5335c24145b03', '7ffb213154444588900d239b7f5e3131', 42, 'ori_tran_time', '原交易时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('9150df0bca0a4300bf9aba3bfe2bfa5c', '7ffb213154444588900d239b7f5e3131', 43, 'ori_system_tracr_no', '原系统跟踪号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0b3e4c917e9f47edac6cb0acb340df8f', '7ffb213154444588900d239b7f5e3131', 44, 'rcv_bank', '接收机构', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('6bbb8f1cf7e443d18a97b19cf7abda5b', '7ffb213154444588900d239b7f5e3131', 45, 'tran_in_acct_no', '转入账号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7168fd39000442778d8519c94df9534c', '7ffb213154444588900d239b7f5e3131', 46, 'trader_discount', '商户折扣率', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('922b9eb3638f49578a44f5ed8b1c11f9', '7ffb213154444588900d239b7f5e3131', 47, 'term_seq_no', '终端流水号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('bfb6bd2ff8084f14a2c137e2c35479e3', '7ffb213154444588900d239b7f5e3131', 48, 'app_bank_id', '经办行行号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2028f526fa1d42a1b82fd215e762401c', '7ffb213154444588900d239b7f5e3131', 49, 'bank_type', '银行类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('076c54301cbb4a8d9b9cd9939f92a20d', '7ffb213154444588900d239b7f5e3131', 50, 'app_branch_no', '经办机构', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('12b764f0f295429c890c6bf7ea0e44cd', '7ffb213154444588900d239b7f5e3131', 51, 'main_bank_id', '主交易行行号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('864f881f49324b6288e2cd41e7e6e40a', '7ffb213154444588900d239b7f5e3131', 52, 'main_bank_type', '主交易银行类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('24cd1610e1a84566ba13ad419628e3c1', '7ffb213154444588900d239b7f5e3131', 53, 'main_branch_id', '主交易机构码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2a707745a7e44696818641012436ddc2', '7ffb213154444588900d239b7f5e3131', 54, 'sub_acct_no', '子账号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('5af0f83c5c5c4e878b23a75810df87db', '7ffb213154444588900d239b7f5e3131', 55, 'assi_bank_id', '副交易行号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('4bd9a87892d64d05b587554d483d5268', '7ffb213154444588900d239b7f5e3131', 56, 'assi_bank_type', '副交易银行类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8ba3be135f3a484ca54199df9a54ae3b', '7ffb213154444588900d239b7f5e3131', 57, 'assi_branch_id', '副交易机构码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8ef99bbba0264f11b75263f9545e139c', '7ffb213154444588900d239b7f5e3131', 58, 'teller_no', '柜员号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('778b68d0360446328bf54ca1829bab8c', '7ffb213154444588900d239b7f5e3131', 59, 'tran_code', '交易码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b350eecd9b244adaa234a39eb240e937', '7ffb213154444588900d239b7f5e3131', 60, 'ccy', '币种', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a72922465ac744e5b96f03eda66a7dfe', '7ffb213154444588900d239b7f5e3131', 61, 'dr_cr_flag', '借贷标志', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('541db3f58e3c4cc6ac54127404af685a', '7ffb213154444588900d239b7f5e3131', 62, 'amt', '交易金额(金额)', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('af703415c9d34f3b890d52d610741bca', '7ffb213154444588900d239b7f5e3131', 63, 'limit_id', '限额编号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('136a98538d8f4260813553cf36cb4d89', '7ffb213154444588900d239b7f5e3131', 64, 'main_card_no', '主卡号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('c52773f1692b4e1ba63fb0b3ba0698e5', '7ffb213154444588900d239b7f5e3131', 65, 'tran_limit_id', '交易限额号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0bde7b69b2214f78867cc77141498a0d', '7ffb213154444588900d239b7f5e3131', 66, 'business_scope', '主经营范围', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('df26e83784a8404699535aa98eb74965', '7ffb213154444588900d239b7f5e3131', 67, 'message_type', '报文类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('63bbc4d59baf4b5aafd0feb41a26d790', '7ffb213154444588900d239b7f5e3131', 68, 'tran_status', '交易状态', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('37fbdbbe80fd4185aa8ab16e4bb26776', '7ffb213154444588900d239b7f5e3131', 1, 'system_tracr_no', '系统跟踪号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b03db4867adf48878c343188868de6be', '7ffb213154444588900d239b7f5e3131', 2, 'card_no', '卡号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b5fd29758c00406090d02d11c712fbbb', '7ffb213154444588900d239b7f5e3131', 3, 'tran_in_acct_no', '转入账号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('db78fa2aac46475390e8d2038c756dcc', '7ffb213154444588900d239b7f5e3131', 4, 'channel_type', '渠道类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('21df131c30b4477781a937a90f43a0eb', '7ffb213154444588900d239b7f5e3131', 5, 'amt', '交易金额(金额)', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('70484df0ac4e4746b09da3c0343a9a7c', '7ffb213154444588900d239b7f5e3131', 6, 'auth_code', '授权码', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('34c19569476f48ce803bf3363d052770', '7ffb213154444588900d239b7f5e3131', 7, 'bu_settlement_date', '银联清算日期', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('ff5731a0233d471e81c1fb91e284d10c', '7ffb213154444588900d239b7f5e3131', 8, 'teller_no', '柜员号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('4c45981e68be4a2493581ad6d2b7d4e6', '7ffb213154444588900d239b7f5e3131', 9, 'bu_tran_code', '银联交易码', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b5cbfefaf306425cad4856f94c4c0650', '7ffb213154444588900d239b7f5e3131', 10, 'ccy', '币种', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('54e1897323e24cbca8a1672177d367b1', '7ffb213154444588900d239b7f5e3131', 11, 'message_type', '报文类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('485a02b0c6c74369b09262f4fcc3fbd7', '7ffb213154444588900d239b7f5e3131', 12, 'tran_status', '交易状态', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7ae50a5d9fd348579993c6ba29cca915', '7ffb213154444588900d239b7f5e3131', 13, 'term_seq_no', '终端流水号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('1e0a4c620f2f490daced9d669bf5eece', '7ffb213154444588900d239b7f5e3131', 14, 'paymen_date', '支付日期', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b92661c1f7b14d4c9410ec7b1877e318', '7ffb213154444588900d239b7f5e3131', 15, 'medium_type', '介质类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('c61425e6f8e9478c870df5491bd88709', '7ffb213154444588900d239b7f5e3131', 16, 'retr_ref_no', '检索参考号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2347093b16094d83a05075abad953eb2', '7ffb213154444588900d239b7f5e3131', 17, 'uni_message_type', '统一报文类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('13c8061a9de54d21987f289c86b14eef', '7ffb213154444588900d239b7f5e3131', 18, 'bu_ret_code', '银联标准返回代码', '1', null, null, 'STRING');
commit;
prompt 90 records loaded
prompt Loading MM_APPLICATION...
prompt Table is empty
prompt Loading MM_APPLICATION_EXECUTE_PARAM...
prompt Table is empty
prompt Loading MM_APPLICATION_RETURN_PARAM...
prompt Table is empty
prompt Loading MM_CONTRACTOR...
prompt Table is empty
prompt Loading OLQ_APPLICATION...
insert into OLQ_APPLICATION (pk_id, olq_sql, olq_ds_id, max_num, del_flg, crt_user, crt_time, upt_user, upt_time, note, name, describe)
values ('eacef02a7a504e44bd9c8ba188785098', 'SELECT * FROM hds.hds_cupatrxjnl  ' || chr(10) || 'ORDER BY main_card_no  ' || chr(10) || 'limit ${limit} offset ${offset}', '4f81658c49ea4ffaab4197e77dc3b820', null, '0', 'admin', '2017-08-01 09:56:18.874', 'admin', '2017-09-02 08:11:33.617', null, 'hds_cupatrxjnl', '前置银联查询');
commit;
prompt 1 records loaded
prompt Loading OLQ_APPLICATION_PARAM...
insert into OLQ_APPLICATION_PARAM (pk_id, param_name, param_desc, default_value, is_need, olq_app_id, seq)
values ('1eaf629ef1574c078fdb3a757f98d9f6', 'limit', '查询数', '10', '0', 'eacef02a7a504e44bd9c8ba188785098', 1);
insert into OLQ_APPLICATION_PARAM (pk_id, param_name, param_desc, default_value, is_need, olq_app_id, seq)
values ('3e4d297e99bd494d8949e8090be43e20', 'offset', '开始数', '0', '0', 'eacef02a7a504e44bd9c8ba188785098', 2);
commit;
prompt 2 records loaded
prompt Loading RC_SERVICE...
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('5cfc9cf2667640ca97017b1b07df009d', 'hds_cupatrxjnl', '前置银联SQL查询', 'OLQ_APP', 'eacef02a7a504e44bd9c8ba188785098', '0', 'admin', '2017-08-01 10:18:15.505', 'admin', '2017-08-01 10:18:15.505');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('ce6bcad33e32415cbaa460223824c6b5', 'rts_jyls_data_producer', null, 'RTS_PRODUCER', '3275cbea78534218b8e5495a77e285e4', '0', 'admin', '2017-08-17 09:27:41.287', 'admin', '2017-08-17 09:27:41.287');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('28bfcbec97d44efca2bb75a0b60b4267', 'rts_jyls_total_producer', '实时交易流水-按总量统计-生产者', 'RTS_PRODUCER', '2e3b08b5009348f6a1511334710d46c1', '0', 'admin', '2017-08-17 16:26:01.179', 'admin', '2017-08-17 16:26:01.179');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('dc61bb9ef31541f9acfb9f27adab73e4', 'rts_jyls_branch_consumer', '实时交易流水-按网点统计-消费者', 'RTS_CONSUMER', '60bebb6656a6460c80d95e382ee7fd9e', '0', 'admin', '2017-08-17 16:28:25.737', 'admin', '2017-08-17 16:28:25.737');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('36717f7406934c25aae98cbea2a1a8a7', 'rts_jyls_borough_consumer', '实时交易流水队列-按区域统计-消费者', 'RTS_CONSUMER', '38ebdd9795a84d2aae51cd753859e325', '0', 'admin', '2017-08-17 16:28:58.256', 'admin', '2017-08-17 16:28:58.256');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('b95ace7d0da14ec286d8cfc7b9fa43b6', 'rts_jyls_branch_producer', '实时交易流水-按网点统计-生产者', 'RTS_PRODUCER', '8f8a9c78d75943d9b62374fa12a3e879', '0', 'admin', '2017-08-17 16:24:52.430', 'admin', '2017-08-17 16:24:52.430');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('5b92cc87714a4a9b9bf8b94ebefba056', 'rts_jyls_total_consumer', '实时交易流水-按总量统计-消费者', 'RTS_CONSUMER', '1ec11dfb5beb4e00902d8048acf04320', '0', 'admin', '2017-08-17 16:27:30.548', 'admin', '2017-08-17 16:27:30.548');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('012cf5961471409595dcbd9551d03a4e', 'rts_jyls_jylx_consumer', '实时交易流水-按交易类型统计-消费者', 'RTS_CONSUMER', '44aebab24d4741eb9d240fd198e15490', '0', 'admin', '2017-08-17 16:28:02.377', 'admin', '2017-08-17 16:28:02.377');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('0198091e57434cc5a472aecbcf469515', 'rts_jyls_borough_producer', '实时交易流水队列-按区域统计', 'RTS_PRODUCER', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', '0', 'admin', '2017-08-17 16:26:48.379', 'admin', '2017-08-17 16:26:48.379');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('693b80333b304f8285a1a5a6a70cb26f', 'hex_cupatrxjnl_service', null, 'IQ', '5f7693ccf3f449fdb575cdd5e9d32f32', '0', 'admin', '2017-08-08 11:42:26.844', 'admin', '2017-08-08 11:42:26.844');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('6722d8418a8b4bd78e4b4da11584bfed', 'rts_jyls_jylx_producer', '实时交易流水-按交易类型统计-生产者', 'RTS_PRODUCER', '82f6a904cd6546c3b1c1cd6bdcf6214b', '0', 'admin', '2017-08-17 16:25:31.140', 'admin', '2017-08-17 16:25:31.140');
commit;
prompt 11 records loaded
prompt Loading RC_USER_SERVICE...
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('b665d634d4b34c3ebadde9387c0c4e2a', 'rtsmap', 'dc61bb9ef31541f9acfb9f27adab73e4', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.351', 'admin', '2017-08-17 16:30:00.351');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('e0e838f332204815910559b7503b2afc', 'admin', '5cfc9cf2667640ca97017b1b07df009d', null, 100, 50, '0', 'admin', '2017-08-08 11:40:00.981', 'admin', '2017-08-08 11:40:00.981');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('a7652dc3654b4d6a8f58116e58f14618', 'rtsmap', 'ce6bcad33e32415cbaa460223824c6b5', null, 100, 100, '0', 'admin', '2017-08-17 09:33:26.887', 'admin', '2017-08-17 09:33:26.887');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('f466bb109c934bec9426273e39ab30f7', 'rtsmap', '5b92cc87714a4a9b9bf8b94ebefba056', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.395', 'admin', '2017-08-17 16:30:00.395');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('a4d5cb1f8c1941a8a51a54bf9b10e7f4', 'rtsmap', '28bfcbec97d44efca2bb75a0b60b4267', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.440', 'admin', '2017-08-17 16:30:00.440');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('93490151691841b097e7dc3e1810314e', 'rtsmap', 'b95ace7d0da14ec286d8cfc7b9fa43b6', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.489', 'admin', '2017-08-17 16:30:00.489');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('b594778e5dc94e9187a927e21363a395', 'rtsmap', '36717f7406934c25aae98cbea2a1a8a7', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.306', 'admin', '2017-08-17 16:30:00.306');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('9cf3db9895274195900246a3389d2c43', 'rtsmap', '012cf5961471409595dcbd9551d03a4e', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.373', 'admin', '2017-08-17 16:30:00.373');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('fa628dac20f44fb2bf6fe459f106732e', 'rtsmap', '0198091e57434cc5a472aecbcf469515', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.418', 'admin', '2017-08-17 16:30:00.418');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('0665d80be99e472dab1f98e77773ac77', 'rtsmap', '6722d8418a8b4bd78e4b4da11584bfed', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.462', 'admin', '2017-08-17 16:30:00.462');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('b03a851be555486abe0d7941def3ca24', 'admin', '693b80333b304f8285a1a5a6a70cb26f', null, 100, 50, '0', 'admin', '2017-08-08 11:42:41.759', 'admin', '2017-08-08 11:42:41.759');
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('7ffe2eaf5d124fdda44956bc65c748c3', 'tomnic', '5cfc9cf2667640ca97017b1b07df009d', null, 10, 10, '0', 'admin', '2017-08-01 10:18:40.296', 'admin', '2017-08-01 10:18:40.296');
commit;
prompt 12 records loaded
prompt Loading RTS_CUSTOMER_CONFIG...
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('3b9c3e875f854ec2bf36a23f2c444e8d', '6afe89b053c14217b5de4b2bb4822bb4', 'rts_jyls_data_consumer', '交易流水测试', 'group2', null, '0', 'admin', '2017-08-17 14:20:29.840', 'admin', '2017-08-17 14:20:29.840');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('38ebdd9795a84d2aae51cd753859e325', 'c1b08f3657314c23aa527c54dd955948', 'rts_jyls_borough_consumer', '实时交易流水队列-按区域统计-消费者', 'rtsmap', '实时交易流水队列-按区域统计-消费者', '0', 'admin', '2017-08-17 16:05:28.580', 'admin', '2017-08-17 16:05:59.859');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('60bebb6656a6460c80d95e382ee7fd9e', '66bde8beaf534c82b71bc91dce7d2102', 'rts_jyls_branch_consumer', '实时交易流水-按网点统计-消费者', 'rtsmap', '实时交易流水-按网点统计-消费者', '0', 'admin', '2017-08-17 16:08:36.256', 'admin', '2017-08-17 16:08:36.256');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('1ec11dfb5beb4e00902d8048acf04320', '3a3dad3c5d0542f4be9f17141bd9a4b3', 'rts_jyls_total_consumer', '实时交易流水-按总量统计-消费者', 'rtsmap', '实时交易流水-按总量统计-消费者', '0', 'admin', '2017-08-17 16:11:04.714', 'admin', '2017-08-17 16:11:04.714');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('44aebab24d4741eb9d240fd198e15490', '020f4314855a434482867b0243a1a849', 'rts_jyls_jylx_consumer', '实时交易流水-按交易类型统计-消费者', 'rtsmap', '实时交易流水-按交易类型统计-消费者', '0', 'admin', '2017-08-17 16:09:53.984', 'admin', '2017-08-17 16:09:53.984');
commit;
prompt 5 records loaded
prompt Loading RTS_METADATA...
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('6afe89b053c14217b5de4b2bb4822bb4', '39dc538bfbe2463da58469242af48ab0', 'jyls_data', '交易流水原始数据', null, '0', 'admin', '2017-08-17 09:24:29.375', 'admin', '2017-08-17 09:24:29.375', 'rts.jyls.data');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('c1b08f3657314c23aa527c54dd955948', '39dc538bfbe2463da58469242af48ab0', 'jyls_borough', '实时交易流水队列-按区域统计', '实时交易流水队列-按区域统计', '0', 'admin', '2017-08-17 15:31:59.212', 'admin', '2017-08-17 16:03:25.153', 'rts_jyls_borough_udsp');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('66bde8beaf534c82b71bc91dce7d2102', '39dc538bfbe2463da58469242af48ab0', 'jyls_branch', '实时交易流水-按网点统计', '实时交易流水-按网点统计', '0', 'admin', '2017-08-17 15:36:29.498', 'admin', '2017-08-17 15:52:26.574', 'rts_jyls_branch_udsp');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('020f4314855a434482867b0243a1a849', '39dc538bfbe2463da58469242af48ab0', 'jyls_jylx', '实时交易流水-按交易类型统计', '实时交易流水-按交易类型统计', '0', 'admin', '2017-08-17 15:43:42.460', 'admin', '2017-08-17 15:52:13.614', 'rts_jyls_jylx_udsp');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('3a3dad3c5d0542f4be9f17141bd9a4b3', '39dc538bfbe2463da58469242af48ab0', 'jyls_total', '实时交易流水-按总量统计', '实时交易流水-按总量统计', '0', 'admin', '2017-08-17 15:51:42.945', 'admin', '2017-08-17 15:51:55.877', 'rts_jyls_total_udsp');
commit;
prompt 5 records loaded
prompt Loading RTS_METADATA_COLUMN...
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('e9ec2e96864e4862889c6135048b516c', 'c1b08f3657314c23aa527c54dd955948', 1, 'boroughId', '区域id', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('ea5c89b8808844219876d783ed1580ce', 'c1b08f3657314c23aa527c54dd955948', 2, 'borough', '区域名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('e3f9f513570048ce9dbafbf3ecf78bfe', 'c1b08f3657314c23aa527c54dd955948', 3, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('38c47cff66fc4ed0aeeed5a962f018cb', 'c1b08f3657314c23aa527c54dd955948', 4, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('15f6a1baf1c945faa8547fb3866827a3', 'c1b08f3657314c23aa527c54dd955948', 5, 'statistime', '统计时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b78caf9479064586add6f6ca5b40fe9f', '6afe89b053c14217b5de4b2bb4822bb4', 1, 'jylsh', '交易流水号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('7d72c8e69f00407c889642fec1b3c645', '6afe89b053c14217b5de4b2bb4822bb4', 2, 'jym', '交易码', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('163139b753554391a3c19975b8861fd5', '6afe89b053c14217b5de4b2bb4822bb4', 3, 'jymc', '交易名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('9b2c5373a0dc4c9ebe72c56a660cadee', '6afe89b053c14217b5de4b2bb4822bb4', 4, 'zcbz', '支出标志', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('2e7ccab5b33d4c898b8e2d4f1acd8c36', '6afe89b053c14217b5de4b2bb4822bb4', 5, 'jgm', '机构码', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('12b949c8e96740d7b777b7eb86a7fc73', '6afe89b053c14217b5de4b2bb4822bb4', 6, 'jgmc', '机构名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('7b38812d62034e4ea6c521ff7980c34a', '6afe89b053c14217b5de4b2bb4822bb4', 7, 'boroughId', '区域id', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('5fd1723401214f3197c9fb7ed5de1f41', '6afe89b053c14217b5de4b2bb4822bb4', 8, 'borough', '区域名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('2d0f0081b82c4e81a2080ef8a4c07ddb', '6afe89b053c14217b5de4b2bb4822bb4', 9, 'khh', '客户号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('90f9c217652949928b3220d9e8cbcafc', '6afe89b053c14217b5de4b2bb4822bb4', 10, 'zhkh', '账号卡号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b7473d1f66494409ae708cc7653770bf', '6afe89b053c14217b5de4b2bb4822bb4', 11, 'zhxh', '账号序号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('bf1e1e210db442f5ad835c61c6e32cab', '6afe89b053c14217b5de4b2bb4822bb4', 12, 'khmc', '客户名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('ad5e2f28bebb48fcb1ec91eba6a0b005', '6afe89b053c14217b5de4b2bb4822bb4', 13, 'dfzh', '对方账号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('95d3320c03b44b73a29ceba8934aa902', '6afe89b053c14217b5de4b2bb4822bb4', 14, 'dfxh', '对方序号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b6ed4717c2e54720ae34df302789e069', '6afe89b053c14217b5de4b2bb4822bb4', 15, 'dfhm', '对方名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('43ca4265de55447daf55ae3947f78ddc', '6afe89b053c14217b5de4b2bb4822bb4', 16, 'jyje', '交易金额', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('2d5cecaec3f14bd19064eb95ce2d1f8b', '6afe89b053c14217b5de4b2bb4822bb4', 17, 'jyrq', '交易日期', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b6834cb6740c492cbcb7d5ec4c73491d', '6afe89b053c14217b5de4b2bb4822bb4', 19, 'zy', '摘要', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('1123rcaec3f14bd19064eb95ce2d1f8b', '6afe89b053c14217b5de4b2bb4822bb4', 18, 'jysj', '交易时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('75f819e1aa7b449f91fe6a176eba364d', '020f4314855a434482867b0243a1a849', 1, 'jym', '交易码', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('02a8249bfc4c405db2b5fb9c795a106b', '020f4314855a434482867b0243a1a849', 2, 'jymc', '交易名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('6da12ab9ddd44b4fa99e6f6149bad745', '020f4314855a434482867b0243a1a849', 3, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('f7dff4ee91d640aa8affdc8cb14cdda1', '020f4314855a434482867b0243a1a849', 4, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('4ed4026ca48f4457b5181e418a664746', '020f4314855a434482867b0243a1a849', 5, 'statistime', '统计时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('ea3f80f5602d49478e9d4549f21e123b', '66bde8beaf534c82b71bc91dce7d2102', 1, 'branchId', '网点id', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('cf51f5e80d4c4cf6ab1d34df40553e53', '66bde8beaf534c82b71bc91dce7d2102', 2, 'branch', '网点名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('8051864ca2cd4eaf8e687286c72fa2a1', '66bde8beaf534c82b71bc91dce7d2102', 3, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('59f584e4f1294e5dabeb1f3675256c28', '66bde8beaf534c82b71bc91dce7d2102', 4, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b811847a188446e1900854781d18f594', '66bde8beaf534c82b71bc91dce7d2102', 5, 'statistime', '统计时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b047436bf64045be82d4bf114cfd851c', '3a3dad3c5d0542f4be9f17141bd9a4b3', 1, 'statisDate', '统计日期', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('0f13c78d65c54714b24686f5bc98a32e', '3a3dad3c5d0542f4be9f17141bd9a4b3', 2, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('817e48a704df42c89302cae5fef6a5c2', '3a3dad3c5d0542f4be9f17141bd9a4b3', 3, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('44c0867405f14c248ccdce6524b02713', '3a3dad3c5d0542f4be9f17141bd9a4b3', 4, 'statistime', '统计时间', 'STRING');
commit;
prompt 38 records loaded
prompt Loading RTS_PRODUCRER_CONFIG...
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('8f8a9c78d75943d9b62374fa12a3e879', '66bde8beaf534c82b71bc91dce7d2102', 'rts_jyls_branch_producer', '实时交易流水-按网点统计-生产者', null, '0', 'admin', '2017-08-17 15:59:52.284', 'admin', '2017-08-17 16:14:04.440');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('82f6a904cd6546c3b1c1cd6bdcf6214b', '020f4314855a434482867b0243a1a849', 'rts_jyls_jylx_producer', '实时交易流水-按交易类型统计-生产者', null, '0', 'admin', '2017-08-17 16:01:50.112', 'admin', '2017-08-17 16:13:56.052');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('2e3b08b5009348f6a1511334710d46c1', '3a3dad3c5d0542f4be9f17141bd9a4b3', 'rts_jyls_total_producer', '实时交易流水-按总量统计-生产者', null, '0', 'admin', '2017-08-17 16:02:44.151', 'admin', '2017-08-17 16:13:47.471');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('3275cbea78534218b8e5495a77e285e4', '6afe89b053c14217b5de4b2bb4822bb4', 'rts_jyls_data', 'rts_jyls_data', null, '0', 'admin', '2017-08-17 09:26:43.050', 'admin', '2017-08-17 09:26:43.050');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'c1b08f3657314c23aa527c54dd955948', 'rts_jyls_borough_producer', '实时交易流水队列-按区域统计', '实时交易流水队列-按区域统计', '0', 'admin', '2017-08-17 16:03:39.510', 'admin', '2017-08-17 16:13:38.471');
commit;
prompt 5 records loaded
prompt Loading T_GF_APPLICATION...
insert into T_GF_APPLICATION (app_code, app_name, app_comment, app_status)
values ('default', '系统管理平台', '系统管理平台', 1);
commit;
prompt 1 records loaded
prompt Loading T_GF_AUTH_RIGHT...
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('11', 'USER', '20170523', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('21', 'USER', '20170315', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('31', 'USER', '2017100801', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('41', 'USER', '2017032101', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('51', 'USER', '2017032102', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('1011', 'ADMIN', 'tomnic', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('1', 'ADMIN', 'admin', 'role', 'default');
commit;
prompt 7 records loaded
prompt Loading T_GF_DICT...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000001', '权限不足', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000003', '并发数量达到上限', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000005', '参数解析失败', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000008', '没有授权服务', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000009', '必输参数为空', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000099', '其他错误', null, 99, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200001', '模型接口无响应', null, 13, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000011', '当前消费id不存在', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '100', '100ms', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'MYSQL', 'MYSQL', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'DB2', 'DB2', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'STRING', 'STRING', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'DECIMAL', 'DECIMAL', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'INT', 'INT', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'SMALLINT', 'SMALLINT', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'FLOAT', 'FLOAT', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'TIMESTAMP', 'TIMESTAMP', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.port', 'redis连接端口号', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'SOLR', 'SOLR', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'SOLR_HBASE', 'SOLR_HBASE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.connection.timeout.ms', '户端连接zookeeper的最大超时时间', null, 3, null, null, 'default', '6000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'HBASE', 'com.hex.bigdata.udsp.iq.provider.impl.HBaseProvider', null, 2, null, null, 'default', '交互查询的HBase接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.sync.time.ms', 'zookeeper同步时间', null, 4, null, null, 'default', '2000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'consumer.timeout.ms', '消费者超时时间', null, 5, null, null, 'default', '-1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'auto.commit.enable', '如果true,consumer定期地往zookeeper写入每个分区的offset', null, 6, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'auto.commit.interval.ms', '消费者向zookeeper发送offset的时间', null, 7, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'rebalance.retries.max', 'rebalance时的最大尝试次数', null, 8, null, null, 'default', '10');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '==', '等于', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', 'like', '模糊匹配', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '>', '大于', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '<', '小于', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '>=', '大于等于', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '<=', '小于等于', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '!=', '不等于', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'IMPALA', 'IMPALA', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'SOLR_HBASE', 'com.hex.bigdata.udsp.iq.provider.impl.SolrHBaseProvider', null, 3, null, null, 'default', '交互查询的Solr+HBase接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'HBASE', 'HBASE', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_DS_TYPE', 'KAFKA', 'KAFKA', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_TYPE', '1', '查询字段', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_DS_PROPS_KAFKA', 'metadata.broker.list', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_IMPL_CLASS', 'KAFKA', 'com.hex.bigdata.udsp.rts.provider.impl.KafkaProvider', null, 1, null, null, 'default', '实时流的KAFKA接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_CARDTYPE', '1', '身份证', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_CARDTYPE', '2', '军官证', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_EMPSTATUS', '1', '正常', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_EMPSTATUS', '2', '离职', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_GENDER', '1', '男', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_GENDER', '2', '女', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_GENDER', '3', '未知', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '1', '总公司', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '2', '总公司部门', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '3', '分公司', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '4', '分公司部门', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_USERSTATUS', '1', '启用', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_USERSTATUS', '2 ', '停用', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_YESORNO', '1', '是', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_YESORNO', '2', '否', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('EXPE_DIR', '1', '正向指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('EXPE_DIR', '2', '反向指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'C', '客户', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'F', '财务', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'L', '创新发展', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'P', '内部运营', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('KF_LEVEL', '1', '一级指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('KF_LEVEL', '2', '二级指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('NUM_PROP', '1', '时期值', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('NUM_PROP', '2', '时点值', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('SCHEDULE_TYPE', 'quartz', 'QUARTZ', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('SCHEDULE_TYPE', 'cron4j', 'CRON4J', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('APP_SERVER', 'serverA', 'A服务器', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('APP_SERVER', 'serverB', 'B服务器', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('LOGIN_CONF', 'USE_USERSESSION', '0', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_TYPE', '2', '返回字段', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'SOLR', 'com.hex.bigdata.udsp.iq.provider.impl.SolrProvider', null, 1, null, null, 'default', '交互查询的Solr接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'none', 'none', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'count', 'count', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'sum', 'sum', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'avg', 'avg', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'max', 'max', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'min', 'min', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'concat', 'concat', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'key.serializer.class', 'key对象的serializer类', null, 3, null, null, 'default', 'kafka.serializer.StringEncoder');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'request.required.acks', '请求确认模式', null, 4, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.session.timeout.ms', '连接zookeeper的session超时时间', null, 2, null, null, 'default', '5000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'rebalance.backoff.ms', '平衡补偿重试间隔时间', null, 9, null, null, 'default', '2000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'auto.offset.reset', 'offset初始化或者达到上线时的处理方式', null, 10, null, null, 'default', 'largest');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.idle', 'redis连接最大空闲数', null, 5, null, null, 'default', '10000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.timeOut', 'redis连接最大超时时间', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.total', 'redis连接池最大连接数', null, 8, null, null, 'default', '20000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'serializer.class', '序列化类', null, 2, null, null, 'default', 'kafka.serializer.StringEncoder');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'max.data.size', '最大返回数', null, 7, null, null, 'default', '65535');
commit;
prompt 100 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'metadata.broker.list', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.seprator', '结果数据分隔符', null, 10, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.max.data.size', '最大返回数', null, 3, null, null, 'default', '65536');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.fq.dsv.seprator', '结果数据分隔符，如：|、||、\\007、\\t、\\036', null, 8, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR', 'solr.max.data.size', '最大返回数', null, 2, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.connect', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.data.size', 'redis查询返回数据最大条数', null, 11, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', 'in', 'in查询', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'VARCHAR', 'VARCHAR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'STRING', 'STRING', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'DECIMAL', 'DECIMAL', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'CHAR', 'CHAR', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'INT', 'INT', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'BIGINT', 'BIGINT', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'SMALLINT', 'SMALLINT', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'BOOLEAN', 'BOOLEAN', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'DOUBLE', 'DOUBLE', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'FLOAT', 'FLOAT', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'TINYINT', 'TINYINT', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'TIMESTAMP', 'TIMESTAMP', null, 12, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'HIVE', 'HIVE', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_ORDER_COL_TYPE', 'ASC', 'ASC', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_ORDER_COL_TYPE', 'DESC', 'DESC', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'IQ', '交互查询', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'OLQ', '联机查询', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'MM', '模型管理', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'RTS_CONSUMER', '实时流-消费者', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'RTS_PRODUCER', '实时流-生产者', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_CONSUME_LOG_STATUS', '0', '成功', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_CONSUME_LOG_STATUS', '1', '失败', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'MYSQL', 'com.hex.bigdata.udsp.olq.provider.impl.MysqlProvider', null, 4, null, null, 'default', '联机查询的mysql接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'DB2', 'com.hex.bigdata.udsp.olq.provider.impl.Db2Provider', null, 6, null, null, 'default', '联机查询的db2接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '1', '添加', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '2', '更新', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '3', '删除', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '4', '查询', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'username', 'Impala 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'IMPALA', 'com.hex.bigdata.udsp.olq.provider.impl.ImpalaProvider', null, 1, null, null, 'default', '联机查询的Impala接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'HIVE', 'com.hex.bigdata.udsp.olq.provider.impl.HiveProvider', null, 2, null, null, 'default', '联机查询的Hive接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '2', '已发布', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '0', '待上传', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'driver.class', 'Hive 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'username', 'Hive 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_REQUEST_TYPE', '1', '外部请求', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SYNC_TYPE', 'ASYNC', '异步', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '200', '200ms', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
commit;
prompt 200 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'HIVE', 'HIVE', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'SOLR', 'SOLR', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'HBASE', 'HBASE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'KAFKA', 'KAFKA', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_STATUS', '2', '已建', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'driver.class', 'Hive 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'username', 'Hive 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'jdbc.url', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/default', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'password', 'Hive 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.max.data.size', '最大返回数', null, 3, null, null, 'default', '65536');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.fq.dsv.seprator', '结果数据分隔符，如：|、||、\\007、\\t、\\036', null, 8, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 7, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.family.name', 'hbase族名', null, 5, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.qulifier.name', 'hbase列名', null, 6, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR', 'solr.max.data.size', '最大返回数', null, 2, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'max.data.size', '最大返回数', null, 7, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.family.name', 'hbase列族名字', null, 4, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.qulifier.name', 'hbase列名', null, 5, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.fqSep', '字段分隔符', null, 6, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'metadata.broker.list', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'SOLR_HBASE', 'SOLR_HBASE', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_STATUS', '1', '未建', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_TYPE', '1', '内表', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_TYPE', '2', '外表', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'password', 'Impala 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_REQUEST_TYPE', '0', '内部请求', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000002', '用户名密码错误', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '1000', '1000ms', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'PGSQL', 'com.hex.bigdata.udsp.olq.provider.impl.PgsqlProvider', null, 5, null, null, 'default', '联机查询的pgsql接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000004', '没有注册服务', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000006', '请求IP不在允许的IP段内', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000007', '程序内部异常', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000010', '调用类型或者ENTITY设置错误', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200002', '模型不支持该接口调用类型', null, 14, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000012', '查询消费状态过于频繁', null, 12, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '2000', '2000ms', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'ORACLE', 'com.hex.bigdata.udsp.olq.provider.impl.OracleProvider', null, 3, null, null, 'default', '联机查询的oracle接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'driver.class', 'pgsql 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'ORACLE', 'ORACLE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'PGSQL', 'PGSQL', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'KYLIN', 'KYLIN', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'KYLIN', 'com.hex.bigdata.udsp.olq.provider.impl.KylinProvider', null, 7, null, null, 'default', '联机查询的kylin接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'jdbc.url', 'pgsql JDBC URL，如：jdbc:postgresql://${ip}:${port}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'password', 'pgsql 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'username', 'pgsql 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'driver.class', 'db2 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'jdbc.url', 'db2 JDBC URL，如:jdbc:db2://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'password', 'db2 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'username', 'db2 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'driver.class', 'kylin 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'jdbc.url', 'kylin JDBC URL，如：' || chr(9) || ' jdbc:kylin://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'password', 'kylin 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'username', 'kylin 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'driver.class', 'mysql 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'jdbc.url', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'password', 'mysql 密码', null, 4, null, null, 'default', null);
commit;
prompt 300 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'username', 'mysql 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'driver.class', 'oracle 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'jdbc.url', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'password', 'oracle 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'username', 'oracle 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'REDIS', 'REDIS', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'REDIS', 'com.hex.bigdata.udsp.iq.provider.impl.RedisProvider', null, 4, null, null, 'default', '交互查询redis接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.ip', 'redis连接ip地址', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.password', 'redis连接密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.wait', 'redis连接最长等待时间', null, 7, null, null, 'default', '1000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.test.on.brrow', 'redis连接是否检查连通性', null, 9, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.user', 'redis连接用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'VARCHAR', 'VARCHAR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'CHAR', 'CHAR', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'BIGINT', 'BIGINT', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'BOOLEAN', 'BOOLEAN', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'TINYINT', 'TINYINT', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 7, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'VARCHAR', 'VARCHAR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'CHAR', 'CHAR', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'BIGINT', 'BIGINT', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'BOOLEAN', 'BOOLEAN', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'TINYINT', 'TINYINT', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'STRING', 'STRING', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'DECIMAL', 'DECIMAL', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'INT', 'INT', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'SMALLINT', 'SMALLINT', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'FLOAT', 'FLOAT', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'TIMESTAMP', 'TIMESTAMP', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'driver.class', 'Impala 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'jdbc.url', 'Impala JDBC URL，如：无密码jdbc:hive2://${ip}:${port}/;auth=noSasl  有密码jdbc:hive2://${ip}:${port}/', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_REQUEST_TYPE', '1', 'HTTP', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '500', '500ms', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', 'right like', 'like右查询', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.family.name', 'hbase族名', null, 5, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.family.name', 'hbase列族名字', null, 4, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.qulifier.name', 'hbase列名', null, 6, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.qulifier.name', 'hbase列名', null, 5, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.fqSep', '字段分隔符', null, 6, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'OLQ_APP', '联机查询应用', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '1', '待发布', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '3', '归档', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'jdbc.url', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/default', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'password', 'Hive 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SYNC_TYPE', 'SYNC', '同步', null, 1, null, null, 'default', null);
commit;
prompt 353 records loaded
prompt Loading T_GF_DICT_TYPE...
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_SOLR_HBASE', '交互建模-数据源配置-SOLR_HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_KAFKA', '交互建模-数据源配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_TYPE', '交互建模-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_TYPE', '交互建模-元数据类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_STATUS', '交互建模-元数据状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_IMPALA', '联机查询-数据源配置-IMPALA参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_CONSUME_ERROR_CODE', 'UDSP消费接口错误编码', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_CONSUMER_TIMEOUT', '消费者消费超时时间', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MM_REQUEST_TYPE', '模型应用程序请求类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OlQ_APP_COL_DATA_TYPE', '联机查应用数据列类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_TYPE', '交互查询-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_HBASE', '交互查询-数据源配置-HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_SOLR', '交互查询-数据源配置-SOLR', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_REDIS', '交互查询redis查询', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_SOLR_HBASE', '交互查询-数据源配置-SOLR_HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_ORDER_COL_TYPE', '交互查询-应用配置-排序字段-排序类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_DS_TYPE', '实时流-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_MD_COL_TYPE', '交互查询-元数据配置-字段信息-所属类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_CONSUMER_PROPS_KAFKA', '实时流-消费者配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_IMPL_CLASS', '实时流-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('COF_CARDTYPE', '证件类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('COF_EMPSTATUS', '入职状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('SCHEDULE_TYPE', '调度类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('APP_SERVER', '应用服务器', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('DICT_SERVER', '字典服务', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('LOGIN_CONF', '登录配置', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_QUERY_COL_OPERATOR', '交互查询-应用配置-查询字段-操作符', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_RETURN_COL_STATS', '交互查询-应用配置-返回字段-统计函数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_IMPL_CLASS', '交互查询-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_DS_PROPS_KAFKA', '实时流-数据源配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_PRODUCER_PROPS_KAFKA', '实时流-生产者配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_MD_COL_DATA_TYPE', '交互查询-元数据配置-字段数据类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_TYPE', '联机查询-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MC_CONSUME_LOG_STATUS', '监控中心-消费日志-结果状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MC_OPERATION_LOG_TYPE', '监控中心-操作日志-操作类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MM_MODEL_STATUS', '模型状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_HIVE', '联机查询-数据源配置-HIVE参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_SYNC_TYPE', '同步/异步', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_SERVICE_TYPE', '应用类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_IMPL_CLASS', '联机查询-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_REQUEST_TYPE', '请求类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_ORACLE', '联机查询oracle数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_MYSQL', '联机查询mysql数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_KYLIN', '联机查询kylin数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_DB2', '联机查询db2数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_PGSQL', '联机查询pgsql数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_COMMON_DATA_TYPE', 'UDSP数据类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_HIVE', '交互建模-数据源配置-HIVE参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_HBASE', '交互建模-数据源配置-HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_SOLR', '交互建模-数据源配置-SOLR', 'default');
commit;
prompt 50 records loaded
prompt Loading T_GF_EMPLOYEE...
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7011', '2016010', 'rtsmap测试用户', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('17-08-2017 09:29:53', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7021', '2016010', 'rtsmap测试用户', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('17-08-2017 09:29:56', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7031', '2016010', 'rtsmap测试用户', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('17-08-2017 09:30:07', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1031', '2017032102', '王吉', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('21-03-2017 09:20:44', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1`', '0002', 'core', 1, '1991-11-22', '1', null, '1', '2014-11-17', null, '15905696810', '15905696810', null, null, null, 'core@groupwith.com', to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, 'core@groupwith.com', null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('2011', '10705055', '王应利', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('23-05-2017 11:00:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4011', '1111', 'IFE', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:12:18', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', '综合前端系统', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4021', '111', 'CRM', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:15:28', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4031', '1111', 'MP', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:16:32', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', '移动工作站', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4041', '1111', 'SMS', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:18:53', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', '短信系统', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('5011', '00001', '核心系统', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('04-07-2017 09:34:51', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1', '00001', 'admin', 1, '2014-11-22', '1', null, '1', '2014-11-17', null, '111', '111111', null, null, null, 'admin@grouwith.com', to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, 'admin@grouwith.com', null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1021', '2017032101', '王大明', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('21-03-2017 09:20:05', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('6011', 'S07', '村镇银行', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('11-07-2017 15:42:14', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('3011', '00000011', 'test', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('31-05-2017 19:18:35', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('3021', 'test1', 'test1', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('31-05-2017 19:20:51', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
commit;
prompt 16 records loaded
prompt Loading T_GF_FUNCATION...
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('17011', 'IM.cm.ds.list.add', '交互建模>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('17021', 'IM.cm.ds.list.edit', '交互建模>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('17031', 'IM.cm.ds.list.remove', '交互建模>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18011', 'IM.cm.md.list.add', '交互建模>元数据配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18021', 'IM.cm.md.list.edit', '交互建模>元数据配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18031', 'IM.cm.md.list.remove', '交互建模>元数据配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('8011', 'RC.service.list.add', '注册中心>用户注册>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('8021', 'RC.service.list.remove', '注册中心>服务注册>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('8031', 'RC.service.list.edit', '注册中心>服务注册>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('10011', 'MM.model.list.add', '模型管理>模型配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('10021', 'MM.model.list.edit', '模型管理>模型配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('10031', 'MM.model.list.remove', '模型管理>模型配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('11011', 'MM.app.list.add', '模型管理>应用配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('11021', 'MM.app.list.edit', '模型管理>应用配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('11031', 'MM.app.list.remove', '模型管理>应用配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14131', 'RC.service.list.auth', '注册中心>服务注册>授权', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('16011', 'OLQ.qm.testapp.search', '联机查询>OLQ应用测试>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('16021', 'OLQ.qm.testapp.download', '联机查询>OLQ应用测试>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('9011', 'RC.userService.list.add', '注册中心>用户注册>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('9021', 'RC.userService.list.edit', '注册中心>用户注册>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('9031', 'RC.userService.listremove', '注册中心>用户注册>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13011', 'IQ.cm.md.list.add', '交互查询>配置管理>元数据配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13021', 'IQ.cm.md.list.edit', '交互查询>配置管理>元数据配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13031', 'IQ.cm.md.list.remove', '交互查询>配置管理>元数据配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13041', 'IQ.cm.app.list.add', '交互查询>配置管理>应用配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13051', 'IQ.cm.app.list.edit', '交互查询>配置管理>应用配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13061', 'IQ.cm.app.list.remove', '交互查询>配置管理>应用配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('12011', 'MM.contractor.list.add', '模型管理>厂商管理>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('12021', 'MM.contractor.list.edit', '模型管理>厂商管理>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('12031', 'MM.contractor.list.remove', '模型管理>厂商管理>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14011', 'IQ.qm.test.search', '交互查询>应用测试>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14141', 'RTS.qm.producer.test', '实时流>应用测试>生产者测试', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14031', 'IQ.qm.test.download', '交互查询>应用测试>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14041', 'IQ.qm.app.search', '交互查询>应用实例>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14151', 'RTS.qm.consumer.test', '实时流>应用测试>消费者测试', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14061', 'IQ.qm.app.download', '交互查询>应用实例>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14071', 'OLQ.qm.test.search', '联机查询>应用测试>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14091', 'OLQ.qm.test.download', '联机查询>应用测试>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14101', 'OLQ.qm.app.search', '联机查询>应用实例>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14121', 'OLQ.qm.app.download', '联机查询>应用实例>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('1011', 'RTS.cm.ds.list.add', '实时流>配置管理>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('1021', 'RTS.cm.ds.list.edit', '实时流>配置管理>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('1031', 'RTS.cm.ds.list.remove', '实时流>配置管理>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5011', 'RTS.cm.producer.list.add', '实时流>配置管理>生产者配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5021', 'RTS.cm.producer.list.edit', '实时流>配置管理>生产者配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5031', 'RTS.cm.producer.list.remove', '实时流>配置管理>生产者配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5041', 'RTS.cm.consumer.list.add', '实时流>配置管理>消费配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5051', 'RTS.cm.consumer.list.edit', '实时流>配置管理>消费者配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5061', 'RTS.cm.consumer.list.remove', '实时流>配置管理>消费者配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('51', 'IQ.cm.ds.list.remove', '交互查询>配置管理>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('4021', 'RTS.cm.md.list.add', '实时流>配置管理>元数据配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('31', 'IQ.cm.ds.list.add', '交互查询>配置管理>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('41', 'IQ.cm.ds.list.edit', '交互查询>配置管理>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('4031', 'RTS.cm.md.list.edit', '实时流>配置管理>元数据配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('4041', 'RTS.cm.md.list.remove', '实时流>配置管理>元数据配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('7011', 'OLQ.cm.ds.list.add', '联机查询>配置管理>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('7021', 'OLQ.cm.ds.list.edit', '联机查询>配置管理>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('7031', 'OLQ.cm.ds.list.remove', '联机查询>配置管理>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14161', 'MM.qm.test.search', '模型管理>模型测试>执行', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('15011', 'OLQ.cm.app.list.add', '联机查询>应用配置>新增', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('15021', 'OLQ.cm.app.list.edit', '联机查询>应用配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('15031', 'OLQ.cm.app.list.remove', '联机查询>应用配置>删除', null, null, null, null, 'default');
commit;
prompt 62 records loaded
prompt Loading T_GF_LOG...
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('1762332ef6e3452491c599e557a55fca', '登录', null, 'admin', null, 'default', to_date('01-08-2017 11:03:04', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('eb2cb49fd4844438b9f1036a3f7db22a', '登录', null, 'admin', null, 'default', to_date('07-08-2017 11:48:30', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('e178b7e590f04e5e9df9e215e5eca726', '登录', null, 'admin', null, 'default', to_date('17-08-2017 08:55:34', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('f7be4c8873d64fa3aae35f821d491012', '登录', null, 'admin', null, 'default', to_date('07-08-2017 14:33:43', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('6a61b16016fa404f8dc97ff12b1afca8', '登录', null, 'admin', null, 'default', to_date('17-08-2017 14:18:25', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('9622ec9cbb4d4cd9a110e3d6e092cc78', '登录', null, 'admin', null, 'default', to_date('17-08-2017 15:27:30', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('d2317b98e8374f1aa646e2c5d4612284', '登录', null, 'admin', null, 'default', to_date('29-08-2017 12:36:08', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('1b9dcf02fadc48e08c07131d9d9df94f', '登录', null, 'admin', null, 'default', to_date('04-09-2017 08:24:38', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('a9c522c6164a41cb93425f3df2fb713f', '登录', null, 'admin', null, 'default', to_date('04-09-2017 08:33:36', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('a7de9cd8582b4497a6943f8264c08dbb', '登录', null, 'admin', null, 'default', to_date('04-09-2017 08:58:04', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('4748d1679b9e40d292353db3989f2880', '登录', null, 'admin', null, 'default', to_date('04-09-2017 09:01:20', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('7ac0cb43ad2e490f8eec14bfbd63f6d6', '登录', null, 'admin', null, 'default', to_date('04-09-2017 11:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('fc05902041d747b0a92e8930bd6a2c93', '登录', null, 'admin', null, 'default', to_date('08-08-2017 11:39:12', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('4a246f6e038145d2af7e68187b961072', '登录', null, 'admin', null, 'default', to_date('31-08-2017 10:18:58', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('6953b0875f4a4e948e0d4b1c8991f7da', '登录', null, 'admin', null, 'default', to_date('31-07-2017 22:06:56', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('257aee25764a4889b29a55c3cf883166', '登录', null, 'admin', null, 'default', to_date('31-07-2017 23:01:41', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('0e26b93a499a46e488a8a522fb7c062b', '登录', null, 'admin', null, 'default', to_date('03-09-2017 18:00:51', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('7d8d425e13e5466c8597806046ca33e4', '登录', null, 'admin', null, 'default', to_date('01-08-2017 01:11:07', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('8067dc96921d4db7987dff697f7d2004', '登录', null, 'admin', null, 'default', to_date('01-08-2017 09:43:44', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('d2a482f20767401a97c43ecec47c011a', '登录', null, 'admin', null, 'default', to_date('02-09-2017 07:56:34', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('8eabec428eb540f9bd1a8df79d111f70', '登录', null, 'admin', null, 'default', to_date('02-09-2017 08:03:22', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('3a3240d016ee4590bf88b500b8524ae2', '登录', null, 'admin', null, 'default', to_date('02-09-2017 10:26:23', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
insert into T_GF_LOG (log_id, action_type, action_url, create_user_id, is_func, app_id, create_date, create_username, log_body)
values ('1f832e745b294aa994a75cf41541b1b7', '登录', null, 'admin', null, 'default', to_date('02-09-2017 17:15:19', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null);
commit;
prompt 23 records loaded
prompt Loading T_GF_LOGINUSER...
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('10011', '7011', 'rtsmap', 'rtsmap测试用户', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('17-08-2017 09:30:13', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5021', '4011', 'IFE', 'IFE', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:14:40', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, '2017-06-08', null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('2011', '2011', 'tomnic', '王应利', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('23-05-2017 11:00:20', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5031', '4021', 'CRM', 'CRM', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:15:28', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5041', '4031', 'MP', 'MP', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:16:32', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5051', '4041', 'SMS', 'SMS', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:18:53', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('1', '1', 'admin', 'admin', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('9011', '6011', 'S07', '村镇银行', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('11-07-2017 15:50:14', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, '2017-07-11', '2019-11-07');
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('7011', '5011', 'S01', '核心系统', '96e79218965eb72c92a549dd5a330112', '1', null, to_date('04-07-2017 10:15:41', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
commit;
prompt 9 records loaded
prompt Loading T_GF_MENU...
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113001', '交互建模', null, 'im.core', '2', null, 110, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113011', 'IM数据源配置', null, 'im.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=IM', '113001', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113021', 'IM元数据配置', null, 'im.cm.md', '1', null, 2, 'default', 'im.cm.md.list?model=IM', '113001', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('109011', '角色授权', null, 'goframe.auth.roleauth', '1', null, 70, 'default', 'goframe.auth.role_auth', '1101', 'fa fa-key');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('107001', '前置银联明细查询应用', null, 'cupatrxjnl_app', '1', null, 2, 'default', 'iq.qm.app?name=soa_cupatrxjnl_solr_hbase_app', '100001', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('110001', 'OLQ应用配置', null, 'olq.application.config', '1', null, 20, 'default', 'olq.cm.app.list', '100081', 'fa fa-list');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('106001', 'MM厂商管理', null, 'mm.contractor.list', '1', null, 1, 'default', 'mm.cm.contractor.list', '100101', 'fa fa-group');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('111011', 'OLQ应用测试', null, 'olq.qm.app', '1', null, 30, 'default', 'olq.qm.olqApps', '100011', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100301', '服务授权', null, 'rc.auth', '1', null, 20, 'default', 'rc.auth.list', '100211', 'fa fa-key');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100311', 'RTS元数据配置', null, 'rts.cm.md', '1', null, 2, 'default', 'rts.cm.md.list?model=RTS', '100121', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('101011', '图表统计', null, 'mc.stats.charts', '1', null, 1, 'default', 'mc.stats.charts', '100251', 'fa fa-bar-chart-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('108001', 'Impala大数据开发环境数据库查询', null, 'tzb_dev_impala', '1', null, 1, 'default', 'olq.qm.app?name=tzb_dev_impala', '100011', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1101', '后台管理', '后台管理', 'goframe.core', '2', null, 1001, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1102', '用户管理', null, 'goframe.user.list', '1', null, 20, 'default', 'goframe.user.list', '1101', 'glyphicon glyphicon-user');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1103', '角色管理', null, 'goframe.role.index', '1', null, 30, 'default', 'goframe.role.index', '1101', 'fa fa-users');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1104', '组织机构', null, 'goframe.org.index', '1', null, 10, 'default', 'goframe.org.tree', '1101', 'fa fa-tree');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1105', '菜单管理', null, 'goframe.menu.manage', '1', null, 40, 'default', 'goframe.menu.manage', '1101', 'glyphicon glyphicon-menu-hamburger');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1107', '数据字典', null, 'goframe.dict.index', '1', null, 50, 'default', 'goframe.dict.index', '1101', 'fa fa-table');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('101001', '任务调度', null, 'goframe.schedule.list', '1', null, 80, 'default', 'goframe.schedule.list', '1101', 'fa fa-tasks');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('101002', '功能管理', null, 'goframe.function.index', '1', null, 60, 'default', 'goframe.function.index', '1101', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100001', '交互查询', null, 'iq.core', '2', null, 101, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100011', '联机查询', null, 'olq.core', '2', null, 201, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100021', '模型管理', null, 'mm.core', '2', null, 301, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100031', '实时流', null, 'rts.core', '2', null, 401, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100041', 'IQ配置管理', null, 'iq.cm', '2', null, 10, 'default', null, '100001', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100051', 'IQ应用测试', null, 'iq.qm', '1', null, 20, 'default', 'iq.qm.test', '100001', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100061', 'IQ数据源配置', null, 'iq.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=IQ', '100041', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100071', 'IQ元数据配置', null, 'iq.cm.md', '1', null, 2, 'default', 'iq.cm.md.list?model=IQ', '100041', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100091', 'OLQ数据源测试', null, 'olq.qm.ds', '1', null, 20, 'default', 'olq.qm.test', '100011', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100111', 'MM应用测试', null, 'mm.qm', '1', null, 20, 'default', 'mm.qm.test', '100021', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100121', 'RTS配置管理', null, 'rts.cm', '2', null, 10, 'default', null, '100031', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100141', 'MM模型配置', null, 'mm.cm.model', '1', null, 2, 'default', 'mm.cm.model.list', '100101', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100161', 'IQ应用配置', null, 'iq.cm.app', '1', null, 3, 'default', 'iq.cm.app.list', '100041', 'fa fa-list');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100171', 'OLQ数据源配置', null, 'olq.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=OLQ', '100081', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100191', 'RTS生产者配置', null, 'rts.cm.producer', '1', null, 3, 'default', 'rts.cm.producer.list', '100121', 'fa fa-sign-in');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100211', '注册中心', null, 'rc.core', '2', null, 501, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100231', '日志监控', null, 'mc.log', '2', null, 10, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100241', '并发监控', null, 'mc.current', '1', null, 20, 'default', 'mc.current.list', '100221', 'fa fa-desktop');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100261', '操作日志', null, 'mc.log.operation', '1', null, 1, 'default', 'mc.log.operation.list', '100231', 'fa fa-file-text-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100291', '服务注册', null, 'rc.service', '1', null, 10, 'default', 'rc.service.list', '100211', 'fa fa-shield');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100081', 'OLQ配置管理', null, 'olq.cm', '2', null, 10, 'default', null, '100011', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100101', 'MM配置管理', null, 'mm.cm', '2', null, 10, 'default', null, '100021', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100131', 'RTS应用测试', null, 'rts.qm', '1', null, 20, 'default', 'rts.qm.test', '100031', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100151', 'MM应用配置', null, 'mm.cm.app', '1', null, 3, 'default', 'mm.cm.app.list', '100101', 'fa fa-list');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100181', 'RTS数据源配置', null, 'rts.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=RTS', '100121', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100201', 'RTS消费者配置', null, 'rts.cm.consumer', '1', null, 4, 'default', 'rts.cm.consumer.list', '100121', 'fa fa-sign-out');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100221', '监控中心', null, 'mc.core', '2', null, 601, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100251', '统计监控', null, 'mc.stats', '2', null, 30, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100271', '消费日志', null, 'mc.log.consume', '1', null, 2, 'default', 'mc.log.consume.list', '100231', 'fa fa-file-text');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('112001', 'Impala联机查询应用', null, 'tzb_olq_app', '1', null, 2, 'default', 'olq.qm.olqApp?name=olq_app3', '100011', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('105001', '存贷比查询应用', null, 'cdb_app', '1', null, 1, 'default', 'iq.qm.app?name=soa_cdb_solr_hbase_app2', '100001', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113031', 'IM模型配置', null, 'im.cm.model', '1', null, 3, 'default', 'im.cm.model.list', '113001', 'fa fa-list');
commit;
prompt 52 records loaded
prompt Loading T_GF_NEXTID...
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFUser', 1101, to_date('17-08-2017 09:30:13', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFMENU', 11400, to_date('02-09-2017 10:30:49', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GF_RES_AUTH', 13900, to_date('04-09-2017 08:39:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('LOG_ID', 197500, to_date('04-09-2017 11:03:19', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFFuncation', 1901, to_date('04-09-2017 08:37:18', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFEMPLOYEE', 801, to_date('17-08-2017 09:29:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFAUTHRIGHT', 201, to_date('23-05-2017 11:00:47', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 7 records loaded
prompt Loading T_GF_ORG...
insert into T_GF_ORG (orgid, orgname, orgcode, org_level, org_seq, org_type, org_address, zipcode, linkman, linktel, create_date, update_date, display_order, org_comment, app_id, parent_orgid)
values ('1', '台州银行', '000001', 1, '.1.', 'undefined', '台州', null, null, null, null, null, null, null, 'default', '0');
commit;
prompt 1 records loaded
prompt Loading T_GF_QUARTZ...
prompt Table is empty
prompt Loading T_GF_RES_AUTH...
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137001', 'ADMIN', 'role', '105001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137011', 'ADMIN', 'role', '107001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137021', 'ADMIN', 'role', '100051', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137031', 'ADMIN', 'role', '100061', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137041', 'ADMIN', 'role', '100071', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137051', 'ADMIN', 'role', '100161', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137061', 'ADMIN', 'role', '113011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137071', 'ADMIN', 'role', '113021', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137081', 'ADMIN', 'role', '113031', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137091', 'ADMIN', 'role', '108001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137101', 'ADMIN', 'role', '112001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137111', 'ADMIN', 'role', '100091', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137121', 'ADMIN', 'role', '111011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137131', 'ADMIN', 'role', '100171', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137141', 'ADMIN', 'role', '110001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137151', 'ADMIN', 'role', '100111', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137161', 'ADMIN', 'role', '106001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137171', 'ADMIN', 'role', '100141', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137181', 'ADMIN', 'role', '100151', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137191', 'ADMIN', 'role', '100131', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137201', 'ADMIN', 'role', '100181', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137211', 'ADMIN', 'role', '100311', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137221', 'ADMIN', 'role', '100191', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137231', 'ADMIN', 'role', '100201', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137241', 'ADMIN', 'role', '100291', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137251', 'ADMIN', 'role', '100301', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137261', 'ADMIN', 'role', '100241', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137271', 'ADMIN', 'role', '100261', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137281', 'ADMIN', 'role', '100271', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137291', 'ADMIN', 'role', '101011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137301', 'ADMIN', 'role', '1104', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137311', 'ADMIN', 'role', '1102', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137321', 'ADMIN', 'role', '1103', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137331', 'ADMIN', 'role', '1105', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137341', 'ADMIN', 'role', '1107', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137351', 'ADMIN', 'role', '101002', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('137361', 'ADMIN', 'role', '109011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138001', 'ADMIN', 'role', 'IM.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138011', 'ADMIN', 'role', 'IM.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138021', 'ADMIN', 'role', 'IM.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138031', 'ADMIN', 'role', 'RC.service.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138041', 'ADMIN', 'role', 'RC.service.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138051', 'ADMIN', 'role', 'RC.service.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138061', 'ADMIN', 'role', 'MM.model.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138071', 'ADMIN', 'role', 'MM.model.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138081', 'ADMIN', 'role', 'MM.model.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138091', 'ADMIN', 'role', 'MM.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138101', 'ADMIN', 'role', 'MM.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138111', 'ADMIN', 'role', 'MM.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138121', 'ADMIN', 'role', 'RC.service.list.auth', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138131', 'ADMIN', 'role', 'OLQ.qm.testapp.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138141', 'ADMIN', 'role', 'OLQ.qm.testapp.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138151', 'ADMIN', 'role', 'RC.userService.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138161', 'ADMIN', 'role', 'RC.userService.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138171', 'ADMIN', 'role', 'RC.userService.listremove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138181', 'ADMIN', 'role', 'IQ.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138191', 'ADMIN', 'role', 'IQ.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138201', 'ADMIN', 'role', 'IQ.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138211', 'ADMIN', 'role', 'IQ.cm.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138221', 'ADMIN', 'role', 'IQ.cm.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138231', 'ADMIN', 'role', 'IQ.cm.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138241', 'ADMIN', 'role', 'MM.contractor.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138251', 'ADMIN', 'role', 'MM.contractor.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138261', 'ADMIN', 'role', 'MM.contractor.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138271', 'ADMIN', 'role', 'IQ.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138281', 'ADMIN', 'role', 'RTS.qm.producer.test', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138291', 'ADMIN', 'role', 'IQ.qm.test.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138301', 'ADMIN', 'role', 'IQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138311', 'ADMIN', 'role', 'RTS.qm.consumer.test', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138321', 'ADMIN', 'role', 'IQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138331', 'ADMIN', 'role', 'OLQ.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138341', 'ADMIN', 'role', 'OLQ.qm.test.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138351', 'ADMIN', 'role', 'OLQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138361', 'ADMIN', 'role', 'OLQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138371', 'ADMIN', 'role', 'RTS.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138381', 'ADMIN', 'role', 'RTS.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138391', 'ADMIN', 'role', 'RTS.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138401', 'ADMIN', 'role', 'RTS.cm.producer.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138411', 'ADMIN', 'role', 'RTS.cm.producer.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138421', 'ADMIN', 'role', 'RTS.cm.producer.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138431', 'ADMIN', 'role', 'RTS.cm.consumer.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138441', 'ADMIN', 'role', 'RTS.cm.consumer.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138451', 'ADMIN', 'role', 'RTS.cm.consumer.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138461', 'ADMIN', 'role', 'IQ.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138471', 'ADMIN', 'role', 'RTS.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138481', 'ADMIN', 'role', 'IQ.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138491', 'ADMIN', 'role', 'IQ.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138501', 'ADMIN', 'role', 'RTS.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138511', 'ADMIN', 'role', 'RTS.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138521', 'ADMIN', 'role', 'OLQ.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138531', 'ADMIN', 'role', 'OLQ.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138541', 'ADMIN', 'role', 'OLQ.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138551', 'ADMIN', 'role', 'MM.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138561', 'ADMIN', 'role', 'OLQ.cm.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138571', 'ADMIN', 'role', 'OLQ.cm.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138581', 'ADMIN', 'role', 'OLQ.cm.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138591', 'ADMIN', 'role', 'IM.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138601', 'ADMIN', 'role', 'IM.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('138611', 'ADMIN', 'role', 'IM.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125001', 'USER', 'role', '105001', 'default', 'menu');
commit;
prompt 100 records committed...
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125011', 'USER', 'role', '107001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125021', 'USER', 'role', '108001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129431', 'USER', 'role', 'IQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129441', 'USER', 'role', 'IQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129451', 'USER', 'role', 'OLQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129461', 'USER', 'role', 'OLQ.qm.app.download', 'default', 'func');
commit;
prompt 106 records loaded
prompt Loading T_GF_ROLE...
insert into T_GF_ROLE (roleid, app_id, rolename, role_desc)
values ('USER', 'default', '普通用户', null);
insert into T_GF_ROLE (roleid, app_id, rolename, role_desc)
values ('ADMIN', 'default', '系统管理员', null);
commit;
prompt 2 records loaded
prompt Loading T_GF_SCHD_JOB...
prompt Table is empty
prompt Loading T_GF_TASK_POOL...
prompt Table is empty
prompt Loading T_GF_USER_SESSION...
insert into T_GF_USER_SESSION (user_id, client_ip, login_time)
values ('admin', '0:0:0:0:0:0:0:1', to_timestamp('04-09-2017 11:03:19.892000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 1 records loaded
set feedback on
set define on
prompt Done.
