package com.huacainfo.ace.zcpa.dao;

import java.util.List;

import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActProjectTopic;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicVo;

public interface ActProjectTopicDao {

    int deleteByPrimaryKey(String id);

    int insert(ActProjectTopic o);

    int updateByPrimaryKey(ActProjectTopic o);

    ActProjectTopicVo selectVoByPrimaryKey(String id);

    List<ActProjectTopicVo> findList(@Param("condition") ActProjectTopicQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActProjectTopicQVo condition);

    int isExist(ActProjectTopic o);

    int deleteByIds(String[] ids);

    int isExistProjectId(@Param("projectId") String projectId);

    int deleteByProjectId(@Param("projectId") String projectId);

    int volunteerCount(@Param("projectCategory") String projectCategory,@Param("projectId") String projectId,@Param("targetId") String targetId,@Param("areaCode") String areaCode);

    List<BaseVolunteerVo> topicVolunteerList(@Param("projectCategory") String projectCategory, @Param("projectId") String projectId, @Param("targetId") String targetId, @Param("areaCode") String areaCode,
                                             @Param("start") int start,
                                             @Param("limit") int limit,
                                             @Param("orderBy") String orderBy);

}
