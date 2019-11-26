package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Dept;

public interface DeptDao {
    int deleteByPrimaryKey(String id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}