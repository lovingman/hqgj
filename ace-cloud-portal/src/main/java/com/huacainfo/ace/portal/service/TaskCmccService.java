package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.vo.TaskCmccQVo;
import com.huacainfo.ace.portal.vo.TaskCmccVo;

public interface TaskCmccService {
    PageDTO<TaskCmccVo> findTaskCmccList(TaskCmccQVo condition, int start, int limit, String orderBy);

    ResponseDTO insertTaskCmcc(TaskCmcc o, UserProp userProp);

    ResponseDTO insertTaskCmcc(TaskCmcc o);

    ResponseDTO updateTaskCmcc(TaskCmcc o, UserProp userProp);

    ResponseDTO deleteTaskCmccByTaskCmccId(String id, UserProp userProp);

    void queueTask();

    void queueTaskDetail();

    ResponseDTO updateTaskStatusCmccByTaskCmccId(String id, UserProp userProp);

    void workFlowMsgTask();

    ResponseDTO<TaskCmcc> selectById(String id);

    /**
     * 添加短信发送任务
     *
     * @param taskName  任务名称
     * @param message   发送给内容
     * @param mobileStr 目标号码 ：nickName1,mobile1;nickName2,mobile2;
     * @return 发送结果
     */
    ResponseDTO addTaskCmcc(String taskName, String message, String mobileStr);
}
