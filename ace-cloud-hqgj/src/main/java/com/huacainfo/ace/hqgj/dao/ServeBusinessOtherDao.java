package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeBusinessOther;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherQVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherVo;

public interface ServeBusinessOtherDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeBusinessOther o);

    int updateByPrimaryKey(ServeBusinessOther o);

    ServeBusinessOtherVo selectVoByPrimaryKey(String id);

    List<ServeBusinessOtherVo> findList(@Param("condition") ServeBusinessOtherQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeBusinessOtherQVo condition);

    int isExist(ServeBusinessOther o);

    int deleteByIds(String[] ids);
}
