package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActActivityForum;
import com.huacainfo.ace.zcpa.vo.ActActivityForumVo;
import com.huacainfo.ace.zcpa.vo.ActActivityForumQVo;

/**
 * @author: 豆文国
 * @version: 2019-08-02
 * @Description: TODO(项目活动论坛回帖情况)
 */
public interface ActActivityForumService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(项目活动论坛回帖情况分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActActivityForumVo>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    PageDTO<ActActivityForumVo> page(ActActivityForumQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建项目活动论坛回帖情况)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    ResponseDTO create(ActActivityForum o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目活动论坛回帖情况)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    ResponseDTO update(ActActivityForum o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动论坛回帖情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActActivityForum>
     * @author: 豆文国
     * @version: 2019-08-02
     */
    ResponseDTO<ActActivityForumVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActActivityForumById
     * @Description: TODO(删除项目活动论坛回帖情况)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动论坛回帖情况)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-08-02
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 修改项目状态（审核）
     *
     * @param actActivityForum
     * @return
     * @throws Exception
     */
    ResponseDTO updateState(ActActivityForum actActivityForum) throws Exception;

}
