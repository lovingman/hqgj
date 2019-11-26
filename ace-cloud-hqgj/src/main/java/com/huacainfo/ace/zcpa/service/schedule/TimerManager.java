package com.huacainfo.ace.zcpa.service.schedule;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.zcpa.dao.TimerDao;
import com.huacainfo.ace.zcpa.service.DataDockingService;
import com.huacainfo.ace.zcpa.service.SynchronizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TimerManager
 * @Description TODO
 */
@Service
public class TimerManager {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TimerDao timerDao;
    @Resource
    private DataDockingService dataDockingService;
    @Resource
    private SynchronizationService synchronizationService;
    /**
     * 更新已经结束的项目状态
     * @return
     */

    //cron = "0 0 7 * * ?" 每天上午7点触发
    @Scheduled(cron = "0 0 7 * * ?")
    @Transactional
    public ResponseDTO batchUpdate() {
       List ids =timerDao.findEndProjectId();
       int i= timerDao.batchUpdate(ids);
       return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * 志愿详情 情暖家庭 推送
     * @return
     */
    @Scheduled(cron = "0 0 7 * * ?")
    @Transactional
    public ResponseDTO pushActivityList() {
      return dataDockingService.findActivityList(null);
    }

    /**
     * 志愿者  推送
     * @return
     */
    @Scheduled(cron = "0 53 23 * * ?")
    @Transactional
    public ResponseDTO findVolunteerList()throws Exception {
        return dataDockingService.findVolunteerList(null);
    }


    /**
     * 被服务学校信息 同步数据
     * @return
     */
    @Scheduled(cron = "0 55 23 * * ?")
    @Transactional
    public ResponseDTO syncSchoolData() {
        logger.info(DateUtil.getNow() + "=>被服务学校信息同步数据");
        return synchronizationService.syncSchoolData(null);
    }


    /**
     * 特殊人员信息 同步数据
     * @return
     */
    @Scheduled(cron = "0 56 23 * * ?")
    @Transactional
    public ResponseDTO syncPersonData() {
        logger.info(DateUtil.getNow() + "=>特殊人员信息 同步数据");
        return synchronizationService.syncPersonData(null);
    }


    /**
     * 社会组织信息 同步数据
     * @return
     */
    @Scheduled(cron = "0 57 23 * * ?")
    @Transactional
    public ResponseDTO syncSocietyOrganizationData() {
        logger.info(DateUtil.getNow() + "=>社会组织信息 同步数据");
        return synchronizationService.syncSocietyOrganizationData(null);
    }

    /**
     * 用户信息 同步数据
     * @return
     */
    @Scheduled(cron = "0 58 23 * * ?")
    @Transactional
    public ResponseDTO syncUserData() {
        logger.info(DateUtil.getNow() + "=>用户信息 同步数据");
        return synchronizationService.syncUserData(null);
    }

    /**
     *平安单位信息 同步数据
     * @return
     */
    @Scheduled(cron = "0 59 23 * * ?")
    @Transactional
    public ResponseDTO syncUnitData() {
        logger.info(DateUtil.getNow() + "=>平安单位信息 同步数据");
        return synchronizationService.syncUnitData(null);
    }
}
