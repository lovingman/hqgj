package com.huacainfo.ace.hqgj.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.service.PersonalCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 个人中心
 */
@Api(value = "/personalCenter", tags = "个人中心相关接口")
@RestController
@RequestMapping("/personalCenter")
public class PersonalCenterController  extends BaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private PersonalCenterService personalCenterService;


    /**
     * 绑定企业或者服务机构
     * type 1 企业，2 机构
     */
    @ApiOperation(value = "/bingUser", notes = "绑定企业或者服务机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/bingUser", produces = "application/json;charset=UTF-8")
    public ResponseDTO bingUser(String id,String type) throws Exception {
        if(CommonUtils.isBlank(id) || CommonUtils.isBlank(type)){
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        UserProp user = this.getCurUserProp();
        if(user==null){
            return new ResponseDTO(ResultCode.FAIL, "请先登录！");
        }
        return this.personalCenterService.bindUser(id,type,user);
    }

    /**
     * 个人中心--
     */
    @ApiOperation(value = "/homePage", notes = "个人中心")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/homePage", produces = "application/json;charset=UTF-8")
    public ResponseDTO homePage() throws Exception {
        UserProp user = this.getCurUserProp();
        if(user==null){
            return new ResponseDTO(ResultCode.FAIL, "请先登录！");
        }
        return this.personalCenterService.homePage(user);
    }


    /**
     * 解除绑定机构或企业
     */
    @ApiOperation(value = "/relieveBind", notes = "解除绑定企业或者服务机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/relieveBind", produces = "application/json;charset=UTF-8")
    public ResponseDTO relieveBind() throws Exception {
        UserProp user = this.getCurUserProp();
        if(user==null){
            return new ResponseDTO(ResultCode.FAIL, "请先登录！");
        }
        return this.personalCenterService.relieveBind(user);
    }


    /**
     * 微信解除绑定
     */
    @ApiOperation(value = "/relieveByUnionId", notes = "微信解除绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userProp", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/relieveByUnionId", produces = "application/json;charset=UTF-8")
    public ResponseDTO relieveByUnionId(){
        UserProp user = this.getCurUserProp();
        if(user==null){
            return new ResponseDTO(ResultCode.FAIL, "请先登录！");
        }
        return this.personalCenterService.relieveByUnionId(user);
    }


    /**
     * 微信绑定
     */
    @ApiOperation(value = "/bindUnionId", notes = "微信绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userProp", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/bindUnionId", produces = "application/json;charset=UTF-8")
    public ResponseDTO bindUnionId(String unionId){
        UserProp user = this.getCurUserProp();
        if(user==null){
            return new ResponseDTO(ResultCode.FAIL, "请先登录！");
        }
        return this.personalCenterService.bindUnionId(user,unionId);
    }

}
