package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeFinanceEvaluate;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateQVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateVo;

public interface ServeFinanceEvaluateDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeFinanceEvaluate o);

    int updateByPrimaryKey(ServeFinanceEvaluate o);

    ServeFinanceEvaluateVo selectVoByPrimaryKey(String id);

    List<ServeFinanceEvaluateVo> findList(@Param("condition") ServeFinanceEvaluateQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeFinanceEvaluateQVo condition);

    int isExist(ServeFinanceEvaluate o);

    int deleteByIds(String[] ids);
}
