package com.huacainfo.ace.zcpa.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.model.ActPerson;
import com.huacainfo.ace.zcpa.service.ActPersonService;
import com.huacainfo.ace.zcpa.vo.ActPersonQVo;
import com.huacainfo.ace.zcpa.vo.ActPersonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目被服务对象 - 人员)
 */
@Api(value = "/actPerson", tags = "项目被服务对象-人员")
@RestController
@RequestMapping("/actPerson")
public class ActPersonController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActPersonService actPersonService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActSchoolVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/page", notes = "获取项目被服务对象-人员数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ActPersonVo> page(ActPersonQVo condition, PageParam page) throws Exception {
        PageDTO<ActPersonVo> rst =this.actPersonService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * @Title:insertActSchool
     * @Description: TODO(创建项目被服务对象 - 人员)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/create", notes = "创建项目被服务对象-人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActSchool的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        ActPerson obj = JSON.parseObject(jsons, ActPerson.class);
        return this.actPersonService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActSchool
     * @Description: TODO(更新项目被服务对象 - 人员)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/update", notes = "更新项目被服务对象-人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActSchool的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ActPerson obj = JSON.parseObject(jsons, ActPerson.class);
        return this.actPersonService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目被服务对象 - 人员)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActSchool>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目被服务对象-人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActPersonVo> getById(String id) throws Exception {
        return this.actPersonService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteActSchoolByActSchoolId
     * @Description: TODO(删除项目被服务对象 - 人员)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目被服务对象-人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actPersonService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目被服务对象 - 人员)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目被服务对象-人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actPersonService.deleteByIds(ids.split(","));
    }

}
