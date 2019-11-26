package com.huacainfo.ace.common.log.aop;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.log.feign.SysLogService;
import com.huacainfo.ace.common.log.model.SysLog;
import com.huacainfo.ace.common.web.controller.BaseController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 *
 * @author weiwenjun
 * @date 2018/9/12
 */
@Aspect
@Component
public class LogAspect extends BaseController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysLogService sysLogService;

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.huacainfo.ace.common.log.annotation.Log)")
    public void sysLog() {
    }

    @Override
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
    }


    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        //System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            // System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            //System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            // System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            //System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            // System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            // System.out.println("getRemoteAddr ip: " + ip);
        }
        logger.info("客户端IP -> {} ", ip);
        return ip;
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("sysLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.logger.info("{}", request.getRequestURL());
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res = joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addsysLog(joinPoint, res, time);
            } catch (Exception e) {
                logger.error("LogAspect 操作失败 {}", e.getMessage());
            }
        }
    }

    private void addsysLog(JoinPoint joinPoint, Object res, long time) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLog sysLog = new SysLog();
        sysLog.setRunTime(time);
        sysLog.setId(UUID.randomUUID().toString());
        sysLog.setCreateTime(new Date());
        sysLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        if (this.getCurUserProp() != null) {
            sysLog.setUserId(this.getCurUserProp().getUserId());
            sysLog.setUserName(this.getCurUserProp().getName());
        }
        Log log = signature.getMethod().getAnnotation(Log.class);
        if (log != null) {
            sysLog.setDescribe(log.detail());
            sysLog.setOperationObj(log.operationObj());
            sysLog.setOperationType(log.operationType());
            sysLog.setOperationIp(this.getIpAddr(this.getRequest()));
        }
        if (res != null) {
            sysLog.setReturnValue(JSON.toJSONString(res));
        }
        if (joinPoint.getArgs() != null) {
            sysLog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        }
        //这里保存日志
        logger.info("日志 -> {}", sysLog.toString());
        ResponseDTO rst = sysLogService.aspectLog(sysLog);
        logger.info("日志返回 -> {}", rst);
    }


    @Before("sysLog()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        logger.info("进入方法前执行");

    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "sysLog()")
    public void doAfterReturning(Object ret) {
        logger.info("方法的返回值 -> {}", ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("sysLog()")
    public void throwss(JoinPoint jp) {
        logger.info("方法异常时执行");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("sysLog()")
    public void after(JoinPoint jp) {
        logger.info("方法最后执行");
    }

}
