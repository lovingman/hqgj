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
import com.huacainfo.ace.hqgj.model.LawPolicy;
import com.huacainfo.ace.hqgj.service.LawPolicyService;
import com.huacainfo.ace.hqgj.vo.LawPolicyVo;
import com.huacainfo.ace.hqgj.vo.LawPolicyQVo;


/**
* @author: 豆文国
* @version: 2019-12-10
* @Description:  TODO(政策服务)
*/
@Api(value = "/lawPolicy",tags = "政策服务")
@RestController
@RequestMapping("/lawPolicy")
public class LawPolicyController extends BaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private LawPolicyService lawPolicyService;


/**
*
* @Title:page
* @Description:  TODO(分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       NewPageDTO
<LawPolicyVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-12-10
    */
    @ApiOperation(value = "/page", notes = "获取政策服务数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
    <LawPolicyVo> page(LawPolicyQVo condition, PageParam page) throws Exception {

        PageDTO<LawPolicyVo> rst =this.lawPolicyService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
            }
            return rst;
            }

            /**
            *
            * @Title:insertLawPolicy
            * @Description: TODO(创建政策服务)
            * @param: @param jsons
            * @return: ResponseDTO
            * @author: 豆文国
            * @version: 2019-12-10
            */
        @ApiOperation(value ="/create",notes = "创建政策服务")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "LawPolicy的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
            public ResponseDTO create(@RequestBody String jsons) throws Exception {
            LawPolicy obj = JSON.parseObject(jsons, LawPolicy.class);
            return this.lawPolicyService.create(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:updateLawPolicy
            * @Description: TODO(更新政策服务)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2019-12-10
            */
        @ApiOperation(value ="/update",notes = "更新政策服务")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "LawPolicy的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
            public ResponseDTO update(@RequestBody String jsons) throws Exception {
            LawPolicy obj = JSON.parseObject(jsons, LawPolicy.class);
            return this.lawPolicyService.update(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取政策服务)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<LawPolicy>
            * @throws
            * @author: 豆文国
            * @version: 2019-12-10
            */
        @ApiOperation(value ="/getById",notes = "根据主键获取 政策服务")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
        })
        @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
            public ResponseDTO<LawPolicyVo> getById(String id)throws Exception {
                return this.lawPolicyService.getById(id);
                }

                /**
                *
                * @Title:deleteLawPolicyByLawPolicyId
                * @Description: TODO(删除政策服务)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-12-10
                */
            @ApiOperation(value ="/deleteById",notes = "根据主键删除 政策服务")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            })
            @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteById(String id) throws Exception {
                return this.lawPolicyService.deleteById(id);
                }


                /**
                *
                * @Title:deleteByIds
                * @Description: TODO(批量删除政策服务)
                * @param: @param ids
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-12-10
                */
            @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 政策服务")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
            "form"),
            })
            @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteByIds(String ids) throws Exception {
                    return this.lawPolicyService.deleteByIds(ids.split(","));
                }

}
