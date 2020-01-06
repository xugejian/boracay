-- 添加IM的数据源类型
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'KUDU', 'KUDU', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'KUDU', 'KUDU', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'KUDU', 'KUDU', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_REALTIME_TYPE', 'KUDU', 'KUDU', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_SOURCE_BATCH_TYPE', 'KUDU', 'KUDU', null, 5, null, null, 'default', null);

-- 添加IM的数据源配置
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_KUDU', '交互建模-数据源配置-KUDU', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KUDU', 'kudu.master.hosts', 'master服务地址（必填，如：10.1.97.1:7051,10.1.97.2:7051）', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KUDU', 'boss.count', 'boss数量（选填，默认为1）', null, 2, null, null, 'default', '1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KUDU', 'default.admin.operation.timeout.ms', '用于管理操作的超时时间（毫秒）（选填，默认为30000）', null, 3, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KUDU', 'default.operation.timeout.ms', '用于用户操作的超时时间（毫秒）（选填，默认为30000）', null, 4, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KUDU', 'default.socket.read.timeout.ms', 'Socket读取的超时时间（毫秒）（选填，默认为10000）', null, 5, null, null, 'default', '10000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KUDU', 'disable.statistics', '禁用客户端的统计数据集（true/false）（选填，默认为false）', null, 6, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KUDU', 'worker.count', '工作线程的最大数量（选填，默认为2*核数）', null, 7, null, null, 'default', null);

-- 添加IM的（目标）元数据配置
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_PROPS_KUDU', '交互建模-元数据参数-KUDU', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_KUDU', 'pre.partitioning', '是否预分桶', null, 1, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_KUDU', 'hash.partitions.buckets', '哈希分桶数（预分区开启时生效，且值必须大于等于2）', null, 2, null, null, 'default', null);

-- 添加IM的（源）模型配置
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_PROPS_KUDU', '交互建模-模型参数-KUDU', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_KUDU', 'kudu.table.name', 'Kudu表名称', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_KUDU', 'violence.query', '是否暴力查询（true/false）', null, 2, null, null, 'default', 'false');

-- 添加IM的接口实现类
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'KUDU', 'com.hex.bigdata.udsp.im.converter.impl.KuduConverter', null, 8, null, null, 'default', '交互建模的Kudu接口实现类');

commit;
