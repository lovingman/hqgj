package com.huacainfo.ace.hqgj.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.CommonUtils;

import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.dao.*;
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;
import com.huacainfo.ace.hqgj.model.BaseOrganization;
import com.huacainfo.ace.hqgj.service.PersonalCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    public ResponseDTO bindUser(String id, String type, UserProp userProp) {
       Users user= registerDao.selectUserInfo(userProp.getUserId());
         //企业
        if(type.equals("1")){
            BaseCompanyMember o=new BaseCompanyMember();
                o.setId(userProp.getUserId());
             int temp = this.baseCompanyMemberDao.isExist(o);
             if (temp > 0) {
                return new ResponseDTO(ResultCode.FAIL, "已经存在！");
             }
                o.setCompanyId(id);
                o.setName(userProp.getName());
                o.setIdCard(user.getIdCard());
                o.setMobile(user.getMobile());
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setCreateUserName(userProp.getName());
                o.setCreateUserId(userProp.getUserId());
                o.setModifyDate(new Date());
               int i= this.baseCompanyMemberDao.insert(o);
            if(i<=0){
                return new ResponseDTO(ResultCode.FAIL, "绑定失败！");
            }
            registerDao.updateUserType("5",userProp.getUserId());
        }else{
            //机构
              String memberId=organizationMemberDao.existMember(id,user.getIdCard());
              if(CommonUtils.isBlank(memberId)){
                  return new ResponseDTO(ResultCode.FAIL, "绑定失败，请联系管理员先添加至该机构！");
              }
                int i=organizationMemberDao.updateUserId(userProp.getUserId(),id);
                 if(i<=0){
                     return new ResponseDTO(ResultCode.FAIL, "绑定失败！");
                 }
              BaseOrganization org =baseOrganizationDao.selectVoByPrimaryKey(id);
                 if(org!=null){
                     registerDao.updateUserType(org.getType(),userProp.getUserId());
                 }
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
        Users user= personCenterDao.selectUserInfo(userProp.getUserId());
       /* ResponseDTO dto=new ResponseDTO();
          if(user.getUserType().equals("5")){
              BaseCompanyMember m= baseCompanyMemberDao.selectVoByPrimaryKey(userProp.getUserId());
              dto= new ResponseDTO(ResultCode.SUCCESS, "成功！",m);
          } else if(user.getUserType().equals("1")||user.getUserType().equals("2")||user.getUserType().equals("3")){
            BaseOrganizationMember m= organizationMemberDao.selectByUserId(userProp.getUserId());
               dto= new ResponseDTO(ResultCode.SUCCESS, "成功！",m);
          }else{
              dto= new ResponseDTO(ResultCode.SUCCESS, "成功！",userProp);
          }*/
        return new ResponseDTO(ResultCode.SUCCESS, "成功！",user);
    }


}
