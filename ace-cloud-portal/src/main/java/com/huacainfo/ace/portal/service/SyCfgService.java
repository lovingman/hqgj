package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;

import java.util.Map;

/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(系统配置)
 */
public interface SyCfgService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(系统配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<SyCfgVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    PageDTO<SyCfgVo> page(SyCfgQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建系统配置)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO create(SyCfg o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新系统配置)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO update(SyCfg o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<SyCfg>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO<SyCfgVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSyCfgById
     * @Description: TODO(删除系统配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统配置)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    ListDTO<Map<String, Object>> selectSyCfgByUser(UserProp userProp);
}
