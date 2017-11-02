--修改OLQ_APPLICATION表的olq_sql字段的字段类型为clob start-----
-- Add/modify columns
alter table OLQ_APPLICATION add olq_sql2 clob not null;
-- Add comments to the columns 
comment on column OLQ_APPLICATION.olq_sql2
  is '应用配置的SQL语句';
-- Drop columns
alter table OLQ_APPLICATION drop column olq_sql;
-- Add/modify columns
alter table OLQ_APPLICATION rename column olq_sql2 to OLQ_SQL;
--修改OLQ_APPLICATION表的olq_sql字段的字段类型为clob end-----


-- Add/modify columns
alter table IQ_APPLICATION_ORDER_COLUMN add note VARCHAR2(4000);
-- Add comments to the columns
comment on column IQ_APPLICATION_ORDER_COLUMN.note
  is '备注';

-- Add/modify columns
alter table IQ_APPLICATION_QUERY_COLUMN add note VARCHAR2(4000);
-- Add comments to the columns
comment on column IQ_APPLICATION_QUERY_COLUMN.note
  is '备注';

-- Add/modify columns
alter table IQ_APPLICATION_RETURN_COLUMN add note VARCHAR2(4000);
-- Add comments to the columns
comment on column IQ_APPLICATION_RETURN_COLUMN.note
  is '备注';

