package com.huacainfo.ace.hqgj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeFinanceItem;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemQVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemVo;

public interface ServeFinanceItemDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeFinanceItem o);

    int updateByPrimaryKey(ServeFinanceItem o);

    ServeFinanceItemVo selectVoByPrimaryKey(String id);

    List<ServeFinanceItemVo> findList(@Param("condition") ServeFinanceItemQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeFinanceItemQVo condition);

    int isExist(ServeFinanceItem o);

    int deleteByIds(String[] ids);
}
