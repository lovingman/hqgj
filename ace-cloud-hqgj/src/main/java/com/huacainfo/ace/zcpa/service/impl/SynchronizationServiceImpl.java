package com.huacainfo.ace.zcpa.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.security.model.Users;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.zcpa.dao.*;
import com.huacainfo.ace.zcpa.model.*;
import com.huacainfo.ace.zcpa.service.SynchronizationService;
import com.huacainfo.ace.zcpa.synchronization.model.*;
import com.huacainfo.ace.zcpa.synchronization.tool.SearchToolKit;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SynchronizationServiceImpl implements SynchronizationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SqlSessionTemplate sqlSession;
    @Override
    /**
     * 被服务学校信息
     */
    @Transactional
    public ResponseDTO syncSchoolData(String srartTime) {
             if(isValidDate(srartTime)==false){
                 return new ResponseDTO(ResultCode.FAIL, "参数错误");
             }
        Thread thread=null;
             try {
                 thread = new Thread(new Runnable() {
                     @Override
                     public void run() {

                         List<School> schoolList = null;
                         try {
                             schoolList = SearchToolKit.getSchoolData(srartTime);
                         } catch (Exception e) {
                             logger.error("[syncSchoolData]接口获取数据异常=>{}", e);
                             return;
                         }
                         SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
                         int i = 0;
                         if (!CommonUtils.isBlank(schoolList)) {
                             try {
                                 ActSchoolDao actSchoolDao = session.getMapper(ActSchoolDao.class);
                                 ActPersonDao actPersonDao = session.getMapper(ActPersonDao.class);
                                 for (School s : schoolList) {
                                     ActSchool school = new ActSchool();
                                     school.setId(s.getId());
                                     school.setSchoolName(s.getChineseName());
                                     school.setDetailAddr(s.getAddress());
                                     school.setPhone(s.getMobileNumber());
                                     Province province=actPersonDao.selcectAreaCode(s.getDepartmentNo());
                                     if(CommonUtils.isBlank(province)){
                                         continue;
                                     }else{
                                         if(province.getType().equals("0")){
                                             school.setAreaCode(province.getParentCode());
                                         }else{
                                             school.setAreaCode(province.getCode());
                                         }
                                     }

                                     school.setCreateDate(new Date());
                                     school.setLastModifyDate(s.getUpdateDate());
                                     int temp = actSchoolDao.isExist(school);
                                     if (temp > 0) {
                                         actSchoolDao.updateByPrimaryKey(school);
                                     } else {
                                         actSchoolDao.insert(school);
                                     }
                                     i++;
                                     if (i % 200 == 0) {
                                         session.commit();
                                     }
                                 }
                                 session.commit();
                             } catch (Exception e) {
                                 logger.error("School批量保存失败：", e);
                                 session.rollback();
                             } finally {
                                 session.clearCache();
                                 session.close();
                             }
                         }
                     }
                 });
                 thread.start();
             }catch (Exception e){
                 logger.error("School批量保存失败：", e);
                 return new ResponseDTO(ResultCode.FAIL, "被服务学校信息同步异常");
             }
        return new ResponseDTO(ResultCode.SUCCESS, "被服务学校信息同步成功");
    }

    /**
     * 特殊人群信息
     * @return
     */
    @Override
    @Transactional
    public ResponseDTO syncPersonData(String srartTime) {
        if(isValidDate(srartTime)==false){
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        Thread thread=null;
        try {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Person> personList = null;
                    try {
                        personList = SearchToolKit.getPersonData(srartTime);
                    } catch (Exception e) {
                        logger.error("[syncPersonData]接口获取数据异常=>{}", e);
                        return;
                    }
                    SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
                    int i = 0;
                    if (!CommonUtils.isBlank(personList)) {
                        try {
                            ActPersonDao actPersonDao = session.getMapper(ActPersonDao.class);
                            for (Person p : personList) {
                                ActPerson person = new ActPerson();
                                person.setId(p.getId());
                                person.setPersonName(p.getName());
                                person.setSex(p.getGender() == 96 ? "1" : "2");
                                person.setIdCard(p.getIdCardNo());
                                person.setPhone(p.getMobileNumber());
                                person.setNowAddr(p.getCurrentAddress());
                                person.setCateogry(p.getType());
                                Province province=actPersonDao.selcectAreaCode(p.getDepartmentNo());
                                if(CommonUtils.isBlank(province)){
                                    continue;
                                }else{
                                    if(province.getType().equals("0")){
                                        person.setAreaCode(province.getParentCode());
                                    }else{
                                        person.setAreaCode(province.getCode());
                                    }
                                }
                                person.setCreateDate(new Date());
                                person.setLastModifyDate(p.getUpdateDate());
                                int temp = actPersonDao.isExist(person);
                                if (temp > 0) {
                                    actPersonDao.updateByPrimaryKey(person);
                                } else {
                                    actPersonDao.insert(person);
                                }
                                i++;
                                if (i % 500 == 0) {
                                    session.commit();
                                }
                            }
                            session.commit();
                        } catch (Exception e) {
                            logger.error("Person批量保存失败：", e);
                            session.rollback();
                            return;
                        } finally {
                            session.clearCache();
                            session.close();
                        }
                    }
                }
            });
            thread.start();
        }catch (Exception e){
            logger.error("Person批量保存失败：", e);
            return new ResponseDTO(ResultCode.FAIL, "特殊人群信息同步异常");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "特殊人群信息同步成功");
    }

    /**
     * 社会组织数据同步
     * @return
     */
    @Override
    @Transactional
    public ResponseDTO syncSocietyOrganizationData(String srartTime) {
        if(isValidDate(srartTime)==false){
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        Thread thread=null;
        try {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<SocietyOrganization> organizationList = null;
                    try {
                        organizationList = SearchToolKit.getSocietyOrganizationData(srartTime);
                    } catch (Exception e) {
                        logger.error("[syncSocietyOrganizationData]接口获取数据异常=>{}", e);
                        return;
                    }
                    SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
                    int i = 0;
                    if (!CommonUtils.isBlank(organizationList)) {
                        try {
                            BaseSocialOrgDao baseSocialOrgDao = session.getMapper(BaseSocialOrgDao.class);
                            ActPersonDao actPersonDao = session.getMapper(ActPersonDao.class);
                            String[] assetIds = new String[organizationList.size()];
                            int j = 0;
                            for (SocietyOrganization p : organizationList) {
                                assetIds[j++] = p.getId();
                            }
                            //先清除已有的数据
                            baseSocialOrgDao.deleteByIds(assetIds);
                            for (SocietyOrganization o : organizationList) {
                                BaseSocialOrg org = new BaseSocialOrg();
                                org.setId(o.getId());
                                org.setOrgName(o.getName());
                                org.setDetailAddr(o.getAddress());
                                org.setDutyName(o.getLegalPerson());
                                org.setPhone(o.getLegalPersonTelephone());
                                org.setUniCreditCode(o.getRegistrationNumber());
                                org.setCategory(o.getType());
                                Province province=actPersonDao.selcectAreaCode(o.getDepartmentNo());
                                if(CommonUtils.isBlank(province)){
                                    continue;
                                }else{
                                    if(province.getType().equals("0")){
                                        org.setAreaCode(province.getParentCode());
                                    }else{
                                        org.setAreaCode(province.getCode());
                                    }
                                }
                                org.setCreateDate(new Date());
                                org.setLastModifyDate(o.getUpdateDate());
                                int temp = baseSocialOrgDao.isExist(org);
                                if (temp > 0) {
                                    baseSocialOrgDao.updateByPrimaryKey(org);
                                } else {
                                    baseSocialOrgDao.insert(org);
                                }
                                i++;
                                if (i % 200 == 0) {
                                    session.commit();
                                }
                            }
                            session.commit();
                        } catch (Exception e) {
                            logger.error("SocietyOrganization批量保存失败：", e);
                            session.rollback();
                            return ;
                        } finally {
                            session.clearCache();
                            session.close();
                        }
                    }
                }
            });
            thread.start();
        }catch (Exception e){
            logger.error("SocietyOrganization批量保存失败：", e);
            return new ResponseDTO(ResultCode.FAIL, "社会组织数据同步异常");
        }
        return new ResponseDTO(ResultCode.SUCCESS, "社会组织数据同步成功");
    }

    /**
     * 同步用户信息
     * @return
     */
    @Override
    @Transactional
    public ResponseDTO syncUserData(String srartTime) {
        if(isValidDate(srartTime)==false){
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        Thread thread=null;
        try {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<UserInfo> userInfoList = null;
                    try {
                        userInfoList = SearchToolKit.getUserData(srartTime);
                    } catch (Exception e) {
                        logger.error("[syncUserData]接口获取数据异常=>{}", e);
                        return;
                    }
                    SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
                    int i = 0;
                    if (!CommonUtils.isBlank(userInfoList)) {
                        try {
                            RegisterDao registerDao = session.getMapper(RegisterDao.class);
                            ActPersonDao actPersonDao = session.getMapper(ActPersonDao.class);
                            for (UserInfo o : userInfoList) {
                                Users user = new Users();
                                String roleId="";
                                user.setUserId(o.getId());
                                Province province=actPersonDao.selcectAreaCode(o.getDepartmentNo());
                                if(CommonUtils.isBlank(province)){
                                    continue;
                                }else{
                                    if(province.getType().equals("0")){
                                        user.setAreaCode(province.getParentCode());
                                    }else{
                                        user.setAreaCode(province.getCode());
                                    }
                                    switch (province.getType()){
                                        case "1":
                                            roleId="1572510811755";
                                            break;
                                        case "2":
                                            roleId="1572510794332";
                                            break;
                                        case "3":
                                            roleId="1566529935559";
                                            break;
                                        case "4":
                                            roleId="1";
                                            break;
                                        default:
                                            roleId="1572510830967";
                                    }
                                }
                            //    user.setMobile(o.getMobile());
                                user.setName(o.getName());
                                user.setPassword(o.getPassword());
                                user.setAccount(o.getUserName());
                                user.setDeptId(o.getOrganizationId());
                                user.setCorpId("0001");
                                user.setStatus("1");
                                user.setIsSync("y");
                                user.setCurSyid("zcpa");
                                user.setCreateTime(o.getUpdateDate());

                                int temp = registerDao.isExist(user);
                                //   int temps = registerDao.userIsExist(user);
                                if (temp > 0) {
                                    registerDao.updateUser(user);
                                } else {
                                    registerDao.insertUser(user);
                                    registerDao.insertUserRole(user.getUserId(),roleId);
                                }
                                i++;
                                if (i % 1000 == 0) {
                                    session.commit();
                                }
                            }
                            session.commit();
                        } catch (Exception e) {
                            logger.error("user批量保存失败：", e);
                            session.rollback();
                            return;
                        } finally {
                            session.clearCache();
                            session.close();
                        }
                    }
                }
            });
            thread.start();
        }catch (Exception e){
            logger.error("user批量保存失败：", e);
            return new ResponseDTO(ResultCode.FAIL, "用户信息同步异常");
        }

        return new ResponseDTO(ResultCode.SUCCESS, "用户信息同步成功");
    }


    /**
     * 平安单位数据同步
     * @return
     */
    @Override
    @Transactional
    public ResponseDTO syncUnitData(String srartTime) {
        if(isValidDate(srartTime)==false ){
            return new ResponseDTO(ResultCode.FAIL, "参数错误");
        }
        Thread thread=null;
        try {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Unit> unitList = null;
                    try {
                        unitList = SearchToolKit.getUnitData(srartTime);
                    } catch (Exception e) {
                        logger.error("[syncUnitData]接口获取数据异常=>{}", e);
                        return;
                    }
                    SqlSession session = sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
                    int i = 0;
                    if (!CommonUtils.isBlank(unitList)) {
                        try {
                            BaseUnitDao baseUnitDao = session.getMapper(BaseUnitDao.class);
                            ActPersonDao actPersonDao = session.getMapper(ActPersonDao.class);
                            String[] assetIds = new String[unitList.size()];
                            int j = 0;
                            for (Unit u : unitList) {
                                assetIds[j++] = u.getId();
                            }
                            //先清除已有的数据
                            baseUnitDao.deleteByIds(assetIds);
                            for (Unit o : unitList) {
                                BaseUnit unit = new BaseUnit();
                                unit.setId(o.getId());
                                unit.setName(o.getName());
                                unit.setAddress(o.getAddress());
                                unit.setTelephone(o.getTelephone());
                                Province province=actPersonDao.selcectAreaCode(o.getDepartmentNo());
                                if(CommonUtils.isBlank(province)){
                                    continue;
                                }else{
                                    if(province.getType().equals("0")){
                                        unit.setAreaCode(province.getParentCode());
                                    }else{
                                        unit.setAreaCode(province.getCode());
                                    }
                                }
                                unit.setCreateDate(new Date());
                                unit.setLastModifyDate(o.getUpdateDate());
                                int temp = baseUnitDao.isExist(unit);
                                if (temp > 0) {
                                    baseUnitDao.updateByPrimaryKey(unit);
                                } else {
                                    baseUnitDao.insert(unit);
                                }
                                i++;
                                if (i % 200 == 0) {
                                    session.commit();
                                }
                            }
                            session.commit();
                        } catch (Exception e) {
                            logger.error("Unit批量保存失败：", e);
                            session.rollback();
                            return;
                        } finally {
                            session.clearCache();
                            session.close();
                        }
                    }
                }

        });
        thread.start();
    }catch (Exception e){
        logger.error("syncUnitData批量保存失败：", e);
        return new ResponseDTO(ResultCode.FAIL, "平安单位数据同步异常");
    }

        return new ResponseDTO(ResultCode.SUCCESS, "平安单位数据同步成功");
    }


    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        if(CommonUtils.isBlank(str)){
            return convertSuccess;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(str);
        } catch (Exception e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }

}
