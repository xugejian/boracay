package com.hex.goframe.engine.das;

import com.alibaba.druid.util.JdbcUtils;
import httl.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hex on 15/12/7.
 */
public class GFDbSchemaUpdate implements BeanNameAware, ApplicationContextAware {
    private static final Logger LOG = LogManager.getLogger (GFDbSchemaUpdate.class);

    private DataSourceManager dataSourceManager;

    private boolean enabled = true;

    private String databaseType = "oracle";

    private String checkTable = "";

    // --------------------- 2019-12-27 by Junjie.M --------------------------
    private String checkSql = "";

    private String separator = ";";
    // --------------------- END --------------------------

    private String[] schemaSqlResources;

    private static String[] JDBC_METADATA_TABLE_TYPES = new String[]{"TABLE"};

    private String beanName;

    private String dsName = GFDataSource.DEFAULT_DS_NAME;

    private DataSource dataSource;

    private ApplicationContext applicationContext;

    private boolean isTablePresent(String tableName) throws Exception {
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection ();) {
            DatabaseMetaData md = conn.getMetaData ();
            if ("pgsql".equals (databaseType)) {
                tableName = tableName.toLowerCase ();
            }
            rs = md.getTables ((String) null, (String) null, tableName, JDBC_METADATA_TABLE_TYPES);
            while (rs.next ()) {
                String table = rs.getString (3);
                if (tableName.equalsIgnoreCase (table)) {
                    return true;
                }
            }
            return false;
        } finally {
            JdbcUtils.close (rs);
        }
    }

    // --------------------- 2019-12-27 by Junjie.M --------------------------
    private boolean isSqlPresent(String checkSql) throws Exception {
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection ();
             Statement stmt = conn.createStatement ();) {
            rs = stmt.executeQuery (checkSql);
            while (rs.next ()) {
                return true;
            }
            return false;
        } finally {
            JdbcUtils.close (rs);
        }
    }
    // --------------------- END --------------------------

    private void executeSchemaResource(String operation, String component, Resource resource) {
        String sqlStatement = null;
        String exceptionSqlStatement = null;
        BufferedReader reader = null;
        GFDataSource dataSource = this.dataSourceManager.getDataSource (this.dsName, true);
        try (Connection conn = dataSource.getConnection ();) {
            Exception exception = null;
            reader = new BufferedReader (new InputStreamReader (resource.getInputStream ()));
            String line = this.readNextTrimmedLine (reader);
            List<String> logLines;
            for (logLines = new ArrayList<String> (); line != null; line = this.readNextTrimmedLine (reader)) {
                if (line.startsWith ("#")) {
                    logLines.add (line.substring (1));
                } else if (line.startsWith ("--")) {
                    logLines.add (line.substring (2));
                } else if (line.length () > 0) {
                    // --------------------- 2019-12-27 by Junjie.M --------------------------
                    if (line.endsWith (this.separator)) {
                        sqlStatement = this.addSqlStatementPiece (sqlStatement, line.substring (0,
                                line.length () - this.separator.length ()));
                        // --------------------- END --------------------------
                        Statement jdbcStatement = conn.createStatement ();
                        try {
                            exceptionSqlStatement = sqlStatement;
                            logLines.add (sqlStatement);
                            jdbcStatement.execute (sqlStatement);
                        } catch (Exception e2) {
                            if (exception == null) {
                                exception = e2;
                            }
                            LOG.fatal (sqlStatement, e2);
                        } finally {
                            sqlStatement = null;
                            JdbcUtils.close (jdbcStatement);
                        }
                    } else {
                        sqlStatement = this.addSqlStatementPiece (sqlStatement, line);
                    }
                }
            }
            if (exception != null) {
                throw exception;
            } else {
                LOG.info ("Succcess update dbschema!operation={},componment={}", operation, component);
            }
        } catch (Exception e) {
            throw new RuntimeException ("operation:" + operation + ",sql:" + exceptionSqlStatement, e);
        } finally {
            IOUtils.closeQuietly (reader);
        }
    }

    protected String addSqlStatementPiece(String sqlStatement, String line) {
        return sqlStatement == null ? line : sqlStatement + " \n" + line;
    }

    protected String readNextTrimmedLine(BufferedReader reader) throws IOException {
        String line = reader.readLine ();
        if (line != null) {
            line = line.trim ();
        }
        return line;
    }

    @Transactional
    public void init() throws Exception {

        // --------------------- 2019-12-27 by Junjie.M --------------------------
        if (!this.enabled || null == this.schemaSqlResources
                || (StringUtils.isEmpty (this.checkTable) && StringUtils.isEmpty (this.checkSql))) {
            return;
        }
        // --------------------- END --------------------------

        if (null == this.dataSourceManager) {
            this.dataSourceManager = this.applicationContext.getBean (DataSourceManager.class);
        }

        if (null == dataSource) {
            this.dataSource = this.dataSourceManager.getDataSource (this.dsName, true);
        }

        if (StringUtils.isNotEmpty (this.checkTable) && this.isTablePresent (this.checkTable)) {
            return;
        }

        // --------------------- 2019-12-27 by Junjie.M --------------------------
        if (StringUtils.isNotEmpty (this.checkSql) && this.isSqlPresent (this.checkSql)) {
            return;
        }
        // --------------------- END --------------------------

        for (String schemaSqlResource : schemaSqlResources) {
            Resource[] resources = this.applicationContext.getResources (schemaSqlResource);
            for (Resource resource : resources) {
                this.executeSchemaResource ("create", this.beanName, resource);
            }
        }
    }

    public void setSchemaSqlResources(String[] schemaSqlResources) {
        this.schemaSqlResources = schemaSqlResources;
    }

    public String getCheckTable() {
        return checkTable;
    }

    public void setCheckTable(String checkTable) {
        this.checkTable = checkTable;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public void setDataSourceManager(DataSourceManager dataSourceManager) {
        this.dataSourceManager = dataSourceManager;
    }

    public String getCheckSql() {
        return checkSql;
    }

    public void setCheckSql(String checkSql) {
        this.checkSql = checkSql;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
