package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessAppendQVo;
/**
* @author: 何双
* @version: 2019-12-13
* @Description:  TODO(创业服务资料其它附加信息)
*/
public interface ServeBusinessAppendService {
/**
*
* @Title:page
* @Description:  TODO(创业服务资料其它附加信息分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ServeBusinessAppendVo>
    * @throws
    * @author: 何双
    * @version: 2019-12-13
    */
    PageDTO
    <ServeBusinessAppendVo> page(ServeBusinessAppendQVo condition, int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建创业服务资料其它附加信息)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-13
        */
        ResponseDTO create(ServeBusinessAppend o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新创业服务资料其它附加信息)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-13
        */
        ResponseDTO update(ServeBusinessAppend o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取创业服务资料其它附加信息)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ServeBusinessAppend>
        * @throws
        * @author: 何双
        * @version: 2019-12-13
        */
        ResponseDTO<ServeBusinessAppendVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteServeBusinessAppendById
        * @Description: TODO(删除创业服务资料其它附加信息)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-13
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除创业服务资料其它附加信息)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-13
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
