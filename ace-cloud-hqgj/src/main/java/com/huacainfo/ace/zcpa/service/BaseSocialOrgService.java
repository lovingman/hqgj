package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.BaseSocialOrg;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgVo;
import com.huacainfo.ace.zcpa.vo.BaseSocialOrgQVo;
/**
* @author: 豆文国
* @version: 2019-09-25
* @Description:  TODO(志愿者社会组织)
*/
public interface BaseSocialOrgService {
/**
*
* @Title:page
* @Description:  TODO(志愿者社会组织分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<BaseSocialOrgVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-09-25
    */
    PageDTO<BaseSocialOrgVo> page(BaseSocialOrgQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建志愿者社会组织)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-25
        */
        ResponseDTO create(BaseSocialOrg o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新志愿者社会组织)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-25
        */
        ResponseDTO update(BaseSocialOrg o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取志愿者社会组织)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<BaseSocialOrg>
        * @throws
        * @author: 豆文国
        * @version: 2019-09-25
        */
        ResponseDTO<BaseSocialOrgVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteBaseSocialOrgById
        * @Description: TODO(删除志愿者社会组织)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-25
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除志愿者社会组织)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-09-25
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
