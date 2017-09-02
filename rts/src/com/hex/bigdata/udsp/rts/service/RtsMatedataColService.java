package com.hex.bigdata.udsp.rts.service;

import com.hex.bigdata.udsp.rts.dao.RtsMatedataColMapper;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
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
public class RtsMatedataColService extends BaseService {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsMatedataService.class);

    @Autowired
    private RtsMatedataColMapper rtsMatedataColMapper;

    @Autowired
    private RtsMatedataService rtsMatedataService;

    @Transactional
    public String insert(RtsMatedataCol rtsMatedataCol) {
        String pkId = Util.uuid();
        rtsMatedataCol.setPkId(pkId);
        if (rtsMatedataColMapper.insert(pkId, rtsMatedataCol)) {
            return pkId;
        }
        return "";
    }

    @Transactional
    public boolean update(RtsMatedataCol rtsMatedataCol) {
        return rtsMatedataColMapper.update(rtsMatedataCol.getPkId(), rtsMatedataCol);
    }

    @Transactional
    public boolean delete(String pkId) {
        return rtsMatedataColMapper.delete(pkId);
    }

    public RtsMatedataCol select(String pkId) {
        return rtsMatedataColMapper.select(pkId);
    }

    public List<RtsMatedataCol> select(RtsMatedataCol rtsMatedataCol, Page page) {
        return rtsMatedataColMapper.select(rtsMatedataCol, page);
    }

    public List<RtsMatedataCol> select(RtsMatedataCol rtsMatedataCol) {
        return rtsMatedataColMapper.select(rtsMatedataCol);
    }


    @Transactional
    public boolean insertList(String fkId, List<RtsMatedataCol> rtsMatedataCols) {
        for (RtsMatedataCol rtsMatedataCol : rtsMatedataCols) {
            rtsMatedataCol.setPkId(Util.uuid());
            rtsMatedataCol.setMdId(fkId);
        }
        return rtsMatedataColMapper.insertList(fkId, rtsMatedataCols);
    }

    public List<RtsMatedataCol> selectByMdId(String mdId){
        return this.rtsMatedataColMapper.selectByMdId(mdId);
    }

    /**
     * 根据元数据Id删除元数据累
     * @param mdId 元数据ID
     * @return
     */
    public boolean deleteByMdId(String mdId) {
        return rtsMatedataColMapper.deleteByMdId(mdId);
    }

    public List<RtsMatedataCol> selectByProducerPkid(String appId) {

        return rtsMatedataColMapper.selectByProducerPkid(appId);
    }

    public boolean hasUsed(String pkId) {

        return rtsMatedataService.hasUsed(pkId);
    }
}
