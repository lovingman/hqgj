package com.huacainfo.ace.hqgj.dao;

import java.util.List;

import com.huacainfo.ace.hqgj.vo.IntegralDetailedVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeBusinessIntegral;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralQVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralVo;

public interface ServeBusinessIntegralDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeBusinessIntegral o);

    int updateByPrimaryKey(ServeBusinessIntegral o);

    ServeBusinessIntegralVo selectVoByPrimaryKey(String id);

    List<ServeBusinessIntegralVo> findList(@Param("condition") ServeBusinessIntegralQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeBusinessIntegralQVo condition);

    int isExist(ServeBusinessIntegral o);

    int deleteByIds(String[] ids);

    List<IntegralDetailedVo> integralDetailed(@Param("userId") String userId);
}
