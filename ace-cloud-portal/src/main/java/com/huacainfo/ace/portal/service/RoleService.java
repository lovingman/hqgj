package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.CheckTree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.vo.RoleQVo;
import com.huacainfo.ace.portal.vo.RoleVo;

import java.util.List;

/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(用户角色)
 */
public interface RoleService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(用户角色分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<RoleVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    PageDTO<RoleVo> page(RoleQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建用户角色)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO create(Role o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新用户角色)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO update(Role o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取用户角色)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Role>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO<RoleVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRoleById
     * @Description: TODO(删除用户角色)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除用户角色)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    ResponseDTO insertRoleResources(String roleId, String[] resourcesIds, UserProp userProp);

    List<CheckTree> getRoleResTreeList(String roleId);
    /**
     * @throws Exception
     * @Title:selectRoleResByRoleId
     * @Description: TODO(角色编号获取已经分配的资源ID)
     * @param: @param roleId
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-07-14
     */
    ResponseDTO<String[]> selectRoleResByRoleId(String roleId) throws Exception;


}
