package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberQVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberVo;

public interface BaseCompanyMemberDao {

    int deleteByPrimaryKey(String id);

    int insert(BaseCompanyMember o);

    int updateByPrimaryKey(BaseCompanyMember o);

    BaseCompanyMemberVo selectVoByPrimaryKey(String id);

    List<BaseCompanyMemberVo> findList(@Param("condition") BaseCompanyMemberQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseCompanyMemberQVo condition);

    int deleteByCompanyId(@Param("companyId") String companyId);

    int isExist(BaseCompanyMember o);

    int deleteByIds(String[] ids);
}
