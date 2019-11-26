package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.RoleResources;
import com.huacainfo.ace.portal.vo.RoleResourcesQVo;
import com.huacainfo.ace.portal.vo.RoleResourcesVo;

/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(角色资源)
 */
public interface RoleResourcesService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(角色资源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<RoleResourcesVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    PageDTO<RoleResourcesVo> page(RoleResourcesQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建角色资源)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO create(RoleResources o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新角色资源)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO update(RoleResources o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取角色资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<RoleResources>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO<RoleResourcesVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRoleResourcesById
     * @Description: TODO(删除角色资源)
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
     * @Description: TODO(批量删除角色资源)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
