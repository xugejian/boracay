
create table RC_SERVICE
(
  pk_id    VARCHAR2(32) not null,
  name     VARCHAR2(64),
  describe VARCHAR2(256),
  type     VARCHAR2(32),
  app_id   VARCHAR2(32),
  del_flg  CHAR(1),
  crt_user VARCHAR2(32),
  crt_time VARCHAR2(32),
  upt_user VARCHAR2(32),
  upt_time VARCHAR2(32),
  status   CHAR(1) default 0 not null
)
;
comment on table RC_SERVICE
  is '注册中心-服务注册表';
comment on column RC_SERVICE.pk_id
  is '主键';
comment on column RC_SERVICE.name
  is '名称';
comment on column RC_SERVICE.describe
  is '说明';
comment on column RC_SERVICE.type
  is '类型（IQ：交互查询；MM：模型管理；OLQ：联机查询；RTS-CUS：实时流消费者；RTS-PRO：实时流生产者）';
comment on column RC_SERVICE.app_id
  is '应用ID';
comment on column RC_SERVICE.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RC_SERVICE.crt_user
  is '创建者';
comment on column RC_SERVICE.crt_time
  is '创建时间';
comment on column RC_SERVICE.upt_user
  is '更新者';
comment on column RC_SERVICE.upt_time
  is '更新时间';
comment on column RC_SERVICE.status
  is '启停标志（0：启动，1：停用）';
create index IDX_RC_SERVICE_DF on RC_SERVICE (DEL_FLG);
create index IDX_RC_SERVICE_DF_NAME on RC_SERVICE (DEL_FLG, NAME);
create index IDX_RC_SERVICE_DF_TYPE on RC_SERVICE (DEL_FLG, TYPE);
create index IDX_RC_SERVICE_DF_TYPE_APPID on RC_SERVICE (DEL_FLG, TYPE, APP_ID);
alter table RC_SERVICE
  add constraint PK_RC_SERVICE primary key (PK_ID);

create table RC_USER_SERVICE
(
  pk_id                     VARCHAR2(32) not null,
  user_id                   VARCHAR2(32) not null,
  service_id                VARCHAR2(32) not null,
  ip_section                VARCHAR2(512),
  max_sync_num              NUMBER(10) not null,
  max_async_num             NUMBER(10) not null,
  del_flg                   CHAR(1) not null,
  crt_user                  VARCHAR2(32) not null,
  crt_time                  VARCHAR2(32) not null,
  upt_user                  VARCHAR2(32) not null,
  upt_time                  VARCHAR2(32) not null,
  max_sync_wait_num         NUMBER(10) default 0 not null,
  max_async_wait_num        NUMBER(10) default 0 not null,
  max_sync_wait_timeout     NUMBER(10) default 3000 not null,
  max_async_wait_timeout    NUMBER(10) default 600000 not null,
  max_sync_execute_timeout  NUMBER(10) default 3000 not null,
  max_async_execute_timeout NUMBER(10) default 600000 not null,
  status                    CHAR(1) default 0 not null,
  alarm_type                VARCHAR2(32) default 'NONE' not null
)
;
comment on table RC_USER_SERVICE
  is '注册中心-服务授权表';
comment on column RC_USER_SERVICE.pk_id
  is '主键';
comment on column RC_USER_SERVICE.user_id
  is '用户ID';
comment on column RC_USER_SERVICE.service_id
  is '服务ID';
comment on column RC_USER_SERVICE.ip_section
  is 'IP段';
comment on column RC_USER_SERVICE.max_sync_num
  is '最大同步并发数';
comment on column RC_USER_SERVICE.max_async_num
  is '最大异步并发数';
comment on column RC_USER_SERVICE.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RC_USER_SERVICE.crt_user
  is '创建者';
comment on column RC_USER_SERVICE.crt_time
  is '创建时间';
comment on column RC_USER_SERVICE.upt_user
  is '更新者';
comment on column RC_USER_SERVICE.upt_time
  is '更新时间';
comment on column RC_USER_SERVICE.max_sync_wait_num
  is '最大同步队列等待数';
comment on column RC_USER_SERVICE.max_async_wait_num
  is '最大异步队列等待数';
comment on column RC_USER_SERVICE.max_sync_wait_timeout
  is '同步最大等待超时时间';
comment on column RC_USER_SERVICE.max_async_wait_timeout
  is '异步最大等待超时时间';
comment on column RC_USER_SERVICE.max_sync_execute_timeout
  is '同步最大执行超时时间';
comment on column RC_USER_SERVICE.max_async_execute_timeout
  is '异步最大执行超时时间';
comment on column RC_USER_SERVICE.status
  is '启停标志（0：启动，1：停用）';
comment on column RC_USER_SERVICE.alarm_type
  is '告警方式（NONE、MAIL、...）';
create index IDX_RC_USER_SERVICE_DF on RC_USER_SERVICE (DEL_FLG);
create index IDX_RC_USER_SERVICE_DF_SID on RC_USER_SERVICE (DEL_FLG, SERVICE_ID);
create index IDX_RC_USER_SERVICE_DF_SID_UID on RC_USER_SERVICE (DEL_FLG, SERVICE_ID, USER_ID);
alter table RC_USER_SERVICE
  add constraint PK_RC_USER_SERVICE primary key (PK_ID);

alter table RC_SERVICE rename column DESCRIBE to DESCRIPTION;

-- 往服务注册信息表中添加是否缓存和缓存时效两个字段
ALTER TABLE RC_SERVICE ADD IS_CACHE CHAR(1) default 1;
COMMENT ON COLUMN RC_SERVICE.IS_CACHE IS '是否缓存（0：是，1：否）';
ALTER TABLE RC_SERVICE ADD TIME_OUT NUMBER(10) default 60;
COMMENT ON COLUMN RC_SERVICE.TIME_OUT IS '缓存时效（秒）';

-- 往服务注册信息表中添加是否存储字段
ALTER TABLE RC_SERVICE ADD IS_STORE CHAR(1) default 1;
COMMENT ON COLUMN RC_SERVICE.IS_STORE IS '是否存储结果数据（0：是，1：否）';

-- 往服务注册信息表中添加备注字段
ALTER TABLE RC_SERVICE ADD NOTE VARCHAR2(4000);
COMMENT ON COLUMN RC_SERVICE.NOTE IS '备注';

-- 往服务授权信息表中添加日期类型、开始时间、结束时间字段
ALTER TABLE RC_USER_SERVICE ADD DATE_TYPE VARCHAR2(32) default 'ALL';
COMMENT ON COLUMN RC_USER_SERVICE.DATE_TYPE IS '日期窗口类型（ALL、MON-FRI、WEEKEND、...）';
ALTER TABLE RC_USER_SERVICE ADD START_TIME VARCHAR2(32);
COMMENT ON COLUMN RC_USER_SERVICE.START_TIME IS '时间窗口的开始时间';
ALTER TABLE RC_USER_SERVICE ADD END_TIME VARCHAR2(32);
COMMENT ON COLUMN RC_USER_SERVICE.END_TIME IS '时间窗口的结束时间';
