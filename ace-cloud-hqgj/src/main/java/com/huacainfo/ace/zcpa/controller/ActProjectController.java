package com.huacainfo.ace.zcpa.controller;

import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.zcpa.model.ActProject;
import com.huacainfo.ace.zcpa.service.ActProjectService;
import com.huacainfo.ace.zcpa.vo.ActProjectQVo;
import com.huacainfo.ace.zcpa.vo.ActProjectVo;
import com.huacainfo.ace.zcpa.vo.TargetVo;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author: 王恩
 * @version: 2019-07-19
 * @Description: TODO(项目活动)
 */
@Api(value = "/actProject", tags = "项目活动")
@RestController
@RequestMapping("/actProject")
public class ActProjectController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActProjectService actProjectService;
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
     * <ActProjectVo>
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/page", notes = "获取项目活动数据集合，支持分页查询")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ActProjectVo> page(ActProjectQVo condition, PageParam page) throws Exception {
        PageDTO<ActProjectVo> rst = this.actProjectService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertActProject
     * @Description: TODO(创建项目活动)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/create", notes = "创建项目活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActProject的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(@RequestBody String jsons) throws Exception {
        ActProject obj = JSON.parseObject(jsons, ActProject.class);
        return this.actProjectService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateActProject
     * @Description: TODO(更新项目活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/update", notes = "更新项目活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "ActProject的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(@RequestBody String jsons) throws Exception {
        ActProject obj = JSON.parseObject(jsons, ActProject.class);
        return this.actProjectService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取项目活动)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<ActProject>
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 项目活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectVo> getById(String id, String category) throws Exception {
        return this.actProjectService.getById(id, category);
    }



    /**
     * @throws
     * @Title:deleteActProjectByActProjectId
     * @Description: TODO(删除项目活动)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 项目活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.actProjectService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除项目活动)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 王恩
     * @version: 2019-07-19
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 项目活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.actProjectService.deleteByIds(ids.split(","));
    }

    /**
     * 修改项目活动状态
     *
     * @param id
     * @param projectState
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/updateProjectState", notes = "根据主键修改项目活动状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectState", value = "项目状态 1-待审核 2-进行中 3-未通过 4-已结束", required = true, dataType = "String")
    })
    @PostMapping(value = "/updateProjectState", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateProjectState(String id, String projectState) throws Exception {
        return this.actProjectService.updateProjectState(id, projectState);
    }

    /**
     * 获取项目详情
     *
     * @param id 项目id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "/getByProjectId", notes = "获取项目参与志愿者接口")
    @GetMapping(value = "/getByProjectId", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ActProjectVo> getByProjectId(String id) throws Exception {
        return actProjectService.getByProjectId(id,null);
    }


    /**
     * 情暖家庭对接, 被服务对象列表. 包含志愿者
     *
     * @param projectId 项目ID
     * @param category  项目类型
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/findVolunteerList", notes = "获取项目参与志愿者接口")
    @GetMapping(value = "/findVolunteerList", produces = "application/json;charset=UTF-8")
    public ResponseDTO findVolunteerList(String projectId, String category,String areaCode, PageParam page) {
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少项目主键");
        }
        if (CommonUtils.isBlank(category)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少项目类型");
        }
        PageDTO<TargetVo> rst = this.actProjectService.findVolunteerList(projectId, category,areaCode, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * 更新项目封面图片
     *
     * @param projectId 项目ID
     * @param coverUrl  封面图片-base64字符串
     * @return ResponseDTO
     */
    @ApiOperation(value = "/updateCoverUrl", notes = "更新项目封面图片")
    @PostMapping(value = "/updateCoverUrl", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateCoverUrl(String projectId, String coverUrl) throws IOException {
        if (CommonUtils.isBlank(projectId) || CommonUtils.isBlank(coverUrl)) {
            return new ResponseDTO(ResultCode.FAIL, "参数传递有误");
        }
        //文件服务器图片处理
        MultipartFile picFile = CommonUtils.base64ToMultipart(coverUrl);
        StorePath storePath = fastFileStorageClient.uploadFile(picFile.getInputStream(), picFile.getSize(),
                FilenameUtils.getExtension(picFile.getOriginalFilename()), null);
        //获取预览地址
        coverUrl = getResAccessUrl(storePath);

        return this.actProjectService.updateCoverUrl(projectId, coverUrl, this.getCurUserProp());
    }



    /**
     * 已对接志愿者满4个， 情暖家庭
     *
     * @param projectId 项目ID
     * @param areaCode  区域
     * @return ResponseDTO
     * @throws Exception
     */
    @ApiOperation(value = "/findVolunteerDockedList", notes = "获取项目已对接志愿者接口")
    @GetMapping(value = "/findVolunteerDockedList", produces = "application/json;charset=UTF-8")
    public ResponseDTO findVolunteerDockedList(String projectId, String areaCode, PageParam page) {
        if (CommonUtils.isBlank(projectId)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少项目主键");
        }
        if (CommonUtils.isBlank(areaCode)) {
            return new ResponseDTO(ResultCode.FAIL, "缺少区域");
        }
        PageDTO<TargetVo> rst = this.actProjectService.findVolunteerDockedList(projectId, areaCode, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart() > 1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

}
