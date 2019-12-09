package com.huacainfo.ace.hqgj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollQVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollVo;

public interface ServeCultivateEnrollDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeCultivateEnroll o);

    int updateByPrimaryKey(ServeCultivateEnroll o);

    ServeCultivateEnrollVo selectVoByPrimaryKey(String id);

    List<ServeCultivateEnrollVo> findList(@Param("condition") ServeCultivateEnrollQVo condition,
                                          @Param("start") int start,
                                          @Param("limit") int limit,
                                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeCultivateEnrollQVo condition);

    int isExist(ServeCultivateEnroll o);

    int deleteByIds(String[] ids);
}
