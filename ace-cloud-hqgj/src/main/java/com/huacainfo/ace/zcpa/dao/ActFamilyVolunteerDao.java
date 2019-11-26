package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActFamilyVolunteer;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerVo;

public interface ActFamilyVolunteerDao {

    int deleteByPrimaryKey(String id);

    int insert(ActFamilyVolunteer o);

    int updateByPrimaryKey(ActFamilyVolunteer o);

    ActFamilyVolunteerVo selectVoByPrimaryKey(String id);

    List<ActFamilyVolunteerVo> findList(@Param("condition") ActFamilyVolunteerQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActFamilyVolunteerQVo condition);

    int isExist(ActFamilyVolunteer o);

    int deleteByIds(String[] ids);

    int deleteByProjectId(@Param("projectId") String projectId);

    List<ActFamilyVolunteer> selectByProjectId(@Param("projectId") String projectId,@Param("targetId")  String targetId);

    int deleteByTargetId(String[] ids);

    List<ActProjectVo> findProjectId(@Param("volunteerId") String volunteerId,@Param("projectState") String state);

    int updateVolunteerId(String volunteerId,String id);

    int batchUpdate(String[] ids);

    Map<String,String> findFamilyTarget(@Param("projectId") String projectId,@Param("volunteerId") String volunteerId);

    Map<String,String> findProjectTarget(@Param("projectId") String projectId,@Param("volunteerId") String volunteerId,String category);

    Map<String,String> findProjectVolunteer(@Param("projectId") String projectId,@Param("volunteerId") String volunteerId,String category);

}
