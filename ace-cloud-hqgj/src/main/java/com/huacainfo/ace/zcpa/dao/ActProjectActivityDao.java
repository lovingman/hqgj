package com.huacainfo.ace.zcpa.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.zcpa.model.ActProjectActivity;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityVo;

public interface ActProjectActivityDao {

    int deleteByPrimaryKey(String id);

    int insert(ActProjectActivity o);

    int updateByPrimaryKey(ActProjectActivity o);

    ActProjectActivityVo selectVoByPrimaryKey(String id);

    List<ActProjectActivityVo> findList(@Param("condition") ActProjectActivityQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActProjectActivityQVo condition);

    int isExist(ActProjectActivity o);

    int deleteByIds(String[] ids);

    int updateProjectState(ActProjectActivity actProjectActivity);

    int selectByPrimaryKey(String s);

    ActProjectActivityVo selectByActivityId(String id);

}
