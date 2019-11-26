package com.huacainfo.ace.zcpa.dao;


import com.huacainfo.ace.zcpa.model.Volunteering;
import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
import com.huacainfo.ace.zcpa.vo.VolunteeringVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VolunteeringDao {
    List<Volunteering> volunteerQuotaCount(String areaCode, String projectId);

    List participationDocking(@Param("aidTargetType") String aidTargetType, String areaCode, String projectId);

    List<VolunteeringVo> schoolVolunteerQuota(String areaCode, String projectId);

    int wupdateVolunteer(String projectId, String volunteerId);

    int wdeleteVolunteer(String projectId, String volunteerId);

    List<BaseVolunteerVo> findVolunteerList(@Param("projectId") String projectId,
                                            @Param("category") String category,
                                            @Param("start") int start,
                                            @Param("limit") int limit,
                                            @Param("orderBy") String orderBy);

    int findVolunteerCount(@Param("projectId") String projectId,
                           @Param("category") String category);

    List<ActProjectVo> findMyVolunteerServiceList(@Param("volunteerId") String volunteerId, @Param("status") String status,
                                                  @Param("start") int start,
                                                  @Param("limit") int limit,
                                                  @Param("orderBy") String orderBy);

    int myVolunteerServiceCount(@Param("volunteerId") String volunteerId, @Param("status") String status);


    //获取当前有没有参与进行中项目
    int findState(@Param("volunteerId") String volunteerId);

    Map<String, Object> getSpoorData(String volunteerId);


    String selectWechatHeadimgurl(@Param("userId") String userId);
}
