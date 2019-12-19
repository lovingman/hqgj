package com.huacainfo.ace.hqgj.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.model.ServeFinanceItem;
import com.huacainfo.ace.hqgj.service.ServeFinanceItemService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemQVo;
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


/**
 * @author: 何双
 * @version: 2019-12-12
 * @Description: TODO(财税服务项目)
 */
@Api(value = "/serveFinanceItem", tags = "财税服务项目")
@RestController
@RequestMapping("/serveFinanceItem")
public class ServeFinanceItemController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeFinanceItemService serveFinanceItemService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceItemVo>
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/page", notes = "获取财税服务项目数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeFinanceItemVo> page(ServeFinanceItemQVo condition, PageParam page) throws Exception {

        PageDTO<ServeFinanceItemVo> rst = this.serveFinanceItemService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeFinanceItem
     * @Description: TODO(创建财税服务项目)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/create", notes = "创建财税服务项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinanceItem的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        ServeFinanceItem obj = JSON.parseObject(jsons, ServeFinanceItem.class);
        return this.serveFinanceItemService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeFinanceItem
     * @Description: TODO(更新财税服务项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/update", notes = "更新财税服务项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinanceItem的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ServeFinanceItem obj = JSON.parseObject(jsons, ServeFinanceItem.class);
        return this.serveFinanceItemService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务项目)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinanceItem>
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 财税服务项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeFinanceItemVo> getById(String id) throws Exception {
        return this.serveFinanceItemService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeFinanceItemByServeFinanceItemId
     * @Description: TODO(删除财税服务项目)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 财税服务项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveFinanceItemService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务项目)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 财税服务项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveFinanceItemService.deleteByIds(ids.split(","));
    }

}
