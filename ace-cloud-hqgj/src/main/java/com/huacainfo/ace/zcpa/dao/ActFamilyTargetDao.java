package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActFamilyTarget;
import com.huacainfo.ace.zcpa.vo.ActFamilyTargetQVo;
import com.huacainfo.ace.zcpa.vo.ActFamilyTargetVo;

public interface ActFamilyTargetDao {

    int deleteByPrimaryKey(String id);

    int insert(ActFamilyTarget o);

    int updateByPrimaryKey(ActFamilyTarget o);

    ActFamilyTargetVo selectVoByPrimaryKey(String id);

    List<ActFamilyTargetVo> findList(@Param("condition") ActFamilyTargetQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActFamilyTargetQVo condition);

    int isExist(ActFamilyTarget o);

    int deleteByIds(String[] ids);

    int deleteByProjectId(@Param("projectId") String projectId);

    ActFamilyTargetVo selectById(@Param("id") String id ,@Param("category") String category);

}
