package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteerSignout;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerSignoutQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerSignoutVo;

public interface ActProjectVolunteerSignoutDao {

    int deleteByPrimaryKey(String id);

    int insert(ActProjectVolunteerSignout o);

    int updateByPrimaryKey(ActProjectVolunteerSignout o);

    ActProjectVolunteerSignoutVo selectVoByPrimaryKey(String id);

    List<ActProjectVolunteerSignoutVo> findList(@Param("condition") ActProjectVolunteerSignoutQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActProjectVolunteerSignoutQVo condition);

    int isExist(ActProjectVolunteerSignout o);

    int deleteByIds(String[] ids);

    int deleteVolunteer(@Param("projectId") String projectId,@Param("volunteerId") String volunteerId );
}
