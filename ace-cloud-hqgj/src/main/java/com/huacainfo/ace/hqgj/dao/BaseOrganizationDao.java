package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationQVo;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationVo;

public interface BaseOrganizationDao {

    int deleteByPrimaryKey(String id);

    int insert(BaseOrganization o);

    int updateByPrimaryKey(BaseOrganization o);

    BaseOrganizationVo selectVoByPrimaryKey(String id);

    List<BaseOrganizationVo> findList(@Param("condition") BaseOrganizationQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseOrganizationQVo condition);

    int isExist(BaseOrganization o);

    int deleteByIds(String[] ids);
}
