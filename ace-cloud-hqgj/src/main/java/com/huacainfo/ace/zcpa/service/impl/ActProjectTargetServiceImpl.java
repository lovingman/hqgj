package com.huacainfo.ace.zcpa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActProjectTargetDao;
import com.huacainfo.ace.zcpa.dao.ActProjectVolunteerDao;
import com.huacainfo.ace.zcpa.model.ActProjectTarget;
import com.huacainfo.ace.zcpa.service.ActProjectTargetService;
import com.huacainfo.ace.zcpa.vo.ActProjectTargetQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectTargetVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.github.pagehelper.PageHelper;

@Service
/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目被帮扶对象列表 < 除情暖家庭外 >)
 */
public class ActProjectTargetServiceImpl implements ActProjectTargetService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActProjectTargetDao actProjectTargetDao;
    @Resource
    private ActProjectVolunteerDao actProjectVolunteerDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目被帮扶对象列表 < 除情暖家庭外 > 分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectTargetVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    public NewPageDTO<ActProjectTargetVo> page(ActProjectTargetQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActProjectTargetVo> list = this.actProjectTargetDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }


    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目被帮扶对象列表 < 除情暖家庭外 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被帮扶对象列表<除情暖家庭外>", operationType = "创建", detail = "创建项目被帮扶对象列表<除情暖家庭外>")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject object = JSON.parseObject(jsons);
        String projectId = object.getString("projectId");
        List<ActProjectTarget> actProjectTarget = new ArrayList<ActProjectTarget>(
                JSONArray.parseArray(object.getString("actProjectTarget"), ActProjectTarget.class));
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(actProjectTarget)) {
            return new ResponseDTO(ResultCode.FAIL, "被服务对象不能为空！");
        }
        if (!CommonUtils.isBlank(actProjectTarget)) {
            for (ActProjectTarget ft : actProjectTarget) {
                if (CommonUtils.isBlank(ft.getTargetId())) {
                    return new ResponseDTO(ResultCode.FAIL, "服务学校Id不能为空");
                }
                if (CommonUtils.isBlank(ft.getAreaCode())) {
                    return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空");
                }
                int temp = this.actProjectTargetDao.isExist(ft);
                if (temp > 0) {
                    return new ResponseDTO(ResultCode.FAIL, "被服务对象已经存在！");
                }
                String actFamilyTargetId = GUIDUtil.getGUID();
                ft.setId(actFamilyTargetId);
                ft.setProjectId(projectId);
                ft.setStatus("1");
                ft.setCreateDate(new Date());
                actProjectTargetDao.insert(ft);
            }
        }

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目被帮扶对象列表 < 除情暖家庭外 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被帮扶对象列表<除情暖家庭外>", operationType = "变更", detail = "变更项目被帮扶对象列表<除情暖家庭外>")
    public ResponseDTO update(ActProjectTarget o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTargetId())) {
            return new ResponseDTO(ResultCode.FAIL, "帮扶对象主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTargetType())) {
            return new ResponseDTO(ResultCode.FAIL, "帮扶对象类型不能为空！");
        }


        this.actProjectTargetDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目被帮扶对象列表 < 除情暖家庭外 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectTarget>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    public ResponseDTO<ActProjectTargetVo> getById(String id) throws Exception {
        ResponseDTO<ActProjectTargetVo> rst = new ResponseDTO<>();
        rst.setData(this.actProjectTargetDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目被帮扶对象列表 < 除情暖家庭外 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Log(operationObj = "项目被帮扶对象列表<除情暖家庭外>", operationType = "删除", detail = "删除项目被帮扶对象列表<除情暖家庭外>")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actProjectTargetDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目被帮扶对象列表 < 除情暖家庭外 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被帮扶对象列表<除情暖家庭外>", operationType = "批量删除", detail = "批量删除项目被帮扶对象列表<除情暖家庭外>")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actProjectTargetDao.deleteByIds(ids);
        this.actProjectVolunteerDao.deleteByTargetId(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 可选志愿者对接区域树
     *
     * @param projectId   项目编号
     * @param projectType 项目类型; 枚举值,可选: family-情暖家庭项目; project-除情暖家庭外的其他项目
     * @return ListDTO<Tree>
     */
    @Override
    public ListDTO<Tree> getAreaTree(String projectId, String projectType) {
        List<Map<String, Object>> list;
        if ("family".equals(projectType)) {
            list = actProjectTargetDao.getFamilyAreaTree(projectId);
        } else if ("project".equals(projectType)) {
            list = actProjectTargetDao.getProjectAreaTree(projectId);
        } else {
            list = null;
        }
        if (CollectionUtils.isEmpty(list)) {
            return new ListDTO<>();
        }

        return parseAreaTree("4307", "", list);
    }

    private ListDTO<Tree> parseAreaTree(String pid, String hasSelf, List<Map<String, Object>> list) {
        CommonTreeUtils tool = new CommonTreeUtils(list);
        ListDTO<Tree> rst = new ListDTO();
        if (StringUtils.isEmpty(hasSelf)) {
            rst.setData(tool.getTreeList(pid));
        } else {
            rst.setData(tool.getTreeListCaseSelf(pid));
        }

        return rst;
    }


}
