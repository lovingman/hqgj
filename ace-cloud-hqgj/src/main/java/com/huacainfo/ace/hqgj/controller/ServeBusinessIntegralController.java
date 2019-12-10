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
import com.huacainfo.ace.hqgj.model.ServeBusinessIntegral;
import com.huacainfo.ace.hqgj.service.ServeBusinessIntegralService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralQVo;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(积分管理)
 */
@Api(value = "/serveBusinessIntegral", tags = "积分管理")
@RestController
@RequestMapping("/serveBusinessIntegral")
public class ServeBusinessIntegralController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeBusinessIntegralService serveBusinessIntegralService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessIntegralVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取积分管理数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeBusinessIntegralVo> page(ServeBusinessIntegralQVo condition, PageParam page) throws Exception {

        PageDTO<ServeBusinessIntegralVo> rst = this.serveBusinessIntegralService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeBusinessIntegral
     * @Description: TODO(创建积分管理)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建积分管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessIntegral的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ServeBusinessIntegral obj = JSON.parseObject(jsons, ServeBusinessIntegral.class);
        return this.serveBusinessIntegralService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeBusinessIntegral
     * @Description: TODO(更新积分管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新积分管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessIntegral的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ServeBusinessIntegral obj = JSON.parseObject(jsons, ServeBusinessIntegral.class);
        return this.serveBusinessIntegralService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取积分管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessIntegral>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 积分管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeBusinessIntegralVo> getById(String id) throws Exception {
        return this.serveBusinessIntegralService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeBusinessIntegralByServeBusinessIntegralId
     * @Description: TODO(删除积分管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 积分管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveBusinessIntegralService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除积分管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 积分管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveBusinessIntegralService.deleteByIds(ids.split(","));
    }

}
