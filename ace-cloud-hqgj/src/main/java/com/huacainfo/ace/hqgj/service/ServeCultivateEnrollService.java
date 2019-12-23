package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeCultivateEnroll;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateEnrollQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升 - 报名管理)
 */
public interface ServeCultivateEnrollService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(培训提升 - 报名管理分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateEnrollVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO<ServeCultivateEnrollVo> page(ServeCultivateEnrollQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建培训提升 - 报名管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(ServeCultivateEnroll o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新培训提升 - 报名管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(ServeCultivateEnroll o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升 - 报名管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivateEnroll>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<ServeCultivateEnrollVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeCultivateEnrollById
     * @Description: TODO(删除培训提升 - 报名管理)
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
     * @Description: TODO(批量删除培训提升 - 报名管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 批量修改状态
     * @param status
     * @param ids
     * @return
     * @throws Exception
     */
    ResponseDTO updateStatus(String status,String[] ids)throws Exception;

}
