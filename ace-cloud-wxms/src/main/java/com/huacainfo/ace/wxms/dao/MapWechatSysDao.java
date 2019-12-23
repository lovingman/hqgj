package com.huacainfo.ace.wxms.dao;

import com.huacainfo.ace.wxms.model.MapWechatSys;

import java.util.Map;

public interface MapWechatSysDao {
    int deleteByPrimaryKey(String id);

    int insert(MapWechatSys record);

    MapWechatSys selectByPrimaryKey(String id);

    int updateByPrimaryKey(MapWechatSys record);

    Map<String, Object> findByUnionId(String unionId);
}
