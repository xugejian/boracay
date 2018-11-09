package com.hex.bigdata.udsp.olq.provider.impl;

import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.olq.provider.model.OlqQuerySql;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by PC on 2017/6/26.
 */
//@Component("com.hex.bigdata.udsp.olq.provider.impl.OracleProvider")
public class OracleProvider extends JdbcProvider {

    private Logger logger = LogManager.getLogger(OracleProvider.class);

    protected OlqQuerySql getPageSql(String sql, Page page) {
        OlqQuerySql olqQuerySql = new OlqQuerySql(sql);
        if (page == null || !sql.toUpperCase().trim().contains("SELECT")) {
            return olqQuerySql;
        }
        // 分页sql组装
        int pageSize = page.getPageSize();
        int pageIndex = page.getPageIndex();
        pageIndex = (pageIndex == 0 ? 1 : pageIndex);
        Integer startRow = (pageIndex - 1) * pageSize;
        Integer endRow = pageSize * pageIndex;
        String pageSql = "SELECT * FROM (" + sql + ") UDSP_VIEW WHERE ROWNUM >=" + startRow + " AND ROWNUM <= " + endRow;
        olqQuerySql.setPageSql(pageSql);
        // 总记录数查询SQL组装
        String totalSql = "SELECT COUNT(1) FROM (" + sql + ") UDSP_VIEW";
        olqQuerySql.setTotalSql(totalSql);
        // page设置
        olqQuerySql.setPage(page);
        logger.debug("配置的源SQL:\n" + olqQuerySql.getOriginalSql());
        logger.debug("分页查询SQL:\n" + olqQuerySql.getPageSql());
        logger.debug("查询总数SQL:\n" + olqQuerySql.getTotalSql());
        return olqQuerySql;
    }
}
