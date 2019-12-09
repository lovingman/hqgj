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
import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.service.ServeCultivateService;
import com.huacainfo.ace.hqgj.vo.ServeCultivateVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateQVo;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升基础表)
 */
@Api(value = "/serveCultivate", tags = "培训提升基础表")
@RestController
@RequestMapping("/serveCultivate")
public class ServeCultivateController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeCultivateService serveCultivateService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取培训提升基础表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeCultivateVo> page(ServeCultivateQVo condition, PageParam page) throws Exception {

        PageDTO<ServeCultivateVo> rst = this.serveCultivateService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeCultivate
     * @Description: TODO(创建培训提升基础表)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建培训提升基础表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeCultivate的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ServeCultivate obj = JSON.parseObject(jsons, ServeCultivate.class);
        return this.serveCultivateService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeCultivate
     * @Description: TODO(更新培训提升基础表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新培训提升基础表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeCultivate的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ServeCultivate obj = JSON.parseObject(jsons, ServeCultivate.class);
        return this.serveCultivateService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升基础表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivate>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 培训提升基础表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeCultivateVo> getById(String id) throws Exception {
        return this.serveCultivateService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeCultivateByServeCultivateId
     * @Description: TODO(删除培训提升基础表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 培训提升基础表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveCultivateService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除培训提升基础表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 培训提升基础表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveCultivateService.deleteByIds(ids.split(","));
    }

}
