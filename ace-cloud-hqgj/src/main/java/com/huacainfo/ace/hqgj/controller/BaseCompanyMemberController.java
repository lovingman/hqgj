package com.huacainfo.ace.hqgj.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberExcelVo;
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
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;
import com.huacainfo.ace.hqgj.service.BaseCompanyMemberService;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberVo;
import com.huacainfo.ace.hqgj.vo.BaseCompanyMemberQVo;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(企业成员表)
 */
@Api(value = "/baseCompanyMember", tags = "企业成员表")
@RestController
@RequestMapping("/baseCompanyMember")
public class BaseCompanyMemberController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseCompanyMemberService baseCompanyMemberService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BaseCompanyMemberVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取企业成员表数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<BaseCompanyMemberVo> page(BaseCompanyMemberQVo condition, PageParam page) throws Exception {

        PageDTO<BaseCompanyMemberVo> rst = this.baseCompanyMemberService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertBaseCompanyMember
     * @Description: TODO(创建企业成员表)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建企业成员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseCompanyMember的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        BaseCompanyMember obj = JSON.parseObject(jsons, BaseCompanyMember.class);
        return this.baseCompanyMemberService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBaseCompanyMember
     * @Description: TODO(更新企业成员表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新企业成员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BaseCompanyMember的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        BaseCompanyMember obj = JSON.parseObject(jsons, BaseCompanyMember.class);
        return this.baseCompanyMemberService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企业成员表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BaseCompanyMember>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 企业成员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<BaseCompanyMemberVo> getById(String id) throws Exception {
        return this.baseCompanyMemberService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteBaseCompanyMemberByBaseCompanyMemberId
     * @Description: TODO(删除企业成员表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 企业成员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.baseCompanyMemberService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企业成员表)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 企业成员表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.baseCompanyMemberService.deleteByIds(ids.split(","));
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
        Sheet sheet = new Sheet(1, 1, BaseCompanyMemberExcelVo.class);

        BaseCompanyMemberQVo condition = new BaseCompanyMemberQVo();
        PageDTO<BaseCompanyMemberVo> rst = this.baseCompanyMemberService.page(condition, 0, 10000, null);
        List<BaseCompanyMemberExcelVo> data = new ArrayList();
        for (BaseCompanyMemberVo o : rst.getRows()) {
            BaseCompanyMemberExcelVo obj = new BaseCompanyMemberExcelVo();
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
