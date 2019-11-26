package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActActivityReport;
import com.huacainfo.ace.zcpa.vo.ActActivityReportVo;
import com.huacainfo.ace.zcpa.vo.ActActivityReportQVo;
/**
* @author: 何双
* @version: 2019-08-06
* @Description:  TODO(项目活动论坛举报情况)
*/
public interface ActActivityReportService {
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
<ActActivityReportVo>
    * @throws
    * @author: 何双
    * @version: 2019-08-06
    */
    NewPageDTO
    <ActActivityReportVo> page(ActActivityReportQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

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
        ResponseDTO create(ActActivityReport o, UserProp userProp) throws Exception;

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
        ResponseDTO update(ActActivityReport o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取项目活动论坛举报情况)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ActActivityReport>
        * @throws
        * @author: 何双
        * @version: 2019-08-06
        */
        ResponseDTO<ActActivityReportVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteActActivityReportById
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
