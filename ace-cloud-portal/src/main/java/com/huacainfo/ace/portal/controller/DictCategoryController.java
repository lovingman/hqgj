package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.DictCategory;
import com.huacainfo.ace.portal.service.DictCategoryService;
import com.huacainfo.ace.portal.vo.DictCategoryQVo;
import com.huacainfo.ace.portal.vo.DictCategoryVo;
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

import java.util.List;


/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(数据字典分类信息)
 */
@Api(value = "/dictCategory", tags = "数据字典分类信息")
@RestController
@RequestMapping("/dictCategory")
public class DictCategoryController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DictCategoryService dictCategoryService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO <DictCategoryVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/page", notes = "获取数据字典分类信息数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<DictCategoryVo> page(DictCategoryQVo condition, PageParam page) throws Exception {
        PageDTO<DictCategoryVo> rst = this.dictCategoryService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertDictCategory
     * @Description: TODO(创建数据字典分类信息)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/create", notes = "创建数据字典分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "DictCategory的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        DictCategory obj = JSON.parseObject(jsons, DictCategory.class);
        return this.dictCategoryService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateDictCategory
     * @Description: TODO(更新数据字典分类信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/update", notes = "更新数据字典分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "DictCategory的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        DictCategory obj = JSON.parseObject(jsons, DictCategory.class);
        return this.dictCategoryService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取数据字典分类信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<DictCategory>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 数据字典分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<DictCategoryVo> getById(String id) throws Exception {
        return this.dictCategoryService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteDictCategoryByDictCategoryId
     * @Description: TODO(删除数据字典分类信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 数据字典分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.dictCategoryService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除数据字典分类信息)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 数据字典分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.dictCategoryService.deleteByIds(ids.split(","));
    }


    @ApiOperation(value = "/findDictCategoryListAll", notes = "根据当前登录者的系统ID，获取对应该系统的全部数据字典分类信息")
    @GetMapping(value = "/findDictCategoryListAll", produces = "application/json;charset=UTF-8")
    public List<DictCategory> findDictCategoryListAll() throws Exception {
        return this.dictCategoryService.findDictCategoryListAll(this.getCurUserProp());
    }

}
