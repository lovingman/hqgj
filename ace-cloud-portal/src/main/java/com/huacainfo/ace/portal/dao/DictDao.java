package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.vo.DictQVo;
import com.huacainfo.ace.portal.vo.DictVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictDao {

    int deleteByPrimaryKey(String id);

    int insert(Dict o);

    int updateByPrimaryKey(Dict o);

    DictVo selectVoByPrimaryKey(String id);

    List<DictVo> findList(@Param("condition") DictQVo condition,
                          @Param("start") int start,
                          @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") DictQVo condition);

    int isExist(Dict o);

    int deleteByIds(String[] ids);

    int isExitByNameAndCategoryId(@Param("name") String name,
                                  @Param("categoryId") String categoryId);

    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     */
    List<Dict> getByCategoryIds(@Param("categoryIds") String[] categoryIds);

    List<Map<String, Object>> selectDictTreeList(String categoryId);

    int updateByCategoryAndCode(Dict o);

}
