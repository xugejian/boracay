package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.mc.dao.McConsumeLogMapper;
import com.hex.bigdata.udsp.mc.dto.McConsumeLogView;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by junjiem on 2017-2-23.
 */
@Service
public class McConsumeLogService extends BaseService {
    @Autowired
    private McConsumeLogMapper mcConsumeLogMapper;

    @Transactional
    public String insert(McConsumeLog mcConsumeLog) {
        if (mcConsumeLogMapper.insert(mcConsumeLog.getPkId(), mcConsumeLog)) {
            return mcConsumeLog.getPkId();
        }
        return "";
    }

    public McConsumeLog select(String pkId) {
        return mcConsumeLogMapper.select(pkId);
    }

    public List<McConsumeLog> select(McConsumeLogView mcConsumeLogView, Page page) {
        return mcConsumeLogMapper.select(mcConsumeLogView, page);
    }

    public List<McConsumeLog> select(McConsumeLogView mcConsumeLogView) {
        return mcConsumeLogMapper.select(mcConsumeLogView);
    }
}
