package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.vo.RoleQVo;
import com.huacainfo.ace.portal.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    int deleteByPrimaryKey(String id);

    int insert(Role o);

    int updateByPrimaryKey(Role role);

    RoleVo selectVoByPrimaryKey(String id);

    List<RoleVo> findList(@Param("condition") RoleQVo condition,
                          @Param("start") int start,
                          @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RoleQVo condition);

    int isExist(Role o);

    int deleteByIds(String[] ids);

    int isExitRoleByRoleName(@Param("roleName") String roleName);

    int deleteRoleResources(@Param("roleId") String roleId);

    int insertRoleResources(@Param("roleId") String roleId,
                            @Param("resourcesId") String resourcesId);

    List<Map<String, Object>> findRoleResTreeList(@Param("roleId") String roleId);

    String [] selectRoleResByRoleId(@Param("roleId") String roleId);
}
