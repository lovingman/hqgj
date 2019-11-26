package com.huacainfo.ace.zcpa.service;

import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.model.ActProjectTarget;
import com.huacainfo.ace.zcpa.vo.ActProjectTargetQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectTargetVo;
/**
* @author: heshuang
* @version: 2019-07-26
* @Description:  TODO(项目被帮扶对象列表<除情暖家庭外>)
*/
public interface ActProjectTargetService {
/**
*
* @Title:page
* @Description:  TODO(项目被帮扶对象列表<除情暖家庭外>分页查询)
* @param:        @param condition
* @param:        @param pageNum     页码
* @param:        @param pageSize    页数
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       NewPageDTO
<ActProjectTargetVo>
    * @throws
    * @author: heshuang
    * @version: 2019-07-26
    */
    NewPageDTO
    <ActProjectTargetVo> page(ActProjectTargetQVo condition, int pageNum, int pageSize, String orderBy) throws Exception;

        /**
        *
        * @Title:insert
        * @Description: TODO(创建项目被帮扶对象列表<除情暖家庭外>)
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
        * @Description: TODO(更新项目被帮扶对象列表<除情暖家庭外>)
        * @param: @param obj
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO update(ActProjectTarget o, UserProp userProp) throws Exception;

        /**
        *
        * @Title:getById
        * @Description: TODO(获取项目被帮扶对象列表<除情暖家庭外>)
        * @param: @param id
        * @param: @throws Exception
        * @return: ResponseDTO<ActProjectTarget>
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO<ActProjectTargetVo> getById(String id) throws Exception;

        /**
        *
        * @Title:deleteActProjectTargetById
        * @Description: TODO(删除项目被帮扶对象列表<除情暖家庭外>)
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
        * @Description: TODO(批量删除项目被帮扶对象列表<除情暖家庭外>)
        * @param: @param ids
        * @param: @throws Exception
        * @return: ResponseDTO
        * @throws
        * @author: heshuang
        * @version: 2019-07-26
        */
        ResponseDTO deleteByIds(String[] ids) throws Exception;

    /**
     * 可选志愿者对接区域树
     *
     * @param projectId   项目编号
     * @param projectType 项目类型; 枚举值,可选: family-情暖家庭项目; other-除情暖家庭外的其他项目
     * @return ListDTO<Tree>
     */
    ListDTO<Tree> getAreaTree(String projectId, String projectType);
}
