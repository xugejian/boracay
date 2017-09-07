package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.dao.ComPropertiesMapper;
import com.hex.bigdata.udsp.im.dao.ImModelFilterColMapper;
import com.hex.bigdata.udsp.im.dao.ImModelMapper;
import com.hex.bigdata.udsp.im.dao.ImModelMappingMapper;
import com.hex.bigdata.udsp.im.dto.ImModelView;
import com.hex.bigdata.udsp.im.model.ImModel;
import com.hex.bigdata.udsp.im.model.ImModelFilterCol;
import com.hex.bigdata.udsp.im.model.ImModelViews;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 交互建模处理类
 * Created by jintian on 2017/9/6.
 */
@Service
public class ImModelService {

    @Autowired
    private ImModelMapper imModelMapper;

    @Autowired
    private ImModelFilterColMapper imModelFilterColMapper;

    @Autowired
    private ImModelMappingMapper imModelMappingMapper;

    @Autowired
    private ComPropertiesMapper comPropertiesMapper;

    public boolean insert(ImModelViews imModelViews){
        String pkId = Util.uuid();
        ImModel model = imModelViews.getImModel();
        model.setPkId(pkId);
        if (!imModelMapper.insert(pkId, model)) {
            return  false;
        }
        if(!imModelFilterColMapper.insertFilterCols(pkId,imModelViews.getImModelFilterCols())){
            return false;
        }
        if(!imModelMappingMapper.insertModelMappings(pkId,imModelViews.getImModelMappings())){
            return false;
        }
        if(!comPropertiesMapper.insertModelComProperties(pkId,imModelViews.getComPropertiesList())){
            return false;
        }
        return true;
    }

    public boolean update(ImModelViews imModelViews) {
        ImModel model = imModelViews.getImModel();
        //获取模型主键
        String pkId = model.getPkId();
        if (!imModelMapper.update(pkId, model)) {
            return  false;
        }
        if(!imModelFilterColMapper.updateList(pkId,imModelViews.getImModelFilterCols())){
            return false;
        }
        if(!imModelMappingMapper.insertModelMappings(pkId,imModelViews.getImModelMappings())){
            return false;
        }
        if(!comPropertiesMapper.insertModelComProperties(pkId,imModelViews.getComPropertiesList())){
            return false;
        }
        return true;
    }

    public List<ImModelView> selectPage(ImModelView imModelView, Page page) {

        return imModelMapper.selectPage(imModelView,page);
    }

    public boolean delete(ImModel[] imModels) {

        for(ImModel imModel : imModels){
            imModel.setDelFlg("1");
            if(!imModelMapper.update(imModel.getPkId(),imModel)){
                return false;
            }
        }
        return true;
    }
}
