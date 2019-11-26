package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.RoleResources;
import com.huacainfo.ace.portal.service.RoleResourcesService;
import com.huacainfo.ace.portal.vo.RoleResourcesQVo;
import com.huacainfo.ace.portal.vo.RoleResourcesVo;
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
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(角色资源)
 */
@Api(value = "/roleResources", tags = "角色资源")
@RestController
@RequestMapping("/roleResources")
public class RoleResourcesController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleResourcesService roleResourcesService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO
     * <RoleResourcesVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/page", notes = "获取角色资源数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<RoleResourcesVo> page(RoleResourcesQVo condition, PageParam page) throws Exception {
        PageDTO<RoleResourcesVo> rst = this.roleResourcesService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertRoleResources
     * @Description: TODO(创建角色资源)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/create", notes = "创建角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "RoleResources的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        RoleResources obj = JSON.parseObject(jsons, RoleResources.class);
        return this.roleResourcesService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRoleResources
     * @Description: TODO(更新角色资源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/update", notes = "更新角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "RoleResources的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        RoleResources obj = JSON.parseObject(jsons, RoleResources.class);
        return this.roleResourcesService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取角色资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<RoleResources>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<RoleResourcesVo> getById(String id) throws Exception {
        return this.roleResourcesService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteRoleResourcesByRoleResourcesId
     * @Description: TODO(删除角色资源)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.roleResourcesService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除角色资源)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 角色资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.roleResourcesService.deleteByIds(ids.split(","));
    }

}
