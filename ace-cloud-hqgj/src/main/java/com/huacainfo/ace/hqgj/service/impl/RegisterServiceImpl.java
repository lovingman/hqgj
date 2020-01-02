package com.huacainfo.ace.hqgj.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;

import com.huacainfo.ace.hqgj.constant.CommonConstant;
import com.huacainfo.ace.hqgj.dao.RegisterDao;
import com.huacainfo.ace.hqgj.model.MapWechatSys;
import com.huacainfo.ace.hqgj.service.RegisterService;
import com.huacainfo.ace.hqgj.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @ClassName RegisterServiceImpl
 * @Description TODO
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RegisterDao registerDao;


    public UserProp systemOperator() {
        UserProp u = new UserProp();
        u.setUserId("systemAdmin");
        u.setName("systemAdmin");
        u.setActiveSyId(CommonConstant.SYS_ID);
        u.setCorpId(CommonConstant.CORP_ID);

        return u;
    }

    /**
     * 人员注册
     *
     * @param dto 注册数据
     * @return 反馈结果 ResponseDTO
     */
    @Override
    @Transactional
    public ResponseDTO insertRegister(Users dto) throws Exception {

        if (CommonUtils.isBlank(dto.getIdCard())) {
            return new ResponseDTO(ResultCode.FAIL, "身份证不能为空！");
        }
        if (CommonUtils.isBlank(dto.getUnionId())) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        int temp = registerDao.isUnionid(dto.getUnionId());
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "微信已存在！");
        }
        dto.setUserId(GUIDUtil.getGUID());
        dto.setAccount(dto.getAccount());
        dto.setName(dto.getName());
        dto.setSex(getSex(dto.getIdCard()));
        dto.setMobile(dto.getAccount());

        //常量设置
        dto.setCreateTime(new Date());
        dto.setStatus(CommonConstant.User_State_VALID);
        dto.setCurSyid(CommonConstant.SYS_ID);
        dto.setCorpId(CommonConstant.CORP_ID);
        ResponseDTO r = insertUser(dto);
        if (r.getStatus() == ResultCode.FAIL) {
            throw new CustomException(ResultCode.FAIL, r.getMessage());
        }
         r =insertMapWechatSys(dto);
        if (r.getStatus() == ResultCode.FAIL) {
            throw new CustomException(ResultCode.FAIL, r.getMessage());
        }
        return r;

    }

    /**
     * 重置找回密码
     *
     * @param mobile      找回账号对应的手机号码
     * @param newPassword 新密码
     * @return ResponseDTO
     * @throws Exception
     */
    @Override
    public ResponseDTO resetPassword(String mobile, String newPassword) {
        if (!isExistByMobile(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "该账号不存在!");
        }
        newPassword = pwdEncoder(newPassword);
        int i = registerDao.updatePassword(mobile, newPassword);
        return new ResponseDTO(ResultCode.SUCCESS, "修改成功!");
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public ResponseDTO<UsersVo> selectUserInfo(String userId) {
        if (CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误!");
        }
        UsersVo users=registerDao.selectUserInfo(userId);
        if (users==null) {
            return new ResponseDTO(ResultCode.FAIL, "信息错误!");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功!",users);
    }

    /**
     * 校验手机号码是否已注册过
     *
     * @param mobile 手机号码
     * @return t/f
     */
    @Override
    public boolean isExistByMobile(String mobile) {

        int temp = registerDao.isExistByMobile(mobile);

        return temp > 0;
    }

    private String getSex(String idCard) {
        if (CommonUtils.isBlank(idCard)) {
            return "1";
        }
        // 1-"男" 2-"女"
        return Integer.parseInt(idCard.substring(16).substring(0, 1)) % 2 == 0 ? "2" : "1";
    }

    private Users getUsers(Users dto) {
        Users user = new Users();
        user.setAccount(dto.getAccount());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setIdCard(dto.getIdCard());
        user.setSex(dto.getSex());
        user.setMobile(dto.getMobile());
        user.setAreaCode(dto.getAreaCode());
        //常量设置
        user.setUserId(dto.getUserId());
        user.setCreateTime(new Date());

        user.setStatus(CommonConstant.User_State_VALID);
        user.setCurSyid(CommonConstant.SYS_ID);
        user.setCorpId(CommonConstant.CORP_ID);
        user.setBirthday(dto.getBirthday());
        return user;
    }


    @Log(operationObj = "创建系统登录账户", operationType = "创建", detail = "创建系统登录账户")
    private ResponseDTO insertUser(Users o) throws Exception {

        if (CommonUtils.isBlank(o.getAccount())) {
            return new ResponseDTO(ResultCode.FAIL, "账号不能为空！");
        }
        if (CommonUtils.isBlank(o.getPassword())) {
            return new ResponseDTO(ResultCode.FAIL, "密码不能为空！");
        }
        if (o.getPassword().length() < 6) {
            return new ResponseDTO(ResultCode.FAIL, "密码字符长度必须为6位以上！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "姓名不能为空！");
        }

        if (CommonUtils.isBlank(o.getMobile())) {
            return new ResponseDTO(ResultCode.FAIL, "手机号码不能为空！");
        }

        int temp = this.registerDao.userIsExist(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "账号已经存在！");
        }

        o.setPassword(pwdEncoder(o.getPassword()));
        this.registerDao.insertUser(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    @Log(operationObj = "微信和用户绑定", operationType = "创建", detail = "创建系统登录账户")
    private ResponseDTO insertMapWechatSys(Users dto){
        String appId=registerDao.selectAppId();
        if (CommonUtils.isBlank(appId)) {
            return new ResponseDTO(ResultCode.FAIL, "未找到数据！");
        }
        MapWechatSys sys=new MapWechatSys();
        sys.setId(GUIDUtil.getGUID());
        sys.setUserId(dto.getUserId());
        sys.setUnionId(dto.getUnionId());
        sys.setCreateDate(new Date());
        sys.setAppId(appId);
        sys.setSysId("hqgj");
        registerDao.insertMapWechatSys(sys);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }
    /**
     * 对密码进行BCrypt加密
     */
    private String pwdEncoder(String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pwd);
    }

}
