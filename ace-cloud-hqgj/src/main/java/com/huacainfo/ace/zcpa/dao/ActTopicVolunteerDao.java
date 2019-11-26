package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActTopicVolunteer;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerVo;

public interface ActTopicVolunteerDao {

    int deleteByPrimaryKey(String id);

    int insert(ActTopicVolunteer o);

    int updateByPrimaryKey(ActTopicVolunteer o);

    ActTopicVolunteerVo selectVoByPrimaryKey(String id);

    List<ActTopicVolunteerVo> findList(@Param("condition") ActTopicVolunteerQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActTopicVolunteerQVo condition);

    int isExist(ActTopicVolunteer o);

    int deleteByIds(String[] ids);
}
