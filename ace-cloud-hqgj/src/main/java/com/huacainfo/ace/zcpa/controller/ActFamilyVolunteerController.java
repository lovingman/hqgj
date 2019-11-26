package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
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
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.zcpa.model.ActFamilyVolunteer;
import com.huacainfo.ace.zcpa.service.ActFamilyVolunteerService;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerVo;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerQVo;

import java.util.List;


/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目志愿者列表 < 限情暖家庭 >)
 */
@Api(value = "/actFamilyVolunteer", tags = "项目志愿者列表<限情暖家庭>")
@RestController
@RequestMapping("/actFamilyVolunteer")
public class ActFamilyVolunteerController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActFamilyVolunteerService actFamilyVolunteerService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActFamilyVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/page", notes = "获取项目志愿者列表<限情暖家庭>数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public NewPageDTO
            <ActFamilyVolunteerVo> page(ActFamilyVolunteerQVo condition, PageParam page) throws Exception {
        return this.actFamilyVolunteerService.page(condition, page.getPageNum(), page.getPageSize(), page.getOrderBy());

    }

    /**
     * @Title:insertActFamilyVolunteer
     * @Description: TODO(创建项目志愿者列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/create", notes = "创建项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActFamilyVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody  String jsons) throws Exception {
       // ActFamilyVolunteer obj = JSON.parseObject(jsons, ActFamilyVolunteer.class);
        return this.actFamilyVolunteerService.create(jsons, this.getCurUserProp());
    }


    /**
     * @throws
     * @Title:updateActFamilyVolunteer
     * @Description: TODO(更新项目志愿者列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/update", notes = "更新项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActFamilyVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ActFamilyVolunteer obj = JSON.parseObject(jsons, ActFamilyVolunteer.class);
        return this.actFamilyVolunteerService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目志愿者列表 < 限情暖家庭 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActFamilyVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActFamilyVolunteerVo> getById(String id) throws Exception {
        return this.actFamilyVolunteerService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteActFamilyVolunteerByActFamilyVolunteerId
     * @Description: TODO(删除项目志愿者列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actFamilyVolunteerService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目志愿者列表 < 限情暖家庭 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actFamilyVolunteerService.deleteByIds(ids.split(","));
    }

    /**
     * 被服务对象关联的志愿者
     * @param projectId
     * @param targetId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/selectByProjectId", produces = "application/json;charset=UTF-8")
    public List<ActFamilyVolunteer> selectByProjectId(String projectId, String targetId) throws Exception{
        return this.actFamilyVolunteerService.selectByProjectId(projectId,targetId);
    }


    /**
     * 批量修改
     * @param ids
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/batchUpdate", produces = "application/json;charset=UTF-8")
    public ResponseDTO batchUpdate(String ids) throws Exception {
        return actFamilyVolunteerService.batchUpdate(ids.split(","));
    }


/******************************/

    /**
     * 查找志愿者参与的项目
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/findProjectId", notes = "查找志愿者参与的项目")
    @GetMapping(value = "/findProjectId", produces = "application/json;charset=UTF-8")
    public ResponseDTO<List> findProjectId(String state) throws Exception {
        UserProp userProp =this.getCurUserProp();
        return actFamilyVolunteerService.findProjectId(userProp.getUserId(),state);
    }


}
