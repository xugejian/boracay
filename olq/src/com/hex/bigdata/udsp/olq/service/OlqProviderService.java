package com.hex.bigdata.udsp.olq.service;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.olq.model.OLQQuerySql;
import com.hex.bigdata.udsp.olq.provider.Provider;
import com.hex.bigdata.udsp.olq.provider.model.OLQRequest;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponseFetch;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by junjiem on 2017-3-2.
 */
@Service
public class OlqProviderService extends BaseService {
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private ComPropertiesService comPropertiesService;
    @Autowired
    private GFDictMapper gfDictMapper;


    /**
     * 查询
     *
     * @param dsId
     * @param olqQuerySql
     * @return
     */
    public OLQResponse select(String dsId, OLQQuerySql olqQuerySql) {
        Datasource datasource = getDatasource(dsId);
        OLQRequest request = new OLQRequest(datasource, olqQuerySql);
        Provider provider = getProviderImpl(datasource);
        OLQResponse response = provider.execute(request);
        //java.sql.SQLException 未执行语句句柄，则放到各个实现类里面去
        //response.setColumns(this.putColumnIntoMap(response.getMetadata()));
        return response;
    }

    /**
     * 查询
     *
     * @param dsId
     * @param sql
     * @return
     */
    public OLQResponse select(String dsId, String sql) {
        Datasource datasource = getDatasource(dsId);
        OLQRequest request = new OLQRequest(datasource, new OLQQuerySql(sql));
        Provider provider = getProviderImpl(datasource);
        OLQResponse response = provider.execute(request);
        return response;
    }


    /**
     * 元数据列信息插入到Map
     * @param rsmd
     * @return
     */
    private LinkedHashMap<String,String> putColumnIntoMap(ResultSetMetaData rsmd){
        LinkedHashMap<String,String> columnMap = new LinkedHashMap<>();
        try {
            for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
                columnMap.put(rsmd.getColumnName(i),rsmd.getColumnTypeName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnMap;
    }

    /**
     * 查询（结果集一批批抽取）
     *
     * @param dsId
     * @param sql
     * @return
     */
    public OLQResponseFetch selectFetch(String dsId, String sql) {
        Datasource datasource = getDatasource(dsId);
        OLQRequest request = new OLQRequest(datasource, new OLQQuerySql(sql));
        Provider provider = getProviderImpl(datasource);
        OLQResponseFetch response = provider.executeFetch(request);
        return response;
    }

    /**
     * 获取数据源
     *
     * @param dsId
     * @return
     */
    private Datasource getDatasource(String dsId) {
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comPropertiesList = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comPropertiesList);
        return datasource;
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(Datasource datasource) {
        Provider provider = getProviderImpl(datasource);
        return provider.testDatasource(datasource);
    }

    /**
     * 得到生产接口的实例
     *
     * @param datasource
     * @return
     */
    private Provider getProviderImpl(Datasource datasource) {
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey("OLQ_IMPL_CLASS", datasource.getType());
            implClass = gfDict.getDictName();
        }
        return (Provider) ObjectUtil.newInstance(implClass);
    }

    /**
     * 得到生产接口的实例
     * @param datasource
     * @return
     */
    public Provider getProviderImpl(ComDatasource datasource) {
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey("OLQ_IMPL_CLASS", datasource.getType());
            implClass = gfDict.getDictName();
        }
        return (Provider) ObjectUtil.newInstance(implClass);
    }
}
