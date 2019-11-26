package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.vo.UsersQVo;
import com.huacainfo.ace.portal.vo.UsersVo;

public interface UsersDao {



    int deleteByPrimaryKey(String id);

    int insert(Users o);


    int updateByPrimaryKey(Users o);


    UsersVo selectVoByPrimaryKey(String id);

    List<UsersVo> findList(@Param("condition") UsersQVo condition,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") UsersQVo condition);

    int isExit(Users o);

    int deleteByIds(String[] ids);


    int initPwd(@Param("userId") String userId,@Param("password") String password);


    int updateStatus(@Param("userId") String userId,@Param("status") String status);


    int assignRoles(@Param("userId") String userId, @Param("roleIds") String[] roleId);

    List<Map<String,Object>> getAllRoles(@Param("syid") String syid);

    List<Map<String,Object>> getMyRoles(@Param("userId") String userId);

    List<Map<String,Object>> getList(Map<String,Object> q);
}