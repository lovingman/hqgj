package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
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

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.dto.ResponseDTO;
//import com.huacainfo.ace.common.web.controller.zcpaController;
import com.huacainfo.ace.zcpa.model.BaseSocialOrg;
import com.huacainfo.ace.zcpa.service.BaseSocialOrgService;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgVo;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgQVo;


/**
* @author: 豆文国
* @version: 2019-09-25
* @Description:  TODO(志愿者社会组织)
*/
@Api(value = "/baseSocialOrg",tags = "志愿者社会组织")
@RestController
@RequestMapping("/baseSocialOrg")
public class BaseSocialOrgController extends BaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private BaseSocialOrgService baseSocialOrgService;


/**
*
* @Title:page
* @Description:  TODO(分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       NewPageDTO
<BaseSocialOrgVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-09-25
    */
    @ApiOperation(value = "/page", notes = "获取志愿者社会组织数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<BaseSocialOrgVo> page(BaseSocialOrgQVo condition, PageParam page) throws Exception {
        UserProp userProp=this.getCurUserProp();
        condition.setUserId(userProp.getUserId());
        PageDTO<BaseSocialOrgVo> rst = this.baseSocialOrgService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

            /**
            *
            * @Title:insertBaseSocialOrg
            * @Description: TODO(创建志愿者社会组织)
            * @param: @param jsons
            * @return: ResponseDTO
            * @author: 豆文国
            * @version: 2019-09-25
            */
        @ApiOperation(value ="/create",notes = "创建志愿者社会组织")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "BaseSocialOrg的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
            public ResponseDTO create(String jsons) throws Exception {
            BaseSocialOrg obj = JSON.parseObject(jsons, BaseSocialOrg.class);
            return this.baseSocialOrgService.create(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:updateBaseSocialOrg
            * @Description: TODO(更新志愿者社会组织)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2019-09-25
            */
        @ApiOperation(value ="/update",notes = "更新志愿者社会组织")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "BaseSocialOrg的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
            public ResponseDTO update(String jsons) throws Exception {
            BaseSocialOrg obj = JSON.parseObject(jsons, BaseSocialOrg.class);
            return this.baseSocialOrgService.update(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取志愿者社会组织)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<BaseSocialOrg>
            * @throws
            * @author: 豆文国
            * @version: 2019-09-25
            */
        @ApiOperation(value ="/getById",notes = "根据主键获取 志愿者社会组织")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
        })
        @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
            public ResponseDTO<BaseSocialOrgVo> getById(String id)throws Exception {
                return this.baseSocialOrgService.getById(id);
                }

                /**
                *
                * @Title:deleteBaseSocialOrgByBaseSocialOrgId
                * @Description: TODO(删除志愿者社会组织)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-09-25
                */
            @ApiOperation(value ="/deleteById",notes = "根据主键删除 志愿者社会组织")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            })
            @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteById(String id) throws Exception {
                return this.baseSocialOrgService.deleteById(id);
                }


                /**
                *
                * @Title:deleteByIds
                * @Description: TODO(批量删除志愿者社会组织)
                * @param: @param ids
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-09-25
                */
            @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 志愿者社会组织")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
            "form"),
            })
            @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteByIds(String ids) throws Exception {
                    return this.baseSocialOrgService.deleteByIds(ids.split(","));
                }

}
