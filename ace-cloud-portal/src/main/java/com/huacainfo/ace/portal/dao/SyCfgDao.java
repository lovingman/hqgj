package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SyCfgDao {

    int deleteByPrimaryKey(String id);

    int insert(SyCfg o);

    int updateByPrimaryKey(SyCfg o);

    SyCfgVo selectVoByPrimaryKey(String id);

    List<SyCfgVo> findList(@Param("condition") SyCfgQVo condition,
                           @Param("start") int start,
                           @Param("limit") int limit,
                           @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SyCfgQVo condition);

    int isExist(SyCfg o);

    int deleteByIds(String[] ids);

    List<Map<String, Object>> selectSyCfgByUser(@Param("syid") String[] syid);
}
