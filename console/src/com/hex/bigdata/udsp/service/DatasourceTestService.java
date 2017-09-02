package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.dto.ComDatasourcePropsView;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.iq.service.IqProviderService;
import com.hex.bigdata.udsp.olq.service.OlqProviderService;
import com.hex.bigdata.udsp.rc.util.RcConstant;
import com.hex.bigdata.udsp.rts.service.RtsProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试数据源的服务
 */
@Service
public class DatasourceTestService {
    @Autowired
    private IqProviderService iqProviderService;
    @Autowired
    private OlqProviderService olqProviderService;
    @Autowired
    private RtsProviderService rtsProviderService;

    /**
     * 测试数据源
     *
     * @param comDatasourcePropsView
     * @return
     */
    public boolean testDatasource(ComDatasourcePropsView comDatasourcePropsView) {
        Datasource datasource = new Datasource(comDatasourcePropsView.getComDatasource(), comDatasourcePropsView.getComPropertiesList());
        if (RcConstant.UDSP_SERVICE_TYPE_IQ.equalsIgnoreCase(comDatasourcePropsView.getComDatasource().getModel())) {
            return iqProviderService.testDatasource(datasource);
        } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ.equalsIgnoreCase(comDatasourcePropsView.getComDatasource().getModel())) {
            return olqProviderService.testDatasource(datasource);
        } else if (RcConstant.UDSP_SERVICE_TYPE_RTS.equalsIgnoreCase(comDatasourcePropsView.getComDatasource().getModel())) {
            return rtsProviderService.testDatasource(datasource);
        }
        return false;
    }
}
