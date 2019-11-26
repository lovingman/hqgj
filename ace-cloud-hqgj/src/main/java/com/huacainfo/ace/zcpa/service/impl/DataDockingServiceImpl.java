package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.zcpa.dao.BaseVolunteerDao;
import com.huacainfo.ace.zcpa.dao.DataDockingDao;
import com.huacainfo.ace.zcpa.dataVo.*;
import com.huacainfo.ace.zcpa.model.BaseVolunteer;
import com.huacainfo.ace.zcpa.oracleDao.ActivityFileDao;
import com.huacainfo.ace.zcpa.oracleDao.ActivitysDao;
import com.huacainfo.ace.zcpa.oracleDao.VolunteersDao;
import com.huacainfo.ace.zcpa.oracleModel.ActivityFile;
import com.huacainfo.ace.zcpa.oracleModel.Activitys;
import com.huacainfo.ace.zcpa.oracleModel.Volunteers;
import com.huacainfo.ace.zcpa.service.DataDockingService;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerQVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class DataDockingServiceImpl implements DataDockingService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DataDockingDao dataDockingDao;
    @Resource
    private ActivityFileDao activityFileDao;
    @Resource
    private ActivitysDao activitysDao;
    @Resource
    private BaseVolunteerDao baseVolunteerDao;
    @Resource
    private VolunteersDao volunteersDao;
    /**
     * 统计平安志愿者数量
     * @param condition 开始时间, 结束时间
     * @param
     * @return
     */
    @Override
    public ResponseDTO findVolunteerNum(DataDockingQVo condition)throws Exception{
        ResponseDTO dto = new ResponseDTO();
        try {
             DataDockingQVo qvo= queryCriteria(condition);
             List<AreaCodeNumVo> volunteerNum = dataDockingDao.findVolunteerNum(qvo);
             dto.setData(volunteerNum);
        }catch (Exception e){
            e.getMessage();
          return  new ResponseDTO(ResultCode.FAIL, "系统异常！",null);
        }
        return  dto;
    }

    /**
     * 项目活动数量
     * @param condition
     * @return
     * @throws Exception
     */
    @Override
    public ResponseDTO findProjectNum(DataDockingQVo condition) throws Exception {
        ResponseDTO dto = new ResponseDTO();
        try {
            DataDockingQVo qvo= queryCriteria(condition);
            List<AreaCodeNumVo> volunteerNum = dataDockingDao.findProjectNum(qvo);
            dto.setData(volunteerNum);
        }catch (Exception e){
            e.getMessage();
            return  new ResponseDTO(ResultCode.FAIL, "系统异常！",null);
        }
        return  dto;
    }

    /**
     * 情暖家庭志愿服务的特殊人群名单
     * @param condition
     * @return
     */
    @Override
    public ResponseDTO<FamilyTargetVo> findFamilyTargetList(DataDockingQVo condition) {
        ResponseDTO dto = new ResponseDTO();
        try {
            DataDockingQVo qvo= queryCriteria(condition);
            List<FamilyTargetVo> list=dataDockingDao.findFamilyTargetList(qvo);
            dto.setData(list);
        }catch (Exception e){
            e.getMessage();
            return  new ResponseDTO(ResultCode.FAIL, "系统异常！",null);
        }
        return dto;
    }


    /**
     *  特殊人群的平安志愿者名单
     * @param condition
     * @return
     */
    @Override
    public ResponseDTO<FamilyTargetListVo> findFamilyVolunteertList(DataDockingQVo condition) {
        ResponseDTO dto = new ResponseDTO();
        try {
            DataDockingQVo qvo= queryCriteria(condition);
            List<FamilyTargetListVo> list=dataDockingDao.findFamilyVolunteertList(qvo);
            dto.setData(list);
        }catch (Exception e){
            e.getMessage();
            return  new ResponseDTO(ResultCode.FAIL, "系统异常！",null);
        }
        return dto;
    }

    /**
     * 特殊人群的志愿服务数据
     * @param condition
     * @return
     */
    @Override
    @Transactional
    public ResponseDTO findActivityList(DataDockingQVo condition) {
        if(CommonUtils.isBlank(condition)){
            condition =new DataDockingQVo();
        }
        try {
            if(CommonUtils.isBlank(condition.getStartTime())) {
                //获取昨天日期
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                Date d = cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String yesterday = sdf.format(d) + " 07:00:00 ";
                condition.setStartTime(sdf2.parse(yesterday));
            }
            List<ActivityVo> list=dataDockingDao.findActivityList(condition);
            ResponseDTO status=create(list);
            if(status.getStatus()==ResultCode.FAIL){
                return  new ResponseDTO(ResultCode.FAIL, "失败");
            }
        }catch (Exception e){
            e.getMessage();
            return  new ResponseDTO(ResultCode.FAIL, "系统异常！");
        }
        return  new ResponseDTO(ResultCode.SUCCESS, "成功");
    }


    /**
     * 推送志愿者列表
      * @param condition
     * @return
     */
    @Override
    @Transactional
    public ResponseDTO findVolunteerList(BaseVolunteerQVo condition)throws Exception  {
        BaseVolunteerQVo conditions=query(condition);
        Thread thread=null;
        try {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    List<BaseVolunteerVo> list =baseVolunteerDao.findList(conditions,0,50000,null);
                    if(!CommonUtils.isBlank(list) && list.size()>0) {
                        try{
                        for (BaseVolunteer v : list) {
                            int temp =volunteersDao.isExist(v.getId());

                            Volunteers vo = new Volunteers();
                            vo.setId(v.getId());
                            vo.setOrgId(null);
                            vo.setOrginternalCode(v.getAreaCode());
                            vo.setName(v.getName());
                            vo.setMobile(v.getMobile());
                            vo.setIdCard(v.getIdCard());
                            vo.setSocietyId(null);
                            vo.setCreateDate(new Date());
                            if(temp>0){
                                volunteersDao.updateByPrimaryKey(vo);
                            }else {
                                volunteersDao.insert(vo);
                            }
                        }
                    } catch (Exception e) {
                        logger.error("平安志愿者批量推送失败：", e);

                    }
                    }
                }
                });
             thread.start();
            }catch (Exception e){
                logger.error("平安志愿者批量推送失败：", e);
                return new ResponseDTO(ResultCode.FAIL, "平安志愿者批量推送异常");
            }

        return  new ResponseDTO(ResultCode.SUCCESS, "平安志愿者批量推送成功");
    }


    private BaseVolunteerQVo query(BaseVolunteerQVo condition)throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (CommonUtils.isBlank(condition)) {
            condition = new BaseVolunteerQVo();

        }
        if(CommonUtils.isBlank(condition.getStartTime())) {
            condition.setStartTime(new Date());
        }
        String startTimes = format.format(condition.getStartTime()) + " 00:00:00 ";
        condition.setStartTime(sdf2.parse(startTimes));

        if(!CommonUtils.isBlank(condition.getEndTime())) {
            String endTimes = format.format(condition.getEndTime()) + " 23:59:59 ";
            condition.setEndTime(sdf2.parse(endTimes));
        }
        return  condition;
    }



  private   ResponseDTO create(List<ActivityVo> list) {

        if(!CommonUtils.isBlank(list) && list.size()>0) {
            //先清除数据
            String[] assetIds = new String[list.size()];
            int j = 0;
            for (ActivityVo u : list) {
                assetIds[j++] = u.getActivityId();
            }
            //先清除已有的数据
            activitysDao.deleteByIds(assetIds);
            activityFileDao.deleteByIds(assetIds);
            //  activitysDao.deleteActivitys();
            // activityFileDao.deleteFile();

            for (ActivityVo vo : list) {
                Activitys activitys = new Activitys();
                activitys.setId(vo.getActivityId());
                activitys.setOrginternalCode(vo.getAreaCode());
                activitys.setPersonId(vo.getPersonId());
                activitys.setPersonName(vo.getPersonName());
                activitys.setPersonType(vo.getPersonType());
                activitys.setIdCard(vo.getIdCard());
                activitys.setActivityTime(vo.getActivityTime());
                activitys.setAreaName(vo.getAreaName());
                activitys.setName(vo.getName());
                activitys.setVidCard(vo.getVidCard());
                activitys.setContent(vo.getContent());
                activitys.setComplete(vo.getIsComplete());
                activitys.setCreateDate(new Date());
                List<String> urlist = vo.getFileURL();
                activitysDao.insert(activitys);
                if (!CommonUtils.isBlank(urlist) || urlist.size() > 0) {
                    for (int i = 0; i < urlist.size(); i++) {
                        ActivityFile file = new ActivityFile();
                        file.setId(GUIDUtil.getGUID());
                        file.setActivityId(vo.getActivityId());
                        file.setFilePath(urlist.get(i));
                        file.setCreateDate(new Date());
                        activityFileDao.insert(file);
                    }
                }
            }
        }
      return  new ResponseDTO(ResultCode.SUCCESS, "成功");
  }



    private  DataDockingQVo  queryCriteria (DataDockingQVo condition)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(CommonUtils.isBlank(condition)){
            condition =new DataDockingQVo();
        }
        if (CommonUtils.isBlank(condition.getEndTime())) {
            condition.setEndTime(new Date());
        }
        String endTimes = sdf.format(condition.getEndTime()) + " 23:59:59 ";
        condition.setEndTime(sdf2.parse(endTimes));
        if (!CommonUtils.isBlank(condition.getStartTime())) {
            String startTimes = sdf.format(condition.getStartTime()) + " 00:00:00 ";
            condition.setStartTime(sdf2.parse(startTimes));
        }
        return  condition;
    }



}
