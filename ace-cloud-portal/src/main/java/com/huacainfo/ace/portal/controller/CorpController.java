package com.huacainfo.ace.portal.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Corp;
import com.huacainfo.ace.portal.service.CorpService;
import com.huacainfo.ace.portal.vo.CorpExcelVo;
import com.huacainfo.ace.portal.vo.CorpQVo;
import com.huacainfo.ace.portal.vo.CorpVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/corp")
/**
 * @author: 陈晓克
 * @version: 2019-05-16
 * @Description: TODO(企事业单位)
 */
@Api(value = "/corp", tags = "企事业单位接口")
public class CorpController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CorpService corpService;

    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO<CorpVo>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<CorpVo> page(CorpQVo condition, PageParam page) throws Exception {
        if (StringUtils.isEmpty(condition.getAreaCode())) {
            condition.setAreaCode(this.getCurUserProp().getAreaCode());
        }
        PageDTO<CorpVo> rst = this.corpService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertcorp
     * @Description: TODO(创建企事业单位)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Corp obj = JSON.parseObject(jsons, Corp.class);

        return this.corpService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updatecorp
     * @Description: TODO(更新企事业单位)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Corp obj = JSON.parseObject(jsons, Corp.class);

        return this.corpService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取企事业单位)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Corp>
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<CorpVo> getById(String id) throws Exception {

        return this.corpService.getById(id);
    }

    /**
     * @throws
     * @Title:deletecorpBycorpId
     * @Description: TODO(删除企事业单位)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {

        return this.corpService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除企事业单位)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-16
     */
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {

        return this.corpService.deleteByIds(ids.split(","));
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(名称 、 行政区划查询企事业单位 ， 主要用于可搜索下拉框)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListDTO<Map < String, Object>>
     * @author: 陈晓克
     * @version: 2019-05-17
     */
    @GetMapping(value = "/getList", produces = "application/json;charset=UTF-8")
    public ListDTO<Map<String, Object>> getList(String name) throws Exception {

        Map<String, Object> p = new HashMap<>();
        p.put("name", name);
        p.put("areaCode", this.getCurUserProp().getAreaCode());
        return this.corpService.getList(p);
    }
    @PostMapping(value = "/importXls", produces = "application/json;charset=UTF-8")
    public ResponseDTO importXls(@RequestParam MultipartFile file) throws Exception {
        List<Object> list = EasyExcelFactory.read(file.getInputStream(), new Sheet(1,1,CorpExcelVo.class));
        return this.corpService.importXls(list);
    }
    @RequestMapping(value = "/exportXls",  method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response) throws Exception {

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=default.xlsx");
        Sheet sheet=new Sheet(1,1,CorpExcelVo.class);

        CorpQVo condition=new CorpQVo();
        condition.setAreaCode(this.getCurUserProp().getAreaCode());
        PageDTO<CorpVo> rst = this.corpService.page(condition, 1, 10000, null);
        List<CorpExcelVo> data=new ArrayList();
        for(CorpVo o:rst.getRows()){
            CorpExcelVo obj=new CorpExcelVo();
            CommonBeanUtils.copyProperties(obj,o);
            obj.setAreaCode(o.getAreaName());
            obj.setType(o.getTypeName());
            obj.setPid(o.getPname());
            data.add(obj);
        }
        OutputStream  outputStream =response.getOutputStream();
        ExcelWriter  writer = EasyExcelFactory.getWriter(outputStream);
        writer.write(data,sheet);
        writer.finish();
        outputStream.flush();
    }



}
