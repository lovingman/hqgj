package com.huacainfo.ace.common.security.feign;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FeignSecurityFallBack implements FeignSecurityService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Users selectUsersByAccount(String account) {
        logger.error("FeignSecurityFallBack {} ", account);
        return null;
    }

    public Users selectUsersByAccount1(String account) {
        logger.error("FeignSecurityFallBack {} ", account);
        return null;
    }
    public Users selectUsersByAccount2(String account) {
        logger.error("FeignSecurityFallBack {} ", account);
        return null;
    }


    public List<Map<String, String>> selectRoleListByUserId(String userId) {

        logger.error("FeignSecurityFallBack");
        return null;
    }


    public Map<String, String> selectUserCfgByUserId(String userId) {
        logger.error("FeignSecurityFallBack");
        return null;
    }


    public String[] selectSyidByUserId(String userId) {
        logger.error("FeignSecurityFallBack");
        return null;
    }


    public List<String> selectRoleTypeListByUserId(String userId) {
        logger.error("FeignSecurityFallBack");
        return null;
    }

    public List<Map<String, String>> loadResourceDefine() {
        logger.error("FeignSecurityFallBack");
        return null;
    }

    public Map<String, String> loadConfig(String syid) {
        logger.error("FeignSecurityFallBack");
        return null;
    }

    public ResponseDTO updateUserLocked(String account) {
        logger.error("FeignSecurityFallBack");
        return null;
    }
}
