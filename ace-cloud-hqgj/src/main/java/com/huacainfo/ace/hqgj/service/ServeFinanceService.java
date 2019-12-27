package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeFinance;
import com.huacainfo.ace.hqgj.vo.ServeFinanceVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceQVo;

/**
 * @author: 何双
 * @version: 2019-12-10
 * @Description: TODO(财税服务包)
 */
public interface ServeFinanceService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(财税服务包分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceVo>
     * @author: 何双
     * @version: 2019-12-10
     */
    PageDTO
            <ServeFinanceVo> page(ServeFinanceQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建财税服务包)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    ResponseDTO create(ServeFinance o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新财税服务包)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    ResponseDTO update(ServeFinance o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinance>
     * @author: 何双
     * @version: 2019-12-10
     */
    ResponseDTO<ServeFinanceVo> getById(String id,String userId) throws Exception;

    /**
     * @throws
     * @Title:deleteServeFinanceById
     * @Description: TODO(删除财税服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务包)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;



    /**
     * 修改封面图片
     * @param id
     * @param coverUrl
     * @return
     */
    ResponseDTO  updateCoverUrl (String id,String coverUrl);

    /**
     * 修改状态 0-待审核 1-审核通过 2-未通过 3-已上线 4-已下线',
     * @param id
     * @param status
     * @return
     */
    ResponseDTO updateStatus(String id,String status,String reason);

}
