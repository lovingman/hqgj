package com.huacainfo.ace.zcpa.service;


import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingVo;
import com.huacainfo.ace.zcpa.vo.ForumVo;


public interface ForumService {
    ResponseDTO forumPosting(String jsons, UserProp userProp) throws Exception;

    /**
     * 获取回帖列表接口
     *
     * @return ResponseDTO
     * @throws Exception
     */
    PageDTO<ForumVo> findForumList(ActActivityForumQVo condition, int start, int limit, String orderBy);

    /**
     * 回帖接口
     *
     * @param userId    当前登录用户ID,可选
     * @param postingId 发帖纪录ID
     * @param text      回帖类容
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO insertFornum(String postingId, String text, String userId, String attachList);

    /**
     * 点赞发帖接口
     *
     * @param userId    当前登录用户ID
     * @param postingId 发帖纪录ID
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO admirerForumPosting(String postingId, String userId);

    /**
     * 点赞回帖接口
     *
     * @param userId  当前登录用户ID
     * @param forumId 回帖纪录ID
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO admirerForum(String forumId, String userId);

    /**
     * 取消点赞发帖接口
     *
     * @param userId    当前登录用户ID,可选
     * @param postingId 发帖纪录ID
     * @return ResponseDTO
     */
    ResponseDTO cancelAdmirerForumPosting(String postingId, String userId);


    /**
     * 取消点赞回帖接口
     *
     * @param userId  当前登录用户ID,可选
     * @param forumId 回帖纪录ID
     * @return ResponseDTO
     */
    ResponseDTO cancelAdmirerForum(String forumId, String userId);


    /**
     * 获取回复的帖子列表
     *
     * @return ResponseDTO
     * @throws Exception
     */
    PageDTO <ActActivityForumpostingVo> myForumpostingList (String userId, int start, int limit, String orderBy);

}
