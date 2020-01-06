
create table RTS_CUSTOMER_CONFIG
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  group_id VARCHAR2(64) not null,
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null
)
;
comment on table RTS_CUSTOMER_CONFIG
  is '实时流-消费者配置';
comment on column RTS_CUSTOMER_CONFIG.pk_id
  is '主键';
comment on column RTS_CUSTOMER_CONFIG.md_id
  is '元数据ID';
comment on column RTS_CUSTOMER_CONFIG.name
  is '名称';
comment on column RTS_CUSTOMER_CONFIG.describe
  is '说明';
comment on column RTS_CUSTOMER_CONFIG.group_id
  is '组';
comment on column RTS_CUSTOMER_CONFIG.note
  is '备注';
comment on column RTS_CUSTOMER_CONFIG.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RTS_CUSTOMER_CONFIG.crt_user
  is '创建者';
comment on column RTS_CUSTOMER_CONFIG.crt_time
  is '创建时间';
comment on column RTS_CUSTOMER_CONFIG.upt_user
  is '更新者';
comment on column RTS_CUSTOMER_CONFIG.upt_time
  is '更新时间';
create index IDX_RTS_CER_DELFLG on RTS_CUSTOMER_CONFIG (DEL_FLG);
create index IDX_RTS_CER_DELFLG_MDID on RTS_CUSTOMER_CONFIG (DEL_FLG, MD_ID);
create index IDX_RTS_CER_DELFLG_NAME on RTS_CUSTOMER_CONFIG (DEL_FLG, NAME);
alter table RTS_CUSTOMER_CONFIG
  add constraint PK_RTS_CUSTOMER_CONFIG primary key (PK_ID);


create table RTS_METADATA
(
  pk_id    VARCHAR2(32) not null,
  ds_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256),
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null,
  topic    VARCHAR2(256) not null
)
;
comment on table RTS_METADATA
  is '实时流-元数据配置';
comment on column RTS_METADATA.pk_id
  is '主键';
comment on column RTS_METADATA.ds_id
  is '数据源ID';
comment on column RTS_METADATA.name
  is '名称(英文)';
comment on column RTS_METADATA.describe
  is '说明';
comment on column RTS_METADATA.note
  is '备注';
comment on column RTS_METADATA.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RTS_METADATA.crt_user
  is '创建者';
comment on column RTS_METADATA.crt_time
  is '创建时间';
comment on column RTS_METADATA.upt_user
  is '更新者';
comment on column RTS_METADATA.upt_time
  is '更新时间';
comment on column RTS_METADATA.topic
  is '主题';
create index IDX_RTS_MD_DELFLG on RTS_METADATA (DEL_FLG);
create index IDX_RTS_MD_DELFLG_DSID on RTS_METADATA (DEL_FLG, DS_ID);
create index IDX_RTS_MD_DELFLG_NAME on RTS_METADATA (DEL_FLG, NAME);
alter table RTS_METADATA
  add constraint PK_RTS_METADATA primary key (PK_ID);


create table RTS_METADATA_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     VARCHAR2(32) not null
)
;
comment on table RTS_METADATA_COLUMN
  is '实时流-元数据字段表';
comment on column RTS_METADATA_COLUMN.pk_id
  is '主键';
comment on column RTS_METADATA_COLUMN.md_id
  is '元数据ID';
comment on column RTS_METADATA_COLUMN.seq
  is '序号';
comment on column RTS_METADATA_COLUMN.name
  is '名称((唯一、英文)';
comment on column RTS_METADATA_COLUMN.describe
  is '说明';
comment on column RTS_METADATA_COLUMN.type
  is '类型';
create index IDX_RTS_MD_COL_MDID on RTS_METADATA_COLUMN (MD_ID);
alter table RTS_METADATA_COLUMN
  add constraint PK_RTS_METADATA_COLUMN primary key (PK_ID);


create table RTS_PRODUCRER_CONFIG
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
comment on table RTS_PRODUCRER_CONFIG
  is '实时流-生产者配置';
comment on column RTS_PRODUCRER_CONFIG.pk_id
  is '主键';
comment on column RTS_PRODUCRER_CONFIG.md_id
  is '元数据ID';
comment on column RTS_PRODUCRER_CONFIG.name
  is '名称（英文）';
comment on column RTS_PRODUCRER_CONFIG.describe
  is '说明';
comment on column RTS_PRODUCRER_CONFIG.note
  is '备注';
comment on column RTS_PRODUCRER_CONFIG.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RTS_PRODUCRER_CONFIG.crt_user
  is '创建者';
comment on column RTS_PRODUCRER_CONFIG.crt_time
  is '创建时间';
comment on column RTS_PRODUCRER_CONFIG.upt_user
  is '更新者';
comment on column RTS_PRODUCRER_CONFIG.upt_time
  is '更新时间';
create index IDX_RTS_PER_DELFLG on RTS_PRODUCRER_CONFIG (DEL_FLG);
create index IDX_RTS_PER_DELFLG_MDID on RTS_PRODUCRER_CONFIG (DEL_FLG, MD_ID);
create index IDX_RTS_PER_DELFLG_NAME on RTS_PRODUCRER_CONFIG (DEL_FLG, NAME);
alter table RTS_PRODUCRER_CONFIG
  add constraint PK_RTS_PRODUCRER_CONFIG primary key (PK_ID);

alter table RTS_CUSTOMER_CONFIG rename column DESCRIBE to DESCRIPTION;
alter table RTS_METADATA rename column DESCRIBE to DESCRIPTION;
alter table RTS_METADATA_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table RTS_PRODUCRER_CONFIG rename column DESCRIBE to DESCRIPTION;

-- 删除字段
alter table RTS_CUSTOMER_CONFIG drop column GROUP_ID;
