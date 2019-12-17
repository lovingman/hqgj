package com.huacainfo.ace.hqgj.controller;

import com.huacainfo.ace.common.tools.CommonUtils;
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

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.service.BasicAnnexService;
import com.huacainfo.ace.hqgj.vo.BasicAnnexVo;
import com.huacainfo.ace.hqgj.vo.BasicAnnexQVo;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


/**
 * @author: 何双
 * @version: 2019-12-09
 * @Description: TODO(附件)
 */
@Api(value = "/basicAnnex", tags = "附件")
@RestController
@RequestMapping("/basicAnnex")
public class BasicAnnexController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BasicAnnexService basicAnnexService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <BasicAnnexVo>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/page", notes = "获取附件数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <BasicAnnexVo> page(BasicAnnexQVo condition, PageParam page) throws Exception {

        PageDTO<BasicAnnexVo> rst = this.basicAnnexService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertBasicAnnex
     * @Description: TODO(创建附件)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/create", notes = "创建附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BasicAnnex的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        BasicAnnex obj = JSON.parseObject(jsons, BasicAnnex.class);
        return this.basicAnnexService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBasicAnnex
     * @Description: TODO(更新附件)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/update", notes = "更新附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "BasicAnnex的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        BasicAnnex obj = JSON.parseObject(jsons, BasicAnnex.class);
        return this.basicAnnexService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取附件)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<BasicAnnex>
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<BasicAnnexVo> getById(String id) throws Exception {
        return this.basicAnnexService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteBasicAnnexByBasicAnnexId
     * @Description: TODO(删除附件)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.basicAnnexService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除附件)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-09
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.basicAnnexService.deleteByIds(ids.split(","));
    }


    /**
     * 下载文件
     * @param relationId
     * @param path
     * @param res
     * @throws Exception
     */
    @RequestMapping(value = "/download")
    public void downloadExcel(String relationId, String path, HttpServletResponse res) throws Exception {
        if(CommonUtils.isBlank(relationId)||CommonUtils.isBlank(path)){
            return;
        }
        res.setContentType("multipart/form-data");
        res.setCharacterEncoding("utf-8");
        BasicAnnexQVo condition=new BasicAnnexQVo();
        condition.setRelationId(relationId);
        PageDTO<BasicAnnexVo> rst = this.basicAnnexService.page(condition, 0,1000,null);
        try {
            //多个图片下载地址
            for(BasicAnnexVo vo:rst.getRows()){
                //根据图片地址构建URL
                URL url= new URL(vo.getFileURL());
                URLConnection conn = url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.connect();
                DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());

                String filename = vo.getFileURL();
                // 取得文件名。
                String ext = filename.substring(filename.lastIndexOf("/") + 1).toUpperCase();
                //创建目录和图片
                File pathFile=new File(path+"\\"+vo.getFileName());
                File file=new File(path+"\\"+vo.getFileName()+"\\"+ext);
                if(!pathFile.exists()) {
                    pathFile.mkdirs();
                    file.createNewFile();
                }
                if(!file.exists()) {
                    file.createNewFile();
                }
                //通过流复制图片
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                fileOutputStream.write(output.toByteArray());
                dataInputStream.close();
                fileOutputStream.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
