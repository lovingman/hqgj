package com.huacainfo.ace.portal.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.Tree;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.service.DictService;
import com.huacainfo.ace.portal.service.SystemService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/www/system")
@Api(tags = "系统菜单接口")
public class WSystemControler extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemService systemService;
    @Autowired
    private DictService dictService;



    @ApiOperation(value = "获取系统按钮集合接口", notes = "ListDTO<Map<String, String>>")
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器接口异常", response = Exception.class),
    })
    @GetMapping(value = "/getSysButtons", produces = "application/json;charset=UTF-8")
    public ListDTO<Map<String, String>> getSysButtons() throws Exception {
        logger.info("SystemControler -> getSysButtons");
        return systemService.getSysButtons(this.getCurUserProp().getUserId());
    }

    /**
     * pid 上级行政区化编码
     * type 数据展示层级 5 省 4市 3县区 2 乡镇 1村
     * hasSelf 是否包含上级行政区化
     **/
    @ApiOperation(value = "获取系统行政区划树形结构数据", response = Tree.class)
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器接口异常", response = Exception.class),
    })
    @GetMapping(value = "/getAreaTree", produces = "application/json;charset=UTF-8")
    public ListDTO<Tree> getAreaTree(String pid, Integer type, String hasSelf) throws Exception {
        return this.systemService.getAreaTree(pid, type, hasSelf);
    }

    @GetMapping(value = "/getUserPropInfo", produces = "application/json;charset=UTF-8")
    public ResponseDTO getUserPropInfo() throws Exception {
        if(CommonUtils.isBlank(this.getCurUserProp())){
            return new ResponseDTO(ResultCode.FAIL,"失败");
        }else{
            ResponseDTO<UserProp> rst=new ResponseDTO(ResultCode.SUCCESS,"成功!");
            rst.setData(this.getCurUserProp());
            return rst;
        }
    }





    /**
     * pid 顶级行政区化编码
     * type 数据展示层级 5 省 4市 3县区 2 乡镇 1村;多级别请用，隔开
     * hasSelf 是否包含顶级行政区化
     **/
    @ApiOperation(value = "获取系统行政区划树形结构数据", response = Tree.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "顶级行政区化编码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "type", value = "数据展示层级 5 省 4市 3县区 2 乡镇 1村;多级别请用，隔开", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "hasSelf", value = "是否包含顶级行政区化", required = true, dataType = "String", paramType = "form"),
    })
    @ApiResponses({
            @ApiResponse(code = 500, message = "服务器接口异常", response = Exception.class),
    })
    @GetMapping(value = "/getNewAreaTree", produces = "application/json;charset=UTF-8")
    public ListDTO<Tree> getNewAreaTree(String pid, String type, String hasSelf) {
        pid = CommonUtils.isBlank(pid) ? "00" : pid;
        type = CommonUtils.isBlank(pid) ? "5,4" : type;

        return this.systemService.getNewAreaTree(pid, type, hasSelf);
    }
    @GetMapping(value = "/sessionOut", produces = "application/json;charset=UTF-8")
    public ResponseDTO sessionOut(){
        return new ResponseDTO(403,"会话超时！");
    }

    @ApiResponses({
            @ApiResponse(code = 500, message = "获取父行政区划", response = ResponseDTO.class),
    })
    @GetMapping(value = "/getPAreaCode", produces = "application/json;charset=UTF-8")
    public ResponseDTO<String[]> getPAreaCode(String areaCode) throws Exception {
        return this.systemService.getPAreaCode(areaCode);
    }



    /**
     * 根据字典分类ID获取数据字典对象，可以一次获取多个
     *
     * @param categoryIds 多个CategoryId
     * @return ResponseDTO<Map < String, List < DictVo>>>
     */
    @ApiOperation(value = "/getByCategoryIds",
            notes = "根据字典分类ID获取数据字典对象，可以一次获取多个",
            response = Dict.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryIds", value = "多个categoryIds以','分开", required = true, dataType = "String",
                    paramType = "query"),
    })
    @GetMapping(value = "/getByCategoryIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO<Map<String, List<Dict>>> getByCategoryIds(String categoryIds) throws Exception {
        if (CommonUtils.isBlank(categoryIds)) {
            return new ResponseDTO<>(ResponseDTO.FAILED, "缺少必要参数");
        }

        return this.dictService.getByCategoryIds(categoryIds.split(","));
    }

}
