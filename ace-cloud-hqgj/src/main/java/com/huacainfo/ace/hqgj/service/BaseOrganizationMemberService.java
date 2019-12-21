package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationMemberVo;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationMemberQVo;

/**
 * @author: 豆文国
 * @version: 2019-12-10
 * @Description: TODO(服务机构成员)
 */
public interface BaseOrganizationMemberService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(服务机构成员分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseOrganizationMemberVo>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    PageDTO
            <BaseOrganizationMemberVo> page(BaseOrganizationMemberQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建服务机构成员)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO create(BaseOrganizationMember o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新服务机构成员)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO update(BaseOrganizationMember o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取服务机构成员)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseOrganizationMember>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO<BaseOrganizationMemberVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBaseOrganizationMemberById
     * @Description: TODO(删除服务机构成员)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除服务机构成员)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;


}
