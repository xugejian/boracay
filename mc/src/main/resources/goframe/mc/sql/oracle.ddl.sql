
create table MC_CONSUME_LOG
(
  pk_id              VARCHAR2(68) not null,
  user_name          VARCHAR2(32) not null,
  service_name       VARCHAR2(64),
  request_content    VARCHAR2(4000) not null,
  request_start_time VARCHAR2(32) not null,
  request_end_time   VARCHAR2(32) not null,
  run_start_time     VARCHAR2(32),
  run_end_time       VARCHAR2(32),
  response_content   VARCHAR2(512),
  status             CHAR(1) not null,
  error_code         VARCHAR2(32),
  app_type           VARCHAR2(32),
  request_type       CHAR(1) not null,
  app_name           VARCHAR2(64),
  message            VARCHAR2(4000),
  sync_type          VARCHAR2(32)
)
;
comment on table MC_CONSUME_LOG
  is '监控中心-消费日志';
comment on column MC_CONSUME_LOG.pk_id
  is '主键';
comment on column MC_CONSUME_LOG.user_name
  is '用户名';
comment on column MC_CONSUME_LOG.service_name
  is '服务名';
comment on column MC_CONSUME_LOG.request_content
  is '请求内容';
comment on column MC_CONSUME_LOG.request_start_time
  is '请求开始时间';
comment on column MC_CONSUME_LOG.request_end_time
  is '请求结束时间';
comment on column MC_CONSUME_LOG.run_start_time
  is '执行开始时间';
comment on column MC_CONSUME_LOG.run_end_time
  is '执行结束时间';
comment on column MC_CONSUME_LOG.response_content
  is '响应内容（文件路径、少量的消息信息）';
comment on column MC_CONSUME_LOG.status
  is '结果状态(0：成功1：失败)';
comment on column MC_CONSUME_LOG.error_code
  is '错误编码，与UDSP错误编码相同';
comment on column MC_CONSUME_LOG.app_type
  is '应用类型（IQ、OLQ、MM、RTS_PRODUCER、RTS_CONSUMER）';
comment on column MC_CONSUME_LOG.request_type
  is '请求类型（0：内部请求 1：外部请求）';
comment on column MC_CONSUME_LOG.app_name
  is '应用名';
comment on column MC_CONSUME_LOG.message
  is '错误信息或其他消息提示';
comment on column MC_CONSUME_LOG.sync_type
  is '同步/异步';
alter table MC_CONSUME_LOG
  add constraint PK_MC_CONSUME_LOG primary key (PK_ID);

-- 消费日志表中添加是否从缓存获取字段
ALTER TABLE MC_CONSUME_LOG ADD IS_CACHE CHAR(1);
COMMENT ON COLUMN MC_CONSUME_LOG.IS_CACHE IS '是否从缓存获取（0：是，1：否）';

-- 消费日志表中添加接口耗时字段
ALTER TABLE MC_CONSUME_LOG ADD CONSUME_TIME NUMBER(13);
COMMENT ON COLUMN MC_CONSUME_LOG.CONSUME_TIME IS '接口耗时（ms）';

create table MC_CONSUME_DATA
(
  user_name        VARCHAR2(32) not null,
  service_name     VARCHAR2(64) not null,
  save_time        VARCHAR2(32) not null,
  request_content  CLOB not null,
  response_content CLOB not null,
  app_type         VARCHAR2(32) not null,
  app_name         VARCHAR2(64) not null
)
;
comment on table MC_CONSUME_DATA
  is '监控中心-消费数据';
comment on column MC_CONSUME_DATA.user_name
  is '用户名';
comment on column MC_CONSUME_DATA.service_name
  is '服务名';
comment on column MC_CONSUME_DATA.save_time
  is '保存时间';
comment on column MC_CONSUME_DATA.request_content
  is '请求内容';
comment on column MC_CONSUME_DATA.response_content
  is '响应内容';
comment on column MC_CONSUME_DATA.app_type
  is '应用类型';
comment on column MC_CONSUME_DATA.app_name
  is '应用名';