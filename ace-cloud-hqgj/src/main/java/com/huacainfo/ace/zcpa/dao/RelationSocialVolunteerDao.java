package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.RelationSocialVolunteer;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerVo;

public interface RelationSocialVolunteerDao {

    int deleteByPrimaryKey(String id);

    int insert(RelationSocialVolunteer o);

    int updateByPrimaryKey(RelationSocialVolunteer o);

    RelationSocialVolunteerVo selectVoByPrimaryKey(String id);

    List<RelationSocialVolunteerVo> findList(@Param("condition") RelationSocialVolunteerQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RelationSocialVolunteerQVo condition);

    int isExist(RelationSocialVolunteer o);

    int deleteByIds(String[] ids);
}
