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
import com.huacainfo.ace.zcpa.dao.ActFamilyTargetDao;
import com.huacainfo.ace.zcpa.dao.ActFamilyVolunteerDao;
import com.huacainfo.ace.zcpa.model.ActFamilyTarget;
import com.huacainfo.ace.zcpa.model.ActFamilyVolunteer;
import com.huacainfo.ace.zcpa.service.ActFamilyTargetService;
import com.huacainfo.ace.zcpa.vo.ActFamilyTargetQVo;
import com.huacainfo.ace.zcpa.vo.ActFamilyTargetVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.github.pagehelper.PageHelper;

@Service
/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(项目被帮扶对象列表 < 限情暖家庭 >)
 */
public class ActFamilyTargetServiceImpl implements ActFamilyTargetService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActFamilyTargetDao actFamilyTargetDao;
    @Resource
    private ActFamilyVolunteerDao actFamilyVolunteerDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目被帮扶对象列表 < 限情暖家庭 > 分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActFamilyTargetVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    public NewPageDTO<ActFamilyTargetVo> page(ActFamilyTargetQVo condition, int pageNum, int pageSize, String orderBy) throws Exception {
//        PageHelper.startPage(pageNum, pageSize);

        List<ActFamilyTargetVo> list = this.actFamilyTargetDao.findList(condition, pageNum, pageSize, orderBy);

        return new NewPageDTO<>(list);
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被帮扶对象列表<限情暖家庭>", operationType = "创建", detail = "创建项目被帮扶对象列表<限情暖家庭>")
    public ResponseDTO create(String jsons, UserProp userProp) throws Exception {
        JSONObject object = JSON.parseObject(jsons);
        String projectId = object.getString("projectId");
        String areaCode = object.getString("areaCode");
        List<ActFamilyTarget> actFamilyTarget = new ArrayList<ActFamilyTarget>(
                JSONArray.parseArray(object.getString("actFamilyTarget"), ActFamilyTarget.class));
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(actFamilyTarget)) {
            return new ResponseDTO(ResultCode.FAIL, "被帮扶对象不能为空！");
        }
        if (CommonUtils.isBlank(areaCode)) {
            return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空");
        }
        if (!CommonUtils.isBlank(actFamilyTarget)) {
            for (ActFamilyTarget ft : actFamilyTarget) {
                if (CommonUtils.isBlank(ft.getPersonId())) {
                    return new ResponseDTO(ResultCode.FAIL, "被帮扶对象Id不能为空");
                }

                int temp = this.actFamilyTargetDao.isExist(ft);
                if (temp > 0) {
                    return new ResponseDTO(ResultCode.FAIL, "被服务对象已经存在！");
                }
                String actFamilyTargetId = GUIDUtil.getGUID();
                ft.setId(actFamilyTargetId);
                ft.setAreaCode(ft.getAreaCode());
                ft.setProjectId(projectId);
                ft.setStatus("1");
                ft.setCreateDate(new Date());
                ft.setCreateUserId(userProp.getUserId());
                ft.setCreateUserName(userProp.getName());
                actFamilyTargetDao.insert(ft);
                for (int i = 0; i < 4; i++) {
                    ActFamilyVolunteer o = new ActFamilyVolunteer();
                    o.setId(GUIDUtil.getGUID());
                    o.setAreaCode(areaCode);
                    o.setProjectId(projectId);
                    o.setTargetId(actFamilyTargetId);
                    o.setCreateDate(new Date());
                    o.setAidTargetType("6");
                    o.setStatus("1");
                    o.setCreateUserName(userProp.getName());
                    o.setCreateUserId(userProp.getUserId());
                    actFamilyVolunteerDao.insert(o);
                }
            }
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被帮扶对象列表<限情暖家庭>", operationType = "变更", detail = "变更项目被帮扶对象列表<限情暖家庭>")
    public ResponseDTO update(ActFamilyTarget o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPersonId())) {
            return new ResponseDTO(ResultCode.FAIL, "被帮扶对象主键不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.actFamilyTargetDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActFamilyTarget>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    public ResponseDTO<ActFamilyTargetVo> getById(String id, String category) throws Exception {
        ResponseDTO<ActFamilyTargetVo> rst = new ResponseDTO<>();
        // rst.setData(this.actFamilyTargetDao.selectVoByPrimaryKey(id));
        rst.setData(this.actFamilyTargetDao.selectById(id, category));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Log(operationObj = "项目被帮扶对象列表<限情暖家庭>", operationType = "删除", detail = "删除项目被帮扶对象列表<限情暖家庭>")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actFamilyTargetDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目被帮扶对象列表 < 限情暖家庭 >)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @Override
    @Transactional
    @Log(operationObj = "项目被帮扶对象列表<限情暖家庭>", operationType = "批量删除", detail = "批量删除项目被帮扶对象列表<限情暖家庭>")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actFamilyTargetDao.deleteByIds(ids);
        actFamilyVolunteerDao.deleteByTargetId(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


}
