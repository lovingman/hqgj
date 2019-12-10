package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationVo;
import com.huacainfo.ace.hqgj.vo.BaseOrganizationQVo;
/**
* @author: 豆文国
* @version: 2019-12-09
* @Description:  TODO(企业管理)
*/
public interface BaseOrganizationService {
/**
*
* @Title:page
* @Description:  TODO(企业管理分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<BaseOrganizationVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-12-09
    */
    PageDTO
    <BaseOrganizationVo> page(BaseOrganizationQVo condition, int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建企业管理)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-09
        */
        ResponseDTO create(BaseOrganization o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新企业管理)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-09
        */
        ResponseDTO update(BaseOrganization o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取企业管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<BaseOrganization>
        * @throws
        * @author: 豆文国
        * @version: 2019-12-09
        */
        ResponseDTO<BaseOrganizationVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteBaseOrganizationById
        * @Description: TODO(删除企业管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-09
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除企业管理)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-09
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
