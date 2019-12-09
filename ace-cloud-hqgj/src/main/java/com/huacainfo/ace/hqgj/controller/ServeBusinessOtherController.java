package com.huacainfo.ace.hqgj.controller;

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
import com.huacainfo.ace.hqgj.model.ServeBusinessOther;
import com.huacainfo.ace.hqgj.service.ServeBusinessOtherService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherQVo;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务资料清单其它资料)
 */
@Api(value = "/serveBusinessOther", tags = "创业服务资料清单其它资料")
@RestController
@RequestMapping("/serveBusinessOther")
public class ServeBusinessOtherController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeBusinessOtherService serveBusinessOtherService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessOtherVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取创业服务资料清单其它资料数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeBusinessOtherVo> page(ServeBusinessOtherQVo condition, PageParam page) throws Exception {

        PageDTO<ServeBusinessOtherVo> rst = this.serveBusinessOtherService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeBusinessOther
     * @Description: TODO(创建创业服务资料清单其它资料)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建创业服务资料清单其它资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessOther的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        ServeBusinessOther obj = JSON.parseObject(jsons, ServeBusinessOther.class);
        return this.serveBusinessOtherService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeBusinessOther
     * @Description: TODO(更新创业服务资料清单其它资料)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新创业服务资料清单其它资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessOther的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ServeBusinessOther obj = JSON.parseObject(jsons, ServeBusinessOther.class);
        return this.serveBusinessOtherService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料清单其它资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessOther>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 创业服务资料清单其它资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeBusinessOtherVo> getById(String id) throws Exception {
        return this.serveBusinessOtherService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeBusinessOtherByServeBusinessOtherId
     * @Description: TODO(删除创业服务资料清单其它资料)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 创业服务资料清单其它资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveBusinessOtherService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务资料清单其它资料)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 创业服务资料清单其它资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveBusinessOtherService.deleteByIds(ids.split(","));
    }

}
