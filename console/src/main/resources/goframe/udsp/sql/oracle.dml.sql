-- 修改字段长度
alter table udsp2.T_GF_DICT modify (DICT_NAME VARCHAR2(256), FILTER VARCHAR2(256));

-- 字典信息
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('118011', '操作导航', null, 'navigator', '1', null, 1, 'default', 'udsp.navigator', '118001', 'fa fa-arrow-right');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('118001', '操作导航', null, 'navigator.core', '2', null, 0, 'default', null, 'root', null);

commit;
