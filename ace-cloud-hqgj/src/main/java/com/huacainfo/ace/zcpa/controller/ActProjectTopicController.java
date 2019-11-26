package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
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
import com.huacainfo.ace.zcpa.model.ActProjectTopic;
import com.huacainfo.ace.zcpa.service.ActProjectTopicService;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicVo;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicQVo;


/**
 * @author: 豆文国
 * @version: 2019-07-29
 * @Description: TODO(项目活动主题)
 */
@Api(value = "/actProjectTopic", tags = "项目活动主题")
@RestController
@RequestMapping("/actProjectTopic")
public class ActProjectTopicController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActProjectTopicService actProjectTopicService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectTopicVo>
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @ApiOperation(value = "/page", notes = "获取项目活动主题数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ActProjectTopicVo> page(ActProjectTopicQVo condition, PageParam page) throws Exception {
        PageDTO<ActProjectTopicVo>  rst= this.actProjectTopicService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * @Title:insertActProjectTopic
     * @Description: TODO(创建项目活动主题)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @ApiOperation(value = "/create", notes = "创建项目活动主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActProjectTopic的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ActProjectTopic obj = JSON.parseObject(jsons, ActProjectTopic.class);
        return this.actProjectTopicService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActProjectTopic
     * @Description: TODO(更新项目活动主题)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @ApiOperation(value = "/update", notes = "更新项目活动主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActProjectTopic的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ActProjectTopic obj = JSON.parseObject(jsons, ActProjectTopic.class);
        return this.actProjectTopicService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动主题)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectTopic>
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目活动主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectTopicVo> getById(String id) throws Exception {
        return this.actProjectTopicService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteActProjectTopicByActProjectTopicId
     * @Description: TODO(删除项目活动主题)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目活动主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actProjectTopicService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动主题)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目活动主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actProjectTopicService.deleteByIds(ids.split(","));
    }


    /**
     * 获取推送主题的志愿者列表
     * @param projectCategory 项目类型
     * @param projectId 项目主键
     * @param targetId
     * @param areaCode 区域
     * @param page
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/topicVolunteerList", notes = "获取项目活动主题数据集合，支持分页查询")
    @GetMapping(value = "/topicVolunteerList", produces = "application/json;charset=UTF-8")
    public PageDTO<BaseVolunteerVo> topicVolunteerList(String projectCategory, String projectId, String targetId, String areaCode, PageParam page) throws Exception {
        PageDTO<BaseVolunteerVo>  rst= this.actProjectTopicService.topicVolunteerList(projectCategory,projectId,targetId,areaCode, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }
}