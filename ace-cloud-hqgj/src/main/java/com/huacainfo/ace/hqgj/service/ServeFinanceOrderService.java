package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeFinanceOrder;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceOrderQVo;

/**
 * @author: 何双
 * @version: 2019-12-12
 * @Description: TODO(财税服务订单表)
 */
public interface ServeFinanceOrderService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(财税服务订单表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceOrderVo>
     * @author: 何双
     * @version: 2019-12-12
     */
    PageDTO
            <ServeFinanceOrderVo> page(ServeFinanceOrderQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建财税服务订单表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    ResponseDTO create(ServeFinanceOrder o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新财税服务订单表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    ResponseDTO update(ServeFinanceOrder o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务订单表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinanceOrder>
     * @author: 何双
     * @version: 2019-12-12
     */
    ResponseDTO<ServeFinanceOrderVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeFinanceOrderById
     * @Description: TODO(删除财税服务订单表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务订单表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-12
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 修改状态
     * @param id
     * @return
     * @throws Exception
     */
    ResponseDTO updateStatus(String id,String status,UserProp userProp) throws Exception;

}
