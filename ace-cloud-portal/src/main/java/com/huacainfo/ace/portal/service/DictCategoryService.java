package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.DictCategory;
import com.huacainfo.ace.portal.vo.DictCategoryQVo;
import com.huacainfo.ace.portal.vo.DictCategoryVo;

import java.util.List;

/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(数据字典)
 */
public interface DictCategoryService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(数据字典分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<DictCategoryVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    PageDTO<DictCategoryVo> page(DictCategoryQVo condition, int start, int limit, String orderBy) throws Exception;

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
    ResponseDTO create(DictCategory o, UserProp userProp) throws Exception;

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
    ResponseDTO update(DictCategory o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取数据字典)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<DictCategory>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO<DictCategoryVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteDictCategoryById
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
     * @throws
     * @Title:findDictCategoryListAll
     * @Description: TODO(获取所有字典类型列表)
     * @param: @return
     * @param: @throws Exception
     * @return: List<DictCategory>
     */
    List<DictCategory> findDictCategoryListAll(UserProp curUserProp);
}
