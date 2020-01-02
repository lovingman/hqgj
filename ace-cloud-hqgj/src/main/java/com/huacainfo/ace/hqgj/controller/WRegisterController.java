package com.huacainfo.ace.hqgj.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.plugin.sms.hxrt.feign.FeignTaskCmccService;
import com.huacainfo.ace.common.security.vo.UsersVo;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.hqgj.constant.CommonConstant;
import com.huacainfo.ace.hqgj.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName WRegisterController
 * @Description TODO
 */
@Api(value = "/www/reg", tags = "注册相关接口")
@RestController
@RequestMapping("/www/reg")
public class WRegisterController extends BizController {
    @Resource
    private RegisterService registerService;

    @Resource
    private FeignTaskCmccService feginTaskCmccService;

    /**
     * 功能描述: 发送短信验证验证码
     *
     * @param: mobile 手机号码
     * @return: ResultResponse
     */
    @ApiOperation(value = "/captchaForgetPwd", notes = "验证码获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "length", value = "数字验证码长度(可选)", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/captchaForgetPwd", produces = "application/json;charset=UTF-8")
    public ResponseDTO captchaForgetPwd(String mobile, String length) {

        if (CommonUtils.isBlank(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "手机号码不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "手机号码格式错误");
        }
        if (!registerService.isExistByMobile(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "未找到该手机号对应的注册用户");
        }
        //四位随机码
        length = CommonUtils.isEmpty(length) ? "4" : length;
        String randCode = CommonUtils.getIdentifyCode(Integer.valueOf(length), 0);
        //内容组装
        String taskName = "验证码" + mobile;
        String msg = "本次提交验证码为" + randCode + "，请及时输入。" + CommonConstant.SMS_TAG;
        String tel = mobile + "," + mobile + ";";
        // 保存进session
        setSession("j_captcha_cmcc_" + mobile, randCode);
        //session获取测试
        logger.debug(mobile + "=>j_captcha_cmcc:{}", getSession("j_captcha_cmcc_" + mobile));

        return feginTaskCmccService.addTaskCmcc(taskName, msg, tel);
    }


    /**
     * 功能描述: 发送短信验证验证码
     *
     * @param: mobile 手机号码
     * @return: ResultResponse
     */
    @ApiOperation(value = "/captcha", notes = "验证码获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "length", value = "数字验证码长度(可选)", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/captcha", produces = "application/json;charset=UTF-8")
    public ResponseDTO captcha(String mobile, String length) {

        if (CommonUtils.isBlank(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "手机号码不能为空");
        }
        if (!CommonUtils.isValidMobile(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "手机号码格式错误");
        }
        if (registerService.isExistByMobile(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "该手机号码已被注册");
        }
        //四位随机码
        length = CommonUtils.isEmpty(length) ? "4" : length;
        String randCode = CommonUtils.getIdentifyCode(Integer.valueOf(length), 0);
        //内容组装
        String taskName = "验证码" + mobile;
        String msg = "本次提交验证码为" + randCode + "，请及时输入。" + CommonConstant.SMS_TAG;
        String tel = mobile + "," + mobile + ";";
        // 保存进session
        setSession("j_captcha_cmcc_" + mobile, randCode);

        logger.info("session1================"+getRequest().getSession().getId());
        //session获取测试
        logger.debug(mobile + "=>j_captcha_cmcc:{}", getSession("j_captcha_cmcc_" + mobile));

        return feginTaskCmccService.addTaskCmcc(taskName, msg, tel);
    }


    /**
     * 人员注册
     *
     * @param data json格式数据串
     * @return 反馈结果 ResponseDTO
     */
    @ApiOperation(value = "/register", notes = "注册接口")
    @PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public ResponseDTO register(UsersVo data) throws Exception {
        String mobile=data.getMobile()==null?data.getAccount():data.getMobile();
        String code = String.valueOf(getSession("j_captcha_cmcc_" + mobile));
        logger.info("session2================"+getRequest().getSession().getId());
        String userCode = data.getCaptcha();
        if (CommonUtils.isBlank(userCode) || !userCode.equals(code)) {
            return new ResponseDTO(ResultCode.FAIL, "验证码验证失败！");
        }
        try {
            return registerService.insertRegister(data);
        } catch (CustomException e) {
            return new ResponseDTO(ResultCode.FAIL, e.getMessage());
        } catch (Exception e) {
            return new ResponseDTO(ResultCode.FAIL, "哦豁,系统异常了！");
        }
    }

    /**
     * 重置找回密码
     *
     * @param mobile      找回账号对应的手机号码
     * @param captcha     验证码
     * @param newPassword 新密码
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/resetPassword", notes = "注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "captcha", value = "数字验证码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/resetPassword", produces = "application/json;charset=UTF-8")
    public ResponseDTO resetPassword(String mobile, String captcha, String newPassword) throws Exception {

        if (CommonUtils.isBlank(mobile)) {
            return new ResponseDTO(ResultCode.FAIL, "手机号码不能为空！");
        }
        if (CommonUtils.isBlank(captcha)) {
            return new ResponseDTO(ResultCode.FAIL, "验证码不能为空！");
        }
        if (CommonUtils.isBlank(newPassword)) {
            return new ResponseDTO(ResultCode.FAIL, "新密码不能为空！");
        }
        //验证码有效验证
        String code = String.valueOf(getSession("j_captcha_cmcc_" + mobile));
        String userCode = captcha;
        if (CommonUtils.isBlank(userCode) || !userCode.equals(code)) {
            return new ResponseDTO(ResultCode.FAIL, "验证码验证失败！");
        }

        return registerService.resetPassword(mobile, newPassword);
    }


    /**
     * 判断登录
     *
     * @param
     * @return 反馈结果 ResponseDTO
     */
    @ApiOperation(value = "/isLogin", notes = "判断登录")
    @GetMapping(value = "/isLogin", produces = "application/json;charset=UTF-8")
    public ResponseDTO isLogin() throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null) {
            return new ResponseDTO(ResultCode.SUCCESS, "尚未登录", "0");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "用户已登录", "1");
    }

}
