package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.Province;
import com.huacainfo.ace.portal.vo.ProvinceQVo;
import com.huacainfo.ace.portal.vo.ProvinceVo;

import java.util.List;
import java.util.Map;

/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(行政区划)
 */
public interface ProvinceService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(行政区划分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<ProvinceVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    PageDTO<ProvinceVo> page(ProvinceQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建行政区划)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO create(Province o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新行政区划)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO update(Province o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取行政区划)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Province>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO<ProvinceVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteProvinceById
     * @Description: TODO(删除行政区划)
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
     * @Description: TODO(批量删除行政区划)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;


    ListDTO<Map<String,Object>> getListByPid(String pid) throws Exception;

    /**
     * execel导入接口
     *
     * @param list 文件流数组
     * @return ResponseDTO
     * @throws Exception
     */
    ResponseDTO importXls(List<Object> list, UserProp userProp) throws Exception;


}
