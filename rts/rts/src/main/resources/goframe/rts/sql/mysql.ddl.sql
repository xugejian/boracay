-- ----------------------------
-- Table structure for rts_customer_config
-- ----------------------------
CREATE TABLE `rts_customer_config` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MD_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元数据ID' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`GROUP_ID`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除）' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_RTS_CER_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_RTS_CER_DELFLG_MDID` (`DEL_FLG`, `MD_ID`) USING BTREE ,
INDEX `IDX_RTS_CER_DELFLG_NAME` (`DEL_FLG`, `NAME`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='实时流-消费者配置'

;

-- ----------------------------
-- Table structure for rts_metadata
-- ----------------------------
CREATE TABLE `rts_metadata` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`DS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据源ID' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称(英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '说明' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除）' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`TOPIC`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主题' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_RTS_MD_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_RTS_MD_DELFLG_DSID` (`DEL_FLG`, `DS_ID`) USING BTREE ,
INDEX `IDX_RTS_MD_DELFLG_NAME` (`NAME`, `DEL_FLG`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='实时流-元数据配置'

;

-- ----------------------------
-- Table structure for rts_metadata_column
-- ----------------------------
CREATE TABLE `rts_metadata_column` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MD_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元数据ID' ,
`SEQ`  decimal(3,0) NOT NULL COMMENT '序号' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((唯一、英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_RTS_MD_COL_MDID` (`MD_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='实时流-元数据字段表'

;

-- ----------------------------
-- Table structure for rts_producrer_config
-- ----------------------------
CREATE TABLE `rts_producrer_config` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MD_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元数据ID' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称（英文）' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除）' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_RTS_PER_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_RTS_PER_DELFLG_MDID` (`DEL_FLG`, `MD_ID`) USING BTREE ,
INDEX `IDX_RTS_PER_DELFLG_NAME` (`DEL_FLG`, `NAME`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='实时流-生产者配置'

;

-- 删除字段
alter table RTS_CUSTOMER_CONFIG drop column GROUP_ID;
