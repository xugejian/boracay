
create table IQ_APPLICATION
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null
)
;
comment on table IQ_APPLICATION
  is '交互查询-应用表';
comment on column IQ_APPLICATION.pk_id
  is '主键';
comment on column IQ_APPLICATION.md_id
  is '元数据ID';
comment on column IQ_APPLICATION.name
  is '名称(唯一、英文)';
comment on column IQ_APPLICATION.describe
  is '说明';
comment on column IQ_APPLICATION.note
  is '备注';
comment on column IQ_APPLICATION.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IQ_APPLICATION.crt_user
  is '创建者';
comment on column IQ_APPLICATION.crt_time
  is '创建时间';
comment on column IQ_APPLICATION.upt_user
  is '更新者';
comment on column IQ_APPLICATION.upt_time
  is '更新时间';
create index IDX_IQ_APP_DELFLG on IQ_APPLICATION (DEL_FLG);
create index IDX_IQ_APP_DELFLG_MDID on IQ_APPLICATION (DEL_FLG, MD_ID);
create index IDX_IQ_APP_DELFLG_NAME on IQ_APPLICATION (DEL_FLG, NAME);
alter table IQ_APPLICATION
  add constraint PK_IQ_APPLICATION primary key (PK_ID);


create table IQ_APPLICATION_ORDER_COLUMN
(
  pk_id      VARCHAR2(32) not null,
  app_id     VARCHAR2(32) not null,
  seq        NUMBER(3) not null,
  name       VARCHAR2(64) not null,
  describe   VARCHAR2(256),
  type       VARCHAR2(32) not null,
  order_type VARCHAR2(32) not null,
  note       VARCHAR2(4000)
)
;
comment on table IQ_APPLICATION_ORDER_COLUMN
  is '交互查询-排序参数';
comment on column IQ_APPLICATION_ORDER_COLUMN.pk_id
  is '主键';
comment on column IQ_APPLICATION_ORDER_COLUMN.app_id
  is '应用ID';
comment on column IQ_APPLICATION_ORDER_COLUMN.seq
  is '序号';
comment on column IQ_APPLICATION_ORDER_COLUMN.name
  is '名称((英文)';
comment on column IQ_APPLICATION_ORDER_COLUMN.describe
  is '说明';
comment on column IQ_APPLICATION_ORDER_COLUMN.type
  is '类型';
comment on column IQ_APPLICATION_ORDER_COLUMN.order_type
  is '排序类型(ASC、DESC)';
comment on column IQ_APPLICATION_ORDER_COLUMN.note
  is '备注';
create index IDX_IQ_APP_ORDER_COL_APPID on IQ_APPLICATION_ORDER_COLUMN (APP_ID);
alter table IQ_APPLICATION_ORDER_COLUMN
  add constraint PK_IQ_APPLICATION_ORDER_COLUMN primary key (PK_ID);


create table IQ_APPLICATION_QUERY_COLUMN
(
  pk_id        VARCHAR2(32) not null,
  app_id       VARCHAR2(32) not null,
  seq          NUMBER(3) not null,
  name         VARCHAR2(64) not null,
  describe     VARCHAR2(256) not null,
  type         VARCHAR2(32),
  length       VARCHAR2(32),
  is_need      CHAR(1) not null,
  default_val  VARCHAR2(64),
  operator     VARCHAR2(32) not null,
  label        VARCHAR2(64),
  is_offer_out CHAR(1) not null,
  note         VARCHAR2(4000)
)
;
comment on table IQ_APPLICATION_QUERY_COLUMN
  is '交互查询-查询参数表';
comment on column IQ_APPLICATION_QUERY_COLUMN.pk_id
  is '主键';
comment on column IQ_APPLICATION_QUERY_COLUMN.app_id
  is '应用ID';
comment on column IQ_APPLICATION_QUERY_COLUMN.seq
  is '序号';
comment on column IQ_APPLICATION_QUERY_COLUMN.name
  is '名称((英文)';
comment on column IQ_APPLICATION_QUERY_COLUMN.describe
  is '说明';
comment on column IQ_APPLICATION_QUERY_COLUMN.type
  is '类型';
comment on column IQ_APPLICATION_QUERY_COLUMN.length
  is '长度';
comment on column IQ_APPLICATION_QUERY_COLUMN.is_need
  is '是否必输（0：是；1：否）';
comment on column IQ_APPLICATION_QUERY_COLUMN.default_val
  is '默认值';
comment on column IQ_APPLICATION_QUERY_COLUMN.operator
  is '操作符';
comment on column IQ_APPLICATION_QUERY_COLUMN.label
  is '别名';
comment on column IQ_APPLICATION_QUERY_COLUMN.is_offer_out
  is '是否开放(0：是；1：否)';
comment on column IQ_APPLICATION_QUERY_COLUMN.note
  is '备注';
create index IDX_IQ_APP_QUERY_COL_APPID on IQ_APPLICATION_QUERY_COLUMN (APP_ID);
alter table IQ_APPLICATION_QUERY_COLUMN
  add constraint PK_IQ_APPLICATION_QUERY_COLUMN primary key (PK_ID);


create table IQ_APPLICATION_RETURN_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  app_id   VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     VARCHAR2(32),
  stats    VARCHAR2(32) not null,
  label    VARCHAR2(64),
  seq      NUMBER(3) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000)
)
;
comment on table IQ_APPLICATION_RETURN_COLUMN
  is '交互查询-返回参数表';
comment on column IQ_APPLICATION_RETURN_COLUMN.pk_id
  is '主键';
comment on column IQ_APPLICATION_RETURN_COLUMN.app_id
  is '应用ID';
comment on column IQ_APPLICATION_RETURN_COLUMN.name
  is '名称((唯一、英文)';
comment on column IQ_APPLICATION_RETURN_COLUMN.describe
  is '说明';
comment on column IQ_APPLICATION_RETURN_COLUMN.type
  is '类型';
comment on column IQ_APPLICATION_RETURN_COLUMN.stats
  is '统计函数';
comment on column IQ_APPLICATION_RETURN_COLUMN.label
  is '别名';
comment on column IQ_APPLICATION_RETURN_COLUMN.seq
  is '序号';
comment on column IQ_APPLICATION_RETURN_COLUMN.length
  is '长度';
comment on column IQ_APPLICATION_RETURN_COLUMN.note
  is '备注';
create index IDX_IQ_APP_RETURN_COL_APPID on IQ_APPLICATION_RETURN_COLUMN (APP_ID);
alter table IQ_APPLICATION_RETURN_COLUMN
  add constraint PK_IQ_APPLICATION_RETURN_COLUM primary key (PK_ID);


create table IQ_METADATA
(
  pk_id    VARCHAR2(32) not null,
  ds_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null,
  tb_name  VARCHAR2(64) not null
)
;
comment on table IQ_METADATA
  is '交互式查询-元数据集';
comment on column IQ_METADATA.pk_id
  is '主键';
comment on column IQ_METADATA.ds_id
  is '数据源ID';
comment on column IQ_METADATA.name
  is '名称/表名((唯一、英文)';
comment on column IQ_METADATA.describe
  is '说明/表说明';
comment on column IQ_METADATA.note
  is '备注';
comment on column IQ_METADATA.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IQ_METADATA.crt_user
  is '创建者';
comment on column IQ_METADATA.crt_time
  is '创建时间';
comment on column IQ_METADATA.upt_user
  is '更新者';
comment on column IQ_METADATA.upt_time
  is '更新时间';
comment on column IQ_METADATA.tb_name
  is '表名';
create index IDX_IQ_MD_DELFLG on IQ_METADATA (DEL_FLG);
create index IDX_IQ_MD_DELFLG_DSID on IQ_METADATA (DEL_FLG, DS_ID);
create index IDX_IQ_MD_DELFLG_NAME on IQ_METADATA (DEL_FLG, NAME);
alter table IQ_METADATA
  add constraint PK_IQ_METADATA primary key (PK_ID);


create table IQ_METADATA_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     CHAR(1) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000),
  col_type VARCHAR2(32)
)
;
comment on table IQ_METADATA_COLUMN
  is '交互查询-元数据字段列表';
comment on column IQ_METADATA_COLUMN.pk_id
  is '主键';
comment on column IQ_METADATA_COLUMN.md_id
  is '元数据ID';
comment on column IQ_METADATA_COLUMN.seq
  is '序号（和类型一起确定唯一）';
comment on column IQ_METADATA_COLUMN.name
  is '名称((唯一、英文)';
comment on column IQ_METADATA_COLUMN.describe
  is '说明';
comment on column IQ_METADATA_COLUMN.type
  is '类型(1、查询字段；2、返回字段)';
comment on column IQ_METADATA_COLUMN.length
  is '长度';
comment on column IQ_METADATA_COLUMN.note
  is '备注';
comment on column IQ_METADATA_COLUMN.col_type
  is '字段类型';
create index IDX_IQ_MD_COL_MDID on IQ_METADATA_COLUMN (MD_ID);
alter table IQ_METADATA_COLUMN
  add constraint PK_IQ_METADATA_COLUMN primary key (PK_ID);

alter table IQ_APPLICATION rename column DESCRIBE to DESCRIPTION;
alter table IQ_APPLICATION_RETURN_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IQ_APPLICATION_ORDER_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IQ_APPLICATION_QUERY_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IQ_METADATA rename column DESCRIBE to DESCRIPTION;
alter table IQ_METADATA_COLUMN rename column DESCRIBE to DESCRIPTION;
