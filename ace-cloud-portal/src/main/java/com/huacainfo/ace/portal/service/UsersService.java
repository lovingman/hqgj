package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.vo.UsersVo;
import com.huacainfo.ace.portal.vo.UsersQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2019-05-12
 * @Description: TODO(系统用户)
 */
public interface UsersService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(系统用户分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<UsersVo>
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    PageDTO<UsersVo> page(UsersQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建系统用户)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO create(Users o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新系统用户)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO update(Users o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统用户)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Users>
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO<UsersVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteUsersById
     * @Description: TODO(删除系统用户)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统用户)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * @throws
     * @Title:initPwd
     * @Description: TODO(初始化密码)
     * @param: @param userId
     * @param: @param password
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO initPwd(String userId,String password) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param userId
     * @param: @param status
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO updateStatus(String userId,String status) throws Exception;



    /**
     * @throws
     * @Title:assignRoles
     * @Description: TODO(分配角色)
     * @param: @param userId
     * @param: @param roleIds
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO assignRoles(String userId,String[] roleIds) throws Exception;

    /**
     * @throws
     * @Title:assignRoles
     * @Description: TODO(分配角色)
     * @param: @param userId
     * @param: @param roleIds
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ResponseDTO deleteassignRoles(String userId) throws Exception;


    /**
     * @throws
     * @Title:getAllRoles
     * @Description: TODO(获取系统已有角色)
     * @param: @param userId
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ListDTO<Map<String,Object>> getAllRoles(String syid) throws Exception;


    /**
     * @throws
     * @Title:getMyRoles
     * @Description: TODO(获取当前用户已分配角色)
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    ListDTO<Map<String,Object>> getMyRoles(String userId) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(用户查询，主要用于可搜索下拉框)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListDTO<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-07-23
     */

    ListDTO<Map<String,Object>> getList(Map<String,Object> p) throws Exception;


    ResponseDTO updatePwd(String oldPwd,String newPwd,UserProp userProp) throws Exception;



}
