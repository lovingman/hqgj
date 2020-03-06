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
import com.huacainfo.ace.hqgj.model.ServeFinanceEvaluate;
import com.huacainfo.ace.hqgj.service.ServeFinanceEvaluateService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateQVo;


/**
 * @author: 何双
 * @version: 2019-12-12
 * @Description: TODO(财税服务订单评价表)
 */
@Api(value = "/serveFinanceEvaluate", tags = "财税服务订单评价表")
@RestController
@RequestMapping("/serveFinanceEvaluate")
public class ServeFinanceEvaluateController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeFinanceEvaluateService serveFinanceEvaluateService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceEvaluateVo>
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/page", notes = "获取财税服务订单评价表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeFinanceEvaluateVo> page(ServeFinanceEvaluateQVo condition, PageParam page) throws Exception {

        PageDTO<ServeFinanceEvaluateVo> rst = this.serveFinanceEvaluateService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeFinanceEvaluate
     * @Description: TODO(创建财税服务订单评价表)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/create", notes = "创建财税服务订单评价表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinanceEvaluate的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        ServeFinanceEvaluate obj = JSON.parseObject(jsons, ServeFinanceEvaluate.class);
        return this.serveFinanceEvaluateService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeFinanceEvaluate
     * @Description: TODO(更新财税服务订单评价表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/update", notes = "更新财税服务订单评价表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinanceEvaluate的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ServeFinanceEvaluate obj = JSON.parseObject(jsons, ServeFinanceEvaluate.class);
        return this.serveFinanceEvaluateService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务订单评价表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinanceEvaluate>
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 财税服务订单评价表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeFinanceEvaluateVo> getById(String id) throws Exception {
        return this.serveFinanceEvaluateService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeFinanceEvaluateByServeFinanceEvaluateId
     * @Description: TODO(删除财税服务订单评价表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 财税服务订单评价表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveFinanceEvaluateService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务订单评价表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 财税服务订单评价表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveFinanceEvaluateService.deleteByIds(ids.split(","));
    }

}
