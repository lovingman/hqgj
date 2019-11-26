package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.vo.DictQVo;
import com.huacainfo.ace.portal.vo.DictVo;

import java.util.List;
import java.util.Map;

/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(数据字典)
 */
public interface DictService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(数据字典分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<DictVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    PageDTO<DictVo> page(DictQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建数据字典)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO create(Dict o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新数据字典)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO update(Dict o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取数据字典)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Dict>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO<DictVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteDictById
     * @Description: TODO(删除数据字典)
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
     * @Description: TODO(批量删除数据字典)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     * @return ResponseDTO<Map < String, List < DictVo>>>
     */
    ResponseDTO<Map<String, List<Dict>>> getByCategoryIds(String[] categoryIds);

    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     * @return ResponseDTO<Map < String, Map < String, String>>>
     */
    ResponseDTO<Map<String, Map<String, String>>> getDictKeyMap(String[] categoryIds);

    ListDTO<Tree> getResTreeList(String topId, String categoryId);

    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(数据导入)
     * @param: @param List<Map<String, Object>> list
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-07-04
     */
    ResponseDTO importXls(List<Object> list,String categoryId) throws Exception;
}
