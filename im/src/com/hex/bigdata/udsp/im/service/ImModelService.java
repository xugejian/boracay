package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.dao.ComDatasourceMapper;
import com.hex.bigdata.udsp.common.dao.ComPropertiesMapper;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.dao.ImModelFilterColMapper;
import com.hex.bigdata.udsp.im.dao.ImModelMapper;
import com.hex.bigdata.udsp.im.dao.ImModelMappingMapper;
import com.hex.bigdata.udsp.im.dao.ImModelUpdateKeyMapper;
import com.hex.bigdata.udsp.im.dto.ImModelView;
import com.hex.bigdata.udsp.im.model.ImModel;
import com.hex.bigdata.udsp.im.model.ImModelUpdateKey;
import com.hex.bigdata.udsp.im.model.ImModelViews;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private ImModelUpdateKeyMapper imModelUpdateKeyMapper;

    @Autowired
    private ImProviderService imProviderService;

    @Autowired
    private ComDatasourceMapper comDatasourceMapper;

    @Transactional
    public boolean insert(ImModelViews imModelViews) throws Exception{
        String pkId = Util.uuid();
        ImModel model = imModelViews.getImModel();
        model.setPkId(pkId);
        if (!imModelMapper.insert(pkId, model)) {
            throw new RuntimeException("保存配置栏基础信息异常");
        }
        if(!imModelFilterColMapper.insertFilterCols(pkId,imModelViews.getImModelFilterCols())){
            throw new RuntimeException("保存过滤字段异常");
        }
        if(!imModelMappingMapper.insertModelMappings(pkId,imModelViews.getImModelMappings())){
            throw new RuntimeException("保存映射字段异常");
        }
        if(!comPropertiesMapper.insertModelComProperties(pkId,imModelViews.getComPropertiesList())){
            throw new RuntimeException("保存参数配置栏信息异常");
        }
        //添加更新字段
        String updateKeys = model.getUpdateKey();
        if(StringUtils.isNotBlank(updateKeys)){
            String[] updateKeyArray = updateKeys.split(",");
            String updateKeyId;
            for(String updateKey : updateKeyArray){
                ImModelUpdateKey imModelUpdateKey = new ImModelUpdateKey();
                updateKeyId = Util.uuid();
                //设置插入的ID
                imModelUpdateKey.setPkId(updateKeyId);
                imModelUpdateKey.setModelId(pkId);
                imModelUpdateKey.setColId(updateKey);
                if(!imModelUpdateKeyMapper.insert(updateKeyId,imModelUpdateKey)){
                    throw new RuntimeException("添加更新主键异常");
                }
            }
        }
        return true;
    }

    @Transactional
    public boolean update(ImModelViews imModelViews) throws Exception{
        ImModel model = imModelViews.getImModel();
        //获取模型主键
        String pkId = model.getPkId();
        if (!imModelMapper.update(pkId, model)) {
            //添加运行时异常，使事务回滚
            throw new RuntimeException("更新配置栏基础信息异常");
        }

        if(!imModelFilterColMapper.deleteList(pkId) ||!imModelFilterColMapper.insertFilterCols(pkId,imModelViews.getImModelFilterCols())){
            throw new RuntimeException("更新过滤字段异常");
        }

        if(!imModelMappingMapper.deleteList(pkId) || !imModelMappingMapper.insertModelMappings(pkId,imModelViews.getImModelMappings())){
            throw new RuntimeException("更新映射字段异常");
        }

        if(!comPropertiesMapper.deleteList(pkId) || !comPropertiesMapper.insertModelComProperties(pkId,imModelViews.getComPropertiesList())){
            throw new RuntimeException("更新参数配置栏信息异常");
        }

        if(!imModelUpdateKeyMapper.deleteList(pkId)){
            throw new RuntimeException("更新更新主键异常1");
        }
        //添加更新字段
        String updateKeys = model.getUpdateKey();
        if(StringUtils.isNotBlank(updateKeys)){
            String[] updateKeyArray = updateKeys.split(",");
            String updateKeyId;
            for(String updateKey : updateKeyArray){
                ImModelUpdateKey imModelUpdateKey = new ImModelUpdateKey();
                updateKeyId = Util.uuid();
                //设置插入的ID
                imModelUpdateKey.setPkId(updateKeyId);
                imModelUpdateKey.setModelId(pkId);
                imModelUpdateKey.setColId(updateKey);
                if(!imModelUpdateKeyMapper.insert(updateKeyId,imModelUpdateKey)){
                    throw new RuntimeException("更新更新主键异常2");
                }
            }
        }
        return true;
    }

    public List<ImModelView> selectPage(ImModelView imModelView, Page page) {

        return imModelMapper.selectPage(imModelView,page);
    }

    public boolean delete(ImModel[] imModels) throws Exception {

        for(ImModel imModel : imModels){
            imModel.setDelFlg("1");
            // 进行逻辑删除
            if(!imModelMapper.update(imModel.getPkId(),imModel)){
                //跑出异常，进行回滚
                throw new RuntimeException("删除失败,删除失败的模型名称为：" + imModel.getName());
            }
        }
        return true;
    }

    public ImModel selectByPkId(String pkId) {
        return imModelMapper.select(pkId);
    }

    public ImModel selectByName(String modelName) {
        return imModelMapper.selectByName(modelName);
    }

    public List<MetadataCol> getSrcMateData(Property[] properties, String srcDataSourceId) {
        //根据元数据获取相关信息
        Model model = new Model(Arrays.asList(properties));
        ComDatasource comDatasource= comDatasourceMapper.select(srcDataSourceId);
        Datasource datasource = new Datasource(comDatasource,new ArrayList<ComProperties>());
        // 由于该实现类和模型中的实现类不一样故制为空
        datasource.setImplClass("");
        model.setProperties(Arrays.asList(properties));
        model.setSourceDatasource(datasource);
        return imProviderService.getCloumnInfo(model);
    }
}
