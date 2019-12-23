package com.huacainfo.ace.wxms.dao;

import com.huacainfo.ace.wxms.model.WechatMiniUser;

public interface WechatMiniUserDao {
    int deleteByPrimaryKey(String id);

    int insert(WechatMiniUser record);

    WechatMiniUser selectByPrimaryKey(String id);

    int updateByPrimaryKey(WechatMiniUser record);

    WechatMiniUser findByUnionId(String unionId);
}
