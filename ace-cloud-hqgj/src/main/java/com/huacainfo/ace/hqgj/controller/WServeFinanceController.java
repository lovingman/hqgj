package com.huacainfo.ace.hqgj.controller;

import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.hqgj.model.ServeFinance;
import com.huacainfo.ace.hqgj.service.ServeFinanceService;
import com.huacainfo.ace.hqgj.vo.ServeFinanceQVo;
import com.huacainfo.ace.hqgj.vo.ServeFinanceVo;
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
 * @author: 何双
 * @version: 2019-12-10
 * @Description: TODO(财税服务包)
 */
@Api(value = "/www/serveFinance", tags = "财税服务包")
@RestController
@RequestMapping("/www/serveFinance")
public class WServeFinanceController extends BaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServeFinanceService serveFinanceService;
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
    public ResponseDTO<ServeFinanceVo> getById(String id,String userId) throws Exception {
        UserProp user = this.getCurUserProp();
        return this.serveFinanceService.getById(id,user==null?userId:user.getUserId());
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
