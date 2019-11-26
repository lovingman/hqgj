package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;

import java.util.Map;

/**
 * @ClassName RegisterService
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 17:33
 */
public interface RegisterService {

    /**
     * 校验手机号码是否已注册过
     *
     * @param mobile 手机号码
     * @return t/f
     */
    boolean isExistByMobile(String mobile);

    /**
     * 人员注册
     *
     * @param dto 注册数据
     * @return 反馈结果 ResponseDTO
     */
    ResponseDTO insertRegister(BaseVolunteerVo dto) throws Exception;

    /**
     * 重置找回密码
     *
     * @param mobile      找回账号对应的手机号码
     * @param newPassword 新密码
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO resetPassword(String mobile, String newPassword);

    /**
     * 手机端-个人中心首页接口
     *
     * @param volunteerId 志愿者ID,登陆后可后端自动获取
     * @param loginUser   当前登录用户
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO homePage(UserProp loginUser, String volunteerId);

    /**
     * 获取当前登录用户的AreaCode
     *
     * @param userId 用户ID
     * @return 反馈结果 ResponseDTO
     */
    Map<String, Object> getAreaCode(String userId);
}
