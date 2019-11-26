package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Dept;
import com.huacainfo.ace.portal.vo.DeptVo;
import com.huacainfo.ace.portal.vo.DeptQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2019-05-16
 * @Description: TODO(科室)
 */
public interface DeptService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(科室分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<DeptVo>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    PageDTO<DeptVo> page(DeptQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建科室)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO create(Dept o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新科室)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO update(Dept o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取科室)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Dept>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO<DeptVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteDeptById
     * @Description: TODO(删除科室)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除科室)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * @throws
     * @Title:getTree
     * @Description: TODO(科室树)
     * @param: @param corpId
     * @param: @throws Exception
     * @return: ListDTO<Tree>
     * @author: 陈晓克
     * @version: 2019-05-16
     */

    ListDTO<Tree> getTree(String corpId) throws Exception;


    ListDTO<Map<String,Object>> getList(Map<String,Object> p) throws Exception;

}
