package com.huacainfo.ace.hqgj.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberQVo;

/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(企业成员表)
 */
public interface BaseCompanyMemberService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(企业成员表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseCompanyMemberVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    PageDTO
            <BaseCompanyMemberVo> page(BaseCompanyMemberQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建企业成员表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO create(BaseCompanyMember o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新企业成员表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO update(BaseCompanyMember o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业成员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseCompanyMember>
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO<BaseCompanyMemberVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBaseCompanyMemberById
     * @Description: TODO(删除企业成员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业成员表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
