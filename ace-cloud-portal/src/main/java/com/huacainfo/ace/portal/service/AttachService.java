package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;

/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(通知公告)
 */
public interface AttachService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<AttachVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    PageDTO<AttachVo> page(AttachQVo condition) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建通知公告)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO create(Attach o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新通知公告)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO update(Attach o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Attach>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO<AttachVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteAttachById
     * @Description: TODO(删除通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除通知公告)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
