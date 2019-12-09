package com.huacainfo.ace.hqgj.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.hqgj.vo.BaseCompanyExcelVo;
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
import com.huacainfo.ace.hqgj.model.BaseCompany;
import com.huacainfo.ace.hqgj.service.BaseCompanyService;
import com.huacainfo.ace.hqgj.vo.BaseCompanyVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyQVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: 何双
 * @version: 2019-12-06
 * @Description: TODO(企业管理)
 */
@Api(value = "/baseCompany", tags = "企业管理")
@RestController
@RequestMapping("/baseCompany")
public class BaseCompanyController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseCompanyService baseCompanyService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseCompanyVo>
     * @author: 何双
     * @version: 2019-12-06
     */
    @ApiOperation(value = "/page", notes = "获取企业管理数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<BaseCompanyVo> page(BaseCompanyQVo condition, PageParam page) throws Exception {

        PageDTO<BaseCompanyVo> rst = this.baseCompanyService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertBaseCompany
     * @Description: TODO(创建企业管理)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    @ApiOperation(value = "/create", notes = "创建企业管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseCompany的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        BaseCompany obj = JSON.parseObject(jsons, BaseCompany.class);
        return this.baseCompanyService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBaseCompany
     * @Description: TODO(更新企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    @ApiOperation(value = "/update", notes = "更新企业管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseCompany的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        BaseCompany obj = JSON.parseObject(jsons, BaseCompany.class);
        return this.baseCompanyService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseCompany>
     * @author: 何双
     * @version: 2019-12-06
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 企业管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<BaseCompanyVo> getById(String id) throws Exception {
        return this.baseCompanyService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteBaseCompanyByBaseCompanyId
     * @Description: TODO(删除企业管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 企业管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.baseCompanyService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 企业管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.baseCompanyService.deleteByIds(ids.split(","));
    }

    /**
     * 导入
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/importXls", produces = "application/json;charset=UTF-8")
    public ResponseDTO importXls(@RequestParam MultipartFile file) throws Exception {
        List<Object> list = EasyExcelFactory.read(file.getInputStream(), new Sheet(1,1, BaseCompanyExcelVo.class));
        return this.baseCompanyService.importXls(list,this.getCurUserProp());
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
        Sheet sheet=new Sheet(1,1,BaseCompanyExcelVo.class);

        BaseCompanyQVo condition=new BaseCompanyQVo();
        PageDTO<BaseCompanyVo> rst = this.baseCompanyService.page(condition, 1, 10000, null);
        List<BaseCompanyExcelVo> data=new ArrayList();
        for(BaseCompanyVo o:rst.getRows()){
            BaseCompanyExcelVo obj=new BaseCompanyExcelVo();
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
