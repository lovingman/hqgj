package com.huacainfo.ace.zcpa.dao;

import com.huacainfo.ace.zcpa.model.ActProject;
import com.huacainfo.ace.zcpa.vo.ActProjectQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import com.huacainfo.ace.zcpa.vo.TargetVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActProjectDao {

    int deleteByPrimaryKey(String id);

    int insert(ActProject o);

    int updateByPrimaryKey(ActProject o);

    ActProjectVo selectVoByPrimaryKey(@Param("id") String id,@Param("volunteerId") String volunteerId);

    List<ActProjectVo> findList(@Param("condition") ActProjectQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActProjectQVo condition);

    int isExist(ActProject o);

    int deleteByIds(String[] ids);

    int updateProjectState(@Param("id") String id, @Param("projectState") String projectState);

    ActProjectVo selectById(@Param("id") String id);

    ActProjectVo selectSchoolById(@Param("id") String id);

    ActProjectVo selectOtherById(@Param("id") String id ,@Param("category") String category);

    String findPid(@Param("areaCode")String areaCode,@Param("category")String category);


    /**
     * 项目对接,被服务对象列表 关联志愿者.category=1 为情暖家庭.2为校园
     * @param projectId
     * @param category
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    List<TargetVo> findVolunteerList(@Param("projectId") String projectId,
                                     @Param("category")String category,@Param("areaCode")String areaCode,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findVolunteerCount(@Param("projectId") String projectId,
                           @Param("category")String category,@Param("areaCode")String areaCode);


    int updateCoverUrl(@Param("projectId") String projectId,
                       @Param("coverUrl") String coverUrl);

    /**
     * 已对接志愿者--情暖家庭
     * @param projectId
     * @param areaCode
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    List<TargetVo>  findVolunteerDockedList (@Param("projectId") String projectId,
                                             @Param("areaCode")String areaCode,
                                             @Param("start") int start,
                                             @Param("limit") int limit,
                                             @Param("orderBy") String orderBy);

    int  findVolunteerDockedCount (@Param("projectId") String projectId,
                                 @Param("areaCode")String areaCode);

    int isdelete(@Param("id")String id);
}
