package com.huacainfo.ace.zcpa.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.*;
import com.huacainfo.ace.zcpa.model.*;
import com.huacainfo.ace.zcpa.service.ForumService;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingVo;
import com.huacainfo.ace.zcpa.vo.ForumVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ForumServiceImpl implements ForumService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActActivityForumpostingDao actActivityForumpostingDao;
    @Resource
    private ActActivityAttachDao actActivityAttachDao;
    @Resource
    private ActActivityForumDao actActivityForumDao;
    @Resource
    private ActActivityAdmirerDao actActivityAdmirerDao;
    @Resource
    private ActAdmirerForumDao actAdmirerForumDao;
    @Resource
    private BaseVolunteerDao baseVolunteerDao;

    /**
     * 论坛发帖
     */
    @Override
    @Transactional
    public ResponseDTO forumPosting(String jsons, UserProp userProp) throws Exception {
        JSONObject jsonObj = JSON.parseObject(jsons);
        ActActivityForumposting o = JSON.parseObject(jsonObj.getString("actActivityForumposting"), ActActivityForumposting.class);
        List<ActActivityAttach> actActivityAttach = new ArrayList<ActActivityAttach>(JSONArray.parseArray(jsonObj.getString("actActivityAttach"), ActActivityAttach.class));
        String[] keys = {"actActivityForumposting"};
        CommonUtils.validateJsonObj(jsonObj, keys);
        if (CommonUtils.isBlank(o.getProjectCode())) {
            return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
        }
        BaseVolunteer volunteer = baseVolunteerDao.selectVoByPrimaryKey(userProp.getUserId());
        if (CommonUtils.isBlank(volunteer)) {
            return new ResponseDTO(ResultCode.FAIL, "获取用户信息失败！");
        }
        String actId = GUIDUtil.getGUID();
        o.setId(actId);
        o.setAreaCode(volunteer.getAreaCode());
        o.setPostingId(userProp.getUserId());
        o.setCreateDate(new Date());
        o.setStatus("3");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());

        if (!CommonUtils.isBlank(actActivityAttach)) {
            for (ActActivityAttach a : actActivityAttach) {
                a.setId(GUIDUtil.getGUID());
                a.setActid(actId);
                a.setType("1");
                a.setStatus("1");
                a.setCreatedate(new Date());
                actActivityAttachDao.insert(a);
            }
        }

        int i = this.actActivityForumpostingDao.insert(o);
        if (i < 1) {
            return new ResponseDTO(ResultCode.FAIL, "发帖失败,请重试");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功");
    }

    /**
     * 获取回帖列表接口
     *
     * @return ResponseDTO
     * @throws Exception
     */
    @Override
    public PageDTO<ForumVo> findForumList(ActActivityForumQVo condition, int start, int limit, String orderBy) {

        PageDTO<ForumVo> rst = new PageDTO<>();
        List<ForumVo> list = this.actActivityForumDao.findForumList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actActivityForumDao.findForumListCount(condition);
            rst.setTotal(allRows);
        }
        return rst;


    }

    /**
     * 回帖接口
     *
     * @param postingId 发帖纪录ID
     * @param text      回帖类容
     * @param userId    当前登录用户ID,可选
     * @return ResponseDTO
     * @throws Exception
     */
    @Transactional
    @Override
    public ResponseDTO insertFornum(String postingId, String text, String userId, String attachList) {
        String forumId = GUIDUtil.getGUID();
        ActActivityForum data = new ActActivityForum();
        data.setId(forumId);
        data.setActivityid(postingId);
        data.setPostingId(postingId);
        data.setUserid(userId);
        data.setCommenttext(text);
        data.setCommenttime(new Date());
        data.setCreatedate(new Date());
        //附件添加
        if (!CommonUtils.isBlank(attachList)) {
            List<ActActivityAttach> list = JsonUtil.toList(attachList, ActActivityAttach.class);
            for (ActActivityAttach item : list) {
                item.setId(GUIDUtil.getGUID());
                item.setActid(forumId);
                item.setStatus("1");
                item.setCreatedate(new Date());
                actActivityAttachDao.insert(item);
            }
        }

        int i = actActivityForumDao.insert(data);
        if (i < 1) {
            return new ResponseDTO(ResultCode.FAIL, "回帖失败,请重试");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "回帖成功");
    }

    /**
     * 点赞发帖接口
     *
     * @param postingId 发帖纪录ID
     * @param userId    当前登录用户ID
     * @return ResponseDTO
     * @throws Exception
     */
    @Override
    public ResponseDTO admirerForumPosting(String postingId, String userId) {
        ActActivityAdmirer data = new ActActivityAdmirer();
        data.setId(GUIDUtil.getGUID());
        data.setActivityId(postingId);
        data.setPostingId(postingId);
        data.setUserId(userId);
        data.setAdmirerState("1");
        data.setCreateDate(new Date());

        int i = actActivityAdmirerDao.insertAdmirer(data);
        return new ResponseDTO(ResultCode.SUCCESS, "点赞成功", "1");
    }

    /**
     * 点赞回帖接口
     *
     * @param forumId 回帖纪录ID
     * @param userId  当前登录用户ID
     * @return ResponseDTO
     * @throws Exception
     */
    @Override
    public ResponseDTO admirerForum(String forumId, String userId) {
        ActAdmirerForum data = new ActAdmirerForum();
        data.setId(GUIDUtil.getGUID());
        data.setForumid(forumId);
        data.setUserid(userId);
        data.setAdmirerstate("1");
        data.setStatus("1");
        data.setCreatedate(new Date());

        int i = actAdmirerForumDao.insertAdmirer(data);
        return new ResponseDTO(ResultCode.SUCCESS, "点赞成功", "1");
    }

    /**
     * 取消点赞发帖接口
     *
     * @param postingId 发帖纪录ID
     * @param userId    当前登录用户ID,可选
     */
    @Override
    public ResponseDTO cancelAdmirerForumPosting(String postingId, String userId) {
        int i = actActivityAdmirerDao.cancelAdmirer(postingId, userId);
        if (i < 1) {
            return new ResponseDTO(ResultCode.FAIL, "取消点赞失败,请重试", "1");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "取消点赞成功", "0");
    }

    /**
     * 取消点赞回帖接口
     *
     * @param forumId 回帖纪录ID
     * @param userId  当前登录用户ID,可选
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO cancelAdmirerForum(String forumId, String userId) {
        int i = actAdmirerForumDao.cancelAdmirer(forumId, userId);
        if (i < 1) {
            return new ResponseDTO(ResultCode.FAIL, "取消点赞失败,请重试", "1");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "取消点赞成功", "0");
    }


    /**
     * 我回复的帖子
     *
     * @param userId
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    @Override
    public PageDTO<ActActivityForumpostingVo> myForumpostingList(String userId, int start, int limit, String orderBy) {
        PageDTO<ActActivityForumpostingVo> rst = new PageDTO<>();
        List<ActActivityForumpostingVo> list = this.actActivityForumpostingDao.myForumpostingList(userId, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actActivityForumpostingDao.myForumpostingListCount(userId);
            rst.setTotal(allRows);
        }
        return rst;
    }

}
