package com.huacainfo.ace.zcpa.plugins.wechat.pojo;


import com.huacainfo.ace.common.tools.JsonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 模板消息数据对象
 * date: 2016-07-11 17:19
 *
 * @author Arvin
 * @version 1.0
 * @since JDK 1.8
 */
public class TemplateData implements Serializable {

    private String touser;
    private String template_id;
    private String url;
    private String topcolor;
    private Map<String, DataItem> data = new HashMap<>();
    private Miniprogram miniprogram;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

    public Miniprogram setMiniAppid(String appid) {
        if (miniprogram == null) {
            miniprogram = new Miniprogram();
        }
        miniprogram.setAppid(appid);

        return miniprogram;
    }

    public Miniprogram setMiniPagePath(String pagePath) {
        if (miniprogram == null) {
            miniprogram = new Miniprogram();
        }
        miniprogram.setPagepath(pagePath);

        return miniprogram;
    }

    public Miniprogram getMiniprogram() {
        return miniprogram;
    }

    public String getTouser() {
        return touser;
    }

    public TemplateData setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public TemplateData setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TemplateData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public TemplateData setTopcolor(String topcolor) {
        this.topcolor = topcolor;
        return this;
    }

    public Map<String, DataItem> getData() {
        return data;
    }

    public TemplateData add(String key, String value, String color) {
        data.put(key, new DataItem(value, color));
        return this;
    }


}
