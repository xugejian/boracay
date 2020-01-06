-- ----------------------------
-- Table structure for im_metadata
-- ----------------------------
CREATE TABLE `im_metadata` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`DS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据源ID' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称/表名((唯一、英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明/表说明' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除）' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`TB_NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '库表' ,
`STATUS`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（1：未建，2：已建）' ,
`TYPE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型（0：内表，1：外表）' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_IM_MD_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_IM_MD_DELFLG_NAME` (`DEL_FLG`, `NAME`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='交互建模-元数据配置'

;

-- ----------------------------
-- Table structure for im_metadata_column
-- ----------------------------
CREATE TABLE `im_metadata_column` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MD_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元数据ID' ,
`SEQ`  decimal(3,0) NOT NULL COMMENT '序号' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称（唯一、英文）' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型' ,
`LENGTH`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '长度' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`INDEXED`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '索引（0：是；1：否）' ,
`UNIQUEKEY`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键（0：是；1：否）' ,
`STORED`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存储（0：是；1：否）' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_IM_MD_COL_MDID` (`MD_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='交互建模-元数据字段'

;

-- ----------------------------
-- Table structure for im_model
-- ----------------------------
CREATE TABLE `im_model` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`S_DS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源数据源ID' ,
`T_MD_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标元数据ID' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`DEL_FLG`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标志（0：未删除，1：删除）' ,
`CRT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者' ,
`CRT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间' ,
`UPT_USER`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' ,
`UPT_TIME`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间' ,
`TYPE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型（1：批量 2：实时）' ,
`BUILD_MODE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '构建策略（1：增量，2：全量）' ,
`UPDATE_MODE`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新策略（1、匹配更新 2、更新、插入 3、增量插入，默认：2）' ,
`E_DS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '引擎数据源ID' ,
`STATUS`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（1：未建，2：已建）' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_IM_MODEL_DELFLG` (`DEL_FLG`) USING BTREE ,
INDEX `IDX_IM_MODEL_DELFLG_NAME` (`DEL_FLG`, `NAME`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='交互建模-模型配置'

;

-- ----------------------------
-- Table structure for im_model_filter_column
-- ----------------------------
CREATE TABLE `im_model_filter_column` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MODEL_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型ID' ,
`SEQ`  decimal(3,0) NOT NULL COMMENT '序号' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称((英文)' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明' ,
`TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型' ,
`LENGTH`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '长度' ,
`IS_NEED`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否必输（0：是；1：否）' ,
`DEFAULT_VAL`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值' ,
`OPERATOR`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作符' ,
`LABEL`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '别名' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_IM_MODEL_FILTER_MODELID` (`MODEL_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='交互模型-模型过滤字段'

;

-- ----------------------------
-- Table structure for im_model_mapping
-- ----------------------------
CREATE TABLE `im_model_mapping` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`MODEL_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型ID' ,
`SEQ`  decimal(3,0) NOT NULL COMMENT '序号' ,
`NAME`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源元数据字段名称' ,
`COL_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标元数据字段ID' ,
`NOTE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' ,
`TYPE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源元数据字段类型' ,
`LENGTH`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源元数据字段长度' ,
`DESCRIPTION`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '源元数据字段说明' ,
`INDEXED`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '索引（0：是；1：否）' ,
`UNIQUEKEY`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主键（0：是；1：否）' ,
`STORED`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储（0：是；1：否）' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_IM_MODEL_MAPPING_MODELID` (`MODEL_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='交互建模-模型字段映射'

;

-- ----------------------------
-- Table structure for im_model_update_key
-- ----------------------------
CREATE TABLE `im_model_update_key` (
`PK_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`COL_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标元数据字段ID' ,
`MODEL_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型ID' ,
PRIMARY KEY (`PK_ID`),
INDEX `IDX_IM_MODEL_UPDATEKEY_MODELID` (`MODEL_ID`) USING BTREE
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='交互建模-模型更新键值'

;
