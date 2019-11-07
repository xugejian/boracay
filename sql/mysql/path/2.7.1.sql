-- 往服务注册信息表中添加是否存储字段
ALTER TABLE RC_SERVICE ADD IS_STORE CHAR(1) default 1;
COMMENT ON COLUMN RC_SERVICE.IS_STORE IS '是否存储结果数据（0：是，1：否）';

-- 创建消费数据表
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

insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RC_DATE_TYPE', '注册中心-日期窗口类型', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_DATE_TYPE', 'ALL', '全部日期', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_DATE_TYPE', 'MON-FRI', '周一至周五', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_DATE_TYPE', 'WEEKEND', '周末', null, 3, null, null, 'default', null);

insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000019', '服务类型不支持', null, 19, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000020', '请求日期不在允许的日期窗口内', null, 20, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000021', '请求时间不在允许的时间窗口内', null, 21, null, null, 'default', null);
