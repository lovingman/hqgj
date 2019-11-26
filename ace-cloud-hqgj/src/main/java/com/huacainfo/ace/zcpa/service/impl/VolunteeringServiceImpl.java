package com.huacainfo.ace.zcpa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.constant.ProjectCategory;
import com.huacainfo.ace.zcpa.dao.*;
import com.huacainfo.ace.zcpa.model.*;
import com.huacainfo.ace.zcpa.service.ActProjectVolunteerSignoutService;
import com.huacainfo.ace.zcpa.service.VolunteeringService;
import com.huacainfo.ace.zcpa.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VolunteeringServiceImpl implements VolunteeringService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private VolunteeringDao volunteeringDao;
    @Resource
    private ActFamilyVolunteerDao actFamilyVolunteerDao;
    @Resource
    private ActProjectVolunteerDao actProjectVolunteerDao;
    @Resource
    private ActProjectActivityDao actProjectActivityDao;
    @Resource
    private ActActivityAttachDao actActivityAttachDao;
    @Resource
    private ActProjectVolunteerSignoutService actProjectVolunteerSignoutService;
    @Resource
    private ActProjectDao actProjectDao;

    /**
     * 志愿者所需名额
     *
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO<List<Volunteering>> volunteerQuotaCount(String areaCode, String projectId) throws Exception {
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        ResponseDTO<List<Volunteering>> rst = new ResponseDTO<>();
        rst.setData(volunteeringDao.volunteerQuotaCount(areaCode, projectId));
        return rst;
    }

    /**
     * 志愿者参与对接
     *
     * @param qvo
     * @param userProp http://localhost:9017/volunteering/participationDocking?category=1&targetId=5&areaCode=4307&projectId=2ca5f97efbd146aeb623e55249db28fd
     *                 http://localhost:9017/volunteering/participationDocking?category=2&projectId=1&targetId=2222
     *                 http://localhost:9017/volunteering/participationDocking?category=3&projectId=1
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ResponseDTO participationDocking(VolunteeringQVo qvo, UserProp userProp) throws Exception {
        int temp = volunteeringDao.findState(userProp.getUserId());
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "已经参与其他项目！");
        }
        String category = qvo.getCategory();
        String projectId = qvo.getProjectId();
        String areaCode = qvo.getAreaCode();
        if (CommonUtils.isBlank(category)) {
            return new ResponseDTO(ResultCode.FAIL, "类型不能为空！");
        }
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(areaCode)) {
            return new ResponseDTO(ResultCode.FAIL, "区域值不能为空！");
        }
        switch (category) {
            case ProjectCategory.CATEGORY_1:
                String aidTargetType = qvo.getTargetId();
                if (CommonUtils.isBlank(aidTargetType)) {
                    return new ResponseDTO(ResultCode.FAIL, "志愿者类型不能为空！");
                }
                //查家庭志愿者对接
                List list = volunteeringDao.participationDocking(aidTargetType, qvo.getAreaCode(), projectId);
                if (list.size() > 0) {
                    String id = list.get(0).toString();
                    actFamilyVolunteerDao.updateVolunteerId(userProp.getUserId(), id);
                }
                break;
            case ProjectCategory.CATEGORY_2:
                if (CommonUtils.isBlank(qvo.getTargetId())) {
                    return new ResponseDTO(ResultCode.FAIL, "被帮扶对象主键不能为空！");
                }
                ActProjectVo vo = actProjectDao.selectVoByPrimaryKey(projectId,null);
                if (!CommonUtils.isBlank(vo)) {
                    int num = vo.getNeedNum();
                    if (num == 0) {
                        return new ResponseDTO(ResultCode.FAIL, "志愿者已满！");
                    }
                }
                //新增校园志愿者
                ActProjectVolunteer o = new ActProjectVolunteer();
                o.setId(GUIDUtil.getGUID());
                o.setAreaCode(areaCode);
                o.setProjectId(qvo.getProjectId());
                o.setTargetId(qvo.getTargetId());
                o.setVolunteerId(userProp.getUserId());
                o.setStatus("1");
                o.setCreateDate(new Date());
                actProjectVolunteerDao.insert(o);
                break;
            case ProjectCategory.CATEGORY_3:
            case ProjectCategory.CATEGORY_4:
            case ProjectCategory.CATEGORY_5:
            case ProjectCategory.CATEGORY_6:
                ActProjectVo vov = actProjectDao.selectVoByPrimaryKey(projectId,null);
                if (!CommonUtils.isBlank(vov)) {
                    int num = vov.getNeedNum();
                    if (num == 0) {
                        return new ResponseDTO(ResultCode.FAIL, "志愿者已满！");
                    }
                }
                //新增志愿者
                ActProjectVolunteer v = new ActProjectVolunteer();
                v.setId(GUIDUtil.getGUID());
                v.setAreaCode(areaCode);
                v.setProjectId(projectId);
                v.setVolunteerId(userProp.getUserId());
                v.setStatus("1");
                v.setCreateDate(new Date());
                actProjectVolunteerDao.insert(v);
                break;
            default:
                System.out.println("未知类型");
        }
        actProjectVolunteerSignoutService.deleteVolunteer(projectId, userProp.getUserId());
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 校园已报名志愿者名额
     *
     * @param areaCode
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO<List<VolunteeringVo>> schoolVolunteerQuota(String areaCode, String projectId) throws Exception {
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        ResponseDTO<List<VolunteeringVo>> dto = new ResponseDTO<>();
        dto.setData(volunteeringDao.schoolVolunteerQuota(areaCode, projectId));
        return dto;
    }

    /**
     * 退出志愿服务
     *
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO signOutVolunteer(ActProjectVolunteerSignout o,String category, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getProjectId())) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
        if (CommonUtils.isBlank(category)) {
            return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new ResponseDTO(ResultCode.FAIL, "区域不能为空！");
        }

        String projectId =o.getProjectId();
        o.setVolunteerId(userProp.getUserId());
        ResponseDTO d = actProjectVolunteerSignoutService.create(o, userProp);

        if (d.getStatus() == ResultCode.FAIL) {
            return new ResponseDTO(ResultCode.FAIL, "退出失败！");
        }
        if (category.equals(ProjectCategory.CATEGORY_1)) {
            this.volunteeringDao.wupdateVolunteer(projectId, userProp.getUserId());
        } else {
            this.volunteeringDao.wdeleteVolunteer(projectId, userProp.getUserId());
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 提交志愿服务
     *
     * @param jsons
     * @param userProp
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ResponseDTO submitVoluntaryServices(String jsons, UserProp userProp) throws Exception {
        JSONObject jsonObj = JSON.parseObject(jsons);
        ActProjectActivity o = JSON.parseObject(jsonObj.getString("actProjectActivity"), ActProjectActivity.class);
        List<ActActivityAttach> activitieDetail = new ArrayList<ActActivityAttach>(JSONArray.parseArray(jsonObj.getString("activitieDetail"), ActActivityAttach.class));
        List<ActActivityAttach> activitieAbnormal = new ArrayList<ActActivityAttach>(JSONArray.parseArray(jsonObj.getString("activitieAbnormal"), ActActivityAttach.class));
        String[] keys = {"actProjectActivity"};
        CommonUtils.validateJsonObj(jsonObj, keys);
        if (CommonUtils.isBlank(o.getProjectId())) {
            return new ResponseDTO(ResultCode.FAIL, "项目主键不能为空！");
        }
      /*  if(CommonUtils.isBlank(o.getProjectCategory())){
            return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
        }*/
        if (CommonUtils.isBlank(o.getAreaCode())) {
            return new ResponseDTO(ResultCode.FAIL, "所属辖区不能为空！");
        }
        if (CommonUtils.isBlank(o.getStartTime())) {
            return new ResponseDTO(ResultCode.FAIL, "服务开始时间不能为空！");
        }
        if (userProp == null) {
            return new ResponseDTO(ResultCode.FAIL, "获取登录信息失败");
        }
        ActProjectVo avo = actProjectDao.selectVoByPrimaryKey(o.getProjectId(),null);
        String actId = GUIDUtil.getGUID();
        o.setId(actId);
        o.setProjectCategory(avo.getCategory());
        o.setVolunteerId(userProp.getUserId());
        o.setCreateDate(new Date());
        o.setStatus("3");//待审核
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());

        if (!CommonUtils.isBlank(activitieDetail)) {
            for (ActActivityAttach a : activitieDetail) {
                a.setId(GUIDUtil.getGUID());
                a.setActid(actId);
                a.setType("1");
                a.setStatus("1");
                a.setCreatedate(new Date());
                actActivityAttachDao.insert(a);
            }
        }
        if (!CommonUtils.isBlank(activitieAbnormal)) {
            for (ActActivityAttach a : activitieAbnormal) {
                a.setId(GUIDUtil.getGUID());
                a.setActid(actId);
                a.setType("2");
                a.setStatus("1");
                a.setCreatedate(new Date());
                actActivityAttachDao.insert(a);
            }
        }
        if (!CommonUtils.isBlank(o.getYcDesc())) {
            o.setIsYc("y");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int date = Integer.valueOf(sdf.format(o.getStartTime()));
        int num1 = 17;
        int num2 = 21;
        if (date >= num1 && date <= num2) {
            //19活动积分 每月17号至21号  取项目19活动积分
            o.setActivityJiFen(null == avo.getNineteenJiFen() ? 0 : avo.getNineteenJiFen());
        } else {
            //不是19活动取自定义积分
            o.setActivityJiFen(null == avo.getJiFen() ? 0 : avo.getJiFen());
        }
        int i = actProjectActivityDao.insert(o);
        if (i < 1) {
            return new ResponseDTO(ResultCode.FAIL, "提交失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * 已经提交志愿服务查看详情
     *
     * @param id
     * @return
     */
    @Override
    public ResponseDTO<ActProjectActivityVo> selectByActivityId(String id) {
        if (CommonUtils.isBlank(id)) {
            return new ResponseDTO(ResultCode.FAIL, "主键不能为空！");
        }
        ActProjectActivityVo vo = this.actProjectActivityDao.selectByActivityId(id);
        ActActivityAttachQVo qvo = new ActActivityAttachQVo();
        qvo.setActid(id);
        qvo.setType("1");
        vo.setActivitieDetail(actActivityAttachDao.findList(qvo, 0, 100, null));
        qvo.setType("2");
        vo.setActivitieAbnormal(actActivityAttachDao.findList(qvo, 0, 100, null));
        ResponseDTO<ActProjectActivityVo> dto = new ResponseDTO<>();
        dto.setData(vo);
        return dto;
    }

    /**
     * 项目详情获取志愿者
     *
     * @param projectId 项目id
     * @param category  项目类型
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    @Override
    public PageDTO<BaseVolunteerVo> findVolunteerList(String projectId, String category, int start, int limit, String orderBy) {
        PageDTO<BaseVolunteerVo> rst = new PageDTO<>();

        List<BaseVolunteerVo> list = this.volunteeringDao.findVolunteerList(projectId, category, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.volunteeringDao.findVolunteerCount(projectId, category);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * 我的志愿服务列表
     *
     * @param status  状态
     * @param start
     * @param limit
     * @param orderBy
     * @return
     */
    @Override
    public PageDTO<ActProjectVo> findMyVolunteerServiceList(String volunteerId, String status, int start, int limit, String orderBy) {
        PageDTO<ActProjectVo> rst = new PageDTO<>();
        List<ActProjectVo> list = this.volunteeringDao.findMyVolunteerServiceList(volunteerId, status, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.volunteeringDao.myVolunteerServiceCount(volunteerId, status);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * 获取志愿者足迹数据
     *
     * @param volunteerId 志愿者ID;不传人时获取当前登录者id
     */
    @Override
    public Map<String, Object> getSpoorData(String volunteerId) {
        return volunteeringDao.getSpoorData(volunteerId);
    }


    /**
     * 获取用户头像
     * @param userId  当前用户id
     * @return
     */
    @Override
    public String selectWechatHeadimgurl(String userId) {
        return volunteeringDao.selectWechatHeadimgurl(userId);
    }
}
