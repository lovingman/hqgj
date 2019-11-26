package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.dto.PageDTO;
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
import com.huacainfo.ace.zcpa.model.BaseUnit;
import com.huacainfo.ace.zcpa.service.BaseUnitService;
import com.huacainfo.ace.zcpa.vo.BaseUnitVo;
import com.huacainfo.ace.zcpa.vo.BaseUnitQVo;


/**
 * @author: 何双
 * @version: 2019-09-27
 * @Description: TODO(平安单位)
 */
@Api(value = "/baseUnit", tags = "平安单位")
@RestController
@RequestMapping("/baseUnit")
public class BaseUnitController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseUnitService baseUnitService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseUnitVo>
     * @author: 何双
     * @version: 2019-09-27
     */
    @ApiOperation(value = "/page", notes = "获取平安单位数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<BaseUnitVo> page(BaseUnitQVo condition, PageParam page) throws Exception {
        PageDTO<BaseUnitVo> rst=this.baseUnitService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * @Title:insertBaseUnit
     * @Description: TODO(创建平安单位)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @ApiOperation(value = "/create", notes = "创建平安单位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseUnit的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        BaseUnit obj = JSON.parseObject(jsons, BaseUnit.class);
        return this.baseUnitService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBaseUnit
     * @Description: TODO(更新平安单位)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @ApiOperation(value = "/update", notes = "更新平安单位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseUnit的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        BaseUnit obj = JSON.parseObject(jsons, BaseUnit.class);
        return this.baseUnitService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取平安单位)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseUnit>
     * @author: 何双
     * @version: 2019-09-27
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 平安单位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<BaseUnitVo> getById(String id) throws Exception {
        return this.baseUnitService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteBaseUnitByBaseUnitId
     * @Description: TODO(删除平安单位)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 平安单位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.baseUnitService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除平安单位)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-09-27
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 平安单位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.baseUnitService.deleteByIds(ids.split(","));
    }

}
