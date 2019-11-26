package com.huacainfo.ace.portal.dao;

/**
 * @author chenxiaoke
 */


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chenxiaoke
 */
public interface SystemDao {

    List<Map<String, Object>> selectResourcesByUserId(String userId);

    List<Map<String, String>> selectButtonsByUserId(String userId);

    List<Map<String, Object>> getAreaTree(@Param("pid") String pid,
                                          @Param("type") Integer type);

    List<Map<String, Object>> getNewAreaTree(@Param("pid") String pid,
                                             @Param("typeArray") String[] typeArray);

    String [] getPAreaCode(String areaCode);
}
