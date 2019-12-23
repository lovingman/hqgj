package com.huacainfo.ace.common.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ClassName RedisTemplate
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/10/8 17:16
 */
public class RedisTemplate {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate redisClient;
    /**
     * redis 塞值方法1
     *
     * @param key   索引key
     * @param value 对象value
     */
    protected void redisSet(String key, String value) {
        this.redisClient.opsForValue().set(key, value);
    }

    /**
     * redis 塞值方法2;附带超期时间
     *
     * @param key   索引key
     * @param value 对象value
     * @param time  超期时间
     */
    protected void redisSet(String key, String value, long time) {
        if (time > 0L) {
            this.redisClient.opsForValue().set(key, value, time);
        } else {
            this.redisClient.opsForValue().set(key, value);
        }

    }

    /**
     * 根据key,获取redis属性值
     *
     * @param key 索引key
     * @return value
     */
    protected String redisGet(String key) {
        Object value = this.redisClient.opsForValue().get(key);
        return null == value ? "" : String.valueOf(value);
    }

    /**
     * 删除redis中,某个key的值
     *
     * @param key 索引key
     */
    protected void redisDelete(String key) {
        this.redisClient.delete(key);
    }

}
