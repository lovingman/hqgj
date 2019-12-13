package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendQVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendVo;

public interface ServeBusinessAppendDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeBusinessAppend o);

    int updateByPrimaryKey(ServeBusinessAppend o);

    ServeBusinessAppendVo selectVoByPrimaryKey(String id);

    List<ServeBusinessAppendVo> findList(@Param("condition") ServeBusinessAppendQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeBusinessAppendQVo condition);

    int isExist(ServeBusinessAppend o);

    int deleteByIds(String[] ids);
}
