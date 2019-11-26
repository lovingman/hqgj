package com.huacainfo.ace.zcpa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.constant.ProjectCategory;
import com.huacainfo.ace.zcpa.dao.*;
import com.huacainfo.ace.zcpa.model.ActProject;

import com.huacainfo.ace.zcpa.service.ActProjectService;
import com.huacainfo.ace.zcpa.vo.ActProjectQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import com.huacainfo.ace.zcpa.vo.TargetVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
/**
 * @author: 王恩
 * @version: 2019-07-19
 * @Description: TODO(项目资料)
 */
public class ActProjectServiceImpl implements ActProjectService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActProjectDao actProjectDao;
    @Resource
    private ActFamilyVolunteerDao actFamilyVolunteerDao;
    @Resource
    private ActFamilyTargetDao actFamilyTargetDao;
    @Resource
    private ActProjectTargetDao actProjectTargetDao;
    @Resource
    private ActProjectVolunteerDao actProjectVolunteerDao;
    @Resource
    private ActProjectTopicDao actProjectTopicDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
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
    @Override
    public PageDTO<ActProjectVo> page(ActProjectQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ActProjectVo> rst = new PageDTO<>();
        if (!CommonUtils.isBlank(condition.getEndTime())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTime = sdf.format(condition.getEndTime()) + " 23:59:59 ";
            condition.setEndTime(sdf2.parse(endTime));
        }
        if(!CommonUtils.isBlank(condition.getProjectState())){
            condition.setStates(condition.getProjectState().split(","));
        }

        if(!CommonUtils.isBlank(condition.getAreaCode())){
            if(condition.getAreaCode().length()>=6){
                condition.setAreaCode(condition.getAreaCode().substring(0,6));
            }
        }
        List<ActProjectVo> list = this.actProjectDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actProjectDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目资料)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @Override
    @Transactional
    @Log(operationObj = "项目资料", operationType = "创建", detail = "创建项目资料")
    public ResponseDTO create(ActProject o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getProjectName())) {
            return new ResponseDTO(ResultCode.FAIL, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectLevel())) {
            return new ResponseDTO(ResultCode.FAIL, "项目级别不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new ResponseDTO(ResultCode.FAIL, "项目区域不能为空！");
        }

        o.setId(GUIDUtil.getGUID());
        if (o.getProjectLevel().equals("0")) { //Level为0时是母项目,项目状态为进行中
            o.setProjectState("2");
            int temp = this.actProjectDao.isExist(o);
            if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "该母项目类型已经存在！");
            }
        } else if(o.getProjectLevel().equals("1")) {//Level为1时是子项目,项目状态为待审核
            if (CommonUtils.isBlank(o.getPid())) {
                return new ResponseDTO(ResultCode.FAIL, "pid不能为空！");
            }
            o.setProjectLevel("1");
            o.setProjectState("1");
            o.setPid(o.getPid());
        }

        StringBuffer key = new StringBuffer();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        o.setCreateDate(date);
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        switch (o.getCategory()) {
            case ProjectCategory.CATEGORY_1:
                key.append("QNJT");
                key.append(sdf.format(date));
                break;
            case ProjectCategory.CATEGORY_2:
                key.append("HAXY");
                key.append(sdf.format(date));
                break;
            case ProjectCategory.CATEGORY_3:
                key.append("HSL");
                key.append(sdf.format(date));
                break;
            case ProjectCategory.CATEGORY_4:
                key.append("FAYL");
                key.append(sdf.format(date));
                break;
            case ProjectCategory.CATEGORY_5:
                key.append("XFGC");
                key.append(sdf.format(date));
                break;
            case ProjectCategory.CATEGORY_6:
                key.append("XLSH");
                key.append(sdf.format(date));
                break;
            default:
                System.out.println("未知类型");
        }
        o.setProjectCode(key.toString());
        int i= this.actProjectDao.insert(o);
        if(i<1){
            return new ResponseDTO(ResultCode.FAIL, "失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");

    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目资料)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @Override
    @Transactional
    @Log(operationObj = "项目资料", operationType = "变更", detail = "变更项目资料")
    public ResponseDTO update(ActProject o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectName())) {
            return new ResponseDTO(ResultCode.FAIL, "项目名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(!CommonUtils.isBlank(o.getEndTime())){
            boolean flag = false;
            Date nowDate = new Date();
            String date=sdf.format(nowDate);
            //调用Date里面的before方法来做判断
            flag = o.getEndTime().before(sdf.parse(date));
            if (flag) {
                o.setProjectState("4");
            }
        }
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        //此处不更新封面图片,有单独接口提供修改
        int i=this.actProjectDao.updateByPrimaryKey(o);
        if(i<1){
            return new ResponseDTO(ResultCode.FAIL, "失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");

    }

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
    @Override
    @Transactional
    public ResponseDTO<ActProjectVo> getById(String id, String category) throws Exception {
        ResponseDTO<ActProjectVo> rst = new ResponseDTO<>();
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "项目活动id不能为空！");
        }
        if (CommonUtils.isBlank(category)) {
            return new ResponseDTO(ResultCode.FAIL, "类型不能为空！");
        }
        switch (category) {
            case ProjectCategory.CATEGORY_1:
                rst.setData(this.actProjectDao.selectById(id));
                break;
            case ProjectCategory.CATEGORY_2:
                rst.setData(this.actProjectDao.selectSchoolById(id));
                break;
            case ProjectCategory.CATEGORY_3:
            case ProjectCategory.CATEGORY_4:
            case ProjectCategory.CATEGORY_5:
            case ProjectCategory.CATEGORY_6:
                rst.setData(this.actProjectDao.selectOtherById(id, category));
                break;
            default:
                System.out.println("未知类型");
        }
        return rst;
    }

    /**
     * 获取项目详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO<ActProjectVo> getByProjectId(String id,String volunteerId) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        ResponseDTO<ActProjectVo> rst = new ResponseDTO<>();
        rst.setData(actProjectDao.selectVoByPrimaryKey(id,volunteerId));
        return rst;
    }


    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @Override
    @Log(operationObj = "项目资料", operationType = "删除", detail = "删除项目资料")
    public ResponseDTO deleteById(String id) throws Exception {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
       int temp =actProjectDao.isdelete(id);
        if(temp>0){
            return new ResponseDTO(ResultCode.FAIL, "该母项目下还有未结束的子项目，不能删除！");
        }
        this.actProjectDao.deleteByPrimaryKey(id);
        actProjectTargetDao.deleteByProjectId(id);
        actProjectVolunteerDao.deleteByProjectId(id);
        actFamilyTargetDao.deleteByProjectId(id);
        actFamilyVolunteerDao.deleteByProjectId(id);
        actProjectTopicDao.deleteByProjectId(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

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
    @Override
    @Transactional
    @Log(operationObj = "项目资料", operationType = "批量删除", detail = "批量删除项目资料")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
       int i= this.actProjectDao.deleteByIds(ids);
        if(i<1){
            return new ResponseDTO(ResultCode.FAIL, "失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");

    }

    /**
     * 修改项目状态
     *
     * @param id
     * @param projectState
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO updateProjectState(String id, String projectState) throws Exception {
        if (CommonUtils.isBlank(id) || CommonUtils.isBlank(projectState))  {
            return new ResponseDTO(ResultCode.FAIL, "缺少参数！");
        }
        int i= this.actProjectDao.updateProjectState(id, projectState);
        if(i<1){
            return new ResponseDTO(ResultCode.FAIL, "失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");

    }


    /**
     * pc 情暖家庭对接, 被服务对象列表. 包含志愿者
     *
     * @param projectId
     * @param category  1 为情暖家庭 2为校园
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    @Override
    public PageDTO<TargetVo> findVolunteerList(String projectId, String category,String areaCode, int start, int limit, String orderBy) {
        PageDTO<TargetVo> rst = new PageDTO<>();
        List<TargetVo> list = this.actProjectDao.findVolunteerList(projectId, category,areaCode, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actProjectDao.findVolunteerCount(projectId, category,areaCode);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * 更新项目封面图片
     *
     * @param projectId 项目ID
     * @param coverUrl  封面图片-base64字符串
     * @param userProp  当前登录用户
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO updateCoverUrl(String projectId, String coverUrl, UserProp userProp) {
        int i = actProjectDao.updateCoverUrl(projectId, coverUrl);
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "数据更新失败");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "数据更新成功", coverUrl);
    }

    /**
     * 已对接志愿者满4个--情暖家庭
     * @param projectId 项目主键
     * @param areaCode 区域
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    @Override
    public PageDTO<TargetVo> findVolunteerDockedList(String projectId, String areaCode, int start, int limit, String orderBy) {

        PageDTO<TargetVo> rst = new PageDTO<>();
        List<TargetVo> list = this.actProjectDao.findVolunteerDockedList(projectId, areaCode, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actProjectDao.findVolunteerDockedCount(projectId, areaCode);
            rst.setTotal(allRows);
        }
        return rst;
    }


}
