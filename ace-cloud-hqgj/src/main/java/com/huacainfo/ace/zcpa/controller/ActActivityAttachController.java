package com.huacainfo.ace.zcpa.controller;

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
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.zcpa.model.ActActivityAttach;
import com.huacainfo.ace.zcpa.service.ActActivityAttachService;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachVo;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachQVo;


/**
* @author: 豆文国
* @version: 2019-07-31
* @Description:  TODO(活动附件)
*/
@Api(value = "/actActivityAttach",tags = "活动附件")
@RestController
@RequestMapping("/actActivityAttach")
public class ActActivityAttachController extends BaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private ActActivityAttachService actActivityAttachService;


/**
*
* @Title:page
* @Description:  TODO(分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       NewPageDTO
<ActActivityAttachVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-07-31
    */
    @ApiOperation(value = "/page", notes = "获取活动附件数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public NewPageDTO
    <ActActivityAttachVo> page(ActActivityAttachQVo condition, PageParam page) throws Exception {
        return this.actActivityAttachService.page(condition, page.getPageNum(), page.getPageSize(), page.getOrderBy());

            }

            /**
            *
            * @Title:insertActActivityAttach
            * @Description: TODO(创建活动附件)
            * @param: @param jsons
            * @return: ResponseDTO
            * @author: 豆文国
            * @version: 2019-07-31
            */
        @ApiOperation(value ="/create",notes = "创建活动附件")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "ActActivityAttach的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
            public ResponseDTO create(String jsons) throws Exception {
            ActActivityAttach obj = JSON.parseObject(jsons, ActActivityAttach.class);
            return this.actActivityAttachService.create(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:updateActActivityAttach
            * @Description: TODO(更新活动附件)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2019-07-31
            */
        @ApiOperation(value ="/update",notes = "更新活动附件")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "ActActivityAttach的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
            public ResponseDTO update(String jsons) throws Exception {
            ActActivityAttach obj = JSON.parseObject(jsons, ActActivityAttach.class);
            return this.actActivityAttachService.update(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取活动附件)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<ActActivityAttach>
            * @throws
            * @author: 豆文国
            * @version: 2019-07-31
            */
        @ApiOperation(value ="/getById",notes = "根据主键获取 活动附件")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
        })
        @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
            public ResponseDTO<ActActivityAttachVo> getById(String id)throws Exception {
                return this.actActivityAttachService.getById(id);
                }

                /**
                *
                * @Title:deleteActActivityAttachByActActivityAttachId
                * @Description: TODO(删除活动附件)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-07-31
                */
            @ApiOperation(value ="/deleteById",notes = "根据主键删除 活动附件")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            })
            @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteById(String id) throws Exception {
                return this.actActivityAttachService.deleteById(id);
                }


                /**
                *
                * @Title:deleteByIds
                * @Description: TODO(批量删除活动附件)
                * @param: @param ids
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-07-31
                */
            @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 活动附件")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
            "form"),
            })
            @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteByIds(String ids) throws Exception {
                    return this.actActivityAttachService.deleteByIds(ids.split(","));
                }

}
