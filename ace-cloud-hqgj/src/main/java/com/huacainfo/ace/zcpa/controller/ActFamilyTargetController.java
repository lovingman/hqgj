package com.huacainfo.ace.zcpa.controller;

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
import com.huacainfo.ace.zcpa.model.ActFamilyTarget;
import com.huacainfo.ace.zcpa.service.ActFamilyTargetService;
import com.huacainfo.ace.zcpa.vo.ActFamilyTargetVo;
import com.huacainfo.ace.zcpa.vo.ActFamilyTargetQVo;


/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目被帮扶对象列表 < 限情暖家庭 >)
 */
@Api(value = "/actFamilyTarget", tags = "项目被帮扶对象列表<限情暖家庭>")
@RestController
@RequestMapping("/actFamilyTarget")
public class ActFamilyTargetController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActFamilyTargetService actFamilyTargetService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActFamilyTargetVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/page", notes = "获取项目被帮扶对象列表<限情暖家庭>数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public NewPageDTO
            <ActFamilyTargetVo> page(ActFamilyTargetQVo condition, PageParam page) throws Exception {
        return this.actFamilyTargetService.page(condition, page.getPageNum(), page.getPageSize(), page.getOrderBy());

    }

    /**
     * @Title:insertActFamilyTarget
     * @Description: TODO(创建项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/create", notes = "创建项目被帮扶对象列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActFamilyTarget的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        return this.actFamilyTargetService.create(jsons, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActFamilyTarget
     * @Description: TODO(更新项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/update", notes = "更新项目被帮扶对象列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActFamilyTarget的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ActFamilyTarget obj = JSON.parseObject(jsons, ActFamilyTarget.class);
        return this.actFamilyTargetService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActFamilyTarget>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目被帮扶对象列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActFamilyTargetVo> getById(String id,String category) throws Exception {
        return this.actFamilyTargetService.getById(id, category);
    }

    /**
     * @throws
     * @Title:deleteActFamilyTargetByActFamilyTargetId
     * @Description: TODO(删除项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目被帮扶对象列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actFamilyTargetService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目被帮扶对象列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actFamilyTargetService.deleteByIds(ids.split(","));
    }

}
