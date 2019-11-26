package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.constant.CommonConstant;
import com.huacainfo.ace.zcpa.dao.RegisterDao;
import com.huacainfo.ace.zcpa.service.BaseVolunteerService;
import com.huacainfo.ace.zcpa.service.RegisterService;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @ClassName RegisterServiceImpl
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 17:36
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RegisterDao registerDao;
    @Resource
    private BaseVolunteerService baseVolunteerService;


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
    public ResponseDTO insertRegister(BaseVolunteerVo dto) throws Exception {
        //基础数据填充
        //解析性别
        dto.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(dto.getIdCard())) {
            return new ResponseDTO(ResultCode.FAIL, "身份证不能为空！");
        }
        dto.setSex(getSex(dto.getIdCard()));
        dto.setAccount(dto.getMobile());
        if (CommonUtils.isBlank(dto.getUnionid())) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }

        int temp = registerDao.isUnionid(dto.getUnionid());
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "微信已存在！");
        }
        dto.setUnionid(dto.getUnionid());
        dto.setBirthDate(getBirthday(dto.getIdCard()));

        //基础信息入库
        ResponseDTO r = baseVolunteerService.create(dto, systemOperator());
        if (r.getStatus() == ResultCode.FAIL) {
            return r;
        }
        //转换注册
        Users user = getUsers(dto);
        r = insertUser(user);
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
     * 手机端-个人中心首页接口
     *
     * @param loginUser   当前登录用户
     * @param volunteerId 志愿者ID,登陆后可后端自动获取
     * @return ResponseDTO
     * @throws Exception
     */
    @Override
    public ResponseDTO homePage(UserProp loginUser, String volunteerId) {
        String userId = loginUser == null ? volunteerId : loginUser.getUserId();

        Map<String, Object> rst = registerDao.homePage(userId);

        if (rst == null) {
            return new ResponseDTO(ResultCode.FAIL, "用户数据丢失");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "查询成功", rst);
    }

    /**
     * 获取当前登录用户的AreaCode
     *
     * @param userId 用户ID
     * @return 反馈结果 ResponseDTO
     */
    @Override
    public Map<String, Object> getAreaCode(String userId) {

        return registerDao.getAreaCode(userId, "zcpa");
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

    private Users getUsers(BaseVolunteerVo dto) {
        Users user = new Users();
        user.setAccount(dto.getAccount());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setIdCard(dto.getIdCard());
        user.setSex(dto.getSex());
        user.setMobile(dto.getMobile());
        user.setAreaCode(dto.getAreaCode());
        //常量设置
        user.setUserId(dto.getId());
        user.setCreateTime(new Date());

        user.setStatus(CommonConstant.User_State_VALID);
        user.setCurSyid(CommonConstant.SYS_ID);
        user.setCorpId(CommonConstant.CORP_ID);
        user.setIsSync("n");
        user.setBirthday(dto.getBirthDate());
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
        if (CommonUtils.isBlank(o.getIdCard())) {
            return new ResponseDTO(ResultCode.FAIL, "身份证号不能为空！");
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

    /**
     * 对密码进行BCrypt加密
     */
    private String pwdEncoder(String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pwd);
    }

    //根据身份证获取出生日期
    public Date getBirthday(String certificateNo) throws Exception {
        String birthday = "";
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return null;
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return null;
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "-"
                    + certificateNo.substring(8, 10) + "-"
                    + certificateNo.substring(10, 12);

        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "-"
                    + certificateNo.substring(10, 12) + "-"
                    + certificateNo.substring(12, 14);

        }
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(birthday);
        return date;
    }
}
