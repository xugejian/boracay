package com.hex.bigdata.udsp.iq.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.iq.provider.model.IqRequest;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;

/**
 * Created by junjiem on 2017-2-15.
 */
public interface Provider {

    /**
     * 查询
     *
     * @param request
     * @return
     */
    IqResponse query(IqRequest request);

    /**
     * 分页查询
     *
     * @param request
     * @param pageIndex
     * @param pageSize
     * @return
     */
    IqResponse query(IqRequest request, int pageIndex, int pageSize);

    /**
     * 测试
     *
     * @param datasource
     * @return
     */
    boolean testDatasource(Datasource datasource);

}
