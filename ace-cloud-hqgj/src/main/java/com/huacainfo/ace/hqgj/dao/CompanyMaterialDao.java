package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.CompanyMaterial;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialQVo;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialVo;

public interface CompanyMaterialDao {

    int deleteByPrimaryKey(String id);

    int insert(CompanyMaterial o);

    int updateByPrimaryKey(CompanyMaterial o);

    CompanyMaterialVo selectVoByPrimaryKey(String id);

    List<CompanyMaterialVo> findList(@Param("condition") CompanyMaterialQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CompanyMaterialQVo condition);

    int isExist(CompanyMaterial o);

    int deleteByIds(String[] ids);
}
