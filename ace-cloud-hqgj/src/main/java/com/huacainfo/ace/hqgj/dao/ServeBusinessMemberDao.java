package com.huacainfo.ace.hqgj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeBusinessMember;
import com.huacainfo.ace.hqgj.vo.ServeBusinessMemberQVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessMemberVo;

public interface ServeBusinessMemberDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeBusinessMember o);

    int updateByPrimaryKey(ServeBusinessMember o);

    ServeBusinessMemberVo selectVoByPrimaryKey(String id);

    List<ServeBusinessMemberVo> findList(@Param("condition") ServeBusinessMemberQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeBusinessMemberQVo condition);

    int isExist(ServeBusinessMember o);

    int deleteByIds(String[] ids);
}
