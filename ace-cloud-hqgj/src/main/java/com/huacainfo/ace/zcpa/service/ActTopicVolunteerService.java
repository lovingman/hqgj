package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActTopicVolunteer;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerVo;
import com.huacainfo.ace.zcpa.vo.ActTopicVolunteerQVo;
/**
* @author: 豆文国
* @version: 2019-08-02
* @Description:  TODO(项目活动主题参与志愿者列表)
*/
public interface ActTopicVolunteerService {
/**
*
* @Title:page
* @Description:  TODO(项目活动主题参与志愿者列表分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ActTopicVolunteerVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-08-02
    */
    NewPageDTO
    <ActTopicVolunteerVo> page(ActTopicVolunteerQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建项目活动主题参与志愿者列表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-08-02
        */
        ResponseDTO create(ActTopicVolunteer o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新项目活动主题参与志愿者列表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-08-02
        */
        ResponseDTO update(ActTopicVolunteer o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取项目活动主题参与志愿者列表)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ActTopicVolunteer>
        * @throws
        * @author: 豆文国
        * @version: 2019-08-02
        */
        ResponseDTO<ActTopicVolunteerVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteActTopicVolunteerById
        * @Description: TODO(删除项目活动主题参与志愿者列表)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-08-02
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除项目活动主题参与志愿者列表)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-08-02
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
