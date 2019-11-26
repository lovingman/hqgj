package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActProjectTopic;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicVo;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicQVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;

/**
* @author: 豆文国
* @version: 2019-07-29
* @Description:  TODO(项目活动主题)
*/
public interface ActProjectTopicService {
/**
*
* @Title:page
* @Description:  TODO(项目活动主题分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ActProjectTopicVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-07-29
    */
PageDTO<ActProjectTopicVo> page(ActProjectTopicQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建项目活动主题)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-29
        */
        ResponseDTO create(ActProjectTopic o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新项目活动主题)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-29
        */
        ResponseDTO update(ActProjectTopic o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取项目活动主题)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ActProjectTopic>
        * @throws
        * @author: 豆文国
        * @version: 2019-07-29
        */
        ResponseDTO<ActProjectTopicVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteActProjectTopicById
        * @Description: TODO(删除项目活动主题)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-29
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除项目活动主题)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-07-29
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;


        /**
         * 获取主题推送的志愿者列表
         * @param
         * @param pageNum
         * @param pageSize
         * @param orderBy
         * @return
         * @throws Exception
         */
        PageDTO<BaseVolunteerVo> topicVolunteerList(String projectCategory, String projectId, String targetId, String areaCode,
                                                    int pageNum, int pageSize, String orderBy) throws Exception;

}
