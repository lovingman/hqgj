package com.huacainfo.ace.zcpa.controller;

import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.web.controller.BizController;
import com.huacainfo.ace.zcpa.service.SynchronizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "/www/synchronization", tags = "同步数据")
@RestController
@RequestMapping("/www/synchronization")
public class WSynchronizationController extends BizController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SynchronizationService synchronizationService;

    /**
     * 被服务学校数据同步
     * @return
     */
    @ApiOperation(value = "/syncSchoolData", notes = "被服务学校数据同步")
    @GetMapping(value = "/syncSchoolData", produces = "application/json;charset=UTF-8")
    public ResponseDTO syncSchoolData(String srartTime) {
        return synchronizationService.syncSchoolData(srartTime);
    }

    /**
     * 特殊人群
     * @return
     */
    @ApiOperation(value = "/syncPersonData", notes = "特殊人群信息数据同步")
    @GetMapping(value = "/syncPersonData", produces = "application/json;charset=UTF-8")
    public ResponseDTO syncPersonData(String srartTime) {
        return synchronizationService.syncPersonData(srartTime);
    }

    /**
     * 社会组织
     * @return
     */
    @ApiOperation(value = "/syncSocietyOrganizationData", notes = "社会组织数据同步")
    @GetMapping(value = "/syncSocietyOrganizationData", produces = "application/json;charset=UTF-8")
    public ResponseDTO syncSocietyOrganizationData(String srartTime) {
        return synchronizationService.syncSocietyOrganizationData(srartTime);
    }

    /**
     * 用户信息同步
     * @return
     */
    @ApiOperation(value = "/syncUserData", notes = "用户信息数据同步")
    @GetMapping(value = "/syncUserData", produces = "application/json;charset=UTF-8")
    public ResponseDTO syncUserData(String srartTime){
        return  synchronizationService.syncUserData(srartTime);

    }

    /**
     * 平安单位数据同步
     * @return
     */
    @ApiOperation(value = "/syncUnitData", notes = "平安单位数据同步")
    @GetMapping(value = "/syncUnitData", produces = "application/json;charset=UTF-8")
    public ResponseDTO syncUnitData(String srartTime) {
        return  synchronizationService.syncUnitData(srartTime);
    }
}
