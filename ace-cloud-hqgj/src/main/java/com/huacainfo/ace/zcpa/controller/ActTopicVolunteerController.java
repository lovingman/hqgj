package com.huacainfo.ace.zcpa.controller;

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
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.zcpa.model.ActTopicVolunteer;
import com.huacainfo.ace.zcpa.service.ActTopicVolunteerService;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerVo;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerQVo;


/**
* @author: 豆文国
* @version: 2019-08-02
* @Description:  TODO(项目活动主题参与志愿者列表)
*/
@Api(value = "/actTopicVolunteer",tags = "项目活动主题参与志愿者列表")
@RestController
@RequestMapping("/actTopicVolunteer")
public class ActTopicVolunteerController extends BaseController {


private static final long serialVersionUID = 1L;
Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private ActTopicVolunteerService actTopicVolunteerService;


/**
*
* @Title:page
* @Description:  TODO(分页查询)
* @param:        @param condition
* @param:        @param page
* @param:        @return
* @param:        @throws Exception
* @return:       NewPageDTO
<ActTopicVolunteerVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-08-02
    */
    @ApiOperation(value = "/page", notes = "获取项目活动主题参与志愿者列表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public NewPageDTO
    <ActTopicVolunteerVo> page(ActTopicVolunteerQVo condition, PageParam page) throws Exception {
        return this.actTopicVolunteerService.page(condition, page.getPageNum(), page.getPageSize(), page.getOrderBy());

            }

            /**
            *
            * @Title:insertActTopicVolunteer
            * @Description: TODO(创建项目活动主题参与志愿者列表)
            * @param: @param jsons
            * @return: ResponseDTO
            * @author: 豆文国
            * @version: 2019-08-02
            */
        @ApiOperation(value ="/create",notes = "创建项目活动主题参与志愿者列表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "ActTopicVolunteer的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
            public ResponseDTO create(String jsons) throws Exception {
            ActTopicVolunteer obj = JSON.parseObject(jsons, ActTopicVolunteer.class);
            return this.actTopicVolunteerService.create(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:updateActTopicVolunteer
            * @Description: TODO(更新项目活动主题参与志愿者列表)
            * @param: @param jsons
            * @param: @throws Exception
            * @return: ResponseDTO
            * @throws
            * @author: 豆文国
            * @version: 2019-08-02
            */
        @ApiOperation(value ="/update",notes = "更新项目活动主题参与志愿者列表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "jsons", value = "ActTopicVolunteer的json化对象", required = true, dataType = "String",
        paramType = "form"),
        })
        @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
            public ResponseDTO update(String jsons) throws Exception {
            ActTopicVolunteer obj = JSON.parseObject(jsons, ActTopicVolunteer.class);
            return this.actTopicVolunteerService.update(obj,this.getCurUserProp());
            }

            /**
            *
            * @Title:getById
            * @Description: TODO(获取项目活动主题参与志愿者列表)
            * @param: @param id
            * @param: @throws Exception
            * @return: ResponseDTO<ActTopicVolunteer>
            * @throws
            * @author: 豆文国
            * @version: 2019-08-02
            */
        @ApiOperation(value ="/getById",notes = "根据主键获取 项目活动主题参与志愿者列表")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
        })
        @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
            public ResponseDTO<ActTopicVolunteerVo> getById(String id)throws Exception {
                return this.actTopicVolunteerService.getById(id);
                }

                /**
                *
                * @Title:deleteActTopicVolunteerByActTopicVolunteerId
                * @Description: TODO(删除项目活动主题参与志愿者列表)
                * @param: @param jsons
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-08-02
                */
            @ApiOperation(value ="/deleteById",notes = "根据主键删除 项目活动主题参与志愿者列表")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
            })
            @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteById(String id) throws Exception {
                return this.actTopicVolunteerService.deleteById(id);
                }


                /**
                *
                * @Title:deleteByIds
                * @Description: TODO(批量删除项目活动主题参与志愿者列表)
                * @param: @param ids
                * @param: @throws Exception
                * @return: ResponseDTO
                * @throws
                * @author: 豆文国
                * @version: 2019-08-02
                */
            @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 项目活动主题参与志愿者列表")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
            "form"),
            })
            @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
                public ResponseDTO deleteByIds(String ids) throws Exception {
                    return this.actTopicVolunteerService.deleteByIds(ids.split(","));
                }

}
