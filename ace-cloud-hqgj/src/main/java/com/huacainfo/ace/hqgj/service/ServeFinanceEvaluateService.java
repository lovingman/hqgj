package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeFinanceEvaluate;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceEvaluateQVo;
/**
* @author: 何双
* @version: 2019-12-12
* @Description:  TODO(财税服务订单评价表)
*/
public interface ServeFinanceEvaluateService {
/**
*
* @Title:page
* @Description:  TODO(财税服务订单评价表分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ServeFinanceEvaluateVo>
    * @throws
    * @author: 何双
    * @version: 2019-12-12
    */
    PageDTO
    <ServeFinanceEvaluateVo> page(ServeFinanceEvaluateQVo condition, int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建财税服务订单评价表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-12
        */
        ResponseDTO create(ServeFinanceEvaluate o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新财税服务订单评价表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-12
        */
        ResponseDTO update(ServeFinanceEvaluate o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取财税服务订单评价表)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ServeFinanceEvaluate>
        * @throws
        * @author: 何双
        * @version: 2019-12-12
        */
        ResponseDTO<ServeFinanceEvaluateVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteServeFinanceEvaluateById
        * @Description: TODO(删除财税服务订单评价表)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-12
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除财税服务订单评价表)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-12-12
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
