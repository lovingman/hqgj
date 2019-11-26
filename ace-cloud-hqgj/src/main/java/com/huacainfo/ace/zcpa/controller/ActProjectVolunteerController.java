package com.huacainfo.ace.zcpa.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteer;
import com.huacainfo.ace.zcpa.service.ActProjectVolunteerService;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目志愿者列表 < 除情暖家庭 >)
 */
@Api(value = "/actProjectVolunteer", tags = "项目志愿者列表<除情暖家庭外>")
@RestController
@RequestMapping("/actProjectVolunteer")
public class ActProjectVolunteerController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActProjectVolunteerService actProjectVolunteerService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <actProjectVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/page", notes = "获取项目志愿者列表<限情暖家庭>数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ActProjectVolunteerVo> page(ActProjectVolunteerQVo condition, PageParam page) throws Exception {
        PageDTO<ActProjectVolunteerVo> rst=this.actProjectVolunteerService.page(condition,page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertactProjectVolunteer
     * @Description: TODO(创建项目志愿者列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/create", notes = "创建项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "actProjectVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody  String jsons) throws Exception {
       // actProjectVolunteer obj = JSON.parseObject(jsons, actProjectVolunteer.class);
        return this.actProjectVolunteerService.create(jsons, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateactProjectVolunteer
     * @Description: TODO(更新项目志愿者列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/update", notes = "更新项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "actProjectVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ActProjectVolunteer obj = JSON.parseObject(jsons, ActProjectVolunteer.class);
        return this.actProjectVolunteerService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目志愿者列表 < 限情暖家庭 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<actProjectVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectVolunteerVo> getById(String id) throws Exception {
        return this.actProjectVolunteerService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteactProjectVolunteerByactProjectVolunteerId
     * @Description: TODO(删除项目志愿者列表 < 限情暖家庭 >)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actProjectVolunteerService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目志愿者列表 < 限情暖家庭 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目志愿者列表<限情暖家庭>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actProjectVolunteerService.deleteByIds(ids.split(","));
    }

    /**
     * 项目关联的志愿者
     * @param projectId
     * @return
     * @throws Exception
     */
   @GetMapping(value = "/selectByProjectId", produces = "application/json;charset=UTF-8")
    public List<ActProjectVolunteerVo> selectByProjectId(String projectId,String targetId) throws Exception{
        return this.actProjectVolunteerService.selectByProjectId(projectId,targetId);
    }
}
