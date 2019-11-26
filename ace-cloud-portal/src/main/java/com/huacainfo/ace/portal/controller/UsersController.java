package com.huacainfo.ace.portal.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.vo.PageParam;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersQVo;
import com.huacainfo.ace.portal.vo.UsersVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
/**
 * @author: 陈晓克
 * @version: 2019-05-12
 * @Description: TODO(系统用户)
 */
@Api(tags = "系统用户接口")
public class UsersController extends BaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersService usersService;

    /**
     * @throws
     * @Title:page
     * @Description: TODO(分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageDTO<UsersVo>
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @GetMapping(value = "/page", produces = "application/json;charset=UTF-8")
    public PageDTO<UsersVo> page(UsersQVo condition, PageParam page) throws Exception {
        if (StringUtils.isEmpty(condition.getAreaCode())) {
            condition.setAreaCode(this.getCurUserProp().getAreaCode());
        }
       /* if (StringUtils.isEmpty(condition.getCorpId())) {
            condition.setCorpId(this.getCurUserProp().getCorpId());
        }*/
        PageDTO<UsersVo> rst = this.usersService.page(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (page.getStart()>1) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertUsers
     * @Description: TODO(创建系统用户)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseDTO create(String jsons) throws Exception {
        Users obj = JSON.parseObject(jsons, Users.class);
        return this.usersService.create(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateUsers
     * @Description: TODO(更新系统用户)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseDTO update(String jsons) throws Exception {
        Users obj = JSON.parseObject(jsons, Users.class);
        return this.usersService.update(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:getById
     * @Description: TODO(获取系统用户)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO<Users>
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @GetMapping(value = "/getById", produces = "application/json;charset=UTF-8")
    public ResponseDTO<UsersVo> getById(String id) throws Exception {
        return this.usersService.getById(id);
    }

    /**
     * @throws
     * @Title:deleteUsersByUsersId
     * @Description: TODO(删除系统用户)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @GetMapping(value = "/deleteById", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteById(String id) throws Exception {
        return this.usersService.deleteById(id);
    }


    /**
     * @throws
     * @Title:deleteByIds
     * @Description: TODO(批量删除系统用户)
     * @param: @param ids
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @GetMapping(value = "/deleteByIds", produces = "application/json;charset=UTF-8")
    public ResponseDTO deleteByIds(String ids) throws Exception {
        return this.usersService.deleteByIds(ids.split(","));
    }


    /**
     * @throws
     * @Title:initPwd
     * @Description: TODO(初始化密码)
     * @param: @param userId
     * @param: @param password
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @PostMapping(value = "/initPwd", produces = "application/json;charset=UTF-8")
    public ResponseDTO initPwd(String userId, String password) throws Exception {
        return this.usersService.initPwd(userId, password);
    }


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param userId
     * @param: @param status
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @GetMapping(value = "/updateStatus", produces = "application/json;charset=UTF-8")
    public ResponseDTO updateStatus(String userId, String status) throws Exception {
        return this.usersService.updateStatus(userId, status);
    }


    /**
     * @throws
     * @Title:assignRoles
     * @Description: TODO(分配角色)
     * @param: @param userId
     * @param: @param roleIds
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @PostMapping(value = "/assignRoles", produces = "application/json;charset=UTF-8")
    public ResponseDTO assignRoles(String userId, String roleId) throws Exception {
        if (CommonUtils.isBlank(userId)) {
            return new ResponseDTO(ResultCode.FAIL, "用户编号不能为空！");
        }
        if (CommonUtils.isBlank(roleId)) {
            return new ResponseDTO(ResultCode.FAIL, "角色编号不能为空！");
        }
        return this.usersService.assignRoles(userId, roleId.split(","));
    }


    /**
     * @throws
     * @Title:getAllRoles
     * @Description: TODO(获取系统已有角色)
     * @param: @param userId
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @GetMapping(value = "/getAllRoles", produces = "application/json;charset=UTF-8")
    public ListDTO<Map<String, Object>> getAllRoles() throws Exception {
        return this.usersService.getAllRoles(this.getCurUserProp().getActiveSyId());
    }


    /**
     * @throws
     * @Title:getMyRoles
     * @Description: TODO(获取当前用户已分配角色)
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @GetMapping(value = "/getMyRoles", produces = "application/json;charset=UTF-8")
    public ListDTO<Map<String, Object>> getMyRoles(String userId) throws Exception {
        return this.usersService.getMyRoles(userId);
    }

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(用户查询，主要用于可搜索下拉框)
     * @param: @param p
     * @param: @throws Exception
     * @return: ListDTO<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-07-23
     */

    @GetMapping(value = "/getList", produces = "application/json;charset=UTF-8")
    public ListDTO<Map<String, Object>> getList(String name,String corpId) throws Exception {

        Map<String, Object> p = new HashMap<>();
        p.put("corpId", corpId);
        p.put("name", name);
        p.put("areaCode", this.getCurUserProp().getAreaCode());
        if(CommonUtils.isBlank(corpId)){
            p.put("corpId", this.getCurUserProp().getCorpId());
        }
        return this.usersService.getList(p);
    }

    /**
     * @throws
     * @Title:initPwd
     * @Description: TODO(初始化密码)
     * @param: @param userId
     * @param: @param password
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @PostMapping(value = "/updatePwd", produces = "application/json;charset=UTF-8")
    public ResponseDTO updatePwd(String oldPwd,String newPwd) throws Exception {
        return this.usersService.updatePwd(oldPwd, newPwd,this.getCurUserProp());
    }

}
