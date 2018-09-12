-- 修改“运行队列”菜单指向的页面地址
update T_GF_MENU set menu_action = 'mc.queue.run.list' where menuid = '115041';
-- 修改“等待队列”菜单指向的页面地址
update T_GF_MENU set menu_action = 'mc.queue.wait.list' where menuid = '115051';
-- 修改“批量作业”菜单指向的页面地址
update T_GF_MENU set menu_action = 'im.job.batch.list' where menuid = '115011';
-- 修改“实时作业”菜单指向的页面地址
update T_GF_MENU set menu_action = 'im.job.realtime.list' where menuid = '115021';
