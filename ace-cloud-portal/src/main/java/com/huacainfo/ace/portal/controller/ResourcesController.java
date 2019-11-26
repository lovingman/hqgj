package com.huacainfo.ace.portal.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.common.vo.MongoFile;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(资源文件管理)
 */
@Api(value = "/resources", tags = "资源文件管理")
@RestController
@RequestMapping("/resources")
public class ResourcesController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ResourcesService resourcesService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO<ResourcesVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/page", notes = "获取资源文件管理数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ResourcesVo> page(ResourcesQVo condition, PageParam page) throws Exception {
        PageDTO<ResourcesVo> rst = this.resourcesService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertResources
     * @Description: TODO(创建资源文件管理)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/create", notes = "创建资源文件管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Resources的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Resources obj = JSON.parseObject(jsons, Resources.class);
        return this.resourcesService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateResources
     * @Description: TODO(更新资源文件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/update", notes = "更新资源文件管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Resources的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Resources obj = JSON.parseObject(jsons, Resources.class);
        return this.resourcesService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取资源文件管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Resources>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 资源文件管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ResourcesVo> getById(String id) throws Exception {
        return this.resourcesService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteResourcesByResourcesId
     * @Description: TODO(删除资源文件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 资源文件管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.resourcesService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除资源文件管理)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 资源文件管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.resourcesService.deleteByIds(ids.split(","));
    }

    /**
     * @Description: TODO(获取资源树)
     * @return: List<Tree>
     */
    @ApiOperation(value = "/getResTreeList", notes = "获取资源树", response = Tree.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topId", value = "资源顶级ID，默认为'0'", defaultValue = "0", dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/getResTreeList", produces = "application/json;charset=UTF-8")
    public List<Tree> getResTreeList(String topId) {
        topId = CommonUtils.isBlank(topId) ? "0" : topId;
        return this.resourcesService.getResTreeList(topId);
    }

    /**
     * execel导入接口
     *
     * @param file 文件流数组
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/importXls", notes = "execel导入接口", response = ResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件流数组", dataType = "MultipartFile[]", paramType = "form"),
    })
    @PostMapping(value = "/importXls", produces = "application/json;charset=UTF-8")
    public ResponseDTO importXls(@RequestParam MultipartFile file) throws Exception {
        List<Object> list = EasyExcelFactory.read(file.getInputStream(), new Sheet(1,1, ResExcelVo.class));
        return this.resourcesService.importXls(list,this.getCurUserProp());
    }
    @RequestMapping(value = "/exportXls",  method = RequestMethod.GET)
    public void exportXls(HttpServletResponse response) throws Exception {
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=default.xlsx");
        Sheet sheet=new Sheet(1,1,ResExcelVo.class);
        ResourcesQVo condition=new ResourcesQVo();
        PageDTO<ResourcesVo> rst = this.resourcesService.page(condition, 1, 10000, null);
        List<ResExcelVo> data=new ArrayList();
        for(ResourcesVo o:rst.getRows()){
            ResExcelVo obj=new ResExcelVo();
            CommonBeanUtils.copyProperties(obj,o);
            data.add(obj);
        }
        OutputStream outputStream =response.getOutputStream();
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        writer.write(data,sheet);
        writer.finish();
        outputStream.flush();
    }
    /**
     * 资源排序接口
     *
     * @param jsons 数据json串
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/updateSequence", notes = "资源排序接口", response = ResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "数据json串", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/updateSequence")
    public ResponseDTO updateSequence(String jsons) throws Exception {
        JSONArray jsonArray = JSON.parseArray(jsons);
        List<Map<String, Object>> list = handleJSONArray(jsonArray);
        return this.resourcesService.updateSequence(list, getCurUserProp());
    }


    private List<Map<String, Object>> handleJSONArray(JSONArray jsonArray) {
        List list = new ArrayList();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Map map = new HashMap<String, Object>();
            for (Map.Entry entry : jsonObject.entrySet()) {
                if (entry.getValue() instanceof JSONArray) {
                    map.put((String) entry.getKey(), handleJSONArray((JSONArray) entry.getValue()));
                } else {
                    map.put((String) entry.getKey(), entry.getValue());
                }
            }
            list.add(map);
        }
        return list;
    }
    @RequestMapping(value = "/getListByPid",  method = RequestMethod.GET)
    public ResponseDTO<List<Map<String, Object>>> getListByPid(String pid) throws Exception{
        return this.resourcesService.getListByPid(pid);
    }

}
