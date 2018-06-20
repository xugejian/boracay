-- 修改实现类路径
update T_GF_DICT set dict_name='com.hex.bigdata.udsp.im.converter.impl.KuduConverter'
where dict_type_id='IM_IMPL_CLASS' and dict_id='KUDU';