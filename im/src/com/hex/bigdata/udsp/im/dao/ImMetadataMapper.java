package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.im.model.ImMetadata;
import java.util.List;

public interface ImMetadataMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(ImMetadata record);

    ImMetadata selectByPrimaryKey(String pkId);

    List<ImMetadata> selectAll();

    int updateByPrimaryKey(ImMetadata record);
}