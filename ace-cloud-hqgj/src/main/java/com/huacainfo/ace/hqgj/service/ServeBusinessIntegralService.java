package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeBusinessIntegral;
import com.huacainfo.ace.hqgj.vo.IntegralDetailedVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessIntegralQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(积分管理)
 */
public interface ServeBusinessIntegralService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(积分管理分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessIntegralVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO
            <ServeBusinessIntegralVo> page(ServeBusinessIntegralQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建积分管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(ServeBusinessIntegral o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新积分管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(ServeBusinessIntegral o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取积分管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessIntegral>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<ServeBusinessIntegralVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeBusinessIntegralById
     * @Description: TODO(删除积分管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除积分管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;


    /**
     * 查看积分明细
     * @param userId
     * @return
     * @throws Exception
     */
    ResponseDTO<IntegralDetailedVo> IntegralDetailed(String userId)throws Exception;

}
