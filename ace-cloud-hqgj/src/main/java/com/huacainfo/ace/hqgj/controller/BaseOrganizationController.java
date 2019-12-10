package com.huacainfo.ace.hqgj.controller;

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
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.service.BaseOrganizationService;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationVo;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationQVo;


/**
* @author: 豆文国
* @version: 2019-12-09
* @Description:  TODO(企业管理)
*/
@Api(value = "/baseOrganization",tags = "企业管理")
@RestController
@RequestMapping("/baseOrganization")
public class BaseOrganizationController extends BaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private BaseOrganizationService baseOrganizationService;


/**
*
* @Title:page
* @Description:  TODO(分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       NewPageDTO
<BaseOrganizationVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-12-09
    */
    @ApiOperation(value = "/page", notes = "获取企业管理数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
    <BaseOrganizationVo> page(BaseOrganizationQVo condition, PageParam page) throws Exception {

        PageDTO<BaseOrganizationVo> rst =this.baseOrganizationService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
            }
            return rst;
            }

            /**
            *
            * @Title:insertBaseOrganization
            * @Description: TODO(创建企业管理)
            * @param: @param jsons
            * @return: ResponseDTO
            * @author: 豆文国
            * @version: 2019-12-09
            */
        @ApiOperation(value ="/create",notes = "创建企业管理")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "BaseOrganization的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
            public ResponseDTO create(@RequestBody String jsons) throws Exception {
            BaseOrganization obj = JSON.parseObject(jsons, BaseOrganization.class);
            return this.baseOrganizationService.create(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:updateBaseOrganization
            * @Description: TODO(更新企业管理)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2019-12-09
            */
        @ApiOperation(value ="/update",notes = "更新企业管理")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "BaseOrganization的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
            public ResponseDTO update(String jsons) throws Exception {
            BaseOrganization obj = JSON.parseObject(jsons, BaseOrganization.class);
            return this.baseOrganizationService.update(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取企业管理)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<BaseOrganization>
            * @throws
            * @author: 豆文国
            * @version: 2019-12-09
            */
        @ApiOperation(value ="/getById",notes = "根据主键获取 企业管理")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
        })
        @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
            public ResponseDTO<BaseOrganizationVo> getById(String id)throws Exception {
                return this.baseOrganizationService.getById(id);
                }

                /**
                *
                * @Title:deleteBaseOrganizationByBaseOrganizationId
                * @Description: TODO(删除企业管理)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-12-09
                */
            @ApiOperation(value ="/deleteById",notes = "根据主键删除 企业管理")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            })
            @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteById(String id) throws Exception {
                return this.baseOrganizationService.deleteById(id);
                }


                /**
                *
                * @Title:deleteByIds
                * @Description: TODO(批量删除企业管理)
                * @param: @param ids
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-12-09
                */
            @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 企业管理")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
            "form"),
            })
            @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteByIds(String ids) throws Exception {
                    return this.baseOrganizationService.deleteByIds(ids.split(","));
                }

}
