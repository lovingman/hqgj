package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeBusiness;
import com.huacainfo.ace.hqgj.vo.ServeBusinessQVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessVo;

public interface ServeBusinessDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeBusiness o);

    int updateByPrimaryKey(ServeBusiness o);

    ServeBusinessVo selectVoByPrimaryKey(String id);

    List<ServeBusinessVo> findList(@Param("condition") ServeBusinessQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeBusinessQVo condition);

    int isExist(ServeBusiness o);

    int deleteByIds(String[] ids);
}
