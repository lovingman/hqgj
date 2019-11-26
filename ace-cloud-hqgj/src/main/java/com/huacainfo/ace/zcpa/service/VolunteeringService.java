package com.huacainfo.ace.zcpa.service;


import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteerSignout;
import com.huacainfo.ace.zcpa.model.Volunteering;
import com.huacainfo.ace.zcpa.vo.*;

import java.util.List;
import java.util.Map;


public interface VolunteeringService {

    ResponseDTO<List<Volunteering>> volunteerQuotaCount(String areaCode, String projectId) throws Exception;

    ResponseDTO participationDocking(VolunteeringQVo qvo, UserProp userProp) throws Exception;

    ResponseDTO<List<VolunteeringVo>> schoolVolunteerQuota(String areaCode, String projectId) throws Exception;

    ResponseDTO signOutVolunteer(ActProjectVolunteerSignout o, String category, UserProp userProp) throws Exception;

    ResponseDTO submitVoluntaryServices(String jsons, UserProp userProp) throws Exception;

    ResponseDTO<ActProjectActivityVo> selectByActivityId(String id);


    PageDTO<BaseVolunteerVo> findVolunteerList(String projectId, String category, int start, int limit, String orderBy);

    PageDTO<ActProjectVo> findMyVolunteerServiceList(String volunteerId, String status, int start, int limit, String orderBy);

    /**
     * 获取志愿者足迹数据
     *
     * @param volunteerId 志愿者ID;不传人时获取当前登录者id
     * @return Map<String, Object>
     */
    Map<String, Object> getSpoorData(String volunteerId);


    /**
     * 获取微信头像
     * @param userId  当前用户id
     * @return
     */
    String selectWechatHeadimgurl(String userId);
}
