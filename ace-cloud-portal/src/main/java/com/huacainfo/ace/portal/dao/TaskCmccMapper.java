package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.QueueCmccWait;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.vo.TaskCmccQVo;
import com.huacainfo.ace.portal.vo.TaskCmccVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TaskCmccMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(TaskCmcc record);

    int insertSelective(TaskCmcc record);

    TaskCmcc selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TaskCmcc record);

    int updateByPrimaryKeyWithBLOBs(TaskCmcc record);

    int updateByPrimaryKey(TaskCmcc record);

    int isExitByTaskName(@Param("name") String name);

    List<TaskCmccVo> findList(@Param("condition") TaskCmccQVo condition,
                              @Param("start") int start, @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TaskCmccQVo condition);

    List<TaskCmcc> selectByTask();

    List<QueueCmccWait> selectQueueByTask();

    int updateStatusByPrimaryKey(@Param("taskId") String taskId, @Param("status") String status);

    List<Map<String, Object>> selectWorkflowTaskMsg();

    int updateWorkflowTaskMsgByTaskId(String id);
}
