package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActActivityForumposting;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingQVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumpostingVo;

/**
 * @author: 何双
 * @version: 2019-08-06
 * @Description: TODO(论坛发贴记录)
 */
public interface ActActivityForumpostingService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(论坛发贴记录分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityForumpostingVo>
     * @author: 何双
     * @version: 2019-08-06
     */
    PageDTO<ActActivityForumpostingVo> page(ActActivityForumpostingQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建论坛发贴记录)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    ResponseDTO create(ActActivityForumposting o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新论坛发贴记录)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    ResponseDTO update(ActActivityForumposting o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取论坛发贴记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityForumposting>
     * @author: 何双
     * @version: 2019-08-06
     */
    ResponseDTO<ActActivityForumpostingVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActActivityForumpostingById
     * @Description: TODO(删除论坛发贴记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除论坛发贴记录)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-08-06
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;


    ResponseDTO updateStatus(String id, String status) throws Exception;

    /**
     * 获取发帖详情接口
     *
     * @param id     发帖纪录主键
     * @param userId 当前登录用户ID,可选
     * @return ResponseDTO
     */
    ResponseDTO getForumPostingDetail(String id, String userId);
}
