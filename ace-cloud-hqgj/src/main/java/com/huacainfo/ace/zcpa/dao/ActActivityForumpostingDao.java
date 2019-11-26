package com.huacainfo.ace.zcpa.dao;

import com.huacainfo.ace.zcpa.model.ActActivityForumposting;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActActivityForumpostingDao {

    int deleteByPrimaryKey(String id);

    int insert(ActActivityForumposting o);

    int updateByPrimaryKey(ActActivityForumposting o);

    ActActivityForumpostingVo selectVoByPrimaryKey(String id);

    List<ActActivityForumpostingVo> findList(@Param("condition") ActActivityForumpostingQVo condition,
                                             @Param("start") int start,
                                             @Param("limit") int limit,
                                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActActivityForumpostingQVo condition);

    int isExist(ActActivityForumposting o);

    int deleteByIds(String[] ids);


    int updateStatus(@Param("id") String id, @Param("status") String status);

    ActActivityForumpostingVo getForumPostingDetail(@Param("id") String id,
                                                    @Param("userId") String userId);

    /**
     * 我回复的帖子
     * @param userId
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    List<ActActivityForumpostingVo> myForumpostingList(@Param("userId") String userId,
                                             @Param("start") int start,
                                             @Param("limit") int limit,
                                             @Param("orderBy") String orderBy);


    int myForumpostingListCount(@Param("userId") String userId);
}
