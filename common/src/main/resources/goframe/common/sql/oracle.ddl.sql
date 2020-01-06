
create table COM_DATASOURCE
(
  pk_id      VARCHAR2(32) not null,
  name       VARCHAR2(64) not null,
  describe   VARCHAR2(256) not null,
  type       VARCHAR2(32) not null,
  note       VARCHAR2(4000),
  del_flg    CHAR(1) not null,
  crt_user   VARCHAR2(32) not null,
  crt_time   VARCHAR2(32) not null,
  upt_user   VARCHAR2(32) not null,
  upt_time   VARCHAR2(32) not null,
  impl_class VARCHAR2(256),
  model      VARCHAR2(32) not null
)
;
comment on table COM_DATASOURCE
  is '数据源表';
comment on column COM_DATASOURCE.pk_id
  is '主键';
comment on column COM_DATASOURCE.name
  is '名称((唯一、英文)';
comment on column COM_DATASOURCE.describe
  is '说明';
comment on column COM_DATASOURCE.type
  is '数据源类型';
comment on column COM_DATASOURCE.note
  is '备注';
comment on column COM_DATASOURCE.del_flg
  is '删除标志（0：未删除，1：删除';
comment on column COM_DATASOURCE.crt_user
  is '创建者';
comment on column COM_DATASOURCE.crt_time
  is '创建时间';
comment on column COM_DATASOURCE.upt_user
  is '更新者';
comment on column COM_DATASOURCE.upt_time
  is '更新时间';
comment on column COM_DATASOURCE.impl_class
  is '接口实现类';
comment on column COM_DATASOURCE.model
  is '所属模块（IQ、OLQ、RTS、IM）';
create index IDX_COM_DS_DELFLG on COM_DATASOURCE (DEL_FLG);
create index IDX_COM_DS_DELFLG_MODEL_NAME on COM_DATASOURCE (DEL_FLG, MODEL, NAME);
alter table COM_DATASOURCE
  add constraint PK_COM_DATASOURCE primary key (PK_ID);


create table COM_OPERATION_LOG
(
  pk_id          VARCHAR2(32) not null,
  action_type    CHAR(1) not null,
  action_url     VARCHAR2(256) not null,
  action_user    VARCHAR2(32) not null,
  action_time    VARCHAR2(32) not null,
  action_content VARCHAR2(1024)
)
;
comment on table COM_OPERATION_LOG
  is '操作日志表';
comment on column COM_OPERATION_LOG.pk_id
  is '主键';
comment on column COM_OPERATION_LOG.action_type
  is '操作类型（1添加 2更新 3删除 4查询）';
comment on column COM_OPERATION_LOG.action_url
  is '操作URL';
comment on column COM_OPERATION_LOG.action_user
  is '操作用户';
comment on column COM_OPERATION_LOG.action_time
  is '操作时间';
comment on column COM_OPERATION_LOG.action_content
  is '操作信息';
alter table COM_OPERATION_LOG
  add constraint PK_MC_PROPERTIES_LOG primary key (PK_ID);


create table COM_PROPERTIES
(
  pk_id    VARCHAR2(32) not null,
  fk_id    VARCHAR2(32) not null,
  name     VARCHAR2(256) not null,
  value    VARCHAR2(4000) not null,
  describe VARCHAR2(4000)
)
;
comment on table COM_PROPERTIES
  is '属性信息表';
comment on column COM_PROPERTIES.pk_id
  is '主键';
comment on column COM_PROPERTIES.fk_id
  is '外键ID';
comment on column COM_PROPERTIES.name
  is '名称';
comment on column COM_PROPERTIES.value
  is '数值';
comment on column COM_PROPERTIES.describe
  is '说明';
alter table COM_PROPERTIES
  add constraint PK_PROPERTIES primary key (PK_ID);

alter table COM_DATASOURCE rename column DESCRIBE to DESCRIPTION;
alter table COM_PROPERTIES rename column DESCRIBE to DESCRIPTION;
