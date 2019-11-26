package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.vo.ConfigQVo;
import com.huacainfo.ace.portal.vo.ConfigVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigDao {

    int deleteByPrimaryKey(String id);

    int insert(Config o);

    int updateByPrimaryKey(Config o);

    ConfigVo selectVoByPrimaryKey(String id);

    List<ConfigVo> findList(@Param("condition") ConfigQVo condition,
                            @Param("start") int start,
                            @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ConfigQVo condition);

    int isExist(Config o);

    int deleteByIds(String[] ids);

    Config findByKey(@Param("syId") String syId,
                     @Param("cfgKey") String cfgKey);
}
