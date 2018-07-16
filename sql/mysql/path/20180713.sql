-- 往服务注册信息表中添加是否缓存和缓存时效两个字段
ALTER TABLE RC_SERVICE ADD IS_CACHE CHAR(1) default 1 COMMENT '是否缓存（0：是，1：否）';
ALTER TABLE RC_SERVICE ADD TIME_OUT DECIMAL(10) default 60 COMMENT '缓存时效（秒）';

-- 初始化是否缓存和缓存时效的值
update RC_SERVICE set IS_CACHE = '1', TIME_OUT = 60;

-- 添加YES_OR_NO
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('YES_OR_NO', '是或否', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('YES_OR_NO', '0', '是', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('YES_OR_NO', '1', '否', null, 2, null, null, 'default', null);
