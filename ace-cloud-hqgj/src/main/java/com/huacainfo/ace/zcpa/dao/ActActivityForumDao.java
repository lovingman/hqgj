package com.huacainfo.ace.zcpa.dao;

import com.huacainfo.ace.zcpa.model.ActActivityForum;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumVo;
import com.huacainfo.ace.zcpa.vo.ForumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActActivityForumDao {

    int deleteByPrimaryKey(String id);

    int insert(ActActivityForum o);

    int updateByPrimaryKey(ActActivityForum o);

    ActActivityForumVo selectVoByPrimaryKey(String id);

    List<ActActivityForumVo> findList(@Param("condition") ActActivityForumQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActActivityForumQVo condition);

    int isExist(ActActivityForum o);

    int updateState(ActActivityForum actActivityForum);

    int deleteByIds(String[] ids);

    int deleteByPostingIds(String[] ids);

    List<ForumVo> findForumList(@Param("condition") ActActivityForumQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findForumListCount(@Param("condition") ActActivityForumQVo condition);

}
