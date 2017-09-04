package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import java.util.List;

public interface ImMetadataColMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(ImMetadataCol record);

    ImMetadataCol selectByPrimaryKey(String pkId);

    List<ImMetadataCol> selectAll();

    int updateByPrimaryKey(ImMetadataCol record);
}