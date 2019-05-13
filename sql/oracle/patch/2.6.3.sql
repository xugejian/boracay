insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_QUERY_COL_EXPRESSION', '交互查询-应用配置-查询字段-表达式', 'default');

insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_EXPRESSION', '${maxValue}', '最大值', null, 1, null, null, 'default', null);

commit;
