-- 删除字段
alter table IQ_APPLICATION drop column MAX_NUM;
alter table MM_APPLICATION drop column MAX_NUM;
alter table RTS_CUSTOMER_CONFIG drop column GROUP_ID;

-- 更新IQ字段信息
update T_GF_DICT set dict_id='max.data.size' where dict_type_id='IQ_DS_PROPS_HBASE' and dict_id='hbase.max.data.size';
update T_GF_DICT set dict_id='max.data.size' where dict_type_id='IQ_DS_PROPS_REDIS' and dict_id='redis.max.data.size';
update T_GF_DICT set dict_id='max.data.size' where dict_type_id='IQ_DS_PROPS_SOLR' and dict_id='solr.max.data.size';
update T_GF_DICT set dict_id='max.data.size' where dict_type_id='IQ_DS_PROPS_ELASTICSEARCH' and dict_id='elasticsearch.max.data.size';
commit;

-- 更新IQ的HBASE字典信息
delete T_GF_DICT where dict_type_id='IQ_DS_PROPS_HBASE' and dict_id='hbase.family.name';
delete T_GF_DICT where dict_type_id='IQ_DS_PROPS_HBASE' and dict_id='hbase.qulifier.name';
delete T_GF_DICT where dict_type_id='IQ_DS_PROPS_HBASE' and dict_id='hbase.fq.data.type';
delete T_GF_DICT where dict_type_id='IQ_DS_PROPS_HBASE' and dict_id='hbase.fq.dsv.seprator';

insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_MD_PROPS_HBASE', '交互查询-元数据配置-HBASE', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_HBASE', 'hbase.family.name', 'hbase族名', null, 1, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_HBASE', 'hbase.qualifier.name', 'hbase列名', null, 2, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.separator生效', null, 3, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_HBASE', 'hbase.fq.dsv.separator', '结果数据分隔符，如：|、||、\007、\t、\036', null, 4, null, null, 'default', '\007');
commit;

-- 更新IQ的SOLR+HBASE字典信息
delete T_GF_DICT where dict_type_id='IQ_DS_PROPS_SOLR_HBASE' and dict_id='hbase.family.name';
delete T_GF_DICT where dict_type_id='IQ_DS_PROPS_SOLR_HBASE' and dict_id='hbase.qulifier.name';
delete T_GF_DICT where dict_type_id='IQ_DS_PROPS_SOLR_HBASE' and dict_id='hbase.fqSep';

insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_MD_PROPS_SOLR_HBASE', '交互查询-元数据配置-SOLR+HBASE', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_SOLR_HBASE', 'solr.primary.key', 'solr主键字段名', null, 1, null, null, 'default', 'id');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_SOLR_HBASE', 'hbase.family.name', 'hbase族名', null, 2, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_SOLR_HBASE', 'hbase.qualifier.name', 'hbase列名', null, 3, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_SOLR_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.separator生效', null, 4, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_SOLR_HBASE', 'hbase.fq.dsv.separator', '结果数据分隔符，如：|、||、\007、\t、\036', null, 5, null, null, 'default', '\007');
commit;

-- 更新IM的字典信息
update T_GF_DICT set dict_id='hbase.fq.dsv.separator' where dict_type_id='IM_MD_PROPS_HBASE' and dict_id='hbase.fq.dsv.seprator';
update T_GF_DICT set dict_id='hbase.fq.dsv.separator' where dict_type_id='IM_MD_PROPS_SOLR_HBASE' and dict_id='hbase.fq.dsv.seprator';
commit;

-- 添加RTS_CONSUMER的KAFKA字典信息
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'group.id', '消费者组ID，如：group1', null, 1, null, null, 'default', '');
commit;
