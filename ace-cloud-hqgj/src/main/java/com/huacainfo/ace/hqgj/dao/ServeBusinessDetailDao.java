package com.huacainfo.ace.hqgj.dao;

import java.util.List;

import com.huacainfo.ace.hqgj.vo.AnnexVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeBusinessDetail;
import com.huacainfo.ace.hqgj.vo.ServeBusinessDetailQVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessDetailVo;

public interface ServeBusinessDetailDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeBusinessDetail o);

    int updateByPrimaryKey(ServeBusinessDetail o);

    ServeBusinessDetailVo selectVoByPrimaryKey(String id);

    List<ServeBusinessDetailVo> findList(@Param("condition") ServeBusinessDetailQVo condition,
                                         @Param("start") int start,
                                         @Param("limit") int limit,
                                         @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeBusinessDetailQVo condition);

    int isExist(ServeBusinessDetail o);

    int deleteByIds(String[] ids);

    List<AnnexVo> annexList(@Param("id") String id);
    int updateState(ServeBusinessDetail serveBusinessDetail);

    int deleteByBusinessIds(String[] ids);

    List<String> selectBusinessIds(@Param("businessIds") String[] businessIds);
}
