package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.vo.ResourcesQVo;
import com.huacainfo.ace.portal.vo.ResourcesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourcesDao {

    int deleteByPrimaryKey(String resourcesId);

    int insert(Resources o);

    int insertNoKey(Resources o);

    int updateByPrimaryKey(Resources o);

    ResourcesVo selectVoByPrimaryKey(String id);

    List<ResourcesVo> findList(@Param("condition") ResourcesQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ResourcesQVo condition);

    int isExist(Resources o);

    int deleteByIds(String[] ids);

    int isExitByName(@Param("name") String name);

    List<Map<String, Object>> selectResourcesTreeList();

    /**
     * 更新菜单显示顺序
     *
     * @param sequence
     * @param resourcesId
     * @return
     */
    int updateSequence(@Param("sequence") int sequence,
                       @Param("resourcesId") String resourcesId);

    /**
     * @description: 查询系统受保护的资源
     * @param:
     * @return: Map
     * @author: 陈晓克
     * @version:3.5.1
     * @exception:
     * @createDate: 2012-12-15
     */
    List<Map<String, String>> loadResourceDefine();

    List<Map<String, Object>> getListByPid(String pid);
}
