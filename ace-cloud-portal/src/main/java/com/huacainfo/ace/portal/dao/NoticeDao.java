package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Notice;
import com.huacainfo.ace.portal.vo.NoticeQVo;
import com.huacainfo.ace.portal.vo.NoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeDao {

    int deleteByPrimaryKey(String id);

    int insert(Notice o);

    int updateByPrimaryKey(Notice o);

    NoticeVo selectVoByPrimaryKey(String id);

    List<NoticeVo> findList(@Param("condition") NoticeQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") NoticeQVo condition);

    int isExist(Notice o);

    int deleteByIds(String[] ids);
}
