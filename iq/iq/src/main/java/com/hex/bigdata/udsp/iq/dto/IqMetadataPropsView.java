package com.hex.bigdata.udsp.iq.dto;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.iq.model.IqMetadata;
import com.hex.bigdata.udsp.iq.model.IqMetadataCol;

import java.io.Serializable;
import java.util.List;

/**
 * Created by junjiem on 2017-2-27.
 */
public class IqMetadataPropsView implements Serializable {
    private IqMetadata iqMetadata;
    private List<IqMetadataCol> iqMetadataQueryColList;
    private List<IqMetadataCol> iqMetadataReturnColList;
    private List<ComProperties> comPropertiesList;

    public IqMetadata getIqMetadata() {
        return iqMetadata;
    }

    public void setIqMetadata(IqMetadata iqMetadata) {
        this.iqMetadata = iqMetadata;
    }

    public List<IqMetadataCol> getIqMetadataQueryColList() {
        return iqMetadataQueryColList;
    }

    public void setIqMetadataQueryColList(List<IqMetadataCol> iqMetadataQueryColList) {
        this.iqMetadataQueryColList = iqMetadataQueryColList;
    }

    public List<IqMetadataCol> getIqMetadataReturnColList() {
        return iqMetadataReturnColList;
    }

    public void setIqMetadataReturnColList(List<IqMetadataCol> iqMetadataReturnColList) {
        this.iqMetadataReturnColList = iqMetadataReturnColList;
    }

    public List<ComProperties> getComPropertiesList() {
        return comPropertiesList;
    }

    public void setComPropertiesList(List<ComProperties> comPropertiesList) {
        this.comPropertiesList = comPropertiesList;
    }
}
