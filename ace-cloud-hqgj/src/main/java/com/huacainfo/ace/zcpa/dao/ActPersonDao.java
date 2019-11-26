package com.huacainfo.ace.zcpa.dao;

import java.util.List;

import com.huacainfo.ace.zcpa.model.Province;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActPerson;
import com.huacainfo.ace.zcpa.vo.ActPersonQVo;
import com.huacainfo.ace.zcpa.vo.ActPersonVo;

public interface ActPersonDao {

    int deleteByPrimaryKey(String id);

    int insert(ActPerson o);

    int updateByPrimaryKey(ActPerson o);

    ActPersonVo selectVoByPrimaryKey(String id);

    List<ActPersonVo> findList(@Param("condition") ActPersonQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActPersonQVo condition);

    int isExist(ActPerson o);

    int deleteByIds(String[] ids);


    Province selcectAreaCode(@Param("areaCode")String areaCode);
}
