package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteerSignout;
import com.huacainfo.ace.zcpa.service.*;

import com.huacainfo.ace.zcpa.vo.*;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: 何双
 * @version: 2019-07-30
 * @Description: TODO(志愿服务接口)
 */
@Api(value = "/volunteering", tags = "项目活动")
@RestController
@RequestMapping("/volunteering")
public class VolunteeringController extends BaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VolunteeringService volunteeringService;
    @Autowired
    private ActFamilyVolunteerService actFamilyVolunteerService;
    @Autowired
    private ActProjectActivityService actProjectActivityService;
    @Autowired
    private BaseVolunteerService baseVolunteerService;


    @ApiOperation(value = "/volunteerQuotaCount", notes = "志愿者类型名额")
    @GetMapping(value = "/volunteerQuotaCount", produces = "application/json;charset=UTF-8")
    public ResponseDTO volunteerQuotaCount(String areaCode,String projectId) throws Exception {
        return this.volunteeringService.volunteerQuotaCount(areaCode,projectId);
    }

    /**
     * 参与对接
     * @param qvo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/participationDocking", notes = "参与对接")
    @PostMapping(value = "/participationDocking", produces = "application/json;charset=UTF-8")
    public ResponseDTO participationDocking(VolunteeringQVo qvo) throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null ) {
            return new ResponseDTO(ResultCode.FAIL, "尚未登录","0");
        }
        return this.volunteeringService.participationDocking(qvo,user);
    }

    /**
     * 校园已报名志愿者名额
     * @param areaCode 地区
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/schoolVolunteerQuota", notes = "校园已报名志愿者名额")
    @GetMapping(value = "/schoolVolunteerQuota", produces = "application/json;charset=UTF-8")
    public ResponseDTO<List<VolunteeringVo>> schoolVolunteerQuota(String areaCode,String projectId) throws Exception {
        return  this.volunteeringService.schoolVolunteerQuota(areaCode,projectId);
    }

    /**
     * 退出志愿服务
     * @param
     * @param category
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/signOutVolunteer", notes = "退出志愿服务")
    @PostMapping(value = "/signOutVolunteer", produces = "application/json;charset=UTF-8")
    public ResponseDTO signOutVolunteer(ActProjectVolunteerSignout o, String category) throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null ) {
            return new ResponseDTO(ResultCode.FAIL, "尚未登录","0");
        }
        return  this.volunteeringService.signOutVolunteer(o,category,user);
    }

    /**
     * 获取志愿者服务的被服务对象
     * @param projectId
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/findTarget", notes = "获取志愿者服务的被服务对象")
    @GetMapping(value = "/findTarget", produces = "application/json;charset=UTF-8")
    public ResponseDTO findTarget(String projectId ,String category) throws Exception {
        return  actFamilyVolunteerService.findTarget(projectId,this.getCurUserProp(),category);
    }

    /**
     * 提交志愿服务
     */
    @ApiOperation(value = "/submitVoluntaryService", notes = "提交志愿服务")
    @PostMapping(value = "/submitVoluntaryService", produces = "application/json;charset=UTF-8")
    public ResponseDTO submitVoluntaryService(@RequestBody String jsons) throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null ) {
            return new ResponseDTO(ResultCode.FAIL, "尚未登录","0");
        }
        return  volunteeringService.submitVoluntaryServices(jsons,user);
    }

    /**
     * 已经提交志愿服务查看详情
     */
    @ApiOperation(value = "/selectByActivity", notes = "已经提交志愿服务查看详情")
    @GetMapping(value = "/selectByActivity", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectActivityVo> selectByActivity(String id) {
        return volunteeringService.selectByActivityId(id);
    }


    /**
     * 已经提交志愿服务列表--活动详情
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/activityPage", notes = "已经提交志愿服务列表，支持分页查询")
    @GetMapping(value = "/activityPage", produces = "application/json;charset=UTF-8")
    public ResponseDTO activityPage(ActProjectActivityQVo condition, PageParam page) throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null ) {
            return new ResponseDTO(ResultCode.FAIL, "尚未登录","0");
        }
        condition.setCreateUserId(user.getUserId());
        PageDTO <ActProjectActivityVo> rst=this.actProjectActivityService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * 我的志愿服务列表
     * @param status 状态
     * @return
     */
    @ApiOperation(value = "/findMyVolunteerServiceList", notes = "我的志愿服务列表，支持分页查询")
    @GetMapping(value = "/findMyVolunteerServiceList", produces = "application/json;charset=UTF-8")
    public ResponseDTO findMyVolunteerServiceList(String status,PageParam page) {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (CommonUtils.isBlank(status) ) {
            return new ResponseDTO(ResultCode.FAIL, "状态不能为空");
        }
        if (user == null ) {
            return new ResponseDTO(ResultCode.FAIL, "尚未登录","0");
        }
        PageDTO<ActProjectVo> rst = this.volunteeringService.findMyVolunteerServiceList(user.getUserId(),status, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取志愿者详细信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/volunteerById", notes = "根据主键获取 群众/志愿者")
    @GetMapping(value = "/volunteerById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<BaseVolunteerVo> volunteerById() throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null ) {
            return new ResponseDTO(ResultCode.FAIL, "尚未登录","0");
        }
        return this.baseVolunteerService.getById(user.getUserId());
    }


    /**
     * 获取微信用户头像
     * @param userId 当前登录用户id
     * @return
     */
    @ApiOperation(value = "/selectWechatHeadimgurl", notes = "获取微信用户头像")
    @GetMapping(value = "/selectWechatHeadimgurl", produces = "application/json;charset=UTF-8")
    public ResponseDTO selectWechatHeadimgurl(String userId) {
        //登录信息
        UserProp user = this.getCurUserProp();
        String img=volunteeringService.selectWechatHeadimgurl(null == user ? userId : user.getUserId());
        return new ResponseDTO(ResultCode.SUCCESS, "成功",img);

    }

}
