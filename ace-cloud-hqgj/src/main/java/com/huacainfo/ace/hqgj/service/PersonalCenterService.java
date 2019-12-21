package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;

public interface PersonalCenterService {
    ResponseDTO bindUser(String id, String type, UserProp userProp);

}
