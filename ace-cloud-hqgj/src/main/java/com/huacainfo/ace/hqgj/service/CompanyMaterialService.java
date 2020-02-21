package com.huacainfo.ace.hqgj.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.CompanyMaterial;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialVo;
import com.huacainfo.ace.hqgj.vo.CompanyMaterialQVo;
/**
* @author: 豆文国
* @version: 2020-02-21
* @Description:  TODO(企业物资表)
*/
public interface CompanyMaterialService {
/**
*
* @Title:page
* @Description:  TODO(企业物资表分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<CompanyMaterialVo>
    * @throws
    * @author: 豆文国
    * @version: 2020-02-21
    */
    PageDTO
    <CompanyMaterialVo> page(CompanyMaterialQVo condition, int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建企业物资表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO create(CompanyMaterial o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新企业物资表)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO update(CompanyMaterial o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取企业物资表)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<CompanyMaterial>
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO<CompanyMaterialVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteCompanyMaterialById
        * @Description: TODO(删除企业物资表)
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
        * @Description: TODO(批量删除企业物资表)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: 豆文国
        * @version: 2020-02-21
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

}
