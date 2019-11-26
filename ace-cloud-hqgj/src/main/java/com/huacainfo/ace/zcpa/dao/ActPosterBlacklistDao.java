package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActPosterBlacklist;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistQVo;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistVo;

public interface ActPosterBlacklistDao {

    int deleteByPrimaryKey(String id);

    int insert(ActPosterBlacklist o);

    int updateByPrimaryKey(ActPosterBlacklist o);

    ActPosterBlacklistVo selectVoByPrimaryKey(String id);

    List<ActPosterBlacklistVo> findList(@Param("condition") ActPosterBlacklistQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActPosterBlacklistQVo condition);

    int isExist(@Param("postingId") String postingId);

    int deleteByIds(String[] ids);
}
