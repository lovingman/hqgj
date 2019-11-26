package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.BaseVolunteer;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;

public interface BaseVolunteerDao {

    int deleteByPrimaryKey(String id);

    int insert(BaseVolunteer o);

    int updateByPrimaryKey(BaseVolunteer o);

    BaseVolunteerVo selectVoByPrimaryKey(String id);

    List<BaseVolunteerVo> findList(@Param("condition") BaseVolunteerQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseVolunteerQVo condition);

    int isExist(BaseVolunteer o);

    int deleteByIds(String[] ids);


}
