package com.huacainfo.ace.zcpa.service;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActFamilyVolunteer;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerVo;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerQVo;

import java.util.List;

/**
* @author: heshuang
* @version: 2019-07-26
* @Description:  TODO(项目志愿者列表<限情暖家庭>)
*/
public interface ActFamilyVolunteerService {
/**
*
* @Title:page
* @Description:  TODO(项目志愿者列表<限情暖家庭>分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ActFamilyVolunteerVo>
    * @throws
    * @author: heshuang
    * @version: 2019-07-26
    */
    NewPageDTO
    <ActFamilyVolunteerVo> page(ActFamilyVolunteerQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建项目志愿者列表<限情暖家庭>)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO create(String jsons, UserProp userProp) throws Exception;

        /**
        *
        * @Title:update
        * @Description: TODO(更新项目志愿者列表<限情暖家庭>)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO update(ActFamilyVolunteer o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取项目志愿者列表<限情暖家庭>)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ActFamilyVolunteer>
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO<ActFamilyVolunteerVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteActFamilyVolunteerById
        * @Description: TODO(删除项目志愿者列表<限情暖家庭>)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO deleteById(String id) throws Exception;



        /**
        *
        * @Title:deleteByIds
        * @Description: TODO(批量删除项目志愿者列表<限情暖家庭>)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;


    /**
     * 查看被服务对象关联的志愿者
     * @param projectId
     * @param targetId
     * @return
     */
      List<ActFamilyVolunteer> selectByProjectId( String projectId, String targetId)throws Exception;

    /**
     * 查找志愿者参与的项目
     * @param volunteerId
     * @return
     * @throws Exception
     */
    ResponseDTO<List> findProjectId(String volunteerId,String state)throws Exception;

    /**
     * 批量修改
     * @param ids
     * @return
     */
    ResponseDTO batchUpdate(String[] ids) throws Exception;

    ResponseDTO findTarget(String projectId,UserProp userProp,String category)throws Exception;

}
