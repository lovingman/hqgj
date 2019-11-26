package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.DictCategory;
import com.huacainfo.ace.portal.vo.DictCategoryQVo;
import com.huacainfo.ace.portal.vo.DictCategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictCategoryDao {

    int deleteByPrimaryKey(String id);

    int insert(DictCategory o);

    int updateByPrimaryKey(DictCategory o);

    DictCategoryVo selectVoByPrimaryKey(String id);

    List<DictCategoryVo> findList(@Param("condition") DictCategoryQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") DictCategoryQVo condition);

    int isExist(DictCategory o);

    int deleteByIds(@Param("ids") String[] ids);

    List<DictCategory> findListAll(@Param("syid") String syid);
}
