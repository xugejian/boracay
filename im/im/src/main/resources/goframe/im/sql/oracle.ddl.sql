
create table IM_METADATA
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
  tb_name  VARCHAR2(64) not null,
  status   CHAR(1) not null,
  type     CHAR(1) not null
)
;
comment on table IM_METADATA
  is '交互建模-元数据配置';
comment on column IM_METADATA.pk_id
  is '主键';
comment on column IM_METADATA.ds_id
  is '数据源ID';
comment on column IM_METADATA.name
  is '名称/表名((唯一、英文)';
comment on column IM_METADATA.describe
  is '说明/表说明';
comment on column IM_METADATA.note
  is '备注';
comment on column IM_METADATA.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IM_METADATA.crt_user
  is '创建者';
comment on column IM_METADATA.crt_time
  is '创建时间';
comment on column IM_METADATA.upt_user
  is '更新者';
comment on column IM_METADATA.upt_time
  is '更新时间';
comment on column IM_METADATA.tb_name
  is '库表';
comment on column IM_METADATA.status
  is '状态（1：未建，2：已建）';
comment on column IM_METADATA.type
  is '类型（1：内表，2：外表）';
alter table IM_METADATA
  add constraint PK_IM_METADATA primary key (PK_ID);


create table IM_METADATA_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     VARCHAR2(32) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000),
  indexed  CHAR(1) not null,
  primary  CHAR(1) not null,
  stored   CHAR(1) not null
)
;
comment on table IM_METADATA_COLUMN
  is '交互建模-元数据字段';
comment on column IM_METADATA_COLUMN.pk_id
  is '主键';
comment on column IM_METADATA_COLUMN.md_id
  is '元数据ID';
comment on column IM_METADATA_COLUMN.seq
  is '序号';
comment on column IM_METADATA_COLUMN.name
  is '名称（唯一、英文）';
comment on column IM_METADATA_COLUMN.describe
  is '说明';
comment on column IM_METADATA_COLUMN.type
  is '类型';
comment on column IM_METADATA_COLUMN.length
  is '长度';
comment on column IM_METADATA_COLUMN.note
  is '备注';
comment on column IM_METADATA_COLUMN.indexed
  is '索引（0：是；1：否）';
comment on column IM_METADATA_COLUMN.primary
  is '主键（0：是；1：否）';
comment on column IM_METADATA_COLUMN.stored
  is '存储（0：是；1：否）';
alter table IM_METADATA_COLUMN
  add constraint PK_IM_METADATA_COL primary key (PK_ID);


create table IM_MODEL
(
  pk_id       VARCHAR2(32) not null,
  name        VARCHAR2(64) not null,
  describe    VARCHAR2(256) not null,
  s_ds_id     VARCHAR2(32) not null,
  t_md_id     VARCHAR2(32) not null,
  note        VARCHAR2(4000),
  del_flg     CHAR(1) not null,
  crt_user    VARCHAR2(32) not null,
  crt_time    VARCHAR2(32) not null,
  upt_user    VARCHAR2(32) not null,
  upt_time    VARCHAR2(32) not null,
  type        CHAR(1) not null,
  build_mode  CHAR(1),
  update_mode CHAR(1),
  e_ds_id     VARCHAR2(32),
  status      CHAR(1) not null
)
;
comment on table IM_MODEL
  is '交互建模-模型配置';
comment on column IM_MODEL.pk_id
  is '主键';
comment on column IM_MODEL.name
  is '名称';
comment on column IM_MODEL.describe
  is '说明';
comment on column IM_MODEL.s_ds_id
  is '源数据源ID';
comment on column IM_MODEL.t_md_id
  is '目标元数据ID';
comment on column IM_MODEL.note
  is '备注';
comment on column IM_MODEL.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IM_MODEL.crt_user
  is '创建者';
comment on column IM_MODEL.crt_time
  is '创建时间';
comment on column IM_MODEL.upt_user
  is '更新者';
comment on column IM_MODEL.upt_time
  is '更新时间';
comment on column IM_MODEL.type
  is '类型（1：批量 2：实时）';
comment on column IM_MODEL.build_mode
  is '构建策略（1：增量，2：全量）';
comment on column IM_MODEL.update_mode
  is '更新策略（1、匹配更新 2、更新、插入 3、增量插入，默认：2）';
comment on column IM_MODEL.e_ds_id
  is '引擎数据源ID';
comment on column IM_MODEL.status
  is '状态（1：未建，2：已建）';
alter table IM_MODEL
  add constraint PK_IM_MODEL primary key (PK_ID);


create table IM_MODEL_FILTER_COLUMN
(
  pk_id       VARCHAR2(32) not null,
  model_id    VARCHAR2(32) not null,
  seq         NUMBER(3) not null,
  name        VARCHAR2(64) not null,
  describe    VARCHAR2(256) not null,
  type        VARCHAR2(32) not null,
  length      VARCHAR2(32),
  is_need     CHAR(1) not null,
  default_val VARCHAR2(64),
  operator    VARCHAR2(32) not null,
  label       VARCHAR2(64) not null
)
;
comment on table IM_MODEL_FILTER_COLUMN
  is '交互模型-模型过滤字段';
comment on column IM_MODEL_FILTER_COLUMN.pk_id
  is '主键';
comment on column IM_MODEL_FILTER_COLUMN.model_id
  is '模型ID';
comment on column IM_MODEL_FILTER_COLUMN.seq
  is '序号';
comment on column IM_MODEL_FILTER_COLUMN.name
  is '名称((英文)';
comment on column IM_MODEL_FILTER_COLUMN.describe
  is '说明';
comment on column IM_MODEL_FILTER_COLUMN.type
  is '类型';
comment on column IM_MODEL_FILTER_COLUMN.length
  is '长度';
comment on column IM_MODEL_FILTER_COLUMN.is_need
  is '是否必输（0：是；1：否）';
comment on column IM_MODEL_FILTER_COLUMN.default_val
  is '默认值';
comment on column IM_MODEL_FILTER_COLUMN.operator
  is '操作符';
comment on column IM_MODEL_FILTER_COLUMN.label
  is '别名';
alter table IM_MODEL_FILTER_COLUMN
  add constraint PK_IM_MODEL_FILTER_COL primary key (PK_ID);


create table IM_MODEL_MAPPING
(
  pk_id    VARCHAR2(32) not null,
  model_id VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  col_id   VARCHAR2(32) not null,
  note     VARCHAR2(4000),
  type     VARCHAR2(32) not null,
  length   VARCHAR2(32),
  describe VARCHAR2(256) not null,
  indexed  CHAR(1) default 1 not null,
  primary  CHAR(1) default 1 not null,
  stored   CHAR(1) default 1 not null
)
;
comment on table IM_MODEL_MAPPING
  is '交互建模-模型字段映射';
comment on column IM_MODEL_MAPPING.pk_id
  is '主键';
comment on column IM_MODEL_MAPPING.model_id
  is '模型ID';
comment on column IM_MODEL_MAPPING.seq
  is '序号';
comment on column IM_MODEL_MAPPING.name
  is '源元数据字段名称';
comment on column IM_MODEL_MAPPING.col_id
  is '目标元数据字段ID';
comment on column IM_MODEL_MAPPING.note
  is '备注';
comment on column IM_MODEL_MAPPING.type
  is '源元数据字段类型';
comment on column IM_MODEL_MAPPING.length
  is '源元数据字段长度';
comment on column IM_MODEL_MAPPING.describe
  is '源元数据字段说明';
comment on column IM_MODEL_MAPPING.indexed
  is '源元数据字段索引（0：是；1：否）';
comment on column IM_MODEL_MAPPING.primary
  is '源元数据字段主键（0：是；1：否）';
comment on column IM_MODEL_MAPPING.stored
  is '源元数据字段存储（0：是；1：否）';
alter table IM_MODEL_MAPPING
  add constraint PK_IM_MODEL_MAPPING primary key (PK_ID);


create table IM_MODEL_UPDATE_KEY
(
  pk_id    VARCHAR2(32) not null,
  col_id   VARCHAR2(32) not null,
  model_id VARCHAR2(32) not null
)
;
comment on table IM_MODEL_UPDATE_KEY
  is '交互建模-模型更新键值';
comment on column IM_MODEL_UPDATE_KEY.pk_id
  is '主键';
comment on column IM_MODEL_UPDATE_KEY.col_id
  is '目标元数据字段ID';
comment on column IM_MODEL_UPDATE_KEY.model_id
  is '模型ID';
alter table IM_MODEL_UPDATE_KEY
  add constraint PK_IM_MODEL_UPDATE_KEY primary key (PK_ID);

alter table IM_METADATA rename column DESCRIBE to DESCRIPTION;
alter table IM_MODEL rename column DESCRIBE to DESCRIPTION;
alter table IM_MODEL_FILTER_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IM_METADATA_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IM_MODEL_MAPPING rename column DESCRIBE to DESCRIPTION;

-- 修改表的字段名称 PRIMARY 改为 UNIQUEKEY
alter table IM_METADATA_COLUMN rename column PRIMARY to UNIQUEKEY;
alter table IM_MODEL_MAPPING rename column PRIMARY to UNIQUEKEY;

-- IM_METADATA表添加索引
create index IDX_IM_MD_DELFLG on IM_METADATA (DEL_FLG);
create index IDX_IM_MD_DELFLG_NAME on IM_METADATA (DEL_FLG, NAME);

-- IM_METADATA_COLUMN表添加索引
create index IDX_IM_MD_COL_MDID on IM_METADATA_COLUMN (MD_ID);

-- IM_MODEL表添加索引
create index IDX_IM_MODEL_DELFLG on IM_MODEL (DEL_FLG);
create index IDX_IM_MODEL_DELFLG_NAME on IM_MODEL (DEL_FLG, NAME);

-- IM_MODEL_FILTER_COLUMN表添加索引
create index IDX_IM_MODEL_FILTER_MODELID on IM_MODEL_FILTER_COLUMN (MODEL_ID);

-- IM_MODEL_MAPPING表添加索引
create index IDX_IM_MODEL_MAPPING_MODELID on IM_MODEL_MAPPING (MODEL_ID);

-- IM_MODEL_UPDATE_KEY表添加索引
create index IDX_IM_MODEL_UPDATEKEY_MODELID on IM_MODEL_UPDATE_KEY (MODEL_ID);
