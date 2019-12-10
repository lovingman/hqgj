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
import com.huacainfo.ace.hqgj.model.ServeBusinessMember;
import com.huacainfo.ace.hqgj.service.ServeBusinessMemberService;
import com.huacainfo.ace.hqgj.vo.ServeBusinessMemberVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessMemberQVo;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务资料清单人员表)
 */
@Api(value = "/serveBusinessMember", tags = "创业服务资料清单人员表")
@RestController
@RequestMapping("/serveBusinessMember")
public class ServeBusinessMemberController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeBusinessMemberService serveBusinessMemberService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessMemberVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取创业服务资料清单人员表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeBusinessMemberVo> page(ServeBusinessMemberQVo condition, PageParam page) throws Exception {

        PageDTO<ServeBusinessMemberVo> rst = this.serveBusinessMemberService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeBusinessMember
     * @Description: TODO(创建创业服务资料清单人员表)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建创业服务资料清单人员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessMember的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ServeBusinessMemberVo obj = JSON.parseObject(jsons, ServeBusinessMemberVo.class);
        return this.serveBusinessMemberService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeBusinessMember
     * @Description: TODO(更新创业服务资料清单人员表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新创业服务资料清单人员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeBusinessMember的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ServeBusinessMember obj = JSON.parseObject(jsons, ServeBusinessMember.class);
        return this.serveBusinessMemberService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料清单人员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessMember>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 创业服务资料清单人员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeBusinessMemberVo> getById(String id) throws Exception {
        return this.serveBusinessMemberService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeBusinessMemberByServeBusinessMemberId
     * @Description: TODO(删除创业服务资料清单人员表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 创业服务资料清单人员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveBusinessMemberService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务资料清单人员表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 创业服务资料清单人员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveBusinessMemberService.deleteByIds(ids.split(","));
    }

}
