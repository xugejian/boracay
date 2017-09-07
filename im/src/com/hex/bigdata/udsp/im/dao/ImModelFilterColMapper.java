package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImModelFilterCol;
import com.hex.goframe.util.Util;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ImModelFilterColMapper  extends SyncMapper<ImModelFilterCol> {

    @Override
    protected boolean insertExe(ImModelFilterCol imModelFilterCol) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.im.dao.insert",imModelFilterCol) == 1;
    }

    @Override
    protected boolean updateExe(ImModelFilterCol imModelFilterCol) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImModelFilterCol selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ImModelFilterCol> selectListExe(String id) {
        return null;
    }

    public boolean insertFilterCols(String pkId, List<ImModelFilterCol> imModelFilterCols) {
        String imModelFilterPkid = "";
        for(ImModelFilterCol imModelFilterCol:imModelFilterCols){
            imModelFilterCol.setModelId(pkId);

            imModelFilterPkid = Util.uuid();
            imModelFilterCol.setPkId(imModelFilterPkid);
            // 当有一个插入失败则算做失败
            if(!insert(imModelFilterPkid,imModelFilterCol)){
                return  false;
            }
        }
        return true;
    }
}