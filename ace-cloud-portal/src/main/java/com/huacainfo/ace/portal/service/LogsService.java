package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Logs;
import com.huacainfo.ace.portal.vo.LogsVo;
import com.huacainfo.ace.portal.vo.LogsQVo;

/**
 * @author: wangen
 * @version: 2019-06-01
 * @Description: TODO(操作日志)
 */
public interface LogsService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(操作日志分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <LogsVo>
     * @author: wangen
     * @version: 2019-06-01
     */
    PageDTO<LogsVo> page(LogsQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;


    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取操作日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Logs>
     * @author: wangen
     * @version: 2019-06-01
     */
    ResponseDTO<LogsVo> getById(String id) throws Exception;


}
