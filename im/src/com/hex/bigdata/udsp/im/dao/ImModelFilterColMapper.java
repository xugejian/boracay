package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.im.model.ImModelFilterCol;
import java.util.List;

public interface ImModelFilterColMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(ImModelFilterCol record);

    ImModelFilterCol selectByPrimaryKey(String pkId);

    List<ImModelFilterCol> selectAll();

    int updateByPrimaryKey(ImModelFilterCol record);
}