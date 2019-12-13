package com.huacainfo.ace.hqgj.service.impl.schedule;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.hqgj.dao.TimerDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时
 */
@Service
public class TimeServiceImpl {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TimerDao timerDao;

    /**
     * 更新已经结束的状态
     * @return
     */
    //cron = "0 0 7 * * ?" 每天凌晨1点触发
    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public ResponseDTO batchUpdate() {
        List ids =timerDao.findEndId();
        int i= timerDao.batchUpdate(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }
}
