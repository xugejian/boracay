-- ----------------------------
-- Table structure for mm_application
-- ----------------------------
CREATE TABLE `mm_application` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MODEL_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型ID' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((英文、唯一)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0：未删除，1：删除)' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_MM_APP_DELFLG` (`DEL_FLG`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='模型管理-应用表'

;

-- ----------------------------
-- Table structure for mm_application_execute_param
-- ----------------------------
CREATE TABLE `mm_application_execute_param` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`SEQ`  decimal(3,0) NOT NULL COMMENT '序号' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`IS_NEED`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否必输（0:是；1：否）' ,
`DEFAULT_VAL`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '默认值' ,
`APP_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型应用ID' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_MM_APP_EXE_APPID` (`APP_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='模型管理-执行参数表'

;

-- ----------------------------
-- Table structure for mm_application_return_param
-- ----------------------------
CREATE TABLE `mm_application_return_param` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`SEQ`  decimal(3,0) NOT NULL COMMENT '序号' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`APP_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型应用ID' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_MM_APP_RET_APPID` (`APP_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='模型管理-返回参数表'

;

-- ----------------------------
-- Table structure for mm_contractor
-- ----------------------------
CREATE TABLE `mm_contractor` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`NAME`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文名称' ,
`HTTP_URL`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '远程连接' ,
`PRINCIPAL`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '负责人' ,
`MOBILE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '负责电话' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0：未删除，1：删除)' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`EXTEND_FIELD1`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段1' ,
`EXTEND_FIELD2`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段2' ,
`CN_NAME`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称' ,
`FTP_PASSWORD`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ftp登陆密码' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_MM_CONT_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_MM_CONT_DELFLG_NAME` (`NAME`, `DEL_FLG`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='模型管理-厂商管理'

;

-- ----------------------------
-- Table structure for mm_model_info
-- ----------------------------
CREATE TABLE `mm_model_info` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((英文、唯一)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`VER_NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '版本注释' ,
`VER_NUM`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号' ,
`STATUS`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布状态(1：待发布；2：已发布；3：归档)' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0：未删除，1：删除)' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`CONTRACTOR`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型开发厂商ID' ,
`MODEL_TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1：同步；2：异步；3：批处理，多个用逗号分隔' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_MM_MODEL_INFO_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_MM_MODEL_INFO_DELFLG_CONID` (`DEL_FLG`, `CONTRACTOR`) USING BTREE ,
INDEX `IDX_MM_MODEL_INFO_DELFLG_NAME` (`DEL_FLG`, `NAME`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='模型管理-模型信息表'

;

-- ----------------------------
-- Table structure for mm_model_param
-- ----------------------------
CREATE TABLE `mm_model_param` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MM_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型ID' ,
`SEQ`  decimal(3,0) NOT NULL COMMENT '序号（和类型一起确定唯一）' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((唯一、英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`TYPE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型(1、查询字段；2、返回字段)' ,
`LENGTH`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '长度' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`COL_TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段类型' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_MM_MODEL_PARAM_MMID` (`MM_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='模型管理-模型字段列表'

;

-- 删除字段
alter table MM_CONTRACTOR drop column EXTEND_FIELD1;
alter table MM_CONTRACTOR drop column EXTEND_FIELD2;
