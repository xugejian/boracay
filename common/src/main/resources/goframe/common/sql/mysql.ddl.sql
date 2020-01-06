-- ----------------------------
-- Table structure for com_datasource
-- ----------------------------
CREATE TABLE `com_datasource` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((唯一、英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据源类型' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`IMPL_CLASS`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '接口实现类' ,
`MODEL`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属模块（IQ、OLQ、RTS、IM）' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_COM_DS_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_COM_DS_DELFLG_MODEL_NAME` (`MODEL`, `NAME`, `DEL_FLG`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='数据源表'
;

-- ----------------------------
-- Table structure for com_operation_log
-- ----------------------------
CREATE TABLE `com_operation_log` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`ACTION_TYPE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型（1添加 2更新 3删除 4查询）' ,
`ACTION_URL`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作URL' ,
`ACTION_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作用户' ,
`ACTION_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作时间' ,
`ACTION_CONTENT`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作信息' ,
PRIMARY KEY (`PK_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='操作日志表'
;

-- ----------------------------
-- Table structure for com_properties
-- ----------------------------
CREATE TABLE `com_properties` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`FK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键ID' ,
`NAME`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称' ,
`VALUE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数值' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '说明' ,
PRIMARY KEY (`PK_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='属性信息表'
;
