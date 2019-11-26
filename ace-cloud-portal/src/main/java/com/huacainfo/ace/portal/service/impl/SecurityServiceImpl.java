package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.portal.dao.SecurityDao;
import com.huacainfo.ace.portal.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SecurityServiceImpl implements SecurityService {


    @Resource
    private SecurityDao securityDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Users selectUsersByAccount(String account) {
        Users users=securityDao.selectUsersByAccount(account);
        logger.debug("{}",users);
        return users;
    }

    @Override
    public Users selectUsersByAccount1(String account) {
        Users users=securityDao.selectUsersByAccount1(account);
        logger.debug("{}",users);
        return users;
    }

    @Override
    public Users selectUsersByAccount2(String account) {
        Users users=securityDao.selectUsersByAccount2(account);
        logger.debug("{}",users);
        return users;
    }

    @Override
    public List<Map<String, String>> selectRoleListByUserId(String userId) {
        return securityDao.selectRoleListByUserId(userId);
    }

    @Override
    public Map<String, Object> selectUserCfgByUserId(String userId) {

        Map<String, Object> cfg = new HashMap<String, Object>();
        List<Map<String, Object>> list = securityDao.selectUserCfgByUserId(userId);
        for (Map<String, Object> o : list) {
            cfg.put((String) o.get("cfgKey"), o.get("value"));
        }
        return cfg;
    }

    @Override
    public String[] selectSyidByUserId(String userId) {
        List<Map<String, String>> list = this.securityDao.selectSyidByUserId(userId);
        String[] rst = new String[list.size()];
        int i = 0;
        for (Map<String, String> o : list) {
            rst[i] = o.get("syid");
            i++;
        }
        return rst;
    }

    @Override
    public List<String> selectRoleTypeListByUserId(String userId) {
        List<String> rst = new ArrayList<String>();
        List<Map<String, String>> list = this.securityDao.selectRoleTypeListByUserId(userId);
        for (Map<String, String> o : list) {
            rst.add(o.get("TYPE"));
        }
        return rst;
    }

    @Override
    public List<Map<String, String>> loadResourceDefine() {
        return securityDao.loadResourceDefine();
    }


    @Override
    public Map<String, String> loadConfig(String syid) {
        Map<String, String> config = new HashMap<String, String>();
        /*先加载sys系统参数作为基础参数*/
        List<Map<String, String>> base = this.securityDao.loadConfig("sys");
        for (Map<String, String> cfg : base) {
            config.put(cfg.get("config_key"), cfg.get("config_value"));
        }
        /*再加载当前系统参数覆盖*/
        List<Map<String, String>> list = this.securityDao.loadConfig(syid);
        for (Map<String, String> cfg : list) {
            config.put(cfg.get("config_key"), cfg.get("config_value"));
        }
        this.logger.info("loadConfig cfg complete");
        return config;
    }

    @Override
    public ResponseDTO updateUserLocked(String account) {
        this.logger.info("用户 {} 登录次数超限锁定", account);
        int t = this.securityDao.updateUserLocked(account, "1");
        return new ResponseDTO();
    }
}
