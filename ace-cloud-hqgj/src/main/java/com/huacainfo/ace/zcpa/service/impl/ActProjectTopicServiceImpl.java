package com.huacainfo.ace.zcpa.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.zcpa.dao.ActProjectTopicDao;
import com.huacainfo.ace.zcpa.dao.RegisterDao;
import com.huacainfo.ace.zcpa.dao.WechatUserDao;
import com.huacainfo.ace.zcpa.model.ActProjectTopic;
import com.huacainfo.ace.zcpa.model.WechatUser;
import com.huacainfo.ace.zcpa.service.ActProjectTopicService;
import com.huacainfo.ace.zcpa.service.WechatTemplateMsgService;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectTopicVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
/**
 * @author: 豆文国
 * @version: 2019-07-29
 * @Description: TODO(项目活动主题)
 */
public class ActProjectTopicServiceImpl implements ActProjectTopicService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ActProjectTopicDao actProjectTopicDao;
    @Resource
    private WechatTemplateMsgService wechatTemplateMsgService;
    @Resource
    private WechatUserDao wechatUserDao;
    @Resource
    private RegisterDao registerDao;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(项目活动主题分页查询)
     * @param: @param condition
     * @param: @param pageNum     页码
     * @param: @param pageSize    页数
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ActProjectTopicVo>
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @Override
    public PageDTO<ActProjectTopicVo> page(ActProjectTopicQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<ActProjectTopicVo> rst = new PageDTO<>();
        List<ActProjectTopicVo> list = this.actProjectTopicDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.actProjectTopicDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建项目活动主题)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动主题", operationType = "创建", detail = "创建项目活动主题")
    public ResponseDTO create(ActProjectTopic o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getProjectId()))  {
            return new ResponseDTO(ResultCode.FAIL, "项目Id不能为空！");
        }
        if (CommonUtils.isBlank(o.getProjectCategory()))  {
            return new ResponseDTO(ResultCode.FAIL, "项目类型不能为空！");
        }
        if (!CommonUtils.isBlank(o.getProjectCategory())&&o.getProjectCategory().equals("2")) {
            if (CommonUtils.isBlank(o.getTargetId())) {
                return new PageDTO(ResultCode.FAIL, "targetId不能为空！");
            }
        }
        String[] targetId=o.getTargetId()==null?null:o.getTargetId().split(",");
        int len=targetId==null?1:targetId.length;
        Map<String, Object> rst = registerDao.getAreaCode(userProp.getUserId(),"zcpa");
        String userAreaCode=rst.get("areaCode").toString();
        if (CommonUtils.isBlank(userAreaCode))  {
            return new ResponseDTO(ResultCode.FAIL, "参数错误！");
        }
        for(int i=0;i<len;i++) {
            int num =actProjectTopicDao.volunteerCount(o.getProjectCategory(),o.getProjectId(),targetId==null?null:targetId[i],userAreaCode);
            o.setId(GUIDUtil.getGUID());
            o.setStatus("1");
            o.setCreateDate(new Date());
            o.setCreateUserName(userProp.getName());
            o.setCreateUserId(userProp.getUserId());
            o.setAcceptState("0");
            o.setTargetId(targetId==null?null:targetId[i]);
            o.setVolunteerNum(num);
           ResponseDTO pto = notice(o,userAreaCode);
            if (pto.getStatus() == ResultCode.FAIL) {
                o.setStatus("2");
            }
            this.actProjectTopicDao.insert(o);
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新项目活动主题)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动主题", operationType = "变更", detail = "变更项目活动主题")
    public ResponseDTO update(ActProjectTopic o, UserProp userProp) throws Exception {

        this.actProjectTopicDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动主题)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProjectTopic>
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @Override
    @Transactional
    public ResponseDTO<ActProjectTopicVo> getById(String id) throws Exception {
        ResponseDTO<ActProjectTopicVo> rst = new ResponseDTO<>();
        rst.setData(this.actProjectTopicDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除项目活动主题)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @Override
    @Log(operationObj = "项目活动主题", operationType = "删除", detail = "删除项目活动主题")
    public ResponseDTO deleteById(String id) throws Exception {
        this.actProjectTopicDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动主题)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-07-29
     */
    @Override
    @Transactional
    @Log(operationObj = "项目活动主题", operationType = "批量删除", detail = "批量删除项目活动主题")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.actProjectTopicDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }


    /**
     * 获取主题推送的志愿者列表
     * @param
     * @param
     * @param
     * @param orderBy
     * @return
     * @throws Exception
     */
    @Override
    public PageDTO<BaseVolunteerVo> topicVolunteerList(String projectCategory, String projectId, String targetId, String areaCode, int start, int limit, String orderBy) throws Exception {
        if (CommonUtils.isBlank(projectId))  {
            return new PageDTO(ResultCode.FAIL, "项目Id不能为空！");
        }
        if (CommonUtils.isBlank(projectCategory)) {
            return new PageDTO(ResultCode.FAIL, "项目类型不能为空！");
        }
        if (!CommonUtils.isBlank(projectCategory)&&projectCategory.equals("2")) {
            if (CommonUtils.isBlank(targetId)) {
                return new PageDTO(ResultCode.FAIL, "targetId不能为空！");
            }
        }
        PageDTO<BaseVolunteerVo> rst = new PageDTO<>();
        List<BaseVolunteerVo> list = this.actProjectTopicDao.topicVolunteerList(projectCategory,projectId,targetId,areaCode, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows =actProjectTopicDao.volunteerCount(projectCategory,projectId,targetId,areaCode);
            rst.setTotal(allRows);
        }
        return rst;
    }


    private ResponseDTO notice(ActProjectTopic o,String areaCode){
       List<WechatUser> openids= wechatUserDao.openIdList(o.getProjectId(),o.getTargetId(),areaCode);
         if(!CommonUtils.isBlank(openids)||openids.size()>0) {
             for (int i = 0; i < openids.size(); i++) {
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                 String startTime = sdf.format(o.getStartTime());
                 String endTime = sdf.format(o.getEndTime());
                 String openid = openids.get(i).getOpenid();
                 String first = openids.get(i).getNickname() + "志愿者，您参与的众创平安志愿服务项目现开展活动，详情如下：";
                 String remark = "望您积极参与，并按时到达活动地点，谢谢！";
                 String linkUri = "";
                 String topic = o.getTitle();
                 String timeStr = startTime + "至" + endTime;
                 String contact = o.getContactName() + "-" + o.getContactTel();
                 String address = o.getAreaCode();
                 String detail = o.getContent();
                 try {
                     wechatTemplateMsgService.notice(openid, first, remark, linkUri, topic, timeStr, contact, address, detail);
                 }catch (Exception e){
                     return new ResponseDTO(ResultCode.FAIL, "推送异常！");
                 }

             }
         }
             return new ResponseDTO(ResultCode.SUCCESS, "成功！");
         }

}
