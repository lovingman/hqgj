package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActSchool;
import com.huacainfo.ace.zcpa.vo.ActSchoolQVo;
import com.huacainfo.ace.zcpa.vo.ActSchoolVo;

public interface ActSchoolDao {

    int deleteByPrimaryKey(String id);

    int insert(ActSchool o);

    int updateByPrimaryKey(ActSchool o);

    ActSchoolVo selectVoByPrimaryKey(String id);

    List<ActSchoolVo> findList(@Param("condition") ActSchoolQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActSchoolQVo condition);

    int isExist(ActSchool o);

    int deleteByIds(String[] ids);
}
