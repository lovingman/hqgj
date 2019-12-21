package com.huacainfo.ace.hqgj.controller;


import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;

import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;
import com.huacainfo.ace.hqgj.service.ServeCultivateEnrollService;
import com.huacainfo.ace.hqgj.service.ServeCultivateScheduleService;
import com.huacainfo.ace.hqgj.service.ServeCultivateService;
import com.huacainfo.ace.hqgj.vo.ServeCultivateQVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleQVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateVo;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升基础表)
 */
@Api(value = "/www/serveCultivate", tags = "培训提升基础表")
@RestController
@RequestMapping("/www/serveCultivate")
public class WServeCultivateController extends BaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeCultivateService serveCultivateService;
    @Autowired
    private ServeCultivateScheduleService serveCultivateScheduleService;
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
     * <ServeCultivateVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取培训提升基础表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeCultivateVo> page(ServeCultivateQVo condition, PageParam page) throws Exception {

        PageDTO<ServeCultivateVo> rst = this.serveCultivateService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateScheduleVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/schedulePage", notes = "获取培训提升日程表数据集合，支持分页查询")
    @GetMapping(value = "/schedulePage", produces = "application/json;charset=UTF-8")
    public PageDTO<ServeCultivateScheduleVo> page(ServeCultivateScheduleQVo condition, PageParam page) throws Exception {
        PageDTO<ServeCultivateScheduleVo> rst = this.serveCultivateScheduleService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升日程表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivateSchedule>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/scheduleGetById", notes = "根据主键获取 培训提升日程表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/scheduleGetById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeCultivateScheduleVo> getById(String id) throws Exception {
        return this.serveCultivateScheduleService.getById(id);
    }



    /**
     * 修改状态 0-待审核  1-进行中 2-未通过 3-已结束',
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "/updateStatus", notes = "修改状态")
    @PostMapping(value = "/updateStatus", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateStatus(String id, String status){
        if (CommonUtils.isBlank(id) || CommonUtils.isBlank(status)) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
     return serveCultivateService.updateStatus(id,status);
    }

    /**
     * 手机端培训服务详情
     * @param id
     * @return
     */
    @ApiOperation(value = "/selectByIdDetails", notes = "修改状态")
    @GetMapping(value = "/selectByIdDetails", produces = "application/json;charset=UTF-8")
    public ResponseDTO selectByIdDetails(String id,String userId) {
        UserProp user = this.getCurUserProp();
        return serveCultivateService.selectByIdDetails(id,user==null?userId:null);
    }


    /**
     * @Title:insertServeCultivateEnroll
     * @Description: TODO(创建培训提升 - 报名管理)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/enrollCreate", notes = "创建培训提升-报名管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeCultivateEnroll的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/enrollCreate", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody  String jsons) throws Exception {
        ServeCultivateEnroll obj = JSON.parseObject(jsons, ServeCultivateEnroll.class);
        return this.serveCultivateEnrollService.create(obj, this.getCurUserProp());
    }
}
