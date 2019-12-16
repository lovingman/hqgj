package com.huacainfo.ace.hqgj.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.tools.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.model.ServeFinance;
import com.huacainfo.ace.hqgj.service.ServeFinanceService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceQVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author: 何双
 * @version: 2019-12-10
 * @Description: TODO(财税服务包)
 */
@Api(value = "/serveFinance", tags = "财税服务包")
@RestController
@RequestMapping("/serveFinance")
public class ServeFinanceController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeFinanceService serveFinanceService;
    @Value("${fdfs.web-server-url}")
    public String webServerUrl;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = webServerUrl + "/" + storePath.getFullPath();
        logger.info("{}", fileUrl);
        return fileUrl;
    }

    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: NewPageDTO
     * <ServeFinanceVo>
     * @author: 何双
     * @version: 2019-12-10
     */
    @ApiOperation(value = "/page", notes = "获取财税服务包数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO
            <ServeFinanceVo> page(ServeFinanceQVo condition, PageParam page) throws Exception {

        PageDTO<ServeFinanceVo> rst = this.serveFinanceService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertServeFinance
     * @Description: TODO(创建财税服务包)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @ApiOperation(value = "/create", notes = "创建财税服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinance的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ServeFinance obj = JSON.parseObject(jsons, ServeFinance.class);
        return this.serveFinanceService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateServeFinance
     * @Description: TODO(更新财税服务包)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @ApiOperation(value = "/update", notes = "更新财税服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ServeFinance的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ServeFinance obj = JSON.parseObject(jsons, ServeFinance.class);
        return this.serveFinanceService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取财税服务包)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ServeFinance>
     * @author: 何双
     * @version: 2019-12-10
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 财税服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ServeFinanceVo> getById(String id) throws Exception {
        return this.serveFinanceService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteServeFinanceByServeFinanceId
     * @Description: TODO(删除财税服务包)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 财税服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.serveFinanceService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除财税服务包)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 何双
     * @version: 2019-12-10
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 财税服务包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.serveFinanceService.deleteByIds(ids.split(","));
    }
    /**
     * 更新封面图片
     *
     * @param id ID
     * @param coverUrl  封面图片-base64字符串
     * @return ResponseDTO
     */
    @ApiOperation(value = "/updateCoverUrl", notes = "更新项目封面图片")
    @PostMapping(value = "/updateCoverUrl", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateCoverUrl(String id, String coverUrl) throws IOException {
        if (CommonUtils.isBlank(id) || CommonUtils.isBlank(coverUrl)) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        //文件服务器图片处理
        MultipartFile picFile = CommonUtils.base64ToMultipart(coverUrl);
        StorePath storePath = fastFileStorageClient.uploadFile(picFile.getInputStream(), picFile.getSize(),
                FilenameUtils.getExtension(picFile.getOriginalFilename()), null);
        //获取预览地址
        coverUrl = getResAccessUrl(storePath);
        return this.serveFinanceService.updateCoverUrl(id, coverUrl);
    }


    /**
     * 修改状态 0-待审核 1-审核通过 2-未通过 3-已上线 4-已下线',
     * @param id 主键
     * @param status 状态
     * @return
     */
    @ApiOperation(value = "/updateStatus", notes = "修改状态")
    @PostMapping(value = "/updateStatus", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateStatus(String id, String status){
        if (CommonUtils.isBlank(id) || CommonUtils.isBlank(status)) {
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        return serveFinanceService.updateStatus(id,status);
    }
}
