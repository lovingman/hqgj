package com.huacainfo.ace.common.security.feign;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "zcpa-portal", fallback = FeignSecurityFallBack.class)
public interface FeignSecurityService {

    @RequestMapping(value = "/feign/selectUsersByAccount", produces = "application/json;charset=UTF-8")
    Users selectUsersByAccount(@RequestParam String account);

    @RequestMapping(value = "/feign/selectUsersByAccount1", produces = "application/json;charset=UTF-8")
    Users selectUsersByAccount1(@RequestParam String account);

    @RequestMapping(value = "/feign/selectUsersByAccount2", produces = "application/json;charset=UTF-8")
    Users selectUsersByAccount2(@RequestParam String account);

    @RequestMapping(value = "/feign/selectRoleListByUserId", produces = "application/json;charset=UTF-8")
    List<Map<String, String>> selectRoleListByUserId(@RequestParam String userId);

    @RequestMapping(value = "/feign/selectUserCfgByUserId", produces = "application/json;charset=UTF-8")
    Map<String, String> selectUserCfgByUserId(@RequestParam String userId);

    @RequestMapping(value = "/feign/selectSyidByUserId", produces = "application/json;charset=UTF-8")
    String[] selectSyidByUserId(@RequestParam String userId);

    @RequestMapping(value = "/feign/selectRoleTypeListByUserId", produces = "application/json;charset=UTF-8")
    List<String> selectRoleTypeListByUserId(@RequestParam String userId);

    @RequestMapping(value = "/feign/loadResourceDefine")
    List<Map<String, String>> loadResourceDefine();

    @RequestMapping(value = "/feign/loadConfig", produces = "application/json;charset=UTF-8")
    Map<String, String> loadConfig(@RequestParam String syid);

    @RequestMapping(value = "/feign/updateUserLocked", produces = "application/json;charset=UTF-8")
    ResponseDTO updateUserLocked(@RequestParam String account);
}
