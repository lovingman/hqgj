package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.zcpa.service.ActProjectTargetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WProjectController
 * @Description 志愿项目控制器
 * @Author Arvin Zou
 * @Date 2019/8/20 19:13
 */
@Api(value = "/www/project", tags = "志愿项目控制器接口")
@RestController
@RequestMapping("/www/project")
public class WProjectController extends BizController {

    @Autowired
    private ActProjectTargetService actProjectTargetService;

    /**
     * 可选志愿者对接区域树
     *
     * @param projectType 项目类型, 枚举值,可选: family-情暖家庭项目; project-除情暖家庭外的其他项目
     * @param projectId   项目编号
     * @return ListDTO<Tree>
     */
    @ApiOperation(value = "/getAreaTree", notes = "可选志愿者对接区域树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "可选志愿者对接区域树", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "projectType", value = "项目类型, 枚举值,可选: family-情暖家庭项目; project-除情暖家庭外的其他项目", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/getAreaTree", produces = "application/json;charset=UTF-8")
    public ResponseDTO getAreaTree(String projectType, String projectId) {
        if (CommonUtils.isEmpty(projectId) || CommonUtils.isEmpty(projectType)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }

        switch (projectType) {
            case "family":
                break;
            case "project":
                break;
            default:
                return new ResponseDTO(ResultCode.FAIL, "项目类型不对头");
        }

        return actProjectTargetService.getAreaTree(projectId, projectType);
    }

}
