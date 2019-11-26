package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.RelationSocialVolunteer;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerVo;
import com.huacainfo.ace.zcpa.vo.RelationSocialVolunteerQVo;
/**
* @author: 豆文国
* @version: 2019-09-26
* @Description:  TODO(志愿者与社会组织关联)
*/
public interface RelationSocialVolunteerService {
/**
*
* @Title:page
* @Description:  TODO(志愿者与社会组织关联分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<RelationSocialVolunteerVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-09-26
    */
    PageDTO<RelationSocialVolunteerVo> page(RelationSocialVolunteerQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建志愿者与社会组织关联)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-26
        */
        ResponseDTO create(RelationSocialVolunteer o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新志愿者与社会组织关联)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-26
        */
        ResponseDTO update(RelationSocialVolunteer o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取志愿者与社会组织关联)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<RelationSocialVolunteer>
        * @throws
        * @author: 豆文国
        * @version: 2019-09-26
        */
        ResponseDTO<RelationSocialVolunteerVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteRelationSocialVolunteerById
        * @Description: TODO(删除志愿者与社会组织关联)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-26
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除志愿者与社会组织关联)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-26
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
