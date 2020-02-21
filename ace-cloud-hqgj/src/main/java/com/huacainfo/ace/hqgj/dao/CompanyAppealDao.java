package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.CompanyAppeal;
import com.huacainfo.ace.hqgj.vo.CompanyAppealQVo;
import com.huacainfo.ace.hqgj.vo.CompanyAppealVo;

public interface CompanyAppealDao {

    int deleteByPrimaryKey(String id);

    int insert(CompanyAppeal o);

    int updateByPrimaryKey(CompanyAppeal o);

    CompanyAppealVo selectVoByPrimaryKey(String id);

    List<CompanyAppealVo> findList(@Param("condition") CompanyAppealQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CompanyAppealQVo condition);

    int isExist(CompanyAppeal o);

    int deleteByIds(String[] ids);
}
