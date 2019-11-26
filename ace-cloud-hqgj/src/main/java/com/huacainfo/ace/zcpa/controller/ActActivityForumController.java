package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.service.ForumService;
import com.huacainfo.ace.zcpa.vo.ForumVo;
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
import com.huacainfo.ace.zcpa.model.ActActivityForum;
import com.huacainfo.ace.zcpa.service.ActActivityForumService;
import com.huacainfo.ace.zcpa.vo.ActActivityForumVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;

import javax.annotation.Resource;


/**
 * @author: 豆文国
 * @version: 2019-08-02
 * @Description: TODO(项目活动论坛回帖情况)
 */
@Api(value = "/actActivityForum", tags = "项目活动论坛回帖情况")
@RestController
@RequestMapping("/actActivityForum")
public class ActActivityForumController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActActivityForumService actActivityForumService;
    @Resource
    private ForumService forumService;

    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityForumVo>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @ApiOperation(value = "/page", notes = "获取项目活动论坛回帖情况数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ActActivityForumVo> page(ActActivityForumQVo condition, PageParam page) throws Exception {
        return this.actActivityForumService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());

    }

    /**
     * 获取回帖列表接口
     *
     * @param userId 当前登录用户ID
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/findForumList", notes = "获取回帖列表接口")
    @GetMapping(value = "/findForumList", produces = "application/json;charset=UTF-8")
    public PageDTO<ForumVo> findForumList(String postingId, String userId, PageParam page) {
        if (CommonUtils.isEmpty(postingId)) {
            return new PageDTO<>(ResultCode.FAIL, "缺少发帖纪录主键！");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        //查询条件
        ActActivityForumQVo condition = new ActActivityForumQVo();
        condition.setUserid(null == user ? userId : user.getUserId());
        condition.setPostingId(postingId);

        PageDTO<ForumVo> rst = this.forumService.findForumList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }
    /**
     * @Title:insertActActivityForum
     * @Description: TODO(创建项目活动论坛回帖情况)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @ApiOperation(value = "/create", notes = "创建项目活动论坛回帖情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActActivityForum的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        ActActivityForum obj = JSON.parseObject(jsons, ActActivityForum.class);
        return this.actActivityForumService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActActivityForum
     * @Description: TODO(更新项目活动论坛回帖情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @ApiOperation(value = "/update", notes = "更新项目活动论坛回帖情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActActivityForum的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        ActActivityForum obj = JSON.parseObject(jsons, ActActivityForum.class);
        return this.actActivityForumService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动论坛回帖情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityForum>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目活动论坛回帖情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActActivityForumVo> getById(String id) throws Exception {
        return this.actActivityForumService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteActActivityForumByActActivityForumId
     * @Description: TODO(删除项目活动论坛回帖情况)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目活动论坛回帖情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actActivityForumService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动论坛回帖情况)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目活动论坛回帖情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actActivityForumService.deleteByIds(ids.split(","));
    }

    /**
     * 修改项目活动状态
     *
     * @param actActivityForum
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/updateState", notes = "根据主键修改项目活动状态")
    @PostMapping(value = "/updateState", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateState(ActActivityForum actActivityForum) throws Exception {
        return this.actActivityForumService.updateState(actActivityForum);
    }

}
