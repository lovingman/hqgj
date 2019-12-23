/**
 * project name:distribution
 * file name:ParaMap
 * package name:com.cdkj.wechat.util
 * date:2016/8/8 16:08
 * author:haing
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.util;

import java.util.HashMap;
import java.util.Map;

/**
 * description: //map参数快速创建 <br>
 * date: 2016/8/8 16:08
 *
 * @author haing
 * @version 1.0
 * @since JDK 1.8
 */
public class ParaMap {
    private Map<String, String> data = new HashMap<>();

    private ParaMap() {
    }

    public static ParaMap create() {
        return new ParaMap();
    }

    public static ParaMap create(String key, String value) {
        return create().put(key, value);
    }

    public ParaMap put(String key, String value) {
        data.put(key, value);
        return this;
    }

    public Map<String, String> getData() {
        return data;
    }
}
