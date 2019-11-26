package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActActivityAdmirer;
import com.huacainfo.ace.zcpa.vo.ActActivityAdmirerVo;
import com.huacainfo.ace.zcpa.vo.ActActivityAdmirerQVo;
/**
* @author: 何双
* @version: 2019-08-06
* @Description:  TODO(项目活动论坛举报情况)
*/
public interface ActActivityAdmirerService {
/**
*
* @Title:page
* @Description:  TODO(项目活动论坛举报情况分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ActActivityAdmirerVo>
    * @throws
    * @author: 何双
    * @version: 2019-08-06
    */
    NewPageDTO
    <ActActivityAdmirerVo> page(ActActivityAdmirerQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建项目活动论坛举报情况)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-08-06
        */
        ResponseDTO create(ActActivityAdmirer o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新项目活动论坛举报情况)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-08-06
        */
        ResponseDTO update(ActActivityAdmirer o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取项目活动论坛举报情况)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ActActivityAdmirer>
        * @throws
        * @author: 何双
        * @version: 2019-08-06
        */
        ResponseDTO<ActActivityAdmirerVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteActActivityAdmirerById
        * @Description: TODO(删除项目活动论坛举报情况)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-08-06
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除项目活动论坛举报情况)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 何双
        * @version: 2019-08-06
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
