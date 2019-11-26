package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActProjectVolunteer;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVolunteerQVo;

import java.util.List;

/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目志愿者列表 < 除情暖家庭外 >)
 */
public interface ActProjectVolunteerService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(项目志愿者列表 < 除情暖家庭外 > 分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    PageDTO<ActProjectVolunteerVo> page(ActProjectVolunteerQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO create(String jsons, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO update(ActProjectVolunteer o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO<ActProjectVolunteerVo> getById(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteActProjectVolunteerById
     * @Description: TODO(删除项目志愿者列表 < 除情暖家庭外 >)
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
     * @Description: TODO(批量删除项目志愿者列表 < 除情暖家庭外 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 查看项目关联的志愿者
     *
     * @param projectId
     * @return
     */
    List<ActProjectVolunteerVo> selectByProjectId(String projectId, String targetId) throws Exception;

}
