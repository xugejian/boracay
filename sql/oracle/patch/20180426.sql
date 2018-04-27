
-- 修改表的字段名称 DESCRIBE 改为 DESCRIPTION
alter table COM_DATASOURCE rename column DESCRIBE to DESCRIPTION;
alter table COM_PROPERTIES rename column DESCRIBE to DESCRIPTION;
alter table IM_METADATA rename column DESCRIBE to DESCRIPTION;
alter table IM_MODEL rename column DESCRIBE to DESCRIPTION;
alter table IM_MODEL_FILTER_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IM_METADATA_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IM_MODEL_MAPPING rename column DESCRIBE to DESCRIPTION;
alter table IQ_APPLICATION rename column DESCRIBE to DESCRIPTION;
alter table IQ_APPLICATION_RETURN_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IQ_APPLICATION_ORDER_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IQ_APPLICATION_QUERY_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table IQ_METADATA rename column DESCRIBE to DESCRIPTION;
alter table IQ_METADATA_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table OLQ_APPLICATION rename column DESCRIBE to DESCRIPTION;
alter table MM_MODEL_INFO rename column DESCRIBE to DESCRIPTION;
alter table MM_APPLICATION rename column DESCRIBE to DESCRIPTION;
alter table MM_APPLICATION_RETURN_PARAM rename column DESCRIBE to DESCRIPTION;
alter table MM_APPLICATION_EXECUTE_PARAM rename column DESCRIBE to DESCRIPTION;
alter table MM_MODEL_PARAM rename column DESCRIBE to DESCRIPTION;
alter table RC_SERVICE rename column DESCRIBE to DESCRIPTION;
alter table RTS_CUSTOMER_CONFIG rename column DESCRIBE to DESCRIPTION;
alter table RTS_METADATA rename column DESCRIBE to DESCRIPTION;
alter table RTS_METADATA_COLUMN rename column DESCRIBE to DESCRIPTION;
alter table RTS_PRODUCRER_CONFIG rename column DESCRIBE to DESCRIPTION;

-- 修改表的字段名称 PRIMARY 改为 UNIQUEKEY
alter table IM_METADATA_COLUMN rename column PRIMARY to UNIQUEKEY;
alter table IM_MODEL_MAPPING rename column PRIMARY to UNIQUEKEY;