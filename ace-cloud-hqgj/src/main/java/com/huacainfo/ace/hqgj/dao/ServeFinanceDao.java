package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeFinance;
import com.huacainfo.ace.hqgj.vo.ServeFinanceQVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceVo;

public interface ServeFinanceDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeFinance o);

    int updateByPrimaryKey(ServeFinance o);

    ServeFinanceVo selectVoByPrimaryKey(String id);

    List<ServeFinanceVo> findList(@Param("condition") ServeFinanceQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeFinanceQVo condition);

    int isExist(ServeFinance o);

    int deleteByIds(String[] ids);
}
