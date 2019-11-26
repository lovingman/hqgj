package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Dept;
import com.huacainfo.ace.portal.vo.DeptQVo;
import com.huacainfo.ace.portal.vo.DeptVo;

public interface DeptDao {



    int deleteByPrimaryKey(String id);

    int insert(Dept o);


    int updateByPrimaryKey(Dept o);


    DeptVo selectVoByPrimaryKey(String id);

    List<DeptVo> findList(@Param("condition") DeptQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") DeptQVo condition);

    int isExit(Dept o);

    int deleteByIds(String[] ids);

    List<Map<String, Object>> getTree(String corpId);

    List<Map<String,Object>> getList(Map<String,Object> q);


}