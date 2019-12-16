package com.huacainfo.ace.hqgj.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.plugin.sms.hxrt.feign.FeignTaskCmccService;
import com.huacainfo.ace.common.security.vo.UsersVo;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.hqgj.constant.CommonConstant;
import com.huacainfo.ace.hqgj.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName RegisterController
 * @Description TODO
 */
@Api(value = "/reg", tags = "注册相关接口")
@RestController
@RequestMapping("/reg")
public class RegisterController extends BizController {
    @Resource
    private RegisterService registerService;

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

    /**
     *
     *获取用户信息
     * @param
     * @return 反馈结果 ResponseDTO
     */
    @ApiOperation(value = "/selectUserInfo", notes = "获取用户信息")
    @GetMapping(value = "/selectUserInfo", produces = "application/json;charset=UTF-8")
    public ResponseDTO selectUserInfo() throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null) {
            return new ResponseDTO(ResultCode.SUCCESS, "尚未登录");
        }
        return registerService.selectUserInfo(user.getUserId());
    }


}
