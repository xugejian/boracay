package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.SourceProvider;
import com.hex.bigdata.udsp.im.provider.TargetProvider;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-6.
 */
@Service
public class ImProviderService {

    @Autowired
    private GFDictMapper gfDictMapper;

    public List<MetadataCol> getCloumnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        String implClass = getImplClass(datasource);
        TargetProvider provider = (TargetProvider) WebApplicationContextUtil.getBean(implClass);
        return provider.columnInfo(metadata);
    }

    private String getImplClass(Datasource datasource) {
        String type = datasource.getType();
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey("IM_IMPL_CLASS", type);
            implClass = gfDict.getDictName();
        }
        return implClass;
    }

    public List<MetadataCol> getCloumnInfo(Model model) {
        Datasource datasource = model.getDatasource();
        String implClass = getImplClass(datasource);
        SourceProvider provider = (SourceProvider) WebApplicationContextUtil.getBean(implClass);
        return provider.columnInfo(model);
    }

}
