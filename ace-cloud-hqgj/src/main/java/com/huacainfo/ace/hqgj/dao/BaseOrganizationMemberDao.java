package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationMemberQVo;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationMemberVo;

public interface BaseOrganizationMemberDao {

    int deleteByPrimaryKey(String id);

    int insert(BaseOrganizationMember o);

    int updateByPrimaryKey(BaseOrganizationMember o);

    BaseOrganizationMemberVo selectVoByPrimaryKey(String id);

    List<BaseOrganizationMemberVo> findList(@Param("condition") BaseOrganizationMemberQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseOrganizationMemberQVo condition);

    int isExist(BaseOrganizationMember o);

    int deleteByIds(String[] ids);

    String existMember(@Param("orgId")String orgId,@Param("idCard")String idCard);

    int updateUserId(@Param("userId")String userId,@Param("id")String id);
}
