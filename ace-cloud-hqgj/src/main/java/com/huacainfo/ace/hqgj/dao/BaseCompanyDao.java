package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.BaseCompany;
import com.huacainfo.ace.hqgj.vo.BaseCompanyQVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyVo;

public interface BaseCompanyDao {

    int deleteByPrimaryKey(String id);

    int insert(BaseCompany o);

    int updateByPrimaryKey(BaseCompany o);

    BaseCompanyVo selectVoByPrimaryKey(String id);

    List<BaseCompanyVo> findList(@Param("condition") BaseCompanyQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseCompanyQVo condition);

    int isExist(BaseCompany o);

    int deleteByIds(String[] ids);
}
