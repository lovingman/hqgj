package com.huacainfo.ace.hqgj.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.CommonUtils;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.dao.*;
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;
import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.model.MapWechatSys;
import com.huacainfo.ace.hqgj.model.WechatConfig;
import com.huacainfo.ace.hqgj.service.PersonalCenterService;
import com.huacainfo.ace.hqgj.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: 何双
 * @version: 2019-12-020
 * @Description: TODO(个人中心相关接口)
 */
@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BaseCompanyMemberDao baseCompanyMemberDao;
    @Resource
    private RegisterDao registerDao;
    @Resource
    private BaseOrganizationMemberDao organizationMemberDao;
    @Resource
    private BaseOrganizationDao baseOrganizationDao;
    @Resource
    private PersonCenterDao personCenterDao;
    /**
     * 用户绑定企业或者机构
     * @param id 企业id或机构id
     * @param   userProp
     * @param type  1,企业 2 机构
     * @return
     */
    @Override
    @Transactional
    public ResponseDTO bindUser(String id, String type, UserProp userProp) {
        try {
            Users user = registerDao.selectUserInfo(userProp.getUserId());
            //企业
            if (type.equals("1")) {
                BaseCompanyMember o = new BaseCompanyMember();
                o.setId(userProp.getUserId());
                int temp = this.baseCompanyMemberDao.isExist(o);
                if (temp > 0) {
                    o.setCompanyId(id);
                    o.setName(userProp.getName());
                    o.setIdCard(user.getIdCard());
                    o.setMobile(user.getMobile());
                    o.setCreateDate(new Date());
                    o.setStatus("1");
                    o.setCreateUserName(userProp.getName());
                    o.setCreateUserId(userProp.getUserId());
                    o.setModifyDate(new Date());
                    this.baseCompanyMemberDao.updateByPrimaryKey(o);
                } else {
                    o.setCompanyId(id);
                    o.setName(userProp.getName());
                    o.setIdCard(user.getIdCard());
                    o.setMobile(user.getMobile());
                    o.setCreateDate(new Date());
                    o.setStatus("1");
                    o.setCreateUserName(userProp.getName());
                    o.setCreateUserId(userProp.getUserId());
                    o.setModifyDate(new Date());
                    int i = this.baseCompanyMemberDao.insert(o);
                    if (i <= 0) {
                        return new ResponseDTO(ResultCode.FAIL, "绑定失败！");
                    }
                }
                registerDao.updateUserType("5", userProp.getUserId(), null);
            } else {
                //机构
                String memberId = organizationMemberDao.existMember(id, user.getIdCard());
                if (CommonUtils.isBlank(memberId)) {
                    return new ResponseDTO(ResultCode.FAIL, "绑定失败，请联系管理员先添加至该机构！");
                }
                int i = organizationMemberDao.updateUserId(userProp.getUserId(), memberId);
                if (i <= 0) {
                    return new ResponseDTO(ResultCode.FAIL, "绑定失败！");
                }
                BaseOrganization org = baseOrganizationDao.selectVoByPrimaryKey(id);
                if (org != null) {
                    registerDao.updateUserType(org.getType(), userProp.getUserId(), id);
                }
            }
        }catch (Exception e) {
            return new ResponseDTO(ResultCode.FAIL, "绑定失败,系统异常！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "绑定成功！");
    }


    /**
     * 个人中心
     * @param userProp
     * @return
     */
    @Override
    public ResponseDTO homePage(UserProp userProp) {
        UsersVo user=null;
        try {
             user = personCenterDao.selectUserInfo(userProp.getUserId());
        }catch (Exception e){
            return new ResponseDTO(ResultCode.FAIL, "用户信息获取失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！",user);
    }


    /**
     * 解除绑定
     * @param userProp
     * @return
     */
    @Override
    public ResponseDTO relieveBind(UserProp userProp) {
        try {
        UsersVo user= personCenterDao.selectUserInfo(userProp.getUserId());
        if(CommonUtils.isBlank(user.getUserType())) {
            return new ResponseDTO(ResultCode.FAIL, "未找到绑定数据！");
        }
            if (user.getUserType().equals("5")) {
                baseCompanyMemberDao.deleteByIds(user.getUserId().split(","));
            } else if (user.getUserType().equals("1") || user.getUserType().equals("2") || user.getUserType().equals("3")) {
                try {
                    String memberId=organizationMemberDao.existMember(user.getOrgId(),user.getIdCard());
                    int i = organizationMemberDao.updateUserId(null, memberId);
                } catch (Exception e) {
                    return new ResponseDTO(ResultCode.FAIL, "绑定失败！");
                }
            }
            registerDao.updateUserType(null, userProp.getUserId(),null);
        }catch (Exception e){
            return new ResponseDTO(ResultCode.FAIL, "绑定失败,系统异常！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "解除成功！");
    }


    /**
     * 解绑微信
     * @param userProp
     * @return
     */
    @Override
    public ResponseDTO relieveByUnionId(UserProp userProp) {
        UsersVo user= personCenterDao.selectUserInfo(userProp.getUserId());
        if(CommonUtils.isBlank(user.getUnionId())) {
            return new ResponseDTO(ResultCode.SUCCESS, "数据获取失败！");
        }
       int i= personCenterDao.deleteByUnionId(user.getUnionId());
        if (i <= 0) {
            return new ResponseDTO(ResultCode.FAIL, "解除失败！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "解除成功！");
    }


    /**
     * 绑定微信
     * @param userProp
     * @return
     */
    @Override
    public ResponseDTO bindUnionId(UserProp userProp,String unionId){
        try {
            WechatConfig config = registerDao.findBySysId();
            if (config == null || CommonUtils.isEmpty(config.getAppId())) {
                return new ResponseDTO(ResultCode.FAIL, "未找到数据！");
            }
            int userId=registerDao.isUserId(userProp.getUserId());
            if (userId > 0) {
                return new ResponseDTO(ResultCode.FAIL, "用户已经绑定微信！");
            }
            int temp = registerDao.isUnionid(unionId);
            if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "微信已经绑定用户！");
            } else {
                MapWechatSys sys = new MapWechatSys();
                sys.setId(GUIDUtil.getGUID());
                sys.setUserId(userProp.getUserId());
                sys.setUnionId(unionId);
                sys.setCreateDate(new Date());
                sys.setAppId(config.getAppId());
                sys.setSysId("hqgj");
                int i = registerDao.insertMapWechatSys(sys);
                if (i <= 0) {
                    return new ResponseDTO(ResultCode.FAIL, "绑定失败！");
                }
            }
        }catch (Exception e){
            return new ResponseDTO(ResultCode.FAIL, "绑定失败,系统异常！");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "成功！");
    }
}
