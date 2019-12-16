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
import com.huacainfo.ace.hqgj.model.ServeBusiness;
import com.huacainfo.ace.hqgj.service.ServeBusinessService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessQVo;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务包)
 */
@Api(value = "/serveBusiness", tags = "创业服务包")
@RestController
@RequestMapping("/serveBusiness")
public class ServeBusinessController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeBusinessService serveBusinessService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取创业服务包数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ServeBusinessVo> page(ServeBusinessQVo condition, PageParam page) throws Exception {
        PageDTO<ServeBusinessVo> rst = this.serveBusinessService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeBusiness
     * @Description: TODO(创建创业服务包)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建创业服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusiness的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ServeBusiness obj = JSON.parseObject(jsons, ServeBusiness.class);
        return this.serveBusinessService.create(jsons, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeBusiness
     * @Description: TODO(更新创业服务包)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新创业服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusiness的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ServeBusiness obj = JSON.parseObject(jsons, ServeBusiness.class);
        return this.serveBusinessService.update(jsons, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusiness>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 创业服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeBusinessVo> getById(String id) throws Exception {
        return this.serveBusinessService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeBusinessByServeBusinessId
     * @Description: TODO(删除创业服务包)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 创业服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveBusinessService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务包)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 创业服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveBusinessService.deleteByIds(ids.split(","));
    }

    /**
     * 创业服务包免费服务申请表
     * @param id 主键
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/previewInfo", notes = "创业服务包免费服务申请表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/previewInfo", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeBusinessVo> previewInfo(String id) throws Exception {
        return this.serveBusinessService.previewInfo(id);
    }

}
