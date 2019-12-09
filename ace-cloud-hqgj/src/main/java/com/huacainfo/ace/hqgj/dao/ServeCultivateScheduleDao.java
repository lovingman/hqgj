package com.huacainfo.ace.hqgj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleQVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleVo;

public interface ServeCultivateScheduleDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeCultivateSchedule o);

    int updateByPrimaryKey(ServeCultivateSchedule o);

    ServeCultivateScheduleVo selectVoByPrimaryKey(String id);

    List<ServeCultivateScheduleVo> findList(@Param("condition") ServeCultivateScheduleQVo condition,
                                            @Param("start") int start,
                                            @Param("limit") int limit,
                                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeCultivateScheduleQVo condition);

    int isExist(ServeCultivateSchedule o);

    int deleteByIds(String[] ids);
}
