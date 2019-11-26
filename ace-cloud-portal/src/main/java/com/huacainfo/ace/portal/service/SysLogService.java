package com.huacainfo.ace.portal.service;


import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.model.SysLog;
import com.huacainfo.ace.common.vo.UserProp;

public interface SysLogService {

    ResponseDTO log(String operationObj, String operationType, String describe, UserProp userProp) throws Exception;

    ResponseDTO aspectLog(SysLog log) throws Exception;
}
