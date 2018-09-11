-- 消费日志表中添加接口耗时字段
ALTER TABLE MC_CONSUME_LOG ADD CONSUME_TIME NUMBER(13);
COMMENT ON COLUMN MC_CONSUME_LOG.CONSUME_TIME IS '接口耗时（ms）';

-- 添加错误信息字典
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200005', '模型不支持该类型', null, 6, null, null, 'default', null);
