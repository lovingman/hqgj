package com.huacainfo.ace.hqgj.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.hqgj.vo.*;
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
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.model.BaseOrganizationMember;
import com.huacainfo.ace.hqgj.service.BaseOrganizationMemberService;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: 豆文国
 * @version: 2019-12-10
 * @Description:  TODO(服务机构成员)
 */
@Api(value = "/www/baseOrganizationMember",tags = "服务机构成员")
@RestController
@RequestMapping("/www/baseOrganizationMember")
public class WBaseOrganizationMemberController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseOrganizationMemberService baseOrganizationMemberService;


    /**
     *
     * @Title:page
     * @Description:  TODO(分页查询)
     * @param:        @param condition
     * @param:        @param page
     * @param:        @return
     * @param:        @throws Exception
     * @return:       NewPageDTO
    <BaseOrganizationMemberVo>
     * @throws
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @ApiOperation(value = "/page", notes = "获取服务机构成员数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <BaseOrganizationMemberVo> page(BaseOrganizationMemberQVo condition, PageParam page) throws Exception {

        PageDTO<BaseOrganizationMemberVo> rst =this.baseOrganizationMemberService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     *
     * @Title:insertBaseOrganizationMember
     * @Description: TODO(创建服务机构成员)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @ApiOperation(value ="/create",notes = "创建服务机构成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseOrganizationMember的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        BaseOrganizationMember obj = JSON.parseObject(jsons, BaseOrganizationMember.class);
        return this.baseOrganizationMemberService.create(obj,this.getCurUserProp());
    }

    /**
     *
     * @Title:updateBaseOrganizationMember
     * @Description: TODO(更新服务机构成员)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @throws
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @ApiOperation(value ="/update",notes = "更新服务机构成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseOrganizationMember的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        BaseOrganizationMember obj = JSON.parseObject(jsons, BaseOrganizationMember.class);
        return this.baseOrganizationMemberService.update(obj,this.getCurUserProp());
    }

    /**
     *
     * @Title:getById
     * @Description: TODO(获取服务机构成员)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseOrganizationMember>
     * @throws
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @ApiOperation(value ="/getById",notes = "根据主键获取 服务机构成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<BaseOrganizationMemberVo> getById(String id)throws Exception {
        return this.baseOrganizationMemberService.getById(id);
    }

    /**
     *
     * @Title:deleteBaseOrganizationMemberByBaseOrganizationMemberId
     * @Description: TODO(删除服务机构成员)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @throws
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @ApiOperation(value ="/deleteById",notes = "根据主键删除 服务机构成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.baseOrganizationMemberService.deleteById(id);
    }


    /**
     *
     * @Title:deleteByIds
     * @Description: TODO(批量删除服务机构成员)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @throws
     * @author: 豆文国
     * @version: 2019-12-10
     */
    @ApiOperation(value ="/deleteByIds",notes = "根据主键批量删除 服务机构成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.baseOrganizationMemberService.deleteByIds(ids.split(","));
    }

    /**
     * 导出
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportXls", method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response) throws Exception {

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=default.xlsx");
        Sheet sheet = new Sheet(1, 1, BaseOrganizationMemberExcelVo.class);

        BaseOrganizationMemberQVo condition = new BaseOrganizationMemberQVo();
        PageDTO<BaseOrganizationMemberVo> rst = this.baseOrganizationMemberService.page(condition, 0, 10000, null);
        List<BaseOrganizationMemberExcelVo> data = new ArrayList();
        for (BaseOrganizationMemberVo o : rst.getRows()) {
            BaseOrganizationMemberExcelVo obj = new BaseOrganizationMemberExcelVo();
            CommonBeanUtils.copyProperties(obj, o);
            data.add(obj);
        }
        OutputStream outputStream = response.getOutputStream();
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        writer.write(data, sheet);
        writer.finish();
        outputStream.flush();
    }
}
