insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_QUERY_COL_EXPRESSION', '交互查询-应用配置-查询字段-表达式', 'default');

insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_EXPRESSION', '${maxValue}', '最大值', null, 1, null, null, 'default', null);

insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.family.replication.scope', 'HBase的族的复制范围（0：关闭复制，1：开启复制）', null, 9, null, null, 'default', '1');

commit;
