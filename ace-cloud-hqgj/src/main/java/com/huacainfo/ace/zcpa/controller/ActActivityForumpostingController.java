package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
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
import com.huacainfo.ace.zcpa.model.ActActivityForumposting;
import com.huacainfo.ace.zcpa.service.ActActivityForumpostingService;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingQVo;


/**
 * @author: 何双
 * @version: 2019-08-06
 * @Description: TODO(论坛发贴记录)
 */
@Api(value = "/actActivityForumposting", tags = "论坛发贴记录")
@RestController
@RequestMapping("/actActivityForumposting")
public class ActActivityForumpostingController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActActivityForumpostingService actActivityForumpostingService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityForumpostingVo>
     * @author: 何双
     * @version: 2019-08-06
     */
    @ApiOperation(value = "/page", notes = "获取论坛发贴记录数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ActActivityForumpostingVo> page(ActActivityForumpostingQVo condition, PageParam page) throws Exception {
        PageDTO
                <ActActivityForumpostingVo> rst=this.actActivityForumpostingService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * @Title:insertActActivityForumposting
     * @Description: TODO(创建论坛发贴记录)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @ApiOperation(value = "/create", notes = "创建论坛发贴记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActActivityForumposting的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        ActActivityForumposting obj = JSON.parseObject(jsons, ActActivityForumposting.class);
        return this.actActivityForumpostingService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActActivityForumposting
     * @Description: TODO(更新论坛发贴记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @ApiOperation(value = "/update", notes = "更新论坛发贴记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActActivityForumposting的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ActActivityForumposting obj = JSON.parseObject(jsons, ActActivityForumposting.class);
        return this.actActivityForumpostingService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取论坛发贴记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityForumposting>
     * @author: 何双
     * @version: 2019-08-06
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 论坛发贴记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActActivityForumpostingVo> getById(String id) throws Exception {
        return this.actActivityForumpostingService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteActActivityForumpostingByActActivityForumpostingId
     * @Description: TODO(删除论坛发贴记录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 论坛发贴记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actActivityForumpostingService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除论坛发贴记录)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 论坛发贴记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actActivityForumpostingService.deleteByIds(ids.split(","));
    }


    @PostMapping(value = "/updateStatus", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateStatus(String id,String status) throws Exception {
        return  this.actActivityForumpostingService.updateStatus(id,status);
    }


    /**
     * 获取发帖详情接口
     *
     * @param id     发帖纪录主键
     * @param userId 当前登录用户ID,可选
     * @return ResponseDTO
     */
    @ApiOperation(value = "/getForumPostingDetail", notes = "获取发帖详情接口")
    @GetMapping(value = "/getForumPostingDetail", produces = "application/json;charset=UTF-8")
    public ResponseDTO getForumPostingDetail(String id, String userId) {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少发帖纪录主键");
        }
        //登录信息
        UserProp user = this.getCurUserProp();

        return actActivityForumpostingService.getForumPostingDetail(id, null == user ? userId : user.getUserId());
    }
}
