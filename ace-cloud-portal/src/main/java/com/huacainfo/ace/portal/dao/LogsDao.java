package com.huacainfo.ace.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Logs;
import com.huacainfo.ace.portal.vo.LogsQVo;
import com.huacainfo.ace.portal.vo.LogsVo;

public interface LogsDao {



    LogsVo selectVoByPrimaryKey(String id);

    List<LogsVo> findList(@Param("condition") LogsQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LogsQVo condition);


}
