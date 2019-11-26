package com.huacainfo.ace.zcpa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.NewPageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.constant.ProjectCategory;
import com.huacainfo.ace.zcpa.dao.ActFamilyVolunteerDao;
import com.huacainfo.ace.zcpa.model.ActFamilyVolunteer;
import com.huacainfo.ace.zcpa.service.ActFamilyVolunteerService;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.ActFamilyVolunteerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

//import com.github.pagehelper.PageHelper;

@Service
/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目志愿者列表 < 限情暖家庭 >)
 */
public class ActFamilyVolunteerServiceImpl implements ActFamilyVolunteerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActFamilyVolunteerDao actFamilyVolunteerDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目志愿者列表 < 限情暖家庭 > 分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActFamilyVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    public NewPageDTO<ActFamilyVolunteerVo> page(ActFamilyVolunteerQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActFamilyVolunteerVo> list = this.actFamilyVolunteerDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目志愿者列表 < 限情暖家庭 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者列表<限情暖家庭>", operationType = "创建", detail = "创建项目志愿者列表<限情暖家庭>")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject object = JSON.parseObject(jsons);
        String projectId = object.getString("projectId");
        String targetId = object.getString("targetId");
        List<ActFamilyVolunteer> actFamilyVolunteer = new ArrayList<ActFamilyVolunteer>(
                JSONArray.parseArray(object.getString("actFamilyVolunteer"), ActFamilyVolunteer.class));

        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(targetId)) {
            return new ResponseDTO(ResultCode.FAIL, "帮扶对象主键不能为空！");
        }
        if (CommonUtils.isBlank(actFamilyVolunteer)) {
            return new ResponseDTO(ResultCode.FAIL, "志愿者不能为空！");
        }

        for (ActFamilyVolunteer o : actFamilyVolunteer) {
            if (CommonUtils.isBlank(o.getAreaCode())) {
                return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空");
            }
            if (CommonUtils.isBlank(o.getVolunteerId())) {
                return new ResponseDTO(ResultCode.FAIL, "志愿者Id不能为空");
            }
            o.setId(GUIDUtil.getGUID());
            o.setProjectId(projectId);
            o.setTargetId(targetId);
            int temp = this.actFamilyVolunteerDao.isExist(o);
            if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "志愿者已经存在！");
            }

            o.setCreateDate(new Date());
            o.setStatus("1");
            o.setAidTargetType("6");
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            actFamilyVolunteerDao.insert(o);
        }

        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目志愿者列表 < 限情暖家庭 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者列表<限情暖家庭>", operationType = "变更", detail = "变更项目志愿者列表<限情暖家庭>")
    public ResponseDTO update(ActFamilyVolunteer o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTargetId())) {
            return new ResponseDTO(ResultCode.FAIL, "被帮扶对象主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new ResponseDTO(ResultCode.FAIL, "地区不能为空！");
        }
        this.actFamilyVolunteerDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目志愿者列表 < 限情暖家庭 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActFamilyVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    public ResponseDTO<ActFamilyVolunteerVo> getById(String id) throws Exception {
        ResponseDTO<ActFamilyVolunteerVo> rst = new ResponseDTO<>();
        rst.setData(this.actFamilyVolunteerDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目志愿者列表 < 限情暖家庭 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Log(operationObj = "项目志愿者列表<限情暖家庭>", operationType = "删除", detail = "删除项目志愿者列表<限情暖家庭>")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actFamilyVolunteerDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目志愿者列表 < 限情暖家庭 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目志愿者列表<限情暖家庭>", operationType = "批量删除", detail = "批量删除项目志愿者列表<限情暖家庭>")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actFamilyVolunteerDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 查看情暖家庭 被帮扶对象的志愿者
     *
     * @param projectId
     * @param targetId
     * @return
     * @throws Exception
     */
    @Override
    public List<ActFamilyVolunteer> selectByProjectId(String projectId, String targetId) throws Exception {
        return actFamilyVolunteerDao.selectByProjectId(projectId, targetId);
    }

    /**
     * 查找志愿者参与的项目
     *
     * @param volunteerId
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO<List> findProjectId(String volunteerId, String state) throws Exception {
        ResponseDTO<List> dto = new ResponseDTO<>();
        dto.setData(actFamilyVolunteerDao.findProjectId(volunteerId, state));
        return dto;
    }

    /**
     * 批量修改
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ResponseDTO batchUpdate(String[] ids) throws Exception {
        this.actFamilyVolunteerDao.batchUpdate(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 获取志愿者服务的被服务对象
     *
     * @param projectId
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO findTarget(String projectId, UserProp userProp, String category) throws Exception {
        ResponseDTO dto = new ResponseDTO();
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(category)) {
            return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
        }
        Map<String, String> map = new HashMap<>();
        if (category.equals(ProjectCategory.CATEGORY_1)) {
            map = this.actFamilyVolunteerDao.findFamilyTarget(projectId, userProp.getUserId());
        } else if (category.equals(ProjectCategory.CATEGORY_2)) {
            map = this.actFamilyVolunteerDao.findProjectTarget(projectId, userProp.getUserId(), category);
        } else {
            map = this.actFamilyVolunteerDao.findProjectVolunteer(projectId, userProp.getUserId(), category);
        }
        dto.setData(map);
        return dto;
    }


}
