package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.model.SysLog;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.SysLogDao;
import com.huacainfo.ace.portal.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class SysLogServiceImpl implements SysLogService {


    @Resource
    private SysLogDao sysLogDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    public ResponseDTO log(String operationObj, String operationType, String describe, UserProp userProp) throws Exception {
        SysLog sysLog = new SysLog();
        sysLog.setId(UUID.randomUUID().toString());
        sysLog.setCreateTime(new Date());
        sysLog.setUserId(userProp.getUserId());
        sysLog.setUserName(userProp.getName());
        sysLog.setDescribe(describe);
        sysLog.setOperationObj(operationObj);
        sysLog.setOperationType(operationType);
        sysLog.setOperationIp(userProp.getIp());
        sysLogDao.insert(sysLog);
        return new ResponseDTO();
    }

    @Transactional
    public ResponseDTO aspectLog(SysLog log) throws Exception {
        sysLogDao.insert(log);
        return new ResponseDTO();
    }


}
