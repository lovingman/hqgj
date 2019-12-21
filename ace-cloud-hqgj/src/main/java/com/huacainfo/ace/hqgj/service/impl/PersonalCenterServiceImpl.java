package com.huacainfo.ace.hqgj.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.CommonUtils;

import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.hqgj.dao.BaseCompanyMemberDao;
import com.huacainfo.ace.hqgj.dao.BaseOrganizationMemberDao;
import com.huacainfo.ace.hqgj.dao.RegisterDao;
import com.huacainfo.ace.hqgj.model.BaseCompanyMember;
import com.huacainfo.ace.hqgj.service.PersonalCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 个人中心
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

    /**
     * 用户绑定企业或者机构
     * @param id
     * @param   userProp
     * @param type  1,企业 2 机构
     * @return
     */
    @Override
    public ResponseDTO bindUser(String id, String type, UserProp userProp) {
       Users user= registerDao.selectUserInfo(userProp.getUserId());
        if(type.equals("1")){
            BaseCompanyMember o=new BaseCompanyMember();
                o.setId(userProp.getUserId());
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
        }else{
              String memberId=organizationMemberDao.existMember(id,user.getIdCard());
              if(CommonUtils.isBlank(memberId)){
                  return new ResponseDTO(ResultCode.FAIL, "绑定失败，请联系管理员先添加至该机构！");
              }
                int i=organizationMemberDao.updateUserId(userProp.getUserId(),id);
                 if(i<=0){
                     return new ResponseDTO(ResultCode.FAIL, "绑定失败！");
                 }
      }
        return new ResponseDTO(ResultCode.SUCCESS, "绑定成功！");
    }
}
