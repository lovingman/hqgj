package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeFinanceOrder;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderQVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderVo;

public interface ServeFinanceOrderDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeFinanceOrder o);

    int updateByPrimaryKey(ServeFinanceOrder o);

    ServeFinanceOrderVo selectVoByPrimaryKey(String id);

    List<ServeFinanceOrderVo> findList(@Param("condition") ServeFinanceOrderQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeFinanceOrderQVo condition);

    int isExist(ServeFinanceOrder o);

    int deleteByIds(String[] ids);

    int updateStatus(String id,String status);
}
