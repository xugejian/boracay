package com.hex.bigdata.udsp.common.aggregator.task;

import com.hex.bigdata.udsp.common.aggregator.util.H2SqlUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * H2数据定时清理
 */
@Component
public class H2DataCleanTask {
    private static Logger logger = LogManager.getLogger (H2DataCleanTask.class);

    @Autowired
    @Qualifier("h2DataSource")
    private BasicDataSource h2DataSource;

    /**
     * 清空H2数据库数据
     */
    @Scheduled(cron = "${aggregator.h2.clean.expression:0 0 0 * * ?}")
    public void cleanH2Database() {
        try (Connection conn = h2DataSource.getConnection ();
             Statement stmt = conn.createStatement ();) {
            String resetDB = H2SqlUtil.resetDB();
            logger.info ("Execute: {}", resetDB);
            stmt.execute (resetDB);
        } catch (SQLException e) {
            e.printStackTrace ();
            logger.error ("", e);
        }
    }
}
