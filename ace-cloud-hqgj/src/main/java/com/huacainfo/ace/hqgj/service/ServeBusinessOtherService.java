package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeBusinessOther;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherVo;
import com.huacainfo.ace.hqgj.vo.ServeBusinessOtherQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(创业服务资料清单其它资料)
 */
public interface ServeBusinessOtherService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(创业服务资料清单其它资料分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeBusinessOtherVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO
            <ServeBusinessOtherVo> page(ServeBusinessOtherQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建创业服务资料清单其它资料)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(ServeBusinessOther o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新创业服务资料清单其它资料)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(ServeBusinessOther o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取创业服务资料清单其它资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeBusinessOther>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<ServeBusinessOtherVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeBusinessOtherById
     * @Description: TODO(删除创业服务资料清单其它资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除创业服务资料清单其它资料)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
