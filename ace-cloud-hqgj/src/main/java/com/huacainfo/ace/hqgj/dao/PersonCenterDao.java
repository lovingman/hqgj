package com.huacainfo.ace.hqgj.dao;

import com.huacainfo.ace.hqgj.vo.UsersVo;
import org.apache.ibatis.annotations.Param;

public interface PersonCenterDao {

    UsersVo selectUserInfo(String userId);

    int deleteByUnionId(String unionId);

    String selectAreaName(@Param("code") String code);
}
