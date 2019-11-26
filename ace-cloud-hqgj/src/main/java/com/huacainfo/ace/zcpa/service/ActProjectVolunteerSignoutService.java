package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteerSignout;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerSignoutVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerSignoutQVo;

/**
 * @author: heshuang
 * @version: 2019-08-22
 * @Description: TODO(项目志愿者退出列表)
 */
public interface ActProjectVolunteerSignoutService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(项目志愿者退出列表分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectVolunteerSignoutVo>
     * @author: heshuang
     * @version: 2019-08-22
     */
    NewPageDTO
            <ActProjectVolunteerSignoutVo> page(ActProjectVolunteerSignoutQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建项目志愿者退出列表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    ResponseDTO create(ActProjectVolunteerSignout o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目志愿者退出列表)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    ResponseDTO update(ActProjectVolunteerSignout o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目志愿者退出列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectVolunteerSignout>
     * @author: heshuang
     * @version: 2019-08-22
     */
    ResponseDTO<ActProjectVolunteerSignoutVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActProjectVolunteerSignoutById
     * @Description: TODO(删除项目志愿者退出列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目志愿者退出列表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(删除项目志愿者)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-08-22
     */
    ResponseDTO deleteVolunteer(String projectId, String volunteerId );

}
