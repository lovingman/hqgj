package com.huacainfo.ace.common.model;

import com.huacainfo.ace.common.tools.JsonUtil;

import java.io.Serializable;

/**
 * @Auther: Arvin
 * @Date: 2018/9/7 09:33
 * @Description: 实现序列化，重写toString()
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
