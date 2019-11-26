package com.huacainfo.ace.zcpa.dao;

import com.huacainfo.ace.zcpa.model.ActAdmirerForum;
import com.huacainfo.ace.zcpa.vo.ActAdmirerForumQVo;
import com.huacainfo.ace.zcpa.vo.ActAdmirerForumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActAdmirerForumDao {

    int deleteByPrimaryKey(String id);

    int insert(ActAdmirerForum o);

    int updateByPrimaryKey(ActAdmirerForum o);

    ActAdmirerForumVo selectVoByPrimaryKey(String id);

    List<ActAdmirerForumVo> findList(@Param("condition") ActAdmirerForumQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActAdmirerForumQVo condition);

    int isExist(ActAdmirerForum o);

    int deleteByIds(String[] ids);

    int insertAdmirer(ActAdmirerForum data);

    int cancelAdmirer(@Param("forumId") String forumId,
                      @Param("userId") String userId);
}
