package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.vo.ProvinceQVo;
import com.huacainfo.ace.portal.vo.ProvinceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProvinceDao {

    int deleteByPrimaryKey(String id);

    int insert(Province o);

    int updateByPrimaryKey(Province o);

    ProvinceVo selectVoByPrimaryKey(String id);

    List<ProvinceVo> findList(@Param("condition") ProvinceQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ProvinceQVo condition);

    int isExist(Province o);

    int deleteByIds(String[] ids);

    List<Map<String,Object>> getListByPid(String pid);
}
