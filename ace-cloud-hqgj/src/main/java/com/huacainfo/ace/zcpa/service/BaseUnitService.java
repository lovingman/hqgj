package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.BaseUnit;
import com.huacainfo.ace.zcpa.vo.BaseUnitVo;
import com.huacainfo.ace.zcpa.vo.BaseUnitQVo;
/**
* @author: 何双
* @version: 2019-09-27
* @Description:  TODO(平安单位)
*/
public interface BaseUnitService {
/**
*
* @Title:page
* @Description:  TODO(平安单位分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<BaseUnitVo>
    * @throws
    * @author: 何双
    * @version: 2019-09-27
    */
PageDTO<BaseUnitVo> page(BaseUnitQVo condition,  int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建平安单位)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-09-27
        */
        ResponseDTO create(BaseUnit o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新平安单位)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-09-27
        */
        ResponseDTO update(BaseUnit o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取平安单位)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<BaseUnit>
        * @throws
        * @author: 何双
        * @version: 2019-09-27
        */
        ResponseDTO<BaseUnitVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteBaseUnitById
        * @Description: TODO(删除平安单位)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-09-27
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除平安单位)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-09-27
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
