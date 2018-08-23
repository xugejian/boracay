package com.hex.bigdata.udsp.rts.service;

import com.hex.bigdata.udsp.rts.dao.RtsMetadataColMapper;
import com.hex.bigdata.udsp.rts.model.RtsMetadataCol;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tomnic on 2017/2/28.
 */
@Service
public class RtsMetadataColService extends BaseService {

    private static Logger logger = LogManager.getLogger(RtsMetadataService.class);

    @Autowired
    private RtsMetadataColMapper rtsMetadataColMapper;
    @Autowired
    private RtsMetadataService rtsMetadataService;

    @Transactional
    public String insert(RtsMetadataCol rtsMetadataCol) {
        String pkId = Util.uuid();
        rtsMetadataCol.setPkId(pkId);
        if (rtsMetadataColMapper.insert(pkId, rtsMetadataCol)) {
            return pkId;
        }
        return "";
    }

    @Transactional
    public boolean update(RtsMetadataCol rtsMetadataCol) {
        return rtsMetadataColMapper.update(rtsMetadataCol.getPkId(), rtsMetadataCol);
    }

    @Transactional
    public boolean delete(String pkId) {
        return rtsMetadataColMapper.delete(pkId);
    }

    public RtsMetadataCol select(String pkId) {
        return rtsMetadataColMapper.select(pkId);
    }

    public List<RtsMetadataCol> select(RtsMetadataCol rtsMetadataCol, Page page) {
        return rtsMetadataColMapper.select(rtsMetadataCol, page);
    }

    public List<RtsMetadataCol> select(RtsMetadataCol rtsMetadataCol) {
        return rtsMetadataColMapper.select(rtsMetadataCol);
    }


    @Transactional
    public boolean insertList(String fkId, List<RtsMetadataCol> rtsMetadataCols) {
        for (RtsMetadataCol rtsMetadataCol : rtsMetadataCols) {
            rtsMetadataCol.setPkId(Util.uuid());
            rtsMetadataCol.setMdId(fkId);
        }
        return rtsMetadataColMapper.insertList(fkId, rtsMetadataCols);
    }

    public List<RtsMetadataCol> selectByMdId(String mdId){
        return rtsMetadataColMapper.selectByMdId(mdId);
    }

    public boolean deleteByMdId(String mdId) {
        return rtsMetadataColMapper.deleteByMdId(mdId);
    }

    public List<RtsMetadataCol> selectByProducerPkid(String appId) {
        return rtsMetadataColMapper.selectByProducerPkid(appId);
    }

    public boolean hasUsed(String pkId) {
        return rtsMetadataService.hasUsed(pkId);
    }
}
