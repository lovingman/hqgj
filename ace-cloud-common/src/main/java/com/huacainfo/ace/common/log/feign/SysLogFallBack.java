package com.huacainfo.ace.common.log.feign;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.model.SysLog;
import com.huacainfo.ace.common.vo.UserProp;

public class SysLogFallBack implements SysLogService {


    public ResponseDTO log(String operationObj, String operationType, String describe, UserProp userProp) throws Exception {


        return null;
    }

    public ResponseDTO aspectLog(SysLog log) throws Exception {
        return null;
    }

}
