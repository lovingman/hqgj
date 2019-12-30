package com.huacainfo.ace.hqgj.controller;

import com.huacainfo.ace.common.vo.UserProp;
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
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;
import com.huacainfo.ace.hqgj.service.ServeCultivateEnrollService;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollQVo;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升 - 报名管理)
 */
@Api(value = "/serveCultivateEnroll", tags = "培训提升-报名管理")
@RestController
@RequestMapping("/serveCultivateEnroll")
public class ServeCultivateEnrollController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeCultivateEnrollService serveCultivateEnrollService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateEnrollVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取培训提升-报名管理数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeCultivateEnrollVo> page(ServeCultivateEnrollQVo condition, PageParam page) throws Exception {

        PageDTO<ServeCultivateEnrollVo> rst = this.serveCultivateEnrollService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * 手机端个人中心
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/enrollPage", notes = "获取培训提升-报名管理数据集合，支持分页查询")
    @GetMapping(value = "/enrollPage", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeCultivateEnrollVo> enrollPage(ServeCultivateEnrollQVo condition, PageParam page) throws Exception {
        UserProp userProp=this.getCurUserProp();
        condition.setCreateUserId(userProp.getUserId());
        PageDTO<ServeCultivateEnrollVo> rst = this.serveCultivateEnrollService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }
    /**
     * @Title:insertServeCultivateEnroll
     * @Description: TODO(创建培训提升 - 报名管理)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeCultivateEnroll的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody  String jsons) throws Exception {
        ServeCultivateEnroll obj = JSON.parseObject(jsons, ServeCultivateEnroll.class);
        return this.serveCultivateEnrollService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeCultivateEnroll
     * @Description: TODO(更新培训提升 - 报名管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeCultivateEnroll的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ServeCultivateEnroll obj = JSON.parseObject(jsons, ServeCultivateEnroll.class);
        return this.serveCultivateEnrollService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升 - 报名管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivateEnroll>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeCultivateEnrollVo> getById(String id) throws Exception {
        return this.serveCultivateEnrollService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeCultivateEnrollByServeCultivateEnrollId
     * @Description: TODO(删除培训提升 - 报名管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveCultivateEnrollService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除培训提升 - 报名管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveCultivateEnrollService.deleteByIds(ids.split(","));
    }


    /**
     * 批量修改状态（签到，待签订的）
     * @param status
     * @param ids
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/updateStatus", notes = "更新培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeCultivateEnroll的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/updateStatus", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateStatus(String status,String ids) throws Exception {
        return this.serveCultivateEnrollService.updateStatus(status, ids.split(","));
    }


    /**
     * 取消报名
     */
    @ApiOperation(value = "/cancelEnroll", notes = "更新培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeCultivateEnroll的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/cancelEnroll", produces = "application/json;charset=UTF-8")
    public ResponseDTO cancelEnroll(String id) throws Exception {
        return this.serveCultivateEnrollService.cancelEnroll(id);
    }


}
