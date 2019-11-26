package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.vo.ConfigQVo;
import com.huacainfo.ace.portal.vo.ConfigVo;

/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(系统参数配置)
 */
public interface ConfigService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(系统参数配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<ConfigVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    PageDTO<ConfigVo> page(ConfigQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建系统参数配置)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO create(Config o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新系统参数配置)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO update(Config o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统参数配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Config>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO<ConfigVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteConfigById
     * @Description: TODO(删除系统参数配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统参数配置)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 根据key查找配置信息
     *
     * @param syId   系统标识
     * @param cfgKey key
     * @return ConfigVo
     */
    Config findByKey(String syId, String cfgKey);

}
