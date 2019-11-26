package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.BaseUnit;
import com.huacainfo.ace.zcpa.vo.BaseUnitQVo;
import com.huacainfo.ace.zcpa.vo.BaseUnitVo;

public interface BaseUnitDao {

    int deleteByPrimaryKey(String id);

    int insert(BaseUnit o);

    int updateByPrimaryKey(BaseUnit o);

    BaseUnitVo selectVoByPrimaryKey(String id);

    List<BaseUnitVo> findList(@Param("condition") BaseUnitQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BaseUnitQVo condition);

    int isExist(BaseUnit o);

    int deleteByIds(String[] ids);
}
