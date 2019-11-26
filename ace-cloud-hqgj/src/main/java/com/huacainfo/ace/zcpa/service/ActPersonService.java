package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActPerson;
import com.huacainfo.ace.zcpa.vo.ActPersonVo;
import com.huacainfo.ace.zcpa.vo.ActPersonQVo;

/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目被服务对象)
 */
public interface ActPersonService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(项目被服务对象分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActPersonVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    PageDTO<ActPersonVo> page(ActPersonQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建项目被服务对象)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO create(ActPerson o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目被服务对象)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO update(ActPerson o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目被服务对象)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActPerson>
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO<ActPersonVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActPersonById
     * @Description: TODO(删除项目被服务对象)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目被服务对象)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
