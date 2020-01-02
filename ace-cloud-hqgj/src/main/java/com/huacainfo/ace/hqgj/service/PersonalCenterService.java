package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import org.springframework.stereotype.Service;

public interface PersonalCenterService {
    ResponseDTO bindUser(String id, String type, UserProp userProp);

    ResponseDTO homePage(UserProp userProp);

    /**
     * 解除绑定
     * @param userProp
     * @return
     */
    ResponseDTO relieveBind(UserProp userProp);
}
