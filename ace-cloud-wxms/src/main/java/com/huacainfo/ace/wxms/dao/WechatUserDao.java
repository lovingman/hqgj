package com.huacainfo.ace.wxms.dao;

import com.huacainfo.ace.wxms.model.WechatUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WechatUserDao {
    int deleteByPrimaryKey(String unionid);

    int insert(WechatUser record);

    WechatUser selectByPrimaryKey(String unionid);

    int updateByPrimaryKey(WechatUser record);

    int isExists(WechatUser user);

    List<WechatUser> openIdList(@Param("projectId") String projectId, @Param("targetId") String targetId);
}
