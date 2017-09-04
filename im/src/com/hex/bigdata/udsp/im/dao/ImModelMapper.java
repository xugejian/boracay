package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.im.model.ImModel;
import java.util.List;

public interface ImModelMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(ImModel record);

    ImModel selectByPrimaryKey(String pkId);

    List<ImModel> selectAll();

    int updateByPrimaryKey(ImModel record);
}