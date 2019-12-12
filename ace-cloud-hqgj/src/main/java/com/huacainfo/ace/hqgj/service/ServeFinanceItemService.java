package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeFinanceItem;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceItemQVo;
/**
* @author: 何双
* @version: 2019-12-11
* @Description:  TODO(财税管家服务项目)
*/
public interface ServeFinanceItemService {
/**
*
* @Title:page
* @Description:  TODO(财税管家服务项目分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ServeFinanceItemVo>
    * @throws
    * @author: 何双
    * @version: 2019-12-11
    */
    PageDTO
    <ServeFinanceItemVo> page(ServeFinanceItemQVo condition, int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建财税管家服务项目)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-11
        */
        ResponseDTO create(ServeFinanceItem o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新财税管家服务项目)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-11
        */
        ResponseDTO update(ServeFinanceItem o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取财税管家服务项目)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ServeFinanceItem>
        * @throws
        * @author: 何双
        * @version: 2019-12-11
        */
        ResponseDTO<ServeFinanceItemVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteServeFinanceItemById
        * @Description: TODO(删除财税管家服务项目)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-11
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除财税管家服务项目)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-11
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
