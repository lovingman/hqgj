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
import com.huacainfo.ace.hqgj.model.CompanyMaterial;
import com.huacainfo.ace.hqgj.service.CompanyMaterialService;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialVo;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialQVo;


/**
* @author: 豆文国
* @version: 2020-02-21
* @Description:  TODO(企业物资表)
*/
@Api(value = "/companyMaterial",tags = "企业物资表")
@RestController
@RequestMapping("/companyMaterial")
public class CompanyMaterialController extends BaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private CompanyMaterialService companyMaterialService;


/**
*
* @Title:page
* @Description:  TODO(分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       NewPageDTO
<CompanyMaterialVo>
    * @throws
    * @author: 豆文国
    * @version: 2020-02-21
    */
    @ApiOperation(value = "/page", notes = "获取企业物资表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
    <CompanyMaterialVo> page(CompanyMaterialQVo condition, PageParam page) throws Exception {

        PageDTO<CompanyMaterialVo> rst =this.companyMaterialService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
            }
            return rst;
            }

            /**
            *
            * @Title:insertCompanyMaterial
            * @Description: TODO(创建企业物资表)
            * @param: @param jsons
            * @return: ResponseDTO
            * @author: 豆文国
            * @version: 2020-02-21
            */
        @ApiOperation(value ="/create",notes = "创建企业物资表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "CompanyMaterial的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
            public ResponseDTO create(@RequestBody String jsons) throws Exception {
            CompanyMaterial obj = JSON.parseObject(jsons, CompanyMaterial.class);
            return this.companyMaterialService.create(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:updateCompanyMaterial
            * @Description: TODO(更新企业物资表)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2020-02-21
            */
        @ApiOperation(value ="/update",notes = "更新企业物资表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "CompanyMaterial的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
            public ResponseDTO update(@RequestBody String jsons) throws Exception {
            CompanyMaterial obj = JSON.parseObject(jsons, CompanyMaterial.class);
            return this.companyMaterialService.update(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取企业物资表)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<CompanyMaterial>
            * @throws
            * @author: 豆文国
            * @version: 2020-02-21
            */
        @ApiOperation(value ="/getById",notes = "根据主键获取 企业物资表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
        })
        @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
            public ResponseDTO<CompanyMaterialVo> getById(String id)throws Exception {
                return this.companyMaterialService.getById(id);
                }

                /**
                *
                * @Title:deleteCompanyMaterialByCompanyMaterialId
                * @Description: TODO(删除企业物资表)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2020-02-21
                */
            @ApiOperation(value ="/deleteById",notes = "根据主键删除 企业物资表")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            })
            @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteById(String id) throws Exception {
                return this.companyMaterialService.deleteById(id);
                }


                /**
                *
                * @Title:deleteByIds
                * @Description: TODO(批量删除企业物资表)
                * @param: @param ids
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2020-02-21
                */
            @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 企业物资表")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
            "form"),
            })
            @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteByIds(String ids) throws Exception {
                    return this.companyMaterialService.deleteByIds(ids.split(","));
                }

}
