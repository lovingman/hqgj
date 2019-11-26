package com.huacainfo.ace.common.security.component;

import com.huacainfo.ace.common.security.feign.FeignSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FeignSecurityService feignSecurityService;

    @Autowired
    private CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;

    @Value("${spring.application.name}")
    private String name;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        logger.info("liting: contextDestroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        logger.info("liting: contextInitialized");
        long delay = 1000;

        if (StringUtils.isEmpty(name)) {
            delay = 1000;
        } else if (name.equals("zcpa-portal")) {
            delay = 1000 * 40;
        }

        logger.info("{} 启动成功 {}", name, delay);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                initSyCfg(arg0.getServletContext());
                customInvocationSecurityMetadataSource.loadResourceDefine();
                //this.cancel();
            }
        }, delay, 3600 * 1000 * 1);

    }

    private void initSyCfg(ServletContext servletContext) {
        this.logger.info("加载系统参数");
        Map<String, String> cfg = this.feignSecurityService.loadConfig("sys");
        servletContext.setAttribute("cfg", cfg);
        logger.info("{}", cfg);
    }

}
