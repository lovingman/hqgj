package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.ServeCultivate;
import com.huacainfo.ace.hqgj.vo.ServeCultivateVo;
import com.huacainfo.ace.hqgj.vo.ServeCultivateQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(培训提升基础表)
 */
public interface ServeCultivateService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(培训提升基础表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeCultivateVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO<ServeCultivateVo> page(ServeCultivateQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建培训提升基础表)
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
     * @Description: TODO(更新培训提升基础表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(String jsons, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取培训提升基础表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeCultivate>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<ServeCultivateVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteServeCultivateById
     * @Description: TODO(删除培训提升基础表)
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
     * @Description: TODO(批量删除培训提升基础表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
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
     * 修改状态  0-待审核  1-进行中 2-未通过 3-已结束',
     * @param id
     * @param status
     * @return
     */
    ResponseDTO updateStatus(String id,String status);


}
