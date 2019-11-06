package com.hex.bigdata.udsp.mc.dao;

import com.hex.bigdata.udsp.common.dao.base.AsyncInsertMapper;
import com.hex.bigdata.udsp.mc.dto.McConsumeLogView;
import com.hex.bigdata.udsp.mc.model.McConsumeData;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.goframe.model.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消费数据
 */
@Repository
public class McConsumeDataMapper extends AsyncInsertMapper<McConsumeData> {

    /**
     * 后台起线程异步插入
     */
    @Override
    protected boolean insertExe(McConsumeData mcConsumeData) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.mc.dao.McConsumeDataMapper.insert", mcConsumeData) == 1;
    }

    @Override
    protected McConsumeData selectExe(String id) {
        return null;
    }

    /**
     * 清空某天和其之前的消费数据
     *
     * @param date
     * @return
     */
    public boolean clean(String date) {
        return this.sqlSessionTemplate.update(
                "com.hex.bigdata.udsp.mc.dao.McConsumeDataMapper.clean", date) >= 0;
    }
}