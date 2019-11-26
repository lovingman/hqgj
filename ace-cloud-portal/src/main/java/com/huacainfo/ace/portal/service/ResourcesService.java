package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.vo.ResourcesQVo;
import com.huacainfo.ace.portal.vo.ResourcesVo;

import java.util.List;
import java.util.Map;

/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(资源文件管理)
 */
public interface ResourcesService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(资源文件管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<ResourcesVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    PageDTO<ResourcesVo> page(ResourcesQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建资源文件管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO create(Resources o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新资源文件管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO update(Resources o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取资源文件管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Resources>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO<ResourcesVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteResourcesById
     * @Description: TODO(删除资源文件管理)
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
     * @Description: TODO(批量删除资源文件管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    List<Tree> getResTreeList(String topId);

    /**
     * execel导入接口
     *
     * @param list 文件流数组
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO importXls(List<Object> list, UserProp userProp) throws Exception;

    /**
     * 资源排序接口
     *
     * @param list list
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO updateSequence(List<Map<String, Object>> list, UserProp userProp);

    /**
     * 鉴权加载系统的资源
     */
    List<Map<String, String>> loadResourceDefine();

    ResponseDTO<List<Map<String, Object>>> getListByPid(String pid) throws Exception;
}
