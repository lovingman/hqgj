package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.portal.controller.base.BizController;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.service.ConfigService;
import com.huacainfo.ace.portal.vo.ConfigQVo;
import com.huacainfo.ace.portal.vo.ConfigVo;
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


/**
 * @author: ArvinZou
 * @version: 2019-05-17
 * @Description: TODO(系统参数配置)
 */
@Api(value = "/config", tags = "系统参数配置")
@RestController
@RequestMapping("/config")
public class ConfigController extends BizController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConfigService configService;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO
     * <ConfigVo>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/page", notes = "获取系统参数配置数据集合，支持分页查询")

    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<ConfigVo> page(ConfigQVo condition, PageParam page) throws Exception {
        if(CommonUtils.isBlank(condition.getSyid())) {
            condition.setSyid(this.getCurUserProp().getActiveSyId());
        }
        PageDTO<ConfigVo> rst = this.configService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @Title:insertConfig
     * @Description: TODO(创建系统参数配置)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/create", notes = "创建系统参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Config的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Config obj = JSON.parseObject(jsons, Config.class);
        return this.configService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateConfig
     * @Description: TODO(更新系统参数配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/update", notes = "更新系统参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Config的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Config obj = JSON.parseObject(jsons, Config.class);
        return this.configService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统参数配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Config>
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 系统参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<ConfigVo> getById(String id) throws Exception {
        return this.configService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteConfigByConfigId
     * @Description: TODO(删除系统参数配置)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 系统参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.configService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统参数配置)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-17
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 系统参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @PostMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.configService.deleteByIds(ids.split(","));
    }

    /**
     * 获取指定系统 的key对象
     *
     * @param syId   系统识别ID
     * @param cfgKey key值
     * @return Config
     * @throws Exception
     */
    @ApiOperation(value = "获取指定系统的key对象", hidden = true)
    @GetMapping(value = "/feign/findByKey", produces = "application/json;charset=UTF-8")
    public Config findByKey(String syId, String cfgKey) throws Exception {
        return this.configService.findByKey(syId, cfgKey);
    }
}
