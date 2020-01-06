
create table OLQ_APPLICATION
(
  pk_id     VARCHAR2(32) not null,
  olq_ds_id VARCHAR2(32) not null,
  max_num   NUMBER(10),
  del_flg   CHAR(1) not null,
  crt_user  VARCHAR2(32) not null,
  crt_time  VARCHAR2(32) not null,
  upt_user  VARCHAR2(32) not null,
  upt_time  VARCHAR2(32) not null,
  note      VARCHAR2(4000),
  name      VARCHAR2(128) not null,
  describe  VARCHAR2(128) not null,
  olq_sql   CLOB not null
)
;
comment on column OLQ_APPLICATION.pk_id
  is '主键';
comment on column OLQ_APPLICATION.olq_ds_id
  is '联机查询数据源ID';
comment on column OLQ_APPLICATION.max_num
  is '最大返回数';
comment on column OLQ_APPLICATION.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column OLQ_APPLICATION.crt_user
  is '创建者';
comment on column OLQ_APPLICATION.crt_time
  is '创建时间';
comment on column OLQ_APPLICATION.upt_user
  is '更新者';
comment on column OLQ_APPLICATION.upt_time
  is '更新时间';
comment on column OLQ_APPLICATION.note
  is '备注';
comment on column OLQ_APPLICATION.name
  is '名称';
comment on column OLQ_APPLICATION.describe
  is '说明';
comment on column OLQ_APPLICATION.olq_sql
  is '应用配置的SQL语句';
alter table OLQ_APPLICATION
  add constraint OLQ_APPLICATION_PKID primary key (PK_ID);

create table OLQ_APPLICATION_PARAM
(
  pk_id         VARCHAR2(32) not null,
  param_name    VARCHAR2(128) not null,
  param_desc    VARCHAR2(256) not null,
  default_value VARCHAR2(64),
  is_need       CHAR(1) not null,
  app_id        VARCHAR2(32) not null,
  seq           NUMBER(3)
)
;
comment on column OLQ_APPLICATION_PARAM.pk_id
  is '主键';
comment on column OLQ_APPLICATION_PARAM.param_name
  is '参数名称';
comment on column OLQ_APPLICATION_PARAM.param_desc
  is '参数说明';
comment on column OLQ_APPLICATION_PARAM.default_value
  is '默认值';
comment on column OLQ_APPLICATION_PARAM.is_need
  is '是否必输，0：必输，1：不必输';
comment on column OLQ_APPLICATION_PARAM.app_id
  is '应用ID';
comment on column OLQ_APPLICATION_PARAM.seq
  is '序号';
alter table OLQ_APPLICATION_PARAM
  add constraint OLQ_APPLICATION_PARAM_PKID primary key (PK_ID);

alter table OLQ_APPLICATION rename column DESCRIBE to DESCRIPTION;
