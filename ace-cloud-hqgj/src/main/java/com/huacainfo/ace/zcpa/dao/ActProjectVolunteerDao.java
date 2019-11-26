package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteer;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerVo;

public interface ActProjectVolunteerDao {

    int deleteByPrimaryKey(String id);

    int insert(ActProjectVolunteer o);

    int updateByPrimaryKey(ActProjectVolunteer o);

    ActProjectVolunteerVo selectVoByPrimaryKey(String id);

    List<ActProjectVolunteerVo> findList(@Param("condition") ActProjectVolunteerQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActProjectVolunteerQVo condition);

    int isExist(ActProjectVolunteer o);

    int deleteByIds(String[] ids);

    int deleteByProjectId(@Param("projectId") String projectId);

    List<ActProjectVolunteerVo> selectByProjectId(@Param("projectId") String projectId,@Param("targetId") String targetId);

    int deleteByTargetId(String[] ids);
}
