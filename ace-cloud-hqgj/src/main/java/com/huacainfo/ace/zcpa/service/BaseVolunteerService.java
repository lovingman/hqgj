package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.BaseVolunteer;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerQVo;

/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(群众 / 志愿者)
 */
public interface BaseVolunteerService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(群众 / 志愿者分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    PageDTO<BaseVolunteerVo> page(BaseVolunteerQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建群众 / 志愿者)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO create(BaseVolunteer o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新群众 / 志愿者)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO update(BaseVolunteer o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取群众 / 志愿者)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO<BaseVolunteerVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBaseVolunteerById
     * @Description: TODO(删除群众 / 志愿者)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除群众 / 志愿者)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

}
