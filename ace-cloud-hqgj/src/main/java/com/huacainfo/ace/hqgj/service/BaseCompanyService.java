package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.BaseCompany;
import com.huacainfo.ace.hqgj.vo.BaseCompanyVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyQVo;

import java.util.List;

/**
 * @author: 何双
 * @version: 2019-12-06
 * @Description: TODO(企业管理)
 */
public interface BaseCompanyService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(企业管理分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseCompanyVo>
     * @author: 何双
     * @version: 2019-12-06
     */
    PageDTO<BaseCompanyVo> page(BaseCompanyQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建企业管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    ResponseDTO create(BaseCompany o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企业管理)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    ResponseDTO update(BaseCompany o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseCompany>
     * @author: 何双
     * @version: 2019-12-06
     */
    ResponseDTO<BaseCompanyVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBaseCompanyById
     * @Description: TODO(删除企业管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-06
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 导入
     * @param list
     * @return
     * @throws Exception
     */
    public ResponseDTO importXls(List<Object> list,UserProp userProp) throws Exception;

}
