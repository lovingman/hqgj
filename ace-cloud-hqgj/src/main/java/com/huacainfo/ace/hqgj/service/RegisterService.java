package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.hqgj.vo.UsersVo;

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
    ResponseDTO insertRegister(Users dto) throws Exception;

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
     * 获取用户信息
     * @param userId
     * @return
     */
    ResponseDTO<UsersVo> selectUserInfo(String userId);

}
