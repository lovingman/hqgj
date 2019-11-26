package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActActivityAttach;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachVo;
import com.huacainfo.ace.zcpa.vo.ActActivityAttachQVo;
/**
* @author: 豆文国
* @version: 2019-07-31
* @Description:  TODO(活动附件)
*/
public interface ActActivityAttachService {
/**
*
* @Title:page
* @Description:  TODO(活动附件分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ActActivityAttachVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-07-31
    */
    NewPageDTO
    <ActActivityAttachVo> page(ActActivityAttachQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建活动附件)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-31
        */
        ResponseDTO create(ActActivityAttach o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新活动附件)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-31
        */
        ResponseDTO update(ActActivityAttach o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取活动附件)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ActActivityAttach>
        * @throws
        * @author: 豆文国
        * @version: 2019-07-31
        */
        ResponseDTO<ActActivityAttachVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteActActivityAttachById
        * @Description: TODO(删除活动附件)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-31
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除活动附件)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-31
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
