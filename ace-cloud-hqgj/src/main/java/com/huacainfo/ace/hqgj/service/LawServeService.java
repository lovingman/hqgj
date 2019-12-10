package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.LawServe;
import com.huacainfo.ace.hqgj.vo.LawServeVo;
import com.huacainfo.ace.hqgj.vo.LawServeQVo;
/**
* @author: 豆文国
* @version: 2019-12-10
* @Description:  TODO(法律服务)
*/
public interface LawServeService {
/**
*
* @Title:page
* @Description:  TODO(法律服务分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<LawServeVo>
    * @throws
    * @author: 豆文国
    * @version: 2019-12-10
    */
    PageDTO
    <LawServeVo> page(LawServeQVo condition, int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建法律服务)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO create(LawServe o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新法律服务)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO update(LawServe o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取法律服务)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<LawServe>
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO<LawServeVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteLawServeById
        * @Description: TODO(删除法律服务)
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
        * @Description: TODO(批量删除法律服务)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2019-12-10
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
