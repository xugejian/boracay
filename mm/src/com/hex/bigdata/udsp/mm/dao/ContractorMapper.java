package com.hex.bigdata.udsp.mm.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.mm.dto.ContractorView;
import com.hex.bigdata.udsp.mm.model.Contractor;
import com.hex.goframe.model.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ContractorMapper extends SyncMapper<Contractor> {

    @Override
    protected boolean insertExe(Contractor contractor) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.mm.dao.ContractorMapper.insert", contractor) == 1;
    }

    @Override
    protected boolean updateExe(Contractor contractor) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.mm.dao.ContractorMapper.updateByPrimaryKey", contractor) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.mm.dao.ContractorMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected Contractor selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.mm.dao.ContractorMapper.selectByPrimaryKey", id);
    }


    @Override
    protected boolean deleteListExe(String fkId) {
        return true;
    }

    @Override
    protected List<Contractor> selectListExe(String fkId) {
        return null;
    }

    /**
     * 多田间分页查询
     * @param contractorView
     * @param page
     * @return
     */
    public List<ContractorView> selectPage(ContractorView contractorView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mm.dao.ContractorMapper.selectPage", contractorView,
                page.toPageBounds());
    }

    /**
     * 查询所有厂商
     * @return
     */
    public List<Contractor> selectAll(){
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mm.dao.ContractorMapper.selectAll");
    }

    /**
     * 根据名称查找
     * @param name
     * @return
     */
    public Contractor selectByName(String name) {
        return sqlSessionTemplate.selectOne(
                "com.hex.bigdata.udsp.mm.dao.ContractorMapper.selectByName",name);
    }


}