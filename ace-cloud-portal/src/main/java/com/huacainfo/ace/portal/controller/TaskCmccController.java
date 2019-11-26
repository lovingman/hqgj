package com.huacainfo.ace.portal.controller;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TaskCmccController
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 20:25
 */
@RestController
@RequestMapping("/feign/sms")
public class TaskCmccController extends BaseController {


    @Autowired
    private TaskCmccService taskCmccService;

    /**
     * 添加短信发送任务
     *
     * @param o 发送数据
     * @return ResponseDTO
     */
    @PostMapping(value = "/insertTaskCmcc", produces = "application/json;charset=UTF-8")
    ResponseDTO insertTaskCmcc(TaskCmcc o) {
        return taskCmccService.insertTaskCmcc(o);
    }

    /**
     * 添加短信发送任务
     *
     * @param taskName  任务名称
     * @param message   发送给内容
     * @param mobileStr 目标号码 ：nickName1,mobile1;nickName2,mobile2;
     * @return 发送结果
     */
    @GetMapping(value = "/addTaskCmcc", produces = "application/json;charset=UTF-8")
    ResponseDTO addTaskCmcc(@RequestParam("taskName") String taskName,
                            @RequestParam("message") String message,
                            @RequestParam("mobileStr") String mobileStr) {
        return taskCmccService.addTaskCmcc(taskName, message, mobileStr);
    }
}
