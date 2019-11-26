package com.huacainfo.ace.zcpa.dao;

import com.huacainfo.ace.zcpa.model.ActProjectTarget;
import com.huacainfo.ace.zcpa.vo.ActProjectTargetQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectTargetVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActProjectTargetDao {

    int deleteByPrimaryKey(String id);

    int insert(ActProjectTarget o);

    int updateByPrimaryKey(ActProjectTarget o);

    ActProjectTargetVo selectVoByPrimaryKey(String id);

    List<ActProjectTargetVo> findList(@Param("condition") ActProjectTargetQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActProjectTargetQVo condition);

    int isExist(ActProjectTarget o);

    int deleteByIds(String[] ids);

    int deleteByProjectId(@Param("projectId") String projectId);

    List<Map<String, Object>> getProjectAreaTree(String projectId);

    List<Map<String, Object>> getFamilyAreaTree(String projectId);
}
