package com.huacainfo.ace.portal.controller;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.portal.service.SecurityService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/feign")
@Api(tags = "安全登录接口", hidden = true)
public class SecurityControler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;


    @GetMapping(value = "/selectUsersByAccount", produces = "application/json;charset=UTF-8")
    Users selectUsersByAccount(@RequestParam String account) {
        return securityService.selectUsersByAccount(account);
    }

    @GetMapping(value = "/selectUsersByAccount1", produces = "application/json;charset=UTF-8")
    Users selectUsersByAccount1(@RequestParam String account) {
        return securityService.selectUsersByAccount1(account);
    }
    @GetMapping(value = "/selectUsersByAccount2", produces = "application/json;charset=UTF-8")
    Users selectUsersByAccount2(@RequestParam String account) {
        return securityService.selectUsersByAccount2(account);
    }

    @GetMapping(value = "/selectRoleListByUserId", produces = "application/json;charset=UTF-8")
    List<Map<String, String>> selectRoleListByUserId(@RequestParam String userId) {
        return securityService.selectRoleListByUserId(userId);
    }

    @GetMapping(value = "/selectUserCfgByUserId", produces = "application/json;charset=UTF-8")
    Map<String, Object> selectUserCfgByUserId(@RequestParam String userId) {
        return securityService.selectUserCfgByUserId(userId);
    }

    @GetMapping(value = "/selectSyidByUserId", produces = "application/json;charset=UTF-8")
    String[] selectSyidByUserId(@RequestParam String userId) {
        return securityService.selectSyidByUserId(userId);
    }

    @GetMapping(value = "/selectRoleTypeListByUserId", produces = "application/json;charset=UTF-8")
    List<String> selectRoleTypeListByUserId(@RequestParam String userId) {
        return securityService.selectRoleTypeListByUserId(userId);
    }

    @GetMapping(value = "/loadResourceDefine", produces = "application/json;charset=UTF-8")
    List<Map<String, String>> loadResourceDefine() {
        return securityService.loadResourceDefine();
    }

    @GetMapping(value = "/loadConfig", produces = "application/json;charset=UTF-8")
    Map<String, String> loadConfig(@RequestParam String syid) {
        return securityService.loadConfig(syid);
    }


    @GetMapping(value = "/updateUserLocked", produces = "application/json;charset=UTF-8")
    ResponseDTO updateUserLocked(@RequestParam String account) {
        return securityService.updateUserLocked(account);
    }


}
