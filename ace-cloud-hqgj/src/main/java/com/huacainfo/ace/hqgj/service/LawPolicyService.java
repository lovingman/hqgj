package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.LawPolicy;
import com.huacainfo.ace.hqgj.vo.LawPolicyVo;
import com.huacainfo.ace.hqgj.vo.LawPolicyQVo;
/**
* @author: 豆文国
* @version: 2019-12-10
* @Description:  TODO(政策服务)
*/
public interface LawPolicyService {
/**
*
* @Title:page
* @Description:  TODO(政策服务分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<LawPolicyVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-12-10
    */
    PageDTO
    <LawPolicyVo> page(LawPolicyQVo condition, int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建政策服务)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO create(LawPolicy o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新政策服务)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO update(LawPolicy o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取政策服务)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<LawPolicy>
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO<LawPolicyVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteLawPolicyById
        * @Description: TODO(删除政策服务)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除政策服务)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
