package com.huacainfo.ace.zcpa.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerExcelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.zcpa.model.BaseVolunteer;
import com.huacainfo.ace.zcpa.service.BaseVolunteerService;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerVo;
import com.huacainfo.ace.zcpa.vo.BaseVolunteerQVo;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: heshuang
 * @version: 2019-07-26
 * @Description: TODO(群众 / 志愿者)
 */
@Api(value = "/baseVolunteer", tags = "群众/志愿者")
@RestController
@RequestMapping("/baseVolunteer")
public class BaseVolunteerController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseVolunteerService baseVolunteerService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseVolunteerVo>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/page", notes = "获取群众/志愿者数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<BaseVolunteerVo> page(BaseVolunteerQVo condition, PageParam page) throws Exception {
        PageDTO<BaseVolunteerVo> rst = this.baseVolunteerService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;

    }

    /**
     * @Title:insertBaseVolunteer
     * @Description: TODO(创建群众 / 志愿者)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/create", notes = "创建群众/志愿者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        BaseVolunteer obj = JSON.parseObject(jsons, BaseVolunteer.class);
        return this.baseVolunteerService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBaseVolunteer
     * @Description: TODO(更新群众 / 志愿者)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/update", notes = "更新群众/志愿者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseVolunteer的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody  String jsons) throws Exception {
        BaseVolunteer obj = JSON.parseObject(jsons, BaseVolunteer.class);
        return this.baseVolunteerService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取群众 / 志愿者)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseVolunteer>
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 群众/志愿者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<BaseVolunteerVo> getById(String id) throws Exception {
        return this.baseVolunteerService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteBaseVolunteerByBaseVolunteerId
     * @Description: TODO(删除群众 / 志愿者)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 群众/志愿者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.baseVolunteerService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除群众 / 志愿者)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: heshuang
     * @version: 2019-07-26
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 群众/志愿者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.baseVolunteerService.deleteByIds(ids.split(","));
    }

    /**
     * 导出
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportXls",  method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response, BaseVolunteerQVo condition) throws Exception {

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=volunteer.xlsx");
        Sheet sheet = new Sheet(1, 1, BaseVolunteerExcelVo.class);
        List<BaseVolunteerExcelVo> data = new ArrayList();
        try {
            PageDTO<BaseVolunteerVo> rst = this.baseVolunteerService.page(condition, 0, 10000, null);
            for (BaseVolunteerVo o : rst.getRows()) {
                BaseVolunteerExcelVo obj = new BaseVolunteerExcelVo();
                CommonBeanUtils.copyProperties(obj, o);
                data.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        OutputStream outputStream = response.getOutputStream();
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        writer.write(data, sheet);
        writer.finish();
        outputStream.flush();
    }
}
