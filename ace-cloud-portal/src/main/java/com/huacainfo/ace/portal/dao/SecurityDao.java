package com.huacainfo.ace.portal.dao;

/**
 * @author chenxiaoke
 */


import com.huacainfo.ace.common.security.model.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chenxiaoke
 *
 */
public interface SecurityDao {

    Users selectUsersByAccount(@Param("account") String account);
    Users selectUsersByOpenId(@Param("account") String account);
    Users selectUsersByAccount1(@Param("account") String account);
    Users selectUsersByAccount2(@Param("account") String account);
    String selectUsersByWx(@Param("account") String account);

    List<Map<String, String>> selectRoleListByUserId(@Param("userId") String userId);

    List<Map<String, String>> selectRoleTypeListByUserId(@Param("userId") String userId);

    List<Map<String, Object>> selectUserCfgByUserId(String userId);

    List<Map<String, String>> selectSyidByUserId(String userId);

    List<Map<String, String>> loadConfig(String syid);

    List<Map<String, String>> loadResourceDefine();

    int updateUserLocked(@Param("account") String account, @Param("locked") String locked);

    List<Map<String, String>> getLockedList();



}
