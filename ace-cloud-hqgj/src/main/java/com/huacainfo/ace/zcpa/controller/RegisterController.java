package com.huacainfo.ace.zcpa.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.zcpa.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName WRegisterController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 17:27
 */
@Api(value = "/reg", tags = "人员注册相关接口")
@RestController
@RequestMapping("/reg")
public class RegisterController extends BizController {
    @Resource
    private RegisterService registerService;



    /**
     * 手机端-个人中心首页接口
     *
     * @param volunteerId 志愿者ID,登陆后可后端自动获取
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/homePage", notes = "个人中心首页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "volunteerId", value = "志愿者ID,登陆后可后端自动获取", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/homePage", produces = "application/json;charset=UTF-8")
    public ResponseDTO homePage(String volunteerId) {
        UserProp loginUser = this.getCurUserProp();
        if (loginUser == null && CommonUtils.isBlank(volunteerId)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }


        return registerService.homePage(loginUser, volunteerId);
    }
}
