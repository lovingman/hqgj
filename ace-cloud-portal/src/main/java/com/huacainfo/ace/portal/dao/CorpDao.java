package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Corp;
import com.huacainfo.ace.portal.vo.CorpQVo;
import com.huacainfo.ace.portal.vo.CorpVo;

public interface CorpDao {



    int deleteByPrimaryKey(String id);

    int insert(Corp o);


    int updateByPrimaryKey(Corp o);


    CorpVo selectVoByPrimaryKey(String id);

    List<CorpVo> findList(@Param("condition") CorpQVo condition,
                          @Param("start") int start,
                          @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CorpQVo condition);

    int isExit(Corp o);

    int deleteByIds(String[] ids);

    List<Map<String,Object>> getList(Map<String,Object> q);

    List<Map<String,String>> getAreaDict();

    List<Map<String,String>> getTypeDict();

    List<Map<String,String>> getCorpDict();
}