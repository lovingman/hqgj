package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActProject;
import com.huacainfo.ace.zcpa.vo.ActProjectQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import com.huacainfo.ace.zcpa.vo.TargetVo;


/**
 * @author: 王恩
 * @version: 2019-07-19
 * @Description: TODO(项目资料)
 */
public interface ActProjectService {
    /**
     * @throws
     * @Title:page
     * @Description: TODO(项目资料分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectVo>
     * @author: 王恩
     * @version: 2019-07-19
     */
    PageDTO<ActProjectVo> page(ActProjectQVo condition, int start, int limit, String orderBy) throws Exception;
    /**
     * @throws
     * @Title:insert
     * @Description: TODO(创建项目资料)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    ResponseDTO create(ActProject o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目资料)
     * @param: @param obj
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    ResponseDTO update(ActProject o, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProject>
     * @author: 王恩
     * @version: 2019-07-19
     */
    ResponseDTO<ActProjectVo> getById(String id,String category) throws Exception;

    /**
     * 获取项目详情
     * @param id
     * @return
     * @throws Exception
     */
    ResponseDTO<ActProjectVo> getByProjectId(String id,String volunteerId) throws Exception;



    /**
     * @throws
     * @Title:deleteActProjectById
     * @Description: TODO(删除项目资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    ResponseDTO deleteById(String id) throws Exception;


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目资料)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 修改项目状态（审核）
     * @param id
     * @param projectState
     * @return
     * @throws Exception
     */
    ResponseDTO updateProjectState(String id,String projectState)throws Exception;

    /**
     * pc 情暖家庭对接, 被服务对象列表. 包含志愿者
     * @param projectId
     * @param category
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    PageDTO<TargetVo> findVolunteerList(String projectId, String category,String areaCode, int start, int limit, String orderBy);

    /**
     * 更新项目封面图片
     *
     * @param projectId 项目ID
     * @param coverUrl  封面图片-base64字符串
     * @return ResponseDTO
     */
    ResponseDTO updateCoverUrl(String projectId, String coverUrl, UserProp curUserProp);

    /**
     * 已对接志愿者--情暖家庭
     * @param projectId
     * @param areaCode
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    PageDTO<TargetVo> findVolunteerDockedList ( String projectId, String areaCode, int start, int limit, String orderBy);

}
