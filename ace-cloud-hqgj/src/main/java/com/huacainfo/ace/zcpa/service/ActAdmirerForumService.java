package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActAdmirerForum;
import com.huacainfo.ace.zcpa.vo.ActAdmirerForumQVo;
import com.huacainfo.ace.zcpa.vo.ActAdmirerForumVo;

/**
 * @author: ArvinZou
 * @version: 2019-08-13
 * @Description: TODO(点赞回帖)
 */
public interface ActAdmirerForumService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(点赞回帖分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActAdmirerForumVo>
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    NewPageDTO
            <ActAdmirerForumVo> page(ActAdmirerForumQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建点赞回帖)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    ResponseDTO create(ActAdmirerForum o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新点赞回帖)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    ResponseDTO update(ActAdmirerForum o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取点赞回帖)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActAdmirerForum>
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    ResponseDTO<ActAdmirerForumVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActAdmirerForumById
     * @Description: TODO(删除点赞回帖)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除点赞回帖)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-08-13
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
