
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR', 'solr.security.authentication', 'Solr的安全认证方式', null, 3, null, null, 'default', 'kerberos');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR', 'solr.java.security.krb5.conf', 'Kerberos的krb5.conf配置文件路径，如：/etc/krb5.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR', 'solr.java.security.auth.login.config', 'Java验证和授权服务的配置文件路径，如：/root/jaas.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 5, null, null, 'default', null);


insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'solr.security.authentication', 'Solr的安全认证方式', null, 53, null, null, 'default', 'kerberos');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'solr.java.security.krb5.conf', 'Kerberos的krb5.conf配置文件路径，如：/etc/krb5.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 54, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'solr.java.security.auth.login.config', 'Java验证和授权服务的配置文件路径，如：/root/jaas.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 55, null, null, 'default', null);


insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR', 'solr.security.authentication', 'Solr的安全认证方式', null, 5, null, null, 'default', 'kerberos');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR', 'solr.java.security.krb5.conf', 'Kerberos的krb5.conf配置文件路径，如：/etc/krb5.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR', 'solr.java.security.auth.login.config', 'Java验证和授权服务的配置文件路径，如：/root/jaas.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 7, null, null, 'default', null);


insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'solr.security.authentication', 'Solr的安全认证方式', null, 55, null, null, 'default', 'kerberos');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'solr.java.security.krb5.conf', 'Kerberos的krb5.conf配置文件路径，如：/etc/krb5.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 56, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_PAIR_SOLR_HBASE', 'solr.java.security.auth.login.config', 'Java验证和授权服务的配置文件路径，如：/root/jaas.conf（注：引擎所属集群的YARN NodeManager所在节点都需要有该路径文件）', null, 56, null, null, 'default', null);

update T_GF_DICT set dict_id='solr.zkHost' where dict_type_id='IM_DS_PROPS_SOLR' and dict_id='solr.url';
update T_GF_DICT set dict_id='solr.zkHost' where dict_type_id='IM_DS_PROPS_SOLR_HBASE' and dict_id='solr.url';
update T_GF_DICT set dict_id='active.solr.zkHost' where dict_type_id='IM_DS_PROPS_PAIR_SOLR' and dict_id='active.solr.url';
update T_GF_DICT set dict_id='standby.solr.zkHost' where dict_type_id='IM_DS_PROPS_PAIR_SOLR' and dict_id='standby.solr.url';
update T_GF_DICT set dict_id='active.solr.zkHost' where dict_type_id='IM_DS_PROPS_PAIR_SOLR_HBASE' and dict_id='active.solr.url';
update T_GF_DICT set dict_id='standby.solr.zkHost' where dict_type_id='IM_DS_PROPS_PAIR_SOLR_HBASE' and dict_id='standby.solr.url';

insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.namespace', 'HBase 命名空间', null, 12, null, null, 'default', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_PAIR_SOLR_HBASE', 'hbase.namespace', 'HBase 命名空间', null, 12, null, null, 'default', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_PROPS_SOLR_HBASE', 'hbase.namespace', 'HBase 命名空间', null, 6, null, null, 'default', 'default');

update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_DB2' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_DSL' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_HIVE' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_IMPALA' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_INCEPTOR' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_KYLIN' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_MYSQL' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_ORACLE' and dict_id='remove.abandoned.timeout';
update T_GF_DICT set dict_name='回收没用的连接超时时间，单位秒', filter='180'
where dict_type_id='OLQ_DS_PROPS_PGSQL' and dict_id='remove.abandoned.timeout';
