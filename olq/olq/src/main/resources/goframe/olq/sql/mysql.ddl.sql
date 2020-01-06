-- ----------------------------
-- Table structure for olq_application
-- ----------------------------
CREATE TABLE `olq_application` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`OLQ_SQL`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用配置的SQL语句' ,
`OLQ_DS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联机查询数据源ID' ,
`MAX_NUM`  decimal(10,0) NULL DEFAULT NULL COMMENT '最大返回数' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志(0：未删除，1：删除)' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`NAME`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称' ,
`DESCRIPTION`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
PRIMARY KEY (`PK_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for olq_application_param
-- ----------------------------
CREATE TABLE `olq_application_param` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`PARAM_NAME`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数名称' ,
`PARAM_DESC`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数说明' ,
`DEFAULT_VALUE`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值' ,
`IS_NEED`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否必输，0：必输，1：不必输' ,
`APP_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用ID' ,
`SEQ`  decimal(3,0) NULL DEFAULT NULL COMMENT '序号' ,
PRIMARY KEY (`PK_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;