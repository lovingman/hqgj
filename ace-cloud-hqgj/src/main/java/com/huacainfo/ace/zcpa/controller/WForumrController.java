package com.huacainfo.ace.zcpa.controller;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.zcpa.service.ActActivityForumpostingService;
import com.huacainfo.ace.zcpa.service.ForumService;
import com.huacainfo.ace.zcpa.service.RegisterService;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingQVo;
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
@Api(value = "/www/forum", tags = "论坛")
@RestController
@RequestMapping("/www/forum")
public class WForumrController extends BizController {
    @Resource
    private ForumService forumService;
    @Resource
    private ActActivityForumpostingService actActivityForumpostingService;
    /**
     * 论坛列表
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/forumpostingPage", notes = "获取论坛发贴记录数据集合，支持分页查询")
    @GetMapping(value = "/forumpostingPage", produces = "application/json;charset=UTF-8")
    public ResponseDTO page(ActActivityForumpostingQVo condition, PageParam page) throws Exception {

        return this.actActivityForumpostingService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());

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

}
