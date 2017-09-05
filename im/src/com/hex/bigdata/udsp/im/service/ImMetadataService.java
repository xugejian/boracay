package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.im.dao.ImMetadataColMapper;
import com.hex.bigdata.udsp.im.dao.ImMetadataMapper;
import com.hex.bigdata.udsp.im.model.ImMetadata;
import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import com.hex.bigdata.udsp.im.model.ImMetadataDto;
import com.hex.bigdata.udsp.im.model.ImMetadataView;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ComPropertiesService comPropertiesService;

    @Transactional
    public String insert(ImMetadata imMetadata) {
        String pkId = Util.uuid();
        imMetadata.setPkId(pkId);
        imMetadata.setStatus("1"); //1未建 2以建
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

    public List<ImMetadata> select(ImMetadataView imMetadataView, Page page) {
        return imMetadataMapper.select(imMetadataView, page);
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
}

