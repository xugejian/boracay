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
