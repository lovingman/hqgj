package com.huacainfo.ace.zcpa.dao;

import com.huacainfo.ace.zcpa.model.ActActivityAdmirer;
import com.huacainfo.ace.zcpa.vo.ActActivityAdmirerQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityAdmirerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActActivityAdmirerDao {

    int deleteByPrimaryKey(String id);

    int insert(ActActivityAdmirer o);

    int updateByPrimaryKey(ActActivityAdmirer o);

    ActActivityAdmirerVo selectVoByPrimaryKey(String id);

    List<ActActivityAdmirerVo> findList(@Param("condition") ActActivityAdmirerQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActActivityAdmirerQVo condition);

    int isExist(ActActivityAdmirer o);

    int deleteByIds(String[] ids);

    int deleteByPostingIds(String[] ids);

    int insertAdmirer(ActActivityAdmirer data);

    int cancelAdmirer(@Param("postingId") String postingId,
                      @Param("userId") String userId);
}
