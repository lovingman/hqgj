package com.huacainfo.ace.zcpa.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.StringKit;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.zcpa.service.ActProjectService;
import com.huacainfo.ace.zcpa.service.VolunteeringService;
import com.huacainfo.ace.zcpa.vo.ActProjectQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @ClassName WVolunteerController
 * @Description TODO
 * @Author heshaung
 * @Date 2019/8/5 17:27
 */
@Api(value = "/www/project", tags = "志愿服务活动")
@RestController
@RequestMapping("/www/project")
public class WVolunteerController extends BizController {
    @Autowired
    private ActProjectService actProjectService;
    @Autowired
    private VolunteeringService volunteeringService;


    @ApiOperation(value = "/page", notes = "获取项目活动数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ActProjectVo> page(ActProjectQVo condition, PageParam page) throws Exception {
        PageDTO<ActProjectVo> rst = this.actProjectService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    @ApiOperation(value = "/getById", notes = "根据主键获取 项目活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "category", value = "项目类型", required = true, dataType = "String", paramType = "form")
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectVo> getById(String id, String category) throws Exception {
        return this.actProjectService.getById(id, category);
    }


    /**
     * 获取项目详情
     *
     * @param id 项目id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/getByProjectId", notes = "获取项目详情")
    @GetMapping(value = "/getByProjectId", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectVo> getByProjectId(String id) throws Exception {
        String volunteerId=null;
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user != null ) {
            volunteerId=user.getUserId();
        }
        return actProjectService.getByProjectId(id,volunteerId);
    }


    /**
     * 获取项目参与志愿者list接口
     *
     * @param projectId 项目ID
     * @param category  项目类型
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/findVolunteerList", notes = "获取项目参与志愿者接口")
    @GetMapping(value = "/findVolunteerList", produces = "application/json;charset=UTF-8")
    public ResponseDTO findVolunteerList(String projectId, String category, PageParam page) {
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少项目主键");
        }
        if (CommonUtils.isBlank(category)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少项目类型");
        }
        return this.volunteeringService.findVolunteerList(projectId, category, page.getStart(), page.getLimit(), page.getOrderBy());
    }


    /**
     * 获取志愿者足迹数据
     *
     * @param volunteerId 志愿者ID;不传人时获取当前登录者id
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/getSpoorData", notes = "个人中心首页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "volunteerId", value = "志愿者ID,登陆后可后端自动获取", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/getSpoorData", produces = "application/json;charset=UTF-8")
    public ResponseDTO getSpoorData(String volunteerId) {

        if (StringKit.isEmpty(volunteerId)) {
            UserProp loginUser = this.getCurUserProp();
            if (loginUser == null && CommonUtils.isBlank(volunteerId)) {
                return new ResponseDTO(ResultCode.FAIL, "志愿者信息不能为空");
            }
            volunteerId = loginUser.getUserId();
        }

        Map<String, Object> rst = volunteeringService.getSpoorData(volunteerId);
        if (rst == null) {
            return new ResponseDTO(ResultCode.SUCCESS, "未找到相关数据");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "SUCCESS", rst);
    }

}
