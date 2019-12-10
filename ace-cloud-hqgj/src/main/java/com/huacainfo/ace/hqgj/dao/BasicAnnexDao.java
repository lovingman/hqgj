package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.vo.BasicAnnexQVo;
import com.huacainfo.ace.hqgj.vo.BasicAnnexVo;

public interface BasicAnnexDao {

    int deleteByPrimaryKey(String id);

    int insert(BasicAnnex o);

    int updateByPrimaryKey(BasicAnnex o);

    BasicAnnexVo selectVoByPrimaryKey(String id);

    List<BasicAnnexVo> findList(@Param("condition") BasicAnnexQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") BasicAnnexQVo condition);

    int isExist(BasicAnnex o);

    int deleteByIds(String[] ids);

    int deleteByRelationIds(String[] relationIds);
}
