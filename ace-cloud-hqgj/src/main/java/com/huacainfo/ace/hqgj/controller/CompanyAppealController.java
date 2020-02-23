package com.huacainfo.ace.hqgj.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.hqgj.vo.CompanyAppealExcelVo;
import com.huacainfo.ace.hqgj.vo.CompanyAppealExlVo;
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
import com.huacainfo.ace.hqgj.model.CompanyAppeal;
import com.huacainfo.ace.hqgj.service.CompanyAppealService;
import com.huacainfo.ace.hqgj.vo.CompanyAppealVo;
import com.huacainfo.ace.hqgj.vo.CompanyAppealQVo;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
* @author: 豆文国
* @version: 2020-02-21
* @Description:  TODO(企业诉求表)
*/
@Api(value = "/companyAppeal",tags = "企业诉求表")
@RestController
@RequestMapping("/companyAppeal")
public class CompanyAppealController extends BaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private CompanyAppealService companyAppealService;


/**
*
* @Title:page
* @Description:  TODO(分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       NewPageDTO
<CompanyAppealVo>
    * @throws
    * @author: 豆文国
    * @version: 2020-02-21
    */
    @ApiOperation(value = "/page", notes = "获取企业诉求表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
    <CompanyAppealVo> page(CompanyAppealQVo condition, PageParam page) throws Exception {

        PageDTO<CompanyAppealVo> rst =this.companyAppealService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
            if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
            }
            return rst;
            }

            /**
            *
            * @Title:insertCompanyAppeal
            * @Description: TODO(创建企业诉求表)
            * @param: @param jsons
            * @return: ResponseDTO
            * @author: 豆文国
            * @version: 2020-02-21
            */
        @ApiOperation(value ="/create",notes = "创建企业诉求表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "CompanyAppeal的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
            public ResponseDTO create(@RequestBody String jsons) throws Exception {
            CompanyAppeal obj = JSON.parseObject(jsons, CompanyAppeal.class);
            return this.companyAppealService.create(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:updateCompanyAppeal
            * @Description: TODO(更新企业诉求表)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2020-02-21
            */
        @ApiOperation(value ="/update",notes = "更新企业诉求表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "CompanyAppeal的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
            public ResponseDTO update(@RequestBody String jsons) throws Exception {
            CompanyAppeal obj = JSON.parseObject(jsons, CompanyAppeal.class);
            return this.companyAppealService.update(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取企业诉求表)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<CompanyAppeal>
            * @throws
            * @author: 豆文国
            * @version: 2020-02-21
            */
        @ApiOperation(value ="/getById",notes = "根据主键获取 企业诉求表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
        })
        @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
            public ResponseDTO<CompanyAppealVo> getById(String id)throws Exception {
                return this.companyAppealService.getById(id);
                }

                /**
                *
                * @Title:deleteCompanyAppealByCompanyAppealId
                * @Description: TODO(删除企业诉求表)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2020-02-21
                */
            @ApiOperation(value ="/deleteById",notes = "根据主键删除 企业诉求表")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            })
            @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteById(String id) throws Exception {
                return this.companyAppealService.deleteById(id);
                }


                /**
                *
                * @Title:deleteByIds
                * @Description: TODO(批量删除企业诉求表)
                * @param: @param ids
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2020-02-21
                */
            @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 企业诉求表")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
            "form"),
            })
            @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteByIds(String ids) throws Exception {
                    return this.companyAppealService.deleteByIds(ids.split(","));
                }

    /**
     *导出
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportXls",  method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response) throws Exception {

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=default.xlsx");
        Sheet sheet=new Sheet(1,1, CompanyAppealExcelVo.class);

        CompanyAppealQVo condition=new CompanyAppealQVo();
        PageDTO<CompanyAppealExlVo> rst = this.companyAppealService.exportPage(condition, 0, 10000, null);
        List<CompanyAppealExcelVo> data=new ArrayList();
        for(CompanyAppealExlVo o:rst.getRows()){
            CompanyAppealExcelVo obj=new CompanyAppealExcelVo();
            CommonBeanUtils.copyProperties(obj,o);
            data.add(obj);
        }
        OutputStream outputStream =response.getOutputStream();
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        writer.write(data,sheet);
        writer.finish();
        outputStream.flush();
    }

}
