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
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;
import com.huacainfo.ace.hqgj.service.ServeBusinessAppendService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendQVo;


/**
 * @author: 豆文国
 * @version: 2019-12-18
 * @Description: TODO(创业服务资料其它附加信息)
 */
@Api(value = "/serveBusinessAppend", tags = "创业服务资料其它附加信息")
@RestController
@RequestMapping("/serveBusinessAppend")
public class ServeBusinessAppendController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeBusinessAppendService serveBusinessAppendService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessAppendVo>
     * @author: 豆文国
     * @version: 2019-12-18
     */
    @ApiOperation(value = "/page", notes = "获取创业服务资料其它附加信息数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeBusinessAppendVo> page(ServeBusinessAppendQVo condition, PageParam page) throws Exception {

        PageDTO<ServeBusinessAppendVo> rst = this.serveBusinessAppendService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeBusinessAppend
     * @Description: TODO(创建创业服务资料其它附加信息)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-18
     */
    @ApiOperation(value = "/create", notes = "创建创业服务资料其它附加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessAppend的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ServeBusinessAppend obj = JSON.parseObject(jsons, ServeBusinessAppend.class);
        return this.serveBusinessAppendService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeBusinessAppend
     * @Description: TODO(更新创业服务资料其它附加信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-18
     */
    @ApiOperation(value = "/update", notes = "更新创业服务资料其它附加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessAppend的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ServeBusinessAppend obj = JSON.parseObject(jsons, ServeBusinessAppend.class);
        return this.serveBusinessAppendService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料其它附加信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessAppend>
     * @author: 豆文国
     * @version: 2019-12-18
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 创业服务资料其它附加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeBusinessAppendVo> getById(String id) throws Exception {
        return this.serveBusinessAppendService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeBusinessAppendByServeBusinessAppendId
     * @Description: TODO(删除创业服务资料其它附加信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-18
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 创业服务资料其它附加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveBusinessAppendService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务资料其它附加信息)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-18
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 创业服务资料其它附加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveBusinessAppendService.deleteByIds(ids.split(","));
    }

}
