package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActActivityAttach;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachVo;

public interface ActActivityAttachDao {

    int deleteByPrimaryKey(String id);

    int insert(ActActivityAttach o);

    int updateByPrimaryKey(ActActivityAttach o);

    ActActivityAttachVo selectVoByPrimaryKey(String id);

    List<ActActivityAttachVo> findList(@Param("condition") ActActivityAttachQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActActivityAttachQVo condition);

    int isExist(ActActivityAttach o);

    int deleteByIds(String[] ids);
}
