package com.huacainfo.ace.hqgj.dao;

import com.huacainfo.ace.hqgj.vo.UsersVo;

public interface PersonCenterDao {

    UsersVo selectUserInfo(String userId);

    int deleteByUnionId(String unionId);
}
