package com.huacainfo.ace.zcpa.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.zcpa.model.ActActivityReport;
import com.huacainfo.ace.zcpa.service.ActActivityForumService;
import com.huacainfo.ace.zcpa.service.ActActivityForumpostingService;
import com.huacainfo.ace.zcpa.service.ActActivityReportService;
import com.huacainfo.ace.zcpa.service.ForumService;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @ClassName WForumrController
 * @Description TODO
 * @Author heshaung
 * @Date 2019/8/10
 */
@Api(value = "/forum", tags = "论坛")
@RestController
@RequestMapping("/forum")
public class ForumrController extends BizController {
    @Resource
    private ForumService forumService;
    @Resource
    private ActActivityForumpostingService actActivityForumpostingService;
    @Resource
    private ActActivityReportService actActivityReportService;
    @Resource
    private ActActivityForumService actActivityForumService;

    /**
     * 论坛列表
     *
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/myForumpostingPage", notes = "获取我的 论坛发贴记录数据集合，支持分页查询")
    @GetMapping(value = "/myForumpostingPage", produces = "application/json;charset=UTF-8")
    public PageDTO<ActActivityForumpostingVo> page(ActActivityForumpostingQVo condition, PageParam page) throws Exception {
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(user)) {
            return new PageDTO(ResultCode.FAIL, "获取登录信息失败");
        }
        condition.setPostingId(user.getUserId());
        PageDTO<ActActivityForumpostingVo> rst = this.actActivityForumpostingService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * 获取我的回帖数据集合
     *
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/myActivityForumPage", notes = "获取我的回帖数据集合，支持分页查询")
    @GetMapping(value = "/myActivityForumPage", produces = "application/json;charset=UTF-8")
    public ResponseDTO myActivityForumPage(ActActivityForumQVo condition, PageParam page) throws Exception {
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(user)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }
        return this.forumService.myForumpostingList(user.getUserId(), page.getStart(), page.getLimit(), page.getOrderBy());

    }

    /**
     * 论坛发帖
     *
     * @param jsons
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/forumPosting", notes = "论坛发帖")
    @PostMapping(value = "/forumPosting", produces = "application/json;charset=UTF-8")
    public ResponseDTO forumPosting(@RequestBody String jsons) throws Exception {
        //登录信息
        UserProp user = this.getCurUserProp();
        if(user==null){
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }
        return this.forumService.forumPosting(jsons, user);
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

    /**
     * 获取回帖列表接口
     *
     * @param userId 当前登录用户ID
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/findForumList", notes = "获取回帖列表接口")
    @GetMapping(value = "/findForumList", produces = "application/json;charset=UTF-8")
    public ResponseDTO findForumList(String postingId, String userId, PageParam page) {
        if (CommonUtils.isBlank(postingId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少发帖纪录主键");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        //查询条件
        ActActivityForumQVo condition = new ActActivityForumQVo();
        condition.setUserid(null == user ? userId : user.getUserId());
        condition.setPostingId(postingId);

        return this.forumService.findForumList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
    }


    /**
     * 回帖接口
     *
     * @param userId     当前登录用户ID,可选
     * @param postingId  发帖纪录ID
     * @param text       回帖类容
     * @param attachList 附件列表:json数组 [{"fileURL":"http://123/1.jpg"},{"fileURL":"http://123/1.jpg"},{"fileURL":"http://123/1.jpg"}]
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/insertFornum", notes = "回帖接口")
    @PostMapping(value = "/insertFornum", produces = "application/json;charset=UTF-8")
    public ResponseDTO insertFornum(String postingId, String text, String userId, String attachList) {
        if (CommonUtils.isBlank(postingId) || CommonUtils.isBlank(text)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }


        return this.forumService.insertFornum(postingId, text, null == user ? userId : user.getUserId(), attachList);
    }

    /**
     * 点赞发帖接口
     *
     * @param userId    当前登录用户ID,可选
     * @param postingId 发帖纪录ID
     * @return ResponseDTO
     */
    @ApiOperation(value = "/admirerForumPosting", notes = "点赞发帖接口")
    @PostMapping(value = "/admirerForumPosting", produces = "application/json;charset=UTF-8")
    public ResponseDTO admirerForumPosting(String postingId, String userId) {
        if (CommonUtils.isBlank(postingId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }

        return forumService.admirerForumPosting(postingId, null == user ? userId : user.getUserId());
    }

    /**
     * 取消点赞发帖接口
     *
     * @param userId    当前登录用户ID,可选
     * @param postingId 发帖纪录ID
     */
    @ApiOperation(value = "/cancelAdmirerForumPosting", notes = "取消点赞发帖接口")
    @PostMapping(value = "/cancelAdmirerForumPosting", produces = "application/json;charset=UTF-8")
    public ResponseDTO cancelAdmirerForumPosting(String postingId, String userId) {
        if (CommonUtils.isBlank(postingId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }

        return forumService.cancelAdmirerForumPosting(postingId, null == user ? userId : user.getUserId());
    }

    /**
     * 点赞回帖接口
     *
     * @param userId  当前登录用户ID,可选
     * @param forumId 回帖纪录ID
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/admirerForum", notes = "点赞回帖接口")
    @PostMapping(value = "/admirerForum", produces = "application/json;charset=UTF-8")
    public ResponseDTO admirerForum(String forumId, String userId) {
        if (CommonUtils.isBlank(forumId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }

        return forumService.admirerForum(forumId, null == user ? userId : user.getUserId());
    }

    /**
     * 取消点赞回帖接口
     *
     * @param userId  当前登录用户ID,可选
     * @param forumId 回帖纪录ID
     * @return ResponseDTO
     */
    @ApiOperation(value = "/cancelAdmirerForum", notes = "取消点赞回帖接口")
    @PostMapping(value = "/cancelAdmirerForum", produces = "application/json;charset=UTF-8")
    public ResponseDTO cancelAdmirerForum(String forumId, String userId) {
        if (CommonUtils.isBlank(forumId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }

        return forumService.cancelAdmirerForum(forumId, null == user ? userId : user.getUserId());
    }


    /**
     * 举报回帖接口
     *
     * @param postingId    当前登录用户ID,可选
     * @param reportUserId 回帖纪录ID
     * @return ResponseDTO
     */
    @ApiOperation(value = "/reportPosting", notes = "举报接口")
    @PostMapping(value = "/reportPosting", produces = "application/json;charset=UTF-8")
    public ResponseDTO reportPosting(String postingId, String reportUserId, String userId) throws Exception {
        if (CommonUtils.isBlank(postingId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        if (CommonUtils.isBlank(reportUserId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少必要参数");
        }
        //登录信息
        UserProp user = this.getCurUserProp();
        if (user == null && CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }
        ActActivityReport o = new ActActivityReport();
        o.setPostingId(postingId);
        o.setReportedUserId(reportUserId);
        o.setUserId(null == user ? userId : user.getUserId());
        return actActivityReportService.create(o, user);
    }


    /**
     * 根据主键删除项目活动论坛回帖情况
     * @param id
     * @return
     * @throws Exception
     */

    @ApiOperation(value = "/deleteByForumId", notes = "根据主键删除项目活动论坛回帖情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deleteByForumId", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteByForumId", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActActivityForumVo> getById(String id) throws Exception {
        return this.actActivityForumService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(删除论坛发贴记录)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    @ApiOperation(value = "/deleteByForumpostingId", notes = "根据主键删除 论坛发贴记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByForumpostingId", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String id) throws Exception {
        return this.actActivityForumpostingService.deleteById(id);
    }
}
