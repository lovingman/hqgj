package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.LawServe;
import com.huacainfo.ace.hqgj.vo.LawServeQVo;
import com.huacainfo.ace.hqgj.vo.LawServeVo;

public interface LawServeDao {

    int deleteByPrimaryKey(String id);

    int insert(LawServe o);

    int updateByPrimaryKey(LawServe o);

    LawServeVo selectVoByPrimaryKey(String id);

    List<LawServeVo> findList(@Param("condition") LawServeQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LawServeQVo condition);

    int isExist(LawServe o);

    int deleteByIds(String[] ids);
}
