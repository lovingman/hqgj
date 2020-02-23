package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.CompanyAppeal;
import com.huacainfo.ace.hqgj.vo.CompanyAppealExlVo;
import com.huacainfo.ace.hqgj.vo.CompanyAppealVo;
import com.huacainfo.ace.hqgj.vo.CompanyAppealQVo;
/**
* @author: 豆文国
* @version: 2020-02-21
* @Description:  TODO(企业诉求表)
*/
public interface CompanyAppealService {
/**
*
* @Title:page
* @Description:  TODO(企业诉求表分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<CompanyAppealVo>
    * @throws
    * @author: 豆文国
    * @version: 2020-02-21
    */
    PageDTO
    <CompanyAppealVo> page(CompanyAppealQVo condition, int start, int limit, String orderBy) throws Exception;


    PageDTO
    <CompanyAppealExlVo> exportPage(CompanyAppealQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
        *
        * @Title:insert
        * @Description: TODO(创建企业诉求表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO create(CompanyAppeal o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新企业诉求表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO update(CompanyAppeal o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取企业诉求表)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<CompanyAppeal>
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO<CompanyAppealVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteCompanyAppealById
        * @Description: TODO(删除企业诉求表)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除企业诉求表)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
