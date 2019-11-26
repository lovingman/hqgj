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
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.zcpa.model.ActPosterBlacklist;
import com.huacainfo.ace.zcpa.service.ActPosterBlacklistService;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistVo;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistQVo;


/**
 * @author: 何双
 * @version: 2019-08-07
 * @Description: TODO(发贴人黑名单列表)
 */
@Api(value = "/actPosterBlacklist", tags = "发贴人黑名单列表")
@RestController
@RequestMapping("/actPosterBlacklist")
public class ActPosterBlacklistController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActPosterBlacklistService actPosterBlacklistService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActPosterBlacklistVo>
     * @author: 何双
     * @version: 2019-08-07
     */
    @ApiOperation(value = "/page", notes = "获取发贴人黑名单列表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ActPosterBlacklistVo> page(ActPosterBlacklistQVo condition, PageParam page) throws Exception {
        PageDTO<ActPosterBlacklistVo> rst =this.actPosterBlacklistService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * @Title:insertActPosterBlacklist
     * @Description: TODO(创建发贴人黑名单列表)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @ApiOperation(value = "/create", notes = "创建发贴人黑名单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActPosterBlacklist的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody  String jsons) throws Exception {
        return this.actPosterBlacklistService.create(jsons, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActPosterBlacklist
     * @Description: TODO(更新发贴人黑名单列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @ApiOperation(value = "/update", notes = "更新发贴人黑名单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActPosterBlacklist的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ActPosterBlacklist obj = JSON.parseObject(jsons, ActPosterBlacklist.class);
        return this.actPosterBlacklistService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取发贴人黑名单列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActPosterBlacklist>
     * @author: 何双
     * @version: 2019-08-07
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 发贴人黑名单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActPosterBlacklistVo> getById(String id) throws Exception {
        return this.actPosterBlacklistService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteActPosterBlacklistByActPosterBlacklistId
     * @Description: TODO(删除发贴人黑名单列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 发贴人黑名单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actPosterBlacklistService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除发贴人黑名单列表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 发贴人黑名单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actPosterBlacklistService.deleteByIds(ids.split(","));
    }


    /**
     * 判断是否存在黑名单
     * @param postingId
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/isExist", notes = "根据发帖人id判断是否存在黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postingId", value = "发帖人id", required = true, dataType = "String", paramType = "form")
    })
    @GetMapping(value = "/isExist", produces = "application/json;charset=UTF-8")
    public ResponseDTO isExist(String postingId) throws Exception {

        return this.actPosterBlacklistService.isExist(postingId);
    }
}
