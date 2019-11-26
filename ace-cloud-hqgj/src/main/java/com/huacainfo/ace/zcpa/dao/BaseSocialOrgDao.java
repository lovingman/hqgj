package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.BaseSocialOrg;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgQVo;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgVo;

public interface BaseSocialOrgDao {

    int deleteByPrimaryKey(String id);

    int insert(BaseSocialOrg o);

    int updateByPrimaryKey(BaseSocialOrg o);

    BaseSocialOrgVo selectVoByPrimaryKey(String id);

    List<BaseSocialOrgVo> findList(@Param("condition") BaseSocialOrgQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseSocialOrgQVo condition);

    int isExist(BaseSocialOrg o);

    int deleteByIds(String[] ids);
}
