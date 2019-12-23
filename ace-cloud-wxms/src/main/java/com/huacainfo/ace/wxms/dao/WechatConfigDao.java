package com.huacainfo.ace.wxms.dao;

import com.huacainfo.ace.wxms.model.WechatConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WechatConfigDao {
    /**
     * 主键删除
     *
     * @param appId 主键
     * @return int
     */
    int deleteByPrimaryKey(String appId);

    /**
     * 实体插入
     *
     * @param record 实体对象
     * @return int
     */
    int insert(WechatConfig record);

    /**
     * 主键查询
     *
     * @param appId 主键
     * @return int
     */
    WechatConfig selectByPrimaryKey(String appId);

    /**
     * 实体更新
     *
     * @param record 实体对象
     * @return int
     */
    int updateByPrimaryKey(WechatConfig record);

    /**
     * 系统ID查询
     *
     * @param sysId 系统ID
     * @return WechatConfig
     */
    WechatConfig findBySysId(String sysId);

    /**
     * 状态查询
     *
     * @param status 状态
     * @return List<WechatConfig>
     */
    List<WechatConfig> findAppListByStatus(@Param("status") int status);
}
