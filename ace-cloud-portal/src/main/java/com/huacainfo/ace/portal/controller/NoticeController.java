package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Notice;
import com.huacainfo.ace.portal.service.NoticeService;
import com.huacainfo.ace.portal.vo.NoticeQVo;
import com.huacainfo.ace.portal.vo.NoticeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(通知公告)
 */
@Api(value = "/notice", tags = "通知公告")
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NoticeService noticeService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO
     * <NoticeVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/page", notes = "获取通知公告数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<NoticeVo> page(NoticeQVo condition, PageParam page) throws Exception {
        condition.setDepartmentId(this.getCurUserProp().getCorpId());
        condition.setSyid(this.getCurUserProp().getActiveSyId());

        PageDTO<NoticeVo> rst = this.noticeService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertNotice
     * @Description: TODO(创建通知公告)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/create", notes = "创建通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Notice的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Notice obj = JSON.parseObject(jsons, Notice.class);
        return this.noticeService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateNotice
     * @Description: TODO(更新通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/update", notes = "更新通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Notice的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Notice obj = JSON.parseObject(jsons, Notice.class);
        return this.noticeService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Notice>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<NoticeVo> getById(String id) throws Exception {
        return this.noticeService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteNoticeByNoticeId
     * @Description: TODO(删除通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.noticeService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除通知公告)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.noticeService.deleteByIds(ids.split(","));
    }

}
