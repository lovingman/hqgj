package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.LawPolicy;
import com.huacainfo.ace.hqgj.vo.LawPolicyVo;
import com.huacainfo.ace.hqgj.vo.LawPolicyQVo;

/**
 * @author: 豆文国
 * @version: 2019-12-10
 * @Description: TODO(政策服务)
 */
public interface LawPolicyService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(政策服务分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <LawPolicyVo>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    PageDTO
            <LawPolicyVo> page(LawPolicyQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建政策服务)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO create(LawPolicy o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新政策服务)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO update(LawPolicy o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取政策服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<LawPolicy>
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO<LawPolicyVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteLawPolicyById
     * @Description: TODO(删除政策服务)
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
     * @Description: TODO(批量删除政策服务)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 修改状态 政策服务发布状态（1-待发布 2-已发布）
     *
     * @param id
     * @param status
     * @return
     */
    ResponseDTO updateStatus(String id, String status);
}
