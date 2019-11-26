package com.huacainfo.ace.portal.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.service.ProvinceService;
import com.huacainfo.ace.portal.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(行政区划)
 */
@Api(value = "/province", tags = "行政区划")
@RestController
@RequestMapping("/province")
public class ProvinceController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProvinceService provinceService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO<ProvinceVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/page", notes = "获取行政区划数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ProvinceVo> page(ProvinceQVo condition, PageParam page) throws Exception {
        if (!CommonUtils.isBlank(condition.getParentCode())) {
            condition.setParentCode(this.getCurUserProp().getAreaCode());
        }

        PageDTO<ProvinceVo> rst = this.provinceService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertProvince
     * @Description: TODO(创建行政区划)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/create", notes = "创建行政区划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Province的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Province obj = JSON.parseObject(jsons, Province.class);
        return this.provinceService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateProvince
     * @Description: TODO(更新行政区划)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/update", notes = "更新行政区划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Province的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Province obj = JSON.parseObject(jsons, Province.class);
        return this.provinceService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取行政区划)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Province>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 行政区划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ProvinceVo> getById(String id) throws Exception {
        return this.provinceService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteProvinceByProvinceId
     * @Description: TODO(删除行政区划)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 行政区划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.provinceService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除行政区划)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 行政区划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.provinceService.deleteByIds(ids.split(","));
    }
    @GetMapping(value = "/getListByPid", produces = "application/json;charset=UTF-8")
    public ListDTO<Map<String,Object>> getListByPid(String pid) throws Exception{
        return this.provinceService.getListByPid(pid);
    }

    /**
     * execel导入接口
     *
     * @param file 文件流数组
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/importXls", notes = "execel导入接口", response = ResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件流数组", dataType = "MultipartFile[]", paramType = "form"),
    })
    @PostMapping(value = "/importXls", produces = "application/json;charset=UTF-8")
    public ResponseDTO importXls(@RequestParam MultipartFile file) throws Exception {
        List<Object> list = EasyExcelFactory.read(file.getInputStream(), new Sheet(1,1, ProvinceExcelVo.class));
        return this.provinceService.importXls(list,this.getCurUserProp());
    }
    @RequestMapping(value = "/exportXls",  method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response) throws Exception {
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=default.xlsx");
        Sheet sheet=new Sheet(1,1,ProvinceExcelVo.class);
        ProvinceQVo condition=new ProvinceQVo();
        PageDTO<ProvinceVo> rst = this.provinceService.page(condition, 1, 10000, null);
        List<ProvinceExcelVo> data=new ArrayList();
        for(ProvinceVo o:rst.getRows()){
            ProvinceExcelVo obj=new ProvinceExcelVo();
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
