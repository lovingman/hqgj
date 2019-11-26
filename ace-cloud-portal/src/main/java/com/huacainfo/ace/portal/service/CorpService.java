package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Corp;
import com.huacainfo.ace.portal.vo.CorpExcelVo;
import com.huacainfo.ace.portal.vo.CorpVo;
import com.huacainfo.ace.portal.vo.CorpQVo;

import java.util.List;
import java.util.Map;


/**
 * @author: 陈晓克
 * @version: 2019-05-16
 * @Description: TODO(企事业单位)
 */
public interface CorpService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(企事业单位分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<CorpVo>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    PageDTO<CorpVo> page(CorpQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建企事业单位)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO create(Corp o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企事业单位)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO update(Corp o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企事业单位)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Corp>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO<CorpVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deletecorpById
     * @Description: TODO(删除企事业单位)
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
     * @Description: TODO(批量删除企事业单位)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;



    /**
     * @throws
     * @Title:getList
     * @Description: TODO(名称、行政区划查询企事业单位，主要用于可搜索下拉框)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListDTO<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-05-17
     */

    ListDTO<Map<String,Object>> getList(Map<String,Object> p) throws Exception;

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
    ResponseDTO importXls(List<Object> list) throws Exception;

}
