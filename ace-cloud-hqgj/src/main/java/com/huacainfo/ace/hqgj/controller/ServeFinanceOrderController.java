package com.huacainfo.ace.hqgj.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.tools.CommonUtils;
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
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.model.ServeFinanceOrder;
import com.huacainfo.ace.hqgj.service.ServeFinanceOrderService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderQVo;


/**
 * @author: 何双
 * @version: 2019-12-12
 * @Description: TODO(财税服务订单表)
 */
@Api(value = "/serveFinanceOrder", tags = "财税服务订单表")
@RestController
@RequestMapping("/serveFinanceOrder")
public class ServeFinanceOrderController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeFinanceOrderService serveFinanceOrderService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceOrderVo>
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/page", notes = "获取财税服务订单表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeFinanceOrderVo> page(ServeFinanceOrderQVo condition, PageParam page) throws Exception {

        PageDTO<ServeFinanceOrderVo> rst = this.serveFinanceOrderService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeFinanceOrder
     * @Description: TODO(创建财税服务订单表)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/create", notes = "创建财税服务订单表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinanceOrder的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        ServeFinanceOrder obj = JSON.parseObject(jsons, ServeFinanceOrder.class);
        return this.serveFinanceOrderService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeFinanceOrder
     * @Description: TODO(更新财税服务订单表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/update", notes = "更新财税服务订单表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinanceOrder的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ServeFinanceOrder obj = JSON.parseObject(jsons, ServeFinanceOrder.class);
        return this.serveFinanceOrderService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务订单表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinanceOrder>
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 财税服务订单表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeFinanceOrderVo> getById(String id) throws Exception {
        return this.serveFinanceOrderService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeFinanceOrderByServeFinanceOrderId
     * @Description: TODO(删除财税服务订单表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 财税服务订单表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveFinanceOrderService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务订单表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 财税服务订单表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveFinanceOrderService.deleteByIds(ids.split(","));
    }


    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "/updateStatus", notes = "修改状态")
    @PostMapping(value = "/updateStatus", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateStatus(String id, String status)throws Exception {
        if (CommonUtils.isBlank(id) || CommonUtils.isBlank(status)) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        return serveFinanceOrderService.updateStatus(id,status);
    }
}
