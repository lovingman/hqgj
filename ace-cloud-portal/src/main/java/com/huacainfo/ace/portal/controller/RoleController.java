package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.component.CustomInvocationSecurityMetadataSource;
import com.huacainfo.ace.common.vo.CheckTree;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.portal.controller.base.BizController;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.service.RoleService;
import com.huacainfo.ace.portal.vo.RoleQVo;
import com.huacainfo.ace.portal.vo.RoleVo;
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

import java.util.List;


/**
 * @author: ArvinZou
 * @version: 2019-05-18
 * @Description: TODO(用户角色)
 */
@Api(value = "/role", tags = "用户角色")
@RestController
@RequestMapping("/role")
public class RoleController extends BizController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleService roleService;
    @Autowired
    private CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;


    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO
     * <RoleVo>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/page", notes = "获取用户角色数据集合，支持分页查询；只能获取当前登录用户的同系统角色")
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<RoleVo> page(RoleQVo condition, PageParam page) throws Exception {
        condition.setSyid(getCurUserProp().getActiveSyId());
        PageDTO<RoleVo> rst = this.roleService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @Title:insertRole
     * @Description: TODO(创建用户角色)
     * @param: @param jsons
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/create", notes = "创建用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Role的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @GetMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Role obj = JSON.parseObject(jsons, Role.class);
        return this.roleService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateRole
     * @Description: TODO(更新用户角色)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/update", notes = "更新用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsons", value = "Role的json化对象", required = true, dataType = "String",
                    paramType = "form"),
    })
    @GetMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Role obj = JSON.parseObject(jsons, Role.class);
        return this.roleService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取用户角色)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Role>
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/getById", notes = "根据主键获取 用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<RoleVo> getById(String id) throws Exception {
        return this.roleService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteRoleByRoleId
     * @Description: TODO(删除用户角色)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/deleteById", notes = "根据主键删除 用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一主键", required = true, dataType = "String", paramType = "form"),
    })
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.roleService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除用户角色)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: ArvinZou
     * @version: 2019-05-18
     */
    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个ID以','分开", required = true, dataType = "String", paramType =
                    "form"),
    })
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.roleService.deleteByIds(ids.split(","));
    }

    @ApiOperation(value = "/deleteByIds", notes = "根据主键批量删除 用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resId", value = "资源ID", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/insertRoleRes", produces = "application/json;charset=UTF-8")
    public ResponseDTO insertRoleResources(String roleId, String resId) throws Exception {

        String[] resourcesIds = null;
        String temp = resId;
        if (temp != null && temp.indexOf(",") != -1) {
            resourcesIds = temp.split(",");
        }
        if (temp != null && temp.indexOf(",") == -1) {
            resourcesIds = new String[]{temp};
        }
        ResponseDTO rst = this.roleService.insertRoleResources(roleId, resourcesIds, this.getCurUserProp());
        customInvocationSecurityMetadataSource.loadResourceDefine();
        return rst;
    }

    @ApiOperation(value = "/getRoleResTreeList", notes = "获取角色资源树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/getRoleResTreeList", produces = "application/json;charset=UTF-8")
    public List<CheckTree> getRoleResTreeList(String roleId) {
        List<CheckTree> list = this.roleService.getRoleResTreeList(roleId);
        return list;
    }


    @ApiOperation(value = "/selectRoleResByRoleId", notes = "角色编号获取已经分配的资源ID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping(value = "/selectRoleResByRoleId", produces = "application/json;charset=UTF-8")
    public ResponseDTO<String[]> selectRoleResByRoleId(String roleId) throws Exception{
      return this.roleService.selectRoleResByRoleId(roleId);
    }
}
