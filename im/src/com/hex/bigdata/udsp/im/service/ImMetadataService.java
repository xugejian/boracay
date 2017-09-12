package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.PropertyUtil;
import com.hex.bigdata.udsp.im.dao.ImMetadataMapper;
import com.hex.bigdata.udsp.im.model.ImMetadata;
import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import com.hex.bigdata.udsp.im.dto.ImMetadataDto;
import com.hex.bigdata.udsp.im.dto.ImMetadataView;
import com.hex.bigdata.udsp.im.provider.constant.MetadataType;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.util.ImUtil;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hj on 2017-9-5.
 */
@Service
public class ImMetadataService extends BaseService {
    @Autowired
    private ImMetadataMapper imMetadataMapper;
    @Autowired
    private ImMetadataColService imMetadataColService;
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private ComPropertiesService comPropertiesService;
    @Autowired
    private ImProviderService imProviderService;
    @Transactional
    public String insert(ImMetadata imMetadata) {
        String pkId = Util.uuid();
        imMetadata.setPkId(pkId);
        if("1".equals(imMetadata.getType())){
            imMetadata.setStatus("2"); //外表状态为已建
        }
        imMetadata.setStatus("1"); //1未建 2已建
        if (imMetadataMapper.insert(imMetadata.getPkId(), imMetadata)) {
            return pkId;
        }
        return "";
    }

    @Transactional
    public String insert(ImMetadataDto imMetadataDto) {
        ImMetadata imMetadata = imMetadataDto.getImMetadata();
        String pkId = this.insert(imMetadata);
        if (StringUtils.isBlank(pkId)) {
            return "";
        }

        comPropertiesService.insertList(pkId, imMetadataDto.getComPropertiesList());

        List<ImMetadataCol> imMetadataCols = imMetadataDto.getImMetadataColList();
        for(ImMetadataCol imMetadataCol : imMetadataCols){
            imMetadataCol.setMdId(pkId);
            imMetadataColService.insert(imMetadataCol);
        }
        return pkId;
    }

    @Transactional
    public boolean update(ImMetadataDto imMetadataDto) {
        ImMetadata imMetadata = imMetadataDto.getImMetadata();
        String pkId = imMetadata.getPkId();
        if (!imMetadataMapper.update(pkId, imMetadata)) {
            return false;
        }
        if (!comPropertiesService.deleteByFkId(pkId)) {
            return false;
        }
        comPropertiesService.insertList(pkId, imMetadataDto.getComPropertiesList());
        if (!imMetadataColService.deleteByMdId(pkId)) {
            return false;
        }
        List<ImMetadataCol> imMetadataCols = imMetadataDto.getImMetadataColList();
        for(ImMetadataCol imMetadataCol : imMetadataCols){
            imMetadataCol.setMdId(pkId);
            imMetadataColService.insert(imMetadataCol);
        }
        return true;
    }

    public List<ImMetadataView> select(ImMetadataView imMetadataView, Page page) {
        return imMetadataMapper.select(imMetadataView, page);
    }

    public ImMetadata select(String pkId) {
        return imMetadataMapper.select(pkId);
    }

    @Transactional
    public boolean delete(String pkId) {
        return imMetadataMapper.delete(pkId);
    }

    @Transactional
    public boolean delete(ImMetadata[] imMetadatas) {
        boolean status = true;
        for (ImMetadata imMetadata : imMetadatas) {
            String pkId = imMetadata.getPkId();
            if (!this.delete(pkId)) {
                status = false;
                break;
            }
        }
        return status;
    }

    public boolean checkName(String name) {
        return imMetadataMapper.selectByName(name) != null;
    }

    public List<MetadataCol> getCloumnInfo(String dsId, String tbName){
        //todo 验证
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comProperties = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comProperties);
        List<Property> prop = new ArrayList<>();
        Metadata metadata = new Metadata(prop);
        metadata.setType(MetadataType.EXTERNAL);
        metadata.setTbName(tbName);
        metadata.setDatasource(datasource);
        List<MetadataCol> list = imProviderService.getCloumnInfo(metadata);
        return list;
    }

    @Transactional
    public boolean createTable(String pkId) throws Exception {
        ImMetadata imMetadata = this.select(pkId);
        imMetadata.setStatus("2"); //状态为已建
        return imProviderService.createTable(getMetadata(pkId)) && imMetadataMapper.update(imMetadata.getPkId(), imMetadata);
    }

    @Transactional
    public boolean dropTable(String pkId) throws Exception {
        ImMetadata imMetadata = this.select(pkId);
        imMetadata.setStatus("1"); //状态为未建
        return  imProviderService.dropTable(getMetadata(pkId)) && imMetadataMapper.update(imMetadata.getPkId(), imMetadata);
    }

    public Metadata getMetadata(String pkId){
        ImMetadata imMetadata = this.select(pkId);
        String dsId = imMetadata.getDsId();
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comProperties = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comProperties);
        List<Property> prop = PropertyUtil.convertToPropertyList(comPropertiesService.selectByFkId(pkId));
        Metadata metadata = new Metadata(prop);
        metadata.setType(MetadataType.EXTERNAL);
        metadata.setTbName(imMetadata.getTbName());
        metadata.setMetadataCols(ImUtil.convertToMetadataColList(imMetadataColService.select(pkId)));
        metadata.setDescribe(imMetadata.getDescribe());
        metadata.setDatasource(datasource);
        return metadata;
    }

    public List<ImMetadata> selectAll() {

        return imMetadataMapper.selectAll();
    }
}

