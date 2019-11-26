package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.RoleResources;
import com.huacainfo.ace.portal.vo.RoleResourcesQVo;
import com.huacainfo.ace.portal.vo.RoleResourcesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourcesDao {

    int deleteByPrimaryKey(String id);

    int insert(RoleResources o);

    int updateByPrimaryKey(RoleResources o);

    RoleResourcesVo selectVoByPrimaryKey(String id);

    List<RoleResourcesVo> findList(@Param("condition") RoleResourcesQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RoleResourcesQVo condition);

    int isExist(RoleResources o);

    int deleteByIds(String[] ids);
}
