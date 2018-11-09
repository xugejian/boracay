-- 修改实现类路径
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.rts.executor.impl.KafkaExecutor'
where dict_type_id='RTS_IMPL_CLASS' and dict_id='KAFKA';
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.HBaseConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='HBASE';
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.HiveConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='HIVE';
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.KafkaConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='KAFKA';
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.MysqlConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='MYSQL';
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.OracleConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='ORACLE';
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.SolrConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='SOLR';
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.SolrHBaseConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='SOLR_HBASE';
commit;

-- 添加清空运行和等待队列按钮的函数
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18041', 'MC.current.list.empty', '监控中心>队列监控>运行队列>清空队列', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18051', 'MC.wait.list.empty', '监控中心>队列监控>等待队列>清空队列', null, null, null, null, 'default');
commit;