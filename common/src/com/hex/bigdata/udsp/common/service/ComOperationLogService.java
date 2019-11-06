package com.hex.bigdata.udsp.common.service;

import com.hex.bigdata.udsp.common.dao.ComOperationLogMapper;
import com.hex.bigdata.udsp.common.dto.ComOperationLogView;
import com.hex.bigdata.udsp.common.model.ComOperationLog;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by junjiem on 2017-2-23.
 */
@Service
public class ComOperationLogService extends BaseService {

    @Autowired
    private ComOperationLogMapper comOperationLogMapper;

    @Transactional
    public boolean insert(ComOperationLog comOperationLog) {
        String pkId = Util.uuid();
        comOperationLog.setPkId(pkId);
        return comOperationLogMapper.insert(comOperationLog);
    }

    public ComOperationLog select(String pkId) {
        return comOperationLogMapper.select(pkId);
    }

    public List<ComOperationLog> select(ComOperationLogView comOperationLogView, Page page) {
        return comOperationLogMapper.select(comOperationLogView, page);
    }

    public List<ComOperationLog> select(ComOperationLogView comOperationLogView) {
        return comOperationLogMapper.select(comOperationLogView);
    }
}
