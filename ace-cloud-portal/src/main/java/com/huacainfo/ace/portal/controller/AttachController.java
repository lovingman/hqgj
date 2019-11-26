package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.portal.controller.base.BizController;
import com.huacainfo.ace.portal.model.Attach;
import com.huacainfo.ace.portal.service.AttachService;
import com.huacainfo.ace.portal.vo.AttachQVo;
import com.huacainfo.ace.portal.vo.AttachVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/attach", tags = "通知公告")
@RestController
@RequestMapping("/attach")
public class AttachController extends BizController {


    private static final long serialVersionUID = 1L;
    @Autowired
    private AttachService attachService;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO
     * <AttachVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/page", notes = "获取通知公告数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<AttachVo> page(AttachQVo condition) throws Exception {
        return this.attachService.page(condition);
    }

    /**
     * @Title:insertAttach
     * @Description: TODO(创建通知公告)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/create", notes = "创建通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Attach的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Attach obj = JSON.parseObject(jsons, Attach.class);
        return this.attachService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateAttach
     * @Description: TODO(更新通知公告)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/update", notes = "更新通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Attach的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Attach obj = JSON.parseObject(jsons, Attach.class);
        return this.attachService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取通知公告)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Attach>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<AttachVo> getById(String id) throws Exception {
        return this.attachService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteAttachByAttachId
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
        return this.attachService.deleteById(id);
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
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.attachService.deleteByIds(ids.split(","));
    }


}
