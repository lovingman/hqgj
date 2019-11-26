package com.huacainfo.ace.common.web.controller;

import com.huacainfo.ace.common.constant.CommonKeys;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.vo.UserProp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

/**
 * @ClassName BizController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 19:28
 */
public class BizController extends BaseController {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisOperations<String, Object> redisClient;

    /**
     * 设置登录用户session
     *
     * @param syUser 用户资料
     */
    public void setUserSession(Users syUser) {
        UserProp u = new UserProp();
        setSession(CommonKeys.SESSION_USERINFO_KEY, u);
    }

    /**
     * 获取session属性值
     *
     * @param name 属性名称
     * @return Object
     */
    public Object getSession(String name) {
        return getRequest().getSession().getAttribute(name);
    }

    /**
     * 移除session的值
     *
     * @param name 移除session属性
     */
    public void delSession(String name) {
        getRequest().getSession().removeAttribute(name);
    }

    /**
     * 设置session的值
     *
     * @param name  属性名称
     * @param value 属性值
     */
    public void setSession(String name, Object value) {
        getRequest().getSession().setAttribute(name, value);
    }


    /**
     * 设置redis的值
     *
     * @param key
     * @param value
     * @param time
     */
    public void redisSet(String key, String value, long time) {
        if (time > 0) {
            redisClient.opsForValue().set(key, value, time);
        } else {
            redisClient.opsForValue().set(key, value);
        }
    }

    /**
     * 获取redis的值
     *
     * @param key
     * @return
     */
    public String redisGet(String key) {
        Object value = redisClient.opsForValue().get(key);
        return null == value ? "" : String.valueOf(value);
    }

    /**
     * 删除redis的值
     *
     * @param key
     */
    public void redisDelete(String key) {
        redisClient.delete(key);
    }


}
