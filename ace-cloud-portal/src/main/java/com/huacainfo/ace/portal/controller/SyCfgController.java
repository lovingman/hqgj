package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.service.SyCfgService;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;
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

import java.util.Map;


/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(系统配置)
 */
@Api(value = "/syCfg", tags = "系统配置")
@RestController
@RequestMapping("/syCfg")
public class SyCfgController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SyCfgService syCfgService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO
     * <SyCfgVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/page", notes = "获取系统配置数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<SyCfgVo> page(SyCfgQVo condition, PageParam page) throws Exception {
        PageDTO<SyCfgVo> rst = this.syCfgService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertSyCfg
     * @Description: TODO(创建系统配置)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/create", notes = "创建系统配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "SyCfg的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        SyCfg obj = JSON.parseObject(jsons, SyCfg.class);
        return this.syCfgService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateSyCfg
     * @Description: TODO(更新系统配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/update", notes = "更新系统配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "SyCfg的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        SyCfg obj = JSON.parseObject(jsons, SyCfg.class);
        return this.syCfgService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<SyCfg>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 系统配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<SyCfgVo> getById(String id) throws Exception {
        return this.syCfgService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteSyCfgBySyCfgId
     * @Description: TODO(删除系统配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 系统配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.syCfgService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统配置)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 系统配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.syCfgService.deleteByIds(ids.split(","));
    }

    @ApiOperation(value = "/selectSyCfgByUser", notes = "根据当前登录信息获取系统配置")
    @PostMapping(value = "/selectSyCfgByUser")
    public ListDTO<Map<String, Object>> selectSyCfgByUser() throws Exception {
        return this.syCfgService.selectSyCfgByUser(this.getCurUserProp());
    }
}
