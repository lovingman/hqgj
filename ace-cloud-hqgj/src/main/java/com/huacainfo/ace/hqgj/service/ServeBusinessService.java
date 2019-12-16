package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeBusiness;
import com.huacainfo.ace.hqgj.vo.ServeBusinessVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务包)
 */
public interface ServeBusinessService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(创业服务包分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO<ServeBusinessVo> page(ServeBusinessQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建创业服务包)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(ServeBusiness o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新创业服务包)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(ServeBusiness o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusiness>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<ServeBusinessVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeBusinessById
     * @Description: TODO(删除创业服务包)
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
     * @Description: TODO(批量删除创业服务包)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;


    /**
     *创业服务包免费服务申请表
     * @param id 主键
     * @return
     * @throws Exception
     */
    ResponseDTO<ServeBusinessVo> previewInfo(String id)throws Exception;
}
