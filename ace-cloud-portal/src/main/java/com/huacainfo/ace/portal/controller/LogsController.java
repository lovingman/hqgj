package com.huacainfo.ace.portal.controller;

import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.service.LogsService;
import com.huacainfo.ace.portal.vo.LogsQVo;
import com.huacainfo.ace.portal.vo.LogsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: wangen
 * @version: 2019-06-01
 * @Description: TODO(操作日志)
 */
@Api(value = "/logs", tags = "操作日志")
@RestController
@RequestMapping("/logs")
public class LogsController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogsService logsService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <LogsVo>
     * @author: wangen
     * @version: 2019-06-01
     */
    @ApiOperation(value = "/page", notes = "获取操作日志数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<LogsVo> page(LogsQVo condition, PageParam page) throws Exception {
        PageDTO<LogsVo> rst = this.logsService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }



    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取操作日志)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Logs>
     * @author: wangen
     * @version: 2019-06-01
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<LogsVo> getById(String id) throws Exception {
        return this.logsService.getById(id);
    }


}
