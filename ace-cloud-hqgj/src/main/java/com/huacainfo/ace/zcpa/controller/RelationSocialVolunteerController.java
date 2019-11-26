package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
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
import com.huacainfo.ace.zcpa.model.RelationSocialVolunteer;
import com.huacainfo.ace.zcpa.service.RelationSocialVolunteerService;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerVo;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerQVo;


/**
 * @author: 豆文国
 * @version: 2019-09-26
 * @Description: TODO(志愿者与社会组织关联)
 */
@Api(value = "/relationSocialVolunteer", tags = "志愿者与社会组织关联")
@RestController
@RequestMapping("/relationSocialVolunteer")
public class RelationSocialVolunteerController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RelationSocialVolunteerService relationSocialVolunteerService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <RelationSocialVolunteerVo>
     * @author: 豆文国
     * @version: 2019-09-26
     */
    @ApiOperation(value = "/page", notes = "获取志愿者与社会组织关联数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<RelationSocialVolunteerVo> page(RelationSocialVolunteerQVo condition, PageParam page) throws Exception {
        UserProp userProp=this.getCurUserProp();
        condition.setVolunteerId(userProp.getUserId());
        PageDTO<RelationSocialVolunteerVo> rst = this.relationSocialVolunteerService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertRelationSocialVolunteer
     * @Description: TODO(创建志愿者与社会组织关联)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-26
     */
    @ApiOperation(value = "/create", notes = "创建志愿者与社会组织关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "RelationSocialVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        RelationSocialVolunteer obj = JSON.parseObject(jsons, RelationSocialVolunteer.class);
        return this.relationSocialVolunteerService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRelationSocialVolunteer
     * @Description: TODO(更新志愿者与社会组织关联)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-26
     */
    @ApiOperation(value = "/update", notes = "更新志愿者与社会组织关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "RelationSocialVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        RelationSocialVolunteer obj = JSON.parseObject(jsons, RelationSocialVolunteer.class);
        return this.relationSocialVolunteerService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取志愿者与社会组织关联)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<RelationSocialVolunteer>
     * @author: 豆文国
     * @version: 2019-09-26
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 志愿者与社会组织关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<RelationSocialVolunteerVo> getById(String id) throws Exception {
        return this.relationSocialVolunteerService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteRelationSocialVolunteerByRelationSocialVolunteerId
     * @Description: TODO(删除志愿者与社会组织关联)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-26
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 志愿者与社会组织关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.relationSocialVolunteerService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除志愿者与社会组织关联)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-09-26
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 志愿者与社会组织关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.relationSocialVolunteerService.deleteByIds(ids.split(","));
    }

}
