package com.huacainfo.ace.portal.service;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;

import java.util.List;
import java.util.Map;

public interface SecurityService {


    Users selectUsersByAccount(String account);
    Users selectUsersByAccount1(String account);
    Users selectUsersByAccount2(String account);
    Users selectUsersByOpenId(String account);
    String selectUsersByWx(String account);


    List<Map<String, String>> selectRoleListByUserId(String userId);


    Map<String, Object> selectUserCfgByUserId(String userId);


    String[] selectSyidByUserId(String userId);


    List<String> selectRoleTypeListByUserId(String userId);


    List<Map<String, String>> loadResourceDefine();

    Map<String, String> loadConfig(String syid);

    ResponseDTO updateUserLocked(String account);
}
