package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeCultivateSchedule;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateScheduleQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升日程表)
 */
public interface ServeCultivateScheduleService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(培训提升日程表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateScheduleVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO<ServeCultivateScheduleVo> page(ServeCultivateScheduleQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建培训提升日程表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(String jsons, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新培训提升日程表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(ServeCultivateSchedule o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升日程表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivateSchedule>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<ServeCultivateScheduleVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeCultivateScheduleById
     * @Description: TODO(删除培训提升日程表)
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
     * @Description: TODO(批量删除培训提升日程表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
