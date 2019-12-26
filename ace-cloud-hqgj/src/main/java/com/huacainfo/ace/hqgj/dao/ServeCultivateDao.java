package com.huacainfo.ace.hqgj.dao;

import java.util.List;

import com.huacainfo.ace.hqgj.vo.ServeCultivateDetail;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.vo.ServeCultivateQVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateVo;

public interface ServeCultivateDao {

    int deleteByPrimaryKey(String id);

    int insert(ServeCultivate o);

    int updateByPrimaryKey(ServeCultivate o);

    ServeCultivateVo selectVoByPrimaryKey(String id);

    List<ServeCultivateVo> findList(@Param("condition") ServeCultivateQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ServeCultivateQVo condition);

    int isExist(ServeCultivate o);

    int deleteByIds(String[] ids);

    int updateCoverUrl(@Param("id") String id, @Param("coverUrl") String coverUrl);

    int updateStatus(@Param("id") String id,@Param("status") String status);

    int addEnrollNum(@Param("id") String id);

    int cancalEnrollNum(@Param("id") String id);

    ServeCultivateDetail selectByIdDetails(@Param("id") String id,@Param("userId")String userId);
}
