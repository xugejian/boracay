-- 修改实时流-Kafka的实现类路径
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.rts.executor.impl.KafkaExecutor'
where dict_type_id='RTS_IMPL_CLASS' and dict_id='KAFKA';
commit;
