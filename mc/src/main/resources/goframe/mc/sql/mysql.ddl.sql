-- ----------------------------
-- Table structure for mc_consume_log
-- ----------------------------
CREATE TABLE `mc_consume_log` (
`PK_ID`  varchar(68) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`USER_NAME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名' ,
`SERVICE_NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务名' ,
`REQUEST_CONTENT`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求内容' ,
`REQUEST_START_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求开始时间' ,
`REQUEST_END_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求结束时间' ,
`RUN_START_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行开始时间' ,
`RUN_END_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行结束时间' ,
`RESPONSE_CONTENT`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应内容（文件路径、少量的消息信息）' ,
`STATUS`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结果状态(0：成功1：失败)' ,
`ERROR_CODE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误编码，与UDSP错误编码相同' ,
`APP_TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用类型（IQ、OLQ、MM、RTS_PRODUCER、RTS_CONSUMER）' ,
`REQUEST_TYPE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求类型（0：内部请求 1：外部请求）' ,
`APP_NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名' ,
`MESSAGE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '错误信息或其他消息提示' ,
`SYNC_TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同步/异步' ,
PRIMARY KEY (`PK_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='监控中心-消费日志'

;

-- 消费日志表中添加是否从缓存获取字段
ALTER TABLE MC_CONSUME_LOG ADD IS_CACHE CHAR(1) COMMENT '是否从缓存获取（0：是，1：否）';

-- 消费日志表中添加接口耗时字段
ALTER TABLE MC_CONSUME_LOG ADD CONSUME_TIME DECIMAL(10,0) COMMENT '接口耗时（ms）';

-- ----------------------------
-- Table structure for mc_consume_data
-- ----------------------------
CREATE TABLE `mc_consume_data` (
`USER_NAME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名' ,
`SERVICE_NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务名' ,
`SAVE_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保存时间' ,
`REQUEST_CONTENT`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求内容' ,
`RESPONSE_CONTENT`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应内容' ,
`APP_TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用类型' ,
`APP_NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名'
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='监控中心-消费数据'
;