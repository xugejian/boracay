package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.im.model.ImModelMapping;
import java.util.List;

public interface ImModelMappingMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(ImModelMapping record);

    ImModelMapping selectByPrimaryKey(String pkId);

    List<ImModelMapping> selectAll();

    int updateByPrimaryKey(ImModelMapping record);
}