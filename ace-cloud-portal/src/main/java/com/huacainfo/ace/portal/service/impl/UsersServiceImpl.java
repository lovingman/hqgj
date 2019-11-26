package com.huacainfo.ace.portal.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ListDTO;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.UsersDao;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersQVo;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
/**
 * @author: 陈晓克
 * @version: 2019-05-12
 * @Description: TODO(系统用户)
 */
public class UsersServiceImpl implements UsersService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UsersDao usersDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(系统用户分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageDTO<UsersVo>
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @Override
    public PageDTO<UsersVo> page(UsersQVo condition, int start, int limit, String orderBy) throws Exception {
        PageDTO<UsersVo> rst = new PageDTO<>();
        List<UsersVo> list = this.usersDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.usersDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:create
     * @Description: TODO(创建系统用户)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @Override
    @Transactional
    @Log(operationObj = "系统用户", operationType = "创建", detail = "创建系统用户")
    public ResponseDTO create(Users o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getAccount())) {
            return new ResponseDTO(ResultCode.FAIL, "账号不能为空！");
        }
        if (CommonUtils.isBlank(o.getPassword())) {
            return new ResponseDTO(ResultCode.FAIL, "密码不能为空！");
        }
        if (o.getPassword().length()<6) {
            return new ResponseDTO(ResultCode.FAIL, "密码字符长度必须为6位以上！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getCorpId())) {
            o.setCorpId(userProp.getCorpId());
        }
        int temp = this.usersDao.isExit(o);
        if (temp > 0) {
            return new ResponseDTO(ResultCode.FAIL, "账号已经存在！");
        }
        o.setUserId(GUIDUtil.getGUID());
        o.setStatus("1");
        o.setCreateTime(new Date());
        o.setCurSyid(userProp.getActiveSyId());
        /**
         * 对密码进行BCrypt加密
         * */
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String pwd=encoder.encode(o.getPassword());
        o.setPassword(pwd);
        this.usersDao.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

    /**
     * @throws
     * @Title:update
     * @Description: TODO(更新系统用户)
     * @param: @param o
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @Override
    @Transactional
    @Log(operationObj = "系统用户", operationType = "变更", detail = "变更系统用户")
    public ResponseDTO update(Users o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getUserId())) {
            return new ResponseDTO(ResultCode.FAIL, "用户编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new ResponseDTO(ResultCode.FAIL, "姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getCorpId())) {
            o.setCorpId(userProp.getCorpId());
        }
        this.usersDao.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
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
    @Override
    @Transactional
    public ResponseDTO<UsersVo> getById(String id) throws Exception {
        ResponseDTO<UsersVo> rst = new ResponseDTO<>();
        rst.setData(this.usersDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteById
     * @Description: TODO(删除系统用户)
     * @param: @param id
     * @param: @throws Exception
     * @return: ResponseDTO
     * @author: 陈晓克
     * @version: 2019-05-12
     */
    @Override
    @Log(operationObj = "系统用户", operationType = "删除", detail = "删除系统用户")
    public ResponseDTO deleteById(String id) throws Exception {
        this.usersDao.deleteByPrimaryKey(id);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
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
    @Override
    @Transactional
    @Log(operationObj = "系统用户", operationType = "批量删除", detail = "批量删除系统用户")
    public ResponseDTO deleteByIds(String[] ids) throws Exception {
        this.usersDao.deleteByIds(ids);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
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
    @Override
    @Transactional
    @Log(operationObj = "系统用户", operationType = "初始化密码", detail = "初始化系统用户密码")
    public ResponseDTO initPwd(String userId,String password) throws Exception{
        if(CommonUtils.isBlank(userId)){
            return new ResponseDTO(ResultCode.FAIL, "用户编号不能为空！");
        }
        if(CommonUtils.isBlank(password)){
            return new ResponseDTO(ResultCode.FAIL, "密码不能为空！");
        }
        if (password.length()<6) {
            return new ResponseDTO(ResultCode.FAIL, "密码字符长度必须为6位以上！");
        }
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String pwd=encoder.encode(password);
        this.logger.info("new password -> {}",pwd);
        this.usersDao.initPwd(userId,pwd);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
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
    @Override
    @Transactional
    @Log(operationObj = "系统用户", operationType = "更新状态", detail = "更新状态")
    public ResponseDTO updateStatus(String userId,String status) throws Exception{
        if(CommonUtils.isBlank(userId)){
            return new ResponseDTO(ResultCode.FAIL, "用户编号不能为空！");
        }
        if(CommonUtils.isBlank(status)){
            return new ResponseDTO(ResultCode.FAIL, "状态不能为空！");
        }
        this.usersDao.updateStatus(userId,status);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
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
    @Override
    @Transactional
    @Log(operationObj = "系统用户", operationType = "分配角色", detail = "分配角色")
    public ResponseDTO assignRoles(String userId,String[] roleIds) throws Exception{
        if(CommonUtils.isBlank(userId)){
            return new ResponseDTO(ResultCode.FAIL, "用户编号不能为空！");
        }
        if(CommonUtils.isBlank(roleIds)){
            return new ResponseDTO(ResultCode.FAIL, "角色不能为空！");
        }
        this.usersDao.assignRoles(userId,roleIds);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
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
    @Override
    public ListDTO<Map<String,Object>> getAllRoles(String syid) throws Exception{
        ListDTO<Map<String, Object>> rst = new ListDTO(ResultCode.SUCCESS, "成功！");
        List data=this.usersDao.getAllRoles(syid);
        rst.setData(data);
        return rst;
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
    @Override
    public ListDTO<Map<String,Object>> getMyRoles(String userId) throws Exception{
        ListDTO<Map<String, Object>> rst = new ListDTO(ResultCode.SUCCESS, "成功！");
        List data=this.usersDao.getMyRoles(userId);
        rst.setData(data);
        return rst;
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
    @Override
   public ListDTO<Map<String,Object>> getList(Map<String,Object> p) throws Exception{
        this.logger.info("{}",p);
        ListDTO<Map<String,Object>> rst = new ListDTO<>();
        rst.setData(this.usersDao.getList(p));
        return rst;
   }
    @Override
    @Transactional
    @Log(operationObj = "系统用户", operationType = "密码修改", detail = "密码修改")
    public ResponseDTO updatePwd(String oldPwd,String newPwd,UserProp userProp) throws Exception {

        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();


        UsersVo vo= usersDao.selectVoByPrimaryKey(userProp.getUserId());
        if(!encoder.matches(oldPwd,vo.getPassword()))
            return new ResponseDTO(ResultCode.FAIL,"原密码错误！");

        if(encoder.matches(newPwd,vo.getPassword()))
            return new ResponseDTO(ResultCode.FAIL,"原密码和新密码相同！");

        if(CommonUtils.isEmpty(newPwd))
            return new ResponseDTO(ResultCode.FAIL,"密码不能为空！");
        if (newPwd.length() < 6)
            return new ResponseDTO(ResultCode.FAIL, "密码字符长度必须为6位以上！");
        if (newPwd.length() > 20)
            return new ResponseDTO(ResultCode.FAIL, "密码字符长度必须为20位以下！");

        String pwd = encoder.encode(newPwd);
        this.logger.info("new password -> {}", pwd);
        this.usersDao.initPwd(userProp.getUserId(), pwd);
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }

}
