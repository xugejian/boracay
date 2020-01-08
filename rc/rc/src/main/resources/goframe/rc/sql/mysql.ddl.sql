-- ----------------------------
-- Table structure for rc_service
-- ----------------------------
CREATE TABLE `rc_service` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '说明' ,
`TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型（IQ：交互查询；MM：模型管理；OLQ：联机查询；RTS-CUS：实时流消费者；RTS-PRO：实时流生产者）' ,
`APP_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用ID' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除）' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`STATUS`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '启停标志（0：启动，1：停用）' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_RC_SERVICE_DF` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_RC_SERVICE_DF_NAME` (`DEL_FLG`, `NAME`) USING BTREE ,
INDEX `IDX_RC_SERVICE_DF_TYPE` (`DEL_FLG`, `TYPE`) USING BTREE ,
INDEX `IDX_RC_SERVICE_DF_TYPE_APPID` (`DEL_FLG`, `TYPE`, `APP_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='注册中心-服务注册表'

;

-- ----------------------------
-- Table structure for rc_user_service
-- ----------------------------
CREATE TABLE `rc_user_service` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID' ,
`SERVICE_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务ID' ,
`IP_SECTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'IP段' ,
`MAX_SYNC_NUM`  decimal(10,0) NOT NULL COMMENT '最大同步并发数' ,
`MAX_ASYNC_NUM`  decimal(10,0) NOT NULL COMMENT '最大异步并发数' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除）' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`MAX_SYNC_WAIT_NUM`  decimal(10,0) NOT NULL COMMENT '最大同步队列等待数' ,
`MAX_ASYNC_WAIT_NUM`  decimal(10,0) NOT NULL COMMENT '最大异步队列等待数' ,
`MAX_SYNC_WAIT_TIMEOUT`  decimal(10,0) NULL DEFAULT NULL COMMENT '同步最大等待超时时间' ,
`MAX_ASYNC_WAIT_TIMEOUT`  decimal(10,0) NULL DEFAULT NULL COMMENT '异步最大等待超时时间' ,
`MAX_SYNC_EXECUTE_TIMEOUT`  decimal(10,0) NULL DEFAULT NULL COMMENT '同步最大执行超时时间' ,
`MAX_ASYNC_EXECUTE_TIMEOUT`  decimal(10,0) NULL DEFAULT NULL COMMENT '异步最大执行超时时间' ,
`ALARM_TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '告警方式（NONE、MAIL、...）' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_RC_USER_SERVICE_DF` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_RC_USER_SERVICE_DF_SID` (`DEL_FLG`, `SERVICE_ID`) USING BTREE ,
INDEX `IDX_RC_USER_SERVICE_DF_SID_UID` (`DEL_FLG`, `SERVICE_ID`, `USER_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='注册中心-服务授权表'
;

-- 往服务注册信息表中添加是否缓存和缓存时效两个字段
ALTER TABLE RC_SERVICE ADD IS_CACHE CHAR(1) default 1 COMMENT '是否缓存（0：是，1：否）';
ALTER TABLE RC_SERVICE ADD TIME_OUT DECIMAL(10) default 60 COMMENT '缓存时效（秒）';

-- 往服务注册信息表中添加是否存储字段
ALTER TABLE RC_SERVICE ADD IS_STORE CHAR(1) default 1 COMMENT '是否存储结果数据（0：是，1：否）';

-- 往服务注册信息表中添加备注字段
ALTER TABLE RC_SERVICE ADD NOTE VARCHAR2(4000) COMMENT '备注';

-- 往服务授权信息表中添加日期类型、开始时间、结束时间字段
ALTER TABLE RC_USER_SERVICE ADD DATE_TYPE VARCHAR2(32) default 'ALL' COMMENT '日期窗口类型（ALL、MON-FRI、WEEKEND、...）';
ALTER TABLE RC_USER_SERVICE ADD START_TIME VARCHAR2(32) COMMENT '时间窗口的开始时间';
ALTER TABLE RC_USER_SERVICE ADD END_TIME VARCHAR2(32) COMMENT '时间窗口的结束时间';
