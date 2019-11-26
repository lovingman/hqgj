package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.service.VolunteeringService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.zcpa.model.ActProjectActivity;
import com.huacainfo.ace.zcpa.service.ActProjectActivityService;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityVo;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityQVo;


/**
 * @author: 王恩
 * @version: 2019-07-19
 * @Description: TODO(项目活动)
 */
@Api(value = "/actProjectActivity", tags = "项目志愿者活动详情")
@RestController
@RequestMapping("/actProjectActivity")
public class ActProjectActivityController extends BaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActProjectActivityService actProjectActivityService;
    @Autowired
    private VolunteeringService volunteeringService;

    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectVo>
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/page", notes = "项目志愿者活动详情数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ActProjectActivityVo> page(ActProjectActivityQVo condition, PageParam page) throws Exception {
        PageDTO <ActProjectActivityVo> rst=this.actProjectActivityService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * @Title:insertActProject
     * @Description: TODO(创建项目活动)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/create", notes = "创建项目志愿者活动详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActProject的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ActProjectActivity obj = JSON.parseObject(jsons, ActProjectActivity.class);
        return this.actProjectActivityService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActProject
     * @Description: TODO(更新项目活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/update", notes = "更新项目志愿者活动详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActProject的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody  String jsons) throws Exception {
        ActProjectActivity obj = JSON.parseObject(jsons, ActProjectActivity.class);
        return this.actProjectActivityService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProject>
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目志愿者活动详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectActivityVo> getById(String id) throws Exception {
        return this.actProjectActivityService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteActProjectByActProjectId
     * @Description: TODO(删除项目活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目志愿者活动详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actProjectActivityService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目志愿者活动详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actProjectActivityService.deleteByIds(ids.split(","));
    }


    /**
     * 修改项目活动状态
     * @param actProjectActivity
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/updateProjectState", notes = "根据主键修改项目活动状态")
    @PostMapping(value = "/updateProjectState", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateProjectState(@RequestBody  ActProjectActivity actProjectActivity)throws Exception {
        return  this.actProjectActivityService.updateProjectState(actProjectActivity);
    }


    /**
     * 已经提交志愿服务查看详情
     */
    @ApiOperation(value = "/selectByActivity", notes = "已经提交志愿服务查看详情")
    @GetMapping(value = "/selectByActivity", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectActivityVo> selectByActivity(String id) {
        return volunteeringService.selectByActivityId(id);
    }
}
