package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.LawPolicy;
import com.huacainfo.ace.hqgj.vo.LawPolicyQVo;
import com.huacainfo.ace.hqgj.vo.LawPolicyVo;

public interface LawPolicyDao {

    int deleteByPrimaryKey(String id);

    int insert(LawPolicy o);

    int updateByPrimaryKey(LawPolicy o);

    LawPolicyVo selectVoByPrimaryKey(String id);

    List<LawPolicyVo> findList(@Param("condition") LawPolicyQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LawPolicyQVo condition);

    int isExist(LawPolicy o);

    int deleteByIds(String[] ids);
}
