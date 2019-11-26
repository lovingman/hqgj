package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.portal.dao.SecurityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SecurityDao securityDao;

    @Scheduled(cron = "0/59 * * * * ?")
    public void taskAutoUnLockUserAccount() {
        //this.logger.info("扫描锁定账号列表 {}", new Date());
        List<Map<String, String>> list = this.securityDao.getLockedList();
        for (Map<String, String> o : list) {
            this.securityDao.updateUserLocked(o.get("account"), "0");
            this.logger.info("账号 {} 已经解锁", o.get("account"), new Date());
        }
    }
}
