package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActProjectActivity;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityVo;
import com.huacainfo.ace.zcpa.vo.ActProjectActivityQVo;

/**
 * @author: 豆文国
 * @version: 2019-07-30
 * @Description: TODO(活动详情)
 */
public interface ActProjectActivityService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(活动详情分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectActivityVo>
     * @author: 豆文国
     * @version: 2019-07-30
     */
    PageDTO
            <ActProjectActivityVo> page(ActProjectActivityQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建活动详情)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    ResponseDTO create(ActProjectActivity o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新活动详情)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    ResponseDTO update(ActProjectActivity o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取活动详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectActivity>
     * @author: 豆文国
     * @version: 2019-07-30
     */
    ResponseDTO<ActProjectActivityVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActProjectActivityById
     * @Description: TODO(删除活动详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除活动详情)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-30
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 修改项目状态（审核）
     *
     * @param actProjectActivity
     * @return
     * @throws Exception
     */
    ResponseDTO updateProjectState(ActProjectActivity actProjectActivity) throws Exception;

}
