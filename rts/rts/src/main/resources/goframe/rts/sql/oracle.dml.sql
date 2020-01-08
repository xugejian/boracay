-- 页面信息
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100031', '实时流', null, 'rts.core', '2', null, 401, 'default', null, 'root', null);

insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100121', 'RTS配置管理', null, 'rts.cm', '2', null, 10, 'default', null, '100031', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100181', 'RTS数据源配置', null, 'rts.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=RTS', '100121', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100311', 'RTS元数据配置', null, 'rts.cm.md', '1', null, 2, 'default', 'rts.cm.md.list', '100121', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100191', 'RTS生产者配置', null, 'rts.cm.producer', '1', null, 3, 'default', 'rts.cm.producer.list', '100121', 'fa fa-sign-in');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100201', 'RTS消费者配置', null, 'rts.cm.consumer', '1', null, 4, 'default', 'rts.cm.consumer.list', '100121', 'fa fa-sign-out');

insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100131', 'RTS应用测试', null, 'rts.qm', '1', null, 20, 'default', 'rts.qm.test', '100031', 'fa fa-dashboard');

commit;

-- 字典信息
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_CONSUMER_TIMEOUT', '实时流-消费者-消费超时时间', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '100', '100ms', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '200', '200ms', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '500', '500ms', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '1000', '1000ms', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '2000', '2000ms', null, 5, null, null, 'default', null);

insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_DS_TYPE', '实时流-数据源类型', 'default');

insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_IMPL_CLASS', '实时流-接口实现类', 'default');

insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'RTS_CONSUMER', '实时流-消费者', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'RTS_PRODUCER', '实时流-生产者', null, 4, null, null, 'default', null);

commit;