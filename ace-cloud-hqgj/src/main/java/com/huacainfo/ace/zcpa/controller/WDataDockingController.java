package com.huacainfo.ace.zcpa.controller;



import com.huacainfo.ace.common.dto.ResponseDTO;

import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.zcpa.dataVo.ActivityVo;
import com.huacainfo.ace.zcpa.dataVo.DataDockingQVo;

import com.huacainfo.ace.zcpa.dataVo.FamilyTargetListVo;
import com.huacainfo.ace.zcpa.dataVo.FamilyTargetVo;
import com.huacainfo.ace.zcpa.service.DataDockingService;

import com.huacainfo.ace.zcpa.vo.BaseVolunteerQVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @ClassName WDataDockingController
 * @Description TODO
 * @Author heshaung
 * @Date 2019/8/28 9:05
 */
@Api(value = "/www/DataDocking", tags = "数据对接")
@RestController
@RequestMapping("/www/DataDocking")
public class WDataDockingController extends BizController {
    @Autowired
    private DataDockingService dataDockingService;


    /**
     * 统计平安志愿者数量
     * @param condition 开始时间,结束时间
     * @return
     */
    @ApiOperation(value = "/findVolunteerNum", notes = "统计平安志愿者数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "Date", paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "Date", paramType = "form")
    })
    @RequestMapping(value = "/findVolunteerNum", produces = "application/json;charset=UTF-8")
    public ResponseDTO findVolunteerNum(DataDockingQVo condition)throws Exception{
        return this.dataDockingService.findVolunteerNum(condition);
    }


    /**
     * 统计项目活动数量
     * @param condition
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/findProjectNum", notes = "统计项目活动数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "Date", paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "Date", paramType = "form")
    })
    @RequestMapping(value = "/findProjectNum", produces = "application/json;charset=UTF-8")
    public ResponseDTO findProjectNum(DataDockingQVo condition) throws Exception {
        return this.dataDockingService.findProjectNum(condition);
    }


    /**
     * 情暖家庭志愿服务的特殊人群名单--情暖家庭
     * @param condition
     * @return
     */
    @ApiOperation(value = "/findFamilyTargetList", notes = "情暖家庭志愿服务的特殊人群名单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "Date", paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "Date", paramType = "form")
    })
    @RequestMapping(value = "/findFamilyTargetList", produces = "application/json;charset=UTF-8")
    public ResponseDTO<FamilyTargetVo> findFamilyTargetList(DataDockingQVo condition) throws Exception {
        return this.dataDockingService.findFamilyTargetList(condition);
    }


    /**
     * 特殊人群的平安志愿者名单 -- 情暖家庭
     * @param condition
     * @return
     */
    @ApiOperation(value = "/findFamilyVolunteertList", notes = "特殊人群的平安志愿者名单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "Date", paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "Date", paramType = "form")
    })
    @RequestMapping(value = "/findFamilyVolunteertList", produces = "application/json;charset=UTF-8")
    public ResponseDTO<FamilyTargetListVo> findFamilyVolunteertList(DataDockingQVo condition) throws Exception {
        return this.dataDockingService.findFamilyVolunteertList(condition);
    }

    /**
     * 特殊人群的志愿服务详情数据--情暖家庭
     * @param condition
     * @return
     */
    @ApiOperation(value = "/findActivityList", notes = "特殊人群的志愿服务详情数据--情暖家庭")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "Date", paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "Date", paramType = "form")
    })
    @RequestMapping(value = "/findActivityList", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActivityVo> findActivityList(DataDockingQVo condition) throws Exception {
        return this.dataDockingService.findActivityList(condition);
    }


    /**
     * 推送志愿者列表
     * @param condition
     * @return
     */
    @ApiOperation(value = "/findVolunteerList", notes = "特殊人群的志愿服务详情数据--情暖家庭")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "Date", paramType = "form"),
    })
    @RequestMapping(value = "/findVolunteerList", produces = "application/json;charset=UTF-8")
    public ResponseDTO findVolunteerList(BaseVolunteerQVo condition)throws Exception  {
        return this.dataDockingService.findVolunteerList(condition);
    }
}
