package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActPosterBlacklist;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistVo;
import com.huacainfo.ace.zcpa.vo.ActPosterBlacklistQVo;

/**
 * @author: 何双
 * @version: 2019-08-07
 * @Description: TODO(发贴人黑名单列表)
 */
public interface ActPosterBlacklistService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(发贴人黑名单列表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActPosterBlacklistVo>
     * @author: 何双
     * @version: 2019-08-07
     */
    PageDTO<ActPosterBlacklistVo> page(ActPosterBlacklistQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建发贴人黑名单列表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    ResponseDTO create(String jsons, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新发贴人黑名单列表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    ResponseDTO update(ActPosterBlacklist o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取发贴人黑名单列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActPosterBlacklist>
     * @author: 何双
     * @version: 2019-08-07
     */
    ResponseDTO<ActPosterBlacklistVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActPosterBlacklistById
     * @Description: TODO(删除发贴人黑名单列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除发贴人黑名单列表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-07
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 判断用户是否存在黑名单
     * @param postingId   发帖人id
     * @return
     * @throws Exception
     */
    ResponseDTO isExist(String postingId)throws  Exception;

}
