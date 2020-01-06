
create table MM_APPLICATION
(
  pk_id    VARCHAR2(32) not null,
  model_id VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null,
  note     VARCHAR2(4000)
)
;
comment on table MM_APPLICATION
  is '模型管理-应用表';
comment on column MM_APPLICATION.pk_id
  is '主键';
comment on column MM_APPLICATION.model_id
  is '模型ID';
comment on column MM_APPLICATION.name
  is '名称((英文、唯一)';
comment on column MM_APPLICATION.describe
  is '说明';
comment on column MM_APPLICATION.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column MM_APPLICATION.crt_user
  is '创建者';
comment on column MM_APPLICATION.crt_time
  is '创建时间';
comment on column MM_APPLICATION.upt_user
  is '更新者';
comment on column MM_APPLICATION.upt_time
  is '更新时间';
comment on column MM_APPLICATION.note
  is '备注';
create index IDX_MM_APP_DELFLG on MM_APPLICATION (DEL_FLG);
alter table MM_APPLICATION
  add constraint PK_MM_APPLICATION primary key (PK_ID);


create table MM_APPLICATION_EXECUTE_PARAM
(
  pk_id       VARCHAR2(32) not null,
  seq         NUMBER(3) not null,
  name        VARCHAR2(64) not null,
  describe    VARCHAR2(256) not null,
  is_need     CHAR(1) not null,
  default_val VARCHAR2(256),
  app_id      VARCHAR2(32)
)
;
comment on table MM_APPLICATION_EXECUTE_PARAM
  is '模型管理-执行参数表';
comment on column MM_APPLICATION_EXECUTE_PARAM.pk_id
  is '主键';
comment on column MM_APPLICATION_EXECUTE_PARAM.seq
  is '序号';
comment on column MM_APPLICATION_EXECUTE_PARAM.name
  is '名称((英文)';
comment on column MM_APPLICATION_EXECUTE_PARAM.describe
  is '说明';
comment on column MM_APPLICATION_EXECUTE_PARAM.is_need
  is '是否必输（0:是；1：否）';
comment on column MM_APPLICATION_EXECUTE_PARAM.default_val
  is '默认值';
comment on column MM_APPLICATION_EXECUTE_PARAM.app_id
  is '模型应用ID';
create index IDX_MM_APP_EXE_APPID on MM_APPLICATION_EXECUTE_PARAM (APP_ID);
alter table MM_APPLICATION_EXECUTE_PARAM
  add constraint PK_MM_APPLICATION_EXECUTE_PARA primary key (PK_ID);


create table MM_APPLICATION_RETURN_PARAM
(
  pk_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  app_id   VARCHAR2(32)
)
;
comment on table MM_APPLICATION_RETURN_PARAM
  is '模型管理-返回参数表';
comment on column MM_APPLICATION_RETURN_PARAM.pk_id
  is '主键';
comment on column MM_APPLICATION_RETURN_PARAM.seq
  is '序号';
comment on column MM_APPLICATION_RETURN_PARAM.name
  is '名称((英文)';
comment on column MM_APPLICATION_RETURN_PARAM.describe
  is '说明';
comment on column MM_APPLICATION_RETURN_PARAM.app_id
  is '模型应用ID';
create index IDX_MM_APP_RET_APPID on MM_APPLICATION_RETURN_PARAM (APP_ID);
alter table MM_APPLICATION_RETURN_PARAM
  add constraint PK_MM_APPLICATION_RETURN_PARAM primary key (PK_ID);


create table MM_CONTRACTOR
(
  pk_id         VARCHAR2(32) not null,
  name          VARCHAR2(128) not null,
  http_url      VARCHAR2(256) not null,
  principal     VARCHAR2(32) not null,
  mobile        VARCHAR2(32) not null,
  note          VARCHAR2(4000),
  del_flg       CHAR(1) not null,
  crt_user      VARCHAR2(32) not null,
  crt_time      VARCHAR2(32) not null,
  upt_user      VARCHAR2(32) not null,
  upt_time      VARCHAR2(32) not null,
  extend_field1 VARCHAR2(128),
  extend_field2 VARCHAR2(128),
  cn_name       VARCHAR2(128),
  ftp_password  VARCHAR2(60)
)
;
comment on table MM_CONTRACTOR
  is '模型管理-厂商管理';
comment on column MM_CONTRACTOR.pk_id
  is '主键';
comment on column MM_CONTRACTOR.name
  is '英文名称';
comment on column MM_CONTRACTOR.http_url
  is '远程连接';
comment on column MM_CONTRACTOR.principal
  is '负责人';
comment on column MM_CONTRACTOR.mobile
  is '负责电话';
comment on column MM_CONTRACTOR.note
  is '备注';
comment on column MM_CONTRACTOR.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column MM_CONTRACTOR.crt_user
  is '创建者';
comment on column MM_CONTRACTOR.crt_time
  is '创建时间';
comment on column MM_CONTRACTOR.upt_user
  is '更新者';
comment on column MM_CONTRACTOR.upt_time
  is '更新时间';
comment on column MM_CONTRACTOR.extend_field1
  is '预留字段1';
comment on column MM_CONTRACTOR.extend_field2
  is '预留字段2';
comment on column MM_CONTRACTOR.cn_name
  is '中文名称';
comment on column MM_CONTRACTOR.ftp_password
  is 'ftp登陆密码';
create index IDX_MM_CONT_DELFLG on MM_CONTRACTOR (DEL_FLG);
create index IDX_MM_CONT_DELFLG_NAME on MM_CONTRACTOR (DEL_FLG, NAME);
alter table MM_CONTRACTOR
  add constraint MM_CONTRACTOR_ID primary key (PK_ID);


create table MM_MODEL_INFO
(
  pk_id      VARCHAR2(32) not null,
  name       VARCHAR2(64) not null,
  describe   VARCHAR2(256) not null,
  ver_note   VARCHAR2(4000),
  ver_num    VARCHAR2(32),
  status     CHAR(1),
  note       VARCHAR2(4000),
  del_flg    CHAR(1) not null,
  crt_user   VARCHAR2(32) not null,
  crt_time   VARCHAR2(32) not null,
  upt_user   VARCHAR2(32) not null,
  upt_time   VARCHAR2(32) not null,
  contractor VARCHAR2(32) not null,
  model_type VARCHAR2(32) not null
)
;
comment on table MM_MODEL_INFO
  is '模型管理-模型信息表';
comment on column MM_MODEL_INFO.pk_id
  is '主键';
comment on column MM_MODEL_INFO.name
  is '名称((英文、唯一)';
comment on column MM_MODEL_INFO.describe
  is '说明';
comment on column MM_MODEL_INFO.ver_note
  is '版本注释';
comment on column MM_MODEL_INFO.ver_num
  is '版本号';
comment on column MM_MODEL_INFO.status
  is '发布状态(1：待发布；2：已发布；3：归档)';
comment on column MM_MODEL_INFO.note
  is '备注';
comment on column MM_MODEL_INFO.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column MM_MODEL_INFO.crt_user
  is '创建者';
comment on column MM_MODEL_INFO.crt_time
  is '创建时间';
comment on column MM_MODEL_INFO.upt_user
  is '更新者';
comment on column MM_MODEL_INFO.upt_time
  is '更新时间';
comment on column MM_MODEL_INFO.contractor
  is '模型开发厂商ID';
comment on column MM_MODEL_INFO.model_type
  is '1：同步；2：异步；3：批处理，多个用逗号分隔';
create index IDX_MM_MODEL_INFO_DELFLG on MM_MODEL_INFO (DEL_FLG);
create index IDX_MM_MODEL_INFO_DELFLG_CONID on MM_MODEL_INFO (DEL_FLG, CONTRACTOR);
create index IDX_MM_MODEL_INFO_DELFLG_NAME on MM_MODEL_INFO (DEL_FLG, NAME);
alter table MM_MODEL_INFO
  add constraint PK_MM_MODEL_INFO primary key (PK_ID);


create table MM_MODEL_PARAM
(
  pk_id    VARCHAR2(32) not null,
  mm_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     CHAR(1) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000),
  col_type VARCHAR2(32)
)
;
comment on table MM_MODEL_PARAM
  is '模型管理-模型字段列表';
comment on column MM_MODEL_PARAM.pk_id
  is '主键';
comment on column MM_MODEL_PARAM.mm_id
  is '模型ID';
comment on column MM_MODEL_PARAM.seq
  is '序号（和类型一起确定唯一）';
comment on column MM_MODEL_PARAM.name
  is '名称((唯一、英文)';
comment on column MM_MODEL_PARAM.describe
  is '说明';
comment on column MM_MODEL_PARAM.type
  is '类型(1、查询字段；2、返回字段)';
comment on column MM_MODEL_PARAM.length
  is '长度';
comment on column MM_MODEL_PARAM.note
  is '备注';
comment on column MM_MODEL_PARAM.col_type
  is '字段类型';
create index IDX_MM_MODEL_PARAM_MMID on MM_MODEL_PARAM (MM_ID);
alter table MM_MODEL_PARAM
  add constraint PK_MM_MODEL_PARAM primary key (PK_ID);

alter table MM_MODEL_INFO rename column DESCRIBE to DESCRIPTION;
alter table MM_APPLICATION rename column DESCRIBE to DESCRIPTION;
alter table MM_APPLICATION_RETURN_PARAM rename column DESCRIBE to DESCRIPTION;
alter table MM_APPLICATION_EXECUTE_PARAM rename column DESCRIBE to DESCRIPTION;
alter table MM_MODEL_PARAM rename column DESCRIBE to DESCRIPTION;

-- 删除字段
alter table MM_CONTRACTOR drop column EXTEND_FIELD1;
alter table MM_CONTRACTOR drop column EXTEND_FIELD2;
