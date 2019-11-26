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
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.service.DictService;
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
 * @Description: TODO(数据字典)
 */
@Api(value = "/dict", tags = "数据字典")
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DictService dictService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO
     * <DictVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/page", notes = "获取数据字典数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<DictVo> page(DictQVo condition, PageParam page) throws Exception {
        PageDTO<DictVo> rst = this.dictService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertDict
     * @Description: TODO(创建数据字典)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/create", notes = "创建数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Dict的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        jsons = jsons.replaceAll("_empty", "");

        Dict obj = JSON.parseObject(jsons, Dict.class);
        return this.dictService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateDict
     * @Description: TODO(更新数据字典)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/update", notes = "更新数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Dict的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Dict obj = JSON.parseObject(jsons, Dict.class);
        return this.dictService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取数据字典)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Dict>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<DictVo> getById(String id) throws Exception {
        return this.dictService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteDictByDictId
     * @Description: TODO(删除数据字典)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.dictService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除数据字典)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.dictService.deleteByIds(ids.split(","));
    }

    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     * @return ResponseDTO<Map < String, List < DictVo>>>
     */
    @ApiOperation(value = "/getByCategoryIds",
            notes = "根据字典分类ID获取数据字典对象，可以一次获取多个",
            response = Dict.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryIds", value = "多个categoryIds以','分开", required = true, dataType = "String",
                    paramType = "query"),
    })
    @GetMapping(value = "/getByCategoryIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO<Map<String, List<Dict>>> getByCategoryIds(String categoryIds) throws Exception {
        if (CommonUtils.isBlank(categoryIds)) {
            return new ResponseDTO<>(ResponseDTO.FAILED, "缺少必要参数");
        }

        return this.dictService.getByCategoryIds(categoryIds.split(","));
    }


    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     * @return ResponseDTO<Map < String, Map < String, String>>>
     */
    @ApiOperation(value = "/getDictKeyMap", notes = "根据字典分类ID获取数据字典对象，可以一次获取多个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryIds", value = "多个categoryIds以','分开", required = true, dataType = "String",
                    paramType = "query"),
    })
    @GetMapping(value = "/getDictKeyMap", produces = "application/json;charset=UTF-8")
    public ResponseDTO getDictKeyMap(String categoryIds) throws Exception {
        if (CommonUtils.isBlank(categoryIds)) {
            return new ResponseDTO<>(ResponseDTO.FAILED, "缺少必要参数");
        }

        return this.dictService.getDictKeyMap(categoryIds.split(","));
    }

    /**
     * @Description: TODO(获取宗教场所资源树)
     * @return: List<Tree>
     */
    @ApiOperation(value = "/getResTreeList", notes = "获取资源树", response = Tree.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topId", value = "资源顶级ID，默认为'0'", defaultValue = "0", dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/getResTreeList", produces = "application/json;charset=UTF-8")
    public ListDTO<Tree> getResTreeList(String topId, String categoryId) {
        topId = CommonUtils.isBlank(topId) ? "0" : topId;
        return this.dictService.getResTreeList(topId,categoryId);
    }

    @PostMapping(value = "/importXls", produces = "application/json;charset=UTF-8")
    public ResponseDTO importXls(@RequestParam MultipartFile file,String categoryId) throws Exception {
        List<Object> list = EasyExcelFactory.read(file.getInputStream(), new Sheet(1,1, DictExcelVo.class));
        return this.dictService.importXls(list,categoryId);
    }
    @RequestMapping(value = "/exportXls",  method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response) throws Exception {

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=default.xlsx");
        Sheet sheet=new Sheet(1,1,DictExcelVo.class);

        DictQVo condition=new DictQVo();
        PageDTO<DictVo> rst = this.dictService.page(condition, 1, 10000, null);
        List<DictExcelVo> data=new ArrayList();
        for(DictVo o:rst.getRows()){
            DictExcelVo obj=new DictExcelVo();
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
