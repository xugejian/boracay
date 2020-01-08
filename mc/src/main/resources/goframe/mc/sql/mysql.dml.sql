-- 页面信息
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100221', '监控中心', null, 'mc.core', '2', null, 601, 'default', null, 'root', null);

insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100251', '统计监控', null, 'mc.stats', '2', null, 40, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('101011', '图表统计', null, 'mc.stats.charts', '1', null, 1, 'default', 'mc.stats.charts', '100251', 'fa fa-bar-chart-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('114001', '用户维度统计', null, 'mc.stats.user.list', '1', null, 2, 'default', 'mc.stats.user.list', '100251', 'fa fa-bar-chart-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('114011', '服务维度统计', null, 'mc.stats.service.list', '1', null, 3, 'default', 'mc.stats.service.list', '100251', 'fa fa-bar-chart-o');

insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100231', '日志监控', null, 'mc.log', '2', null, 10, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100261', '操作日志', null, 'mc.log.operation', '1', null, 1, 'default', 'mc.log.operation.list', '100231', 'fa fa-file-text-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100271', '消费日志', null, 'mc.log.consume', '1', null, 2, 'default', 'mc.log.consume.list', '100231', 'fa fa-file-text');

insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115031', '队列监控', null, 'mc.queue', '2', null, 20, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115041', '运行队列', null, 'mc.queue.run', '1', null, 1, 'default', 'mc.queue.run.list', '115031', 'fa fa-align-justify');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115051', '等待队列', null, 'mc.queue.wait', '1', null, 2, 'default', 'mc.queue.wait.list', '115031', 'fa fa-align-center');

-- 字典信息
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MC_OPERATION_LOG_TYPE', '监控中心-操作日志-操作类型', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '1', '添加', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '2', '更新', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '3', '删除', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '4', '查询', null, 4, null, null, 'default', null);

insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MC_CONSUME_LOG_STATUS', '监控中心-消费日志-结果状态', 'default');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_CONSUME_LOG_STATUS', '0', '成功', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_CONSUME_LOG_STATUS', '1', '失败', null, 2, null, null, 'default', null);
