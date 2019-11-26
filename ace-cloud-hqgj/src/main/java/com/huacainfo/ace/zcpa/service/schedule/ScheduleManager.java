package com.huacainfo.ace.zcpa.service.schedule;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.zcpa.model.WechatConfig;
import com.huacainfo.ace.zcpa.service.WechatConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ScheduleManager
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/30 11:28
 */
@Service
public class ScheduleManager {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WechatConfigService configService;

    /**
     * 每5min自动检测令牌过期时间;
     * 如令牌已过期,则自动刷新获取新的令牌
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void accessTokenRefresh() {
        logger.info(DateUtil.getNow() + "=>开始刷新微信令牌");
        //获取配置列表
        List<WechatConfig> appList = configService.findAppList();
        for (WechatConfig app : appList) {
            configService.refreshAccessToken(app);
        }

        logger.info(DateUtil.getNow() + "=>微信令牌刷新结束");
    }


}
