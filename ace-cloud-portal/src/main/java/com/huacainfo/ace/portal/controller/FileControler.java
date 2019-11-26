package com.huacainfo.ace.portal.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.controller.base.BizController;
import io.swagger.annotations.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "文件控制类", tags = "文件控制类")

@RestController
public class FileControler extends BizController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    @ApiOperation(value = "文件上传接口", notes = "成功后返回文件URL地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流", required = true, dataType = "MultipartFile", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器接口异常", response = Exception.class),
    })
    @PostMapping(value = "/www/uploadFile", produces = "application/json;charset=UTF-8")
    public ResponseDTO<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        ResponseDTO rst = new ResponseDTO();
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        String url = getResAccessUrl(storePath);
        rst.setData(url);
        return rst;
    }

    @ApiOperation(value = "图片上传接口，可自动完成切图功能（400*400）", notes = "成功后返回文件URL地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片流", required = true, dataType = "MultipartFile", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器接口异常", response = Exception.class),
    })
    @PostMapping(value = "/www/uploadImage", produces = "application/json;charset=UTF-8")
    public ResponseDTO<String> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        ResponseDTO rst = new ResponseDTO();
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        String url = getResAccessUrl(storePath);
        rst.setData(url);
        //获取到缩略图前缀_400x400
        // /group1/M00/00/56/i-AA41zK77eAeK6dAACoH-kzJAk65_400x400.jpeg
        return rst;
    }

    @ApiOperation(value = "文件上传接口", notes = "成功后返回文件URL地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file/fileStr", value = "文件流/base64", required = true, dataType = "MultipartFile", paramType = "form") })
    @ApiResponses({ @ApiResponse(code = 500, message = "服务器接口异常", response = Exception.class), })
    @PostMapping(value = "/www/uploadFileBase", produces = "application/json;charset=UTF-8")
    public ResponseDTO<String> uploadFileBase(MultipartFile picFile, String file) throws Exception {
        ResponseDTO<String> rst = new ResponseDTO<>();
        if (CommonUtils.isNotEmpty(file)) {
            picFile = CommonUtils.base64ToMultipart(file);
        }
        if (CommonUtils.isBlank(picFile)) {
            return new ResponseDTO<>(ResultCode.FAIL,"文件数据为空或非base64图片字符串！");
        }
        StorePath storePath = fastFileStorageClient.uploadFile(picFile.getInputStream(), picFile.getSize(),
                FilenameUtils.getExtension(picFile.getOriginalFilename()), null);
        String url = getResAccessUrl(storePath);
        rst.setData(url);
        return rst;
    }

}
