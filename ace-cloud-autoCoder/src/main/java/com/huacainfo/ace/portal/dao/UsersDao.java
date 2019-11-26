package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Users;

public interface UsersDao {
    int deleteByPrimaryKey(String user_id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String user_id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}